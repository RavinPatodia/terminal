package com.langmy.terminal.modules.device.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Camera;
import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.entity.Driveway;
import com.langmy.terminal.common.entity.LedScreen;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.DrivewayDao;
import com.langmy.terminal.modules.device.model.CameraModel;
import com.langmy.terminal.modules.device.model.DrivewayModel;
import com.langmy.terminal.modules.device.utils.BrakeMachineUtils;
import com.langmy.terminal.modules.device.utils.CameraUtils;
import com.langmy.terminal.modules.device.utils.DeviceUtils;
import com.langmy.terminal.modules.device.utils.LedScreenUtils;

/**
 * 车道model和实体类之间的转换
 * @author LuZixiang
 *
 */
public class DrivewayExtend extends BaseExtend{
	private static DrivewayDao drivewayDao = SpringContextHolder.getBean("drivewayDao");

	public static DrivewayDao getDrivewayDao() {
		return drivewayDao;
	}

	public static void setDrivewayDao(DrivewayDao drivewayDao) {
		DrivewayExtend.drivewayDao = drivewayDao;
	}
	
	/**
	 * 车道实体类到model类的转换
	 * @param driveways
	 * @return
	 */
	public static List<DrivewayModel> coverToModel(List<Driveway> driveways){
		List<DrivewayModel> drivewayModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(driveways)) {
			return Lists.newArrayList(drivewayModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "bayonet.name");
		map.put("targetPro1", "bayonetName");
		map.put("sourcePro2", "terminal.name");
		map.put("targetPro2", "terminalName");
		try {
			drivewayModels = BeanUtils.copyListProperties(driveways,
					DrivewayModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			System.out.println(e);
		}
		for(int i = 0;i<driveways.size();i++){
			int drivewayKey = driveways.get(i).getId();
			Device brakeMachine = BrakeMachineUtils.getBrakeMachineByDriveway(drivewayKey);
			if(brakeMachine!=null){
				drivewayModels.get(i).setBrakeMachine(brakeMachine.getName());
			}
			
			List<LedScreen> ledScreen = LedScreenUtils.findByConfigureId(drivewayKey);
			if(ledScreen.size()>=1){
				drivewayModels.get(i).setLedScreen(ledScreen.get(0).getName());
			}
		}
		
		
		return Lists.newArrayList(drivewayModels);
	}

	/**
	 * 获取单个model
	 * @param driveway
	 * @return
	 */
	public static DrivewayModel getModelByDriveway(Driveway driveway) {
		DrivewayModel model = new DrivewayModel();
		model.setId(driveway.getId());
		model.setName(driveway.getName());
		model.setDirection(driveway.getDirection());
		model.setReleaseRule(driveway.getReleaseRule());
		if(driveway.getBayonet()!=null){
			model.setBayonetName(driveway.getBayonet().getName());
			model.setBayonetPK(driveway.getBayonet().getId());
		}
		if(driveway.getTerminal()!=null){
			model.setTerminalName(driveway.getTerminal().getName());
			model.setTerminalPK(driveway.getTerminal().getId());
		}
		
		int drivewayKey = driveway.getId();
		
		List<Camera> cameras = drivewayDao.findOne(drivewayKey).getCamera();
		List<CameraModel> cameraModelsInGroup = CameraExtend.coverToModel(cameras);
		
		List<CameraModel> cameraModelsNotInGroup;
		cameraModelsNotInGroup = CameraExtend.coverToModel(CameraUtils.findByDrivewayIsNull());
		
		String camerasIds = "";
		for (Camera c : cameras) {
			camerasIds += c.getCameraId() + ",";
		}
		
		model.setCameraModelsInGroup(cameraModelsInGroup);
		model.setCameraModelsNotInGroup(cameraModelsNotInGroup);
		model.setCamerasIds(camerasIds);
		
		//显示屏
		List<LedScreen> ledScreen = LedScreenUtils.findByConfigureId(drivewayKey);
		if(ledScreen.size()>=1){
			if(model.getDirection()==0){
				model.setLedScreenIn(ledScreen.get(0).getName());
				model.setScreenInPK(ledScreen.get(0).getId());
			}
			else{
				model.setLedScreenOut(ledScreen.get(0).getName());
				model.setScreenOutPK(ledScreen.get(0).getId());
			}
		}
		
		//闸机
		Device brakeMachine = BrakeMachineUtils.getBrakeMachineByDriveway(drivewayKey);
		if(brakeMachine!=null){
			model.setBrakeMachine(brakeMachine.getName());
			model.setBrakePK(brakeMachine.getId());
		}
		
		return model;
	}
}
