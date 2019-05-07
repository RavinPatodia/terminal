package com.langmy.terminal.modules.device.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;

public class LedScreenModel extends BaseModel{
	private static final long serialVersionUID = 2091074361537855107L;
	private String ledId;
	private String name;
	private int type;
	private int color;
	private int direction;
	private Integer configureId;
	private boolean lineState;
	private String message;
	private int cardNum;
	private int width;
	private int height;
	private boolean delFlag;
	private String dataMasterName;
	private Integer dataMasterId;
	public Integer getDataMasterId() {
		return dataMasterId;
	}
	public void setDataMasterId(Integer dataMasterId) {
		this.dataMasterId = dataMasterId;
	}
	/**
	 * 当前状态（拖拽的时候用到）
	 */
	private Integer configureFlag;
	
	private List<String> areaSelect; 
	private List<AreaModel> areaModelsInGroup;
	private List<AreaModel> areaModelsNotInGroup; 
	private List<Select> selects = Lists.newArrayList();
	private String areasIds;
	private String areaNames; 
	
	public String getLedId() {
		return ledId;
	}
	public void setLedId(String ledId) {
		this.ledId = ledId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean isLineState() {
		return lineState;
	}
	public void setLineState(boolean lineState) {
		this.lineState = lineState;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCardNum() {
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isDelFlag() {
		return delFlag;
	}
	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	public List<AreaModel> getAreaModelsInGroup() {
		return areaModelsInGroup;
	}
	public void setAreaModelsInGroup(List<AreaModel> areaModelsInGroup) {
		this.areaModelsInGroup = areaModelsInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(areaModelsInGroup,
				"id", "name", true));
	}
	public List<AreaModel> getAreaModelsNotInGroup() {
		return areaModelsNotInGroup;
	}
	public void setAreaModelsNotInGroup(List<AreaModel> areaModelsNotInGroup) {
		this.areaModelsNotInGroup = areaModelsNotInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(areaModelsNotInGroup,
				"id", "name", false));
	}
	public List<Select> getSelects() {
		return selects;
	}
	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}
	public String getAreasIds() {
		return areasIds;
	}
	public void setAreasIds(String areasIds) {
		this.areasIds = areasIds;
	}
	public Integer getConfigureId() {
		return configureId;
	}
	public void setConfigureId(Integer configureId) {
		this.configureId = configureId;
	}
	public String getDataMasterName() {
		return dataMasterName;
	}
	public void setDataMasterName(String dataMasterName) {
		this.dataMasterName = dataMasterName;
	}
	public List<String> getAreaSelect() {
		return areaSelect;
	}
	public void setAreaSelect(List<String> areaSelect) {
		this.areaSelect = areaSelect;
	}
	public String getAreaNames() {
		return areaNames;
	}
	public void setAreaNames(String areaNames) {
		this.areaNames = areaNames;
	}
	public Integer getConfigureFlag() {
		return configureFlag;
	}
	public void setConfigureFlag(Integer configureFlag) {
		this.configureFlag = configureFlag;
	}
	
}
