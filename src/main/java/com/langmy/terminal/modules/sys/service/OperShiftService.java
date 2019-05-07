package com.langmy.terminal.modules.sys.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.config.Constant.PayType;
import com.langmy.terminal.common.config.Constant.SessionKey;
import com.langmy.terminal.common.config.Constant.UserGroupType;
import com.langmy.terminal.common.entity.AdminssionRec;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.CarType;
import com.langmy.terminal.common.entity.ChargeRec;
import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.entity.OperShift;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.SystemUtils;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.caradmission.dao.AdmissionRecDao;
import com.langmy.terminal.modules.caradmission.utils.CarTypeUtils;
import com.langmy.terminal.modules.device.utils.DeviceUtils;
import com.langmy.terminal.modules.device.utils.TerminalUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.psp.utils.PSpUtils;
import com.langmy.terminal.modules.sys.dao.OperShiftDao;
import com.langmy.terminal.modules.sys.dao.OperaterDao;
import com.langmy.terminal.modules.sys.model.OperShiftModel;
import com.langmy.terminal.modules.sys.security.FormAuthenticationFilter;
import com.langmy.terminal.modules.sys.security.UsernamePasswordToken;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 操作员交接班业务层
 * 
 * @author ZZD
 *
 */
@Service
public class OperShiftService extends BaseService<OperShift> {

	@Autowired
	private OperShiftDao operShiftDao;
	@Autowired
	private AdmissionRecDao admissionRecDao;
	@Autowired
	private OperaterDao operaterDao;

	public OperShiftService() {
		super.baseDao = SpringContextHolder.getBean(OperShiftDao.class);
	}

