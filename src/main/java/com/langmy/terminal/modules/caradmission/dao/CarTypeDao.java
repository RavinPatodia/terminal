package com.langmy.terminal.modules.caradmission.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.CarType;

/**
 * 车辆相关信息管理Dao
 * 
 * @author ZZD
 *
 */
public interface CarTypeDao extends BaseDao<CarType> {
	/**
	 * 安类型获得未删除的相关信息
	 * 
	 * @param type
	 * @return
	 */
	@Query("from CarType ct where ct.type= :type and ct.delFlag=0 ")
	public List<CarType> findByType(@Param("type") Integer type);

	/**
	 * 获得车辆型号
	 * 
	 * @param type
	 * @param model
	 * @return
	 */
	public List<CarType> findByTypeAndModelLikeAndDelFlagFalse(int type,
			String model);

	/**
	 * 获得车辆颜色
	 * 
	 * @param type
	 * @param model
	 * @return
	 */
	public List<CarType> findByTypeAndColorLikeAndDelFlagFalse(int type,
			String color);

	/**
	 * 获得车牌类型
	 * 
	 * @param type
	 * @param model
	 * @return
	 */
	public List<CarType> findByTypeAndTypeNameLikeAndDelFlagFalse(int type,
			String typeName);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update CarType ct set ct.delFlag=1 where ct.id in (:ids)")
	@Modifying
	public int del(@Param("ids") List<Integer> ids);

	public CarType findByTypeName(String typeName);
}
