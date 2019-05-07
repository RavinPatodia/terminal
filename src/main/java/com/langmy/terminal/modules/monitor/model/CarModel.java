package com.langmy.terminal.modules.monitor.model;

import java.util.Date;

import com.langmy.terminal.common.model.BaseModel;

/**
 * 车辆Model
 * 
 * @author ZZD
 *
 */
public class CarModel extends BaseModel {

	/**
	 * @param licensePlate
	 * @param type
	 * @param carColor
	 * @param carModel
	 * @param carType
	 */
	public CarModel(int id, String licensePlate, String type, String carColor,
			String carModel, int carType) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.type = type;
		this.carColor = carColor;
		this.carModel = carModel;
		this.carType = carType;
	}

	/**
	 * @param licensePlate
	 * @param type
	 * @param carColor
	 * @param carModel
	 * @param carType
	 */
	public CarModel(String licensePlate, String type, String carColor,
			String carModel, int carType) {
		super();
		this.licensePlate = licensePlate;
		this.type = type;
		this.carColor = carColor;
		this.carModel = carModel;
		this.carType = carType;
	}

	public CarModel() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7013318573598389682L;
	/**
	 * 车牌号码
	 */
	private String licensePlate;
	/**
	 * 车牌类型
	 */
	private String type;
	/**
	 * 车辆颜色
	 */
	private String carColor;
	/**
	 * 车辆型号
	 */
	private String carModel;
	/**
	 * 车辆类型
	 */
	private int carType;
	/**
	 * 是否免费放行
	 */
	private int isFree;

	// user
	/**
	 * 客户id
	 */
	private Integer userPK;
	/**
	 * 客户名
	 */
	private String uacc;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 客户身份证
	 */
	private String idCard;
	/**
	 * 客户性别
	 */
	private Boolean gender;
	/**
	 * 客户出生日期
	 */
	private Date birthday;

	private int state;
	/**
	 * 客户类型
	 */
	private Integer UType;

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

	public Integer getUserPK() {
		return userPK;
	}

	public void setUserPK(Integer userPK) {
		this.userPK = userPK;
	}

	public String getUacc() {
		return uacc;
	}

	public void setUacc(String uacc) {
		this.uacc = uacc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getUType() {
		return UType;
	}

	public void setUType(Integer uType) {
		UType = uType;
	}

	public int getIsFree() {
		return isFree;
	}

	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
