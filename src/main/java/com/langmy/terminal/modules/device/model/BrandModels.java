package com.langmy.terminal.modules.device.model;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

public class BrandModels extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brandModelId;
	private int type;
	private String name;
	public String getBrandModelId() {
		return brandModelId;
	}
	public void setBrandModelId(String brandModelId) {
		this.brandModelId = brandModelId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
