package com.langmy.terminal.modules.device.model;


import com.langmy.terminal.common.model.BaseModel;

public class AreaModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159580488547398477L;
	private String areaId;
	private String name;
	private String posit;
	private String pspId;
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPspId() {
		return pspId;
	}
	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	
}
