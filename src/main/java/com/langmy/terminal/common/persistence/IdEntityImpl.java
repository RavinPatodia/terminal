package com.langmy.terminal.common.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 实体类中Id不自增的对象的父类
 * @author lzy
 */
@MappedSuperclass
public class IdEntityImpl implements Serializable,IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4393366175608412895L;
	protected Integer id;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
