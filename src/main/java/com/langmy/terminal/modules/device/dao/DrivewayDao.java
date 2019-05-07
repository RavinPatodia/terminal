package com.langmy.terminal.modules.device.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Driveway;

/**
 * 车道的DAO层
 * @author LuZixiang
 *
 */
public interface DrivewayDao extends BaseDao<Driveway>{
	
	
	/**
	 * 根据传入的车道名称找到车道列表
	 * @param name 车道模糊名称
	 * @return
	 */
	public List<Driveway> findByNameLikeAndDelFlagFalse(String name);
	
	/**
	 * 根据传入的车道名称和车道方向找到车道列表
	 * @param name 车道模糊名称
	 * @param direction 车道方向
	 * @return
	 */
	public List<Driveway> findByNameLikeAndDirectionAndDelFlagFalse(String name,int direction);
	
	/**
	 * 根据传入的车道方向找到车道列表
	 * @param direction 车道方向
	 * @return
	 */
	public List<Driveway> findByDirectionAndDelFlagFalse(int direction);
	
	/**
	 * 获得未删除的车道
	 * 
	 * @return List<Bayonet>
	 */
	public List<Driveway> findByDelFlagFalse();
	
	/**
	 * 逻辑删除多个车道
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Driveway driveway set driveway.delFlag=1 where driveway.id in (:ids) ")
	@Modifying
	public int softdelete(@Param("ids") List<Integer> ids);
	
	/**
	 * 根据终端机ID获得绑定的车道
	 * @param terminalId 终端机ID
	 * @return
	 */
	public List<Driveway> findByTerminalId(int terminalId);
	
	/**
	 * 获得没有绑定终端机的车道
	 * @return
	 */
	public List<Driveway> findByTerminalIdIsNull();
	
	@Query("update Driveway driveway set driveway.terminal.id =:id where driveway.id in (:ids) ")
	@Modifying
	public int updateTerminal(@Param("ids") List<Integer> ids,@Param("id") Integer id);
	
	@Query("update Driveway driveway set driveway.terminal.id = null where driveway.terminal.id =:id ")
	@Modifying
	public int resetTerminal(@Param("id") Integer id);
}
