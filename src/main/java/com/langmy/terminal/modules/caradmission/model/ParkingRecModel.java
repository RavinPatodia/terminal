package com.langmy.terminal.modules.caradmission.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

public class ParkingRecModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -41377595741492430L;
	/**
	 * 出入场记录Model
	 */
	private AdmissionRecModel admissionRecModel;
	/**
	 * 车位编号
	 */
	private String pspId;
	/**
	 * 入场时间
	 */
	private Date inTime;
	/**
	 * 出场时间
	 */
	private Date outTime;

	// car
	/**
	 * 车牌号码
	 */
	private String licensePlate;
	/**
	 * 车牌类型
	 */
	private String licenseType;


	public AdmissionRecModel getAdmissionRecModel() {
		return admissionRecModel;
	}

	public void setAdmissionRecModel(AdmissionRecModel admissionRecModel) {
		this.admissionRecModel = admissionRecModel;
	}

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

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
}
