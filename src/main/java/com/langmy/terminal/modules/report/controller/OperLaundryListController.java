package com.langmy.terminal.modules.report.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.modules.caradmission.model.AdmissionRecModel;
import com.langmy.terminal.modules.caradmission.service.AdmissionRecService;
import com.langmy.terminal.modules.caradmission.service.CarService;
import com.langmy.terminal.modules.caradmission.service.CarTypeService;

/**
 * 值班流水账记录
 * 
 * @author zzd
 *
 */
@Controller
@RequestMapping(value = "/report/operLaundryList")
public class OperLaundryListController extends BaseController {

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
		return "/report/operLaundryList/operLaundryList";
	}

	/**
	 * 获得操作员流水账记录列表
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

}
