package com.langmy.terminal.modules.charge.dao;

import java.util.List;

import com.langmy.terminal.common.entity.DctRule;

public interface DctRuleDao extends BaseRuleDao<DctRule>{

	/**
	 * 查找生效的优惠规则，并且Id不在列表之中
	 * @param ids
	 * @return
	 */
	public List<DctRule> findByDelFlagFalseAndIdNotIn(List<Integer> ids);
	
	/**
	 * 查找生效的优惠规则
	 * @return
	 */
	public List<DctRule> findByDelFlagFalse();

	/**
	 * @param dctRuleIds
	 * @return
	 */
	public List<DctRule> findByIdIn(List<Integer> dctRuleIds);
	
}
