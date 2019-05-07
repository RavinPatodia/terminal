package com.langmy.terminal.modules.charge.service;

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
import com.langmy.terminal.common.entity.DctRule;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.charge.dao.DctRuleDao;
import com.langmy.terminal.modules.charge.model.DctRuleModel;
import com.langmy.terminal.modules.charge.service.extend.DctRuleExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 优惠规则Service
 * 
 * @author lzy
 *
 */
@Service
public class DctRuleService extends BaseRuleService<DctRule> {
	// 优惠规则类型
	public static int DCT_TYPE_BIRTH = 0;
	public static int DCT_TYPE_HOLIDAY = 1;
	public static int DCT_TYPE_WEEKEND = 2;
	public static int DCT_TYPE_TIME = 3;
	public static int DCT_TYPE_DATE = 4;
	public static int DCT_TYPE_RECHARGE = 5;

	// 双休日类型
	public static int WEEKEND_TYPE_SAT = 0;
	public static int WEEKEND_TYPE_SUN = 1;
	public static int WEEKEND_TYPE_BOTH = 2;

	@Autowired
	private DctRuleGroupService dctRuleGroupService;
	@Autowired
	private DctRuleDao dctRuleDao;

	@Autowired
	public DctRuleService(DctRuleDao dctRuleDao) {
		super(dctRuleDao);
	}

	public boolean add(DctRuleModel dctRuleModel) {
		DctRule dctRule = DctRuleExtend.covertToDctRule(dctRuleModel);
		dctRule = dctRuleDao.save(dctRule);
		if (dctRule == null) {
			return false;
		}

		LogUtil.save("收费模块", LogUtil.Option.ADD, "新增优惠规则：" + dctRule.toString());
		return true;
	}

	public boolean edit(DctRuleModel model) throws ServiceException {
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(model));
		DctRule dctRule = DctRuleExtend.covertToDctRule(model);
		dctRule = dctRuleDao.save(dctRule);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(dctRule));
		if(dctRule==null){
			logger.error("保存优惠规则失败");
			return false;
		}
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"修改优惠规则：" + dctRule.toString());
		return true;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<DctRule> dctRules) {
		return Lists.newArrayList(DctRuleExtend.covertToDctRuleModel(dctRules));
	}

	/**
	 * 获得当前在运用的启用的优惠规则
	 * 
	 * @return
	 */
	public List<DctRuleModel> getEnableAndActiveDctRules() {
		return DctRuleExtend.covertToDctRuleModel(dctRuleDao
				.findByDelFlagFalse());
	}

	/**
	 * 根据编号找到优惠规则
	 * 
	 * @param id
	 * @return
	 */
	public DctRuleModel getDctRuleById(int id) {
		DctRule dctRule = dctRuleDao.findOne(id);
		return DctRuleExtend.covertToModel(dctRule);
	}

	@Override
	protected Specification<DctRule> buildSpecification(BaseModel model) {
		DctRuleModel dctRuleModel = (DctRuleModel) model;
		String dctId = dctRuleModel.getDctId();
		String dctName = dctRuleModel.getDctName();
		int type = dctRuleModel.getType();

		return new Specification<DctRule>() {
			@Override
			public Predicate toPredicate(Root<DctRule> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				list.add(cb.equal(root.get("delFlag").as(boolean.class), false));
				if (StringUtils.isNotNullAndEmpty(dctId)) {
					list.add(cb.like(root.get("dctId"), "%" + dctId + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(dctName)) {
					list.add(cb.like(root.get("dctName"), "%" + dctName + "%"));
				}
				if (type != -1) {
					list.add(cb.equal(root.get("type"), type));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
	
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            优惠规则Id {1,2,3}
	 * @return boolean
	 * @throws ServiceException 
	 */
	public void delete(String ids) throws ServiceException {
		List<Integer> idList = super.getIdList(ids);
		for(int id:idList){
			//逻辑删除长期收费规则
			int count = dctRuleDao.del(id);
			if(count==0)
				throw new ServiceException("删除优惠规则:id为"+id+"失败");
			DctRule dctRule = dctRuleDao.findOne(id);
			dctRule.setDctRuleGroups(Lists.newArrayList());
			dctRule = dctRuleDao.save(dctRule);
			if(dctRule==null)
				throw new ServiceException("删除优惠规则与优惠规则组对应的联系失败");
			LogUtil.save("优惠规则管理", LogUtil.Option.DEL, "删除优惠规则：" + id);
		}
	}
}
