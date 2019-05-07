package com.langmy.terminal.modules.psp.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.config.Constant.RechargeType;
import com.langmy.terminal.common.entity.AnchorRent;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.PSpLock;
import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.entity.RechargeRec;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.entity.UserPSp;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.charge.dao.ChargeRuleDao;
import com.langmy.terminal.modules.charge.utils.AnchorRentUtils;
import com.langmy.terminal.modules.device.dao.ParkDao;
import com.langmy.terminal.modules.device.utils.LockUtils;
import com.langmy.terminal.modules.device.utils.ParkUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.psp.dao.AreaDao;
import com.langmy.terminal.modules.psp.dao.DragDivDao;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.extend.PspChargeRuleExtend;
import com.langmy.terminal.modules.psp.model.PSpModel;
import com.langmy.terminal.modules.psp.model.RechargeRecModel;
import com.langmy.terminal.modules.psp.service.extend.PspExtend;
import com.langmy.terminal.modules.psp.utils.AreaUtils;
import com.langmy.terminal.modules.psp.utils.ArrangeUtils;
import com.langmy.terminal.modules.report.utils.RechargeRecUtils;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;
import com.langmy.terminal.modules.user.utils.UserPSpUtils;
import com.langmy.terminal.modules.user.utils.UserUtils;

/**
 * 车位的service
 * 
 * @author MC
 *
 */
@Service
public class PSpService extends BaseService<PSp> {

	@Autowired
	private PSpDao pspDao;
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private DragDivDao dragDivDao;
	@Autowired
	private ChargeRuleDao chargeRuleDao;

	public PSpService() {
		super.baseDao = SpringContextHolder.getBean("PSpDao");
	}

	/**
	 * 获取用户对象
	 * 
	 * @param uacc
	 * @return
	 */
	public List<BaseModel> getUser(String uacc) {
		return UserUtils.getUserNotBlackAndIsEnable(uacc);
	}

	/**
	 * 获取区域ID
	 * 
	 * @param areaId
	 * @return
	 */
	public List<BaseModel> getAreaId(String areaId) {
		return AreaUtils.getAreaIdByName(areaId);
	}

	/**
	 * 获取park
	 * 
	 * @param areaId
	 * @return
	 */
	public List<BaseModel> getParks(String parkName) {
		return ParkUtils.getParks(parkName);
	}

	/**
	 * 获取所有的车位
	 * 
	 * @return
	 */
	public List<BaseModel> getAllPsp() {
		List<PSp> psp = pspDao.findAll();
		return getModelsByBeans(psp);
	}

	/**
	 * 获取区域对象
	 * 
	 * @return
	 */
	public List<BaseModel> getAllPspByArea() {
		List<PSp> psp = pspDao.getPspWhereAreaEmpty();
		return getModelsByBeans(psp);
	}

	/**
	 * 获取所有没有摄像头的车位
	 * 
	 * @return
	 */
	public List<BaseModel> getAllPspWherePspWithOutCamera() {
		List<PSp> psp = pspDao.getAllPspWherePspWithOutCamera();
		return getModelsByBeans(psp);
	}

	/**
	 * 获取车位锁对象
	 * 
	 * @param pspId
	 * @return
	 */
	public List<BaseModel> getAllPspByPspLock(String pspId) {
		if (StringUtils.isNotNullAndEmpty(pspId)) {
			List<PSp> psp = pspDao.getPspByLock("%" + pspId + "%");
			return getModelsByBeans(psp);
		} else {
			List<PSp> psp = pspDao.getPspByLock();
			return getModelsByBeans(psp);
		}
	}

	/**
	 * 获取没有被占用的车位
	 * 
	 * @param pspId
	 * @return
	 */
	public List<BaseModel> getAllPspWherePspInAndOut(String pspId) {
		if (StringUtils.isNotNullAndEmpty(pspId)) {
			List<PSp> psp = pspDao.getPspWhereCarIsNull("%" + pspId + "%");
			return getModelsByBeans(psp);
		} else {
			List<PSp> psp = pspDao.findAll();
			return getModelsByBeans(psp);
		}
	}

	/**
	 * 获取所有车位
	 * 
	 * @param pspId
	 * @return
	 */
	public List<BaseModel> getAllPspWithoutParam() {
		return getModelsByBeans(pspDao.findAll());
	}

