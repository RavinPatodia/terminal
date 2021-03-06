package com.langmy.terminal.common.entity;

// Generated 2015-2-3 18:18:41 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.langmy.terminal.common.persistence.DetailRule;
import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * TimeCharge generated by hbm2java
 */
@Entity
@Table(name = "time_charge", catalog = "cloud")
public class TimeCharge extends GenIdEntityImpl implements DetailRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8435239910171823901L;
	private String timeChargeId; // 计时收费ID
	private int billingModel; // 计费模式(标准模式0，时段模式1，昼夜模式2，昼夜不分开3)
	private Integer timeOneLen; // 首时段长度
	private Date beginDay; // 白天begin
	private Date endDay; // 白天end
	private int freeTime; // 计时卡免费时间/分
	private boolean freeTimeFlag;// 计算免费时间标志位
	private double carUnitFee; // 白天小车单价（元）
	private int carUnitTime; // 白天小车单位时间（分钟）
	private double truckUnitFee; // 白天大车单价（元）
	private int truckUnitTime; // 白天大车单位时间（分钟）
	private Double nightCarUnitFee; // 夜晚小车单价（元）
	private Integer nightCarUnitTime; // 夜晚小车单位时间（分钟）
	private Double nightTruckUnitFee; // 夜晚大车单价（元） 
	private Integer nightTruckUnitTime; // 夜晚大车单位时间（分钟）
	private int timeout; // 出场超时长/分
	private int timeHandle; // 对不足一单位时间的处理（记为1时间单位/舍）
	private int deciHandle; // 对金额小树部分的处理（记为1元/舍/不处理） 
	private boolean delFlag; // 删除标志位
	private double dailyCharge;// 每日收费上限

	public TimeCharge() {
	}

	public TimeCharge(String timeChargeId, int billingModel, int freeTime,
			double carUnitFee, int carUnitTime, double truckUnitFee,
			int truckUnitTime, int timeout, int timeHandle, int deciHandle) {
		this.timeChargeId = timeChargeId;
		this.billingModel = billingModel;
		this.freeTime = freeTime;
		this.carUnitFee = carUnitFee;
		this.carUnitTime = carUnitTime;
		this.truckUnitFee = truckUnitFee;
		this.truckUnitTime = truckUnitTime;
		this.timeout = timeout;
		this.timeHandle = timeHandle;
		this.deciHandle = deciHandle;
	}

	public TimeCharge(String timeChargeId, int billingModel,
			Integer timeOneLen, Date beginDay, Date endDay, int freeTime,
			double carUnitFee, int carUnitTime, double truckUnitFee,
			int truckUnitTime, Double nightCarUnitFee,
			Integer nightCarUnitTime, Double nightTruckUnitFee,
			Integer nightTruckUnitTime, int timeout, int timeHandle,
			int deciHandle) {
		this.timeChargeId = timeChargeId;
		this.billingModel = billingModel;
		this.timeOneLen = timeOneLen;
		this.beginDay = beginDay;
		this.endDay = endDay;
		this.freeTime = freeTime;
		this.carUnitFee = carUnitFee;
		this.carUnitTime = carUnitTime;
		this.truckUnitFee = truckUnitFee;
		this.truckUnitTime = truckUnitTime;
		this.nightCarUnitFee = nightCarUnitFee;
		this.nightCarUnitTime = nightCarUnitTime;
		this.nightTruckUnitFee = nightTruckUnitFee;
		this.nightTruckUnitTime = nightTruckUnitTime;
		this.timeout = timeout;
		this.timeHandle = timeHandle;
		this.deciHandle = deciHandle;
	}

	@Column(name = "time_charge_id", length = 30)
	public String getTimeChargeId() {
		return this.timeChargeId;
	}

	public void setTimeChargeId(String timeChargeId) {
		this.timeChargeId = timeChargeId;
	}

	@Column(name = "billing_model", nullable = false)
	public int getBillingModel() {
		return this.billingModel;
	}

	public void setBillingModel(int billingModel) {
		this.billingModel = billingModel;
	}

	@Column(name = "time_one_len")
	public Integer getTimeOneLen() {
		return this.timeOneLen;
	}

	public void setTimeOneLen(Integer timeOneLen) {
		this.timeOneLen = timeOneLen;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "begin_day", length = 8)
	public Date getBeginDay() {
		return this.beginDay;
	}

	public void setBeginDay(Date beginDay) {
		this.beginDay = beginDay;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "end_day", length = 8)
	public Date getEndDay() {
		return this.endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	@Column(name = "free_time", nullable = false)
	public int getFreeTime() {
		return this.freeTime;
	}

	public void setFreeTime(int freeTime) {
		this.freeTime = freeTime;
	}

	@Column(name = "car_unit_fee", nullable = false, precision = 10)
	public double getCarUnitFee() {
		return this.carUnitFee;
	}

	public void setCarUnitFee(double carUnitFee) {
		this.carUnitFee = carUnitFee;
	}

	@Column(name = "car_unit_time", nullable = false)
	public int getCarUnitTime() {
		return this.carUnitTime;
	}

	public void setCarUnitTime(int carUnitTime) {
		this.carUnitTime = carUnitTime;
	}

	@Column(name = "truck_unit_fee", nullable = false, precision = 10)
	public double getTruckUnitFee() {
		return this.truckUnitFee;
	}

	public void setTruckUnitFee(double truckUnitFee) {
		this.truckUnitFee = truckUnitFee;
	}

	@Column(name = "truck_unit_time", nullable = false)
	public int getTruckUnitTime() {
		return this.truckUnitTime;
	}

	public void setTruckUnitTime(int truckUnitTime) {
		this.truckUnitTime = truckUnitTime;
	}

	@Column(name = "night_car_unit_fee", precision = 10)
	public Double getNightCarUnitFee() {
		return this.nightCarUnitFee;
	}

	public void setNightCarUnitFee(Double nightCarUnitFee) {
		this.nightCarUnitFee = nightCarUnitFee;
	}

	@Column(name = "night_car_unit_time")
	public Integer getNightCarUnitTime() {
		return this.nightCarUnitTime;
	}

	public void setNightCarUnitTime(Integer nightCarUnitTime) {
		this.nightCarUnitTime = nightCarUnitTime;
	}

	@Column(name = "night_truck_unit_fee", precision = 10)
	public Double getNightTruckUnitFee() {
		return this.nightTruckUnitFee;
	}

	public void setNightTruckUnitFee(Double nightTruckUnitFee) {
		this.nightTruckUnitFee = nightTruckUnitFee;
	}

	@Column(name = "night_truck_unit_time")
	public Integer getNightTruckUnitTime() {
		return this.nightTruckUnitTime;
	}

	public void setNightTruckUnitTime(Integer nightTruckUnitTime) {
		this.nightTruckUnitTime = nightTruckUnitTime;
	}

	@Column(name = "timeout", nullable = false)
	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Column(name = "time_handle", nullable = false)
	public int getTimeHandle() {
		return this.timeHandle;
	}

	public void setTimeHandle(int timeHandle) {
		this.timeHandle = timeHandle;
	}

	@Column(name = "deci_handle", nullable = false)
	public int getDeciHandle() {
		return this.deciHandle;
	}

	public void setDeciHandle(int deciHandle) {
		this.deciHandle = deciHandle;
	}

	@Override
	@Transient
	public String getRuleId() {
		return timeChargeId;
	}

	@Column(name = "del_flag", nullable = false)
	public boolean isDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "daily_charge", nullable = false)
	public double getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	@Column(name = "free_time_flag", nullable = false)
	public boolean isFreeTimeFlag() {
		return freeTimeFlag;
	}

	public void setFreeTimeFlag(boolean freeTimeFlag) {
		this.freeTimeFlag = freeTimeFlag;
	}
}
