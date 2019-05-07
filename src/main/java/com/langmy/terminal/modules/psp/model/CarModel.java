package com.langmy.terminal.modules.psp.model;

import com.langmy.terminal.common.model.BaseModel;

/**
 * 车的model
 * 
 * @author MC
 *
 */
public class CarModel extends BaseModel {
	private static final long serialVersionUID = -3282696934329078004L;

	/**
	 * 车牌号
	 */
	private String licensePlate;
	/**
	 * 车类型
	 */
	private String type;
	/**
	 * 车颜色
	 */
	private String carColor;
	/**
	 * 车型号
	 */
	private String carModel;

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
