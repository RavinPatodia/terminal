package com.langmy.terminal.modules.charge.utils;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.charge.dao.ChargeRuleDao;
import com.langmy.terminal.modules.charge.service.ChargeRuleService;
import com.langmy.terminal.modules.charge.service.extend.ChargeRuleExtend;

@Service
public class ChargeRuleUtils {

	private static ChargeRuleDao chargeRuleDao = (ChargeRuleDao) SpringContextHolder
			.getBean(ChargeRuleDao.class);

	/**
	 * 根据Id查找收费规则
	 * 
	 * @param id
	 * @return
	 */
	public static ChargeRule findChargeRuleById(Integer id) {
		Assert.notNull(id, "查询收费规则的id不能为空");
		return chargeRuleDao.findOne(id);
	}

	/**
	 * 将传入的chargeRule列表转化成Model列表
	 * @return ChargeRule Model数组
	 */
	public static List<BaseModel> covertChargeRuleToModel(List<ChargeRule> chargeRules) {
		return Lists.newArrayList(ChargeRuleExtend.covertToModel(chargeRules));
	}

	/**
	 * 查找生效的规则，另外Id不在传入的列表之内
	 * @param ids id列表
	 * @return
	 */
	public static List<ChargeRule> findEffectRuleAndIdNotIn(List<Integer> ids) {
		return chargeRuleDao.findByDelFlagFalseAndIdNotIn(ids);
	}

	/**
	 * 查找所有应用于用户组的生效<不是历史规则、启用>的收费规则
	 * 
	 * @return
	 */
	public static List<ChargeRule> findEffectRuleBelongUserG() {
		return chargeRuleDao.findByDelFlagFalse();
	}
	
	/**
	 * 根据类型以及收费规则名称查找相应的生效的收费规则
	 * 
	 * @return
	 */
	public static List<ChargeRule> findEffectRuleByNameAndType(String ruleName,int rentType) {
		if(StringUtils.isNotNullAndEmpty(ruleName))
			return chargeRuleDao.findByDelFlagFalseAndRuleNameContainingAndRentType(ruleName, rentType);
		else
			return chargeRuleDao.findByDelFlagFalseAndRentType(rentType);
	}
	
	/**
	 * 根据名称与传入的规则类型，查找不是该收费类型的名称匹配（不是历史规则的未删除）的收费规则
	 * 
	 * @return
	 */
	public static List<ChargeRule> findEffectRuleByNameAndTypeNotEqual(String ruleName,int rentType) {
//		if(StringUtils.isNotNullAndEmpty(ruleName))
			return chargeRuleDao.findByDelFlagFalseAndRuleNameContainingAndRentTypeNot(ruleName, rentType);
//		else
//			return chargeRuleDao.findByEnableFlagTrueAndHistoryFlagFalseAndDelFlagFalseAndRentType(rentType);
	}

	/**
	 * 根据名称查找生效的车位租赁规则
	 * @param ruleName 收费规则名称
	 * @return
	 */
	public static List<ChargeRule> findEffectAnchorChargeRule(String ruleName) {
		return chargeRuleDao.findByDelFlagFalseAndRuleNameContainingAndRentType(ruleName,ChargeRuleService.RENT_TYPE_ANCHOR);
	}

}