	/**
	 * 操作员交接班
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public Json shift(OperShiftModel model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Json j = new Json();
		OperShift operShift = new OperShift();
		try {
			BeanUtils.copyProperties(model, operShift);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("操作员交接班-model类赋值给实体类失败");
			j.setSuccess(false);
			return j;
		}
		// 交班人
		int offWorkOperId = model.getOffOperPK();
		Operater offWorkOper = operaterDao.findOne(offWorkOperId);
		operShift.setOffWorkOper(offWorkOper);
		// 接班人
		Integer workOperId = model.getWorkOperPK();
		Operater workOper = operaterDao.findOne(workOperId);
		operShift.setWorkOper(workOper);
		// 验证接班人密码
		String pwd = model.getPwd();
		if (!SystemService.validatePassword(pwd, workOper.getOperPwd())) {
			j.setMsg("操作员密码错误！");
			j.setSuccess(false);
			return j;
		}
		// 终端机
		Integer termianlId = model.getTerminalPK();
		Device terminal = DeviceUtils.getDeviceById(termianlId);
		operShift.setTerminal(terminal);
		operShift = operShiftDao.save(operShift);
		if (operShift == null) {
			j.setMsg("交接班失败！");
			j.setSuccess(false);
			return j;
		}
		// 当前已登录操作员退出系统, 接班操作员登陆
		Session session = SystemUtils.getSession();
		session.removeAttribute(SessionKey.LOGINTIME.getValue());
		session.removeAttribute(SessionKey.CARNUM.getValue());
		SecurityUtils.getSubject().logout();
		// 登陆
		String host = request.getRemoteHost();
		String captcha = WebUtils.getCleanParam(request,
				FormAuthenticationFilter.DEFAULT_CAPTCHA_PARAM);
		UsernamePasswordToken token = new UsernamePasswordToken(
				workOper.getOperName(), pwd.toCharArray(), false, host, captcha);
		FormAuthenticationFilter filter = new FormAuthenticationFilter();
		if (filter.changeLogin(token, request, response)) {
			j.setSuccess(true);
			LogUtil.save("操作员交接班", LogUtil.Option.ADD, "添加操作员交接班记录："
					+ operShift.toString());// 日志
		}
		return j;
	}

	/**
	 * 获取操作员交接班详细信息
	 * 
	 * @param operId
	 * @return
	 */
	public OperShiftModel view(HttpServletRequest request) {
		OperShiftModel model = new OperShiftModel();
		// 获得当前登录操作员
		Operater oper = OperaterUtils.getOperater();
		if (oper == null) {
			return model;
		}
		Date now = new Date();
		model.setOffOperName(oper.getName());
		model.setOffOperPK(oper.getId());
		Session session = SystemUtils.getSession();
		Date workTime = (Date) session.getAttribute(SessionKey.LOGINTIME
				.getValue());

		int workCar = (int) session.getAttribute(SessionKey.CARNUM.getValue());
		model.setWorkTime(workTime);
		model.setWorkCar(workCar);
		model.setOffWorkTime(now);

		// 根据操作员和出场时间获得出入场记录 ，计算该操作员当天应收金额 、实收金额、普通会员扣款
		List<AdminssionRec> adminssionRecs = admissionRecDao
				.findByOutOperAndOutTimeBetween(oper, workTime, now);
		double totalPay = 0;// 总流水==
		double tempCash = 0;// 临时车辆收费金额
		double memberAccountPay = 0;// 普通会员账户扣款金额
		double memberCash = 0;// 普通会员支付现金
		double amoutPay = 0;// 停车应收现金金额
		double actualPay = 0;// 停车实收现金金额

		int chargeCar = 0;// 收费车辆数
		int tempCar = 0;// 临时车辆出场数
		int memberCar = 0;// 普通会员出场数
		int anchorCar = 0;// 长期会员车辆出场数
		int freeCar = 0;// VIP免费车辆出场数
		int freePlate = 0;// 免费车牌数
		int specialFreeCar = 0;// 发生费用免费出场数
		int outLift = 0;// 手动开闸出场数
		int outCar = 0;// 自动开闸出场数
		boolean special = false;// 标志是否发生费用免费出场数
		for (AdminssionRec rec : adminssionRecs) {
			Car car = rec.getCar();
			User user = car.getUser();
			if (user == null) {
				continue;
			}
			int uType = user.getUGroup().getType();
			Set<ChargeRec> chargeRecs = rec.getChargeRecs();
			for (ChargeRec chargeRec : chargeRecs) {
				if (uType == UserGroupType.TEMPORARYUSER.getValue()) {
					tempCash += chargeRec.getActualPay();// 临时车辆收费金额
				}
				if (uType == UserGroupType.MEMBER_COMMON.getValue()) {
					if (chargeRec.getPayType() == PayType.ACCOUNT.getValue()) {
						memberAccountPay += chargeRec.getActualPay();// 普通会员账户扣款金额
					}
					if (chargeRec.getPayType() == PayType.CASH.getValue()) {
						memberCash += chargeRec.getActualPay();// 普通会员支付现金
					}

				}
				if (chargeRec.getPayType() == PayType.CASH.getValue()) {
					amoutPay += chargeRec.getAmoutPay();// 停车应收现金金额
					actualPay += chargeRec.getActualPay();// 停车实收现金金额
				}
				if (chargeRec.getAmoutPay() > 0
						&& chargeRec.getActualPay() <= 0) {
					special = true;// 应付金额>0 实付金额<=0
				}
				totalPay += chargeRec.getActualPay();// 总流水
			}
//			if (chargeRecs != null && chargeRecs.size() > 0 && rec.getFee() > 0) {
//				chargeCar++;// 收费车辆数(有收费记录的出入场记录)
//			}
			if (uType == UserGroupType.TEMPORARYUSER.getValue()) {
				tempCar++;// 临时车辆出场数
			}
			if (uType == UserGroupType.MEMBER_COMMON.getValue()) {
				memberCar++;// 普通会员出场数
			}
			if (uType == UserGroupType.LONGTREM.getValue()) {
				anchorCar++;// 长期会员车辆出场数
			}
			if (uType == UserGroupType.VIP.getValue()) {
				freeCar++;// VIP免费车辆出场数
			}
			CarType carType = CarTypeUtils.getByLicensePlateType(car.getType());
			if (carType.getIsFree()) {
				freePlate++;// 免费车牌数
			}
			if (special) {
				specialFreeCar++;// 发生费用免费出场数
			}
			// 出场手动开闸
			if (rec.isOutLiftFlag()) {
				outLift++;// 手动开闸出场数
			} else {
				outCar++;// 自动开闸出场数
			}
		}
		model.setTotalPay(totalPay);
		model.setTempCash(tempCash);// 临时车辆收费金额==
		model.setMemberAccountPay(memberAccountPay);// 普通会员账户扣款金额
		model.setMemberCash(memberCash);// 普通会员支付现金
		model.setActualPay(actualPay);
		model.setAmoutPay(amoutPay);

		model.setChargeCar(chargeCar);
		model.setTempCar(tempCar);// 临时车辆出场数
		model.setMemberCar(memberCar);// 普通会员出场数
		model.setAnchorCar(anchorCar);
		model.setFreePlate(freePlate);// 免费车牌数
		model.setFreeCar(freeCar);
		model.setSpecialFreeCar(specialFreeCar);

		model.setOutLift(outLift);
		model.setOutCar(outCar);
		// 获得停车场内停车数
		int offWorkCar = PSpUtils.getPspNumWherePpsCarIsNotNull();
		model.setOffWorkCar(offWorkCar);
		// 终端机
		String ip = SystemUtils.getRemoteHost(request);
		System.out.println("###########################" + ip);
		Device terminal = TerminalUtils.getTerminal("192.168.203.129");
		if (terminal != null) {
			model.setTerminalName(terminal.getName());
			model.setTerminalPK(terminal.getId());
		}
		return model;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<OperShift> operShift) {

		List<OperShiftModel> operShiftModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(operShift)) {
			return Lists.newArrayList(operShiftModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "offWorkOper.id");
		map.put("targetPro1", "offOperPK");
		map.put("sourcePro2", "offWorkOper.name");
		map.put("targetPro2", "offOperName");
		map.put("sourcePro3", "workOper.id");
		map.put("targetPro3", "workOperPK");
		map.put("sourcePro4", "workOper.name");
		map.put("targetPro4", "workOperName");
		map.put("sourcePro5", "terminal.name");
		map.put("targetPro5", "terminalName");
		try {
			operShiftModels = BeanUtils.copyListProperties(operShift,
					OperShiftModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("记录查询-操作员交接班记录-实体类与Model类数组转化失败");
		}
		return Lists.newArrayList(operShiftModels);

	}

	@Override
	protected Specification<OperShift> buildSpecification(BaseModel model) {
		Integer offOperPK = ((OperShiftModel) model).getOffOperPK();
		Integer workOperPK = ((OperShiftModel) model).getWorkOperPK();
		Date offTimeFrom = ((OperShiftModel) model).getOffTimeFrom();
		Date offTimeTo = ((OperShiftModel) model).getOffTimeTo();
		Date workTimeFrom = ((OperShiftModel) model).getWorkTimeFrom();
		Date workTimeTo = ((OperShiftModel) model).getWorkTimeTo();
		Integer terminalPK = ((OperShiftModel) model).getTerminalPK();
		return new Specification<OperShift>() {

			@Override
			public Predicate toPredicate(Root<OperShift> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (offOperPK != null) {
					list.add(cb.equal(root.get("offWorkOper").get("id"),
							offOperPK));
				}
				if (workOperPK != null) {
					list.add(cb.equal(root.get("workOper").get("id"),
							workOperPK));
				}
				if (offTimeFrom != null) {
					list.add(cb.greaterThan(
							root.get("offWorkTime").as(Date.class),
							DateUtils.getDateStart(offTimeFrom)));
				}
				if (offTimeTo != null) {
					list.add(cb.lessThan(
							root.get("offWorkTime").as(Date.class),
							DateUtils.getDateEnd(offTimeTo)));
				}
				if (workTimeFrom != null) {
					list.add(cb.greaterThan(
							root.get("workTime").as(Date.class),
							DateUtils.getDateStart(workTimeFrom)));
				}
				if (workTimeTo != null) {
					list.add(cb.lessThan(root.get("workTime").as(Date.class),
							DateUtils.getDateEnd(workTimeTo)));
				}
				if (terminalPK != null) {
					list.add(cb.equal(root.get("terminal").get("id"),
							terminalPK));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

	}
}
