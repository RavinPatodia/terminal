package com.langmy.terminal.modules.charge.service.extend;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.AnchorRent;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.AnchorRentDao;
import com.langmy.terminal.modules.charge.model.AnchorRentModel;

/**
 * 长期收费规则Model和实体转化类
 * 
 * @author lzy
 *
 */
public class AnchorRentExtend extends BaseRuleExtend {

	private static AnchorRentDao anchorRentDao = SpringContextHolder.getBean("anchorRentDao");

	public static List<AnchorRentModel> covertToModel(List<AnchorRent> anchorRents) {
		List<AnchorRentModel> anchorRentModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(anchorRents)) {
			return Lists.newArrayList(anchorRentModels);
		}
		try {
			anchorRentModels = BeanUtils.copyPropertiesBylist(anchorRents, AnchorRentModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			logger.error("收费模块-长期收费管理-实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(anchorRentModels);
	}

	public static AnchorRentModel covertToModel(AnchorRent charge) {
		AnchorRentModel model = new AnchorRentModel();
		try {
			BeanUtils.copyProperties(charge, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类转化失败", e);
			return null;
		}
		return model;
	}

	public static AnchorRent convertToAnchorRent(AnchorRentModel model) {
		int id = model.getId();
		AnchorRent anchorRent;
		if (id == 0)
			anchorRent = new AnchorRent();
		else{
			anchorRent = anchorRentDao.findOne(id);
			if(logger.isDebugEnabled())
				logger.debug(JSONObject.toJSONString(anchorRent));
		}
		try {
			BeanUtils.copyProperties(model, anchorRent);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			logger.error("长期收费规则bean转化失败", e);
		}
		return anchorRent;
	}

}