	/**
	 * 根据pspId获取车位
	 * 
	 * @param pspId
	 * @return
	 */
	public List<BaseModel> getPspBypspId(String pspId) {
		PSpModel model = new PSpModel();
		model.setPspId(pspId);
		model.setNowState(-1);
		model.setIsRent(-1);
		model.setIsEnable(-1);
		Specification<PSp> spec = buildSpecification(model);
		List<PSp> psp = baseDao.findAll(spec, null).getContent();
		return getModelsByBeans(psp);
	}

	/**
	 * 根据pspId获取车位（包含车位锁信息）
	 * 
	 * @param pspId
	 * @return
	 */
	public List<BaseModel> getPspBypspIdAndPSPLock(String pspId) {
		PSpModel model = new PSpModel();
		model.setPspId(pspId);
		model.setNowState(-1);
		model.setIsRent(-1);
		model.setIsEnable(-1);
		Specification<PSp> spec = buildSpecification(model);
		List<PSp> psp = baseDao.findAll(spec, null).getContent();
		return getModelsByBeans(psp);
	}

	/**
	 * model类转实体类
	 * 
	 * @param pspModel
	 * @return
	 * @throws ServiceException
	 */
	public PSp addByModel(PSpModel pspModel) throws ServiceException {
		PSp psp = new PSp();
		try {
			BeanUtils.copyProperties(pspModel, psp);
		} catch (IllegalAccessException | InvocationTargetException
				| IllegalArgumentException e) {
			logger.error("车位实体类转化失败", e);
		}
		Park park = parkDao.findByNameAndDelFlagFalse(pspModel.getParkName());
		psp.setPark(park);
		psp.setIsEnable(true);
		psp.setNowState(false);
		psp = pspDao.save(psp);
		return psp;
	}

	/**
	 * 判断输入的车位编号是否已存在
	 * 
	 * @param pspId
	 * @return
	 * @throws ServiceException
	 */
	public boolean getSamePspId(String pspId) throws ServiceException {

		PSp psp = pspDao.findByPspId(pspId);
		if (psp != null) {
			return false;
		}
		return true;
	}

