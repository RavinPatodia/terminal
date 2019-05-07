package com.langmy.terminal.modules.report.utils;

import org.springframework.transaction.annotation.Transactional;

import com.langmy.terminal.common.entity.RechargeRec;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.report.dao.RecharRecDao;

public class RechargeRecUtils {
	private static RecharRecDao recharRecDao = (RecharRecDao) SpringContextHolder
			.getBean(RecharRecDao.class);

	@Transactional(readOnly = false)
	public static RechargeRec save(RechargeRec rec) {
		return recharRecDao.save(rec);
	}

}
