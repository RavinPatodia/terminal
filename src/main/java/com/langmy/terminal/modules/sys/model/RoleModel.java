package com.langmy.terminal.modules.sys.model;

/**
 * 角色Model
 * 
 * @author ZZD
 *
 */
public class RoleModel extends SysBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7833245943163902607L;
	/**
	 * 角色编号
	 */
	private String roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色描述
	 */
	private String roleDesc;
	/**
	 * 权限id
	 */
	private String authPKs;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getAuthPKs() {
		return authPKs;
	}

	public void setAuthPKs(String authPKs) {
		this.authPKs = authPKs;
	}

}
