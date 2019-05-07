package com.langmy.terminal.modules.charge.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.MeterCharge;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.modules.charge.model.MeterChargeModel;

/**
 * 计次收费规则Model和实体转化类
 * @author lzy
 */
public class MeterChargeExtend extends BaseRuleExtend{
	
	public static MeterChargeModel covertToModel(MeterCharge meterCharge) {
		MeterChargeModel model = new MeterChargeModel();
		try {
			BeanUtils.copyProperties(meterCharge, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类转化失败",e);
			return null;
		}
		return model;
	}
	
	public static MeterCharge covertToMeterCharge(MeterChargeModel model) {
		MeterCharge meterCharge = new MeterCharge();
		try {
			BeanUtils.copyProperties(model, meterCharge);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException  e) {
			logger.error("计次规则实体类与model类转化失败", e);
			return null;
		}
		return meterCharge;
	}
	
	public static List<MeterChargeModel> covertToModel(List<MeterCharge> meterCharges) {
		List<MeterChargeModel> meterChargeModels = Lists.newArrayList();
		if(ListUtils.isNullOrEmpty(meterCharges)){
			return Lists.newArrayList(meterChargeModels);
		}

		try {
			meterChargeModels = BeanUtils.copyListProperties(meterCharges, MeterChargeModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("收费模块-计次收费管理-实体类与Model类数组转化失败");
		}
		return Lists.newArrayList(meterChargeModels);
	}
	
	
	
	
	
}
