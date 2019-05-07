package com.langmy.terminal.modules.user.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

public class BaseGroupModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4363489822926046795L;
	protected boolean enableFlag;
	protected Date lastDisable;
	protected Date createTime;// 创建日期
	protected Date lastEnable;

	@JSONField(name = "enableFlag")
	public boolean isEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastDisable() {
		return lastDisable;
	}

	public void setLastDisable(Date lastDisable) {
		this.lastDisable = lastDisable;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastEnable() {
		return lastEnable;
	}

	public void setLastEnable(Date lastEnable) {
		this.lastEnable = lastEnable;
	}
}
