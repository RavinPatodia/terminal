package com.langmy.terminal.modules.ruleEngine.fact;

import java.util.Date;

public class AdmissionRecModel {
	
	public static int CAR_TYPE_CAR = 0 ;
	public static int CAR_TYPE_TRUCK = 1;
	
	private Date inTime;//入场时间

	private Date outTime;//出场时间
	private int carType; //车辆类型 0：小车 1：大车
	
	private Date payTime;//付款时间
	private boolean payFlag;//付款标志位
	

	public AdmissionRecModel(Date inTime, Date outTime, int carType, Date payTime,
			boolean payFlag) {
		super();
		this.inTime = inTime;
		this.outTime = outTime;
		this.carType = carType;
		this.payTime = payTime;
		this.payFlag = payFlag;
	}
	
	public AdmissionRecModel(Date inTime, Date outTime, int carType,
			boolean payFlag) {
		super();
		this.inTime = inTime;
		this.outTime = outTime;
		this.carType = carType;
		this.payFlag = payFlag;
	}
	
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public AdmissionRecModel(Date inTime, Date outTime) {
		super();
		this.inTime = inTime;
		this.outTime = outTime;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public boolean isPayFlag() {
		return payFlag;
	}

	public void setPayFlag(boolean payFlag) {
		this.payFlag = payFlag;
	}
	
}
