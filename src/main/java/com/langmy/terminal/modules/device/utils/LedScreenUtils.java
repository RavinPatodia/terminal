package com.langmy.terminal.modules.device.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.langmy.terminal.common.entity.LedScreen;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.LedScreenDao;
import com.langmy.terminal.modules.device.service.LedScreenService;

public class LedScreenUtils {
	private static LedScreenDao ledScreenDao = SpringContextHolder
			.getBean(LedScreenDao.class);
	private static LedScreenService ledScreenService = SpringContextHolder
			.getBean(LedScreenService.class);
	

	/**
	 * 根据车道Id得到显示屏
	 * @param drivewayId 车道Id
	 * @return
	 */
	public static List<LedScreen> getByDrivewayId(int drivewayId) {
		return ledScreenService.getByDrivewayId(drivewayId);
	}
	
	/**
	 * 根据车道Id得到显示屏并且在该显示屏上显示信息
	 * @param drivewayId 车道Id message信息
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void setoneLineMessageByDrivewayId(int drivewayId,String message) throws UnknownHostException, IOException {
		List<LedScreen> ledScreens = ledScreenService.getByDrivewayId(drivewayId);
		for(LedScreen ledScreen :ledScreens){
			ledScreenService.setOneLineMessage(ledScreen,message);
		}
	}
	
	public static List<BaseModel> findMessageScreen() {
		return ledScreenService.findMessageScreen();
	}
	
	public static List<BaseModel> findCostScreen() {
		return ledScreenService.findCostScreen();
	}
	
	public static LedScreen findByName(String name){
		return ledScreenDao.findByName(name);
	}
	
	public static LedScreen findByLedId(String ledId){
		return ledScreenDao.findByLedId(ledId);
	}
	
	public static LedScreen findById(int id){
		return ledScreenDao.findOne(id);
	}
	
	public static List<LedScreen> findByConfigureId(int id){
		return ledScreenDao.findByConfigureId(id);
	}
	/**
	 * 根据传入的显示屏ID修改显示屏状态
	 * @param id 显示屏ID
	 * @return 1/0   成功/失败
	 */
	public static int updateStateOpen(String id) {
		return ledScreenDao.updateStateOpen(id);
	}
	
	/**
	 * 根据传入的显示屏ID修改显示屏状态
	 * @param id 显示屏ID
	 * @return 1/0   成功/失败
	 */
	public static int updateStateClose(String id) {
		return ledScreenDao.updateStateClose(id);
	}
}
