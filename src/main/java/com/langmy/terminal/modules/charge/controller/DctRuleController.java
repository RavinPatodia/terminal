package com.langmy.terminal.modules.charge.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.modules.charge.model.DctRuleModel;
import com.langmy.terminal.modules.charge.service.DctRuleService;

/**
 * 优惠规则
 * @author lzy
 *
 */
@Controller
@RequestMapping(value = "charge/dctRule")
public class DctRuleController extends BaseController {

	@Autowired
	private DctRuleService dctRuleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "charge/dctRule/dctRule";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, DctRuleModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, dctRuleService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST,RequestMethod.GET })
	public void add(DctRuleModel model, HttpServletResponse response) {
		Json j = new Json();
		if (dctRuleService.add(model)) {
			j.setSuccess(true);
			j.setMsg("优惠规则保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("优惠规则保存失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(DctRuleModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			dctRuleService.edit(model);
			j.setSuccess(true);
			j.setMsg("优惠规则修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("优惠规则修改失败");
			logger.error("优惠规则修改失败", e);
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getDctRule/{id}", method = { RequestMethod.GET })
	public void getDctRule(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		DctRuleModel model = dctRuleService.getDctRuleById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("优惠规则信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("优惠规则信息获取失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getDctRuleId", method = { RequestMethod.GET })
	public void getDctRuleId(HttpServletResponse response) {
		Json j = new Json();
		String dctRuleId = dctRuleService.getGenId("dctRule");
		j.setObject(dctRuleId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		try {
			dctRuleService.delete(ids);
			j.setSuccess(true);
			j.setMsg("优惠规则删除成功");
		} catch (ServiceException e) {
			logger.error("优惠规则删除失败",e);
			j.setSuccess(false);
			j.setMsg("优惠规则删除失败");
		}
			
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/getDctRules", method = { RequestMethod.GET })
	@ResponseBody
	public List<Select> getDctRules(HttpServletResponse response) {
		List<Select> dctRuleModels  = MutiSelectExtend.convertToSelect(dctRuleService.getEnableAndActiveDctRules(), "id", "dctName", false);
		return dctRuleModels;
	}
	
}
