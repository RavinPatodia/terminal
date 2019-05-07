package com.langmy.terminal.modules.psp.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.modules.charge.model.AnchorRentModel;

/**
 * 收费记录的model
 * 
 * @author MC
 */
public class RechargeRecModel extends BaseModel {
	private static final long serialVersionUID = 805573424796525984L;
	private String rechargeType;
	private int rechargeWay;
	private double money;

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date beginDate;

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date endDate;

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date rechargeTime;

	private int operName;
	private int uacc;
	private int rentRuleId;
	private String pspId;
	private String licensePlate;

	private Integer anchorRentPK;
	private List<AnchorRentModel> anchorRentModels;

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
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public int getRentRuleId() {
		return rentRuleId;
	}

	public void setRentRuleId(int rentRuleId) {
		this.rentRuleId = rentRuleId;
	}

	public int getOperName() {
		return operName;
	}

	public void setOperName(int operName) {
		this.operName = operName;
	}

	public int getUacc() {
		return uacc;
	}

	public void setUacc(int uacc) {
		this.uacc = uacc;
	}

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	public Integer getAnchorRentPK() {
		return anchorRentPK;
	}

	public void setAnchorRentPK(Integer anchorRentPK) {
		this.anchorRentPK = anchorRentPK;
	}

	public List<AnchorRentModel> getAnchorRentModels() {
		return anchorRentModels;
	}

	public void setAnchorRentModels(List<AnchorRentModel> anchorRentModels) {
		this.anchorRentModels = anchorRentModels;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

}
