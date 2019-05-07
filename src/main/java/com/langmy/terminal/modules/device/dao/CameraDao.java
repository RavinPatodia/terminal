package com.langmy.terminal.modules.device.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Camera;

/**
 * 摄像头 DAO 层
 * @author LuZixiang
 *
 */
public interface CameraDao extends BaseDao<Camera> {
	/**
	 * 启用摄像头
	 * @param id 摄像头的主键
	 * @return
	 */
	@Query("update Camera camera set camera.cameraState=1 where camera.id= :id")
	@Modifying
	public int enable(@Param("id")int id);
	
	/**
	 * 根据摄像头的ID启用摄像头状态
	 * @param cameraId 摄像头ID
	 * @return
	 */
	@Query("update Camera camera set camera.nowState=1 where camera.cameraId= :cameraId")
	@Modifying
	public int updateStateOpen(@Param("cameraId")String cameraId);
	
	/**
	 * 根据摄像头的ID关闭摄像头状态
	 * @param cameraId 摄像头ID
	 * @return
	 */
	@Query("update Camera camera set camera.nowState=0 where camera.cameraId= :cameraId")
	@Modifying
	public int updateStateClose(@Param("cameraId")String cameraId);
	
	
	/**
	 * 启用多个摄像头
	 * @param ids 摄像头的主键
	 * @return
	 */
	@Query("update Camera camera set camera.cameraState=1 where camera.id in (:ids)")
	@Modifying
	public int enable(@Param("ids")List<Integer> ids);
	
	/**
	 * 禁用摄像头
	 * @param id 摄像头的主键
	 * @return
	 */
	@Query("update Camera camera set camera.cameraState=0 where camera.id= :id ")
	@Modifying
	public int disable(@Param("id")int id);
	
	/**
	 * 根据摄像头ID查找摄像头
	 * @param cameraId 摄像头ID
	 * @return
	 */
	public Camera findByCameraId(String cameraId);
	
	/**
	 * 禁用多个摄像头
	 * @param ids 摄像头的主键 
	 * @return
	 */
	@Query("update Camera camera set camera.cameraState=0 where camera.id in (:ids) ")
	@Modifying
	public int disable(@Param("ids")List<Integer> ids);
	
	/**
	 * 逻辑删除多个摄像机
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Camera camera set camera.delFlag=1 where camera.id in (:ids) ")
	@Modifying
	public int softdelete(@Param("ids") List<Integer> ids);
	
	/**
	 * 获得未删除的摄像机
	 * 
	 * @return List<Camera>
	 */
	public List<Camera> findByDelFlagFalse();
	
	/**
	 * 获得没有绑定车道的摄像头
	 * @return
	 */
	public List<Camera> findByDrivewayIsNullAndType(int type);
	
	/**
	 * 根据车道ID找到对应的摄像机
	 * @param id 车道ID
	 * @return
	 */
	public Camera findByDrivewayId(int id);
	
	/**
	 * 根据传入的Ids来查找id在数组之内的区域
	 * 
	 * @param ids
	 * @return
	 */
	List<Camera> findByIdIn(List<Integer> ids);
}
