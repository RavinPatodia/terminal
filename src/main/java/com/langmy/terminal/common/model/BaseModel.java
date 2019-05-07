package com.langmy.terminal.common.model;

import java.io.Serializable;

/**
 * 所有model的基类
 * @author lzy
 *
 */
public class BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -60817637666656299L;
	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
