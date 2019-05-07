package com.langmy.terminal.modules.user.utils;

import java.util.Date;

import com.langmy.terminal.common.entity.Blacklist;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.user.dao.BlacklistDao;

/**
 * 黑名单Utils
 * 
 * @author lxj
 *
 */
public class BlacklistUtils {

	private static BlacklistDao blacklistDao = SpringContextHolder
			.getBean(BlacklistDao.class);

	/**
	 * 根据客户id获取黑名单
	 * 
	 * @param userId
	 * @return
	 */
	public static Blacklist getEffectBlacklist(User user) {
		Blacklist blacklist = blacklistDao.findByUserIdAndIsEffectTrue(user
				.getId());
		return blacklist;
	}

	/**
	 * 判断是否黑名单用户
	 * 
	 * @param userId
	 * @return
	 */

	public static boolean isBlack(User user) {
		Date now = new Date();
		Blacklist blackList = blacklistDao.findByUserIdAndIsEffectTrue(user
				.getId());
		if (blackList == null) {
			return false;
		}
		Date endTime = blackList.getEndTime();
		if (endTime != null && endTime.before(now)) {
			return false;
		}
		return true;
	}
}
