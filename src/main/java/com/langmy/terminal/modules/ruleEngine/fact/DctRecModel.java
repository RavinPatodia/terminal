package com.langmy.terminal.modules.ruleEngine.fact;

import com.langmy.terminal.common.model.BaseModel;

/**
 * @author lzy
 * 2015年6月2日
 */
public class DctRecModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5622320896767046822L;
	
	private String reason; //优惠原因
	private int dctType;//0:减免金额;1:打折;2:全免
	private double dctPer;//优惠折扣或者减免金额
	
	public DctRecModel(String reason,double dctPer){
		this.reason = reason;
		this.dctPer = dctPer;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public double getDctPer() {
		return dctPer;
	}
	public void setDctPer(double dctPer) {
		this.dctPer = dctPer;
	}

	public int getDctType() {
		return dctType;
	}

	public void setDctType(int dctType) {
		this.dctType = dctType;
	}
	
}
