package com.langmy.terminal.modules.user.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.UserPSp;

/**
 * 用户-车位组Dao
 * 
 * @author Administrator
 *
 */
public interface UserPSpDao extends BaseDao<UserPSp> {
	/**
	 * 通过用户ID找用户-车位
	 * 
	 * @param userid
	 * @return
	 */
	@Query("from UserPSp u where u.user.id = :userid")
	public List<UserPSp> findByUserId(@Param("userid") Integer userid);
	
	public List<UserPSp> findByExpirationTimeGreaterThan(Date date);

}
