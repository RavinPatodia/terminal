package com.langmy.terminal.modules.psp.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;

/**
 * 区域的model
 * 
 * @author MC
 *
 */
public class AreaModel extends BaseModel {

	private static final long serialVersionUID = 8159580488547398477L;
	/**
	 * 区域编号
	 */
	private String areaId;
	/**
	 * 区域名称
	 */
	private String name;
	/**
	 * 区域位置
	 */
	private String posit;
	/**
	 * 车位编号
	 */
	private String pspId;
	
	private List<String> pspIds;
	
	
	/**
	 * 是否为删除区域
	 */
	private Boolean delFlag;

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	private List<PSpModel> pspModelsInGroup;
	private List<PSpModel> pspModelsNotInGroup;
	private List<Select> selects = Lists.newArrayList();

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosit() {
		return posit;
	}

	public void setPosit(String posit) {
		this.posit = posit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	public List<PSpModel> getPspModelsInGroup() {
		return pspModelsInGroup;
	}

	public void setPspModelsInGroup(List<PSpModel> pspModelsInGroup) {
		this.pspModelsInGroup = pspModelsInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(pspModelsInGroup,
				"id", "pspId", true));
	}

	public List<PSpModel> getPspModelsNotInGroup() {
		return pspModelsNotInGroup;
	}

	public void setPspModelsNotInGroup(List<PSpModel> pspModelsNotInGroup) {
		this.pspModelsNotInGroup = pspModelsNotInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(
				pspModelsNotInGroup, "id", "pspId", false));
	}

	public List<Select> getSelects() {
		return selects;
	}

	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}

	public List<String> getPspIds() {
		return pspIds;
	}

	public void setPspIds(List<String> pspIds) {
		this.pspIds = pspIds;
	}

}
