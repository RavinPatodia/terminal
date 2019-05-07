package com.langmy.terminal.modules.user.utils;

import java.util.List;

import com.langmy.terminal.common.entity.UserPSp;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.user.dao.UserPSpDao;

/**
 * 用户-车位Utils
 * @author lxj
 *
 */
public class UserPSpUtils {
	private static UserPSpDao userPSpDao = SpringContextHolder.getBean(UserPSpDao.class);
	
	/**
	 * 保存
	 * @param userPSp
	 * @return
	 */
	public static UserPSp save(UserPSp userPSp){
		return userPSpDao.save(userPSp);
	}
	
	/**
	 * 通过用户id查找车位
	 * @param id
	 * @return
	 */
	public static List<UserPSp> findPspByUserId(Integer id){
		return userPSpDao.findByUserId(id);
	}
}
