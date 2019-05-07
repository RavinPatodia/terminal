package com.langmy.terminal.modules.device.model;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Camera;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;

public class DrivewayModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7952798323462968079L;

	private String name;
	private String releaseRule;
	private int direction;
	private String qdirection;
	private String terminalName;
	private String bayonetName;
	private boolean delFlag;
	private Integer screenInPK;
	private Integer screenOutPK;
	private Integer brakePK;
	private Integer bayonetPK;
	private Integer terminalPK;
	
	public Integer getTerminalPK() {
		return terminalPK;
	}

	public void setTerminalPK(Integer terminalPK) {
		this.terminalPK = terminalPK;
	}

	public Integer getBrakePK() {
		return brakePK;
	}

	public void setBrakePK(Integer brakePK) {
		this.brakePK = brakePK;
	}

	public Integer getBayonetPK() {
		return bayonetPK;
	}

	public void setBayonetPK(Integer bayonetPK) {
		this.bayonetPK = bayonetPK;
	}

	private List<LedScreenModel> ledScreenModels = Lists.newArrayList();

	private String brakeMachine;
	private String ledScreenIn;
	private String ledScreenOut;
	private String ledScreen;
	
	private List<String> cameraSelect; 
	private List<Camera> cameras = Lists.newArrayList();
	private List<CameraModel> cameraModelsInGroup;
	private List<CameraModel> cameraModelsNotInGroup; 
	private List<Select> selects = Lists.newArrayList();
	private String camerasIds;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReleaseRule() {
		return releaseRule;
	}

	public void setReleaseRule(String releaseRule) {
		this.releaseRule = releaseRule;
	}

	public String getQdirection() {
		return qdirection;
	}

	public void setQdirection(String qdirection) {
		this.qdirection = qdirection;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getBayonetName() {
		return bayonetName;
	}

	public void setBayonetName(String bayonetName) {
		this.bayonetName = bayonetName;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public List<LedScreenModel> getLedScreenModels() {
		return ledScreenModels;
	}

	public void setLedScreenModels(List<LedScreenModel> ledScreenModels) {
		this.ledScreenModels = ledScreenModels;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	
	public List<Camera> getCameras() {
		return cameras;
	}
	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}
	public List<CameraModel> getCameraModelsInGroup() {
		return cameraModelsInGroup;
	}
	public void setCameraModelsInGroup(List<CameraModel> cameraModelsInGroup) {
		this.cameraModelsInGroup = cameraModelsInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(cameraModelsInGroup, "id", "cameraId", true));
	}
	public List<CameraModel> getCameraModelsNotInGroup() {
		return cameraModelsNotInGroup;
	}
	public void setCameraModelsNotInGroup(List<CameraModel> cameraModelsNotInGroup) {
		this.cameraModelsNotInGroup = cameraModelsNotInGroup;
		selects.addAll(MutiSelectExtend.convertToSelect(cameraModelsNotInGroup, "id", "cameraId", false));
		
	}
	public List<Select> getSelects() {
		return selects;
	}
	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}
	public String getCamerasIds() {
		return camerasIds;
	}
	public void setCamerasIds(String camerasIds) {
		this.camerasIds = camerasIds;
	}

	public List<String> getCameraSelect() {
		return cameraSelect;
	}

	public void setCameraSelect(List<String> cameraSelect) {
		this.cameraSelect = cameraSelect;
	}

	public String getBrakeMachine() {
		return brakeMachine;
	}

	public void setBrakeMachine(String brakeMachine) {
		this.brakeMachine = brakeMachine;
	}

	public String getLedScreenIn() {
		return ledScreenIn;
	}

	public void setLedScreenIn(String ledScreenIn) {
		this.ledScreenIn = ledScreenIn;
		this.ledScreen = ledScreenIn;
	}

	public String getLedScreenOut() {
		return ledScreenOut;
	}

	public void setLedScreenOut(String ledScreenOut) {
		this.ledScreenOut = ledScreenOut;
		this.ledScreen = ledScreenOut;
	}

	public String getLedScreen() {
		return ledScreen;
	}

	public void setLedScreen(String ledScreen) {
		this.ledScreen = ledScreen;
	}

	public Integer getScreenInPK() {
		return screenInPK;
	}

	public void setScreenInPK(Integer screenInPK) {
		this.screenInPK = screenInPK;
	}

	public Integer getScreenOutPK() {
		return screenOutPK;
	}

	public void setScreenOutPK(Integer screenOutPK) {
		this.screenOutPK = screenOutPK;
	}
}
