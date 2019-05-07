package com.langmy.terminal.modules.user.utils;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.UGChargeRule;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.user.dao.UGChargeRuleDao;
import com.langmy.terminal.modules.user.dao.UserDao;

/**
 * 客户组 收费规则 Util
 * 
 * @author ZZD
 *
 */

public class UGChargeRuleUtils {
	private static UGChargeRuleDao ugchargeRuleDao = SpringContextHolder
			.getBean(UGChargeRuleDao.class);
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

	/**
	 * 根据ugroupIdi查询 收费规则
	 * 
	 * @param ugroupId
	 *            客户组编号
	 * @return
	 */
	public static String getChargeRuleNamesByUGId(Integer ugroupId) {
		String chargeRuleNames = null;
		List<UGChargeRule> ugchargeRules = ugchargeRuleDao
				.getUGChargeRulesByUGroup(ugroupId);
		for (UGChargeRule ugc : ugchargeRules) {
			chargeRuleNames = chargeRuleNames + ugc.getChargeRule().getName()
					+ ',';
		}
		return chargeRuleNames;
	}

	/**
	 * 根据id找到计时计次收费规则
	 * 
	 * @param ugroupId
	 * @return
	 */
	public static ChargeRule getChargeRuleByUGId(Integer ugroupId) {
		List<ChargeRule> chargeRules = Lists.newArrayList();
		chargeRules = ugchargeRuleDao.getChargeRulesByUGroup(ugroupId);
		if (ListUtils.isNullOrEmpty(chargeRules)) {
			return null;
		}
		return chargeRules.get(0);
	}

	/**
	 * 根据id找到长期收费规则
	 * 
	 * @param ugroupId
	 * @return
	 */
	public static ChargeRule getLongtermCargeRuleByUGId(Integer ugroupId) {
		List<ChargeRule> chargeRules = Lists.newArrayList();
		chargeRules = ugchargeRuleDao.getLongtermChargeRulesByUGroup(ugroupId);
		if (ListUtils.isNullOrEmpty(chargeRules)) {
			return null;
		}
		return chargeRules.get(0);
	}

	public static List<UGChargeRule> saveUGChargeRules(
			List<UGChargeRule> ugchargeRules) {
		return (List<UGChargeRule>) ugchargeRuleDao.save(ugchargeRules);
	}

	/**
	 * 根据用户id找到收费规则
	 * 
	 * @param ugroupId
	 * @return
	 */
	public static ChargeRule getChargeRuleByUserId(Integer userId) {
		User user = userDao.findOne(userId);
		List<ChargeRule> chargeRules = Lists.newArrayList();
		chargeRules = ugchargeRuleDao.getChargeRuleByUGroupId(user.getUGroup()
				.getId());
		if (ListUtils.isNullOrEmpty(chargeRules)) {
			return null;
		}
		return chargeRules.get(0);
	}

}
