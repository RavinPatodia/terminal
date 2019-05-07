package com.langmy.terminal.modules.caradmission.utils;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.ParkingRec;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.caradmission.dao.ParkingRecDao;

/**
 * 停车记录Util
 * 
 * @author ZZD
 *
 */
public class ParkingRecUtils {

	private static ParkingRecDao parkingRecDao = SpringContextHolder
			.getBean(ParkingRecDao.class);

	/**
	 * 获取车位上当前停的车
	 * 
	 * @param pspId
	 * @return
	 */
	public static Car getCurrentParkingCarByPspId(String pspId) {
		List<ParkingRec> parkingRecs = Lists.newArrayList();
		parkingRecs = parkingRecDao.getCurrentParkingRecsByPspId(pspId);
		if (ListUtils.isNullOrEmpty(parkingRecs)) {
			return null;
		}
		return parkingRecs.get(0).getCar();
	}
}
