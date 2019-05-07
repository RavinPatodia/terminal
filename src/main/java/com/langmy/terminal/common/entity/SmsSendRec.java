package com.langmy.terminal.common.entity;

// Generated 2015-7-6 16:29:01 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * 短信发送记录
 */
@Entity
@Table(name = "sms_send_rec", catalog = "cloud")
public class SmsSendRec extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2630626288182995879L;
	private int userId;// 客户主键
	private int smsRecId;// 短息猫主键
	private int type;// 类型
	private String platePsp;// 车牌号或者车位编号(车位编号或者车位主键)
	private Date sendTime;// 发送时间
	private String content;// 实际发送内容
	private boolean enableFlag; // 是否旧记录标志位

	public SmsSendRec() {
	}

	public SmsSendRec(int userId, int smsRecId, int type, Date sendTime,
			String content, boolean enableFlag) {
		super();
		this.userId = userId;
		this.smsRecId = smsRecId;
		this.type = type;
		this.sendTime = sendTime;
		this.content = content;
		this.enableFlag = enableFlag;
	}

	public SmsSendRec(int userId, int smsRecId, int type, String platePsp,
			Date sendTime, String content, boolean enableFlag) {
		super();
		this.userId = userId;
		this.smsRecId = smsRecId;
		this.type = type;
		this.platePsp = platePsp;
		this.sendTime = sendTime;
		this.content = content;
		this.enableFlag = enableFlag;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "sms_rec_id", nullable = false)
	public int getSmsRecId() {
		return this.smsRecId;
	}

	public void setSmsRecId(int smsRecId) {
		this.smsRecId = smsRecId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "send_time", nullable = false, length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "type", nullable = false)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "plate_psp", length = 20)
	public String getPlatePsp() {
		return platePsp;
	}

	public void setPlatePsp(String platePsp) {
		this.platePsp = platePsp;
	}

	@Column(name = "content", nullable = false, length = 50)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "enable_flag", nullable = true)
	public boolean isEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

}
