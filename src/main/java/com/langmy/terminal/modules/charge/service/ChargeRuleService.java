package com.langmy.terminal.modules.charge.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.MeterCharge;
import com.langmy.terminal.common.entity.TimeCharge;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.DelValidate;
import com.langmy.terminal.modules.charge.dao.ChargeRuleDao;
import com.langmy.terminal.modules.charge.model.ChargeRuleModel;
import com.langmy.terminal.modules.charge.model.MeterChargeModel;
import com.langmy.terminal.modules.charge.model.TimeChargeModel;
import com.langmy.terminal.modules.charge.service.extend.ChargeRuleExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

@Service
public class ChargeRuleService extends BaseRuleService<ChargeRule> {

	public static int RENT_TYPE_TIME = 0;
	public static int RENT_TYPE_METER = 1;
	public static int RENT_TYPE_ANCHOR = 2;
	public static int FREE = 3;

	@Autowired
	private TimeChargeService timeChargeService;
	@Autowired
	private MeterChargeService meterChargeService;
	@Autowired
	private ChargeRuleDao chargeRuleDao;

	@Autowired
	public ChargeRuleService(ChargeRuleDao chargeRuleDao) {
		super(chargeRuleDao);
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<ChargeRule> chargeRules) {
		return Lists.newArrayList(ChargeRuleExtend.covertToModel(chargeRules));
	}

	/**
	 * 
	 * @param model
	 * 
	 * @param rentRuleId
	 * @param isHistory
	 * @return
	 */
	@Override
	protected Specification<ChargeRule> buildSpecification(BaseModel model) {
		ChargeRuleModel chargeRuleModel = (ChargeRuleModel) model;
		String rentRuleId = chargeRuleModel.getRentRuleId();
		String ruleName = chargeRuleModel.getRuleName();
		int rentType = chargeRuleModel.getRentType();
		return new Specification<ChargeRule>() {
			@Override
			public Predicate toPredicate(Root<ChargeRule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("delFlag").as(boolean.class), false));
				if (StringUtils.isNotNullAndEmpty(rentRuleId)) {
					list.add(cb.like(root.get("rentRuleId"), "%" + rentRuleId + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(ruleName)) {
					list.add(cb.like(root.get("ruleName"), "%" + ruleName + "%"));
				}
				if (rentType != -1) {
					list.add(cb.equal(root.get("rentType"), rentType));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}

	/**
	 * 新增收费规则
	 * 
	 * @param model
	 *            前台传入的包含收费规则属性的model
	 * @return
	 */
	public boolean add(ChargeRuleModel model) {
		int type = model.getRentType();
		if (type == RENT_TYPE_TIME)
			return addTimeChargeRule(model);
		else if (type == RENT_TYPE_METER)
			return addMeterChargeRule(model);
		else if (type == RENT_TYPE_ANCHOR)
			return addAnchorGroupChargeRule(model);
		else if (type == FREE)
			return addFreeChargeRule(model);
		return false;
	}

	private boolean addFreeChargeRule(ChargeRuleModel model) {
		ChargeRule chargeRule = ChargeRuleExtend.covertToChargeRule(model);
		chargeRule = chargeRuleDao.save(chargeRule);
		if (chargeRule == null)
			return false;
		LogUtil.save("收费模块", LogUtil.Option.ADD, "新增收费规则：" + chargeRule.toString());
		return true;
	}

	/**
	 * 保存计时的收费规则，并记录日志
	 * 
	 * @param model
	 *            前台传入的包含收费规则属性的model
	 * @return
	 */
	private boolean addTimeChargeRule(ChargeRuleModel model) {
		TimeCharge timeCharge = timeChargeService.add(model.getTimeChargeModel());
		if (timeCharge == null) {
			logger.error("新增收费规则中的计时收费失败");
			return false;
		}
		ChargeRule chargeRule = ChargeRuleExtend.covertToChargeRule(model);
		chargeRule.setBaseRuleId(timeCharge.getId());
		chargeRule = chargeRuleDao.save(chargeRule);
		if (chargeRule == null)
			return false;
		LogUtil.save("收费模块", LogUtil.Option.ADD, "新增收费规则：" + chargeRule.toString());
		return true;
	}

	/**
	 * 保存计次的收费规则，并记录日志
	 * 
	 * @param model
	 *            前台传入的包含收费规则属性的model
	 * @return
	 */
	private boolean addMeterChargeRule(ChargeRuleModel model) {
		MeterCharge meterCharge = meterChargeService.add(model.getMeterChargeModel());
		if (meterCharge == null) {
			logger.error("新增收费规则中的计次收费失败");
			return false;
		}
		ChargeRule chargeRule = ChargeRuleExtend.covertToChargeRule(model);
		chargeRule.setBaseRuleId(meterCharge.getId());
		chargeRule = chargeRuleDao.save(chargeRule);
		if (chargeRule == null)
			return false;
		LogUtil.save("收费模块", LogUtil.Option.ADD, "新增收费规则：[" + chargeRule.toString() + "]");
		return true;
	}

	/**
	 * 保存长期收费的收费规则，并记录日志
	 * 
	 * @param model
	 *            前台传入的包含收费规则属性的model
	 * @return
	 */
	private boolean addAnchorGroupChargeRule(ChargeRuleModel model) {
		ChargeRule chargeRule = ChargeRuleExtend.covertToChargeRule(model);
		chargeRule = chargeRuleDao.save(chargeRule);
		if (chargeRule == null)
			return false;
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"新增收费规则：" + chargeRule.toString() + "||新增长期收费组:主键=" + chargeRule.getBaseRuleId());
		return true;
	}

	/**
	 * 规则的修改
	 * 
	 * @throws InstantiationException
	 * @throws IOException
	 * @throws IntrospectionException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws ServiceException
	 */
	public boolean edit(ChargeRuleModel model) throws ServiceException {
		if(logger.isDebugEnabled())
			logger.debug("从前台的得到属性："+JSONObject.toJSONString(model));
		int type = model.getRentType();
		if (type == RENT_TYPE_TIME)
			return editTimeChargeRule(model);
		else if (type == RENT_TYPE_METER)
			return editMeterChargeRule(model);
		else if (type == RENT_TYPE_ANCHOR)
			return editAnchorGroupChargeRule(model);
		else if (type == FREE)
			return editFreeChargeRule(model);
		return false;
	}

	/**
	 * @param model
	 * @return
	 */
	private boolean editFreeChargeRule(ChargeRuleModel model) {
		ChargeRule chargeRule = ChargeRuleExtend.covertToChargeRule(model);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(chargeRule));
		chargeRule = chargeRuleDao.save(chargeRule);
		if(chargeRule==null){
			logger.error("保存免费类型的收费规则失败");
			return false;
		}
		return true;
	}

	/**
	 * @param model
	 * @return
	 */
	private boolean editAnchorGroupChargeRule(ChargeRuleModel model) {
		ChargeRule chargeRule = ChargeRuleExtend.covertToChargeRule(model);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(chargeRule));
		chargeRule = chargeRuleDao.save(chargeRule);
		if(chargeRule==null){
			logger.error("保存免费类型的收费规则失败");
			return false;
		}
		return true;
	}

