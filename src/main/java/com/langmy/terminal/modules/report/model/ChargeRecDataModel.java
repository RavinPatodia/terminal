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
public class ChargeRecDataModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4051929471157299850L;
	/**
	 * 操作员id
	 */
	private Integer operId;
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

	private String payTimeStr;
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
	/**
	 * 付款方式（0:账户扣款/1:手工收费/2:缴费机/）
	 */
	private String payType;

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

	@ExcelField(title = "应付金额", align = 2, sort = 30)
	public double getAmoutPay() {
		return amoutPay;
	}

	public void setAmoutPay(double amoutPay) {
		this.amoutPay = amoutPay;
	}

	@ExcelField(title = "实付金额", align = 2, sort = 40)
	public double getActualPay() {
		return actualPay;
	}

	public void setActualPay(double actualPay) {
		this.actualPay = actualPay;
	}
	@ExcelField(title = "操作员姓名", align = 2, sort = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title = "支付时间", align = 2, sort = 50)
	public String getPayTimeStr() {
		return payTimeStr;
	}

	public void setPayTimeStr(String payTimeStr) {
		this.payTimeStr = payTimeStr;
	}

	@ExcelField(title = "付款方式", align = 2, sort = 60)
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
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

	public Integer getOperId() {
		return operId;
	}

	public void setOperId(Integer operId) {
		this.operId = operId;
	}
}
