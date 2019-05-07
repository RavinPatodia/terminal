package com.langmy.terminal.modules.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.entity.UGroup;

/**
 * 客户组Dao
 * 
 * @author ZZD
 *
 */
public interface UGroupDao extends BaseDao<UGroup> {
	/**
	 * 启用
	 * 
	 * @param id
	 * @return
	 */
	@Query("update UGroup ugroup set ugroup.type= :type2 where ugroup.type= :type1")
	@Modifying
	public int changeType(@Param("type1") int type1, @Param("type2") int type2);

	/**
	 * 启用
	 * 
	 * @param id
	 * @return
	 */
	@Query("update UGroup ugroup set ugroup.enableFlag=1,ugroup.lastEnable = NOW() where ugroup.id= :id")
	@Modifying
	public int enable(@Param("id") int id);

	/**
	 * 禁用
	 * 
	 * @param id
	 * @return
	 */
	@Query("update UGroup ugroup set ugroup.enableFlag=0,ugroup.lastDisable = NOW() where ugroup.id= :id")
	@Modifying
	public int disable(@Param("id") int id);

	/**
	 * 启用
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update UGroup u set u.enableFlag=1,u.lastEnable= NOW() where u.id in (:ids)")
	@Modifying
	public int enable(@Param("ids") List<Integer> ids);

	/**
	 * 禁用
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update UGroup u set u.enableFlag=0,u.lastDisable = NOW() where u.id in (:ids) ")
	@Modifying
	public int disable(@Param("ids") List<Integer> ids);

	/**
	 * 查询优惠规则
	 * 
	 * @param id
	 *            客户组id
	 * @return
	 */
	@Query("select u.dctRuleGroup from UGroup u where u.id = :id and u.delFlag=0")
	public DctRuleGroup findDctRuleGroupById(@Param("id") Integer id);

	/**
	 * 根据 客户组编号查询
	 * 
	 * @param ugroupId
	 * @return
	 */
	public List<UGroup> findByUgroupIdLikeAndDelFlagFalse(String ugroupId);

	/**
	 * 根据name查询
	 * 
	 * @param name
	 *            客户组名称
	 * @return
	 */
	public List<UGroup> findByNameLikeAndDelFlagFalse(String name);

	/**
	 * 根据名称、类型查询
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<UGroup> findByNameLikeAndTypeAndDelFlagFalseAndEnableFlagTrue(
			String name, Integer type);

	/**
	 * 排除类型查询 名称模糊查询
	 * 
	 * @param type
	 * @return
	 */
	public List<UGroup> findByNameLikeAndTypeNotAndDelFlagFalseAndEnableFlagTrue(
			String name, Integer type);

	/**
	 * 根据类型查询
	 * 
	 * @param type
	 * @return
	 */
	public List<UGroup> findByTypeAndDelFlagFalseAndEnableFlagTrue(Integer type);

	/**
	 * 获得最大的id
	 * 
	 * @return
	 */
	@Query(value = "select MAX(id) from u_group", nativeQuery = true)
	public Integer getMaxId();

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update UGroup u set u.delFlag=1 where u.id in (:ids)")
	@Modifying
	public int del(@Param("ids") List<Integer> ids);

}
