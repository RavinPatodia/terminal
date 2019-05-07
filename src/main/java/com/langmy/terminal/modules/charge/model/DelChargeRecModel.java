package com.langmy.terminal.modules.charge.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;

public class DelChargeRecModel extends ChargeRecModel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4357561457400880278L;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date delTime;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date delTimeFrom;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date delTimeTo;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDelTimeFrom() {
		return delTimeFrom;
	}
	public void setDelTimeFrom(Date delTimeFrom) {
		this.delTimeFrom = delTimeFrom;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDelTimeTo() {
		return delTimeTo;
	}
	public void setDelTimeTo(Date delTimeTo) {
		this.delTimeTo = delTimeTo;
	}
}
