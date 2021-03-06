package com.langmy.terminal.common.entity;

// Generated 2015-2-3 18:18:41 by Hibernate Tools 4.3.1

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import com.langmy.terminal.common.persistence.DetailRule;
import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * AnchorGroup generated by hbm2java
 */
@Entity
@Table(name = "anchor_group", catalog = "cloud")
public class AnchorGroup extends GenIdEntityImpl implements DetailRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4142810659578186897L;
	private String anchorGroupId; //收费组编号
	private String name; //收费组名称
	private String describion; //收费组描述
	private boolean delFlag; //删除标志
	private List<AnchorRent> GAnchorRents = Lists.newArrayList(); 

	public AnchorGroup() {
	}

	public AnchorGroup(String anchorGroupId, String name, boolean isEnable) {
		this.anchorGroupId = anchorGroupId;
		this.name = name;
	}

	public AnchorGroup(String anchorGroupId, String name) {
		this.anchorGroupId = anchorGroupId;
		this.name = name;
	}

	public AnchorGroup(String anchorGroupId, String name, String describion, List<AnchorRent> GAnchorRents) {
		this.anchorGroupId = anchorGroupId;
		this.name = name;
		this.describion = describion;
		this.GAnchorRents = GAnchorRents;
	}

	public AnchorGroup(AnchorGroup anchorGroup) {
		this.anchorGroupId = anchorGroup.getAnchorGroupId();
		this.name = anchorGroup.getName();
		this.describion = anchorGroup.getDescribion();
	}

	@Column(name = "anchor_group_id", nullable = false, length = 30)
	public String getAnchorGroupId() {
		return this.anchorGroupId;
	}

	public void setAnchorGroupId(String anchorGroupId) {
		this.anchorGroupId = anchorGroupId;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name = "describion")
	public String getDescribion() {
		return this.describion;
	}

	public void setDescribion(String describion) {
		this.describion = describion;
	}

	@JSONField(serialize = false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "g_anchor_rent", catalog = "cloud", joinColumns = { @JoinColumn(name = "group_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "anchor_rent_id", nullable = false, updatable = false) })
	public List<AnchorRent> getAnchorRents() {
		return GAnchorRents;
	}

	public void setAnchorRents(List<AnchorRent> GAnchorRents) {
		this.GAnchorRents = GAnchorRents;
	}

	@Override
	@Transient
	public String getRuleId() {
		return anchorGroupId;
	}

	@Column(name = "del_flag", nullable = false)
	public boolean isDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "长期收费组 [收费组Id=" + anchorGroupId + ", 名称=" + name + ", 描述=" + describion
				+ ", 删除标志位=" + delFlag + "]";
	}

}
