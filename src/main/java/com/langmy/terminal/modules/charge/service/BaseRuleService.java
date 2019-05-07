package com.langmy.terminal.modules.charge.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import com.langmy.terminal.common.persistence.IdEntity;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.modules.charge.dao.BaseRuleDao;

/**
 * 基础的规则service
 * @author lzy
 *
 * @param <T>规则对应相应实体
 */
public abstract class BaseRuleService<T extends IdEntity> extends BaseService<T> {

	BaseRuleDao<T> baseRuleDao;
	List<String> ignoreProperties = Arrays.asList("id", "createTime");
	
	
	public BaseRuleService(BaseRuleDao<T> baseRuleDao){
		super(baseRuleDao);
		this.baseRuleDao = baseRuleDao;
	}
	
	
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
	protected void copyRuleProperties(final Object source, final Object dest,
			final String... excludeProperties) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, InstantiationException,
			ClassNotFoundException, IntrospectionException, IOException {
		for (String property : excludeProperties) {
			ignoreProperties.add(property);
		}
		BeanUtils.copyProperties(source, dest, ignoreProperties);
	}
}
	
