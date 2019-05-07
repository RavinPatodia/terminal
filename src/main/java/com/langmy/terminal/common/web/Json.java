package com.langmy.terminal.common.web;

import java.io.Serializable;
import java.util.Map;

/**
 * 用来封装转化成json字符串返回前台的对象
 * @author lzy
 */
public class Json implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7580655381021395783L;

	/**
	 * hibernate 验证的结果
	 */
	private boolean validateResult = false;
	
	/**
	 * hibernate 验证失败后的信息
	 */
	private Map<String,String> validateMsg;
	
	/**
	 * 后台处理的结果
	 */
	private String msg = "";

	private Object object;

	/**
	 * 后台处理的结果
	 */
	private boolean success = false;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isValidateResult() {
		return validateResult;
	}

	public void setValidateResult(boolean validateResult) {
		this.validateResult = validateResult;
	}

	public Map<String, String> getValidateMsg() {
		return validateMsg;
	}

	public void setValidateMsg(Map<String, String> validateMsg) {
		this.validateMsg = validateMsg;
	}
}
