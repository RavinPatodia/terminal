package com.langmy.terminal.modules.charge.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

public class ChargeRecModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6945605174918424565L;
	
	private CarModel carModel = new CarModel();
	private List<ChargeRuleModel> chargeRuleModels;
	private DctRuleGroupModel dctRuleGroupModel = new DctRuleGroupModel();
	private Integer operPK;
	private String operName;
	
	private int payType;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date payTime;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date payTimeFrom;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date payTimeTo;
	private double dctPay;
	private double amoutPay;
	private double actualPay;
	
	public Integer getOperPK() {
		return operPK;
	}
	public void setOperPK(Integer operPK) {
		this.operPK = operPK;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public double getDctPay() {
		return dctPay;
	}
	public void setDctPay(double dctPay) {
		this.dctPay = dctPay;
	}
	public double getAmoutPay() {
		return amoutPay;
	}
	public void setAmoutPay(double amoutPay) {
		this.amoutPay = amoutPay;
	}
	public double getActualPay() {
		return actualPay;
	}
	public void setActualPay(double actualPay) {
		this.actualPay = actualPay;
	}
	public CarModel getCarModel() {
		return carModel;
	}
	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}
	public List<ChargeRuleModel> getChargeRuleModels() {
		return chargeRuleModels;
	}
	public void setChargeRuleModels(List<ChargeRuleModel> chargeRuleModels) {
		this.chargeRuleModels = chargeRuleModels;
	}
	public DctRuleGroupModel getDctRuleGroupModel() {
		return dctRuleGroupModel;
	}
	public void setDctRuleGroupModel(DctRuleGroupModel dctRuleGroupModel) {
		this.dctRuleGroupModel = dctRuleGroupModel;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTimeFrom() {
		return payTimeFrom;
	}
	public void setPayTimeFrom(Date payTimeFrom) {
		this.payTimeFrom = payTimeFrom;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTimeTo() {
		return payTimeTo;
	}
	public void setPayTimeTo(Date payTimeTo) {
		this.payTimeTo = payTimeTo;
	}
	
}
