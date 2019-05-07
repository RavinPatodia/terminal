package com.langmy.terminal.common.web;

/**
 * @author lzy
 * 前台显示多选框中显示的text和相应的value的对象
 */
public class Select {
	
	private int id;//选项的值
	private String text;//选项显示的值
	private boolean if_select;//是否被选中
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isIf_select() {
		return if_select;
	}
	public void setIf_select(boolean if_select) {
		this.if_select = if_select;
	}
	
}
