package com.langmy.terminal.modules.device.utils;

import java.util.List;

import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.entity.Driveway;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.DrivewayDao;
import com.langmy.terminal.modules.device.service.DrivewayService;

/**
 * 摄像头对外的接口
 * @author LuZixiang
 *
 */
public class DrivewayUtils {
	private static DrivewayDao drivewayDao = SpringContextHolder
			.getBean(DrivewayDao.class);
	private static DrivewayService drivewayService = SpringContextHolder
			.getBean(DrivewayService.class);

	/**
	 * 根据车道模糊名称 得到车道列表
	 * @param name 车道模糊名称
	 * @return
	 */
	public static List<BaseModel> getDrivewayByNameLike(String name) {
		return drivewayService.getDrivewayByNameLike(name);
	}


	/**
	 * 根据车道模糊名称和车道方向 得到车道列表
	 * @param name 车道模糊名称
	 * @param direction 车道方向
	 * @return
	 */
	public static List<BaseModel> getDrivewayByNameLikeAndDirection(String name,int direction) {
		return drivewayService.getDrivewayByNameLikeAndDirection(name, direction);
	}
	
	public static Driveway getDrivewayById(int id) {
		return drivewayService.getDrivewayById(id);
	}
	
	public static List<BaseModel> getDrivewayByDirectionIn(){
		return drivewayService.getDrivewayByDirection(0);
	}
	
	public static List<BaseModel> getDrivewayByDirectionOut(){
		return drivewayService.getDrivewayByDirection(1);
		
	}

	public static int updateTerminal(List<Integer> ids,Integer terminalId){
		return drivewayDao.updateTerminal(ids, terminalId);
	}
	
	public static int resetTerminal(Integer terminalId){
		return drivewayDao.resetTerminal(terminalId);
	}
	
	public static void addTerminal(List<Integer> drivewayIds,Device terminal){
		for(int id:drivewayIds){
			Driveway driveway = drivewayDao.findOne(id);
			driveway.setTerminal(terminal);
		}
	}
	
	public static List<Driveway> findByTerminalIdIsNull(){
		return drivewayDao.findByTerminalIdIsNull();
	}
}
