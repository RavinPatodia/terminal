package com.langmy.terminal.modules.psp.model;

// Generated 2015-2-3 18:24:28 by Hibernate Tools 4.3.1

import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.excel.annotation.ExcelField;
import com.langmy.terminal.modules.device.model.PSpLockModel;

/**
 * 车位的model
 * 
 * @author MC
 * 
 */
public class PSpModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 区域的model
	 */
	private AreaModel areaModel;
	/**
	 * 车辆的model
	 */
	private CarModel carModel;
	/**
	 * 车位锁的model
	 */
	private PSpLockModel PSpLockModel;
	/**
	 * 车位编号
	 */
	private String pspId;
	/**
	 * 车位位置
	 */
	private String posit;
	/**
	 * 车位的3D坐标
	 */
	private String posit3d;
	/**
	 * 是否被租赁
	 */
	private int isRent;
	/**
	 * 是否被禁用
	 */
	private int isEnable;
	/**
	 * 批量添加的数量
	 */
	private Integer pspNum;
	/**
	 * 当前状态（拖拽的时候用到）
	 */
	private Integer nowState;
	/**
	 * 启用禁用的状态（boolean类型）
	 */
	private boolean enableStateBoolean;
	/**
	 * 是否为删除车位
	 */
	private boolean delFlag;
	/**
	 * 区域编号
	 */
	private String areaName;
	/**
	 * 停车场名
	 */
	private String parkName;
	/**
	 * 车牌号
	 */
	private String licensePlate;
	/**
	 * 车位锁编号
	 */
	private String lockId;
	
	@ExcelField(title="车位编号", align=2, sort=30)
	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	@ExcelField(title="车位位置", align=1, sort=40)
	public String getPosit() {
		return posit;
	}

	public void setPosit(String posit) {
		this.posit = posit;
	}

	@ExcelField(title="车位3D坐标", align=2, sort=50)
	public String getPosit3d() {
		return posit3d;
	}

	public void setPosit3d(String posit3d) {
		this.posit3d = posit3d;
	}

	public Integer isNowState() {
		return nowState;
	}

	public void setNowState(Integer nowState) {
		this.nowState = nowState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public AreaModel getAreaModel() {
		return areaModel;
	}

	public void setAreaModel(AreaModel areaModel) {
		this.areaModel = areaModel;
	}

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public int getIsRent() {
		return isRent;
	}

	public void setIsRent(int isRent) {
		this.isRent = isRent;
	}

	public PSpLockModel getPSpLockModel() {
		return PSpLockModel;
	}

	public void setPSpLockModel(PSpLockModel pSpLockModel) {
		PSpLockModel = pSpLockModel;
	}

	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getNowState() {
		return nowState;
	}

	public boolean isEnableStateBoolean() {
		return enableStateBoolean;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public void setEnableStateBoolean(boolean enableStateBoolean) {
		if (enableStateBoolean)
			isEnable = 1;
		else {
			isEnable = 0;
		}
		this.enableStateBoolean = enableStateBoolean;
	}

	public Integer getPspNum() {
		return pspNum;
	}

	public void setPspNum(Integer pspNum) {
		this.pspNum = pspNum;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
}
