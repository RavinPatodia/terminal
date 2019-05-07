package com.langmy.terminal.modules.monitor.service;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.langmy.terminal.common.config.Constant;
import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.entity.Account;
import com.langmy.terminal.common.entity.AdminssionRec;
import com.langmy.terminal.common.entity.Bayonet;
import com.langmy.terminal.common.entity.Blacklist;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.entity.Driveway;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.entity.ParkingEnvir;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.entity.UserPSp;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.modules.caradmission.utils.AdmissionRecUtils;
import com.langmy.terminal.modules.caradmission.utils.CarUtils;
import com.langmy.terminal.modules.device.utils.BrakeMachineUtils;
import com.langmy.terminal.modules.device.utils.DrivewayUtils;
import com.langmy.terminal.modules.device.utils.LedScreenUtils;
import com.langmy.terminal.modules.device.utils.LockUtils;
import com.langmy.terminal.modules.device.utils.ParkUtils;
import com.langmy.terminal.modules.monitor.model.BlacklistModel;
import com.langmy.terminal.modules.monitor.model.CarModel;
import com.langmy.terminal.modules.monitor.model.EnterModel;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;
import com.langmy.terminal.modules.user.utils.BlacklistUtils;
import com.langmy.terminal.modules.user.utils.UserPSpUtils;
import com.langmy.terminal.modules.user.utils.UserUtils;

/**
 * 入场业务层
 * 
 * @author lzy
 *
 */
@Service
public class EnterService {

	private static Logger logger = LoggerFactory.getLogger(EnterService.class);

	/**
	 * 入场方法
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public EnterModel enter(EnterModel model) throws ServiceException {
		CarModel carModel = model.getCarModel();
		String licensePlate = carModel.getLicensePlate();
		licensePlateVerify(model);
		User user = UserUtils.findUserById(model.getUserPK());
		int drivewayId = model.getDrivewayPK();
		// 判断停车权限问题
		boolean inFlag = validateUserPermission(user, model.getDrivewayPK());
		if (!inFlag) {
			model.setPermissionFlag(false);
			sendLedMsg(drivewayId, "无权限进场 "+licensePlate);
			model.setMsg("无权限进场 "+licensePlate);
			return model;
		}
		// 判断是否是黑名单
		if(!validateBlacklistInPermission(model, user)){
			model.setMsg("黑名单车牌  "+licensePlate +" 禁止入场");
			return model;
		}
		// 根据车牌号判断车辆对应用户类型
		int userType = CarUtils.getUserTypeByLicensePlate(licensePlate);
		// 判断车辆是临时、普通还是长期，分别进入不同的流程中
		if (userType == Constant.UserGroupType.TEMPORARYUSER.getValue())
			enterTemporaryUser(model);
		else if (userType == Constant.UserGroupType.MEMBER_COMMON.getValue())
			enterCommonUser(model);
		else if (userType == Constant.UserGroupType.LONGTREM.getValue())
			enterLongTermUser(model);
		openBrakeMachine(model);
		model.setInFlag(true);
		model.setMsg(licensePlate + " 入场成功");
		if (!addAdmissionRecAndCopyPropertyByModel(model))// 若是拥有固定车位的会员入场则开启固定车位上的车位锁，并把要停的固定车位的主键和编号放入到enterModel中。
			throw new ServiceException("入场管理-保存车辆入场记录失败");
		return model;
	}

	private boolean validateBlacklistInPermission(EnterModel model, User user)
			throws ServiceException {
		if (BlacklistUtils.isBlack(user)) {
			model.setBlacklistFlag(true);
			Blacklist blacklist = BlacklistUtils.getEffectBlacklist(user);
			copyBlacklistToModel(blacklist, model);
			// 判断是否可以进停车场
			if (!blacklist.isInFlag()) {
				logger.info("用户为不能进场的黑名单，拒绝进场");
				// TODO 语音、短信提示黑名单信息
				model.getLedMsg().add(
						"黑名单用户 " + "黑名单列入时间" + blacklist.getListTime()
								+ " 黑名单列入时间");
				sendLedMsg(model.getDrivewayPK(), "黑名单用户不允许进场 "+model.getCarModel().getLicensePlate());
				return false;
			}
		}
		return true;
	}

	/**
	 * 根据车道编号控制显示屏显示信息
	 * 
	 * @param drivewayId
	 *            车道Id
	 * @param msg
	 *            显示屏显示信息
	 * @return
	 */
	private void sendLedMsg(int drivewayId, String msg) {
		try {
			LedScreenUtils.setoneLineMessageByDrivewayId(drivewayId, msg);
		} catch (IOException e) {
			logger.error("入口信息显示屏显示入场信息失败");
		}
	}

