package com.langmy.terminal.modules.user.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

/**
 * 黑名单Model
 * 
 * @author ZZD
 *
 */
public class BlacklistModel extends BaseModel {
	private static final long serialVersionUID = 452798638346289064L;
	private Integer userPK;// 客户id
	private String name;// 客户姓名
	private UserModel userModel;// 客户Model

	private String blacklistId;// 黑名单编号
	private String listReason;// 加入黑名单原因
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date listTime;// 列入黑名单时间
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date endTime;// 截止时间
	private Integer isEffect;// 是否有效
	private Double needFee;// 应交费用
	private String removeWay;// 解除黑名单方式

	private boolean inFlag;// 是否允许进场

	public BlacklistModel() {
		super();
	}

	public BlacklistModel(Integer userPK, String listReason, Date endTime,
			Double needFee, boolean inFlag) {
		super();
		this.userPK = userPK;
		this.listReason = listReason;
		this.endTime = endTime;
		this.needFee = needFee;
		this.inFlag = inFlag;
	}

	public Integer getUserPK() {
		return userPK;
	}

	public void setUserPK(Integer userPK) {
		this.userPK = userPK;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getBlacklistId() {
		return blacklistId;
	}

	public void setBlacklistId(String blacklistId) {
		this.blacklistId = blacklistId;
	}

	public String getListReason() {
		return listReason;
	}

	public void setListReason(String listReason) {
		this.listReason = listReason;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getListTime() {
		return listTime;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}

	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect;
	}

	public Double getNeedFee() {
		return needFee;
	}

	public void setNeedFee(Double needFee) {
		this.needFee = needFee;
	}

	public String getRemoveWay() {
		return removeWay;
	}

	public void setRemoveWay(String removeWay) {
		this.removeWay = removeWay;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInFlag() {
		return inFlag;
	}

	public void setInFlag(boolean inFlag) {
		this.inFlag = inFlag;
	}

}
