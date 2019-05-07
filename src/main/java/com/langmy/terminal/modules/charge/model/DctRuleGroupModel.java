package com.langmy.terminal.modules.charge.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;

public class DctRuleGroupModel extends BaseRuleModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 881443543854163324L;
	private String groupId;
	private String name;
	private String describion;
	private List<Integer> dctRules;
	
	private List<DctRuleModel> dctRuleModelsInGroup;
	private List<DctRuleModel> dctRuleModelsNotInGroup; 
	private List<Select> selects = Lists.newArrayList();
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribion() {
		return describion;
	}
	public void setDescribion(String describion) {
		this.describion = describion;
	}

	public void setDctRuleModelsInGroup(List<DctRuleModel> dctRuleModelsInGroup) {
		this.dctRuleModelsInGroup = dctRuleModelsInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(dctRuleModelsInGroup, "id", "dctName", true));
	}

	public void setDctRuleModelsNotInGroup(List<DctRuleModel> dctRuleModelsNotInGroup) {
		this.dctRuleModelsNotInGroup = dctRuleModelsNotInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(dctRuleModelsNotInGroup, "id", "dctName", false));
	}

	public List<Select> getSelects() {
		return selects;
	}

	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}

	public List<DctRuleModel> getDctRuleModelsInGroup() {
		return dctRuleModelsInGroup;
	}

	public List<DctRuleModel> getDctRuleModelsNotInGroup() {
		return dctRuleModelsNotInGroup;
	}
	public List<Integer> getDctRules() {
		return dctRules;
	}
	public void setDctRules(List<Integer> dctRules) {
		this.dctRules = dctRules;
	}

	
}
