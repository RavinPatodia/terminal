package com.langmy.terminal.modules.device.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Camera;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.CameraDao;
import com.langmy.terminal.modules.device.model.CameraModel;

/**
 * 摄像头model和实体之间的转换
 * @author LuZixiang
 *
 */
public class CameraExtend extends BaseExtend{
	private static CameraDao cameraDao = SpringContextHolder.getBean("cameraDao");

	public static CameraDao getCameraDao() {
		return cameraDao;
	}

	public static void setCameraDao(CameraDao cameraDao) {
		CameraExtend.cameraDao = cameraDao;
	}
	
	public static List<CameraModel> coverToModel(List<Camera> cameras) {
		List<CameraModel> cameraModels = Lists.newArrayList();
		if(ListUtils.isNullOrEmpty(cameras)){
			return Lists.newArrayList(cameraModels);
		}
		
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "driveway.name");
		map.put("targetPro1", "drivewayName");
		map.put("sourcePro2", "driveway.id");
		map.put("targetPro2", "drivewayId");
		try {
			cameraModels = BeanUtils
					.copyListProperties(cameras, CameraModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException
				| IllegalArgumentException | ClassNotFoundException | IntrospectionException
				| IOException e) {
			logger.error("",e);
		}
		return Lists.newArrayList(cameraModels);
	}
	
	/**
	 * 单个camera实体到model的转换
	 * @param camera
	 * @return
	 */
	public static CameraModel getModelByCamera(Camera camera) {
		CameraModel model = new CameraModel();
		try {
			BeanUtils.copyProperties(camera, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("camera实体类转换失败",e);
			return null;
		}
		model.setCameraId(camera.getCameraId());
		model.setBrand(camera.getBrand());
		model.setCameraState(camera.getCameraState());
		model.setDescribion(camera.getDescribion());
		model.setDevicePosit(camera.getDevicePosit());
		model.setIp(camera.getIp());
		model.setLightState(camera.getLightState());
		model.setType(camera.getType());
		if(camera.getDriveway()!=null){
			model.setDrivewayId(camera.getDriveway().getId());
			model.setDrivewayName(camera.getDriveway().getName());
		}

		
		return model;
	}
}
