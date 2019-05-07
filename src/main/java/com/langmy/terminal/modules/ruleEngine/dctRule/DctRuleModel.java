package com.langmy.terminal.modules.ruleEngine.dctRule;

import java.io.Serializable;
import java.util.Date;

public class DctRuleModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4769654005438809917L;
	// 优惠规则类型
	public static int DCT_TYPE_BIRTH = 0;
	public static int DCT_TYPE_HOLIDAY = 1;
	public static int DCT_TYPE_WEEKEND = 2;
	public static int DCT_TYPE_TIME = 3;
	public static int DCT_TYPE_DATE = 4;
	public static int DCT_TYPE_RECHARGE = 5;

	// Weekend type
	public static int WEEKEND_TYPE_SAT = 0;
	public static int WEEKEND_TYPE_SUN = 1;
	public static int WEEKEND_TYPE_BOTH = 2;

	private int id;
	private String dctId; //优惠规则编号
	private String dctName; //优惠规则名称
	private int type; //类型
	private Date beginTime;//开始时间
	private Date endTime;//结束时间
	private Integer weekendType;//双休日类型
	private Date beginDate;//开始日期
	private Date endDate;//结束日期
	private Double dctPer;//优惠金额或者折扣
	private Integer dctType;//优惠类型(0:减免金额;1:打折;2:全免)
	private boolean delFlag;//删除标志位

	
	public DctRuleModel(){}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDctId() {
		return dctId;
	}


	public void setDctId(String dctId) {
		this.dctId = dctId;
	}


	public String getDctName() {
		return dctName;
	}


	public void setDctName(String dctName) {
		this.dctName = dctName;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public Date getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Integer getWeekendType() {
		return weekendType;
	}


	public void setWeekendType(Integer weekendType) {
		this.weekendType = weekendType;
	}


	public Date getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Double getDctPer() {
		return dctPer;
	}


	public void setDctPer(Double dctPer) {
		this.dctPer = dctPer;
	}


	public Integer getDctType() {
		return dctType;
	}


	public void setDctType(Integer dctType) {
		this.dctType = dctType;
	}


	public boolean isDelFlag() {
		return delFlag;
	}


	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

}
