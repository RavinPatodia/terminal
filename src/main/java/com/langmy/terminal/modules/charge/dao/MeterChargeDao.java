package com.langmy.terminal.modules.charge.dao;

import java.util.List;

import com.langmy.terminal.common.entity.MeterCharge;

public interface MeterChargeDao extends BaseRuleDao<MeterCharge>{

	List<MeterCharge> findByNameContainingAndDelFlagFalse(String name);
}
