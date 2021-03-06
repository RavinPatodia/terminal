package com.langmy.terminal.common.entity;

// Generated 2015-5-12 16:46:30 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * Park generated by hbm2java
 */
@Entity
@Table(name = "park", catalog = "cloud")
public class Park extends GenIdEntityImpl  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7874305958572738038L;
	
	private ChargeRule chargeRule; //收费规则
	private String name; //停车场名称
	private Integer type; //类型 (0:区域，1：停车场)
	private Integer parentId; //父停车场/区域
	private Integer spNum; //停车场总车位数
	private Integer nowSpNum; //剩余停车位
	private Integer heightLimit; //高度限制
	private boolean isCharge; //是否收费
	private Integer floor; //楼层
	private Integer parkType; //停车场类型(0:室内，1:室外)
	private String posit; //位置
	private String remark; //备注
	private boolean delFlag; //删除标志位
	private String mapPath; //底图的路径 
	private List<UGroup> groups = Lists.newArrayList(); //客户组
	private Set<Bayonet> bayonets = new HashSet<Bayonet>(0); //卡口
	private Set<PSp> PSps = new HashSet<PSp>(0); //车位

	public Park() {
	}

	@Override
	public String toString() {
		return "Park [chargeRule=" + chargeRule.getRuleName()
				+ ", name=" + name + ", type=" + type + ", parentId="
				+ parentId + ", spNum=" + spNum + ", nowSpNum=" + nowSpNum
				+ ", heightLimit=" + heightLimit + ", isCharge=" + isCharge
				+ ", floor=" + floor + ", parkType=" + parkType + ", posit="
				+ posit + ", remark=" + remark + ", delFlag=" + delFlag
				  + "]";
	}

	public Park(String name, Integer type, boolean delFlag) {
		this.name = name;
		this.type = type;
		this.delFlag = delFlag;
	}

	public Park(ChargeRule chargeRule,String name, Integer type, Integer parentId,
			Integer spNum, Integer nowSpNum, Integer heightLimit, Boolean isCharge, Integer floor,
			Integer parkType, String posit, String remark, boolean delFlag, Set<Bayonet> bayonets,
			Set<PSp> PSps) {
		this.chargeRule = chargeRule;
		this.name = name;
		this.type = type;
		this.parentId = parentId;
		this.spNum = spNum;
		this.nowSpNum = nowSpNum;
		this.heightLimit = heightLimit;
		this.isCharge = isCharge;
		this.floor = floor;
		this.parkType = parkType;
		this.posit = posit;
		this.remark = remark;
		this.delFlag = delFlag;
		this.bayonets = bayonets;
		this.PSps = PSps;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "charge_rule_id")
	public ChargeRule getChargeRule() {
		return this.chargeRule;
	}

	public void setChargeRule(ChargeRule chargeRule) {
		this.chargeRule = chargeRule;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "sp_num")
	public Integer getSpNum() {
		return this.spNum;
	}

	public void setSpNum(Integer spNum) {
		this.spNum = spNum;
	}

	@Column(name = "now_sp_num")
	public Integer getNowSpNum() {
		return this.nowSpNum;
	}

	public void setNowSpNum(Integer nowSpNum) {
		this.nowSpNum = nowSpNum;
	}

	@Column(name = "height_limit")
	public Integer getHeightLimit() {
		return this.heightLimit;
	}

	public void setHeightLimit(Integer heightLimit) {
		this.heightLimit = heightLimit;
	}

	@Column(name = "is_charge")
	public Boolean getIsCharge() {
		return this.isCharge;
	}

	public void setIsCharge(Boolean isCharge) {
		this.isCharge = isCharge;
	}

	@Column(name = "floor")
	public Integer getFloor() {
		return this.floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	@Column(name = "park_type")
	public Integer getParkType() {
		return this.parkType;
	}

	public void setParkType(Integer parkType) {
		this.parkType = parkType;
	}

	@Column(name = "posit", length = 50)
	public String getPosit() {
		return this.posit;
	}

	public void setPosit(String posit) {
		this.posit = posit;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "del_flag", nullable = false)
	public boolean isDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "park")
	public Set<Bayonet> getBayonets() {
		return this.bayonets;
	}

	public void setBayonets(Set<Bayonet> bayonets) {
		this.bayonets = bayonets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "park")
	public Set<PSp> getPSps() {
		return this.PSps;
	}

	public void setPSps(Set<PSp> PSps) {
		this.PSps = PSps;
	}

	@Column(name = "map_path", length=500)
	public String getMapPath() {
		return mapPath;
	}

	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "u_g_park", catalog = "cloud", joinColumns = { @JoinColumn(name = "park_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "u_group_id", nullable = false, updatable = false) })
	public List<UGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<UGroup> groups) {
		this.groups = groups;
	}

}
