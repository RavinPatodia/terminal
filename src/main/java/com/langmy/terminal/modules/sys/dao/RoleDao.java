package com.langmy.terminal.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Role;

/**
 * 角色Dao
 * 
 * @author ZZD
 *
 */
public interface RoleDao extends BaseDao<Role> {
	/**
	 * 根据roleName查询
	 * 
	 * @param roleName
	 *            角色名称
	 * @return
	 */
	public Role findByRoleNameAndDelFlagFalse(String roleName);

	/**
	 * 根据roleName模糊查询
	 * 
	 * @param roleName
	 * @return
	 */
	public List<Role> findByRoleNameLikeAndDelFlagFalse(String roleName);

	/**
	 * 根据roleId模糊查询
	 * 
	 * @param roleId
	 *            角色编号
	 * @return
	 */
	public List<Role> findByRoleIdLikeAndDelFlagFalse(String roleId);

	/**
	 * 获得数据库最大id
	 * 
	 * @return
	 */
	@Query(value = "select MAX(id) from role;", nativeQuery = true)
	public Integer getMaxId();

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Role role set role.delFlag=1 where role.id in (:ids)")
	@Modifying
	public int del(@Param("ids") List<Integer> ids);

	/**
	 * 删除对应的角色权限
	 * 
	 * @param ids
	 * @return
	 */
	@Query(value = "DELETE from role_auth WHERE role_id in (:ids) ", nativeQuery = true)
	@Modifying
	public int delAuthOfRole(@Param("ids") List<Integer> ids);

	/**
	 * 删除对应的操作员角色
	 * 
	 * @param ids
	 * @return
	 */
	@Query(value = "DELETE from oper_role where role_id in (:ids) ", nativeQuery = true)
	@Modifying
	public int delOperaterOfRole(@Param("ids") List<Integer> ids);

	/**
	 * 获得未删除的角色
	 * 
	 * @return
	 */
	public List<Role> findByDelFlagFalse();
}
