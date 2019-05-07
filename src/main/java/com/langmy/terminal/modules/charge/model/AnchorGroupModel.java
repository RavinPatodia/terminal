package com.langmy.terminal.modules.charge.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;

/**
 * 长期收费规则组Model
 * 
 * @author lzy
 *
 */
public class AnchorGroupModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8171206252910819977L;
	private String anchorGroupId;//长期规则组编号
	private String name;//长期规则组名称
	private String describion;//描述
	// private String anchorRentPKs;
	private List<Integer> anchorRents; //长期收费id

	private List<AnchorRentModel> anchorRentModelsInGroup;
	private List<AnchorRentModel> anchorRentModelsNotInGroup;
	private List<Select> selects = Lists.newArrayList();

	public String getAnchorGroupId() {
		return anchorGroupId;
	}

	public void setAnchorGroupId(String anchorGroupId) {
		this.anchorGroupId = anchorGroupId;
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

	public void setAnchorRentModelsInGroup(List<AnchorRentModel> anchorRentModelsInGroup) {
		this.anchorRentModelsInGroup = anchorRentModelsInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(anchorRentModelsInGroup, "id", "name", true));
	}

	public void setAnchorRentModelsNotInGroup(List<AnchorRentModel> anchorRentModelsNotInGroup) {
		this.anchorRentModelsNotInGroup = anchorRentModelsNotInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(anchorRentModelsNotInGroup, "id", "name", false));
	}

	public List<Select> getSelects() {
		return selects;
	}

	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}

	public List<AnchorRentModel> getAnchorRentModelsInGroup() {
		return anchorRentModelsInGroup;
	}

	public List<AnchorRentModel> getAnchorRentModelsNotInGroup() {
		return anchorRentModelsNotInGroup;
	}

	public List<Integer> getAnchorRents() {
		return anchorRents;
	}

	public void setAnchorRents(List<Integer> anchorRents) {
		this.anchorRents = anchorRents;
	}
}
