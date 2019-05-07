package com.langmy.terminal.modules.charge.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

/**
 * rule model基类
 * @author Administrator
 *
 */
public class BaseRuleModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1121615638907387572L;
	/**
	 * 
	 */
	protected boolean enableFlag;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	protected Date lastDisable;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	protected Date createTime;// 创建日期
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	protected Date lastEnable;

	@JSONField(name="enableFlag")
	public boolean isEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastDisable() {
		return lastDisable;
	}

	public void setLastDisable(Date lastDisable) {
		this.lastDisable = lastDisable;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastEnable() {
		return lastEnable;
	}

	public void setLastEnable(Date lastEnable) {
		this.lastEnable = lastEnable;
	}
	
}
