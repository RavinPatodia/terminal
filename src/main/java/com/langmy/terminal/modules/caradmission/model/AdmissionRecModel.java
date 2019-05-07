package com.langmy.terminal.modules.caradmission.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

/**
 * 出入场记录Model
 * 
 * @author ZZD
 *
 */
public class AdmissionRecModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198059525266394426L;
	private Date inTime;// 入场时间
	private Date outTime;// 出场时间

	private boolean freeFlag;// 是否免费放行标志位
	private boolean outLiftFlag;// 出场是否手动开闸放行 0:否 1：是
	private boolean inLiftFlag;// 入场是否手动开闸放行 0:否 1：是

	private Integer inRecoWay;// 入场识别方式
	private Integer outRecoWay;// 出场识别方式
	private Double fee;// 费用
	private Boolean isPay;// 是否付款
	private Integer isPayInt;// 是否付款
	private String inPicUrl;// 入场图片
	private String outPicUrl;// 出场图片

	private String inPicPlate;// 入场车牌图片
	private String outPicPlate;// 出场车牌图片
	private String inPicSafety;// 入场防刮蹭图片（多张图片以";"隔开）
	private String outPicSafety;// 出场防刮蹭图片（多张图片以";"隔开）

	/**
	 * 车道
	 */
	private Integer inDriveWayPK;// 入口id
	private String inDriveWayName;// 入口名称
	private Integer outDriveWayPK;// 出口id
	private String outDriveWayName;// 出口名称
	/**
	 * 卡口
	 */
	private String bayonetExitName;// 出卡口
	private String bayonetEntranceName;// 入卡口
	/**
	 * 车辆
	 */
	private String licensePlate;// 车牌号码
	private String licenseType;// 车牌类型
	private String module;// 型号
	private String color;// 颜色
	private Integer carType;// 车辆类型
	private String parkName;// 停车场
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

	public boolean isFreeFlag() {
		return freeFlag;
	}

	public void setFreeFlag(boolean freeFlag) {
		this.freeFlag = freeFlag;
	}

	public boolean isOutLiftFlag() {
		return outLiftFlag;
	}

	public void setOutLiftFlag(boolean outLiftFlag) {
		this.outLiftFlag = outLiftFlag;
	}

	public boolean isInLiftFlag() {
		return inLiftFlag;
	}

	public void setInLiftFlag(boolean inLiftFlag) {
		this.inLiftFlag = inLiftFlag;
	}

	public String getInPicPlate() {
		return inPicPlate;
	}

	public void setInPicPlate(String inPicPlate) {
		this.inPicPlate = inPicPlate;
	}

	public String getOutPicPlate() {
		return outPicPlate;
	}

	public void setOutPicPlate(String outPicPlate) {
		this.outPicPlate = outPicPlate;
	}

	public String getInPicSafety() {
		return inPicSafety;
	}

	public void setInPicSafety(String inPicSafety) {
		this.inPicSafety = inPicSafety;
	}

	public String getOutPicSafety() {
		return outPicSafety;
	}

	public void setOutPicSafety(String outPicSafety) {
		this.outPicSafety = outPicSafety;
	}

}
