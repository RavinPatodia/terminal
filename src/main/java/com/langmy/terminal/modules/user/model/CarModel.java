package com.langmy.terminal.modules.user.model;

import com.langmy.terminal.common.model.BaseModel;

/**
 * 车辆Mode
 * 
 * @author ZZD
 *
 */
public class CarModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6099810619739390998L;
	/**
	 * 客户Model
	 */
	private UserModel userModel;
	/**
	 * c车牌号码
	 */
	private String licensePlate;
	/**
	 * 车牌类型
	 */
	private String licensePlateType;
	/**
	 * 车辆颜色
	 */
	private String carColor;
	/**
	 * 车辆型号
	 */
	private String carModel;
	/**
	 * 车辆类型 0-小车 1-大车
	 */
	private int carType;
	/**
	 * 车辆id
	 */
	private Integer carPK;

	public CarModel(String licensePlate, String licensePlateType,
			String carColor, String carModel) {
		super();
		this.licensePlate = licensePlate;
		this.licensePlateType = licensePlateType;
		this.carColor = carColor;
		this.carModel = carModel;
	}

	public CarModel() {
		super();
	}

	public Integer getCarPK() {
		return carPK;
	}

	public void setCarPK(Integer carPK) {
		this.carPK = carPK;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getLicensePlateType() {
		return licensePlateType;
	}

	public void setLicensePlateType(String licensePlateType) {
		this.licensePlateType = licensePlateType;
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

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

}
