package com.langmy.terminal.common.entity;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * BrandModel generated by hbm2java
 */
@Entity
@Table(name = "brand_model", catalog = "cloud")
public class BrandModel extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4939733804184386712L;
	private BrandModel brandModel; 
	private String brandModelId; //品牌、型号编号
	private int type; //类型
	private String name; //名称
	private Set<BrandModel> brandModels = new HashSet<BrandModel>(0); //摄像机品牌型号

	public BrandModel() {
	}

	public BrandModel(String brandModelId, int type) {
		this.brandModelId = brandModelId;
		this.type = type;
	}

	public BrandModel(BrandModel brandModel, String brandModelId, int type,
			String name, Set<BrandModel> brandModels) {
		this.brandModel = brandModel;
		this.brandModelId = brandModelId;
		this.type = type;
		this.name = name;
		this.brandModels = brandModels;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	public BrandModel getBrandModel() {
		return this.brandModel;
	}

	public void setBrandModel(BrandModel brandModel) {
		this.brandModel = brandModel;
	}

	@Column(name = "brand_model_id", nullable = false, length = 20)
	public String getBrandModelId() {
		return this.brandModelId;
	}

	public void setBrandModelId(String brandModelId) {
		this.brandModelId = brandModelId;
	}

	@Column(name = "type", nullable = false)
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brandModel")
	public Set<BrandModel> getBrandModels() {
		return this.brandModels;
	}

	public void setBrandModels(Set<BrandModel> brandModels) {
		this.brandModels = brandModels;
	}

}
