package com.langmy.terminal.modules.charge.model;

import com.langmy.terminal.common.model.BaseModel;



public class ChargeRuleModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 633795817484010944L;

	private String rentRuleId;//收费规则编号
	private String ruleName;//收费规则名称
	private Integer baseRuleId; //计时、计次、长期组的外键
	private String baseRuleName;//计时、计次、长期组名称
	private Integer rentType; //租赁方式
	private boolean delFlag;//删除标志位

	private TimeChargeModel timeChargeModel;//计时收费规则，用于接收前端传来的计时规则属性
	private MeterChargeModel meterChargeModel;//计次收费规则，用于接收前端传来的计次规则属性

	public String getRentRuleId() {
		return rentRuleId;
	}

	public void setRentRuleId(String rentRuleId) {
		this.rentRuleId = rentRuleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public Integer getBaseRuleId() {
		return baseRuleId;
	}

	public void setBaseRuleId(Integer baseRuleId) {
		this.baseRuleId = baseRuleId;
	}

	public Integer getRentType() {
		return rentType;
	}

	public void setRentType(Integer rentType) {
		this.rentType = rentType;
	}
	public String getBaseRuleName() {
		return baseRuleName;
	}

	public void setBaseRuleName(String baseRuleName) {
		this.baseRuleName = baseRuleName;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public TimeChargeModel getTimeChargeModel() {
		return timeChargeModel;
	}

	public void setTimeChargeModel(TimeChargeModel timeChargeModel) {
		this.timeChargeModel = timeChargeModel;
	}

	public MeterChargeModel getMeterChargeModel() {
		return meterChargeModel;
	}

	public void setMeterChargeModel(MeterChargeModel meterChargeModel) {
		this.meterChargeModel = meterChargeModel;
	}

}
