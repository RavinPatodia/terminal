package com.langmy.terminal.common.web;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;

/**
 * Select对象转化工具 
 * @author lzy
 *
 */
public class SelectExtend extends BaseExtend{
	
	public static <T> List<Select> convertToSelect(List<T> list,String idProperty,String textProperty){
		Map<String,String> maps = Maps.newHashMap();
		maps.put("sourcePro1", idProperty);
		maps.put("targetPro1", "id");
		maps.put("sourcePro2", textProperty);
		maps.put("targetPro2", "text");
		if(list==null||list.size()==0){
			return new ArrayList<Select>();
		}
		try {
			return BeanUtils.copyListProperties(list, Select.class, maps);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("List转化成Select失败");
			return new ArrayList<Select>();
		}
	}
	
	public static <T> List<Select2> convertToSelect2(List<T> list,String idProperty,String textProperty){
		Map<String,String> maps = Maps.newHashMap();
		maps.put("sourcePro1", idProperty);
		maps.put("targetPro1", "id");
		maps.put("sourcePro2", textProperty);
		maps.put("targetPro2", "text");
		if(list==null||list.size()==0){
			return new ArrayList<Select2>();
		}
		try {
			return BeanUtils.copyListProperties(list, Select2.class, maps);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("List转化成Select失败");
			return new ArrayList<Select2>();
		}
	}
	
}
