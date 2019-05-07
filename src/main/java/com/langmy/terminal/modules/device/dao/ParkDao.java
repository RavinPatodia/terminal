package com.langmy.terminal.modules.device.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Park;

/**
 * 区域的Dao
 * 
 * @author MC
 *
 */
public interface ParkDao extends BaseDao<Park> {
	
	public List<Park> findByParentId(Integer parentId);
	
	/**
	 * 根据name查找所有名称相似的实体
	 * 
	 * @param name
	 * @return
	 */
	public List<Park> findByDelFlagFalseAndNameLike(String name);
	
	public Park findByNameAndDelFlagFalse(String name);	
	
	
	@Query("update Park p set p.mapPath = :mapPath where p.id = :id")
	@Modifying
	public int saveParkMapPath(@Param("id") int id,@Param("mapPath") String mapPath);

}
