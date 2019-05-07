package com.langmy.terminal.modules.charge.utils;

import com.langmy.terminal.common.entity.DctRule;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.DctRuleDao;

/**
 * 优惠规则接口类
 * 
 * @author lzy
 *
 */
public class DctRuleUtils {

	private static DctRuleDao dctRuleDao = (DctRuleDao) SpringContextHolder
			.getBean(DctRuleDao.class);

	/**
	 * 根据id找到优惠规则
	 * 
	 * @param id
	 * @return
	 */
	public static DctRule findById(int id) {
		return dctRuleDao.findOne(id);
	}

}
