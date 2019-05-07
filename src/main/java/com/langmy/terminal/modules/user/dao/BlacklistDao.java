package com.langmy.terminal.modules.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Blacklist;

/**
 * 黑名单Dao
 * 
 * @author ZZD
 *
 */
public interface BlacklistDao extends BaseDao<Blacklist> {
	/**
	 * 根据userId查询有效的黑名单
	 * 
	 * @param userId
	 *            客户id
	 * @return
	 */
	public Blacklist findByUserIdAndIsEffectTrue(Integer userId);
	

	/**
	 * 根据blacklistId模糊查询有效的黑名单
	 * 
	 * @param blacklistId
	 * @return
	 */
	public List<Blacklist> findByBlacklistIdLike(
			String blacklistId);

	/**
	 * 获得有效的黑名单
	 * 
	 * @return
	 */
	public List<Blacklist> findByIsEffectTrue();
	
	/**
	 * 获得数据库最大id
	 * 
	 * @return
	 */
	@Query(value = "select MAX(id) from blacklist", nativeQuery = true)
	public Integer getMaxId();
}
