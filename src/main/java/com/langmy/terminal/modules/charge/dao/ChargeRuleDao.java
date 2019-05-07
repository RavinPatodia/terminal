package com.langmy.terminal.modules.charge.dao;

import java.util.List;

import com.langmy.terminal.common.entity.ChargeRule;

public interface ChargeRuleDao extends BaseRuleDao<ChargeRule> {

	/**
	 * 查找所有应用于用户组的生效（不是历史规则、启用）的收费规则
	 * 
	 * @return
	 */
	public List<ChargeRule> findByDelFlagFalse();
	
	/**
	 * 根据规则类型查找收费规则
	 * @param rentType 收费规则类型
	 * @return
	 */
	public List<ChargeRule> findByDelFlagFalseAndRentType(int rentType);
	
	/**
	 * 根据规则类型和收费规则名称查找收费规则
	 * @param ruleName 收费规则名称
	 * @param rentType 收费规则类型
	 * @return
	 */
	public List<ChargeRule> findByDelFlagFalseAndRuleNameContainingAndRentType(String ruleName,int rentType);

	
	/**
	 * 根据名称与传入的规则类型，查找不是该收费类型并且名称匹配的收费规则
	 * @param ruleName 收费规则名称
	 * @param rentType 收费规则类型
	 * @return
	 */
	public List<ChargeRule> findByDelFlagFalseAndRuleNameContainingAndRentTypeNot(String ruleName,int rentType);
	
	/**
	 * 根据收费规则的租赁类型和外键编号找到的收费规则
	 * @param oldBaseRuleId
	 * @param rentType 租赁类型
	 * @return
	 */
	public List<ChargeRule> findByBaseRuleIdAndRentType(
			int oldBaseRuleId, int rentType);

	/**
	 * 查找Id不在传入的列表之内的收费规则
	 * 
	 * @return
	 */
	public List<ChargeRule> findByDelFlagFalseAndIdNotIn(
			List<Integer> ids);

}
