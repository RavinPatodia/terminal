package com.langmy.terminal.modules.psp.utils;

import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.psp.service.ArrangeService;

/**
 * 拖拽的utils
 * 
 * @author MC
 *
 */
public class ArrangeUtils {
	private static ArrangeService arrangeService = SpringContextHolder
			.getBean(ArrangeService.class);

	/**
	 * 修改当前车位和摄像头的状态（开）
	 * 
	 * @param divId
	 * @return
	 */
	public static boolean updateCameraPspOpen(String divId) {
		return arrangeService.updateCameraPspOpen(divId);
	}

	/**
	 * 修改当前车位和摄像头的状态（关）
	 * 
	 * @param divId
	 * @return
	 */
	public static boolean updateCameraPspClose(String divId) {
		return arrangeService.updateCameraPspClose(divId);
	}

	/**
	 * 更新当期那车位上车辆信息
	 * 
	 * @param licensePlate
	 * @param divId
	 */
	public static void updatePspCar(String licensePlate, String divId) {
		arrangeService.updatePspCar(licensePlate, divId);
	}
	
	public static void deletePsp(String pspId,String type) throws ServiceException {
		arrangeService.delete(pspId, type);
	}
}
