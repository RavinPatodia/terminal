package com.langmy.terminal.modules.charge.controller;

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
import com.langmy.terminal.modules.charge.model.DelChargeRecModel;
import com.langmy.terminal.modules.charge.service.DelChargeRecService;

/**
 * 已删除收费记录
 * @author lzy
 *
 */
@Controller
@RequestMapping(value = "charge/delChargeRec")
public class DelChargeRecController extends BaseController {

	@Autowired
	private DelChargeRecService delChargeRecService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "charge/delChargeRec/delChargeRec";
	}


	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, DelChargeRecModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, delChargeRecService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/getDelChargeRec/{id}", method = { RequestMethod.GET })
	public void getDelChargeRec(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		DelChargeRecModel model = delChargeRecService.getDelChargeRecById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("收费规则信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("收费规则信息获取失败");
		}
		super.writeJson(response, j);
	}


	@RequestMapping(value = "/getDelChargeRecId", method = { RequestMethod.GET })
	public void getDelChargeRecId(HttpServletResponse response) {
		Json j = new Json();
		j.setObject("getDelChargeRecId");
		j.setSuccess(true);
		j.setMsg("启用收费规则成功");
		super.writeJson(response, j);
	}

}
