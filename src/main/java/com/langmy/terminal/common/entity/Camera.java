package com.langmy.terminal.common.entity;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * Camera generated by hbm2java
 */
@Entity
@Table(name = "camera", catalog = "cloud", uniqueConstraints = @UniqueConstraint(columnNames = "camera_id"))
public class Camera extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7472013732351944464L;
	private String cameraId; //设备编号
	private Integer type; //识别设备类型(0-出入口/1-过道/2-车位)
	private String brand; //品牌
	private String describion; //作用描述
	/**
	 * 卡口摄像机对应车道
	 */
	private Driveway driveway; //车道
	private String model; //型号
	private String devicePosit; //位置
	private String ip; //IP
	private Integer lightState; //指示灯状态
	private Integer cameraState; //摄像机状态
	private Integer nowState; //当前的配置信息
	private boolean lineState; //在线离线状态
	private boolean delFlag; //删除标志
 
	private Set<PSp> pSp = new HashSet<PSp>(0); //车位

	public Camera() {
	}

	public Camera(String cameraId, Integer type, String brand,
			String describion, String model, String devicePosit, String ip,
			Integer lightState, Integer cameraState, boolean lineState,
			Set<PSp> pSp) {
		this.cameraId = cameraId;
		this.type = type;
		this.brand = brand;
		this.describion = describion;
		this.model = model;
		this.devicePosit = devicePosit;
		this.ip = ip;
		this.lightState = lightState;
		this.cameraState = cameraState;
		this.lineState = lineState;
		this.pSp = pSp;
	}

	@Column(name = "camera_id", unique = true)
	public String getCameraId() {
		return this.cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "brand", length = 20)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "describion", length = 255)
	public String getDescribion() {
		return this.describion;
	}

	public void setDescribion(String describion) {
		this.describion = describion;
	}

	@Column(name = "model", length = 20)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "device_posit", length = 20)
	public String getDevicePosit() {
		return this.devicePosit;
	}

	public void setDevicePosit(String devicePosit) {
		this.devicePosit = devicePosit;
	}

	@Column(name = "ip", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "light_state")
	public Integer getLightState() {
		return this.lightState;
	}

	public void setLightState(Integer lightState) {
		this.lightState = lightState;
	}

	@Column(name = "camera_state")
	public Integer getCameraState() {
		return this.cameraState;
	}

	public void setCameraState(Integer cameraState) {
		this.cameraState = cameraState;
	}

	@Column(name = "now_state")
	public Integer getNowState() {
		return nowState;
	}

	public void setNowState(Integer nowState) {
		this.nowState = nowState;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "camera_p_sp", catalog = "cloud", joinColumns = { @JoinColumn(name = "camera_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "space_id", nullable = false, updatable = false) })
	public Set<PSp> getpSp() {
		return pSp;
	}

	public void setpSp(Set<PSp> pSp) {
		this.pSp = pSp;
	}

	@Override
	public String toString() {

		String lightStateStr = lightState == 1 ? "指示灯开启" : "指示灯关闭";
		String cameraStateStr = cameraState == 1 ? "摄像头开启" : "摄像头关闭";

		return "Camera [id=" + id +  ", cameraId="
				+ cameraId + ", type=" + type + ", brand=" + brand
				+ ", describion=" + describion + ", model=" + model
				+ ", devicePosit=" + devicePosit + ", ip=" + ip
				+ ", lightState=" + lightStateStr + ", cameraState="
				+ cameraStateStr + ", pSp=" + pSp + "]";
	}

	@Column(name = "line_state")
	public boolean isLineState() {
		return lineState;
	}

	public void setLineState(boolean lineState) {
		this.lineState = lineState;
	}

	@Column(name = "del_flag", nullable = false)
	public boolean isDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driveway_id")
	public Driveway getDriveway() {
		return driveway;
	}

	public void setDriveway(Driveway driveway) {
		this.driveway = driveway;
	}

}
