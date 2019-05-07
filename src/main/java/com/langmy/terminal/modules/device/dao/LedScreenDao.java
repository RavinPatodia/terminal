package com.langmy.terminal.modules.device.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.LedScreen;

/**
 * 设备的DAO层
 * @author LuZixiang
 *
 */
public interface LedScreenDao extends BaseDao<LedScreen> {
	
	/**
	 * 根据传入的显示屏ID得到显示屏
	 * @param ledId 显示屏ID
	 * @return
	 */
	public LedScreen findByLedId(String ledId);
	
	
	/**
	 * 根据传入的显示屏名称得到显示屏
	 * @param name 显示屏名称
	 * @return
	 */
	public LedScreen findByName(String name);
	
	/**
	 * 根据传入的设备类型查找设备列表
	 * @param type 设备类型
	 * @return
	 */
	public List<LedScreen> findByDelFlagFalseAndType(int type);
	
	
	
	
	/**
	 * 根据传入的卡口ID和设备类型得到设备
	 * @param id 卡口ID(int)
	 * @param type 设备类型，1闸机，2显示屏，3终端
	 * @return
	 */
	/*@Query("from Device rule where rule.bayonet.id =:id and rule.type = :type")
	public List<Device> getDeviceByBayonetId(@Param("id")int id,@Param("type")int type);*/
//	public List<Device> findByBayonetIdAndType(@Param("id")int id,@Param("type")int type);
	/**
	 * 根据传入的卡口ID和设备类型得到设备
	 * @param bayonetId 卡口ID(String)
	 * @param type 设备类型，1闸机，2显示屏，3终端
	 * @return
	 */
/*	@Query("from Device rule where rule.bayonet.bayonetId =:bayonetId and rule.type = :type")
	public Device getDeviceByBayonetId(@Param("bayonetId")String bayonetId,@Param("type")int type);*/
	
	/**
	 * 逻辑删除多个显示屏
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update LedScreen ledScreen set ledScreen.delFlag=1 where ledScreen.id in (:ids) ")
	@Modifying
	public int softdelete(@Param("ids") List<Integer> ids);
	
	/**
	 * 获得未删除的 显示屏
	 * 
	 * @return List<LedScreen>
	 */
	public List<LedScreen> findByDelFlagFalse();
	
	
	public List<LedScreen> findByDelFlagFalseAndTypeInAndConfigureId(List<Integer> types,int configureId);
	
	public List<LedScreen> findByConfigureId(Integer configureId);
	
	/**
	 * 根据显示屏的ID启用显示屏状态
	 * @param cameraId 显示屏ID
	 * @return
	 */
	@Query("update LedScreen led set led.configureFlag=1 where led.ledId= :ledId")
	@Modifying
	public int updateStateOpen(@Param("ledId")String ledId);
	
	/**
	 * 根据显示屏的ID关闭显示屏状态
	 * @param cameraId 显示屏ID
	 * @return
	 */
	@Query("update LedScreen led set led.configureFlag=0 where led.ledId= :ledId")
	@Modifying
	public int updateStateClose(@Param("ledId")String ledId);
	
}


