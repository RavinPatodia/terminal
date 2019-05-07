package com.langmy.terminal.common.entity;

// default package
// Generated 2015-1-29 19:22:28 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.langmy.terminal.common.persistence.GenIdEntityImpl;

/**
 * ContactAddr generated by hbm2java
 */
@Entity
@Table(name = "contact_addr", catalog = "cloud")
public class ContactAddr extends GenIdEntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3204535716547231736L;
	private int type; //类型
	private int contactId; //外键
	private String province; //省
	private String city; //市
	private String dist; //区
	private String addr; //详细地址

	@Override
	public String toString() {
		return "ContactAddr [类型=" + type + ", 外键=" + contactId + ", 省="
				+ province + ", 市=" + city + ", 区=" + dist + ", 详细地址=" + addr
				+ "]";
	}

	public ContactAddr() {
	}

	public ContactAddr(int type, int contactId) {
		this.type = type;
		this.contactId = contactId;
	}

	public ContactAddr(int type, int contactId, String province, String city,
			String dist, String addr) {
		this.type = type;
		this.contactId = contactId;
		this.province = province;
		this.city = city;
		this.dist = dist;
		this.addr = addr;
	}

	@Column(name = "type", nullable = false)
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "contact_id", nullable = false)
	public int getContactId() {
		return this.contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	@Column(name = "province", length = 20)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 20)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "dist", length = 20)
	public String getDist() {
		return this.dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	@Column(name = "addr", length = 50)
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
