package com.langmy.terminal.modules.monitor.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.langmy.terminal.common.config.Constant;
import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.entity.AdminssionRec;
import com.langmy.terminal.common.entity.Bayonet;
import com.langmy.terminal.common.entity.Blacklist;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.ChargeRec;
import com.langmy.terminal.common.entity.Driveway;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.entity.ParkingEnvir;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.persistence.IdEntity;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.PropertiesLoader;
import com.langmy.terminal.common.web.DataTable;
import com.langmy.terminal.modules.caradmission.utils.AdmissionRecUtils;
import com.langmy.terminal.modules.caradmission.utils.CarUtils;
import com.langmy.terminal.modules.charge.utils.ChargeRecUtils;
import com.langmy.terminal.modules.device.utils.DeviceUtils;
import com.langmy.terminal.modules.device.utils.DrivewayUtils;
import com.langmy.terminal.modules.monitor.model.CarModel;
import com.langmy.terminal.modules.monitor.model.ExitModel;
import com.langmy.terminal.modules.ruleEngine.fact.AdmissionRecModel;
import com.langmy.terminal.modules.ruleEngine.fact.ChargeResult;
import com.langmy.terminal.modules.ruleEngine.fact.UserModel;
import com.langmy.terminal.modules.ruleEngine.utils.RuleUtils;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;
import com.langmy.terminal.modules.user.utils.BlacklistUtils;
import com.langmy.terminal.modules.user.utils.UserUtils;

/**
 * 出场缴费业务层
 * 
 * @author lxj
 *
 */
@Service
public class ExitService extends BaseService<IdEntity> {

	private static Logger logger = LoggerFactory.getLogger(ExitService.class);

	private final static Integer TYPE_BLACKLISTUSER = 3;// 黑名单客户

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader(
			"park.properties");
	private static int plateFirstWeight = propertiesLoader
			.getInteger("plateFirst");
	private static int plateSecondWeight = propertiesLoader
			.getInteger("plateSecond");
	private static int plateThirdWeight = propertiesLoader
			.getInteger("plateThird");
	private static int plateForthWeight = propertiesLoader
			.getInteger("plateForth");
	private static int plateFifthWeight = propertiesLoader
			.getInteger("plateFifth");
	private static int plateSixthWeight = propertiesLoader
			.getInteger("plateSixth");
	private static int plateSeventh = propertiesLoader
			.getInteger("plateSeventh");
	private static int carModelWeight = propertiesLoader.getInteger("carModel");
	private static int carColorWeight = propertiesLoader.getInteger("carColor");
	private static int licensePlateTypeWeight = propertiesLoader
			.getInteger("licensePlateType");
	private static int threshold = propertiesLoader.getInteger("threshold");

