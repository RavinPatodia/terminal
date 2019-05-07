package com.langmy.terminal.common.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 规则实体类的父类
 * @author lzy
 */
@MappedSuperclass
public class RuleEntity extends GenIdEntityImpl {

	private static final long serialVersionUID = 1L;

	protected boolean enableFlag;
	protected Date lastDisable;
	protected Date lastEnable;
	
	@PrePersist
	public void prePersist(){
		if(enableFlag){
			this.lastEnable = new Date();
		}
		else{
			this.lastDisable=new Date();
		}
	}
	
	/*@PreUpdate
	public void preUpdate(){
		this.createTime = new Date();
	}*/
	
	@Column(name = "is_enable", nullable = false)
	public boolean isEnableFlag() {
		return this.enableFlag;
	}

	public void setEnableFlag(boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_disable", length = 19)
	public Date getLastDisable() {
		return this.lastDisable;
	}

	public void setLastDisable(Date lastDisable) {
		this.lastDisable = lastDisable;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_enable", length = 19)
	public Date getLastEnable() {
		return this.lastEnable;
	}

	public void setLastEnable(Date lastEnable) {
		this.lastEnable = lastEnable;
	}
}
