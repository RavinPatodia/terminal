package com.langmy.terminal.modules.device.model;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import com.langmy.terminal.common.model.BaseModel;
public class CameraModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7472013732351944464L;
	private String cameraId;
	private Integer type;
	private Integer nowState;
	private String brand;
	private String describion;
	private String model;
	private String devicePosit;
	private String ip;
	private String pspId;
	private boolean lineState;
	private Integer lightState;
	private Integer cameraState = 0;
	private String drivewayName;
	private Integer drivewayId;
	
	private boolean lightStateBoolean;
	private boolean cameraStateBoolean;
	private boolean delFlag;

	
	public String getCameraId() {
		return cameraId;
	}
	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescribion() {
		return describion;
	}
	public void setDescribion(String describion) {
		this.describion = describion;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDevicePosit() {
		return devicePosit;
	}
	public void setDevicePosit(String devicePosit) {
		this.devicePosit = devicePosit;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getLightState() {
		return lightState;
	}
	public void setLightState(Integer lightState) {
		this.lightState = lightState;
	}
	public Integer getCameraState() {
		return cameraState;
	}
	public void setCameraState(Integer cameraState) {
		this.cameraState = cameraState;
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
	public boolean getLightStateBoolean() {
		return lightStateBoolean;
	}
	public void setLightStateBoolean(boolean lightStateBoolean) {
		if(lightStateBoolean)
			lightState=1;
		else 
			lightState=0;
		this.lightStateBoolean = lightStateBoolean;
	}
	public boolean getCameraStateBoolean() {
		return cameraStateBoolean;
	}
	public void setCameraStateBoolean(boolean cameraStateBoolean) {
		if(cameraStateBoolean)
			cameraState=1;
		else 
			cameraState=0;
		this.cameraStateBoolean = cameraStateBoolean;
	}
	public Integer getNowState() {
		return nowState;
	}
	public void setNowState(Integer nowState) {
		this.nowState = nowState;
	}
	public boolean isLineState() {
		return lineState;
	}
	public void setLineState(boolean lineState) {
		this.lineState = lineState;
	}
	public boolean isDelFlag() {
		return delFlag;
	}
	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	public String getDrivewayName() {
		return drivewayName;
	}
	public void setDrivewayName(String drivewayName) {
		this.drivewayName = drivewayName;
	}
	public Integer getDrivewayId() {
		return drivewayId;
	}
	public void setDrivewayId(Integer drivewayId) {
		this.drivewayId = drivewayId;
	}
	
}