	/**
	 * 准许车辆出场
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public boolean exit(ExitModel model) throws ServiceException,
			UnknownHostException, IOException {
		// 若客户是临时客户或者是普通会员
		CarModel carModel = model.getCarModel();
		if (model.getUserType() == Constant.UserGroupType.TEMPORARYUSER
				.getValue()
				|| model.getUserType() == Constant.UserGroupType.MEMBER_COMMON
						.getValue()) {
			if (!addAdminssionRec(model))// 保存出场信息
				throw new ServiceException("出场-保存出场记录失败");
			if (!addChargeRec(model))// 保存收费记录
				throw new ServiceException("出场-保存收费记录失败");
			// 向LED显示屏中显示相应的收费信息
			String[] msg = { carModel.getLicensePlate(),
					"优惠" + model.getDctPay() + "元",
					"实付" + model.getActualPay() + "元" };
			DeviceUtils.setMutilineMessage(model.getBayonetExitPK(), msg);
			// try {
			// boolean flag =
			// BrakeMachineUtils.openByDrivewayId(model.getBayonetExitPK());//
			// 开启闸机
			// if (!flag)
			// throw new ServiceException("出场开闸失败");
			// } catch (AWTException | IOException e) {
			// throw new ServiceException("出场开闸失败");
			// }
			return true;
		}
		// 若客户是长期会员
		else if (model.getUserType() == Constant.UserGroupType.LONGTREM
				.getValue()) {
			if (!addAdminssionRec(model))// 保存出场信息
				throw new ServiceException("长期会员出场-保存出场记录失败");
			// 向LED显示屏中显示相应的信息
			String[] msg = { carModel.getLicensePlate(), "长期会员不收费" };
			DeviceUtils.setMutilineMessage(model.getBayonetExitPK(), msg);
			// try {
			// boolean flag =
			// BrakeMachineUtils.openByDrivewayId(model.getBayonetExitPK());//
			// 开启闸机
			// if (!flag)
			// throw new ServiceException("出场开闸失败");
			// } catch (AWTException | IOException e) {
			// throw new ServiceException("出场开闸失败");
			// }
			return true;
		}
		/*
		 * else if(model.getUsertype() == TYPE_BLACKLISTUSER){ solveBlacklist();
		 * return false; }
		 */
		return false;
	}

	/**
	 * 根据出场车辆找到该车入场的记录
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public ExitModel matchingAdmission(ExitModel model) throws ServiceException {
		// 根据车牌号查找对应入场记录
		List<AdminssionRec> admissionRecs = AdmissionRecUtils
				.findEnterAdmissionRec(model.getCarModel().getLicensePlate());
		// 若是没有对应入场记录，将卡口和出场时间放入model后直接返回。
		if (ListUtils.isNullOrEmpty(admissionRecs)) {
			int drivewayId = model.getDrivewayPK();
			Driveway outDriveway = DrivewayUtils.getDrivewayById(drivewayId);
			model.setDrivewayName(outDriveway.getName());
			Bayonet bayonetExit = outDriveway.getBayonet();
			model.setBayonetExitName(bayonetExit.getName());
			model.setOutTime(new Date());
			return model;
		}
		// 返回第一个值，即最近一次的入场记录
		AdminssionRec admissionRec = admissionRecs.get(0);

		model = fillingExitModel(admissionRec, model);// 根据得到的入场记录将ExitModel填充相应的信息,出入信息，卡口信息，车辆主键，客户信息，黑名单信息
		if (model == null) {
			throw new ServiceException("出场模块-根据出场车辆找记录失败");
		}

		// 判断是否停车场是否会进行收费
		ParkingEnvir parkingEnvir = Global.getConfig();
		int userType = model.getUserType();
		if (parkingEnvir.isIsCharge()
				&& userType != Constant.UserGroupType.LONGTREM.getValue())
			model = calculateFee(model);// 计算费用
		model.setSuccessflag(true);// 代表已根据相应的入场信息完成所需信息的填写
		return model;
	}

	/**
	 * 根据得到的入场记录将ExitModel填充相应的信息,出入信息，卡口信息，车辆主键，客户信息，黑名单信息
	 * 
	 * @param admission
	 * @param model
	 * @return
	 */
	private ExitModel fillingExitModel(AdminssionRec admission, ExitModel model) {
		// 向model填入出入记录相关信息
		model.setInTime(admission.getInTime());
		model.setInPicUrl(admission.getInPicUrl());
		model.setInRecoWay(admission.getInRecoWay());
		model.setAmoutPay(admission.getAmoutPay());
		model.setPayFlag(admission.isIsPay());
		model.setAdmissionRecPK(admission.getId());

		Driveway outDriveway = DrivewayUtils.getDrivewayById(model
				.getDrivewayPK());
		model.setDrivewayPK(outDriveway.getId());
		model.setDrivewayName(outDriveway.getName());
		Driveway inDriveway = admission.getInDriveWay();
		model.setDrivewayPK(inDriveway.getId());
		model.setDrivewayName(inDriveway.getName());

		// 向model中填入卡口信息.
		Bayonet bayonetExit = outDriveway.getBayonet();
		Bayonet bayonetEntrance = inDriveway.getBayonet();
		model.setBayonetExitPK(bayonetExit.getId());
		model.setBayonetExitName(bayonetExit.getName());
		model.setBayonetEntrancePK(bayonetEntrance.getId());
		model.setBayonetEntranceName(bayonetEntrance.getName());

		// 向model中填入该车的主键
		CarModel carModel = new CarModel();
		Car car = admission.getCar();
		carModel.setId(car.getId());
		carModel.setLicensePlate(car.getLicensePlate());
		carModel.setType(car.getType());
		model.setCarModel(carModel);
		// 向model中填入客户的相关信息
		User user = car.getUser();
		model.setUserPK(user.getId());
		model.setUname(user.getName());
		model.setHasParkingSpaces(user.getHasParkingSpaces());
		model.setUserType(user.getUGroup().getType());

		model.setOutTime(new Date());
		// 向model中填入黑名单信息
		Blacklist blacklist = BlacklistUtils.getEffectBlacklist(user);
		if (blacklist != null) {
			model.setListReason(blacklist.getListReason());
			model.setListTime(blacklist.getListTime());
			model.setUserType(TYPE_BLACKLISTUSER);
		}
		return model;
	}

	/**
	 * 计算费用，向model中放入收费的费用信息，生效的优惠规则和收费规则，优惠规则组
	 * 
	 * @param model
	 * @return
	 */
	private ExitModel calculateFee(ExitModel model) {
		com.langmy.terminal.modules.ruleEngine.fact.ChargeResult chargeResult = getChargeResult(model);
		// 向model中放入收费信息
		model.setAmoutPay(chargeResult.getAmoutPay());
		model.setDctPay(chargeResult.getDctPay());
		model.setActualPay(chargeResult.getDctResult());
		model.setFee(model.getFee() + chargeResult.getAmoutPay());

		// TODO 将优惠情况保存进数据库

		return model;
	}

	/**
	 * 保存出场信息
	 * 
	 * @param model
	 * @return
	 */
	private boolean addAdminssionRec(ExitModel model) {
		AdminssionRec admissionRec = AdmissionRecUtils.findOne(model
				.getAdmissionRecPK());
		// TODO保存出场的车道信息
		// 得到车辆信息
		Car car = CarUtils.getCarByLicensePlate(model.getCarModel()
				.getLicensePlate());
		admissionRec.setCar(car);
		// 放入离场相关信息
		admissionRec.setOutRecoWay(model.getOutRecoWay());
		admissionRec.setOutTime(model.getOutTime());
		admissionRec.setOutPicUrl(model.getOutPicUrl());
		admissionRec.setAmoutPay(model.getAmoutPay());
		admissionRec.setIsPay(true);
		Driveway outDriveway = DrivewayUtils.getDrivewayById(model
				.getDrivewayPK());
		admissionRec.setOutDriveway(outDriveway);
		admissionRec = AdmissionRecUtils.save(admissionRec);
		if (admissionRec == null) {
			logger.error("保存出场记录失败");
			return false;
		}
		return true;
	}

	/**
	 * 保存收费记录
	 * 
	 * @param model
	 * @return
	 */
	private boolean addChargeRec(ExitModel model) {
		AdminssionRec adminssionRec = AdmissionRecUtils.findOne(model
				.getAdmissionRecPK());
		Operater operater = OperaterUtils.getOperater();
		// TODO 生效的优惠记录需要添加
		ChargeRec c = new ChargeRec(adminssionRec, operater,
				model.getPayType(), new Date(), model.getDctPay(),
				model.getAmoutPay(), model.getActualPay());
		c = ChargeRecUtils.saveChargeRec(c);
		if (c == null)
			return false;
		model.setPayFlag(true);
		return true;
	}

	/**
	 * 从规则引擎中获取收费信息
	 * 
	 * @param model
	 * @return
	 */
	private com.langmy.terminal.modules.ruleEngine.fact.ChargeResult getChargeResult(
			ExitModel model) {
		User u = UserUtils.findUserById(model.getUserPK());
		CarModel carModel = model.getCarModel();
		List<ChargeRec> chargeRecs = ChargeRecUtils.findByAdmissionRec(model
				.getAdmissionRecPK());
		if (ListUtils.notNullAndEmpty(chargeRecs)) {
			ChargeRec chargeRec = chargeRecs.get(0);// 拿到最近一次支付时间的收费记录
			AdmissionRecModel rec = new AdmissionRecModel(model.getInTime(),
					model.getOutTime(), carModel.getCarType(),
					chargeRec.getPayTime(), true);
			UserModel user = new UserModel(u.getBirthday(), u.getUGroup()
					.getId());
			ChargeResult chargeResult = RuleUtils.executeRules(rec, user);
			return chargeResult;
		}
		AdmissionRecModel rec = new AdmissionRecModel(model.getInTime(),
				model.getOutTime(), carModel.getCarType(), false);
		UserModel user = new UserModel(u.getBirthday(), u.getUGroup().getId());
		ChargeResult chargeResult = RuleUtils.executeRules(rec, user);
		return chargeResult;

	}

	/**
	 * 智能匹配
	 * 
	 * @param model
	 * @return
	 */
	public DataTable<ExitModel> matching(ExitModel model) {
		List<ExitModel> models = matchingAlgorithm(model);// 通过智能匹配的算法得到匹配的车辆信息以表格形式返给前台

		DataTable<ExitModel> dt = new DataTable<ExitModel>();
		dt.setAaData(models);
		dt.setiTotalDisplayRecords(models.size());
		dt.setiTotalRecords(models.size());

		return dt;
	}

	/**
	 * 智能匹配的算法
	 * 
	 * @param model
	 * @return
	 */
	private List<ExitModel> matchingAlgorithm(ExitModel model) {
		List<ExitModel> exitModels = Lists.newArrayList();
		CarModel car = model.getCarModel();

		String licensePlate = car.getLicensePlate();
		String licensePlateType = car.getType();
		String carColor = car.getCarColor();
		String carModel = car.getCarModel();

		// 把车牌号分割放入到list中
		List<String> splitLicensePlate = Splitter.fixedLength(1)
				.omitEmptyStrings().splitToList(licensePlate);
		// 智能匹配算法的权值和阈值需要权衡，得到最优权值
		List<Integer> weight = Lists.newArrayList(plateFirstWeight,
				plateSecondWeight, plateThirdWeight, plateForthWeight,
				plateFifthWeight, plateSixthWeight, plateSeventh,
				carModelWeight, carColorWeight, licensePlateTypeWeight);// 权值
		// 对所有在场的车辆进行匹配
		List<AdminssionRec> admissionRecs = AdmissionRecUtils.findParkingCar();
		for (AdminssionRec a : admissionRecs) {
			ExitModel emodel = new ExitModel();
			Integer priority = 0;
			List<String> splits = Splitter.fixedLength(1).omitEmptyStrings()
					.splitToList(a.getCar().getLicensePlate());
			for (int i = 0; i < Constant.LicensePlateReco.LICENSEPLATE_LENGTH
					.getValue(); i++) {
				if (splitLicensePlate.get(i).equals(splits.get(i))) {
					priority += weight.get(i);
				}
			}
			if (carModel.equals(a.getCar().getCarModel())) {
				priority += weight
						.get(Constant.LicensePlateReco.CAR_MODEL_INDEX
								.getValue());
			}
			if (carColor.equals(a.getCar().getCarColor())) {
				priority += weight
						.get(Constant.LicensePlateReco.CAR_COLOR_INDEX
								.getValue());
			}
			if (licensePlateType.equals(a.getCar().getType())) {
				priority += weight
						.get(Constant.LicensePlateReco.LICENSEPLATE_TYPE_INDEX
								.getValue());
			}
			if (priority >= threshold) {// 这里是阈值(识别率)，当超过阈值则代表符合匹配
				Car matchCar = a.getCar();
				CarModel c = new CarModel(matchCar.getId(),
						matchCar.getLicensePlate(), matchCar.getType(),
						matchCar.getCarColor(), matchCar.getCarModel(),
						matchCar.getCarType());
				emodel.setPriority(priority);
				emodel.setCarModel(c);
				exitModels.add(emodel);
			}
		}
		// 根据匹配度进行排序，匹配度越高越前面
		Ordering<ExitModel> byPriority = new Ordering<ExitModel>() {
			@Override
			public int compare(ExitModel left, ExitModel right) {
				return right.getPriority() - left.getPriority();
			}
		};
		exitModels = byPriority.immutableSortedCopy(exitModels);

		return exitModels;
	}

	/**
	 * 这里是用来把智能匹配中选中的车辆根据ID查信息
	 * 
	 * @param model
	 * @param carPK
	 * @return
	 */
	public ExitModel getCarByCarId(ExitModel model, Integer carPK) {
		Car car = CarUtils.getCarById(carPK);// 根据车辆的主键id找到车辆对象
		if (car == null) {
			return model;
		}
		// 把车辆信息放入到exitModel中
		CarModel carModel = new CarModel(car.getId(), car.getLicensePlate(),
				car.getType(), car.getCarColor(), car.getCarModel(),
				car.getCarType());
		model.setCarModel(carModel);
		// TODO 这里的离场卡口定死了，需要改,原因是在智能匹配提交只提交了车辆的id,而把卡口等其他信息漏掉了.
		// 个人认为解决方法是，把相关的其他信息以隐藏域形式放入到智能匹配页面中,然后直接把这些信息放入一个model类返回给控制器中的getdata方法。
		model.setBayonetExitPK(2);
		return model;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<IdEntity> entities) {
		return null;
	}

	@Override
	protected Specification<IdEntity> buildSpecification(BaseModel model) {
		return null;
	}

}
