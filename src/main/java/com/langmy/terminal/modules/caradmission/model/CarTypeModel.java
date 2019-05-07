package com.langmy.terminal.modules.caradmission.model;

import com.langmy.terminal.common.model.BaseModel;

/**
 * 车辆相关信息Model
 * 
 * @author ZZD
 *
 */
public class CarTypeModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5473443636300876129L;
	/**
	 * 类型 颜色/车牌类型/车辆型号
	 */
	private Integer type;
	/**
	 * 是否免费放行
	 */
	private String isFree;
	/**
	 * 车牌类型名称
	 */
	private String typeName;
	/**
	 * 车辆颜色
	 */
	private String color;
	/**
	 * 车辆型号
	 */
	private String model;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
