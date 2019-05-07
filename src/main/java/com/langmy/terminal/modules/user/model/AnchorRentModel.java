package com.langmy.terminal.modules.user.model;

import com.langmy.terminal.common.model.BaseModel;

public class AnchorRentModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8720454785407020693L;
	private String anchorRentId;
	private String name;
	private double fee;
	private int month;
	private Double addFee;
	private boolean historyFlag;
	
	public String getAnchorRentId() {
		return anchorRentId;
	}
	public void setAnchorRentId(String anchorRentId) {
		this.anchorRentId = anchorRentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Double getAddFee() {
		return addFee;
	}
	public void setAddFee(Double addFee) {
		this.addFee = addFee;
	}
	public boolean isHistoryFlag() {
		return historyFlag;
	}
	public void setHistoryFlag(boolean historyFlag) {
		this.historyFlag = historyFlag;
	}
	
}
