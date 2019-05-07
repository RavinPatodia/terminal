package com.langmy.terminal.modules.user.utils;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Account;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.entity.UserPSp;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.user.dao.UserDao;
import com.langmy.terminal.modules.user.model.UserModel;
import com.langmy.terminal.modules.user.service.UserService;

/**
 * 客户Utils
 * 
 * @author lxj
 *
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static UserService userService = SpringContextHolder
			.getBean(UserService.class);

	/**
	 * 通过id查找客户
	 * 
	 * @param id
	 * @return
	 */
	public static User findUserById(Integer id) {
		return userDao.findOne(id);
	}

	/**
	 * 获取所有非黑名单且启用的客户
	 * 
	 * @param uacc
	 * @return
	 */
	public static List<BaseModel> getUserNotBlackAndIsEnable(String name) {
		return userService.getUserNotBlack(name);
	}

	/**
	 * 根据id获取客户
	 * 
	 * @param id
	 * @return
	 */
	public static UserModel getUser(int id) {
		return userService.view(id);
	}

	public static User findBylicensePlate(String licensePlate) {
		return userDao.findBylicensePlate(licensePlate);
	}

	/**
	 * 根据用户Id找到用户拥有的，没有被占用的车位
	 * 
	 * @param userId
	 * @return
	 */
	public static List<PSp> findPspNotUsedByUserId(int userId) {
		User user = userDao.findOne(userId);
		if (user == null) {
			return null;
		}
		Set<UserPSp> userPsps = user.getUserPSps();
		List<PSp> pspList = Lists.newArrayList();
		for (UserPSp up : userPsps) {
			PSp p = up.getPSp();
			if (p.isDelFlag()) {
				continue;
			}
			if (p.getCar() == null) {
				pspList.add(p);
			}
		}
		return pspList;
	}

	public static Account findAccountByUserId(Integer userPK) {
		User user = userDao.findOne(userPK);
		if (user != null) {
			return user.getAccount();
		}
		return null;
	}
}
