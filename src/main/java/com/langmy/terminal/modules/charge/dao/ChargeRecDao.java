package com.langmy.terminal.modules.charge.dao;

import java.util.List;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.ChargeRec;

public interface ChargeRecDao extends BaseDao<ChargeRec>{
	
	List<ChargeRec> findByAdminssionRecId(int id);
	
}
