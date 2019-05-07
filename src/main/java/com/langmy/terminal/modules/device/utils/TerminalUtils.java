package com.langmy.terminal.modules.device.utils;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.device.dao.DeviceDao;
import com.langmy.terminal.modules.device.service.DrivewayService;
import com.langmy.terminal.modules.device.service.extend.DeviceExtend;

/**
 * 终端机对外的接口
 * @author LuZixiang
 *
 */
public class TerminalUtils {
	private static DeviceDao deviceDao = SpringContextHolder
			.getBean(DeviceDao.class);
	private static DrivewayService drivewayService = SpringContextHolder
			.getBean(DrivewayService.class);
	
	/**
	 * 根据传入的车道Id 找到对应的终端机
	 * @param id 车道id
	 * @return 终端机实体
	 */
	public static Device getTerminalByDriveway(int id){
		return drivewayService.getDrivewayById(id).getTerminal();
 	}

	/**
	 * 根据传入的name 找到对应的终端机
	 * @param name
	 * @return 终端机List
	 */
	public static List<BaseModel> getTerminalByNameAndType(String name){
		List<Device> devices = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(name)) {
			devices = deviceDao.findByNameLikeAndTypeAndDelFlagFalse("%" + name + "%",3);
		} else {
			devices = deviceDao.findByDelFlagFalseAndType(3);
		}
		return Lists.newArrayList(DeviceExtend.coverToModel(devices));
 	}
	
	public static Device getTerminal(String ip){
		return deviceDao.findByIp(ip);
	}
	
}