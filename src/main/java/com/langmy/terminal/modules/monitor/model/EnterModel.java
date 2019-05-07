package com.langmy.terminal.modules.monitor.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import com.langmy.terminal.common.model.BaseModel;

/**
 * 入场model类
 * 
 * @author lxj
 *
 */
public class EnterModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -208695227294198363L;
	private Date inTime;// 入场时间
	private Integer inRecoWay;// 入场车牌识别方式
	private String inPicUrl;// 入场抓拍照片

	private Integer type;// 0:临时用户。1：黑名单用户。2：普通会员。3：长期会员

	// bayonet
	private Integer bayonetEntrancePK;// 入场卡口的主键
	private String bayonetEntranceName;// 入场卡口的名称

	// 车道
	private Integer drivewayPK;// 入场车道的主键
	private String drivewayName;// 入场车道的名称

	private CarModel carModel;

	// User
	private Integer userPK;// 用户主键
	private String uacc;// 用户名
	private String uname;// 用户姓名
	private int uaccType;// 0：临时。1：会员
	private Boolean hasParkingSpaces;// 是否拥有停车位

	// Blacklist
	BlacklistModel blacklistModel;
	private boolean blacklistFlag;// 是否是再黑名单中 true为是，false为否
	private boolean blacklistInFlag;//黑名单用户是否允许进场

	// Account
	private Date expirationTime;// 长期会员有效时间
	private double balance;// 余额

	// Psp 开启的车位
	private Integer pspPK;// 车位主键
	private String pspId;// 车位编号

	private String terminalIP;// 终端IP地址
	private String msg;// 通知信息
	private boolean msgFlag;// 通知成功与否标志位

	private boolean permissionFlag=true;// 是否有权限进入停车场
	private boolean openBrakeMachineFlag;// 判断闸机是否开启
	private boolean inFlag;//车辆是否成功入场 1：入场 0：尚未入场

	private List<String> errorMsg = Lists.newArrayList();
	private List<String> ledMsg = Lists.newArrayList();//最后在Led显示屏中显示的内容
	
	public List<String> getLedMsg() {
		return ledMsg;
	}

	public void setLedMsg(List<String> ledMsg) {
		this.ledMsg = ledMsg;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Integer getInRecoWay() {
		return inRecoWay;
	}

	public void setInRecoWay(Integer inRecoWay) {
		this.inRecoWay = inRecoWay;
	}

	public String getInPicUrl() {
		return inPicUrl;
	}

	public void setInPicUrl(String inPicUrl) {
		this.inPicUrl = inPicUrl;
	}

	public Integer getBayonetEntrancePK() {
		return bayonetEntrancePK;
	}

	public void setBayonetEntrancePK(Integer bayonetEntrancePK) {
		this.bayonetEntrancePK = bayonetEntrancePK;
	}

	public String getBayonetEntranceName() {
		return bayonetEntranceName;
	}

	public void setBayonetEntranceName(String bayonetEntranceName) {
		this.bayonetEntranceName = bayonetEntranceName;
	}

	public Integer getUserPK() {
		return userPK;
	}

	public void setUserPK(Integer userPK) {
		this.userPK = userPK;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUacc() {
		return uacc;
	}

	public void setUacc(String uacc) {
		this.uacc = uacc;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getUaccType() {
		return uaccType;
	}

	public void setUaccType(int uaccType) {
		this.uaccType = uaccType;
	}

	public Boolean getHasParkingSpaces() {
		return hasParkingSpaces;
	}

	public void setHasParkingSpaces(Boolean hasParkingSpaces) {
		this.hasParkingSpaces = hasParkingSpaces;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Integer getPspPK() {
		return pspPK;
	}

	public void setPspPK(Integer pspPK) {
		this.pspPK = pspPK;
	}

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	public String getTerminalIP() {
		return terminalIP;
	}

	public void setTerminalIP(String terminalIP) {
		this.terminalIP = terminalIP;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(boolean msgFlag) {
		this.msgFlag = msgFlag;
	}

	public Integer getDrivewayPK() {
		return drivewayPK;
	}

	public void setDrivewayPK(Integer drivewayPK) {
		this.drivewayPK = drivewayPK;
	}

	public String getDrivewayName() {
		return drivewayName;
	}

	public void setDrivewayName(String drivewayName) {
		this.drivewayName = drivewayName;
	}

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public BlacklistModel getBlacklistModel() {
		return blacklistModel;
	}

	public void setBlacklistModel(BlacklistModel blacklistModel) {
		this.blacklistModel = blacklistModel;
	}

	public boolean isBlacklistFlag() {
		return blacklistFlag;
	}

	public void setBlacklistFlag(boolean blacklistFlag) {
		this.blacklistFlag = blacklistFlag;
	}

	public List<String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(List<String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isPermissionFlag() {
		return permissionFlag;
	}

	public void setPermissionFlag(boolean permissionFlag) {
		this.permissionFlag = permissionFlag;
	}

	public boolean isOpenBrakeMachineFlag() {
		return openBrakeMachineFlag;
	}

	public void setOpenBrakeMachineFlag(boolean openBrakeMachineFlag) {
		this.openBrakeMachineFlag = openBrakeMachineFlag;
	}

	public boolean isBlacklistInFlag() {
		return blacklistInFlag;
	}

	public void setBlacklistInFlag(boolean blacklistInFlag) {
		this.blacklistInFlag = blacklistInFlag;
	}

	public boolean isInFlag() {
		return inFlag;
	}

	public void setInFlag(boolean inFlag) {
		this.inFlag = inFlag;
	}
}
