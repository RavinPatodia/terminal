package com.langmy.terminal.modules.device.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Device;

/**
 * 设备的DAO层
 * @author LuZixiang
 *
 */
public interface DeviceDao extends BaseDao<Device> {

	
/*	*//**
	 * 启用设备的状态
	 * @param deviceId 设备ID
	 * @return
	 *//*
	@Query("update Device rule set where rule.deviceId= :deviceId and lineState= 1")
	@Modifying
	public int enableLineState(@Param("deviceId")String deviceId);*/

	/**
	 * 根据传入的设备ID得到设备
	 * @param deviceId 设备ID
	 * @return
	 */
	public Device findByDeviceId(String deviceId);
	
	/**
	 * 根据传入的设备名称得到设备
	 * @param deviceName 设备名称
	 * @return
	 */
	public Device findByName(String deviceName);
	
	/**
	 * 根据传入的设备类型查找设备列表
	 * @param type 设备类型
	 * @return
	 */
	public List<Device> findByTypeAndDelFlagFalse(int type);
	
	/**
	 * 逻辑删除多个设备
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Device device set device.delFlag=1 where device.id in (:ids) ")
	@Modifying
	public int softdelete(@Param("ids") List<Integer> ids);
	
	/**
	 * 获得未删除的设备
	 * 
	 * @return List<Device>
	 */
	public List<Device> findByDelFlagFalse();
	
	/**
	 * 获得未删除的终端机
	 * 
	 * @return List<Device>
	 */
	public List<Device> findByDelFlagFalseAndType(int type);
	
	/**
	 * 根据传入的设备名称找到设备列表
	 * @param name 设备模糊名称
	 * @return
	 */
	public List<Device> findByNameLikeAndTypeAndDelFlagFalse(String name,int type);
	
	/**
	 * 根据传入的车道ID和类型查找对应的设备
	 * @param id 车道ID
	 * @param type 设备类型
	 * @return 设备
	 */
	public Device findByDrivewayIdAndType(int id,int type);
	
	/**
	 * 根据传入的IP找到对应的终端机
	 * @param ip
	 * @return
	 */
	public Device findByIp(String ip);
	
}


