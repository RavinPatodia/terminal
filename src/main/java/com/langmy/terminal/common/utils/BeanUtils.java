package com.langmy.terminal.common.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * 用来封装Bean之间拷贝和赋值的方法，主要用了org.apache.commons.beanutils.BeanUtils.copyProperties(
 * ) 以及反射和泛型
 * 
 * @author lzy
 */
public class BeanUtils {

	private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/** bean嵌套 */
	private static final String NESTED = ".";

	public static void copyProperties(Object source, Object target) throws IllegalAccessException,
			InvocationTargetException {
		org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
	}

	public static void copyProperties(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		org.apache.commons.beanutils.BeanUtils.copyProperty(bean, name, value);
	}

	/**
	 * @param source 源对象
	 * @param target 目标对象
	 * @param propertyMap 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 * @throws IOException
	 */
	public static void copyProperties(Object source, Object target,
			final Map<String, String> propertyMap) throws IllegalAccessException,
			InvocationTargetException, InstantiationException, IllegalArgumentException,
			ClassNotFoundException, IntrospectionException, IOException {
		BeanUtils.copyProperties(source, target);
		copyMapProperties(propertyMap, source, target);
		copyMapObjProperties(propertyMap, source, target);
	}

	/**
	 * 复制集合
	 * 
	 * @param <E>
	 * @param source
	 * @param destinationClass
	 * @return
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static <E> List<E> copyPropertiesBylist(List<?> source, Class<E> destinationClass)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if (source.size() == 0)
			throw new IllegalArgumentException("要拷贝的源对象为空");
		List<E> res = Lists.newArrayList();
		for (Object o : source) {
			E e = destinationClass.newInstance();
			BeanUtils.copyProperties(o, e);
			res.add(e);
		}
		return res;
	}

	/**
	 * 复制集合
	 * 
	 * @param <E>
	 * @param source
	 * @param destinationClass
	 * @return
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws IntrospectionException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 */
	public static <E> List<E> copyListProperties(List<?> source, Class<E> destinationClass,
			String... excludeProperties) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, IllegalArgumentException, ClassNotFoundException,
			IntrospectionException, IOException {
		if (source == null)
			throw new IllegalArgumentException("要拷贝的源对象为空");
		List<E> res = Lists.newArrayList();
		for (Object o : source) {
			E e = destinationClass.newInstance();
			BeanUtils.copyProperties(o, e, excludeProperties);
			res.add(e);
		}
		return res;
	}

	public static <E> void copyProperties(List<E> bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		for (E e : bean) {
			org.apache.commons.beanutils.BeanUtils.copyProperty(e, name, value);
		}
	}

	/**
	 * 复制集合,附加一个额外对于属性SourcePro、targetPro
	 * 
	 * @param <E>
	 * @param source
	 * @param destinationClass
	 * @param SourcePro
	 *            实体类中需要赋值到Model里面的属性。可以是实体类.属性。比如student里面的Class班级
	 *            class1,那么就是class1.name
	 * @param targetPro
	 *            传值类中需要被赋值的属性
	 * @return
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static <E> List<E> copyListPropertiesByParam(List<?> source, Class<E> destinationClass,
			String SourcePro, String targetPro) throws InstantiationException,
			IllegalAccessException, InvocationTargetException {
		if (source == null || source.size() == 0)
			throw new IllegalArgumentException("要拷贝的源对象为空");
		List<E> res = Lists.newArrayList();
		for (Object o : source) {
			E e = destinationClass.newInstance();
			BeanUtils.copyProperties(o, e);
			Object value = Reflections.invokeGetter(o, SourcePro);
			Reflections.invokeSetter(e, targetPro, value);
			res.add(e);
		}
		return res;
	}

	/**
	 * 复制集合
	 * 
	 * @param <E>
	 * @param source
	 * @param destinationClass
	 * @param SourcePro
	 *            实体类中需要赋值到Model里面的属性。可以是实体类.属性。比如student里面的Class班级
	 *            class1,那么就是class1.name
	 * @param targetPro
	 *            传值类中需要被赋值的属性
	 * @return
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws IntrospectionException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 */
	public static <E> List<E> copyListProperties(final List<?> source, Class<E> destinationClass,
			final Map<String, String> propertyMap) throws IllegalAccessException,
			InvocationTargetException, InstantiationException, IllegalArgumentException,
			ClassNotFoundException, IntrospectionException, IOException {
		if (source == null || source.size() == 0)
			throw new IllegalArgumentException("要拷贝的源对象为空");
		List<E> res = Lists.newArrayList();
		for (Object o : source) {
			E e = destinationClass.newInstance();
			BeanUtils.copyProperties(o, e);
			copyMapProperties(propertyMap, o, e);
			copyMapObjProperties(propertyMap, o, e);
			res.add(e);
		}
		return res;
	}

