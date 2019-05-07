package com.langmy.terminal.modules.device.utils;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.service.DeviceService;

/**
 * 闸机对外的接口
 * @author LuZixiang
 *
 */
public class BrakeMachineUtils {
	private static DeviceService deviceService = SpringContextHolder
			.getBean(DeviceService.class);

	/**
	 * 开启闸机
	 * @param id 闸机设备的ID
	 * @return
	 * @throws AWTException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean openByDrivewayId(int id) throws AWTException, UnknownHostException, IOException {
		Device device = BrakeMachineUtils.getBrakeMachineByDriveway(id);
		return deviceService.openBrake(device.getId());
	}

	/**
	 * 关闭闸机
	 * @param id 闸机设备的ID
	 * @return
	 * @throws AWTException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean close(int id) throws AWTException, UnknownHostException, IOException {
		Device device = BrakeMachineUtils.getBrakeMachineByDriveway(id);
		return deviceService.closeBrake(device.getId());
	}
	
	/**
	 * 根据传入的闸机名称 得到所有的闸机
	 * @param name
	 * @return
	 */
	public static List<BaseModel> getAllBrakeMachineByName(String name) {
		return deviceService.getAllBrakeMachineByName(name);
	}
	
	/**
	 * 打开闸机
	 * @param device
	 * @return
	 * @throws AWTException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean openBrakeMachine(Device device) throws AWTException, UnknownHostException, IOException {
		int deviceId= device.getId();
		return deviceService.openBrake(deviceId);
	}
	
	/**
	 * 根据传入的车道Id 找到对应的闸机机
	 * @param id 车道id
	 * @return 闸机实体
	 */
	public static Device getBrakeMachineByDriveway(int id){
 		return deviceService.getBrakeMachineByDriveway(id);
 	}
}
