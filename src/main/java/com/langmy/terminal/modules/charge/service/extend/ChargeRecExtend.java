package com.langmy.terminal.modules.charge.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.ChargeRec;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.modules.charge.model.ChargeRecModel;

/**
 * 收费记录Model和实体转化类
 * @author lzy
 *
 */
public class ChargeRecExtend extends BaseRuleExtend {

	public static ChargeRecModel covertToModel(ChargeRec chargeRec) {
		ChargeRecModel model = new ChargeRecModel();

		Map<String, String> map = Maps.newHashMap();
		map.put("modelSourcePro1", "effectChargeRules");
		map.put("modelTargetPro1", "chargeRuleModels");
		map.put("modelSourcePro1", "dctRuleGroup");
		map.put("modelTargetPro1", "dctRuleGroupModel");
		map.put("modelSourcePro2", "adminssionRec.car");
		map.put("modelTargetPro2", "carModel");
		map.put("sourcePro1", "operater.id");
		map.put("targetPro1", "operPK");
		map.put("sourcePro2", "operater.operName");
		map.put("targetPro2", "operName");

		try {
			BeanUtils.copyProperties(chargeRec, model, map);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类转化失败", e);
			return null;
		}
		return model;
	}
	
	public static List<ChargeRecModel> covertToModel(List<ChargeRec> chargeRecs){
		List<ChargeRecModel> chargeRecModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(chargeRecs)) {
			return Lists.newArrayList(chargeRecModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("modelSourcePro1", "dctRuleGroup");
		map.put("modelTargetPro1", "dctRuleGroupModel");
		map.put("modelSourcePro2", "adminssionRec.car");
		map.put("modelTargetPro2", "carModel");
		map.put("sourcePro1", "operater.id");
		map.put("targetPro1", "operPK");
		map.put("sourcePro2", "operater.operName");
		map.put("targetPro2", "operName");

		try {
			chargeRecModels = BeanUtils.copyListProperties(chargeRecs, ChargeRecModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("收费模块-收费记录管理-实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(chargeRecModels);
	}

}
