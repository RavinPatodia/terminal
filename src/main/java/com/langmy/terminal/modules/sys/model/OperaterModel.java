package com.langmy.terminal.modules.sys.model;

import java.util.List;

import com.langmy.terminal.common.utils.ListUtils;

/**
 * 操作员Model
 * 
 * @author ZZD
 *
 */
public class OperaterModel extends SysBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8979743589017374517L;
	/**
	 * 操作员账户名
	 */
	private String operName;
	/**
	 * 操作员姓名
	 */
	private String name;
	/**
	 * 身份证号码
	 */
	private String idCard;
	/**
	 * 性别 false-女 true-男
	 */
	private Boolean gender;
	private int genderInt;
	/**
	 * 工作
	 */
	private String job;
	/**
	 * 头像图片url
	 */
	private String picUrl;
	
	private String ftpPicUrl;
	/**
	 * 删除标志
	 */
	private boolean delFlag;

	private BaseSearchModel bsm;
	/**
	 * 角色id
	 */
	private Integer rolePK;
	/**
	 * 角色Model
	 */
	private List<RoleModel> roleModels;
	/**
	 * 角色名
	 */
	private String roleName;

	private String oldPwd;// 旧密码
	private String newPwd;// 新密码

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public List<RoleModel> getRoleModels() {
		return roleModels;
	}

	public void setRoleModels(List<RoleModel> roleModels) {
		this.roleModels = roleModels;
	}

	public Integer getRolePK() {
		return rolePK;
	}

	public void setRolePK(Integer rolePK) {
		this.rolePK = rolePK;
	}

	// 获取第一个role名字
	public String getRoleName() {
		if (ListUtils.isNullOrEmpty(roleModels))
			return roleName;
		return roleModels.get(0).getRoleName();
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public BaseSearchModel getBsm() {
		return bsm;
	}

	public void setBsm(BaseSearchModel bsm) {
		this.bsm = bsm;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public int getGenderInt() {
		return genderInt;
	}

	public void setGenderInt(int genderInt) {
		this.genderInt = genderInt;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getFtpPicUrl() {
		return ftpPicUrl;
	}

	public void setFtpPicUrl(String ftpPicUrl) {
		this.ftpPicUrl = ftpPicUrl;
	}
	

}
