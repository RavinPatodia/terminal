package com.langmy.terminal.modules.sys.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class BaseSearchModel {
	// 查询
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date dtCreateTimeFrom;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date dtCreateTimeTo;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date dtLastEnableFrom;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date dtLastEnableTo;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date dtLastDisableFrom;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date dtLastDisableTo;

	private int sectEnableFlag;

	public Date getDtCreateTimeFrom() {
		return dtCreateTimeFrom;
	}

	public void setDtCreateTimeFrom(Date dtCreateTimeFrom) {
		this.dtCreateTimeFrom = dtCreateTimeFrom;
	}

	public Date getDtCreateTimeTo() {
		return dtCreateTimeTo;
	}

	public void setDtCreateTimeTo(Date dtCreateTimeTo) {
		this.dtCreateTimeTo = dtCreateTimeTo;
	}

	public Date getDtLastEnableFrom() {
		return dtLastEnableFrom;
	}

	public void setDtLastEnableFrom(Date dtLastEnableFrom) {
		this.dtLastEnableFrom = dtLastEnableFrom;
	}

	public Date getDtLastEnableTo() {
		return dtLastEnableTo;
	}

	public void setDtLastEnableTo(Date dtLastEnableTo) {
		this.dtLastEnableTo = dtLastEnableTo;
	}

	public Date getDtLastDisableFrom() {
		return dtLastDisableFrom;
	}

	public void setDtLastDisableFrom(Date dtLastDisableFrom) {
		this.dtLastDisableFrom = dtLastDisableFrom;
	}

	public Date getDtLastDisableTo() {
		return dtLastDisableTo;
	}

	public void setDtLastDisableTo(Date dtLastDisableTo) {
		this.dtLastDisableTo = dtLastDisableTo;
	}

	public int getSectEnableFlag() {
		return sectEnableFlag;
	}

	public void setSectEnableFlag(int sectEnableFlag) {
		this.sectEnableFlag = sectEnableFlag;
	}
}
