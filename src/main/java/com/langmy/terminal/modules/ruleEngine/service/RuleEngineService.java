package com.langmy.terminal.modules.ruleEngine.service;

import java.text.ParseException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.modules.ruleEngine.chargeRule.Rule;
import com.langmy.terminal.modules.ruleEngine.dctRule.DctRuleGroupModel;
import com.langmy.terminal.modules.ruleEngine.fact.AdmissionRecModel;
import com.langmy.terminal.modules.ruleEngine.fact.ChargeResult;
import com.langmy.terminal.modules.ruleEngine.fact.UserModel;
import com.langmy.terminal.modules.ruleEngine.service.extend.RuleEngineExtend;
import com.langmy.terminal.modules.user.utils.UGChargeRuleUtils;
import com.langmy.terminal.modules.user.utils.UGroupUtils;

@Service
public class RuleEngineService {

	public static Logger logger = LoggerFactory.getLogger(RuleEngineService.class);
	public static int RENT_TYPE_TIME = 0;
	public static int RENT_TYPE_METER = 1;

	public static int DCT_TYPE_BIRTH = 0;
	public static int DCT_TYPE_HOLIDAY = 1;
	public static int DCT_TYPE_WEEKEND = 2;
	public static int DCT_TYPE_TIME = 3;
	public static int DCT_TYPE_DATE = 4;
	public static int DCT_TYPE_RECHARGE = 5;

	private KieServices service;
	private KieContainer container;//存放KieSession的容器
	private KieSession kieSession;

	/**
	 * 初始化规则引擎，新建一个有状态的KieSession
	 */
	public RuleEngineService() {
		if (service == null)
			service = KieServices.Factory.get();
		if (container == null)
			container = service.getKieClasspathContainer();
		
	}

	/**
	 * 执行规则
	 * @param rec 出入场记录
	 * @param user 用户信息
	 * @return
	 */
	public ChargeResult executeRules(AdmissionRecModel rec, UserModel user) {
		if(logger.isInfoEnabled())
			logger.info("规则引擎RuleEngineService executeRules方法运行提示{}","规则引擎算法运行开始----------------");
		ChargeResult result = new ChargeResult();
		Rule chargeRule = getChargeRuleByUserGroupId(user.getUserGroupId());
		if(chargeRule==null){
			logger.error("规则引擎RuleEngineService executeRules方法运行出错,错误原因:{}","收费规则为空");
			return null;
		}
		if(logger.isDebugEnabled()){
			logger.debug("规则引擎RuleEngineService executeRules方法运行提示{}","收费信息为:"+chargeRule.toString());
			logger.debug("规则引擎RuleEngineService executeRules方法运行提示{}","用户信息为:"+user.toString());
		}
		DctRuleGroupModel dctRuleGroup = getDctRuleGroupModelByGroupId(user.getUserGroupId());		
		if(dctRuleGroup==null||ListUtils.isNullOrEmpty(dctRuleGroup.getDctRuleModels())){
			if(logger.isInfoEnabled()){
				logger.info("规则引擎RuleEngineService executeRules方法运行提示:{}","用户组未绑定优惠规则组或者组中无优惠规则");
				logger.info("规则引擎RuleEngineService executeRules方法运行提示:{}","准备执行无优惠规则的规则引擎");
			}
			kieSession = container.newKieSession("ChargeRuleWithOutDctKS");
		}
		else{
			if(logger.isInfoEnabled())
				logger.info("规则引擎RuleEngineService executeRules方法运行提示:{}","准备执行含优惠规则的规则引擎,优惠规则组名为"+dctRuleGroup.getName());
			kieSession = container.newKieSession("ChargeRuleKS");
			kieSession.insert(dctRuleGroup);
		}
		kieSession.insert(chargeRule);
		kieSession.insert(rec);
		kieSession.insert(user);
		kieSession.insert(result);
		kieSession.fireAllRules();
		if(logger.isInfoEnabled()){
			logger.info("规则引擎RuleEngineService executeRules方法运行提示:{}","运行结果为"+result.toString());
			logger.info("----------------规则引擎RuleEngineService executeRules方法运行提示:{}","规则引擎算法运行结束----------------");
		}
		return result;
	}

	/**
	 * 根据用户组的ID获取相应的收费规则
	 * 
	 * @param id
	 * @return
	 */
	public static Rule getChargeRuleByUserGroupId(int id) {
		ChargeRule chargeRule = UGChargeRuleUtils.getChargeRuleByUGId(id);
		if(chargeRule==null){
			logger.error("用户组没有相应的收费规则，无法实现收费");
			return null;
		}
		Rule rule = RuleEngineExtend.convertToRuleByChargeRule(chargeRule);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(rule));
		return rule;
	}

	/**
	 * 根据用户组的编号获得优惠规则组
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	public static DctRuleGroupModel getDctRuleGroupModelByGroupId(int id){
		DctRuleGroupModel model = new DctRuleGroupModel();
		DctRuleGroup group = UGroupUtils.findDctRuleGroupByGroupId(id);
		if(group==null){
			logger.error("规则引擎RuleEngineService getDctRuleGroupModelByGroupId方法运行出错,错误原因{}","用户组未绑定优惠规则组");
			return model;
		}
		model = RuleEngineExtend.convertToDctRuleGroup(group);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(model));
		return model;
	}
}