	/**
	 * 修改类型是计次的收费规则
	 * @param model
	 * @return
	 * @throws ServiceException 
	 */
	private boolean editMeterChargeRule(ChargeRuleModel model) throws ServiceException {
		MeterChargeModel meterModel = model.getMeterChargeModel();
		meterModel.setId(model.getBaseRuleId());
		if(!meterChargeService.edit(meterModel)){
			logger.error("修改收费规则-修改计次规则失败");
			return false;
		}
		ChargeRule chargeRule = ChargeRuleExtend.covertToChargeRule(model);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(chargeRule));
		chargeRule = chargeRuleDao.save(chargeRule);
		if(chargeRule==null){
			logger.error("保存免费类型的收费规则失败");
			throw new ServiceException("修改收费规则失败");
		}
		return true;
	}

	/**
	 * @param model
	 * @return
	 * @throws ServiceException 
	 */
	private boolean editTimeChargeRule(ChargeRuleModel model) throws ServiceException {
		TimeChargeModel timeModel = model.getTimeChargeModel();
		timeModel.setId(model.getBaseRuleId());
		if(!timeChargeService.edit(timeModel)){
			logger.error("修改收费规则-修改计时规则失败");
			return false;
		}
		ChargeRule chargeRule = ChargeRuleExtend.covertToChargeRule(model);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(chargeRule));
		chargeRule = chargeRuleDao.save(chargeRule);
		if(chargeRule==null){
			logger.error("保存免费类型的收费规则失败");
			throw new ServiceException("修改收费规则失败");
		}
		return true;
	}

	/**
	 * 根据收费规则的Id获取相应的收费规则model
	 * 
	 * @param id
	 * @return
	 */
	public ChargeRuleModel getChargeRuleById(int id) {
		ChargeRule charge = chargeRuleDao.findOne(id);
		return ChargeRuleExtend.covertToModel(charge);
	}

	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            优惠规则Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = chargeRuleDao.del(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("收费规则管理", LogUtil.Option.DEL, "删除收费规则：" + id);
			}
		}

		return flag;
	}

	/**
	 * 删除验证
	 * @param ids
	 * @return
	 */
	public DelValidate validateDel(String ids){
		List<Integer> idList = super.getIdList(ids);
		List<Integer> idPermission = Lists.newArrayList();
		List<String> nameNotPermission = Lists.newArrayList();
		List<String>  associateObjs = Lists.newArrayList();
		for(int id:idList){
			ChargeRule group = chargeRuleDao.findOne(id);
			List<UGroup> uGroups = group.getUGroups();
			if(ListUtils.isNullOrEmpty(uGroups)){
				idPermission.add(id);
			}
			else{
				nameNotPermission.add(group.getName());
				for(UGroup uGroup:uGroups)
					associateObjs.add(uGroup.getName());
			}
		}
		DelValidate delValidate = new DelValidate(idPermission, nameNotPermission, associateObjs);
		return delValidate;
	}

}
