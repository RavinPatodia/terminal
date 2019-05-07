package com.langmy.terminal.modules.caradmission.model;

import com.langmy.terminal.common.model.BaseModel;

/**
 * 车位出入model类
 * 
 * @author lxj
 *
 */
public class PSpInOutModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5724359201849099179L;

	private Integer pspPK;// 车位主键

	private String pspId;// 车位编号

	private String licensePlate;// 车牌号码

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getPspPK() {
		return pspPK;
	}

	public void setPspPK(Integer pspPK) {
		this.pspPK = pspPK;
	}

}
