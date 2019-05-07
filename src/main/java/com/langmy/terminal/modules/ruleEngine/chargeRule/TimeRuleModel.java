package com.langmy.terminal.modules.ruleEngine.chargeRule;

import java.util.Date;

import com.langmy.terminal.common.config.Constant;

public class TimeRuleModel extends Rule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7532479881497697253L;

	public static int BILLING_MODEL_STANDARD = 0;
	public static int BILLING_MODEL_PERIOD = 1;
	public static int BILLING_MODEL_DAY_NIGHT = 2;
	public static int BILLING_MODEL_DAY_NIGHT2 = 3;
	
	public static int DECIHANDLE_ROUNDING_UP = 0;
	public static int DECIHANDLE_ABANDON = 1;
	public static int DECIHANDLE_NONE = 2;

	public static int TIMEHANDLE_ROUNDING_UP = 0;
	public static int TIMEHANDLE_ABANDON = 1;

	private int billingModel; //计费模式(标准模式0，时段模式1，昼夜模式2，昼夜不分开3)
	private Integer timeOneLen; //时段一长度
	private Date beginDay; //白天begin
	private Date endDay; //白天end
	private boolean freeTimeFlag;// 计算免费时间标志位
	private double carUnitFee; //白天小车单价（元）
	private int carUnitTime; //白天小车单位时间（分钟）
	private double truckUnitFee; //白天大车单价（元）
	private int truckUnitTime; //白天大车单位时间（分钟）
	private Double nightCarUnitFee; //夜晚小车单价（元）
	private Integer nightCarUnitTime; //夜晚小车单位时间（分钟）
	private Double nightTruckUnitFee; //夜晚大车单价（元） 
	private Integer nightTruckUnitTime; //夜晚大车单位时间（分钟） 
	private int timeout; //出场超时长/分 
	private int timeHandle;//对不足一单位时间的处理（记为1时间单位/舍）
	private int deciHandle;//对金额小树部分的处理（记为1元/舍/不处理） 
	private double dailyCharge;//每日收费上限
	
	public void setDailyCharge(double dailyCharge){
		this.dailyCharge=dailyCharge;
	}
	
	public double getDailyCharge(){
		return dailyCharge;
	}
	public int getTimeHandle() {
		return timeHandle;
	}

	public void setTimeHandle(int timeHandle) {
		this.timeHandle = timeHandle;
	}

	public int getBillingModel() {
		return billingModel;
	}

	public void setBillingModel(int billingModel) {
		this.billingModel = billingModel;
	}

	public Integer getTimeOneLen() {
		return timeOneLen;
	}

	public void setTimeOneLen(Integer timeOneLen) {
		this.timeOneLen = timeOneLen;
	}

	public Date getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(Date beginDay) {
		this.beginDay = beginDay;
	}

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

	public int getDeciHandle() {
		return deciHandle;
	}

	public void setDeciHandle(int deciHandle) {
		this.deciHandle = deciHandle;
	}

	public TimeRuleModel(int id,String name,int billingModel, int freeTime, double carUnitFee, int carUnitTime,
			double truckUnitFee, int truckUnitTime, int timeout, int timeHandle, int deciHandle) {
		super();
		this.id=id;
		this.name=name;
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

	public TimeRuleModel(int id,String name,int billingModel, Integer timeOneLen, int freeTime, double carUnitFee,
			int carUnitTime, double truckUnitFee, int truckUnitTime, Double nightCarUnitFee,
			Integer nightCarUnitTime, Double nightTruckUnitFee, Integer nightTruckUnitTime,
			int timeout, int timeHandle, int deciHandle) {
		super();
		this.id=id;
		this.name=name;
		this.billingModel = billingModel;
		this.timeOneLen = timeOneLen;
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

	public TimeRuleModel(int id,String name,int billingModel, Date beginDay, Date endDay, int freeTime,
			double carUnitFee, int carUnitTime, double truckUnitFee, int truckUnitTime,
			Double nightCarUnitFee, Integer nightCarUnitTime, Double nightTruckUnitFee,
			Integer nightTruckUnitTime, int timeout, int timeHandle, int deciHandle) {
		super();
		this.id=id;
		this.name=name;
		this.billingModel = billingModel;
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
	
	public TimeRuleModel(){}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(billingModel==Constant.TimeChargeType.BILLING_MODEL_STANDARD.getValue())
			builder.append("计时收费模型 [收费模式为=").append("标准")
			.append(", 小车单位时间收费=").append(carUnitFee)
			.append(", 小车单位时间=").append(carUnitTime)
			.append(", 大车单位时间收费=").append(truckUnitFee)
			.append(", 大车单位时间=").append(truckUnitTime);
		else if(billingModel==Constant.TimeChargeType.BILLING_MODEL_PERIOD.getValue())
			builder.append("计时收费模型 [收费模式为=").append("时段")
					.append(", 首时段长度=").append(timeOneLen)
					.append(", 首时段小车收费=").append(carUnitFee)
					.append(", 首时段大车收费=").append(truckUnitTime)
					.append(", 后续小车单位时间收费=").append(nightCarUnitFee)
					.append(", 后续小车单位时间=").append(nightCarUnitTime)
					.append(", 后续大车单位时间收费=").append(nightTruckUnitFee)
					.append(", 后续大车单位时间=").append(nightTruckUnitTime);
		else if (billingModel == Constant.TimeChargeType.BILLING_MODEL_DAY_NIGHT
				.getValue()
				|| billingModel == Constant.TimeChargeType.BILLING_MODEL_DAY_NIGHT2
						.getValue())
			builder.append("计时收费模型 [收费模式为=").append(billingModel)
					.append(", 白天开始时间=").append(beginDay).append(", 白天结束时间=")
					.append(endDay).append(", 白天小车单位时间收费=").append(carUnitFee)
					.append(", 白天小车单位时间=").append(carUnitTime)
					.append(", 白天大车单位时间收费=").append(truckUnitFee)
					.append(", 白天大车单位时间=").append(truckUnitTime)
					.append(", 夜晚小车单位时间收费=").append(nightCarUnitFee)
					.append(", 夜晚小车单位时间=").append(nightCarUnitTime)
					.append(", 夜晚大车单位时间收费=").append(nightTruckUnitFee)
					.append(", 夜晚大车单位时间=").append(nightTruckUnitTime);
		builder.append(", 出场超时长=").append(timeout).append(", 时间处理模式=")
		.append(timeHandle).append(", 金钱处理模式=").append(deciHandle)
		.append(", 每日最高收费上限=").append(dailyCharge).append("]");
		return builder.toString();
	}

	public boolean isFreeTimeFlag() {
		return freeTimeFlag;
	}

	public void setFreeTimeFlag(boolean freeTimeFlag) {
		this.freeTimeFlag = freeTimeFlag;
	}
}
