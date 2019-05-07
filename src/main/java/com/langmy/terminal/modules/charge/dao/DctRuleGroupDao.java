package com.langmy.terminal.modules.charge.dao;

import java.util.List;

import com.langmy.terminal.common.entity.DctRuleGroup;

public interface DctRuleGroupDao extends BaseRuleDao<DctRuleGroup>{

	public List<DctRuleGroup> findByDelFlagFalse();
	
	public List<DctRuleGroup> findByDelFlagFalseAndNameLike(String name);
}
