package com.langmy.terminal.modules.sys.utils;

import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.sys.dao.RoleDao;

/**
 * 角色工具类
 * 
 * @author
 *
 */
public class RoleUtils {
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);

	public static String getRoleNum() {
		int num = roleDao.getMaxId() + 1;
		return "Role" + num;
	}
}
