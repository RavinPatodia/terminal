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
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.DelValidate;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.charge.model.DctRuleGroupModel;
import com.langmy.terminal.modules.charge.service.DctRuleGroupService;
import com.langmy.terminal.modules.charge.service.DctRuleService;

/**
 * 优惠规则组
 * @author lzy
 */
@Controller
@RequestMapping(value = "charge/dctRuleGroup")
public class DctRuleGroupController extends BaseController {

	@Autowired
	private DctRuleGroupService dctRuleGroupService;
	@Autowired
	private DctRuleService dctRuleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "charge/dctRuleGroup/dctRuleGroup";
	}


	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, DctRuleGroupModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, dctRuleGroupService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(DctRuleGroupModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			if (dctRuleGroupService.add(model)) {
				j.setSuccess(true);
				j.setMsg("优惠规则组保存成功");
			} else {
				j.setSuccess(false);
				j.setMsg("优惠规则组保存失败");
			}
		} catch (ServiceException e) {
			logger.error("优惠规则组新增失败",e);
			j.setSuccess(false);
			j.setMsg("优惠规则组保存失败");
		}
		super.writeJson(response, j);
	}

	/*@RequestMapping(value = "/editRulesInGroup", method = { RequestMethod.POST })
	public void editRulesInGroup(DctRuleGroupModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			dctRuleGroupService.editRulesInGroup(model);
			j.setSuccess(true);
			j.setMsg("组内优惠规则修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("组内优惠规则修改失败");
			logger.error("组内优惠规则修改失败", e);
		}
		super.writeJson(response, j);
	}*/
	
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(DctRuleGroupModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			dctRuleGroupService.edit(model);
			j.setSuccess(true);
			j.setMsg("优惠规则组修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("优惠规则组修改失败");
			logger.error("优惠规则组修改失败", e);
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getDctRuleGroup/{id}", method = { RequestMethod.GET })
	public void getDctRuleGroup(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		DctRuleGroupModel model = dctRuleGroupService.getDctRuleGroupById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("优惠规则组信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("优惠规则组信息获取失败");
		}
		super.writeJson(response, j);
	}


	@RequestMapping(value = "/getDctRuleGroupId", method = { RequestMethod.GET })
	public void getDctRuleGroupId(HttpServletResponse response) {
		Json j = new Json();
		String dctRuleGroupId = dctRuleGroupService.getGenId("dctRuleGroup");
		if(StringUtils.isNotNullAndEmpty(dctRuleGroupId)){
			j.setObject(dctRuleGroupId);
			j.setSuccess(true);
		}
		else{
			j.setMsg("获取生成的优惠规则组编号失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (dctRuleGroupService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("优惠规则删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("优惠规则删除失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/delValidate/{ids}", method = { RequestMethod.GET })
	@ResponseBody
	public Json delValidate(@PathVariable(value = "ids") String ids) {
		Json j = new Json();
		DelValidate delValidate = dctRuleGroupService.validateDel(ids) ;
		j.setObject(delValidate);
		j.setSuccess(true);
		j.setMsg("验证是否允许被删除");
		return j;
	}
}
