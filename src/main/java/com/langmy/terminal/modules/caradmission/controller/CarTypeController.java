package com.langmy.terminal.modules.caradmission.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.web.Select2;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.caradmission.model.CarTypeModel;
import com.langmy.terminal.modules.caradmission.service.CarTypeService;

/**
 * 车辆相关信息管理控制层
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/caradmission/carType")
public class CarTypeController extends BaseController {
	@Autowired
	private CarTypeService carTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "caradmission/carType/carType";
	}

	/**
	 * 获得车辆颜色列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/colorList", method = RequestMethod.POST)
	protected void colorList(String jsonParam, CarTypeModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, carTypeService.colorList(dataTableParam));
	}

	/**
	 * 获得车辆型号列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/moduleList", method = RequestMethod.POST)
	protected void moduleList(String jsonParam, CarTypeModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, carTypeService.moduleList(dataTableParam));
	}

	/**
	 * 获得车牌类型列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/typeList", method = RequestMethod.POST)
	protected void typeList(String jsonParam, CarTypeModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, carTypeService.typeList(dataTableParam));
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void delete(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (carTypeService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("删除记录成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除记录失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 添加颜色
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/addColor", method = { RequestMethod.POST })
	public void addColor(CarTypeModel model, HttpServletResponse response) {
		Json j = new Json();
		if (!beanValidator(j, model)) {
			j.setSuccess(false);
			j.setMsg("保存失败");
			super.writeJson(response, j);
			return;
		}
		if (carTypeService.addCarColor(model)) {
			j.setSuccess(true);
			j.setMsg("保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 添加车牌类型
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/addType", method = { RequestMethod.POST })
	public void addType(CarTypeModel model, HttpServletResponse response) {
		Json j = new Json();
		if (!beanValidator(j, model)) {
			j.setSuccess(false);
			j.setMsg("保存失败");
			super.writeJson(response, j);
			return;
		}
		if (carTypeService.addTypeName(model)) {
			j.setSuccess(true);
			j.setMsg("保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 添加车辆型号
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/addModule", method = { RequestMethod.POST })
	public void addModule(CarTypeModel model, HttpServletResponse response) {
		Json j = new Json();
		if (!beanValidator(j, model)) {
			j.setSuccess(false);
			j.setMsg("保存失败");
			super.writeJson(response, j);
			return;
		}
		if (carTypeService.addCarModel(model)) {
			j.setSuccess(true);
			j.setMsg("保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 获得车辆类型下拉框
	 * 
	 * @param type
	 * @param response
	 */
	@RequestMapping(value = "/typeName")
	protected void getTypeName(String type, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				carTypeService.getTypeName(type), "typeName", "typeName");
		super.writeJson(response, selects);
	}

	/**
	 * 获得车辆颜色下拉框
	 * 
	 * @param carColor
	 * @param response
	 */
	@RequestMapping(value = "/color")
	protected void getColor(String carColor, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				carTypeService.getColor(carColor), "color", "color");
		super.writeJson(response, selects);
	}

	/**
	 * 获得车辆型号下拉框
	 * 
	 * @param carModel
	 * @param response
	 */
	@RequestMapping(value = "/module")
	protected void getModule(String carModel, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				carTypeService.getModel(carModel), "model", "model");
		super.writeJson(response, selects);
	}

}
