package com.langmy.terminal.common.entity;

// Generated 2015-5-12 17:17:42 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * SmsRec generated by hbm2java
 */
@Entity
@Table(name = "sms_rec", catalog = "cloud")
public class SmsRec extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8076646086245006099L;
	private int address; //发送信息地址
	private int type; //发送条件 (0:余额不足;1:停车时长超过阀值;2:长期车位即将到期;3:长期会员即将到期)
	private double condition; //条件(余额，停车时长（分钟）,长期车位/长期会员（天）)
	private String content; //发送内容
	private boolean enableFlag; //是否启用

	public SmsRec() {
	}

	public SmsRec(int address, int type, double condition, String content,
			boolean enableFlag) {
		this.address = address;
		this.type = type;
		this.condition = condition;
		this.content = content;
		this.enableFlag = enableFlag;
	}

	@Column(name = "address", nullable = false)
	public int getAddress() {
		return this.address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	@Column(name = "type", nullable = false)
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "msg_condition", nullable = false, precision = 5)
	public double getCondition() {
		return this.condition;
	}

	public void setCondition(double condition) {
		this.condition = condition;
	}

	@Column(name = "content", nullable = false, length = 50)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "enable_flag", nullable = false)
	public boolean isEnableFlag() {
		return this.enableFlag;
	}

	public void setEnableFlag(boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

}
