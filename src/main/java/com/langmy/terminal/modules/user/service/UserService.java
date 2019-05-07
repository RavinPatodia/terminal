package com.langmy.terminal.modules.user.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.config.Constant.RechargeType;
import com.langmy.terminal.common.config.Constant.SmsType;
import com.langmy.terminal.common.config.Constant.UserGroupType;
import com.langmy.terminal.common.entity.Account;
import com.langmy.terminal.common.entity.AnchorCharge;
import com.langmy.terminal.common.entity.AnchorRent;
import com.langmy.terminal.common.entity.Blacklist;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.ContactAddr;
import com.langmy.terminal.common.entity.ContactWay;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.entity.RechargeRec;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.entity.UserPSp;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.persistence.DetailRule;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.DataTable;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.modules.caradmission.utils.CarUtils;
import com.langmy.terminal.modules.charge.utils.AnchorRentUtils;
import com.langmy.terminal.modules.charge.utils.DetailRuleUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.log.utils.LogUtil.Option;
import com.langmy.terminal.modules.report.utils.RechargeRecUtils;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;
import com.langmy.terminal.modules.user.dao.AccountDao;
import com.langmy.terminal.modules.user.dao.BlacklistDao;
import com.langmy.terminal.modules.user.dao.ContactAddrDao;
import com.langmy.terminal.modules.user.dao.ContactWayDao;
import com.langmy.terminal.modules.user.dao.UGroupDao;
import com.langmy.terminal.modules.user.dao.UserDao;
import com.langmy.terminal.modules.user.daoImpl.UserDaoImpl;
import com.langmy.terminal.modules.user.model.AnchorRentModel;
import com.langmy.terminal.modules.user.model.BlacklistModel;
import com.langmy.terminal.modules.user.model.CarModel;
import com.langmy.terminal.modules.user.model.UserModel;
import com.langmy.terminal.modules.user.service.extend.UserExtend;
import com.langmy.terminal.modules.user.utils.UGChargeRuleUtils;
import com.langmy.terminal.modules.user.utils.UserPSpUtils;

/**
 * 客户业务层
 * 
 * @author ZZD
 *
 */
@Service
public class UserService extends BaseService<User> {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private ContactAddrDao contactAddrDao;
	@Autowired
	private ContactWayDao contactWayDao;
	@Autowired
	private UGroupDao ugroupDao;
	@Autowired
	private BlacklistDao blacklistDao;
	@Autowired
	private BlacklistService blacklistService;

	public UserService() {
		super.baseDao = SpringContextHolder.getBean(UserDao.class);
	}

