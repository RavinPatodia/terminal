package com.langmy.terminal.modules.device.utils;

import java.util.List;

import com.langmy.terminal.common.entity.Camera;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.CameraDao;

/**
 * 摄像头对外的接口
 * @author LuZixiang
 *
 */
public class CameraUtils {
	private static CameraDao cameraDao = SpringContextHolder
			.getBean(CameraDao.class);
	
	/**
	 * 根据传入的摄像机ID修改摄像机状态
	 * @param id 摄像机ID
	 * @return 1/0   成功/失败
	 */
	public static int updateStateOpen(String id) {
		return cameraDao.updateStateOpen(id);
	}
	
	/**
	 * 根据传入的摄像机ID修改摄像机状态
	 * @param id 摄像机ID
	 * @return 1/0   成功/失败
	 */
	public static int updateStateClose(String id) {
		return cameraDao.updateStateClose(id);
	}
	
	/**
	 * 根据传入的摄像机ID返回摄像机
	 * @param id 摄像机ID
	 * @return 摄像机
	 */
	public static Camera findByCameraId(String id) {
		return cameraDao.findByCameraId(id);
	}
	
	public static List<Camera> findByDrivewayIsNull(){
		int driveway = 0;
		return cameraDao.findByDrivewayIsNullAndType(driveway);
	}
	
	/**
	 * 根据传入的Ids来查找id在数组之内的区域
	 * 
	 * @param ids
	 * @return
	 */
	public static List<Camera> findCameraByIdIn(List<Integer> ids) {
		return cameraDao.findByIdIn(ids);
	}
}
