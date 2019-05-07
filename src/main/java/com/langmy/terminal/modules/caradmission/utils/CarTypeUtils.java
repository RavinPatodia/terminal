package com.langmy.terminal.modules.caradmission.utils;

import com.langmy.terminal.common.entity.CarType;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.caradmission.dao.CarTypeDao;

/**
 * 车辆类型Util
 * 
 * @author ZZD
 *
 */
public class CarTypeUtils {
	private static CarTypeDao carTypeDao = SpringContextHolder
			.getBean(CarTypeDao.class);

	public static CarType getByLicensePlateType(String licensePlateType) {
		return carTypeDao.findByTypeName(licensePlateType);
	}
}
