package com.langmy.terminal.common.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Id自增长的实体的父类
 * @author lzy
 */
@MappedSuperclass
public class GenIdEntityImpl implements Serializable,IdEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4528656494435322119L;
	protected Integer id;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	
}
