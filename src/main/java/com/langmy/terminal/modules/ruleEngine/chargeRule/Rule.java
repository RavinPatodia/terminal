package com.langmy.terminal.modules.ruleEngine.chargeRule;

import java.io.Serializable;



public abstract class Rule implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -985941662358798874L;
	protected int freeTime; //免费时间
	 protected int id;
	 protected String name;//收费规则名称
	 
	public int getFreeTime() {
		return freeTime;
	}
	public void setFreeTime(int freeTime) {
		this.freeTime = freeTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
