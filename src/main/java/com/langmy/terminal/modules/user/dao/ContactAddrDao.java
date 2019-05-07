package com.langmy.terminal.modules.user.dao;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.ContactAddr;

/**
 * 联系地址Dao
 * 
 * @author ZZD
 *
 */
public interface ContactAddrDao extends BaseDao<ContactAddr> {
	/**
	 * 
	 * @param contactId
	 * @param type
	 * @return
	 */
	public ContactAddr findByContactIdAndType(Integer contactId, Integer type);

	/**
	 * 根据id查询联系地址
	 * 
	 * @param id
	 *            客户id
	 * @return
	 */
	public ContactAddr findByContactId(int id);
}
