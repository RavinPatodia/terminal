package com.langmy.terminal.modules.log.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

/**
 * 操作日志Module
 * 
 * @author ZZD
 *
 */
public class LogModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8132054989162925208L;
	/**
	 * 操作员
	 */
	private String operMan;
	/**
	 * 操作时间 YYYY-MM-DD HH:MM:SS
	 */

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date operTime;
	/**
	 * 操作模块
	 */
	private String operModule;
	/**
	 * 操作类型
	 */
	private String operOption;
	/**
	 * 操作内容
	 */
	private String operCon;

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date startTime;

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date endTime;

	public String getOperMan() {
		return operMan;
	}

	public void setOperMan(String operMan) {
		this.operMan = operMan;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getOperModule() {
		return operModule;
	}

	public void setOperModule(String operModule) {
		this.operModule = operModule;
	}

	public String getOperOption() {
		return operOption;
	}

	public void setOperOption(String operOption) {
		this.operOption = operOption;
	}

	public String getOperCon() {
		return operCon;
	}

	public void setOperCon(String operCon) {
		this.operCon = operCon;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
