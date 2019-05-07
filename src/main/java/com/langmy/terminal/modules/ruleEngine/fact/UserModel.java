package com.langmy.terminal.modules.ruleEngine.fact;

import java.util.Date;

public class UserModel {
	
	private Date birthDay; //生日
	private int userGroupId; //用户组编号

	public UserModel(){}
	
	public UserModel(Date birthDay, int userGroupId) {
		super();
		this.birthDay = birthDay;
		this.userGroupId = userGroupId;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public int getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("规则引擎用户Model [生日=").append(birthDay)
				.append(", 用户组编号=").append(userGroupId).append("]");
		return builder.toString();
	}
	
}
