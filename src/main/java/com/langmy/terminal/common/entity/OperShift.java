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
 * @author lzy
 * 2015年6月12日
 */
@Entity
@Table(name = "oper_shift", catalog = "cloud")
public class OperShift extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128592832066717462L;

	private Operater offWorkOper;// 交班操作员
	private Operater workOper;// 接班操作员
	private Date workTime;// 上班时间
	private Date offWorkTime;// 下班时间
	private double totalPay;//总流水
	private double memberAccountPay;//普通会员账户扣款金额
	private double memberCash;// 普通会员支付现金
	private int memberCar;//普通会员出场数
	private double amoutPay;// 停车应收现金金额
	private double actualPay;// 停车实收现金金额
	private int chargeCar;// 收费车辆数
	private int tempCar;//临时车辆出场数
	private Double tempCash;//临时车辆收费金额
	private Integer anchorCar;//固定车辆进场数
	private int freeCar;// VIP免费车辆数
	private int freePlate;//免费车牌数
	private int specialFreeCar;// 发生费用免费出场数
	private int workCar;// 上班时场内停车数
	private int offWorkCar;// 下班时场内停车数
	private int inLift;// 入口开闸入场数
	private int outLift;// 出口开闸出场数
	private Integer inCar;//车辆出场数
	private Integer outCar;//车辆入场数
	private Device terminal;// 终端机

	public OperShift() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "off_work_oper_id")
	public Operater getOffWorkOper() {
		return offWorkOper;
	}

	public void setOffWorkOper(Operater offWorkOper) {
		this.offWorkOper = offWorkOper;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "work_oper_id")
	public Operater getWorkOper() {
		return workOper;
	}

	public void setWorkOper(Operater workOper) {
		this.workOper = workOper;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "work_time", length = 19)
	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "off_work_time", length = 19)
	public Date getOffWorkTime() {
		return offWorkTime;
	}

	public void setOffWorkTime(Date offWorkTime) {
		this.offWorkTime = offWorkTime;
	}

	@Column(name = "amout_pay", precision = 10, nullable = false)
	public double getAmoutPay() {
		return amoutPay;
	}

	public void setAmoutPay(double amoutPay) {
		this.amoutPay = amoutPay;
	}

	@Column(name = "actual_pay", precision = 10, nullable = false)
	public double getActualPay() {
		return actualPay;
	}

	public void setActualPay(double actualPay) {
		this.actualPay = actualPay;
	}


	@Column(name = "charge_car", nullable = false)
	public int getChargeCar() {
		return chargeCar;
	}

	public void setChargeCar(int chargeCar) {
		this.chargeCar = chargeCar;
	}
	
	@Column(name = "temp_cash", nullable = false, precision = 10)
	public double getTempCash() {
		return this.tempCash;
	}

	public void setTempCash(double tempCash) {
		this.tempCash = tempCash;
	}
	
	@Column(name = "total_pay", nullable = false, precision = 10)
	public double getTotalPay() {
		return this.totalPay;
	}

	public void setTotalPay(double totalPay) {
		this.totalPay = totalPay;
	}
	
	@Column(name = "member_account_pay", nullable = false, precision = 10)
	public double getMemberAccountPay() {
		return this.memberAccountPay;
	}

	public void setMemberAccountPay(double memberAccountPay) {
		this.memberAccountPay = memberAccountPay;
	}
	
	@Column(name = "temp_car", nullable = false)
	public int getTempCar() {
		return this.tempCar;
	}

	public void setTempCar(int tempCar) {
		this.tempCar = tempCar;
	}
	
	@Column(name = "member_car", nullable = false)
	public int getMemberCar() {
		return this.memberCar;
	}

	public void setMemberCar(int memberCar) {
		this.memberCar = memberCar;
	}

	@Column(name = "member_cash", nullable = false, precision = 10)
	public double getMemberCash() {
		return this.memberCash;
	}

	public void setMemberCash(double memberCash) {
		this.memberCash = memberCash;
	}
	
	@Column(name = "free_plate", nullable = false)
	public int getFreePlate() {
		return this.freePlate;
	}

	public void setFreePlate(int freePlate) {
		this.freePlate = freePlate;
	}
	
	@Column(name = "anchor_car",nullable = false)
	public Integer getAnchorCar() {
		return this.anchorCar;
	}

	public void setAnchorCar(Integer anchorCar) {
		this.anchorCar = anchorCar;
	}

	@Column(name = "free_car", nullable = false)
	public int getFreeCar() {
		return freeCar;
	}

	public void setFreeCar(int freeCar) {
		this.freeCar = freeCar;
	}

	@Column(name = "special_free_car", nullable = false)
	public int getSpecialFreeCar() {
		return specialFreeCar;
	}

	public void setSpecialFreeCar(int specialFreeCar) {
		this.specialFreeCar = specialFreeCar;
	}

	@Column(name = "work_car", nullable = false)
	public int getWorkCar() {
		return workCar;
	}

	public void setWorkCar(int workCar) {
		this.workCar = workCar;
	}

	@Column(name = "off_work_car", nullable = false)
	public int getOffWorkCar() {
		return offWorkCar;
	}

	public void setOffWorkCar(int offWorkCar) {
		this.offWorkCar = offWorkCar;
	}
	
	@Column(name = "in_lift", nullable = false)
	public int getInLift() {
		return inLift;
	}

	public void setInLift(int inLift) {
		this.inLift = inLift;
	}
	
	@Column(name = "out_lift", nullable = false)
	public int getOutLift() {
		return outLift;
	}

	public void setOutLift(int outLift) {
		this.outLift = outLift;
	}
	
	@Column(name = "in_car")
	public Integer getInCar() {
		return this.inCar;
	}

	public void setInCar(Integer inCar) {
		this.inCar = inCar;
	}

	@Column(name = "out_car")
	public Integer getOutCar() {
		return this.outCar;
	}

	public void setOutCar(Integer outCar) {
		this.outCar = outCar;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "terminal_id")
	public Device getTerminal() {
		return terminal;
	}

	public void setTerminal(Device terminal) {
		this.terminal = terminal;
	}

}
