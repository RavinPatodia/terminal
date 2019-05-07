package com.langmy.terminal.modules.device.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.DeviceDao;
import com.langmy.terminal.modules.device.model.DeviceModel;
import com.langmy.terminal.modules.device.service.DeviceService;

/**
 * 设备对外的接口
 * @author LuZixiang
 *
 */
public class DeviceUtils {
	private static DeviceDao deviceDao = SpringContextHolder
			.getBean(DeviceDao.class);
	private static DeviceService deviceService = SpringContextHolder
			.getBean(DeviceService.class);

	/**
	 * 查找所有显示屏
	 * @return
	 */
	public static List<BaseModel> getScreenId() {
		return deviceService.getDeviceByScreen();
	}
	
	/**
	 * 查找所有数据转换终端
	 * @return
	 */
	public static List<BaseModel> getDataMaster(String name) {
		
		return deviceService.getAllDataMasterByName(name);
	}



	/**
	 * 显示当前显示屏所对应的区域的车位数
	 * @param deviceId 显示屏设备ID
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean  changePspNum(String deviceId) throws UnknownHostException, IOException {
//		return deviceService.setMessageAboutNum(deviceId);
		return true;
	}

	/**
	 * 显示当前显示屏的信息(单行)
	 * @param bayonetId 显示屏设备ID
	 * @param message
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean  setOneLineMessage(Integer bayonetId,String message) throws UnknownHostException, IOException {
//		return deviceService.setOneLineMessage(bayonetId,message);
		return true;
	}
	
	/**
	 * 显示当前显示屏的信息(多行)
	 * @param bayonetId 显示屏设备ID
	 * @param msgs 要显示的信息
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean  setMutilineMessage(Integer bayonetId,String msgs[]) throws UnknownHostException, IOException {
	//	return deviceService.setMutilneMessage(bayonetId, msgs);
		return true;
	}
	
	public static DeviceModel getDeviceModelById(int id){
		return deviceService.getDeviceById(id);
	}
	
	
	public static Device getDeviceByName(String deviceName){
		return deviceDao.findByName(deviceName);
	}
	
	public static Device getDeviceById(Integer id){
		return deviceDao.findOne(id);
	}
}
