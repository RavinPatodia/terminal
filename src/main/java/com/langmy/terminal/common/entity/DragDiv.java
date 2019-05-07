package com.langmy.terminal.common.entity;

// Generated 2015-3-30 14:34:35 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * DragDiv generated by hbm2java
 */
@Entity
@Table(name = "drag_div", catalog = "cloud")
public class DragDiv extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2255842631577460778L;
	private String divId; //车位摄像头编号
	private String pspLockId; //车位锁编号
	private String divX; //横坐标
	private String divY; //纵坐标
	private String type; //类型（0：摄像头，1：车位）
	private String licensePlate; //车牌号
	private Integer enable; //启用禁用状态
	private Integer lineState;//在线离线状态
	private Integer parkId;//停车场编号

	public DragDiv() {
	}

	@Override
	public String toString() {
		return "拖拽DIV [ID=" + id + ", div编号=" + divId + ", 车位锁编号=" + pspLockId
				+ ", 横坐标=" + divX + ", 纵坐标=" + divY + ", 类型=" + type + ", 车牌号="
				+ licensePlate + ", 启用禁用状态=" + enable + ", 在线离线状态=" + lineState
				+ "]";
	}

	public DragDiv(String divId, String divX, String divY) {
		this.divId = divId;
		this.divX = divX;
		this.divY = divY;
	}

	public DragDiv(String divId, String pspLockId, String divX, String divY,
			String type, String licensePlate, Integer enable, Integer lineState) {
		this.divId = divId;
		this.pspLockId = pspLockId;
		this.divX = divX;
		this.divY = divY;
		this.type = type;
		this.licensePlate = licensePlate;
		this.enable = enable;
		this.lineState = lineState;
	}

	@Column(name = "div_id", nullable = false, length = 30)
	public String getDivId() {
		return this.divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	@Column(name = "div_x", length = 30)
	public String getDivX() {
		return this.divX;
	}

	public void setDivX(String divX) {
		this.divX = divX;
	}

	@Column(name = "div_y", length = 30)
	public String getDivY() {
		return this.divY;
	}

	public void setDivY(String divY) {
		this.divY = divY;
	}

	@Column(name = "enable")
	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	@Column(name = "type", length = 30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "license_plate", length = 30)
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@Column(name = "line_state")
	public Integer getLineState() {
		return lineState;
	}

	public void setLineState(Integer lineState) {
		this.lineState = lineState;
	}

	@Column(name = "psp_lock_id", length = 30)
	public String getPspLockId() {
		return pspLockId;
	}

	public void setPspLockId(String pspLockId) {
		this.pspLockId = pspLockId;
	}

	@Column(name = "park_id")
	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

}