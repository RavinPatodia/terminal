package com.langmy.terminal.modules.charge.service;

import com.langmy.terminal.common.persistence.DetailRule;
import com.langmy.terminal.common.persistence.RuleEntity;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.BaseRuleDao;

/**
 * 计时、计次、长期收费组工厂类
 * @author lzy
 *
 */
public class DetailRuleFactory {
	
	private static BaseRuleDao<RuleEntity> baseRuleDao;
	
	@SuppressWarnings("unchecked")
	public static DetailRule findDetailRuleByIdAndRentType(final int baseRuleId,final int rentType) {
		if(rentType==0){
			baseRuleDao = (BaseRuleDao<RuleEntity>)SpringContextHolder.getBean("timeChargeDao");
		}
		else if(rentType==1){
			baseRuleDao = (BaseRuleDao<RuleEntity>)SpringContextHolder.getBean("meterChargeDao");
		}
		else if(rentType==2){
			baseRuleDao = (BaseRuleDao<RuleEntity>)SpringContextHolder.getBean("anchorGroupDao");
		}
		DetailRule rule = (DetailRule) baseRuleDao.findOne(baseRuleId);
		return rule;
	}
}
