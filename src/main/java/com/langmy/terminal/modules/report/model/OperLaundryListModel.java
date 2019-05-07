package com.langmy.terminal.modules.report.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.modules.caradmission.model.ParkingRecModel;

/**
 * 值班流水账记录Model
 * 
 * @author ZZD
 *
 */
public class OperLaundryListModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -417163130905739665L;
	/**
	 * 入场时间
	 */
	private Date inTime;
	/**
	 * 出场时间
	 */
	private Date outTime;
	/**
	 * 入场识别方式
	 */
	private Integer inRecoWay;
	/**
	 * 出场识别方式
	 */
	private Integer outRecoWay;
	/**
	 * 费用
	 */
	private Double fee;
	/**
	 * 是否付款
	 */
	private Boolean isPay;
	/**
	 * 是否付款
	 */
	private Integer isPayInt;
	/**
	 * 入场图片
	 */
	private String inPicUrl;
	/**
	 * 出场图片
	 */
	private String outPicUrl;
	/**
	 * 入口id
	 */
	private Integer inDriveWayPK;
	/**
	 * 入口名称
	 */
	private String inDriveWayName;
	/**
	 * 出口id
	 */
	private Integer outDriveWayPK;
	/**
	 * 出口名称
	 */
	private String outDriveWayName;
	// 卡口
	/**
	 * 出卡口
	 */
	private String bayonetExitName;
	/**
	 * 入卡口
	 */
	private String bayonetEntranceName;
	// car
	/**
	 * 车牌号码
	 */
	private String licensePlate;
	/**
	 * 车牌类型
	 */
	private String licenseType;
	/**
	 * 型号
	 */
	private String module;
	/**
	 * 颜色
	 */
	private String color;
	/**
	 * 车辆类型
	 */
	private Integer carType;
	/**
	 * 停车场
	 */
	private String parkName;
	/**
	 * 停车记录Model
	 */
	private List<ParkingRecModel> parkingRecModels;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Integer getInRecoWay() {
		return inRecoWay;
	}

	public void setInRecoWay(Integer inRecoWay) {
		this.inRecoWay = inRecoWay;
	}

	public Integer getOutRecoWay() {
		return outRecoWay;
	}

	public void setOutRecoWay(Integer outRecoWay) {
		this.outRecoWay = outRecoWay;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Boolean getIsPay() {
		return isPay;
	}

	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}

	public String getInPicUrl() {
		return inPicUrl;
	}

	public void setInPicUrl(String inPicUrl) {
		this.inPicUrl = inPicUrl;
	}

	public String getOutPicUrl() {
		return outPicUrl;
	}

	public void setOutPicUrl(String outPicUrl) {
		this.outPicUrl = outPicUrl;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public List<ParkingRecModel> getParkingRecModels() {
		return parkingRecModels;
	}

	public void setParkingRecModels(List<ParkingRecModel> parkingRecModels) {
		this.parkingRecModels = parkingRecModels;
	}

	public Integer getIsPayInt() {
		return isPayInt;
	}

	public void setIsPayInt(Integer isPayInt) {
		this.isPayInt = isPayInt;
	}

	public Integer getInDriveWayPK() {
		return inDriveWayPK;
	}

	public void setInDriveWayPK(Integer inDriveWayPK) {
		this.inDriveWayPK = inDriveWayPK;
	}

	public String getInDriveWayName() {
		return inDriveWayName;
	}

	public void setInDriveWayName(String inDriveWayName) {
		this.inDriveWayName = inDriveWayName;
	}

	public Integer getOutDriveWayPK() {
		return outDriveWayPK;
	}

	public void setOutDriveWayPK(Integer outDriveWayPK) {
		this.outDriveWayPK = outDriveWayPK;
	}

	public String getOutDriveWayName() {
		return outDriveWayName;
	}

	public void setOutDriveWayName(String outDriveWayName) {
		this.outDriveWayName = outDriveWayName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getBayonetExitName() {
		return bayonetExitName;
	}

	public void setBayonetExitName(String bayonetExitName) {
		this.bayonetExitName = bayonetExitName;
	}

	public String getBayonetEntranceName() {
		return bayonetEntranceName;
	}

	public void setBayonetEntranceName(String bayonetEntranceName) {
		this.bayonetEntranceName = bayonetEntranceName;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

}
