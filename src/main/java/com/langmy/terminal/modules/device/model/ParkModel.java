package com.langmy.terminal.modules.device.model;

// Generated 2015-2-3 18:24:28 by Hibernate Tools 4.3.1

import com.langmy.terminal.common.model.BaseModel;

/**
 * 车位的model
 * 
 * @author MC
 * 
 */
public class ParkModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 停车场编号
	 */
	private String parkId;
	/**
	 * 停车场名称
	 */
	private String name;
	/**
	 * 类型 (0:区域，1：停车场)
	 */
	private int type;
	/**
	 * 父节点
	 */
	private Integer parentId;
	/**
	 * 停车位
	 */
	private Integer spNum;
	/**
	 * 剩余车位
	 */
	private Integer nowSpNum;
	/**
	 * 高度限制
	 */
	private Integer heightLimit;
	/**
	 * 是否收费
	 */
	private Boolean isCharge;
	/**
	 * 楼层
	 */
	private Integer floor;
	/**
	 * 停车场类型(0:室内，1:室外)
	 */
	private int parkType;
	/**
	 * 位置
	 */
	private String posit;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 删除标志位
	 */
	private boolean delFlag;

	private Integer rulePK;
	private String ruleName;
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getRulePK() {
		return rulePK;
	}

	public void setRulePK(Integer rulePK) {
		this.rulePK = rulePK;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSpNum() {
		return spNum;
	}

	public void setSpNum(Integer spNum) {
		this.spNum = spNum;
	}

	public Integer getNowSpNum() {
		return nowSpNum;
	}

	public void setNowSpNum(Integer nowSpNum) {
		this.nowSpNum = nowSpNum;
	}

	public Integer getHeightLimit() {
		return heightLimit;
	}

	public void setHeightLimit(Integer heightLimit) {
		this.heightLimit = heightLimit;
	}

	public Boolean getIsCharge() {
		return this.isCharge;
	}

	public void setIsCharge(Boolean isCharge) {
		this.isCharge = isCharge;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public int getParkType() {
		return parkType;
	}

	public void setParkType(int parkType) {
		this.parkType = parkType;
	}

	public String getPosit() {
		return posit;
	}

	public void setPosit(String posit) {
		this.posit = posit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

}
