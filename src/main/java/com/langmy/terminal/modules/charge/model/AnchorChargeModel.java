package com.langmy.terminal.modules.charge.model;

import com.langmy.terminal.common.model.BaseModel;

/**
 * 长期收费规则
 * 
 * @author lzy
 *
 */
public class AnchorChargeModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8171206252910819977L;

	private String monthPay; // 收费组编号
	private String quarterPay; // 收费组名称
	private String yearPay; // 收费组描述
	private boolean delFlag; // 删除标志

	public String getMonthPay() {
		return monthPay;
	}

	public void setMonthPay(String monthPay) {
		this.monthPay = monthPay;
	}

	public String getQuarterPay() {
		return quarterPay;
	}

	public void setQuarterPay(String quarterPay) {
		this.quarterPay = quarterPay;
	}

	public String getYearPay() {
		return yearPay;
	}

	public void setYearPay(String yearPay) {
		this.yearPay = yearPay;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

}
