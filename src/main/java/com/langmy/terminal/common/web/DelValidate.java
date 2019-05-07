package com.langmy.terminal.common.web;

import java.util.List;

/**
 * @author zz
 * 2015年6月9日
 */
public class DelValidate {
	
	List<Integer> idPermission;//允许删除的对象的id列表
	List<String> nameNotPermission;//不允许删除的对象的名称列表
	List<String>  associateObjs;//不允许删除的对象依赖的对象的名称
	
	/**
	 * @param idPermission
	 * @param nameNotPermission
	 * @param associateObjs
	 */
	public DelValidate(List<Integer> idPermission, List<String> nameNotPermission, List<String> associateObjs) {
		super();
		this.idPermission = idPermission;
		this.nameNotPermission = nameNotPermission;
		this.associateObjs = associateObjs;
	}
	public List<Integer> getIdPermission() {
		return idPermission;
	}
	public void setIdPermission(List<Integer> idPermission) {
		this.idPermission = idPermission;
	}
	public List<String> getNameNotPermission() {
		return nameNotPermission;
	}
	public void setNameNotPermission(List<String> nameNotPermission) {
		this.nameNotPermission = nameNotPermission;
	}
	public List<String> getAssociateObjs() {
		return associateObjs;
	}
	public void setAssociateObjs(List<String> associateObjs) {
		this.associateObjs = associateObjs;
	}
	
}