	/**
	 * 更新车位锁
	 * 
	 * @param id
	 * @param pSpLock
	 * @return
	 * @throws ServiceException
	 */
	public boolean updatePspLock(Integer id, PSpLock pSpLock)
			throws ServiceException {

		int count = pspDao.updatePspByLock(pSpLock, id);
		if (count == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 添加方法
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean add(PSpModel model) throws ServiceException {
		PSp psp = addByModel(model);
		if (psp == null) {
			return false;
		}
		LogUtil.save("车位模块", LogUtil.Option.ADD, "新增车位信息：" + psp.toString());
		return true;
	}

	/**
	 * 批量添加方法
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean batchAdd(PSpModel model) throws ServiceException {
		String pspId = model.getPspId();
		// 切割字符串的方法
		String deleteFirstString = pspId.substring(1);
		// 获取字符串第一个字符
		String startString = pspId.substring(0, 1);
		Integer startNum = Integer.parseInt(deleteFirstString);
		Integer pspNum = model.getPspNum();
		Integer i;
		for (i = 0; i < pspNum; i++) {
			String newPspId = startString
					+ String.format("%04d", (startNum + i));// 重新生成的pspId
			model.setPspId(newPspId);
			model.setPosit(null);
			PSp psp = addByModel(model);
			if (psp == null) {
				return false;
			}
			LogUtil.save("车位模块", LogUtil.Option.ADD,
					"批量新增车位信息：" + psp.toString());
		}
		return true;
	}

	/**
	 * 租赁方法
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean addRent(RechargeRecModel model) throws ServiceException {
		boolean flag = true;
		RechargeRec rechargeRec = new RechargeRec();
		UserPSp userPSp = new UserPSp();
		try {
			BeanUtils.copyProperties(model, rechargeRec);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("车位管理-实体类与Model类转化失败", e);
		}
		User user = UserUtils.findBylicensePlate(model.getLicensePlate());

		Operater operater = OperaterUtils.getOperater();
		String pspId = model.getPspId();
		// 修改车位信息中与租赁相关的信息
		PSp pSp = pspDao.findByPspId(pspId);
		pspDao.updatePspIsRent(pspId);
		ChargeRule chargeRule = PspExtend.getChargeRuleByPspId(pspId);
		// 绑定与chanrgeRec相关的实体
		AnchorRent anchorRent = AnchorRentUtils.findById(model
				.getAnchorRentPK());
		// 加入充值记录
		rechargeRec.setMoney(model.getMoney());
		rechargeRec.setRechargeTime(new Date());
		rechargeRec.setBeginDate(model.getBeginDate());
		rechargeRec.setEndDate(DateUtils.dateAddMonths(model.getBeginDate(),
				anchorRent.getMonth()));
		rechargeRec.setChargeRule(chargeRule);
		rechargeRec.setOperater(operater);
		rechargeRec.setUser(user);
		rechargeRec.setRechargeWay(0);
		rechargeRec.setRechargeType(RechargeType.RENTPSP.getValue());

		// 绑定用户和车位关系
		userPSp.setUser(user);
		userPSp.setExpirationTime(DateUtils.dateAddMonths(model.getBeginDate(),
				anchorRent.getMonth()));
		userPSp.setRentTime(model.getBeginDate());
		userPSp.setRentFee(model.getMoney());
		userPSp.setChargeRule(chargeRule);
		userPSp.setPSp(pSp);
		// 保存用户车位信息
		userPSp = UserPSpUtils.save(userPSp);
		// 保存收费记录
		rechargeRec = RechargeRecUtils.save(rechargeRec);
		if (rechargeRec == null) {
			throw new ServiceException("新增用户租赁车位记录失败");
		} else if (userPSp == null) {
			throw new ServiceException("新增用户租赁车位记录失败");
		} else
			flag = true;
		LogUtil.save("车位租赁模块", LogUtil.Option.RENT,
				"车位租赁：" + rechargeRec.toString());
		return flag;
	}

	/**
	 * 根据车位的ID获取车位及租赁规则
	 * 
	 * @param id
	 * @return
	 */
	public PSpModel getPspById(int id) {
		PSp psp = pspDao.findOne(id);
		// 将psp中的chargeRule转化的方法
		return PspChargeRuleExtend.covertModelByPsp(psp);
	}

	/**
	 * 根据车位的pspId获取车位及租赁规则
	 * 
	 * @param pspId
	 * @return
	 */
	public PSpModel getChargeRuleByEditPspId(String pspId) {
		PSp psp = pspDao.findByPspId(pspId);
		// 将psp中的chargeRule转化的方法
		return PspChargeRuleExtend.covertModelByPsp(psp);
	}

	/**
	 * 编辑方法
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean edit(PSpModel model) throws ServiceException {
		boolean flag = true;
		PSp psp = pspDao.findOne(model.getId());

		if (psp == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		try {
			BeanUtils.copyProperties(model, psp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("车位管理-实体类与Model类转化失败", e);
			e.printStackTrace();
		}
		// 保存park
		Park park = parkDao.findByNameAndDelFlagFalse(model.getParkName());
		psp.setPark(park);
		psp = pspDao.save(psp);
		if (psp == null) {
			flag = false;
			return flag;
		}
		LogUtil.save("车位模块", LogUtil.Option.EDIT, "修改车位信息：" + psp.toString());
		return flag;
	}

	/**
	 * 删除车位
	 * 
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	public boolean deletePsp(String ids) throws ServiceException {
		List<Integer> idss = super.getIdList(ids);
		for (Integer id : idss) {
			PSp psp = pspDao.findOne(id);
			psp.setDelFlag(true);
			psp.setArea(null);
			// psp.setPark(null);
			LockUtils.deleteLockById(psp.getPSpLock().getId());
			psp.setPSpLock(null);
			// 删除拖拽界面中的车位信息
			if (psp.isNowState() == true) {
				ArrangeUtils.deletePsp(psp.getPspId(), "psp");
			}
			pspDao.save(psp);
			LogUtil.save("车位模块", LogUtil.Option.DEL, "车位删除：" + psp.toString());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.langmy.park.common.service.BaseService#getModelsByBeans(java.util
	 * .List)
	 */
	@Override
	protected List<BaseModel> getModelsByBeans(List<PSp> pSps) {
		List<PSpModel> pSpModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(pSps)) {
			return Lists.newArrayList(pSpModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "park.name");
		map.put("targetPro1", "parkName");
		map.put("sourcePro2", "car.licensePlate");
		map.put("targetPro2", "licensePlate");
		map.put("sourcePro3", "PSpLock.lockId");
		map.put("targetPro3", "lockId");
		map.put("sourcePro4", "area.name");
		map.put("targetPro4", "areaName");
		try {
			pSpModels = BeanUtils.copyListProperties(pSps, PSpModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(pSpModels);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.langmy.park.common.service.BaseService#buildSpecification(com.langmy
	 * .park.common.model.BaseModel)
	 */
	@Override
	protected Specification<PSp> buildSpecification(BaseModel model) {
		PSpModel pSpModel = (PSpModel) model;

		String pspId = pSpModel.getPspId();
		String parkName = pSpModel.getParkName();
		int isRent = pSpModel.getIsRent();
		int isEnable = pSpModel.getIsEnable();
		int nowState = pSpModel.getNowState();
		return new Specification<PSp>() {
			@Override
			public Predicate toPredicate(Root<PSp> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.isNotNullAndEmpty(pspId)) {
					list.add(cb.like(root.get("pspId"), "%" + pspId + "%"));
				}
				if (parkName != null) {
					list.add(cb.like(root.get("park").get("name"), "%"
							+ parkName + "%"));
				}
				if (isEnable != -1) {
					if (isEnable == 0) {
						list.add(cb.equal(root.get("isEnable"), false));
					} else if (isEnable == 1) {
						list.add(cb.equal(root.get("isEnable"), true));
					}
				}
				if (isRent != -1) {
					if (isRent == 0) {
						list.add(cb.equal(root.get("isRent"), false));
					} else if (isRent == 1) {
						list.add(cb.equal(root.get("isRent"), true));
					}
				}
				if (nowState != -1) {
					if (nowState == 0) {
						list.add(cb.equal(root.get("nowState"), false));
					} else if (nowState == 1) {
						list.add(cb.equal(root.get("nowState"), true));
					}
				}
				list.add(cb.equal(root.get("delFlag"), false));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}

	/**
	 * 启用方法
	 * 
	 * @param ids
	 * @return
	 */
	public String enable(String ids) {
		String message = "";
		List<Integer> idss = super.getIdList(ids);
		for (Integer id : idss) {
			PSp psp = pspDao.findOne(id);
			String pspId = psp.getPspId();
			if (dragDivDao.findByDivId(pspId) != null) {
				// 改变车位为启用状态
				ArrangeUtils.updateCameraPspOpen(pspId);
			}
			int updatePsp = pspDao.enable(id);
			if (updatePsp == 1) {
				message = "";
				LogUtil.save("车位模块", LogUtil.Option.START,
						"车位启用：" + psp.toString());
			} else
				message = pspId + "/";
		}
		return message;
	}

	/**
	 * 禁用车位
	 * 
	 * @param ids
	 * @return
	 */
	public String disable(String ids) {
		List<Integer> idss = super.getIdList(ids);
		String message = "";
		for (Integer id : idss) {
			PSp psp = pspDao.findOne(id);
			String pspId = psp.getPspId();
			Car car = psp.getCar();
			if (car != null) {
				message = "当前车位上有车";
			} else {
				if (dragDivDao.findByDivId(pspId) != null) {
					// 改变车位为禁用状态
					ArrangeUtils.updateCameraPspClose(pspId);
				}
				int count = pspDao.disable(id);
				if (count == 1) {
					LogUtil.save("车位模块", LogUtil.Option.FORBIDDEN, "车位禁用："
							+ psp.toString());
					message = "";
				} else {
					message = pspId + "/";
				}
			}
		}
		return message;
	}

	/**
	 * 车辆进入车位时改变当前车位上的车信息
	 * 
	 * @param pspId
	 * @param car
	 */
	public void changePspCarIn(String pspId, Car car) {
		PSp pSp = pspDao.findByPspId(pspId);
		pSp.setCar(car);
		pspDao.save(pSp);
	}

	/**
	 * 车辆出车位时改变当前车位上的车信息
	 * 
	 * @param pspId
	 */
	public void changePspCarOut(String pspId) {
		PSp pSp = pspDao.findByPspId(pspId);
		pSp.setCar(null);
		pspDao.save(pSp);
	}
}
