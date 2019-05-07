package com.langmy.terminal.modules.report.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

public class RechargeRecModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 805573424796525984L;
	private String operName;
	private String operOperName;
	private String userName;
	private String userUacc;
	private String rechargeType;
	private int rechargeWay;
	private double money;
	private String ruleName;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date rechargeTime;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date startTime;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date endTime;

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public int getRechargeWay() {
		return rechargeWay;
	}

	public void setRechargeWay(int rechargeWay) {
		this.rechargeWay = rechargeWay;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperOperName() {
		return operOperName;
	}

	public void setOperOperName(String operOperName) {
		this.operOperName = operOperName;
	}

	public String getUserUacc() {
		return userUacc;
	}

	public void setUserUacc(String userUacc) {
		this.userUacc = userUacc;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

}
