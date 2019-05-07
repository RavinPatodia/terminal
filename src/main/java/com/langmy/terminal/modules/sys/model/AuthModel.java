package com.langmy.terminal.modules.sys.model;

import com.langmy.terminal.common.model.BaseModel;

/**
 * 资源model类
 * 
 * @author lxj
 *
 */
public class AuthModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5790424861090386543L;
	private String authId;//资源编号
	private Integer parentId;//父节点主键
	private String parentIds;//所有父节点
	private String name;//资源名称
	private String href;//资源链接地址
	private String target;//资源目标（暂时没用）
	private String icon;//资源图标
	private String sort;//资源排序权值
	private boolean showFlag;//是否显示
	private String permission;//权限
	
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public boolean isShowFlag() {
		return showFlag;
	}
	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}

	
	
	
}
