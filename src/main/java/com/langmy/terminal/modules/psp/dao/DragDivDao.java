package com.langmy.terminal.modules.psp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.DragDiv;

/**
 * 拖拽的Dao
 * @author MC
 *
 */
public interface DragDivDao extends BaseDao<DragDiv> {

	/**
	 * 根据唯一的divId查找实体
	 * 
	 * @param divId
	 * @return
	 */
	public DragDiv findByDivId(String divId);
	
	/**
	 * 根据唯一的parkId查找实体
	 * 
	 * @param parkId
	 * @return
	 */
	public List<DragDiv> findByParkId(Integer parkId);

	/**
	 * 修改摄像头、车位的状态信息为启用
	 * 
	 * @param divId
	 * @return
	 */
	@Query("update DragDiv dd set dd.enable=1 where dd.divId = :divId ")
	@Modifying
	public int updatePspCameraStart(@Param("divId") String divId);

	/**
	 * 修改摄像头、车位的状态信息为禁用
	 * 
	 * @param divId
	 * @return
	 */
	@Query("update DragDiv dd set dd.enable=0 where dd.divId = :divId ")
	@Modifying
	public int updatePspCameraStop(@Param("divId") String divId);

	/**
	 * 修改摄像头、车位的状态信息
	 * 
	 * @param enable
	 * @param divId
	 * @return
	 */
	@Query("update DragDiv dd set dd.enable= :enable where dd.divId = :divId")
	@Modifying
	public int dragDivState(@Param("enable") Integer enable,
			@Param("divId") String divId);

	/**
	 * 修改摄像头、车位的状态信息
	 * 
	 * @param licensePlate
	 * @param divId
	 * @return
	 */
	@Query("update DragDiv dd set dd.licensePlate= :licensePlate where dd.divId = :divId")
	@Modifying
	public int dragDivLicensePlate(@Param("licensePlate") String licensePlate,
			@Param("divId") String divId);

	
	/**
	 * 根据divId查找dragDiv
	 * 
	 * @param divId
	 * @return list
	 */
	@Query("from DragDiv dd where dd.divId like :divId")
	public List<DragDiv> getDragDivs(@Param("divId") String divId);
}