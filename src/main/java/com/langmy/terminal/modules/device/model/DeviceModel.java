package com.langmy.terminal.modules.device.model;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;

public class DeviceModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7952798323462968079L;
	private DrivewayModel drivewayModel;
	private String deviceId;
	private Integer type;
	private Integer open;
	private Integer close;
	private Integer port;
	private String name;
	private String posit;
	private String ip;
	private boolean lineState;
	private boolean delFlag;
	private boolean openFlag;	//闸机开启关闭状态
	
	private String drivewayNames; //
	private List<String> drivewaySelect; //
	private List<DrivewayModel> drivewayModelsInGroup;
	private List<DrivewayModel> drivewayModelsNotInGroup;
	private List<Select> selects = Lists.newArrayList();

	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getOpen() {
		return open;
	}
	public void setOpen(Integer open) {
		this.open = open;
	}
	public Integer getClose() {
		return close;
	}
	public void setClose(Integer close) {
		this.close = close;
	}

	public boolean isLineState() {
		return lineState;
	}
	public void setLineState(boolean lineState) {
		this.lineState = lineState;
	}
	public boolean isDelFlag() {
		return delFlag;
	}
	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	public DrivewayModel getDrivewayModel() {
		return drivewayModel;
	}
	public void setDrivewayModel(DrivewayModel drivewayModel) {
		this.drivewayModel = drivewayModel;
	}
	public boolean isOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(boolean openFlag) {
		this.openFlag = openFlag;
	}
	
	
	public List<String> getDrivewaySelect() {
		return drivewaySelect;
	}
	public void setDrivewaySelect(List<String> drivewaySelect) {
		this.drivewaySelect = drivewaySelect;
	}
	public List<DrivewayModel> getDrivewayModelsInGroup() {
		return drivewayModelsInGroup;
	}
	public void setDrivewayModelsInGroup(List<DrivewayModel> drivewayModelsInGroup) {
		this.drivewayModelsInGroup = drivewayModelsInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(drivewayModelsInGroup,
				"id", "name", true));
	}
	public List<DrivewayModel> getDrivewayModelsNotInGroup() {
		return drivewayModelsNotInGroup;
	}
	public void setDrivewayModelsNotInGroup(
			List<DrivewayModel> drivewayModelsNotInGroup) {
		this.drivewayModelsNotInGroup = drivewayModelsNotInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(drivewayModelsNotInGroup,
				"id", "name", false));
	}
	public List<Select> getSelects() {
		return selects;
	}
	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}
	public String getDrivewayNames() {
		return drivewayNames;
	}
	public void setDrivewayNames(String drivewayNames) {
		this.drivewayNames = drivewayNames;
	}
}
