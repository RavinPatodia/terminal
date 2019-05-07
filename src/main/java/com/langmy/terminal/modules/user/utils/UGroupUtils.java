package com.langmy.terminal.modules.user.utils;

import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.user.dao.UGroupDao;

/**
 * 客户组Util
 * 
 * @author ZZD
 *
 */
public class UGroupUtils {
	private static UGroupDao ugroupDao = SpringContextHolder
			.getBean(UGroupDao.class);

	/**
	 * 根据客户组主键获得客户组
	 * 
	 * @param id
	 *            客户组主键
	 * @return
	 */
	public static UGroup findOne(int id) {
		return ugroupDao.findOne(id);
	}

	public static UGroup save(UGroup ugroup) {
		return ugroupDao.save(ugroup);

	}

	/**
	 * 通过id查到对应的优惠规则组
	 * 
	 * @param id
	 * @return
	 */
	public static DctRuleGroup findDctRuleGroupByGroupId(Integer id) {
		return ugroupDao.findDctRuleGroupById(id);
	}

	/**
	 * 根据用户得到用户的类型
	 * 
	 * @param user
	 *            用户
	 * @return
	 */
	public static int getUserTypeByUser(User user) {
		UGroup uGroup = user.getUGroup();
		return uGroup.getType();
	}

	public static void changeType(int type1, int type2) {
		ugroupDao.changeType(type1, type2);
	}

}
