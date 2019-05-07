package com.langmy.terminal.common.entity;

// Generated 2015-2-3 18:24:28 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * PSp generated by hbm2java
 */
/**
 * @author MC
 *
 */
@Entity
@Table(name = "p_sp", catalog = "cloud", uniqueConstraints = @UniqueConstraint(columnNames = "p_sp_id"))
public class PSp extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2812106511246365446L;

	@Override
	public String toString() {
		return "PSp [ " + ", 车位编号=" + pspId + ", 位置=" + posit
				+ ", 3D位置=" + posit3d + ", 是否被租用=" + isRent + ", 是否在拖拽页面="
				+ nowState + ", 启用禁用状态=" + isEnable + "]";
	}

	private Area area; //区域
	private Car car; //车辆
	private PSpLock PSpLock; //车位锁
	private Park park; //停车场
	private String pspId; //车位编号
	private String posit; //位置
	private String posit3d; //3D位置
	private boolean isRent; //是否被租用
	private boolean nowState; //当前车位状态  0：车位上无车 1：车位上有车
	private boolean isEnable; //是否启用
	private boolean delFlag; //删除标志
	private Set<Camera> camera = new HashSet<Camera>(0); //摄像机
	private Set<ParkingRec> parkingRecs = new HashSet<ParkingRec>(0); //停车记录
	private Set<UserPSp> userPSps = new HashSet<UserPSp>(0); //用户车位表
	private Set<DelParkingRec> delParkingRecs = new HashSet<DelParkingRec>(0); //已删除停车记录

	public PSp() {
	}

	public PSp(Area area, PSpLock PSpLock, String pspId, boolean isRent,
			boolean nowState, boolean isEnable) {
		this.area = area;
		this.PSpLock = PSpLock;
		this.pspId = pspId;
		this.isRent = isRent;
		this.nowState = nowState;
		this.isEnable = isEnable;
	}

	public PSp(Area area, Car car, PSpLock PSpLock, Park park,
			String pspId, String posit, String posit3d,
			ChargeRule spRentRuleId, boolean isRent, boolean nowState,
			Date lastEnable, Set<Camera> camera, Set<ParkingRec> parkingRecs,
			Set<UserPSp> userPSps) {
		this.area = area;
		this.car = car;
		this.PSpLock = PSpLock;
		this.park = park;
		this.pspId = pspId;
		this.posit = posit;
		this.posit3d = posit3d;
		this.isRent = isRent;
		this.nowState = nowState;
		this.camera = camera;
		this.parkingRecs = parkingRecs;
		this.userPSps = userPSps;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "now_car")
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "park_id", nullable = false)
	public Park getPark() {
		return this.park;
	} 
	public void setPark(Park park) {
		this.park = park;
	}
	
	@Column(name = "p_sp_id", unique = true, nullable = false, length = 20)
	public String getPspId() {
		return this.pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	@Column(name = "posit", length = 20)
	public String getPosit() {
		return this.posit;
	}

	public void setPosit(String posit) {
		this.posit = posit;
	}

	@Column(name = "posit_3D", length = 20)
	public String getPosit3d() {
		return this.posit3d;
	}

	public void setPosit3d(String posit3d) {
		this.posit3d = posit3d;
	}

	@Column(name = "is_rent", nullable = false)
	public boolean getIsRent() {
		return this.isRent;
	}

	public void setIsRent(boolean isRent) {
		this.isRent = isRent;
	}

	@Column(name = "now_state", nullable = false)
	public boolean isNowState() {
		return this.nowState;
	}

	public void setNowState(boolean nowState) {
		this.nowState = nowState;
	}

	@Column(name = "is_enable", nullable = false)
	public boolean getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "PSp")
	public Set<ParkingRec> getParkingRecs() {
		return this.parkingRecs;
	}

	public void setParkingRecs(Set<ParkingRec> parkingRecs) {
		this.parkingRecs = parkingRecs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "PSp")
	public Set<UserPSp> getUserPSps() {
		return this.userPSps;
	}

	public void setUserPSps(Set<UserPSp> userPSps) {
		this.userPSps = userPSps;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "camera_p_sp", catalog = "cloud", joinColumns = { @JoinColumn(name = "space_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "camera_id", nullable = false, updatable = false) })
	public Set<Camera> getCamera() {
		return camera;
	}

	public void setCamera(Set<Camera> camera) {
		this.camera = camera;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "PSp")
	public Set<DelParkingRec> getDelParkingRecs() {
		return delParkingRecs;
	}

	public void setDelParkingRecs(Set<DelParkingRec> delParkingRecs) {
		this.delParkingRecs = delParkingRecs;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lock_id")
	public PSpLock getPSpLock() {
		return PSpLock;
	}

	public void setPSpLock(PSpLock PSpLock) {
		this.PSpLock = PSpLock;
	}

	@Column(name = "del_flag", nullable = false)
	public boolean isDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

}
