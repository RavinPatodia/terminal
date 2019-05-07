package com.langmy.terminal.modules.device.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Area;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.model.AreaModel;
import com.langmy.terminal.modules.psp.dao.AreaDao;

/**
 * 区域model和实体类之间的转换
 * @author LuZixiang
 *
 */
public class AreaExtend extends BaseExtend{
	private static AreaDao areaDao = SpringContextHolder.getBean("areaDao");
	
	/**
	 * Area实体类到model的转换
	 * @param areas
	 * @return
	 */
	public static List<AreaModel> coverToModel(List<Area> areas){
		List<AreaModel> areaModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(areas)) {
			return Lists.newArrayList(areaModels);
		}
		try {
			areaModels = BeanUtils.copyListProperties(areas, AreaModel.class
					);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("硬件管理-显示屏区域管理-实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(areaModels);
	}

	public static AreaDao getAreaDao() {
		return areaDao;
	}

	public static void setAreaDao(AreaDao areaDao) {
		AreaExtend.areaDao = areaDao;
	}
}
