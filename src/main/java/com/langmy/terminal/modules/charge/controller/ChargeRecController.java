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
import com.langmy.terminal.modules.charge.model.ChargeRecModel;
import com.langmy.terminal.modules.charge.service.ChargeRecService;

/**
 * 收费记录
 * @author lzy
 */
@Controller
@RequestMapping(value = "charge/chargeRec")
public class ChargeRecController extends BaseController {

	@Autowired
	private ChargeRecService chargeRecService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "charge/chargeRec/chargeRec";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, ChargeRecModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, chargeRecService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/getChargeRec/{id}", method = { RequestMethod.GET })
	public void getChargeRec(@PathVariable(value = "id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		ChargeRecModel model = chargeRecService.getChargeRecById(id);
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

	@RequestMapping(value = "/getChargeRecId", method = { RequestMethod.GET })
	public void getChargeRecId(HttpServletResponse response) {
		Json j = new Json();
		j.setObject("getChargeRecId");
		j.setSuccess(true);
		j.setMsg("启用收费规则成功");
		super.writeJson(response, j);
	}
}
