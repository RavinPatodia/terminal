package com.langmy.terminal.common.entity;

// Generated 2015-2-3 18:18:41 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * ChargeRec generated by hbm2java
 */
@Entity
@Table(name = "charge_rec", catalog = "cloud")
public class ChargeRec extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8774059073480687534L;
	private AdminssionRec adminssionRec; //出入场记录主键
	private ChargeRule chargeRule; //收费规则ID 
	private DctRuleGroup dctRuleGroup; //优惠规则组
	private Operater operater; //操作员名称
	private int payType; //付款方式（0:账户扣款/1:手工收费/2:缴费机/）
	private Date payTime; //付款时间
	private double dctPay; //优惠金额
	private double amoutPay; //应付金额
	private double actualPay; //实付金额
	private List<DctRec> dctRecs = Lists.newArrayList(); //优惠情况记录
	
	public ChargeRec() {
	}

	public ChargeRec(AdminssionRec adminssionRec, int payType, Date payTime,
			double amoutPay, double actualPay) {
		this.adminssionRec = adminssionRec;
		this.payType = payType;
		this.payTime = payTime;
		this.amoutPay = amoutPay;
		this.actualPay = actualPay;
	}

	public ChargeRec(AdminssionRec adminssionRec, Operater operater, int payType,Date payTime, double dctPay, double amoutPay, double actualPay) {
		this.adminssionRec = adminssionRec;
		this.operater = operater;
		this.payType = payType;
		this.payTime = payTime;
		this.dctPay = dctPay;
		this.amoutPay = amoutPay;
		this.actualPay = actualPay;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminssion_rec_id", nullable = false)
	public AdminssionRec getAdminssionRec() {
		return this.adminssionRec;
	}

	public void setAdminssionRec(AdminssionRec adminssionRec) {
		this.adminssionRec = adminssionRec;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "charge_rule_id")
	public ChargeRule getChargeRule() {
		return chargeRule;
	}

	public void setChargeRule(ChargeRule chargeRule) {
		this.chargeRule = chargeRule;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dct_group_id")
	public DctRuleGroup getDctRuleGroup() {
		return this.dctRuleGroup;
	}

	public void setDctRuleGroup(DctRuleGroup dctRuleGroup) {
		this.dctRuleGroup = dctRuleGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oper_id")
	public Operater getOperater() {
		return this.operater;
	}

	public void setOperater(Operater operater) {
		this.operater = operater;
	}

	@Column(name = "pay_type", nullable = false)
	public int getPayType() {
		return this.payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pay_time", nullable = false, length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "dct_pay", nullable = false, precision = 10)
	public double getDctPay() {
		return this.dctPay;
	}

	public void setDctPay(double dctPay) {
		this.dctPay = dctPay;
	}

	@Column(name = "amout_pay", nullable = false, precision = 10)
	public double getAmoutPay() {
		return this.amoutPay;
	}

	public void setAmoutPay(double amoutPay) {
		this.amoutPay = amoutPay;
	}

	@Column(name = "actual_pay", nullable = false, precision = 10)
	public double getActualPay() {
		return this.actualPay;
	}

	public void setActualPay(double actualPay) {
		this.actualPay = actualPay;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chargeRec")
	public List<DctRec> getDctRecs() {
		return this.dctRecs;
	}

	public void setDctRecs(List<DctRec> dctRecs) {
		this.dctRecs = dctRecs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "收费记录 [出入场记录主键=" + adminssionRec.getId() + ", 操作员名称=" + operater.getName() + ", payType=" + payType + ", 付款时间=" + payTime
				+ ", 优惠金额=" + dctPay + ", 应付金额=" + amoutPay + ", 实付金额=" + actualPay + "]";
	}

}
