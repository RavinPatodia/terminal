package com.langmy.terminal.modules.charge.service.extend;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.DctRule;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.DctRuleDao;
import com.langmy.terminal.modules.charge.model.DctRuleModel;

/**
 * 优惠规则Model和实体转化类
 * @author Administrator
 *
 */
public class DctRuleExtend extends BaseRuleExtend{
	
	private static DctRuleDao dctRuleDao = SpringContextHolder.getBean("dctRuleDao");
	
	
	public static List<DctRuleModel> covertToDctRuleModel(List<DctRule> dctRules){
		List<DctRuleModel> dctRuleModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(dctRules)) {
			return dctRuleModels;
		}
		try {
			dctRuleModels = BeanUtils.copyPropertiesBylist(dctRules, DctRuleModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			logger.error("收费模块-优惠规则管理-实体类与Model类数组转化失败");
		}
		return dctRuleModels;
	}
	
	public static DctRuleModel covertToModel(DctRule dctRule) {
		DctRuleModel model = new DctRuleModel();
		try {
			BeanUtils.copyProperties(dctRule, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类转化失败", e);
			return null;
		}
		return model;
	}
	
	public static DctRule covertToDctRule(DctRuleModel dctRuleModel){
		int id = dctRuleModel.getId();
		DctRule dctRule;
		if(id==0)
			dctRule  = new DctRule();
		else 
			dctRule = dctRuleDao.findOne(id);
		try {
			BeanUtils.copyProperties(dctRuleModel, dctRule);
		} catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
			logger.error("收费规则模块-优惠规则管理-新增收费规则-实体类属性copy失败");
		}
		return dctRule;
	}
	
}
