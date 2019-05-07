package com.langmy.terminal.modules.report.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.excel.annotation.ExcelField;

/**
 * 操作员结账记录Module
 * 
 * @author ZZD
 *
 */
public class ChargeRecModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4051929471157299850L;
	/**
	 * 操作员id
	 */
	private Integer operId;

	private Double operChargeSum;
	/**
	 * 操作员账户名
	 */
	private String operName;
	/**
	 * 操作员名字
	 */
	private String name;
	/**
	 * 结账时间
	 */
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date payTime;
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
	/**
	 * 应付金额
	 */
	private double amoutPay;
	/**
	 * 实付金额
	 */
	private double actualPay;

	@ExcelField(title = "操作员账户名", align = 2, sort = 10)
	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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
	@ExcelField(title = "操作姓名", align = 2, sort = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOperId() {
		return operId;
	}

	public void setOperId(Integer operId) {
		this.operId = operId;
	}

	@ExcelField(title = "收费次数", align = 2, sort = 30)
	public Double getOperChargeSum() {
		return operChargeSum;
	}

	public void setOperChargeSum(Double operChargeSum) {
		this.operChargeSum = operChargeSum;
	}

}
