package com.langmy.terminal.common.entity;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * OperLiftRec generated by hbm2java
 */
@Entity
@Table(name = "oper_lift_rec", catalog = "cloud")
public class OperLiftRec extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4193383957164263769L;
	private Bayonet bayonet; //卡口
	private Device device; //闸机
	private Operater operater; //操作员
	private Date openTime; //开闸时间
	private String openReason; //开闸原因

	@Override
	public String toString() {
		return "OperLiftRec [卡口=" + bayonet + ", 闸机=" + device + ", 操作员="
				+ operater + ", 开闸时间=" + openTime + ", 开闸原因=" + openReason
				+ "]";
	}

	@PrePersist
	public void prePersist() {
		openTime = new Date();
	}

	public OperLiftRec() {
	}

	public OperLiftRec(Bayonet bayonet, Device device, Operater operater,
			Date openTime, String openReason) {
		this.bayonet = bayonet;
		this.device = device;
		this.operater = operater;
		this.openTime = openTime;
		this.openReason = openReason;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bayonet_id", nullable = false)
	public Bayonet getBayonet() {
		return this.bayonet;
	}

	public void setBayonet(Bayonet bayonet) {
		this.bayonet = bayonet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id", nullable = false)
	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oper_id", nullable = false)
	public Operater getOperater() {
		return this.operater;
	}

	public void setOperater(Operater operater) {
		this.operater = operater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "open_time", nullable = false, length = 19)
	public Date getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	@Column(name = "open_reason", nullable = false, length = 255)
	public String getOpenReason() {
		return this.openReason;
	}

	public void setOpenReason(String openReason) {
		this.openReason = openReason;
	}

}
