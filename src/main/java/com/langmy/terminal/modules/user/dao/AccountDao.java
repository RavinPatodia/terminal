package com.langmy.terminal.modules.user.dao;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Account;

/**
 * 账户Dao
 * 
 * @author ZZD
 *
 */
public interface AccountDao extends BaseDao<Account> {
	/**
	 * 根据userId查询账户
	 * 
	 * @param userId
	 *            客户id
	 * @return
	 */
	public Account findByUserId(Integer userId);
}
