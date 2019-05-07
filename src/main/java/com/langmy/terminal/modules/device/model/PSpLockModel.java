package com.langmy.terminal.modules.device.model;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import com.langmy.terminal.common.model.BaseModel;

public class PSpLockModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4902476611173001932L;
	private String lockId;
	private String ip;
	private Integer lockState;
	private String brand;
	private String model;
	private int port;
	private String pspId;
	private boolean state;
	private boolean lineState;
	private boolean delFlag;
	
	public String getLockId() {
		return this.lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getLockState() {
		return this.lockState;
	}

	public void setLockState(Integer lockState) {
		this.lockState = lockState;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		if(state)
			lockState=1;
		else
			lockState=0;
		this.state = state;
	}

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	public boolean isLineState() {
		return lineState;
	}

	public void setLineState(boolean lineState) {
		this.lineState = lineState;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
}
