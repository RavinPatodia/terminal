package com.langmy.terminal.modules.user.dao;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.ContactWay;

/**
 * 联系方式Dao
 * 
 * @author ZZD
 *
 */
public interface ContactWayDao extends BaseDao<ContactWay> {
	/**
	 * 
	 * @param contactId
	 * @param type
	 * @return
	 */
	public ContactWay findByContactIdAndType(Integer contactId, Integer type);

	/**
	 * 根据id查询联系方式
	 * 
	 * @param id
	 *            客户id
	 * @return
	 */
	public ContactWay findByContactId(Integer contactId);
}
