package com.langmy.terminal.common.web;

import java.util.List;


/**
 * 接收前台参数的对象
 * @author lzy
 */
public class DataTableParameter {
	
	private int sEcho; //请求服务器端次数
	
	private int iDisplayStart;//其实记录，第一条为0
	private int iDisplayLength;//每页显示的长度
	
	private int iColumns;
	private List<String> mDataProps; //列的Name列表
	private List<Boolean> bSortables;//列对应是否能排序
	
	private int iSortingCols;
	private List<Integer> iSortCols;    //排序列的编号
	private List<String> iSortColsName; //排序列的名称
	private List<String> sSortDirs;     //排布列排序形式 Asc/Desc
	
	public int getsEcho() {
		return sEcho;
	}
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public int getiColumns() {
		return iColumns;
	}
	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}
	public List<String> getmDataProps() {
		return mDataProps;
	}
	public void setmDataProps(List<String> mDataProps) {
		this.mDataProps = mDataProps;
	}
	public List<Boolean> getbSortables() {
		return bSortables;
	}
	public void setbSortables(List<Boolean> bSortables) {
		this.bSortables = bSortables;
	}
	public List<Integer> getiSortCols() {
		return iSortCols;
	}
	public void setiSortCols(List<Integer> iSortCols) {
		this.iSortCols = iSortCols;
	}
	public List<String> getsSortDirs() {
		return sSortDirs;
	}
	public void setsSortDirs(List<String> sSortDirs) {
		this.sSortDirs = sSortDirs;
	}
	public int getiSortingCols() {
		return iSortingCols;
	}
	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public DataTableParameter(int sEcho, int iDisplayStart, int iDisplayLength, int iColumns, List<String> mDataProps, List<Boolean> bSortables, int iSortingCols, List<Integer> iSortCols,
			List<String> sSortDirs,List<String> iSortColsName) {
		super();
		this.sEcho = sEcho;
		this.iDisplayStart = iDisplayStart;
		this.iDisplayLength = iDisplayLength;
		this.iColumns = iColumns;
		this.mDataProps = mDataProps;
		this.bSortables = bSortables;
		this.iSortingCols = iSortingCols;
		this.iSortCols = iSortCols;
		this.sSortDirs = sSortDirs;
		this.iSortColsName = iSortColsName;
	}
	@Override
	public String toString() {
		return "DataTableParameter [sEcho=" + sEcho + ", iDisplayStart=" + iDisplayStart + ", iDisplayLength=" + iDisplayLength + ", iColumns=" + iColumns + ", mDataProps=" + mDataProps
				+ ", bSortables=" + bSortables + ", iSortingCols=" + iSortingCols + ", iSortCols=" + iSortCols + ", sSortDirs=" + sSortDirs + "]";
	}
	public List<String> getiSortColsName() {
		return iSortColsName;
	}
	public void setiSortColsName(List<String> iSortColsName) {
		this.iSortColsName = iSortColsName;
	}
	
}
