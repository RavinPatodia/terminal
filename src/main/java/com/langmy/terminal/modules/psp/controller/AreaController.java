package com.langmy.terminal.modules.psp.controller;

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
import com.langmy.terminal.modules.psp.model.AreaModel;
import com.langmy.terminal.modules.psp.service.AreaService;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

/**
 * 区域的controller
 * 
 * @author MC
 *
 */
@Controller
@RequestMapping(value = "/pSp/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "pSp/area/area";
	}

	/**
	 * 获取area的list
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, AreaModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, areaService.list(model, dataTableParam));
	}

	/**
	 * 根据pspId获取psp实体
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getPspId", method = { RequestMethod.GET })
	@ResponseBody
	protected List<Select> getPsp(HttpServletResponse response) {
		List<Select> pspModels = MutiSelectExtend.convertToSelect(
				PSpUtils.getAllPspIdsByArea(), "id", "pspId", false);
		return pspModels;
	}

	/**
	 * 添加功能
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(AreaModel model, HttpServletResponse response) {
		Json j = new Json();
		if (areaService.add(model)) {
			j.setSuccess(true);
			j.setMsg("保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 修改功能
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(AreaModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			areaService.edit(model);
			j.setSuccess(true);
			j.setMsg("修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("修改失败");
			logger.error("修改失败", e);
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 删除功能
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/delete/{ids}", method = { RequestMethod.GET })
	public void delete(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (areaService.deleteArea(ids)) {
			j.setSuccess(true);
			j.setMsg("删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 根据id获取area实体
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/getArea/{id}", method = { RequestMethod.GET })
	public void getArea(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		AreaModel model = areaService.getAreaById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 自动生成area编号
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getAreaId", method = { RequestMethod.GET })
	public void getAreaId(HttpServletResponse response) {
		Json j = new Json();
		j.setObject(areaService.getGenId("Area"));
		j.setSuccess(true);
		super.writeJson(response, j);
	}

}
