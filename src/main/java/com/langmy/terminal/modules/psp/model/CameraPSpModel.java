package com.langmy.terminal.modules.psp.model;

// Generated 2015-2-3 18:24:28 by Hibernate Tools 4.3.1

import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.modules.device.model.CameraModel;

/**
 * 车位与识别设别的model
 * 
 * @author MC
 *
 */
public class CameraPSpModel extends BaseModel {
	private static final long serialVersionUID = 1L;

	/**
	 * 识别设备model
	 */
	private CameraModel cameraModel;
	/**
	 * 车位model
	 */
	private PSpModel pSpModel;

	/**
	 * 识别设备编号
	 */
	private String cameraId;
	/**
	 * 车位编号
	 */
	private String pspId;

	public PSpModel getpSpModel() {
		return pSpModel;
	}

	public void setpSpModel(PSpModel pSpModel) {
		this.pSpModel = pSpModel;
	}

	public CameraModel getCameraModel() {
		return cameraModel;
	}

	public void setCameraModel(CameraModel cameraModel) {
		this.cameraModel = cameraModel;
	}

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}
}
