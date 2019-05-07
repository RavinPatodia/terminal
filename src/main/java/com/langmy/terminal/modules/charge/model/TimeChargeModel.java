package com.langmy.terminal.modules.charge.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

public class TimeChargeModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5625399517334327365L;
	private String timeChargeId;
	private String name;
	private int billingModel;
	private Integer timeOneLen;
	private Date beginDay;
	private Date endDay;
	private int freeTime;
	private double carUnitFee;
	private int carUnitTime;
	private double truckUnitFee;
	private int truckUnitTime;
	private Double nightCarUnitFee;
	private Integer nightCarUnitTime;
	private Double nightTruckUnitFee;
	private Integer nightTruckUnitTime;
	private int timeout;
	private int timeHandle;
	private int deciHandle;
	private boolean historyFlag;
	private BaseSearchModel bsm;
	private double dailyCharge;//每日收费上限 

	
	public BaseSearchModel getBsm() {
		return bsm;
	}

	public void setBsm(BaseSearchModel bsm) {
		this.bsm = bsm;
	}
	
	public String getTimeChargeId() {
		return timeChargeId;
	}

	public void setTimeChargeId(String timeChargeId) {
		this.timeChargeId = timeChargeId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTimeOneLen() {
		return timeOneLen;
	}

	public void setTimeOneLen(Integer timeOneLen) {
		this.timeOneLen = timeOneLen;
	}

	@DateTimeFormat(iso=ISO.DATE_TIME,pattern="HH:mm")
	@JSONField(format = "HH:mm")
	public Date getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(Date beginDay) {
		this.beginDay = beginDay;
	}

	@DateTimeFormat(iso=ISO.DATE_TIME,pattern="HH:mm")
	@JSONField(format = "HH:mm")
	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public int getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(int freeTime) {
		this.freeTime = freeTime;
	}

	public double getCarUnitFee() {
		return carUnitFee;
	}

	public void setCarUnitFee(double carUnitFee) {
		this.carUnitFee = carUnitFee;
	}

	public int getCarUnitTime() {
		return carUnitTime;
	}

	public void setCarUnitTime(int carUnitTime) {
		this.carUnitTime = carUnitTime;
	}

	public double getTruckUnitFee() {
		return truckUnitFee;
	}

	public void setTruckUnitFee(double truckUnitFee) {
		this.truckUnitFee = truckUnitFee;
	}

	public int getTruckUnitTime() {
		return truckUnitTime;
	}

	public void setTruckUnitTime(int truckUnitTime) {
		this.truckUnitTime = truckUnitTime;
	}

	public Double getNightCarUnitFee() {
		return nightCarUnitFee;
	}

	public void setNightCarUnitFee(Double nightCarUnitFee) {
		this.nightCarUnitFee = nightCarUnitFee;
	}

	public Integer getNightCarUnitTime() {
		return nightCarUnitTime;
	}

	public void setNightCarUnitTime(Integer nightCarUnitTime) {
		this.nightCarUnitTime = nightCarUnitTime;
	}

	public Double getNightTruckUnitFee() {
		return nightTruckUnitFee;
	}

	public void setNightTruckUnitFee(Double nightTruckUnitFee) {
		this.nightTruckUnitFee = nightTruckUnitFee;
	}

	public Integer getNightTruckUnitTime() {
		return nightTruckUnitTime;
	}

	public void setNightTruckUnitTime(Integer nightTruckUnitTime) {
		this.nightTruckUnitTime = nightTruckUnitTime;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getTimeHandle() {
		return timeHandle;
	}

	public void setTimeHandle(int timeHandle) {
		this.timeHandle = timeHandle;
	}

	public int getDeciHandle() {
		return deciHandle;
	}

	public void setDeciHandle(int deciHandle) {
		this.deciHandle = deciHandle;
	}

	public boolean getHistoryFlag() {
		return historyFlag;
	}

	public void setHistoryFlag(boolean historyFlag) {
		this.historyFlag = historyFlag;
	}

	public int getBillingModel() {
		return billingModel;
	}

	public void setBillingModel(int billingModel) {
		this.billingModel = billingModel;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

}
