package com.langmy.terminal.modules.device.model;

import com.langmy.terminal.common.model.BaseModel;

public class BayonetModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4322796135345730184L;
	private String bayonetId;
	private String name;
	private String posit;
	private boolean delFlag;
	private ParkModel parkModel;
	private int parkId;
	private String parkName; 
	
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getBayonetId() {
		return bayonetId;
	}
	public void setBayonetId(String bayonetId) {
		this.bayonetId = bayonetId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosit() {
		return posit;
	}
	public void setPosit(String posit) {
		this.posit = posit;
	}
	public boolean isDelFlag() {
		return delFlag;
	}
	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	public ParkModel getParkModel() {
		return parkModel;
	}
	public void setParkModel(ParkModel parkModel) {
		this.parkModel = parkModel;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	
}
