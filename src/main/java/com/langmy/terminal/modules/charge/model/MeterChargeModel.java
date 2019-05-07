package com.langmy.terminal.modules.charge.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;



public class MeterChargeModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6437443364986102618L;
	private String meterChargeId;
	private String name;
	private int meteringFree;
	private double carMetering;
	private double truckMetering;
	private boolean historyFlag;
	private BaseSearchModel bsm;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	protected Date createTime;// 创建日期

	
	public BaseSearchModel getBsm() {
		return bsm;
	}

	public void setBsm(BaseSearchModel bsm) {
		this.bsm = bsm;
	}
	public String getMeterChargeId() {
		return meterChargeId;
	}

	public void setMeterChargeId(String meterChargeId) {
		this.meterChargeId = meterChargeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMeteringFree() {
		return meteringFree;
	}

	public void setMeteringFree(int meteringFree) {
		this.meteringFree = meteringFree;
	}

	public double getCarMetering() {
		return carMetering;
	}

	public void setCarMetering(double carMetering) {
		this.carMetering = carMetering;
	}

	public double getTruckMetering() {
		return truckMetering;
	}

	public void setTruckMetering(double truckMetering) {
		this.truckMetering = truckMetering;
	}

	public boolean getHistoryFlag() {
		return historyFlag;
	}

	public void setHistoryFlag(boolean historyFlag) {
		this.historyFlag = historyFlag;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
