package com.langmy.terminal.modules.sys.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

/**
 * 操作员交接班记录Model
 * 
 * @author ZZD
 *
 */
public class OperShiftModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3946864211412477445L;

	private Date workTime;// 上班时间
	private Date offWorkTime;// 下班时间

	private double totalPay;// 总流水==
	private double tempCash;// 临时车辆收费金额==
	private double memberAccountPay;// 普通会员账户扣款金额==
	private double memberCash;// 普通会员支付现金==
	private double amoutPay;// 停车应收现金金额
	private double actualPay;// 停车实收现金金额

	private int chargeCar;// 收费车辆数
	private int tempCar;// 临时车辆出场数==
	private int memberCar;// 普通会员出场数==
	private int anchorCar;// 长期会员出场数
	private int freeCar;// VIP免费车辆出场数
	private int freePlate;// 免费车牌数
	private int specialFreeCar;// 发生费用免费出场数

	private int workCar;// 上班时场内停车数
	private int offWorkCar;// 下班时场内停车数
	private int outLift;// 手动开闸出场数
	private int outCar;// 自动开闸出场数
	/**
	 * 交班操作员
	 */
	private Integer offOperPK;// 交班操作员主键
	private String offOperName;// 交班操作员姓名
	/**
	 * 接班操作员
	 */
	private Integer workOperPK;// 接班操作员主键
	private String workOperName;// 接班操作员姓名
	private String pwd;
	/**
	 * 终端机
	 */
	private Integer terminalPK;// 终端机主键
	private String terminalName;// 终端机名字

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date workTimeFrom;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date workTimeTo;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date offTimeFrom;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date offTimeTo;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOffWorkTime() {
		return offWorkTime;
	}

	public void setOffWorkTime(Date offWorkTime) {
		this.offWorkTime = offWorkTime;
	}

	public double getAmoutPay() {
		return amoutPay;
	}

	public void setAmoutPay(double amoutPay) {
		this.amoutPay = amoutPay;
	}

	public double getActualPay() {
		return actualPay;
	}

	public void setActualPay(double actualPay) {
		this.actualPay = actualPay;
	}

	public int getChargeCar() {
		return chargeCar;
	}

	public void setChargeCar(int chargeCar) {
		this.chargeCar = chargeCar;
	}

	public int getFreeCar() {
		return freeCar;
	}

	public void setFreeCar(int freeCar) {
		this.freeCar = freeCar;
	}

	public int getSpecialFreeCar() {
		return specialFreeCar;
	}

	public void setSpecialFreeCar(int specialFreeCar) {
		this.specialFreeCar = specialFreeCar;
	}

	public int getWorkCar() {
		return workCar;
	}

	public void setWorkCar(int workCar) {
		this.workCar = workCar;
	}

	public int getOffWorkCar() {
		return offWorkCar;
	}

	public void setOffWorkCar(int offWorkCar) {
		this.offWorkCar = offWorkCar;
	}

	public int getOutLift() {
		return outLift;
	}

	public void setOutLift(int outLift) {
		this.outLift = outLift;
	}

	public Integer getOffOperPK() {
		return offOperPK;
	}

	public void setOffOperPK(Integer offOperPK) {
		this.offOperPK = offOperPK;
	}

	public String getOffOperName() {
		return offOperName;
	}

	public void setOffOperName(String offOperName) {
		this.offOperName = offOperName;
	}

	public Integer getWorkOperPK() {
		return workOperPK;
	}

	public void setWorkOperPK(Integer workOperPK) {
		this.workOperPK = workOperPK;
	}

	public String getWorkOperName() {
		return workOperName;
	}

	public void setWorkOperName(String workOperName) {
		this.workOperName = workOperName;
	}

	public Integer getTerminalPK() {
		return terminalPK;
	}

	public void setTerminalPK(Integer terminalPK) {
		this.terminalPK = terminalPK;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public Date getWorkTimeFrom() {
		return workTimeFrom;
	}

	public void setWorkTimeFrom(Date workTimeFrom) {
		this.workTimeFrom = workTimeFrom;
	}

	public Date getWorkTimeTo() {
		return workTimeTo;
	}

	public void setWorkTimeTo(Date workTimeTo) {
		this.workTimeTo = workTimeTo;
	}

	public Date getOffTimeFrom() {
		return offTimeFrom;
	}

	public void setOffTimeFrom(Date offTimeFrom) {
		this.offTimeFrom = offTimeFrom;
	}

	public Date getOffTimeTo() {
		return offTimeTo;
	}

	public void setOffTimeTo(Date offTimeTo) {
		this.offTimeTo = offTimeTo;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getAnchorCar() {
		return anchorCar;
	}

	public void setAnchorCar(int anchorCar) {
		this.anchorCar = anchorCar;
	}

	public int getOutCar() {
		return outCar;
	}

	public void setOutCar(int outCar) {
		this.outCar = outCar;
	}

	public void setTempCash(double tempCash) {
		this.tempCash = tempCash;
	}

	public double getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(double totalPay) {
		this.totalPay = totalPay;
	}

	public double getMemberAccountPay() {
		return memberAccountPay;
	}

	public void setMemberAccountPay(double memberAccountPay) {
		this.memberAccountPay = memberAccountPay;
	}

	public double getMemberCash() {
		return memberCash;
	}

	public void setMemberCash(double memberCash) {
		this.memberCash = memberCash;
	}

	public int getMemberCar() {
		return memberCar;
	}

	public void setMemberCar(int memberCar) {
		this.memberCar = memberCar;
	}

	public int getTempCar() {
		return tempCar;
	}

	public void setTempCar(int tempCar) {
		this.tempCar = tempCar;
	}

	public double getTempCash() {
		return tempCash;
	}

	public int getFreePlate() {
		return freePlate;
	}

	public void setFreePlate(int freePlate) {
		this.freePlate = freePlate;
	}

}
