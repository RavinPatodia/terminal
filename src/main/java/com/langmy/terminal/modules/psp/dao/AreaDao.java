package com.langmy.terminal.modules.psp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Area;

/**
 * 区域的Dao
 * 
 * @author MC
 *
 */
public interface AreaDao extends BaseDao<Area> {

	List<Area> findByIdNotIn(List<Integer> ids);

	/**
	 * 根据传入的Ids来查找id在数组之内的区域
	 * 
	 * @param ids
	 * @return
	 */
	List<Area> findByIdIn(List<Integer> ids);

	/**
	 * 根据唯一的areaId查找实体
	 * 
	 * @param areaId
	 * @return
	 */
	public Area findByAreaId(String areaId);

	/**
	 * 根据区域name查找所有名称相似的实体
	 * 
	 * @param name
	 * @return
	 */
	public List<Area> findByNameLike(String name);

	/**
	 * 根据唯一的name查找实体
	 * 
	 * @param name
	 * @return
	 */
	public Area findByName(String name);
	
	@Query("from Area area where area.delFlag=0")
	@Modifying
	public List<Area> getAllAreaWhereISNOTDEL();
}
