package com.langmy.terminal.modules.charge.controller;

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
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTable;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.DelValidate;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.charge.model.ChargeRuleModel;
import com.langmy.terminal.modules.charge.service.ChargeRuleService;

/**
 * 收费规则
 * @author lzy
 */
@Controller
@RequestMapping(value = "/charge/chargeRule")
public class ChargeRuleController extends BaseController {

	@Autowired
	private ChargeRuleService chargeRuleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "charge/chargeRule/chargeRule";
	}


	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	@ResponseBody
	protected DataTable<BaseModel> list(String jsonParam, ChargeRuleModel model) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		return chargeRuleService.list(model, dataTableParam);
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(ChargeRuleModel model, HttpServletResponse response) {
		Json j = new Json();
		if (chargeRuleService.add(model)) {
			j.setSuccess(true);
			j.setMsg("收费规则保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("收费规则保存失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(ChargeRuleModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			chargeRuleService.edit(model);
			j.setSuccess(true);
			j.setMsg("收费规则修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("收费规则修改失败");
			logger.error("收费规则修改失败", e);
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getChargeRule/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Json getChargeRule(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		ChargeRuleModel model = chargeRuleService.getChargeRuleById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("收费规则信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("收费规则信息获取失败");
		}
		return j;
	}


	@RequestMapping(value = "/getChargeRuleId", method = { RequestMethod.GET })
	public void getChargeRuleId(HttpServletResponse response) {
		Json j = new Json();
		String chargeRuleId = chargeRuleService.getGenId("chargeRule");
		j.setObject(chargeRuleId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (chargeRuleService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("收费规则删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("收费规则删除失败");
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 删除之前验证
	 * @param ids 删除验证对象ld的列表
	 * @return
	 */
	@RequestMapping(value = "/delValidate/{ids}", method = { RequestMethod.GET })
	@ResponseBody
	public Json delValidate(@PathVariable(value = "ids") String ids) {
		Json j = new Json();
		DelValidate delValidate = chargeRuleService.validateDel(ids) ;
		j.setObject(delValidate);
		j.setSuccess(true);
		j.setMsg("验证是否允许被删除");
		return j;
	}

}
