package com.langmy.terminal.modules.report.dao;

import org.springframework.data.jpa.repository.Query;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.ChargeRec;

/**
 * 操作员结账记录Dao
 * 
 * @author ZZD
 *
 */
public interface CharRecDao extends BaseDao<ChargeRec> {
	@Query(value = "select MAX(DATE_FORMAT(c.pay_time,'%Y'))from charge_rec c;", nativeQuery = true)
	public String getMaxYear();
	
	@Query(value = "select MIN(DATE_FORMAT(c.pay_time,'%Y'))from charge_rec c;", nativeQuery = true)
	public String getMinYear();
}
