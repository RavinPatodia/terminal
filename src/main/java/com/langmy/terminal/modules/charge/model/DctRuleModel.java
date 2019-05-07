package com.langmy.terminal.modules.charge.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

public class DctRuleModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 523573171359040690L;
	private String dctId;
	private String dctName;
	
	private int type;
	private Date beginTime;
	private Date endTime;
	
	private Integer weekendType;
	private Date beginDate;
	private Date endDate;
	private Double dctPer;
	/**
	 * 优惠类型 0：减免金额 1：折扣 2：全免
	 */
	private int dctType;
	

	
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

	@NumberFormat
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@JSONField(format = "HH:mm:ss")
	@DateTimeFormat(iso=ISO.DATE_TIME,pattern="HH:mm")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@JSONField(format = "HH:mm:ss")
	@DateTimeFormat(iso=ISO.DATE_TIME,pattern="HH:mm")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@NumberFormat
	public Integer getWeekendType() {
		return weekendType;
	}

	public void setWeekendType(Integer weekendType) {
		this.weekendType = weekendType;
	}

	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@NumberFormat(style=Style.PERCENT)
	public Double getDctPer() {
		return dctPer;
	}

	public void setDctPer(Double dctPer) {
		this.dctPer = dctPer;
	}

	public int getDctType() {
		return dctType;
	}

	public void setDctType(int dctType) {
		this.dctType = dctType;
	}

}
