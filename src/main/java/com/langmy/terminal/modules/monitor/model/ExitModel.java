package com.langmy.terminal.modules.monitor.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

/**
 * 出场model类
 * 
 * @author lxj
 *
 */
public class ExitModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3435896368679542554L;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date outTime;// 出场时间
	private Integer outRecoWay;// 出场车牌识别方式
	private String outPicUrl;// 出场抓拍照片
	private double fee;// 应付金额
	private boolean payFlag;// 是否已付款

	// chargerec
	private int payType;// 付款方式（0:账户扣款/1:手工收费/2:缴费机/）
	private Date payTime;// 付款时间
	private double dctPay;// 优惠金额
	private double amoutPay;// 应付金额
	private double actualPay;// 实付金额

	private Date inTime;// 入场时间
	private Integer inRecoWay;// 入场车牌识别方式
	private String inPicUrl;// 入场抓拍照片

	private Integer userType;// 0:临时用户。1：黑名单用户。2：普通会员。3：长期会员

	private boolean successflag;// 判断matchingAdmission执行是否顺利的标志位

	private Integer admissionRecPK;// 出入场记录主键

	private Integer priority; // 给车牌匹配中的算法用的优先级

	// bayonet
	private Integer bayonetExitPK;// 出场卡口主键
	private String bayonetExitName;// 出场卡口名称

	private Integer bayonetEntrancePK;// 入场卡口主键
	private String bayonetEntranceName;// 入场卡口名称

	// 车道
	private Integer drivewayPK;// 出场车道的主键
	private String drivewayName;// 出场车道的名称

	private CarModel carModel;

	private String matchingCarPKs;// 智能匹配得到的多辆车的id

	// User
	private Integer userPK;// 用户主键
	private String uacc;// 用户名
	private String uname;// 用户名字
	private Boolean hasParkingSpaces;// 是否拥有车位

	// Blacklist
	private String listReason;// 列入黑名单原因
	private Date listTime;// 列入黑名单时间

	// 优惠规则组
	private Integer dctRuleGroupPK;// 优惠规则组主键
	private String dctRuleGroupName;// 优惠规则组名称
	private String dctRulePKs;// 多个优惠规则的主键

	// 收费规则
	private String chargeRuleNames;// 多个收费规则的名称
	private String chargeRulePKs;// 多个收费规则的主键

	private String msg;// 通知信息
	private boolean msgFlag;// 通知成功与否标志位

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Integer getOutRecoWay() {
		return outRecoWay;
	}

	public void setOutRecoWay(Integer outRecoWay) {
		this.outRecoWay = outRecoWay;
	}

	public String getOutPicUrl() {
		return outPicUrl;
	}

	public void setOutPicUrl(String outPicUrl) {
		this.outPicUrl = outPicUrl;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Integer getBayonetExitPK() {
		return bayonetExitPK;
	}

	public void setBayonetExitPK(Integer bayonetExitPK) {
		this.bayonetExitPK = bayonetExitPK;
	}

	public String getBayonetExitName() {
		return bayonetExitName;
	}

	public void setBayonetExitName(String bayonetExitName) {
		this.bayonetExitName = bayonetExitName;
	}

	public Integer getUserPK() {
		return userPK;
	}

	public void setUserPK(Integer userPK) {
		this.userPK = userPK;
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

	public Boolean getHasParkingSpaces() {
		return hasParkingSpaces;
	}

	public void setHasParkingSpaces(Boolean hasParkingSpaces) {
		this.hasParkingSpaces = hasParkingSpaces;
	}

	public String getListReason() {
		return listReason;
	}

	public void setListReason(String listReason) {
		this.listReason = listReason;
	}

	public Date getListTime() {
		return listTime;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
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

	public String getDctRuleGroupName() {
		return dctRuleGroupName;
	}

	public void setDctRuleGroupName(String dctRuleGroupName) {
		this.dctRuleGroupName = dctRuleGroupName;
	}

	public String getChargeRuleNames() {
		return chargeRuleNames;
	}

	public void setChargeRuleNames(String chargeRuleNames) {
		this.chargeRuleNames = chargeRuleNames;
	}

	public String getMatchingCarPKs() {
		return matchingCarPKs;
	}

	public void setMatchingCarPKs(String matchingCarPKs) {
		this.matchingCarPKs = matchingCarPKs;
	}

	public boolean isPayFlag() {
		return payFlag;
	}

	public void setPayFlag(boolean payFlag) {
		this.payFlag = payFlag;
	}

	public Integer getAdmissionRecPK() {
		return admissionRecPK;
	}

	public void setAdmissionRecPK(Integer admissionRecPK) {
		this.admissionRecPK = admissionRecPK;
	}

	public Integer getDctRuleGroupPK() {
		return dctRuleGroupPK;
	}

	public void setDctRuleGroupPK(Integer dctRuleGroupPK) {
		this.dctRuleGroupPK = dctRuleGroupPK;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public double getDctPay() {
		return dctPay;
	}

	public void setDctPay(double dctPay) {
		this.dctPay = dctPay;
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

	public boolean isSuccessflag() {
		return successflag;
	}

	public void setSuccessflag(boolean successflag) {
		this.successflag = successflag;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDctRulePKs() {
		return dctRulePKs;
	}

	public void setDctRulePKs(String dctRulePKs) {
		this.dctRulePKs = dctRulePKs;
	}

	public String getChargeRulePKs() {
		return chargeRulePKs;
	}

	public void setChargeRulePKs(String chargeRulePKs) {
		this.chargeRulePKs = chargeRulePKs;
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
