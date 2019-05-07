package com.langmy.terminal.modules.sys.model;

import java.util.List;

import com.langmy.terminal.common.model.BaseModel;

/**
 * 环境变量model类
 * 
 * @author lxj
 *
 */
public class ParkingEnvirModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698888948215847743L;

	// 系统参数配置 数据保留时间
	private int logDataKeep;// 操作日志保留时间/月
	private int chargeRecDataKeep;// 收费明细数据保留时间/月
	private int normalAdmissionDataKeep;// 正常过车数据保留时间/月
	private int violationsAdmissionDataKeep;// 违章过车数据保留时间/月
	private int deviceStateDataKeep;// 设备状态数据保留时间/月
	private int syncDataKeep;// 场内同步数据保留时间/月
	// 参数配置 系统参数
	private String parkingId;// 停车场ID
	private Integer spNumber;// 停车场总车位数
	private Integer nowSpNumber;// 停车场当前剩余车位数
	private int pattern;// 识别模式（卡识别0，车牌识别1，双重识别2）
	private int uexpirReminder;// 用户到期时间
	private int pspExpirReminder;// 车位租赁到期时间
	private boolean plateRecoCameraFlag;// 是否使用车位识别摄像机
	private String plateRecoCameraFlagStr;
	private boolean chargeFlag;// 是否收费
	private String chargeFlagStr;
	private boolean useRfidFlag;// 是否使用射频识别
	private String useRfidFlagStr;
	private boolean videoShowFlag;// 是否显示视频
	private String videoShowFlagStr;
	private boolean changeCarFlag;// 是否允许换车
	private String changeCarFlagStr;
	private boolean pspLockFlag;// 是否使用车位锁
	private String pspLockFlagStr;
	private boolean countSpFlag;// 固定车位用户进出场是否计算车位
	private String countSpFlagStr;
	private double minBalance;// 储蓄用户额度提醒
	private double minPay;// 最小缴费金额
	private int changeCarTime;// 换车时间/分
	private Integer dataKeepTime;// 数据保存时间/天
	private String backupPath; // 备份路径
	private String backupTime; // 备份时间
	private boolean backupFlag;// 是否启用自动备份
	private String backupFlagStr;
	private String loadPath; // 还原文件路径
	private Integer defaultGroup;// 默认临时客户组
	private String defaultGroupName;// 默认临时客户组
	private List<SmsRecModel> smsRecModels;// 短信猫

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

	public int getUexpirReminder() {
		return uexpirReminder;
	}

	public void setUexpirReminder(int uexpirReminder) {
		this.uexpirReminder = uexpirReminder;
	}

	public int getPspExpirReminder() {
		return pspExpirReminder;
	}

	public void setPspExpirReminder(int pspExpirReminder) {
		this.pspExpirReminder = pspExpirReminder;
	}

	public Integer getDataKeepTime() {
		return dataKeepTime;
	}

	public void setDataKeepTime(Integer dataKeepTime) {
		this.dataKeepTime = dataKeepTime;
	}

	public boolean isCountSpFlag() {
		return countSpFlag;
	}

	public int getPattern() {
		return pattern;
	}

	public void setPattern(int pattern) {
		this.pattern = pattern;
	}

	public boolean isPlateRecoCameraFlag() {
		return plateRecoCameraFlag;
	}

	public void setPlateRecoCameraFlag(boolean plateRecoCameraFlag) {
		this.plateRecoCameraFlag = plateRecoCameraFlag;
	}

	public boolean isChargeFlag() {
		return chargeFlag;
	}

	public void setChargeFlag(boolean chargeFlag) {
		this.chargeFlag = chargeFlag;
	}

	public boolean isUseRfidFlag() {
		return useRfidFlag;
	}

	public void setUseRfidFlag(boolean useRfidFlag) {
		this.useRfidFlag = useRfidFlag;
	}

	public boolean isPspLockFlag() {
		return pspLockFlag;
	}

	public void setPspLockFlag(boolean pspLockFlag) {
		this.pspLockFlag = pspLockFlag;
	}

	public void setCountSpFlag(boolean countSpFlag) {
		this.countSpFlag = countSpFlag;
	}

	public boolean isChangeCarFlag() {
		return changeCarFlag;
	}

	public void setChangeCarFlag(boolean changeCarFlag) {
		this.changeCarFlag = changeCarFlag;
	}

	public void setVideoShowFlag(boolean videoShowFlag) {
		this.videoShowFlag = videoShowFlag;
	}

	public String getLoadPath() {
		return loadPath;
	}

	public void setLoadPath(String loadPath) {
		this.loadPath = loadPath;
	}

	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}

	public String getBackupTime() {
		return backupTime;
	}

	public void setBackupTime(String backupTime) {
		this.backupTime = backupTime;
	}

	public List<SmsRecModel> getSmsRecModels() {
		return smsRecModels;
	}

	public void setSmsRecModels(List<SmsRecModel> smsRecModels) {
		this.smsRecModels = smsRecModels;
	}

	public int getLogDataKeep() {
		return logDataKeep;
	}

	public void setLogDataKeep(int logDataKeep) {
		this.logDataKeep = logDataKeep;
	}

	public int getChargeRecDataKeep() {
		return chargeRecDataKeep;
	}

	public void setChargeRecDataKeep(int chargeRecDataKeep) {
		this.chargeRecDataKeep = chargeRecDataKeep;
	}

	public int getNormalAdmissionDataKeep() {
		return normalAdmissionDataKeep;
	}

	public void setNormalAdmissionDataKeep(int normalAdmissionDataKeep) {
		this.normalAdmissionDataKeep = normalAdmissionDataKeep;
	}

	public int getViolationsAdmissionDataKeep() {
		return violationsAdmissionDataKeep;
	}

	public void setViolationsAdmissionDataKeep(int violationsAdmissionDataKeep) {
		this.violationsAdmissionDataKeep = violationsAdmissionDataKeep;
	}

	public int getDeviceStateDataKeep() {
		return deviceStateDataKeep;
	}

	public void setDeviceStateDataKeep(int deviceStateDataKeep) {
		this.deviceStateDataKeep = deviceStateDataKeep;
	}

	public int getSyncDataKeep() {
		return syncDataKeep;
	}

	public void setSyncDataKeep(int syncDataKeep) {
		this.syncDataKeep = syncDataKeep;
	}

	public boolean isVideoShowFlag() {
		return videoShowFlag;
	}

	public int getChangeCarTime() {
		return changeCarTime;
	}

	public void setChangeCarTime(int changeCarTime) {
		this.changeCarTime = changeCarTime;
	}

	public String getPlateRecoCameraFlagStr() {
		return plateRecoCameraFlagStr;
	}

	public void setPlateRecoCameraFlagStr(String plateRecoCameraFlagStr) {
		this.plateRecoCameraFlagStr = plateRecoCameraFlagStr;
	}

	public String getChargeFlagStr() {
		return chargeFlagStr;
	}

	public void setChargeFlagStr(String chargeFlagStr) {
		this.chargeFlagStr = chargeFlagStr;
	}

	public String getUseRfidFlagStr() {
		return useRfidFlagStr;
	}

	public void setUseRfidFlagStr(String useRfidFlagStr) {
		this.useRfidFlagStr = useRfidFlagStr;
	}

	public String getVideoShowFlagStr() {
		return videoShowFlagStr;
	}

	public void setVideoShowFlagStr(String videoShowFlagStr) {
		this.videoShowFlagStr = videoShowFlagStr;
	}

	public String getChangeCarFlagStr() {
		return changeCarFlagStr;
	}

	public void setChangeCarFlagStr(String changeCarFlagStr) {
		this.changeCarFlagStr = changeCarFlagStr;
	}

	public String getPspLockFlagStr() {
		return pspLockFlagStr;
	}

	public void setPspLockFlagStr(String pspLockFlagStr) {
		this.pspLockFlagStr = pspLockFlagStr;
	}

	public String getCountSpFlagStr() {
		return countSpFlagStr;
	}

	public void setCountSpFlagStr(String countSpFlagStr) {
		this.countSpFlagStr = countSpFlagStr;
	}

	public Integer getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(Integer defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public String getDefaultGroupName() {
		return defaultGroupName;
	}

	public void setDefaultGroupName(String defaultGroupName) {
		this.defaultGroupName = defaultGroupName;
	}

	public boolean isBackupFlag() {
		return backupFlag;
	}

	public void setBackupFlag(boolean backupFlag) {
		this.backupFlag = backupFlag;
	}

	public String getBackupFlagStr() {
		return backupFlagStr;
	}

	public void setBackupFlagStr(String backupFlagStr) {
		this.backupFlagStr = backupFlagStr;
	}

}
