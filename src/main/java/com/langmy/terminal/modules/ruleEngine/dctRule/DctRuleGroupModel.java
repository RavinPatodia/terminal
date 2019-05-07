package com.langmy.terminal.modules.ruleEngine.dctRule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DctRuleGroupModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7360564513550930353L;
	private int id;
	private String name;//优惠规则组
	private List<DctRuleModel> dctRuleModels = new ArrayList<DctRuleModel>();//优惠规则列表
	
	public DctRuleGroupModel(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<DctRuleModel> getDctRuleModels() {
		return dctRuleModels;
	}
	public void setDctRuleModels(List<DctRuleModel> dctRuleModels) {
		this.dctRuleModels = dctRuleModels;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
