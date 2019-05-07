package com.langmy.terminal.modules.psp.extend;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.modules.charge.model.ChargeRuleModel;
import com.langmy.terminal.modules.charge.service.extend.BaseRuleExtend;

public class ChargeExtend extends BaseRuleExtend{
	
	public static List<ChargeRuleModel> covertToChargeRuleModel(List<ChargeRule> chargeRule){
		List<ChargeRuleModel> chargeRuleModel = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(chargeRule)) {
			return chargeRuleModel;
		}
		try {
			chargeRuleModel = BeanUtils.copyPropertiesBylist(chargeRule, ChargeRuleModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			logger.error("收费模块-优惠规则管理-实体类与Model类数组转化失败");
		}
		return chargeRuleModel;
	}
	
}
