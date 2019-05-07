package com.langmy.terminal.modules.charge.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.AnchorGroup;
import com.langmy.terminal.common.entity.AnchorRent;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.AnchorGroupDao;
import com.langmy.terminal.modules.charge.dao.AnchorRentDao;
import com.langmy.terminal.modules.charge.model.AnchorGroupModel;
import com.langmy.terminal.modules.charge.model.AnchorRentModel;


/**
 * 长期收费组规则Model和实体转化类
 * 
 * @author lzy
 *
 */
public class AnchorGroupExtend extends BaseRuleExtend {

	private static AnchorRentDao anchorRentDao = SpringContextHolder.getBean("anchorRentDao");
	private static AnchorGroupDao anchorGroupDao = SpringContextHolder.getBean("anchorGroupDao");

	public static List<AnchorGroupModel> coverToModel(List<AnchorGroup> anchorGroups) {
		List<AnchorGroupModel> anchorGroupModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(anchorGroups)) {
			return Lists.newArrayList(anchorGroupModels);
		}
		try {
			anchorGroupModels = BeanUtils.copyListProperties(anchorGroups, AnchorGroupModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("收费模块-长期规则组管理-实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(anchorGroupModels);
	}

	public static AnchorGroupModel covertToModel(AnchorGroup anchorGroup) {
		AnchorGroupModel model = new AnchorGroupModel();
		try {
			BeanUtils.copyProperties(anchorGroup, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-实体类与Model类转化失败", e);
			return null;
		}
		// 获得长期收费规则组中的长期收费规则
		List<AnchorRentModel> anchorRentModelsInGroup = AnchorRentExtend.covertToModel(anchorGroup
				.getAnchorRents());
		// 获得不在组内的长期收费规则
		List<AnchorRentModel> anchorRentModelsNotInGroup;
		if (ListUtils.isNullOrEmpty(anchorRentModelsInGroup))
			anchorRentModelsNotInGroup = AnchorRentExtend.covertToModel(anchorRentDao
					.findByDelFlagFalse());
		else {
			List<Integer> dctRuleIds = getIdListByAchorRentModels(anchorRentModelsInGroup);
			anchorRentModelsNotInGroup = AnchorRentExtend.covertToModel(anchorRentDao
					.findByDelFlagFalseAndIdNotIn(dctRuleIds));
		}
		model.setAnchorRentModelsInGroup(anchorRentModelsInGroup);
		model.setAnchorRentModelsNotInGroup(anchorRentModelsNotInGroup);
		return model;
	}

	/**
	 * 从长期收费规则列表中得到Id列表
	 * 
	 * @param anchorRentsInGroup
	 *            长期收费规则Model
	 * @return
	 */
	public static List<Integer> getIdListByAchorRentModels(List<AnchorRentModel> anchorRentsInGroup) {
		List<Integer> ids = Lists.newArrayList();
		for (AnchorRentModel model : anchorRentsInGroup) {
			Integer id = model.getId();
			ids.add(id);
		}
		return ids;
	}

	/**
	 * 长期收费规则组Model转化成实体
	 * 
	 * @param model
	 *            长期收费规则组Model
	 * @return
	 */
	public static AnchorGroup covertToAnchorGroup(AnchorGroupModel model) {
		int id = model.getId();
		AnchorGroup anchorGroup;
		if(id==0)
			anchorGroup = new AnchorGroup();
		else
			anchorGroup = anchorGroupDao.findOne(id);
		try {
			BeanUtils.copyProperties(model, anchorGroup);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			logger.error("长期规则组实体类与model类转化失败", e);
			return null;
		}
		List<Integer> anchorRents = model.getAnchorRents();
		bindAnchorRents(anchorGroup, anchorRents);
		logger.debug(anchorGroup.toString());
		return anchorGroup;
	}

	/**
	 * 长期收费规则组绑定长期收费规则外键
	 * 
	 * @param anchorGroup
	 *            长期收费规则组
	 * @param anchorRentPKs
	 *            长期收费规则Id的数组
	 */
	public static void bindAnchorRents(AnchorGroup anchorGroup, List<Integer> anchorRents) {
		if (ListUtils.isNullOrEmpty(anchorRents)) {
			return;
		}
		List<AnchorRent> GAnchorRents = anchorRentDao.findByIdIn(anchorRents);
		anchorGroup.setAnchorRents(GAnchorRents);
	}

}
