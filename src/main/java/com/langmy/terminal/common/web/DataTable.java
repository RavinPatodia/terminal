package com.langmy.terminal.common.web;

import java.util.List;

/**
 * 前台DataTable插件返回参数的对象
 * @author lzy
 * @param <T>
 */
public class DataTable<T>{
	
	private List<T> aaData;//数据
	private int iTotalDisplayRecords;//得到的记录数
	private int iTotalRecords;//数据库中记录数
	private int sEcho; //请求服务器端次数
	
	public int getsEcho() {
		return sEcho;
	}
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	
}