	/**
	 * 开启闸机
	 * 
	 * @param model
	 * @throws ServiceException
	 */
	private void openBrakeMachine(EnterModel model) throws ServiceException {
		try {
			Device brakeMachine = BrakeMachineUtils
					.getBrakeMachineByDriveway(model.getDrivewayPK());
			if (brakeMachine == null) {
				logger.info("车道未绑定闸机");
				model.getErrorMsg().add("车道未绑定闸机");
				return;
			}
			if (!BrakeMachineUtils.openBrakeMachine(brakeMachine)) {
				model.getErrorMsg().add("闸机" + brakeMachine.getName() + "开启失败");
				logger.error("闸机" + brakeMachine.getName() + "开启失败");
			} else {
				logger.info("闸机" + brakeMachine.getName() + "开启成功");
				model.setOpenBrakeMachineFlag(true);
			}
		} catch (AWTException | IOException e) {
			throw new ServiceException("入场开闸失败");
		}
	}

	/**
	 * 判断用户是否有进入该停车场的权限,只要用户拥有停车场以及其子停车场的任意一个权限，则用户可以进入
	 * 
	 * @param user
	 *            用户
	 * @param driveWayId
	 *            车道编号
	 * @return true 用户有进入该停车场的权限 false没有权限
	 */
	private boolean validateUserPermission(User user, Integer driveWayId) {
		Driveway driveWay = DrivewayUtils.getDrivewayById(driveWayId);
		Bayonet bayonet = driveWay.getBayonet();
		Park park = bayonet.getPark();
		List<Park> parks = user.getUGroup().getParks();
		if (ListUtils.isNullOrEmpty(parks))
			return false;
		List<Park> inParkAndChildren = ParkUtils.getParkChildrenByPark(park);
		if (parks.contains(park))
			return true;
		for (Park child : inParkAndChildren) {
			if (parks.contains(child))
				return true;
		}
		return false;
	}

	/**
	 * @param model
	 * @throws ServiceException
	 */
	private void licensePlateVerify(EnterModel model) throws ServiceException {
		CarModel carModel = model.getCarModel();
		User user = getExistUserOrCreateUser(carModel);
		copyUserToModel(user, model);// 将用户的信息拷贝到前端用于前端显示
		String licensePlate = carModel.getLicensePlate();
		Car car = CarUtils.getCarByLicensePlate(licensePlate);
		copyCarToModel(car, model);
		Driveway inDriveway = DrivewayUtils.getDrivewayById(model
				.getDrivewayPK());
		Bayonet bayonet = inDriveway.getBayonet();// 获取入场卡口的对象
		model.setDrivewayName(inDriveway.getName());
		model.setBayonetEntrancePK(bayonet.getId());
		model.setBayonetEntranceName(bayonet.getName());
		model.getLedMsg().add(licensePlate);// 添加Led显示信息1：车牌号
	}

