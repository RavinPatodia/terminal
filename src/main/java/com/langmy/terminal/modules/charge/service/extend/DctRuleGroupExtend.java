package com.langmy.terminal.modules.charge.service.extend;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.DctRule;
import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.DctRuleDao;
import com.langmy.terminal.modules.charge.dao.DctRuleGroupDao;
import com.langmy.terminal.modules.charge.model.DctRuleGroupModel;
import com.langmy.terminal.modules.charge.model.DctRuleModel;

public class DctRuleGroupExtend extends BaseRuleExtend {

	private static DctRuleDao dctRuleDao = SpringContextHolder.getBean("dctRuleDao");
	private static DctRuleGroupDao dctRuleGroupDao = SpringContextHolder.getBean("dctRuleGroupDao");

	/**	
	 * 将优惠规则组转化成前台显示的model
	 * 
	 * @param dctRuleGroup
	 *            优惠规则组
	 * @return
	 */
	public static DctRuleGroupModel covertToModel(DctRuleGroup dctRuleGroup) {
		DctRuleGroupModel model = new DctRuleGroupModel();
		try {
			BeanUtils.copyProperties(dctRuleGroup, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-优惠规则组-实体类与Model类转化失败", e);
			return model;
		}
		//获得优惠规则组中的优惠规则列表
		List<DctRuleModel> dctRuleModelsInGroup = DctRuleExtend
				.covertToDctRuleModel(dctRuleGroup.getDctRules());
		//获得不在优惠规则组中的优惠规则列表
		List<DctRuleModel> dctRuleModelsNotInGroup;
		if(ListUtils.isNullOrEmpty(dctRuleModelsInGroup)){  
			dctRuleModelsNotInGroup = DctRuleExtend.covertToDctRuleModel(dctRuleDao
					.findByDelFlagFalse());
		}
		else{
			List<Integer> dctRuleIds = getIdListByDctRuleModels(dctRuleModelsInGroup);
			dctRuleModelsNotInGroup = DctRuleExtend.covertToDctRuleModel(dctRuleDao
					.findByDelFlagFalseAndIdNotIn(dctRuleIds));
		}
		model.setDctRuleModelsInGroup(dctRuleModelsInGroup);
		model.setDctRuleModelsNotInGroup(dctRuleModelsNotInGroup);
		return model;
	}
	
	
	/**	
	 * 将优惠规则组Model转化成实体类
	 * 
	 * @param dctRuleGroup
	 *            优惠规则组
	 * @return
	 */
	public static DctRuleGroup covertToDctRuleGroup(DctRuleGroupModel model) {
		DctRuleGroup dctRuleGroup;
		int id = model.getId();
		if( id == 0)
			dctRuleGroup =  new DctRuleGroup();
		else
			dctRuleGroup = dctRuleGroupDao.findOne(id);
		try {
			BeanUtils.copyProperties(model, dctRuleGroup);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费模块-收费规则管理-优惠规则组-实体类与Model类转化失败", e);
			return null;
		}
		List<Integer> dctRuleIds = model.getDctRules();
		List<DctRule> dctRules = dctRuleDao.findByIdIn(dctRuleIds);
		dctRuleGroup.setDctRules(dctRules);
		return dctRuleGroup;
	}
	
	public static List<DctRuleGroupModel> covertToDctRuleModel(List<DctRuleGroup> dctRuleGroups){
		List<DctRuleGroupModel> dctRuleGroupModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(dctRuleGroups)) 
			return dctRuleGroupModels;
		try {
			dctRuleGroupModels = BeanUtils.copyPropertiesBylist(dctRuleGroups,
					DctRuleGroupModel.class);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			logger.error("收费模块-优惠规则组管理-实体类与Model类数组转化失败");
		}
		return dctRuleGroupModels;
	}

	/**
	 * 从优惠规则列表中得到Id列表
	 * @param dctRuleModels 优惠规则Model列表
	 * @return
	 */
	private static List<Integer> getIdListByDctRuleModels(List<DctRuleModel> dctRuleModels) {
		List<Integer> ids = Lists.newArrayList();
		for (DctRuleModel model : dctRuleModels) {
			Integer id = model.getId();
			ids.add(id);
		}
		return ids;
	}

}
