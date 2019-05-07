package com.langmy.terminal.modules.ruleEngine.fact;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.modules.ruleEngine.dctRule.DctRuleGroupModel;

public class ChargeResult {
	
	private double amoutPay;//应付金额
	private double dctPay; //优惠金额
	private int chargeFlag;//0：正常收费进行了打折 ；1：正常收费未打折；2：在免费时间内 ；3：出场超时长；4：全免打折
	private double dctResult;//打折后的金额
	
	private List<DctRecModel> dctRecModel = Lists.newArrayList();
	private DctRuleGroupModel dctRuleGroupModel; 
	
	private boolean timeDctRuleExecuteFlag; //时间段优惠规则是否执行
	private boolean dateDctRuleExecuteFlag;//日期段优惠规则是否执行
	
	public double getAmoutPay() {
		return amoutPay;
	}
	public void setAmoutPay(double amoutPay) {
		this.amoutPay = amoutPay;
	}
	public double getDctPay() {
		return dctPay;
	}
	public void setDctPay(double dctPay) {
		this.dctPay = dctPay;
	}
	public double getDctResult() {
		return amoutPay-dctPay;
	}
	public void setDctResult(double dctResult) {
		this.dctResult = dctResult;
	}
	public DctRuleGroupModel getDctRuleGroupModel() {
		return dctRuleGroupModel;
	}
	public void setDctRuleGroupModel(DctRuleGroupModel dctRuleGroupModel) {
		this.dctRuleGroupModel = dctRuleGroupModel;
	}
	public boolean isTimeDctRuleExecuteFlag() {
		return timeDctRuleExecuteFlag;
	}
	public void setTimeDctRuleExecuteFlag(boolean timeDctRuleExecuteFlag) {
		this.timeDctRuleExecuteFlag = timeDctRuleExecuteFlag;
	}
	public boolean isDateDctRuleExecuteFlag() {
		return dateDctRuleExecuteFlag;
	}
	public void setDateDctRuleExecuteFlag(boolean dateDctRuleExecuteFlag) {
		this.dateDctRuleExecuteFlag = dateDctRuleExecuteFlag;
	}
	public List<DctRecModel> getDctRecModel() {
		return dctRecModel;
	}
	public void setDctRecModel(List<DctRecModel> dctRecModel) {
		this.dctRecModel = dctRecModel;
	}
	@Override
	public String toString() {
		return "收费结果 [应付金额=" + amoutPay + ", 优惠金额=" + dctPay
				+ ", 打折后的金额=" + getDctResult() + ", 优惠生效记录如下=" + dctRecModel
				+",chargeFlag=尚未写入]";
	}
	public int getChargeFlag() {
		return chargeFlag;
	}
	public void setChargeFlag(int chargeFlag) {
		this.chargeFlag = chargeFlag;
	}
	
	/*public List<TimeChargeRule> getEffectTimeChargeRule() {
		return effectTimeChargeRule;
	}
	public void setEffectTimeChargeRule(List<TimeChargeRule> effectTimeChargeRule) {
		this.effectTimeChargeRule = effectTimeChargeRule;
	}
	public List<MeterChargeRule> getEffectMeterChargeRule() {
		return effectMeterChargeRule;
	}
	public void setEffectMeterChargeRule(List<MeterChargeRule> effectMeterChargeRule) {
		this.effectMeterChargeRule = effectMeterChargeRule;
	}*/
	
}
