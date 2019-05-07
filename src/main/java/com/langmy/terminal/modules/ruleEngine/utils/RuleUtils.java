package com.langmy.terminal.modules.ruleEngine.utils;

import org.springframework.stereotype.Service;

import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.ruleEngine.fact.AdmissionRecModel;
import com.langmy.terminal.modules.ruleEngine.fact.ChargeResult;
import com.langmy.terminal.modules.ruleEngine.fact.UserModel;
import com.langmy.terminal.modules.ruleEngine.service.RuleEngineService;

@Service
public class RuleUtils {
	
	private static RuleEngineService ruleEngine = SpringContextHolder.getBean(RuleEngineService.class);
	
	/**
	 * 执行规则引擎，返回执行结果
	 * @param rec 出入场记录
	 * @param user 用户信息
	 * @return 执行结果
	 */
	public static ChargeResult executeRules(AdmissionRecModel rec,UserModel user){
		return ruleEngine.executeRules(rec, user);
	}
}
