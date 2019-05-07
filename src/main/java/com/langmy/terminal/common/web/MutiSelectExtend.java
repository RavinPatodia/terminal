package com.langmy.terminal.common.web;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;

/**
 * 多选插件 JQuery Muti-select使用的转化
 * @author lzy
 * 2015年5月31日
 */
public class MutiSelectExtend extends BaseExtend{
	
	public static <T> List<Select> convertToSelect(List<T> list,String idProperty,String textProperty,Boolean if_select){
		List<Select> selects = Lists.newArrayList();
		Map<String,String> maps = Maps.newHashMap();
		maps.put("sourcePro1", idProperty);
		maps.put("targetPro1", "id");
		maps.put("sourcePro2", textProperty);
		maps.put("targetPro2", "text");
		if(list==null||list.size()==0){
			return new ArrayList<Select>();
		}
		try {
			selects =  BeanUtils.copyListProperties(list, Select.class, maps);
			BeanUtils.copyProperties(selects, "if_select", if_select);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("List转化成Select失败");
			return new ArrayList<Select>();
		}
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(selects));
		return selects;
	}
	
}
