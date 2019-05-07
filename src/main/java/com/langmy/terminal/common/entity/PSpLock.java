package com.langmy.terminal.common.entity;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * PSpLock generated by hbm2java
 */
@Entity
@Table(name = "p_sp_lock", catalog = "cloud", uniqueConstraints = @UniqueConstraint(columnNames = "lock_id"))
public class PSpLock extends GenIdEntityImpl {

	@Override
	public String toString() {
		String lineStateStr = lineState == true ? "在线" : "离线";
		String lockStateStr = lockState == 1 ? "开启" : "关闭";

		return "PSpLock [lockId=" + lockId + ", ip=" + ip + ", lockState="
				+ lockStateStr + ", brand=" + brand + ", model=" + model
				+ ", port=" + port + ", lineState=" + lineStateStr
				+ ", electricalQuantity=" + electricalQuantity + ", PSps="
				+ PSps + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4902476611173001932L;
	private String lockId; //车位锁编号
	private String ip; //IP
	private Integer lockState; //车位锁状态
	private String brand; //品牌
	private String model; //型号
	private int port; //端口号
	private boolean lineState; //在线离线状态
	private Integer electricalQuantity; //电量状态
	private boolean delFlag; //删除标志位
	private Set<PSp> PSps = new HashSet<PSp>(0); //车位

	public PSpLock() {
	}

	public PSpLock(String lockId, String ip, Integer lockState, String brand,
			String model, int port, Set<PSp> PSps, boolean lineState,
			Integer electricalQuantity) {
		this.lockId = lockId;
		this.ip = ip;
		this.lockState = lockState;
		this.brand = brand;
		this.model = model;
		this.port = port;
		this.PSps = PSps;
		this.lineState = lineState;
		this.electricalQuantity = electricalQuantity;
	}

	@Column(name = "lock_id")
	public String getLockId() {
		return this.lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	@Column(name = "ip", nullable = false, length = 20)
	public String getIp() {
		return this.ip;
	}

	@Column(name = "port", nullable = false)
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "lock_state")
	public Integer getLockState() {
		return this.lockState;
	}

	public void setLockState(Integer lockState) {
		this.lockState = lockState;
	}

	@Column(name = "brand", length = 20)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "model", length = 20)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "PSpLock")
	public Set<PSp> getPSps() {
		return PSps;
	}

	public void setPSps(Set<PSp> pSps) {
		PSps = pSps;
	}

	@Column(name = "line_state")
	public boolean isLineState() {
		return lineState;
	}

	public void setLineState(boolean lineState) {
		this.lineState = lineState;
	}

	@Column(name = "electrical_quantity")
	public Integer getElectricalQuantity() {
		return electricalQuantity;
	}

	public void setElectricalQuantity(Integer electricalQuantity) {
		this.electricalQuantity = electricalQuantity;
	}

	@Column(name = "del_flag", nullable = false)
	public boolean isDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
}