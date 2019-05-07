package com.langmy.terminal.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

@Entity
@Table(name = "oper_log", catalog = "cloud_log")
public class Log extends GenIdEntityImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6412665417395733630L;
	/**
	 * 操作员
	 */
	private String operMan;
	/**
	 * 操作时间 YYYY-MM-DD HH:MM:SS
	 */
	private Date operTime;
	/**
	 * 操作模块
	 */
	private String operModule;
	/**
	 * 操作名称
	 */
	private String operOption;
	/**
	 * 操作内容
	 */
	private String operCon;

	@Override
	public String toString() {
		return "Log [操作员=" + operMan + ", 操作时间=" + operTime + ", 操作模块="
				+ operModule + ", 操作名称=" + operOption + ", 操作内容=" + operCon
				+ "]";
	}

	public Log() {
	}

	public Log(String operMan, Date operTime, String operModule,
			String operOption, String operCon) {
		this.operMan = operMan;
		this.operTime = operTime;
		this.operModule = operModule;
		this.operOption = operOption;
		this.operCon = operCon;
	}

	@Column(name = "oper_man", nullable = false)
	public String getOperMan() {
		return operMan;
	}

	public void setOperMan(String operMan) {
		this.operMan = operMan;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "oper_time", nullable = false, length = 19)
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "oper_module", nullable = false)
	public String getOperModule() {
		return operModule;
	}

	public void setOperModule(String operModule) {
		this.operModule = operModule;
	}

	@Column(name = "oper_con", nullable = false)
	public String getOperCon() {
		return operCon;
	}

	public void setOperCon(String operCon) {
		this.operCon = operCon;
	}

	@Column(name = "oper_option", nullable = false)
	public String getOperOption() {
		return operOption;
	}

	public void setOperOption(String operOption) {
		this.operOption = operOption;
	}

}
