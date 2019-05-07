package com.langmy.terminal.modules.charge.model;

import com.langmy.terminal.common.model.BaseModel;




public class CarModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3282696934329078004L;
	
	private String licensePlate;
	private String type;
	private String carColor;
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
}
