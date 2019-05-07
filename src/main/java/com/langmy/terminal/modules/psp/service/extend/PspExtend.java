package com.langmy.terminal.modules.psp.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.AnchorGroup;
import com.langmy.terminal.common.entity.AnchorRent;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.persistence.DetailRule;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.model.AnchorRentModel;
import com.langmy.terminal.modules.charge.utils.DetailRuleUtils;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.model.PSpModel;
import com.langmy.terminal.modules.psp.model.RechargeRecModel;

public class PspExtend extends BaseExtend{
	
	private static PSpDao pspDao = SpringContextHolder.getBean(PSpDao.class);
	public static List<PSpModel> coverToModel(List<PSp> psps){
		List<PSpModel> pspModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(psps)) {
			return Lists.newArrayList(pspModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "area.name");
		map.put("targetPro1", "areaName");
		map.put("sourcePro2", "car.licensePlate");
		map.put("targetPro2", "licensePlate");
		try {
			pspModels = BeanUtils.copyListProperties(psps, PSpModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("车位管理-区域车位管理-实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(pspModels);
	}
	
	/**
	 * 根据车位ID获取收费规则给前台
	 * 
	 * @param pspId
	 * @param userId
	 * @return
	 */
	public static RechargeRecModel getAnchorRentsByPspId(String pspId) {
		RechargeRecModel model = new RechargeRecModel();
		ChargeRule chargeRule = getChargeRuleByPspId(pspId);
		if (chargeRule != null) {
			// 获取详细的收费规则
			DetailRule detailRule = DetailRuleUtils
					.findDetailRuleByIdAndRentType(chargeRule);
			if (detailRule == null) {
				logger.error("取到的规则为空");
				return null;
			}
			// 类型的转化
			AnchorGroup anchorGroup = (AnchorGroup) detailRule;
			List<AnchorRent> anchorRents = anchorGroup.getAnchorRents();
			List<AnchorRentModel> anchorRentModels = Lists.newArrayList();
			try {
				anchorRentModels = BeanUtils.copyListProperties(anchorRents,
						AnchorRentModel.class);
			} catch (IllegalAccessException | InvocationTargetException
					| InstantiationException | IllegalArgumentException
					| ClassNotFoundException | IntrospectionException
					| IOException e) {
				logger.error("收费规则-实体类转Model类失败");
				return null;
			}
			model.setAnchorRentModels(anchorRentModels);
			return model;
		} else {
			return null;
		}
	}

	/**
	 * 根据车位ID获取收费规则
	 * 
	 * @param pspId
	 * @param userId
	 * @return
	 */
	public static ChargeRule getChargeRuleByPspId(String pspId) {
		PSp pSp = pspDao.findByPspId(pspId);
		Park park = pSp.getPark();
		// 定义chargeRule用于接收传过来的最终可用的规则
		ChargeRule chargeRule = park.getChargeRule();
		return chargeRule;
	}

	public static PSpDao getAreaDao() {
		return pspDao;
	}

	public static void setPspDao(PSpDao pspDao) {
		PspExtend.pspDao = pspDao;
	}
}
