package com.langmy.terminal.modules.monitor.model;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 452798638346289064L;
	/**
	 * 黑名单编号
	 */
	private String blacklistId;
	/**
	 * 加入黑名单原因
	 */
	private String listReason;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date listTime;// 介入黑名单时间
	/**
	 * 截止时间
	 */
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date endTime;
	/**
	 * 是否有效
	 */
	private int isEffect;
	/**
	 * 应交费用
	 */
	private Double needFee;
	/**
	 * 解除黑名单方式
	 */
	private String removeWay;
	/**
	 * 是否允许进场
	 */
	private boolean inFlag;

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

	public int getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(int isEffect) {
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

	public boolean isInFlag() {
		return inFlag;
	}

	public void setInFlag(boolean inFlag) {
		this.inFlag = inFlag;
	}

}
