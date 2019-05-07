package com.langmy.terminal.modules.charge.dao;

import java.util.List;

import com.langmy.terminal.common.entity.AnchorGroup;

public interface AnchorGroupDao extends BaseRuleDao<AnchorGroup>{

	/**
	 * 根据Name模块查询生效的长期收费组
	 * @param name
	 * @return
	 */
	List<AnchorGroup> findByNameContainingAndDelFlagFalse(String name);
}
