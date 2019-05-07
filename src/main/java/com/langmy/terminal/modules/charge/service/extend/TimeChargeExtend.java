package com.langmy.terminal.modules.charge.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.TimeCharge;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.modules.charge.model.TimeChargeModel;

/**
 * 计时收费规则Model和实体转化类
 * @author lzy
 *
 */
public class TimeChargeExtend extends BaseRuleExtend{
	
	
	public static TimeChargeModel covertToModel(TimeCharge timeCharge) {
		TimeChargeModel model = new TimeChargeModel();
		try {
			BeanUtils.copyProperties(timeCharge, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类转化失败",e);
			return null;
		}
		return model;
	}
	
	public static TimeCharge convertToTimeCharge(TimeChargeModel model){
		TimeCharge timeCharge =  new TimeCharge();
		try {
			BeanUtils.copyProperties(model, timeCharge);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			logger.error("长期规则组实体类与model类转化失败",e);
			return null;
		}
		return timeCharge;
	}
	
	public static List<TimeChargeModel> covertToModel(List<TimeCharge> timeCharges) {
		List<TimeChargeModel> results = Lists.newArrayList();
		if (timeCharges == null || timeCharges.size() == 0) {
			return Lists.newArrayList(results);
		}

		try {
			results = BeanUtils.copyListProperties(timeCharges, TimeChargeModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("收费模块-计时收费管理-实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(results);
	}
	
}