	/**
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	private void enterTemporaryUser(EnterModel model) throws ServiceException {
		model.setType(Constant.UserGroupType.TEMPORARYUSER.getValue());
		StringBuffer msg = new StringBuffer();
		if (model.isBlacklistFlag()) {
			msg.append("黑名单用户车辆 ");
		}
		msg.append("临时用户车辆 " + model.getCarModel().getLicensePlate());
		sendLedMsg(model.getDrivewayPK(), msg.toString());
	}

	/**
	 * @param model
	 */
	private void enterLongTermUser(EnterModel model) {
		StringBuffer ledMsg = new StringBuffer();
		if (model.isBlacklistFlag())
			ledMsg.append("黑名单用户 长期会员车辆 "
					+ model.getCarModel().getLicensePlate());

		model.setType(Constant.UserGroupType.LONGTREM.getValue());
		logger.info("长期用户进场流程处理");
		Account account = UserUtils.findAccountByUserId(model.getUserPK());
		ParkingEnvir parkingEnvir = Global.getConfig();// 得到停车场运行参数的对象
		// 验证长期会员是否快要到期
		if (DateUtils.remainDays(account.getExpirationTime()) <= parkingEnvir
				.getUExpirReminder()) {
			// TODO 用短信和语音播报提醒快要到期
			ledMsg.append(" 会员于" + account.getExpirationTime() + "到期,及时充值");
		}
		// TODO 用户其他车辆已经进过停车场，则作为特殊的临时车辆进场，再停车记录中标记了
		if (parkingEnvir.isPSpLockFlag()) {
			logger.info("停车场启用车位锁，正在准备验证用户是否拥有固定车位");
			PSp psp = openPspLock(model);
			if (psp == null) {
				logger.info("用户无固定车位开启或者是固定车位已被占用");
			}
		}
		if (parkingEnvir.isIsCountSp())
			System.out.println();
		// TODO 车道对应的停车场的空余车位-1
		sendLedMsg(model.getDrivewayPK(), ledMsg.toString());
	}

	/**
	 * @param model
	 */
	private void enterCommonUser(EnterModel model) {
		StringBuffer ledMsg = new StringBuffer();
		if (model.isBlacklistFlag())
			ledMsg.append("黑名单用户 储蓄会员车辆 "
					+ model.getCarModel().getLicensePlate());
		model.setType(Constant.UserGroupType.MEMBER_COMMON.getValue());
		Account account = UserUtils.findAccountByUserId(model.getUserPK());
		ParkingEnvir parkingEnvir = Global.getConfig();// 得到停车场运行参数的对象
		// 验证账户余额是否小于规定的最小余额
		if (account.getBalance() <= parkingEnvir.getMinBalance()) {
			ledMsg.append(" 账户余额不足" + parkingEnvir.getMinBalance() + " 充值续费");
		}
		sendLedMsg(model.getDrivewayPK(), ledMsg.toString());
		if (parkingEnvir.isPSpLockFlag())
			openPspLock(model);
	}

	/**
	 * 若是拥有固定车位的会员入场则开启固定车位上的车位锁，并把要停的固定车位的主键和编号放入到enterModel中。
	 * 
	 * @param model
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private PSp openPspLock(EnterModel model) {
		List<UserPSp> userpsps = UserPSpUtils
				.findPspByUserId(model.getUserPK());// 得到该客户所拥有的固定车位
		PSp psp = null;
		if (ListUtils.notNullAndEmpty(userpsps)) {// 若有固定车位，则开启其中一个固定车位的车锁
			psp = userpsps.get(0).getPSp();
			try {
				if (!LockUtils.openLockById(psp.getId())) {
					logger.error("打开" + psp.getPspId() + "的车位锁失败");
					model.getErrorMsg().add("打开" + psp.getPspId() + "的车位锁失败");
				}
			} catch (IOException e) {
				logger.error("打开" + psp.getPspId() + "的车位锁失败");
				model.getErrorMsg().add("打开" + psp.getPspId() + "的车位锁失败");
			}
		}
		return psp;
	}

	/**
	 * 获得已经存在的用户或者创建一个用户
	 * 
	 * @param carModel
	 * @return
	 * @throws ServiceException
	 */
	private User getExistUserOrCreateUser(CarModel carModel)
			throws ServiceException {
		Car car = CarUtils.getCarByLicensePlate(carModel.getLicensePlate());
		if (car == null) {
			User user = null;/*UserUtils.addTemporaryUser()*/;// 新增临时客户
			if (user == null) {
				logger.error("添加临时用户失败");
				throw new ServiceException("添加临时用户失败");
			}
			car = new Car(user, carModel.getLicensePlate(), carModel.getType(),
					carModel.getCarColor(), carModel.getCarModel(),
					carModel.getCarType());
			car = CarUtils.saveCar(car);
			if (car == null) {
				logger.error("添加临时车辆失败");
				throw new ServiceException("添加临时车辆失败");
			}
		}
		// 若车辆存在但是不对应用户，则创建一个临时用户与他对应
		User user = car.getUser();
		if (user == null) {
			//user = UserUtils.addTemporaryUser();// 新增临时客户
			car.setUser(user);
			CarUtils.saveCar(car);
		}
		return user;
	}

