package com.langmy.terminal.common.utils;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

/**
 * Set 工具
 * @author lzy
 */
public class SetUtils {
	
	/**
	 * 把Set转化成List，删除set中一个对象，添加另一个对象
	 * @param sets
	 * @param removeObj 删除的对象
	 * @param addObj 新增的对象
	 * @return
	 */
	public static <T> List<T> replace(Set<T> sets,T removeObj,T addObj){
		List<T> anchorRents  = Lists.newArrayList(sets);
		if(!anchorRents.remove(removeObj)){
			return null;
		}
		if(!anchorRents.add(addObj)){
			return null;
		}
		return anchorRents;
	}
	
	/**
	 * 把Set转化成List
	 * @param sets
	 * @return
	 */
	public static <T> List<T> convertList(Set<T> sets){
		List<T> anchorRents=null;
		if(sets==null){
			return anchorRents;
		}
		anchorRents  = Lists.newArrayList(sets);
		return anchorRents;
	}
	
}