	/**
	 * 把源对象中的属性根据map中传入的源对象中属性，以及目标对象属性。通过反射的形式赋值给目标对象.
	 * 
	 * @param propertyMap
	 * @param source
	 * @param target
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 * @throws IOException
	 */
	private static <E> void copyMapObjProperties(final Map<String, String> propertyMap,
			Object source, E target) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, IllegalArgumentException, ClassNotFoundException,
			IntrospectionException, IOException {
		int i = 1;
		while (propertyMap.get("modelSourcePro" + i) != null) {
			String sourcePro = propertyMap.get("modelSourcePro" + i);
			String targetPro = propertyMap.get("modelTargetPro" + i);
			i++;
			copyProperty(source, target, sourcePro, targetPro);
		}
	}

	private static void copyProperty(Object source, Object target, String sourcePro,
			String targetPro) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, IllegalArgumentException, ClassNotFoundException,
			IntrospectionException, IOException {
		if (source == null) {
			throw new IllegalArgumentException("要拷贝的源对象为空");
		}
		Object sourceObj = Reflections.invokeGetter(source, sourcePro);
		Object targetValue = null;
		if (sourceObj == null) {
			logger.error("要拷贝的对象为空"+sourcePro);
			return;
		}
		Field field = Reflections.getPropertyFieldByName(target.getClass(), targetPro);
		Class<?> filedClass = field.getType();
		if (filedClass.isAssignableFrom(List.class)) {
			Type t = field.getGenericType();
			if (t == null) {
				throw new IllegalArgumentException("要拷贝的目标属List未加泛型约束");
			}
			if (t instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType) t;
				Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0]; // 得到泛型里的class类型对象。
				targetValue = BeanUtils.copyListProperties((List<?>) sourceObj, genericClazz);
			}
		} else {
			targetValue = filedClass.newInstance();
			BeanUtils.copyProperties(sourceObj, targetValue);
		}
		Reflections.invokeSetter(target, targetPro, targetValue);
	}

	/**
	 * 把源对象中的属性根据map中传入的源对象中属性，以及目标对象属性。通过反射的形式赋值给目标对象.
	 * 这个方法主要是基础属性以及相应的衍生属性，不包括list以及Bean类型
	 * 
	 * @param propertyMap
	 * @param source
	 * @param target
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 * @throws IOException
	 */
	private static void copyMapProperties(final Map<String, String> propertyMap, Object source,
			Object target) {
		int i = 1;
		while (propertyMap.get("sourcePro" + i) != null) {
			String sourcePro = propertyMap.get("sourcePro" + i);
			String targetPro = propertyMap.get("targetPro" + i);
			Object value = Reflections.invokeGetter(source, sourcePro);
			if (value != null) {
				Reflections.invokeSetter(target, targetPro, value);
			}
			i++;
		}
	}

	/**
	 * 复制bean的属性（支持嵌套属性，以点号分割）
	 * 
	 * @param source
	 *            拷贝属性的源对象
	 * 
	 * @param dest
	 *            拷贝属性的目的地对象
	 * 
	 * @param includeProperties
	 *            拷贝的属性列表
	 * 
	 * @author yang_qiao
	 * 
	 * @throws InvocationTargetException
	 * 
	 * @throws IllegalAccessException
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @throws InstantiationException
	 * 
	 * @throws IntrospectionException
	 * 
	 * @date 2013-12-18
	 */
	public static final void copyIncludeProperties(final Object source, Object dest,
			final String[] includeProperties) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, InstantiationException,
			IntrospectionException {
		if (includeProperties == null || includeProperties.length == 0) {
			throw new IllegalArgumentException("未传入要拷贝的属性列表");
		}
		if (source == null) {
			throw new IllegalArgumentException("要拷贝的源对象为空");
		}
		if (dest == null) {
			throw new IllegalArgumentException("要拷贝的目的对象为空");
		}
		// 日志信息
		if (logger.isTraceEnabled()) {
			logger.trace("[source bean: " + source.getClass().getName() + " ]");
			logger.trace("[destination bean: " + dest.getClass().getName() + " ]");
		}
		// 拷贝
		for (String property : includeProperties) {
			PropertyDescriptor sourcePropertyDescriptor = null;
			PropertyDescriptor destPropertyDescriptor = null;
			if (isSimpleProperty(property)) { // 简单属性
				sourcePropertyDescriptor = getProperty(property, source);
				destPropertyDescriptor = getProperty(property, dest);
				if (sourcePropertyDescriptor == null) {
					throw new IllegalArgumentException("要拷贝的源对象不存在该属性");
				}
				if (destPropertyDescriptor == null) {
					throw new IllegalArgumentException("要拷贝到的目标对象不存在该属性");
				}
				copyProperty(property, source, dest);
			} else { // 嵌套bean属性
				Object target = dest;
				Object realSource = source;
				String[] nestedProperty = getNestedProperty(property);
				if (nestedProperty != null && nestedProperty.length > 1) {
					for (int i = 0; i < nestedProperty.length - 1; i++) {
						sourcePropertyDescriptor = getProperty(nestedProperty[i], realSource);
						destPropertyDescriptor = getProperty(nestedProperty[i], target);
						if (sourcePropertyDescriptor == null) {
							throw new IllegalArgumentException("要拷贝的源对象不存在该属性");
						}
						if (destPropertyDescriptor == null) {
							throw new IllegalArgumentException("要拷贝到的目标对象不存在该属性");
						}
						Method readMethod = sourcePropertyDescriptor.getReadMethod();
						realSource = readMethod.invoke(realSource);
						readMethod = destPropertyDescriptor.getReadMethod();
						Method writeMethod = destPropertyDescriptor.getWriteMethod();
						Object value = readMethod.invoke(target);
						if (value == null) {
							value = destPropertyDescriptor.getPropertyType().newInstance();
							writeMethod.invoke(target, value);
						}
						target = value;
					}
					final String prop = nestedProperty[nestedProperty.length - 1];
					sourcePropertyDescriptor = getProperty(prop, realSource);
					destPropertyDescriptor = getProperty(prop, target);
					if (sourcePropertyDescriptor == null) {
						throw new IllegalArgumentException("要拷贝的源对象不存在该属性");
					}
					if (destPropertyDescriptor == null) {
						throw new IllegalArgumentException("要拷贝到的目标对象不存在该属性");
					}
					copyProperty(prop, realSource, target);
				}
			}
		}
	}

	/**
	 * 复制bean的属性（支持嵌套属性，以点号分割）
	 * 
	 * @param source
	 *            拷贝属性的源对象
	 * 
	 * @param dest
	 *            拷贝属性的目的地对象
	 * 
	 * @param includeProperties
	 *            拷贝的属性列表
	 * 
	 * @author yang_qiao
	 * 
	 * @throws IntrospectionException
	 * 
	 * @throws InvocationTargetException
	 * 
	 * @throws IllegalAccessException
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @throws InstantiationException
	 * 
	 * @throws ClassNotFoundException
	 * 
	 * @throws IOException
	 * 
	 * @date 2013-12-18
	 */
	public static void copyProperties(final Object source, final Object dest,
			final List<String> excludeProperties) throws IntrospectionException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException, IOException, ClassNotFoundException {
		final Object backupSource = clone(dest);
		if (source == null) {
			throw new IllegalArgumentException("要拷贝的源对象为空");
		}
		if (dest == null) {
			throw new IllegalArgumentException("要拷贝的目的对象为空");
		}
		org.apache.commons.beanutils.BeanUtils.copyProperties(dest, source);
		// 还原排除的属性值
		revertProperties(backupSource, dest, excludeProperties.toArray(new String[] {}));
	}

	public static void copyProperties(final Object source, final Object dest,
			final String... excludeProperties) throws IntrospectionException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			InstantiationException, IOException, ClassNotFoundException {
		final Object backupSource = clone(dest);
		if (source == null) {
			throw new IllegalArgumentException("要拷贝的源对象为空");
		}
		if (dest == null) {
			throw new IllegalArgumentException("要拷贝的目的对象为空");
		}
		org.apache.commons.beanutils.BeanUtils.copyProperties(dest, source);
		// 还原排除的属性值
		revertProperties(backupSource, dest, excludeProperties);
	}

	/**
	 * 从备份对象中还原属性
	 * 
	 * @param backup
	 *            备份bean
	 * 
	 * @param target
	 *            目标bean
	 * 
	 * @param properties
	 *            属性列表
	 * 
	 * @author yang_qiao
	 * 
	 * @throws InvocationTargetException
	 * 
	 * @throws IllegalAccessException
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @throws IntrospectionException
	 * 
	 * @throws InstantiationException
	 * 
	 * @date 2013-12-18
	 */
	private static void revertProperties(final Object backup, Object target,
			final String... properties) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, IntrospectionException, InstantiationException {
		if (properties == null || properties.length == 0) {
			return;
		}
		if (backup == null) {
			throw new IllegalArgumentException("备份对象为空");
		}
		if (target == null) {
			throw new IllegalArgumentException("目的对象为空");
		}
		// 日志信息
		if (logger.isTraceEnabled()) {
			logger.trace("[source bean: " + backup.getClass().getName() + " ]");
			logger.trace("[destination bean: " + target.getClass().getName() + " ]");
		}
		// 拷贝
		for (String property : properties) {
			PropertyDescriptor sourcePropertyDescriptor = null;
			PropertyDescriptor destPropertyDescriptor = null;
			if (isSimpleProperty(property)) { // 简单属性
				sourcePropertyDescriptor = getProperty(property, backup);
				destPropertyDescriptor = getProperty(property, target);
				if (sourcePropertyDescriptor == null) {
					throw new IllegalArgumentException("要拷贝的源对象不存在该属性");
				}
				if (destPropertyDescriptor == null) {
					throw new IllegalArgumentException("要拷贝到的目标对象不存在该属性");
				}
				copyProperty(property, backup, target);
			} else { // 嵌套bean属性
				Object targetObj = target;
				Object realBackup = backup;
				String[] nestedProperty = getNestedProperty(property);
				if (nestedProperty != null && nestedProperty.length > 1) {
					for (int i = 0; i < nestedProperty.length - 1; i++) {
						sourcePropertyDescriptor = getProperty(nestedProperty[i], realBackup);
						destPropertyDescriptor = getProperty(nestedProperty[i], targetObj);
						if (sourcePropertyDescriptor == null) {
							throw new IllegalArgumentException("要拷贝的源对象不存在该属性");
						}
						if (destPropertyDescriptor == null) {
							throw new IllegalArgumentException("要拷贝到的目标对象不存在该属性");
						}
						Method readMethod = sourcePropertyDescriptor.getReadMethod();
						realBackup = readMethod.invoke(realBackup);
						if (realBackup == null) { // 判断备份对象嵌套属性是否为空
							realBackup = sourcePropertyDescriptor.getPropertyType().newInstance();
						}
						Method writeMethod = destPropertyDescriptor.getWriteMethod();
						readMethod = destPropertyDescriptor.getReadMethod();
						Object value = readMethod.invoke(targetObj);
						if (value == null) {
							value = destPropertyDescriptor.getPropertyType().newInstance();
							writeMethod.invoke(targetObj, value);
						}
						targetObj = value;
					}
					final String prop = nestedProperty[nestedProperty.length - 1];
					sourcePropertyDescriptor = getProperty(prop, realBackup);
					destPropertyDescriptor = getProperty(prop, targetObj);
					if (sourcePropertyDescriptor == null) {
						throw new IllegalArgumentException("要拷贝的源对象不存在该属性");
					}
					if (destPropertyDescriptor == null) {
						throw new IllegalArgumentException("要拷贝到的目标对象不存在该属性");
					}
					copyProperty(prop, realBackup, targetObj);
				}
			}
		}
	}

	/**
	 * 对象克隆
	 * 
	 * @author yang_qiao
	 * 
	 * @date 2013-12-18
	 */
	private static Object clone(final Object value) throws IOException, ClassNotFoundException {
		// 字节数组输出流，暂存到内存中
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// 序列化
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(value);
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		// 反序列化
		return ois.readObject();
	}

	/**
	 * 判断是否为简单属性，是，返回ture
	 * 
	 * @author yang_qiao
	 * 
	 * @date 2013-12-18
	 */
	private static boolean isSimpleProperty(final String property) {
		return !property.contains(NESTED);
	}

	/**
	 * 获取目标bean的属性
	 * 
	 * @author yang_qiao
	 * 
	 * @date 2013-12-17
	 */
	private static PropertyDescriptor getProperty(final String propertyName, final Object target) {
		if (target == null) {
			new IllegalArgumentException("查询属性的对象为空");
		}
		if (propertyName == null || "".equals(propertyName)) {
			new IllegalArgumentException("查询属性不能为空值");
		}
		PropertyDescriptor propertyDescriptor = null;
		try {
			propertyDescriptor = new PropertyDescriptor(propertyName, target.getClass());
		} catch (IntrospectionException e) {
			logger.info("不存在该属性");
			return null;
		}
		return propertyDescriptor;
	}

	/**
	 * 单个属性复制--原数据源和目的数据源必须要有该属性方可
	 * 
	 * @author yang_qiao
	 * 
	 * @throws InvocationTargetException
	 * 
	 * @throws IllegalAccessException
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @throws IntrospectionException
	 * 
	 * @date 2013-12-17
	 */
	public static void copyProperty(final String propertyName, final Object source, Object dest)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			IntrospectionException {
		PropertyDescriptor property;
		property = new PropertyDescriptor(propertyName, source.getClass());
		Method getMethod = property.getReadMethod();
		Object value = getMethod.invoke(source);
		property = new PropertyDescriptor(propertyName, dest.getClass());
		Method setMethod = property.getWriteMethod();
		setMethod.invoke(dest, value);
	}

	/**
	 * 获取嵌套Bean的属性
	 * 
	 * @author yang_qiao
	 * 
	 * @date 2013-12-18
	 */
	public static String[] getNestedProperty(final String nestedProperty) {
		if (nestedProperty == null || "".equals(nestedProperty)) {
			new IllegalArgumentException("参数为空值");
		}
		return nestedProperty.split("\\" + NESTED);
	}

}
