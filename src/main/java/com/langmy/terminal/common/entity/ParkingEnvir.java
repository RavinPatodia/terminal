package com.langmy.terminal.common.entity;

// Generated 2015-5-12 17:17:42 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.langmy.terminal.common.persistence.IdEntityImpl;

/**
 * ParkingEnvir generated by hbm2java
 */
@Entity
@Table(name = "parking_envir", catalog = "cloud")
public class ParkingEnvir extends IdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -630683395355728423L;
	private String parkingId; // 停车场ID
	private String parkingName;// 停车场名称
	private String parkingTel;// 停车场联系电话
	private String parkingAddr;// 停车场地址
	private Integer spNumber; // 停车场总车位数
	private Integer nowSpNumber; // 停车场当前剩余车位数
	private double minBalance; // 储蓄用户额度提醒
	private int UExpirReminder; // 用户到期时间
	private int PSpExpirReminder; // 车位租赁到期时间
	private Integer dataKeepTime; // 数据保存时间/天
	private boolean isCountSp; // 固定车位用户进出场是否计算车位
	private boolean isCharge; // 是否收费
	private boolean PSpLockFlag; // 是否使用车位锁
	private boolean plateRecoCameraFlag; // (tinyint) 是否使用车位识别摄像机
	private boolean videoShowFlag; // 是否显示视频
	private boolean changeCarFlag; // 是否允许换车 0：不允许，1：允许
	private int changeCarTime; // 换车时间/分钟
	private boolean backupFlag; // 是否启用自动备份
	private String backupPath; // 备份路径
	private String backupTime; // 自动备份时间
	private int logDataKeep; // 操作日志数据保留时间
	private int chargeRecDataKeep; // 收费明细数据保留时间
	private int normalAdmissionDataKeep; // 正常过车数据数据保留时间
	private int violationsAdmissionDataKeep; // 违章过车数据数据保留时间
	private int deviceStateDataKeep; // 设备状态数据数据保留时间
	private int syncDataKeep; // 场内同步数据数据保留时间
	private Integer defaultGroup;// 默认临时客户组
	private String smsIp;// 短信猫
	private String smsPort;// 短信猫端口号

	public ParkingEnvir() {
	}

	public ParkingEnvir(int id, String parkingId, double minBalance,
			String parkingName, int UExpirReminder, int PSpExpirReminder,
			boolean isCountSp, boolean isCharge, String parkingTel,
			String parkingAddr, boolean PSpLockFlag,
			boolean plateRecoCameraFlag, boolean videoShowFlag,
			boolean changeCarFlag, int changeCarTime, boolean backupFlag) {
		this.id = id;
		this.parkingId = parkingId;
		this.minBalance = minBalance;
		this.parkingName = parkingName;
		this.UExpirReminder = UExpirReminder;
		this.PSpExpirReminder = PSpExpirReminder;
		this.isCountSp = isCountSp;
		this.isCharge = isCharge;
		this.parkingTel = parkingTel;
		this.parkingAddr = parkingAddr;
		this.PSpLockFlag = PSpLockFlag;
		this.plateRecoCameraFlag = plateRecoCameraFlag;
		this.videoShowFlag = videoShowFlag;
		this.changeCarFlag = changeCarFlag;
		this.changeCarTime = changeCarTime;
		this.backupFlag = backupFlag;
	}

	public ParkingEnvir(int id, String parkingId, Integer spNumber,
			Integer nowSpNumber, double minBalance, String parkingName,
			int UExpirReminder, int PSpExpirReminder, Integer dataKeepTime,
			boolean isCountSp, boolean isCharge, String parkingTel,
			String parkingAddr, boolean PSpLockFlag,
			boolean plateRecoCameraFlag, boolean videoShowFlag,
			boolean changeCarFlag, int changeCarTime, boolean backupFlag,
			String backupPath, String backupTime) {
		this.id = id;
		this.parkingId = parkingId;
		this.spNumber = spNumber;
		this.nowSpNumber = nowSpNumber;
		this.minBalance = minBalance;
		this.parkingName = parkingName;
		this.UExpirReminder = UExpirReminder;
		this.PSpExpirReminder = PSpExpirReminder;
		this.dataKeepTime = dataKeepTime;
		this.isCountSp = isCountSp;
		this.isCharge = isCharge;
		this.parkingTel = parkingTel;
		this.parkingAddr = parkingAddr;
		this.PSpLockFlag = PSpLockFlag;
		this.plateRecoCameraFlag = plateRecoCameraFlag;
		this.videoShowFlag = videoShowFlag;
		this.changeCarFlag = changeCarFlag;
		this.changeCarTime = changeCarTime;
		this.backupFlag = backupFlag;
		this.backupPath = backupPath;
		this.backupTime = backupTime;
	}

	@Column(name = "parking_id", nullable = false, length = 20)
	public String getParkingId() {
		return this.parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	@Column(name = "parking_name", nullable = false, length = 50)
	public String getParkingName() {
		return this.parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	@Column(name = "parking_tel", nullable = false)
	public String getParkingTel() {
		return parkingTel;
	}

	public void setParkingTel(String parkingTel) {
		this.parkingTel = parkingTel;
	}

	@Column(name = "parking_addr", nullable = false)
	public String getParkingAddr() {
		return parkingAddr;
	}

	public void setParkingAddr(String parkingAddr) {
		this.parkingAddr = parkingAddr;
	}

	@Column(name = "sp_number")
	public Integer getSpNumber() {
		return this.spNumber;
	}

	public void setSpNumber(Integer spNumber) {
		this.spNumber = spNumber;
	}

	@Column(name = "now_sp_number")
	public Integer getNowSpNumber() {
		return this.nowSpNumber;
	}

	public void setNowSpNumber(Integer nowSpNumber) {
		this.nowSpNumber = nowSpNumber;
	}

	@Column(name = "min_balance", nullable = false, precision = 10)
	public double getMinBalance() {
		return this.minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	@Column(name = "u_expir_reminder", nullable = false)
	public int getUExpirReminder() {
		return this.UExpirReminder;
	}

	public void setUExpirReminder(int UExpirReminder) {
		this.UExpirReminder = UExpirReminder;
	}

	@Column(name = "p_sp_expir_reminder", nullable = false)
	public int getPSpExpirReminder() {
		return this.PSpExpirReminder;
	}

	public void setPSpExpirReminder(int PSpExpirReminder) {
		this.PSpExpirReminder = PSpExpirReminder;
	}

	@Column(name = "data_keep_time")
	public Integer getDataKeepTime() {
		return this.dataKeepTime;
	}

	public void setDataKeepTime(Integer dataKeepTime) {
		this.dataKeepTime = dataKeepTime;
	}

	@Column(name = "is_count_sp", nullable = false)
	public boolean isIsCountSp() {
		return this.isCountSp;
	}

	public void setIsCountSp(boolean isCountSp) {
		this.isCountSp = isCountSp;
	}

	@Column(name = "is_charge", nullable = false)
	public boolean isIsCharge() {
		return this.isCharge;
	}

	public void setIsCharge(boolean isCharge) {
		this.isCharge = isCharge;
	}

	@Column(name = "p_sp_lock_flag", nullable = false)
	public boolean isPSpLockFlag() {
		return this.PSpLockFlag;
	}

	public void setPSpLockFlag(boolean PSpLockFlag) {
		this.PSpLockFlag = PSpLockFlag;
	}

	@Column(name = "plate_reco_camera_flag", nullable = false)
	public boolean isPlateRecoCameraFlag() {
		return this.plateRecoCameraFlag;
	}

	public void setPlateRecoCameraFlag(boolean plateRecoCameraFlag) {
		this.plateRecoCameraFlag = plateRecoCameraFlag;
	}

	@Column(name = "video_show_flag", nullable = false)
	public boolean isVideoShowFlag() {
		return this.videoShowFlag;
	}

	public void setVideoShowFlag(boolean videoShowFlag) {
		this.videoShowFlag = videoShowFlag;
	}

	@Column(name = "change_car_flag", nullable = false)
	public boolean isChangeCarFlag() {
		return this.changeCarFlag;
	}

	public void setChangeCarFlag(boolean changeCarFlag) {
		this.changeCarFlag = changeCarFlag;
	}

	@Column(name = "change_car_time", nullable = false)
	public int getChangeCarTime() {
		return this.changeCarTime;
	}

	public void setChangeCarTime(int changeCarTime) {
		this.changeCarTime = changeCarTime;
	}

	@Column(name = "backup_flag", nullable = false)
	public boolean isBackupFlag() {
		return this.backupFlag;
	}

	public void setBackupFlag(boolean backupFlag) {
		this.backupFlag = backupFlag;
	}

	@Column(name = "backup_path", length = 100)
	public String getBackupPath() {
		return this.backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}

	@Column(name = "backup_time", length = 20)
	public String getBackupTime() {
		return this.backupTime;
	}

	public void setBackupTime(String backupTime) {
		this.backupTime = backupTime;
	}

	@Column(name = "log_data_keep", nullable = false)
	public int getLogDataKeep() {
		return logDataKeep;
	}

	public void setLogDataKeep(int logDataKeep) {
		this.logDataKeep = logDataKeep;
	}

	@Column(name = "charge_rec_data_keep", nullable = false)
	public int getChargeRecDataKeep() {
		return chargeRecDataKeep;
	}

	public void setChargeRecDataKeep(int chargeRecDataKeep) {
		this.chargeRecDataKeep = chargeRecDataKeep;
	}

	@Column(name = "normal_admission_data_keep", nullable = false)
	public int getNormalAdmissionDataKeep() {
		return normalAdmissionDataKeep;
	}

	public void setNormalAdmissionDataKeep(int normalAdmissionDataKeep) {
		this.normalAdmissionDataKeep = normalAdmissionDataKeep;
	}

	@Column(name = "violations_admission_data_keep", nullable = false)
	public int getViolationsAdmissionDataKeep() {
		return violationsAdmissionDataKeep;
	}

	public void setViolationsAdmissionDataKeep(int violationsAdmissionDataKeep) {
		this.violationsAdmissionDataKeep = violationsAdmissionDataKeep;
	}

	@Column(name = "device_state_data_keep", nullable = false)
	public int getDeviceStateDataKeep() {
		return deviceStateDataKeep;
	}

	public void setDeviceStateDataKeep(int deviceStateDataKeep) {
		this.deviceStateDataKeep = deviceStateDataKeep;
	}

	@Column(name = "sync_data_keep", nullable = false)
	public int getSyncDataKeep() {
		return syncDataKeep;
	}

	public void setSyncDataKeep(int syncDataKeep) {
		this.syncDataKeep = syncDataKeep;
	}

	@Column(name = "default_group")
	public Integer getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(Integer defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	@Column(name = "sms_ip", nullable = false, length = 16)
	public String getSmsIp() {
		return smsIp;
	}

	public void setSmsIp(String smsIp) {
		this.smsIp = smsIp;
	}

	@Column(name = "sms_port", nullable = false, length = 10)
	public String getSmsPort() {
		return smsPort;
	}

	public void setSmsPort(String smsPort) {
		this.smsPort = smsPort;
	}

}
