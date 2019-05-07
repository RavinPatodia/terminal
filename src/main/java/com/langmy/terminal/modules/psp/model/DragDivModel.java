package com.langmy.terminal.modules.psp.model;

import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.excel.annotation.ExcelField;

/**
 * 拖拽的model
 * 
 * @author MC
 * 
 */
public class DragDivModel extends BaseModel {

	private static final long serialVersionUID = -2255842631577460778L;
	/**
	 * div编号
	 */
	private String divId;
	/**
	 * div显示的内容
	 */
	private String pspLockId;
	@ExcelField(title="车位锁编号", align=2, sort=20)
	public String getPspLockId() {
		return pspLockId;
	}

	public void setPspLockId(String pspLockId) {
		this.pspLockId = pspLockId;
	}

	/**
	 * div的横坐标
	 */
	private String divX;
	/**
	 * div的纵坐标
	 */
	private String divY;
	/**
	 * div的类型（车位、识别设备）
	 */
	private String type;
	/**
	 * 车位上的车辆的车牌号
	 */
	private String licensePlate;
	/**
	 * 禁用启用状态
	 */
	private Integer enable;
	/**
	 * 在线离线状态
	 */
	private String mapPath;//底图路径
	private Integer parkId;
	private Integer lineState;
	@ExcelField(title="类型（1：车位；0：摄像头）", align=2, sort=30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@ExcelField(title="车牌号", align=2, sort=40)
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getLineState() {
		return lineState;
	}

	public void setLineState(Integer lineState) {
		this.lineState = lineState;
	}
	@ExcelField(title="车位/摄像头编号", align=2, sort=10)
	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}
	@ExcelField(title="横坐标", align=2, sort=30)
	public String getDivX() {
		return divX;
	}

	public void setDivX(String divX) {
		this.divX = divX;
	}
	@ExcelField(title="纵坐标", align=2, sort=30)
	public String getDivY() {
		return divY;
	}

	public void setDivY(String divY) {
		this.divY = divY;
	}
	@ExcelField(title="是否可用（0：不可用；1：可用）", align=2, sort=30)
	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public String getMapPath() {
		return mapPath;
	}

	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}

}
