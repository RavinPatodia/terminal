package com.langmy.terminal.modules.device.utils;

import java.util.List;

import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.ParkDao;
import com.langmy.terminal.modules.device.service.ParkService;

/**
 * 区域的utils
 * 
 * @author MC
 *
 */
public class ParkUtils {
	private static ParkService parkService = SpringContextHolder
			.getBean(ParkService.class);
	private static ParkDao parkDao = SpringContextHolder
			.getBean(ParkDao.class);
	/*private static ParkDao parkDao = SpringContextHolder.getBean(ParkDao.class);*/
	
	
	/**
	 * 根据Id找到停车场
	 * @param id 停车场主键
	 * @return
	 */
	public static Park findById(int id){
		return parkDao.findOne(id);
	}
	
	/**
	 * 获取park
	 * 
	 * @return
	 */
	public static List<BaseModel> getParks(String name) {
		return parkService.getParkIdByName(name);

	}
	
	/**
	 * 获取park
	 * 
	 * @return
	 */
	public static Park getPark(String name) {
		return parkService.getParkByName(name);

	}
	

	/**
	 * 根据ID和图片路径更新park信息
	 * @param id
	 * @param mapPath
	 * @return
	 */
	public static int saveParkMapPath(int id,String mapPath) {
		return parkService.saveParkMapPath(id, mapPath);
	}
	
	
	/**
	 * 根据ID获取停车场的底图路径
	 * @param id
	 * @return
	 */
	public static String getMapPath(int id){
		return parkService.getMapPath(id);
	}
	
	/**
	 * 根据传入的停车场获得其下级子节点
	 * @param park 停车场
	 * @return
	 */
	public static List<Park> getParkChildrenByPark(Park park){
		return parkService.getParkChildrenByPark(park);
	}
}
