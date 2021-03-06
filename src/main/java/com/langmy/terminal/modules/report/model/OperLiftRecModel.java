package com.langmy.terminal.modules.report.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.excel.annotation.ExcelField;

/**
 * 手动抬杆记录Module
 * 
 * @author ZZD
 *
 */
public class OperLiftRecModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3716884106154885213L;
	/**
	 * 卡口
	 */
	private String bayonetName;
	/**
	 * 闸机
	 */
	private String deviceName;
	/**
	 * 操作员账户名
	 */
	private String operName;
	/**
	 * 操作员id
	 */
	private Integer operId;
	/**
	 * 操作员抬杆次数
	 */
	private int operLiftSum;
	/**
	 * 操作员姓名
	 */
	private String name;
	/**
	 * 时间
	 */
	private Date openTime;
	/**
	 * 开闸原因
	 */
	private String openReason;
	/**
	 * 开始时间
	 */
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date startTime;
	/**
	 * 结束时间
	 */
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date endTime;

	public String getBayonetName() {
		return bayonetName;
	}

	public void setBayonetName(String bayonetName) {
		this.bayonetName = bayonetName;
	}

	@ExcelField(title = "操作员账户名", align = 2, sort = 10)
	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}
	
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public String getOpenReason() {
		return openReason;
	}

	public void setOpenReason(String openReason) {
		this.openReason = openReason;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	@ExcelField(title = "操作员姓名", align = 2, sort = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title = "抬杆次数", align = 2, sort = 30)
	public int getOperLiftSum() {
		return operLiftSum;
	}

	public void setOperLiftSum(int operLiftSum) {
		this.operLiftSum = operLiftSum;
	}

	public Integer getOperId() {
		return operId;
	}

	public void setOperId(Integer operId) {
		this.operId = operId;
	}
	
}
