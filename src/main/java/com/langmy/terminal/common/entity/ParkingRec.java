package com.langmy.terminal.common.entity;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * ParkingRec generated by hbm2java
 */
@Entity
@Table(name = "parking_rec", catalog = "cloud")
public class ParkingRec extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6767706464698052887L;
	private AdminssionRec adminssionRec;  //出入场记录
	private Car car; //车辆
	private PSp PSp; //车位
	private Date inTime; //入场时间
	private Date outTime; //出场时间

	public ParkingRec() {
	}

	public ParkingRec(AdminssionRec adminssionRec, Car car, PSp PSp, Date inTime) {
		this.adminssionRec = adminssionRec;
		this.car = car;
		this.PSp = PSp;
		this.inTime = inTime;
	}

	public ParkingRec(AdminssionRec adminssionRec, Car car, PSp PSp,
			Date inTime, Date outTime) {
		this.adminssionRec = adminssionRec;
		this.car = car;
		this.PSp = PSp;
		this.inTime = inTime;
		this.outTime = outTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminssion_rec_id", nullable = false)
	public AdminssionRec getAdminssionRec() {
		return this.adminssionRec;
	}

	public void setAdminssionRec(AdminssionRec adminssionRec) {
		this.adminssionRec = adminssionRec;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id", nullable = false)
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_sp_id", nullable = false)
	public PSp getPSp() {
		return this.PSp;
	}

	public void setPSp(PSp PSp) {
		this.PSp = PSp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "in_time", nullable = false, length = 19)
	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "out_time", length = 19)
	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
}