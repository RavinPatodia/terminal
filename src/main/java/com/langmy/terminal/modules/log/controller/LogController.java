package com.langmy.terminal.modules.log.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.log.model.LogModel;
import com.langmy.terminal.modules.log.service.LogService;

/**
 * 操作日志Controller
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "log/log";
	}

	/**
	 * 获得日志列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, LogModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, logService.list(model, dataTableParam));
	}

	/**
	 * 删除日志记录
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void delete(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (logService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("删除日志成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除日志失败");
		}
		super.writeJson(response, j);
	}

}
