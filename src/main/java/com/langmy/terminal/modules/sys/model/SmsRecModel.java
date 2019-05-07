package com.langmy.terminal.modules.sys.model;

import com.langmy.terminal.common.model.BaseModel;

/**
 * @author lzy
 * 2015年6月1日
 */
public class SmsRecModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7031473532994538218L;
	
	private int address;
	private int type;
	private double condition;
	private String content;
	private boolean enableFlag;
	
	public SmsRecModel(){}
	
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getCondition() {
		return condition;
	}
	public void setCondition(double condition) {
		this.condition = condition;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(boolean enableFlag) {
		this.enableFlag = enableFlag;
	}
	
	
}
