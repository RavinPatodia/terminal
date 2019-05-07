package com.langmy.terminal.modules.charge.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;


/**
 * 收费规则模块 Model和实体转化基类
 * @author lzy
 *
 */
public class BaseRuleExtend extends BaseExtend{
	
	/**
	 * 有禁用启用规则不需要拷贝的属性
	 */
	static List<String> ignoreRuleProperties = Arrays.asList("id", "createTime", "lastEnable", "lastDisable");
	/**
	 * 无禁用弃用规则不需要要拷贝的属性
	 */
	static List<String> ignoreProperties = Arrays.asList("id", "createTime");
	
	/**
	 * 拷贝方法，去除excludeProperties中的属性
	 * @param source 源对象
	 * @param dest 目标对象
	 * @param excludeProperties 不包含的属性
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 * @throws IOException
	 */
	protected static void copyRuleProperties(final Object source, final Object dest,
			final String... excludeProperties) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, InstantiationException,
			ClassNotFoundException, IntrospectionException, IOException {
		for (String property : excludeProperties) {
			ignoreProperties.add(property);
		}
		BeanUtils.copyProperties(source, dest, ignoreRuleProperties);
	}
	
	protected static void copyProperties(final Object source, final Object dest,
			final String... excludeProperties) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, InstantiationException,
			ClassNotFoundException, IntrospectionException, IOException {
		for (String property : excludeProperties) {
			ignoreProperties.add(property);
		}
		BeanUtils.copyProperties(source, dest, ignoreProperties);
	}
	
}
