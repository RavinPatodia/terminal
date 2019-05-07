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
import com.langmy.terminal.modules.charge.model.AnchorRentModel;
import com.langmy.terminal.modules.charge.service.AnchorRentService;

/**
 * 长期收费
 * 
 * @author lzy
 */
@Controller
@RequestMapping(value = "charge/anchorRent")
public class AnchorRentController extends BaseController {

	@Autowired
	private AnchorRentService anchorRentService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "charge/anchorCharge/anchorCharge";

	}

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, AnchorRentModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, anchorRentService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(AnchorRentModel model, HttpServletResponse response) {
		Json j = new Json();
		if (anchorRentService.add(model)) {
			j.setSuccess(true);
			j.setMsg("长期收费规则保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("长期收费规则保存失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(AnchorRentModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			anchorRentService.edit(model);
			j.setSuccess(true);
			j.setMsg("长期收费规则修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("长期收费规则修改失败");
			logger.error("长期收费规则修改失败", e);
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getAnchorRent/{id}", method = { RequestMethod.GET })
	public void getAnchorRent(@PathVariable(value = "id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		AnchorRentModel model = anchorRentService.findAnchorRentById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("长期收费规则信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("长期收费规则信息获取失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getAnchorRentId", method = { RequestMethod.GET })
	public void getAnchorRentId(HttpServletResponse response) {
		Json j = new Json();
		String anchorRentId = anchorRentService.getGenId("anchorRent");
		j.setObject(anchorRentId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids, HttpServletResponse response) {
		Json j = new Json();
		try {
			anchorRentService.delete(ids);
			j.setSuccess(true);
			j.setMsg("长期收费规则删除成功");
		} catch (ServiceException e) {
			logger.error("长期收费规则删除失败", e);
			j.setSuccess(false);
			j.setMsg("长期收费规则删除失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/allAnchorRents", method = { RequestMethod.GET })
	@ResponseBody
	public List<Select> getAnchorRents(HttpServletResponse response) {
		List<Select> anchorRents  = MutiSelectExtend.convertToSelect(anchorRentService.getEnableAndActiveAnchorRent(), "id", "name", false);
		return anchorRents;
	}
	
}