	/**
	 * 获得客户列表
	 * 
	 * @param model
	 * @param dataTableParam
	 * @return
	 */
	public DataTable<BaseModel> list(UserModel model,
			DataTableParameter dataTableParam) {
		Map<String, Object> result = userDaoImpl.getNotBlackUsers(model,
				dataTableParam.getiDisplayStart(),
				dataTableParam.getiDisplayLength());
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) result.get("list");
		int wholeCount = (int) result.get("wholeCount");
		List<BaseModel> models = getModelsByBeans(users);
		DataTable<BaseModel> dt = new DataTable<BaseModel>();
		dt.setAaData(models);
		dt.setiTotalDisplayRecords(wholeCount);
		dt.setiTotalRecords((int) baseDao.count());
		dt.setsEcho(dataTableParam.getsEcho() + 1);
		return dt;
	}

	/**
	 * 新增客户
	 * 
	 * @param model
	 * @param carModel
	 * @return
	 * @throws ServiceException
	 */
	public boolean add(UserModel model, List<CarModel> carModels) {
		User user = addByModel(model);
		try {
			if (user == null)
				throw new ServiceException("客户管理模块-添加客户-保存客户失败");
			user = addRecharge(model, user);
			if (user == null)
				throw new ServiceException("客户管理模块-添加客户-充值失败");
			boolean flag = addContactWay(model, user.getId());
			if (!flag)
				throw new ServiceException("客户管理模块-添加客户-新增联系方式失败");
			flag = addContactAddr(model, user.getId());
			if (!flag)
				throw new ServiceException("客户管理模块-添加客户-新增联系地址失败");
			if (!addCars(user, carModels)) {
				throw new ServiceException("客户管理模块-添加客户-绑定客户车辆失败");
			}

		} catch (ServiceException e) {
			LogUtil.save("客户管理", LogUtil.Option.ADD, "添加客户：" + user.toString()
					+ "失败");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		LogUtil.save("客户管理", LogUtil.Option.ADD, "添加客户：" + user.toString()
				+ "成功");
		return true;
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean edit(UserModel model, List<CarModel> carModels)
			throws ServiceException {
		User user = editByModel(model);
		if (user == null)
			throw new ServiceException("客户管理模块-修改客户-保存客户失败");
		boolean flag = editContactWay(model, user);
		if (!flag)
			throw new ServiceException("客户管理模块-修改客户-修改联系地址失败");
		flag = editContactAddr(model, user);
		if (!flag)
			throw new ServiceException("客户管理模块-修改客户-修改联系方式失败");
		// 断开数据库所有该客户与车辆的关系
		List<Car> cars = CarUtils.findByUserId(user.getId());
		for (Car car : cars) {
			car.setUser(null);
			if (CarUtils.saveCar(car) == null) {
				throw new ServiceException("客户管理模块-修改客户-解除客户车辆失败");
			}
		}
		if (!addCars(user, carModels)) {
			throw new ServiceException("客户管理模块-修改客户-绑定客户车辆失败");
		}
		LogUtil.save("客户管理", LogUtil.Option.EDIT, "修改客户：" + user.toString());
		return true;
	}

	/**
	 * 保存客户拥有的车辆
	 * 
	 * @param userId
	 * @param carModel
	 * @return
	 */
	private boolean addCars(User user, List<CarModel> carModels) {
		for (CarModel c : carModels) {
			Car car = CarUtils.getCarByLicensePlate(c.getLicensePlate());
			if (car == null) {
				Car car2 = new Car();
				car2.setUser(user);
				car2.setLicensePlate(c.getLicensePlate());
				car2.setType(c.getLicensePlateType());
				car2.setCarColor(c.getCarColor());
				car2.setCarModel(c.getCarModel());
				car2.setCarType(c.getCarType());
				car2 = CarUtils.saveCar(car2);
				if (car2 == null) {
					return false;
				}
			} else {
				Account userAccount = user.getAccount();
				User carUser = car.getUser();
				if (carUser != null) {
					// 已存在车辆，且该车辆对应临时账户不为空 则将临时账户信息合并到当前账户 并删除临时账户
					Account carAccount = carUser.getAccount();
					// 如果绑定车辆临时账户是自己 则不用修改账户信息
					if (carAccount.getId() != userAccount.getId()) {
						if (carAccount.getCreateTime().before(
								userAccount.getCreateTime())) {
							userAccount.setCreateTime(carAccount
									.getCreateTime());
							userAccount.setProcTime(carAccount.getProcTime());
							accountDao.save(userAccount);
						}
						// 若临时用户被拉黑 则该会员账户也被拉黑
						for (Blacklist black : carUser.getBlacklists()) {
							black.setUser(user);
							blacklistDao.save(black);
						}
						//
						for (UserPSp userpsp : carUser.getUserPSps()) {
							userpsp.setUser(user);
							UserPSpUtils.save(userpsp);
						}
						accountDao.delete(carAccount);
						userDao.delete(carUser);
					}
				}
				// 临时账户为空 或者 临时账户已删除 则绑定该临时该车辆到当前账户
				car.setUser(user);
				car = CarUtils.saveCar(car);
				if (car == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @return
	 */
	private User addByModel(UserModel model) {
		User user = new User();
		try {
			BeanUtils.copyProperties(model, user);
			if ("on".equals(model.getGenderStr())) {
				user.setGender(true);
			} else {
				user.setGender(false);
			}
			user.setState(0);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("客户管理模块-添加客户-根据Model得到相应的类-Bean之间相互赋值异常");
			return null;
		}
		user = userDao.save(user);
		return user;
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public User editByModel(UserModel model) throws ServiceException {
		User user = userDao.findOne(model.getId());
		try {
			BeanUtils.copyProperties(model, user);
			if ("on".equals(model.getGenderStr())) {
				user.setGender(true);
			} else {
				user.setGender(false);
			}
			UGroup ugroup = ugroupDao.findOne(model.getUgroupPK());
			if (ugroup != null) {
				user.setUGroup(ugroup);
			}
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("客户管理模块-修改客户-根据Model得到相应的类-Bean之间相互赋值异常");
			return null;
		}
		user = userDao.save(user);
		return user;
	}

	/**
	 * 加入黑名单
	 * 
	 * @param userModel
	 * @return
	 */
	public boolean addBlickList(UserModel userModel) {
		BlacklistModel blacklistModel = new BlacklistModel(userModel.getId(),
				userModel.getListReason(), userModel.getBlackListEndTime(),
				userModel.getNeedFee(), userModel.isInFlag());
		if (blacklistService.addByModel(blacklistModel) == null) {
			return false;
		}
		LogUtil.save("客户管理", LogUtil.Option.EDIT, "加入黑名单：" + userModel.getId());
		return true;
	}

	/**
	 * 添加联系地址
	 * 
	 * @param model
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	private boolean addContactAddr(UserModel model, int userId)
			throws ServiceException {
		ContactAddr contactAddr = new ContactAddr();
		contactAddr = UserExtend.convertContactAddr(contactAddr, model, userId);
		contactAddr = contactAddrDao.save(contactAddr);
		if (contactAddr == null)
			throw new ServiceException("客户管理模块-添加客户-添加联系地址-新增联系地址失败");
		return true;
	}

	/**
	 * 添加联系方式
	 * 
	 * @param model
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	private boolean addContactWay(UserModel model, int userId)
			throws ServiceException {
		ContactWay contactWay = new ContactWay();
		contactWay = UserExtend.convertToContactWay(contactWay, model, userId);
		contactWay = contactWayDao.save(contactWay);
		if (contactWay == null)
			throw new ServiceException("客户管理模块-添加客户-添加联系地址-新增联系方式失败");
		return true;
	}

	/**
	 * 修改联系地址
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	private boolean editContactAddr(UserModel model, User user)
			throws ServiceException {
		int userId = user.getId();
		ContactAddr addr = contactAddrDao.findByContactId(userId);
		if (addr == null) {
			return addContactAddr(model, userId);
		}
		addr = UserExtend.convertContactAddr(addr, model, userId);
		addr = contactAddrDao.save(addr);
		if (addr == null) {
			throw new ServiceException("客户管理模块-修改客户-修改联系地址-修改联系地址失败");
		}
		return true;
	}

	/**
	 * 修改联系方式
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	private boolean editContactWay(UserModel model, User user)
			throws ServiceException {
		int userId = user.getId();
		ContactWay conWay = contactWayDao.findByContactId(userId);
		if (conWay == null) {
			return addContactWay(model, userId);
		}
		conWay = UserExtend.convertToContactWay(conWay, model, userId);
		conWay = contactWayDao.save(conWay);
		if (conWay == null) {
			throw new ServiceException("客户管理模块-添加客户-添加联系方式-添加联系方式失败");
		}
		return true;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean del(String ids) {
		List<Integer> idList = getIdList(ids);
		for (int id : idList) {
			if (userDao.del(idList) > 0) {
				LogUtil.save("客户管理", LogUtil.Option.DEL, "删除客户：" + id);
				List<Car> cars = baseDao.findOne(id).getCarList();
				for (Car car : cars) {
					CarUtils.delete(car.getId());
				}
				return true;
			}

		}
		return false;
	}

	/**
	 * 返回长期客户组收费规则给前台
	 * 
	 * @param ugroupId
	 * @return
	 */
	public UserModel getAnchorRentsByUGId(Integer ugroupId) {
		UserModel model = new UserModel();
		ChargeRule chargeRule = UGChargeRuleUtils
				.getLongtermCargeRuleByUGId(ugroupId);
		if (chargeRule == null) {
			logger.error("取到的收费规则为空");
			return null;
		}
		DetailRule detailRule = DetailRuleUtils
				.findDetailRuleByIdAndRentType(chargeRule);
		if (detailRule == null) {
			logger.error("取到的规则为空");
			return null;
		}
		AnchorCharge anchorGroup = (AnchorCharge) detailRule;
		// List<AnchorRent> anchorRents = anchorGroup.getAnchorRents();
		List<AnchorRent> anchorRents = null;
		List<AnchorRentModel> anchorRentModels = Lists.newArrayList();
		try {
			anchorRentModels = BeanUtils.copyListProperties(anchorRents,
					AnchorRentModel.class);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("客户-长期客户组收费规则-实体类转Model类失败");
			return null;
		}
		model.setAnchorRentModels(anchorRentModels);
		return model;
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 * @throws IOException
	 */
	public UserModel view(int id) {
		User user = userDao.findOne(id);
		return UserExtend.covertToModel(user);
	}

	/**
	 * 会员办理 充值
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	private User addRecharge(UserModel model, User user)
			throws ServiceException {
		Operater operater = OperaterUtils.getOperater();
		Date now = new Date();
		Integer rechargeWay = model.getRechargeWay();
		double money = model.getRechargeFee();
		Account account = user.getAccount();
		if (account == null) {
			account = new Account(user, now, now, now, 0);
		}
		Integer ugroupType = model.getUgroupType();
		Integer ugroupPk = null;
		// 普通会员
		if (ugroupType == UserGroupType.MEMBER_COMMON.getValue()) {
			ugroupPk = model.getMemberUgroupPK();
			double balance = account.getBalance() + model.getRechargeFee();
			account.setBalance(balance);
			account.setUser(user);
			account = accountDao.save(account);
			if (account == null) {
				throw new ServiceException("客户管理模块-客户充值-保存客户账户失败");
			}
			// 充值记录
			RechargeRec rec = new RechargeRec(operater, user,
					RechargeType.BALANCE.getValue(), rechargeWay, money, now);
			rec = RechargeRecUtils.save(rec);
			if (rec == null) {
				throw new ServiceException("客户管理模块-客户充值-保存充值记录失败");
			}
		} else if (ugroupType == UserGroupType.LONGTREM.getValue()) {
			ugroupPk = model.getLongUgroupPK();
			ChargeRule chargeRule = UGChargeRuleUtils
					.getLongtermCargeRuleByUGId(ugroupPk);
			Date expTime = model.getExpTime();
			account.setExpirationTime(expTime);
			account.setBeginTime(now);
			account.setUser(user);
			account = accountDao.save(account);
			if (account == null) {
				throw new ServiceException("客户管理模块-客户充值-保存客户账户失败");
			}
			// 加入充值记录
			RechargeRec rec = new RechargeRec(chargeRule, operater, user,
					RechargeType.LONGTERM.getValue(), rechargeWay, now,
					expTime, money, now);
			rec = RechargeRecUtils.save(rec);
			if (rec == null) {
				throw new ServiceException("客户管理模块-客户充值-保存充值记录失败");
			}
		}
		UGroup ugroup = ugroupDao.findOne(ugroupPk);
		if (ugroup == null) {
			logger.error("客户管理模块-添加客户-无法根据主键找到相应的客户组");
			return null;
		}
		user.setUGroup(ugroup);
		userDao.save(user);
		return user;
	}

	/**
	 * 充值
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean recharge(UserModel model) {
		Operater operater = OperaterUtils.getOperater();
		Integer rechargeWay = model.getRechargeWay();
		double money = model.getRechargeFee();
		Date expTime = model.getExpTime();
		User user = userDao.findOne(model.getId());
		Date now = new Date();
		Account account = user.getAccount();
		try {
			if (account == null) {
				account = new Account(user, now, now, now, 0);
			}
			Integer rechargeType = model.getRechargeType();
			if (rechargeType == RechargeType.BALANCE.getValue()) {// 余额充值
				double balance = account.getBalance() + model.getRechargeFee();
				account.setBalance(balance);
				account = accountDao.save(account);
				if (account == null) {
					throw new ServiceException("客户管理模块-客户充值-保存客户账户失败");
				}
				// 充值记录
				RechargeRec rec = new RechargeRec(operater, user, rechargeType,
						rechargeWay, money, now);
				rec = RechargeRecUtils.save(rec);
				if (rec == null) {
					throw new ServiceException("客户管理模块-客户充值-保存充值记录失败");
				}
				// 会员充值余额 相应 客户 类型为余额不足 的短信发送记录置为无效
//				SmsSendRecUtils.disableByUserId(user.getId(),
//						SmsType.BALANCE.getValue());
			} else if (rechargeType == RechargeType.LONGTERM.getValue()) {
				Integer groupType = user.getUGroup().getType();
				UGroup longtermUgroup = ugroupDao.findOne(model.getUgroupPK());
				ChargeRule chargeRule = UGChargeRuleUtils
						.getLongtermCargeRuleByUGId(model.getUgroupPK());
				AnchorRent anchorRent = AnchorRentUtils.findById(model
						.getAnchorRentPK());
				if (groupType == UserGroupType.MEMBER_COMMON.getValue()) {// 普通会员升级为长期会员
					// 更新账户信息
					account.setExpirationTime(expTime);
					account.setBeginTime(now);
					account = accountDao.save(account);
					if (account == null) {
						throw new ServiceException("客户管理模块-客户充值-保存客户账户失败");
					}
					// 移动到长期客户组
					user.setUGroup(longtermUgroup);
					user = userDao.save(user);
					if (user == null) {
						throw new ServiceException("客户管理模块-客户充值-移动到长期客户组失败");
					}
					// 加入充值记录
					RechargeRec rec = new RechargeRec(chargeRule, operater,
							user, rechargeType, rechargeWay, now, expTime,
							money, now);
					rec = RechargeRecUtils.save(rec);
					if (rec == null) {
						throw new ServiceException("客户管理模块-客户充值-保存充值记录失败");
					}
				} else if (groupType == UserGroupType.LONGTREM.getValue()) {// 长期会员续期
					// 这次充值生效时间是上次充值的截止时间
					Date beginTime = account.getExpirationTime();
					RechargeRec rec = new RechargeRec(chargeRule, operater,
							user, rechargeType, rechargeWay, beginTime,
							expTime, model.getRechargeFee(), now);
					rec = RechargeRecUtils.save(rec);
					if (rec == null) {
						throw new ServiceException("客户管理模块-客户充值-保存充值记录失败");
					}
					account.setBeginTime(beginTime);
					account.setExpirationTime(DateUtils.dateAddMonths(
							account.getExpirationTime(), anchorRent.getMonth()));
					account = accountDao.save(account);
					if (account == null) {
						throw new ServiceException("客户管理模块-客户充值-保存客户账户失败");
					}
					// 长期会员续期 相应 客户 类型为长期会员到期 的短信发送记录置为无效
//					SmsSendRecUtils.disableByUserId(user.getId(),
//							SmsType.LONGTERM.getValue());
				}

			}
		} catch (ServiceException e) {
			LogUtil.save("客户管理", LogUtil.Option.RECHARGE, user.toString()
					+ "业务办理失败");
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
		LogUtil.save("客户管理", LogUtil.Option.RECHARGE, user.toString()
				+ "业务办理成功");
		return true;
	}

	/**
	 * 根据name 查询未拉黑客户
	 * 
	 * @param uacc
	 *            客户名
	 * @return
	 */
	public List<BaseModel> getUserNotBlack(String name) {
		List<User> users = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(name)) {
			users = userDao.getUserNotBlack("%" + name + "%");
		} else {
			users = userDao.getUserNotBlack();
		}
		return UserExtend.convertToSimpleModels(users);

	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<User> users) {
		if (logger.isDebugEnabled())
			logger.debug(users.toString());// 实体类列表用toString，如果用JsonString会导致大量查询数据库
		List<UserModel> userModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(users)) {
			return Lists.newArrayList(userModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "UGroup.ugroupId");
		map.put("targetPro1", "ugroupId");
		map.put("sourcePro2", "UGroup.name");
		map.put("targetPro2", "ugroupName");
		map.put("sourcePro3", "UGroup.describion");
		map.put("targetPro3", "ugroupDescribion");
		try {
			userModels = BeanUtils.copyListProperties(users, UserModel.class,
					map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("客户管理模块-实体类与Model类数组转化失败", e);
			return null;
		}
		userModels = UserExtend.isBlackList(userModels);
		return Lists.newArrayList(userModels);
	}

	@Override
	protected Specification<User> buildSpecification(BaseModel baseModel) {
		return null;

	}

	/**
	 * 长期会员到期 变更到变更客户组
	 */
	// @Scheduled(cron = "0 0 2 * * *")
	public void changUroup() {
		List<User> users = userDao.findByUgroupType(UserGroupType.LONGTREM
				.getValue());
		Date now = new Date();
		// 默认临时客户组
		UGroup changeUGroup = new UGroup();
		List<UGroup> ugs = ugroupDao
				.findByTypeAndDelFlagFalseAndEnableFlagTrue(UserGroupType.DEFAULT_TEMPORARYUSER
						.getValue());
		if (!ListUtils.isNullOrEmpty(ugs)) {
			changeUGroup = ugs.get(0);
		}
		for (User user : users) {
			Account account = user.getAccount();
			if (account == null) {
				continue;
			}
			if (now.after(account.getExpirationTime())) {
				Integer ugroupPk = user.getUGroup().getId();
				UGroup ugroup = ugroupDao.findOne(ugroupPk);
				Integer changeUgPK = ugroup.getChangeGroup();
				if (changeUgPK == null || changeUgPK == 0) {
					user.setUGroup(changeUGroup);
				} else {
					changeUGroup = ugroupDao.findOne(changeUgPK);
					user.setUGroup(changeUGroup);
				}
				if (userDao.save(user) != null) {
					LogUtil.saveAutoOper(
							"客户管理",
							Option.CHANGEUG,
							user.getIdCard() + "长期会员到期,客户组由"
									+ ugroup.getUgroupId() + "变更到"
									+ changeUGroup.getUgroupId());
				}
			}

		}
	}
}
