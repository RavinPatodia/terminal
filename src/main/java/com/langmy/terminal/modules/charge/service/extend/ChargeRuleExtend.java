package com.langmy.terminal.modules.charge.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.AnchorGroup;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.MeterCharge;
import com.langmy.terminal.common.entity.TimeCharge;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.persistence.DetailRule;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.ChargeRuleDao;
import com.langmy.terminal.modules.charge.model.ChargeRuleModel;
import com.langmy.terminal.modules.charge.model.TimeChargeModel;
import com.langmy.terminal.modules.charge.service.ChargeRuleService;
import com.langmy.terminal.modules.charge.service.DetailRuleFactory;

/**
 * 收费规则实体类和Model类之间的转化
 * @author lzy
 *
 */
public class ChargeRuleExtend extends BaseExtend{
	
	private static ChargeRuleDao chargeRuleDao = SpringContextHolder.getBean("chargeRuleDao");
	
	
	public static  ChargeRuleModel covertToModel(ChargeRule charge) {
		ChargeRuleModel model = new ChargeRuleModel();
		try {
			BeanUtils.copyProperties(charge, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类转化失败",e);
			return null;
		}
		int rentType = charge.getRentType();
		if(rentType!=ChargeRuleService.FREE){
			int baseRuleId = charge.getBaseRuleId();
			DetailRule detailRule = DetailRuleFactory.findDetailRuleByIdAndRentType(baseRuleId,rentType);
			if(rentType==ChargeRuleService.RENT_TYPE_ANCHOR){
				AnchorGroup anchorGroup = (AnchorGroup)detailRule;
				model.setBaseRuleName(anchorGroup.getName());
			}
			else if(rentType==ChargeRuleService.RENT_TYPE_TIME){
				TimeCharge timeCharge  = (TimeCharge)detailRule;
				TimeChargeModel timeChargeModel = TimeChargeExtend.covertToModel(timeCharge);
				model.setTimeChargeModel(timeChargeModel);
			}
			else if(rentType ==ChargeRuleService.RENT_TYPE_METER){
				MeterCharge meterCharge = (MeterCharge)detailRule;
				model.setMeterChargeModel(MeterChargeExtend.covertToModel(meterCharge));
			}
		}
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(model));
		return model;
	}
	
	public static ChargeRule covertToChargeRule(ChargeRuleModel model){
		int id = model.getId();
		ChargeRule chargeRule;
		if(id==0){
			 chargeRule = new ChargeRule();
		}
		else
			chargeRule = chargeRuleDao.findOne(id);
		try {
			BeanUtils.copyProperties(model, chargeRule);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类转化失败",e);
			return null;
		}
		return chargeRule;
	}
	
	public static List<ChargeRuleModel> covertToModel(List<ChargeRule> chargeRules){
		List<ChargeRuleModel> chargeRuleModels = Lists.newArrayList();
		if(ListUtils.isNullOrEmpty(chargeRules)){
			return Lists.newArrayList(chargeRuleModels);
		}
		try {
			chargeRuleModels = BeanUtils
					.copyListProperties(chargeRules, ChargeRuleModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类数组转化失败",e);
		}
		return Lists.newArrayList(chargeRuleModels);
	}
	
	
}