	/**
	 * 拷贝车辆属性到model中
	 * 
	 * @param car
	 * @param model
	 * @return
	 */
	private boolean copyCarToModel(Car car, EnterModel model) {
		CarModel carModel = new CarModel();
		try {
			BeanUtils.copyProperties(car, carModel);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("入场模块-后台验证-黑名单实体类赋值给model类失败");
			return false;
		}
		model.setCarModel(carModel);
		return true;
	}

	/**
	 * 将账户信息赋值给enterModel
	 * 
	 * @param account
	 * @param enterModel
	 * @return EnterModel
	 */
	private boolean copyAccountToModel(Account account, EnterModel enterModel) {
		try {
			BeanUtils.copyProperties(account, enterModel);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("入场模块-会员余额和有效时间验证-账户实体类赋值给model类失败");
			return false;
		}
		return true;
	}

	/**
	 * 将黑名单信息赋值给enterModel
	 * 
	 * @param blacklist
	 * @return enterModel
	 * @throws ServiceException
	 */
	private void copyBlacklistToModel(Blacklist blacklist, EnterModel model)
			throws ServiceException {
		BlacklistModel blacklistModel = new BlacklistModel();
		try {
			BeanUtils.copyProperties(blacklist, blacklistModel);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			throw new ServiceException("入场模块-后台验证-黑名单实体类赋值给model类失败");
		}
		model.setBlacklistModel(blacklistModel);
	}

	/**
	 * 将用户信息(账户、黑名单信息)复制给enterModel
	 * 
	 * @param user
	 * @param model
	 * @return enterModel
	 */
	private boolean copyUserToModel(User user, EnterModel model) {
		try {
			BeanUtils.copyProperties(user, model);
			model.setUserPK(user.getId());
			model.setUname(user.getName());
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("入场模块-后台验证-用户实体类赋值给model类失败");
			return false;
		}
		Account account = user.getAccount();
		if (account == null)
			return false;
		if (!copyAccountToModel(account, model))
			return false;
		return true;
	}

	/**
	 * @param model
	 * @return boolean
	 */
	private boolean addAdmissionRecAndCopyPropertyByModel(EnterModel model) {
		Driveway inDriveway = DrivewayUtils.getDrivewayById(model
				.getDrivewayPK());
		Car car = CarUtils.getCarByLicensePlate(model.getCarModel()
				.getLicensePlate());// 获取入场车辆的对象
		Date now = new Date();
		AdminssionRec rec = new AdminssionRec(inDriveway, car, now,
				model.getInRecoWay(), model.getInPicUrl());

		Bayonet bayonet = inDriveway.getBayonet();// 获取入场卡口的对象
		model.setBayonetEntranceName(bayonet.getName());
		model.setBayonetEntrancePK(bayonet.getId());
		model.setInTime(now);
		// Device terminal = inDriveway.getTerminal();
		// TODO 获得正在值班的操作员
		rec.setInOper(OperaterUtils.getOperater());

		rec = AdmissionRecUtils.save(rec);
		if (rec == null) {
			logger.error("添加入场记录失败");
			return false;
		}
		return true;
	}
}
