package com.langmy.terminal.modules.charge.dao;

import java.util.List;

import com.langmy.terminal.common.entity.AnchorRent;

public interface AnchorRentDao extends BaseRuleDao<AnchorRent>{
	
	List<AnchorRent> findByIdIn(List<Integer> ids);
	
	public List<AnchorRent> findByDelFlagFalse();

	List<AnchorRent> findByDelFlagFalseAndIdNotIn(List<Integer> ids);
}
