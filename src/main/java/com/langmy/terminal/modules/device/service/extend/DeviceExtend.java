package com.langmy.terminal.modules.device.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.entity.Driveway;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.DeviceDao;
import com.langmy.terminal.modules.device.model.DeviceModel;
import com.langmy.terminal.modules.device.model.DrivewayModel;
import com.langmy.terminal.modules.device.utils.DrivewayUtils;


/**
 * 设备model和实体类之间的转换
 * @author LuZixiang
 *
 */
public class DeviceExtend extends BaseExtend{
	private static DeviceDao deviceDao = SpringContextHolder.getBean("deviceDao");
	
	private final static Integer TERMINAL_TYPE = 3;			// 设备类型:终端机
	
	public static DeviceDao getDeviceDao() {
		return deviceDao;
	}

	public static void setDeviceDao(DeviceDao deviceDao) {
		DeviceExtend.deviceDao = deviceDao;
	}
	
	public static List<BaseModel> coverToModel(List<Device> devices) {
		List<DeviceModel> deviceModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(devices)) {
			return Lists.newArrayList(deviceModels);
		}

		Map<String, String> map = Maps.newHashMap();

		try {
			deviceModels = BeanUtils.copyListProperties(devices,
					DeviceModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("实体类与Model类数组转化失败", e);
		}

		return Lists.newArrayList(deviceModels);
	}

	
	/**
	 * 单个Device到model的转换
	 * @param device
	 * @return
	 */
	public static DeviceModel getModelByDevice(Device device) {
		DeviceModel model = new DeviceModel();
		try {
			BeanUtils.copyProperties(device, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("实体类与Model类转化失败", e);
			return null;
		}
		if(device.getType()==TERMINAL_TYPE){
			Set<Driveway> driveways = device.getDriveWays();
			
			List<Driveway> drivewayList = Lists.newArrayList();
			String drivewayIds = "";
			for (Driveway d : driveways) {
				if(d.isDelFlag()==true){
					continue ;
				}
				drivewayIds += d.getName() + ",";
				drivewayList.add(d);
			}
			List<DrivewayModel> drivewayModelsInGroup = DrivewayExtend.coverToModel(drivewayList);
			List<DrivewayModel> drivewayModelsNotInGroup;
			drivewayModelsNotInGroup = DrivewayExtend.coverToModel(DrivewayUtils.findByTerminalIdIsNull());

			model.setDrivewayModelsInGroup(drivewayModelsInGroup);
			model.setDrivewayModelsNotInGroup(drivewayModelsNotInGroup);
			model.setDrivewayNames(drivewayIds);
		}
		
		return model;
	}

}
