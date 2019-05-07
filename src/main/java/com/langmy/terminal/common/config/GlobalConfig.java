package com.langmy.terminal.common.config;

public class GlobalConfig {
	
	private String parkingId;
	private Integer spNumber;
	private Integer nowSpNumber;
	private double minBalance;
	private double minPay;
	private int UExpirReminder;
	private int PSpExpirReminder;
	private Integer dataKeepTime;
	private boolean isCountSp;
	private boolean isCharge;
	private boolean isUseRfid;
	private int pattern;
	private boolean PSpLockFlag;
	private boolean plateRecoCameraFlag;
	
	public String getParkingId() {
		return parkingId;
	}
	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	public Integer getSpNumber() {
		return spNumber;
	}
	public void setSpNumber(Integer spNumber) {
		this.spNumber = spNumber;
	}
	public Integer getNowSpNumber() {
		return nowSpNumber;
	}
	public void setNowSpNumber(Integer nowSpNumber) {
		this.nowSpNumber = nowSpNumber;
	}
	public double getMinBalance() {
		return minBalance;
	}
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	public double getMinPay() {
		return minPay;
	}
	public void setMinPay(double minPay) {
		this.minPay = minPay;
	}
	public int getUExpirReminder() {
		return UExpirReminder;
	}
	public void setUExpirReminder(int uExpirReminder) {
		UExpirReminder = uExpirReminder;
	}
	public int getPSpExpirReminder() {
		return PSpExpirReminder;
	}
	public void setPSpExpirReminder(int pSpExpirReminder) {
		PSpExpirReminder = pSpExpirReminder;
	}
	public Integer getDataKeepTime() {
		return dataKeepTime;
	}
	public void setDataKeepTime(Integer dataKeepTime) {
		this.dataKeepTime = dataKeepTime;
	}
	public boolean isCountSp() {
		return isCountSp;
	}
	public void setCountSp(boolean isCountSp) {
		this.isCountSp = isCountSp;
	}
	public boolean isCharge() {
		return isCharge;
	}
	public void setCharge(boolean isCharge) {
		this.isCharge = isCharge;
	}
	public boolean isUseRfid() {
		return isUseRfid;
	}
	public void setUseRfid(boolean isUseRfid) {
		this.isUseRfid = isUseRfid;
	}
	public int getPattern() {
		return pattern;
	}
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	public boolean isPSpLockFlag() {
		return PSpLockFlag;
	}
	public void setPSpLockFlag(boolean pSpLockFlag) {
		PSpLockFlag = pSpLockFlag;
	}
	public boolean isPlateRecoCameraFlag() {
		return plateRecoCameraFlag;
	}
	public void setPlateRecoCameraFlag(boolean plateRecoCameraFlag) {
		this.plateRecoCameraFlag = plateRecoCameraFlag;
	}
	
}
