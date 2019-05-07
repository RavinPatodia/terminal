package com.langmy.terminal.modules.ruleEngine.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.DctRule;
import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.persistence.DetailRule;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.modules.charge.utils.DetailRuleUtils;
import com.langmy.terminal.modules.ruleEngine.chargeRule.MeterRuleModel;
import com.langmy.terminal.modules.ruleEngine.chargeRule.Rule;
import com.langmy.terminal.modules.ruleEngine.chargeRule.TimeRuleModel;
import com.langmy.terminal.modules.ruleEngine.dctRule.DctRuleGroupModel;
import com.langmy.terminal.modules.ruleEngine.dctRule.DctRuleModel;
import com.langmy.terminal.modules.ruleEngine.service.RuleEngineService;

/**
 * @author lzy
 * 2015年5月31日
 */
public class RuleEngineExtend extends BaseExtend{
	
	/**
	 * 将优惠规则组转化成Model
	 * @param dctRuleGroup 优惠规则组
	 * @return
	 */
	public static DctRuleGroupModel convertToDctRuleGroup(DctRuleGroup dctRuleGroup){
		DctRuleGroupModel model = new DctRuleGroupModel();
		try {
			BeanUtils.copyProperties(dctRuleGroup, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("优惠规则组与规则引擎内部实体类转化失败",e);
		}
		List<DctRule> dctRules = dctRuleGroup.getDctRules();
		List<DctRuleModel> dctRuleModels = covertToDctRuleModel(dctRules);
		model.setDctRuleModels(dctRuleModels);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(dctRuleModels));
		return model;
	}
	
	/**
	 * 将优惠规则转化成Model
	 * @param dctRules 优惠规则列表
	 * @return
	 */
	public static List<DctRuleModel> covertToDctRuleModel(List<DctRule> dctRules ){
		List<DctRuleModel> dctRuleModels = null;
		try {
			dctRuleModels = BeanUtils.copyListProperties(Lists.newArrayList(dctRules), DctRuleModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("优惠规则与规则引擎内部VO类转化失败",e);
		}
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(dctRuleModels));
		return dctRuleModels;
	}
	
	/**
	 * 将收费规则转化成规则引擎中的规则
	 * @param chargeRule
	 * @return
	 */
	public static Rule convertToRuleByChargeRule(ChargeRule chargeRule) {
		Rule rule = null;
		int rentType  = chargeRule.getRentType();
		if(rentType==RuleEngineService.RENT_TYPE_TIME)
			rule = new TimeRuleModel();
		else if(rentType==RuleEngineService.RENT_TYPE_METER)
			rule = new MeterRuleModel();
		DetailRule detailRule = DetailRuleUtils.findDetailRuleByIdAndRentType(chargeRule);
		try {
			BeanUtils.copyProperties(detailRule, rule, "id","name");
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
				| InstantiationException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("收费规则与规则引擎内部实体类转化失败",e);
		}
		rule.setId(chargeRule.getId());
		rule.setName(chargeRule.getName());
		return rule;
	}
	
	
}
