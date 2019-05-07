package com.langmy.terminal.modules.charge.model;

import com.langmy.terminal.common.model.BaseModel;



/**
 * 长期收费Model
 * @author lzy
 *
 */
public class AnchorRentModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7813231308015197936L;
	private String anchorRentId;
	private String name;
	private Double fee;
	private Integer month;
	private Double addFee;
	
	public String getAnchorRentId() {
		return anchorRentId;
	}
	public void setAnchorRentId(String anchorRentId) {
		this.anchorRentId = anchorRentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getAddFee() {
		return addFee;
	}
	public void setAddFee(Double addFee) {
		this.addFee = addFee;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
	
}
