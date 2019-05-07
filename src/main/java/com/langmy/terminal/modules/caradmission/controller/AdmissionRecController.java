package com.langmy.terminal.modules.caradmission.controller;

import java.io.IOException;
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
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.Select2;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.caradmission.model.AdmissionRecModel;
import com.langmy.terminal.modules.caradmission.service.AdmissionRecService;
import com.langmy.terminal.modules.caradmission.service.CarService;
import com.langmy.terminal.modules.caradmission.service.CarTypeService;

/**
 * 出入场记录
 * 
 * @author zzd
 *
 */
@Controller
@RequestMapping(value = "/caradmission/admissRec")
public class AdmissionRecController extends BaseController {

	@Autowired
	private AdmissionRecService admissionRecService;
	@Autowired
	private CarTypeService carTypeService;

	@Autowired
	private CarService carService;

	/**
	 * 跳转到出入场记录
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "/caradmission/admissRec/admissRec";
	}

	/**
	 * 获得出入场记录列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, AdmissionRecModel model,
			HttpServletResponse response) throws IOException {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response,
				admissionRecService.list(model, dataTableParam));
	}

	/**
	 * 获得车牌类型下拉框
	 * 
	 * @param licenseType
	 *            车牌类型
	 * @param response
	 */
	@RequestMapping(value = "/getType")
	protected void getType(String licenseType, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				carTypeService.getTypeName(licenseType), "id", "typeName");
		super.writeJson(response, selects);
	}

	/**
	 * 获得车牌号码下拉框
	 * 
	 * @param licensePlate
	 * @param response
	 */
	@RequestMapping(value = "/licensePlate")
	protected void getLicensePlate(String licensePlate,
			HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				carService.getLicensePlates(licensePlate), "licensePlate",
				"licensePlate");
		super.writeJson(response, selects);
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/getAdminssion/{id}", method = { RequestMethod.GET })
	public void getAdminssion(@PathVariable(value = "id") int id,
			HttpServletResponse response) {
		Json j = new Json();
		AdmissionRecModel model = admissionRecService.view(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("出入记录获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("出入记录获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(AdmissionRecModel model, HttpServletResponse response) {
		Json j = new Json();

		admissionRecService.edit(model);
		j.setSuccess(true);
		j.setMsg("出入场修改成功");
		super.writeJson(response, j);
	}

}
