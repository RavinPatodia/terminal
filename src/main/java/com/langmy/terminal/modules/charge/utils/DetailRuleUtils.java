package com.langmy.terminal.modules.charge.utils;

import org.springframework.stereotype.Service;

import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.persistence.DetailRule;
import com.langmy.terminal.modules.charge.service.DetailRuleFactory;

/**
 * 收费规则详细规则计时、计次、长期收费组的接口类
 * @author lzy
 */
@Service
public class DetailRuleUtils {

	/**
	 * 根据传入的收费规则找到相应的计时、计次或者长期规则组
	 * 
	 * @param chargeRule
	 * @return
	 */
	public static DetailRule findDetailRuleByIdAndRentType(ChargeRule chargeRule) {
		return DetailRuleFactory.findDetailRuleByIdAndRentType(chargeRule.getBaseRuleId(),
				chargeRule.getRentType());
	}

}
