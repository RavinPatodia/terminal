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
import com.langmy.terminal.modules.caradmission.model.ParkingRecModel;
import com.langmy.terminal.modules.caradmission.service.CarService;
import com.langmy.terminal.modules.caradmission.service.CarTypeService;
import com.langmy.terminal.modules.caradmission.service.ParkingRecService;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

/**
 * 停车记录控制层
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/caradmission/parkingRec")
public class ParkingRecController extends BaseController {
	@Autowired
	private ParkingRecService parkingRecService;
	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private CarService carService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "/caradmission/parkingRec/parkingRec";
	}

	/**
	 * 获得停车记录列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, ParkingRecModel model,
			HttpServletResponse response) throws IOException {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, parkingRecService.list(model, dataTableParam));
	}

	/**
	 * 获得车牌类型下拉框
	 * 
	 * @param type
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
	 * @param type
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
	 * 获得车位编号下拉框
	 * 
	 * @param pspId
	 * @param response
	 */
	@RequestMapping(value = "/pspId")
	protected void getPspIds(String pspId, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				PSpUtils.getPspBypspId(pspId), "pspId", "pspId");
		super.writeJson(response, selects);
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/getParkingRec/{id}", method = { RequestMethod.GET })
	public void getParkingRec(@PathVariable(value = "id") int id,
			HttpServletResponse response) {
		Json j = new Json();
		ParkingRecModel model = parkingRecService.view(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("停车记录获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("停车记录获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(ParkingRecModel model, HttpServletResponse response) {
		Json j = new Json();
		if (parkingRecService.add(model)) {
			j.setSuccess(true);
			j.setMsg("停车记录保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("停车记录保存失败");
		}
		super.writeJson(response, j);
	}

}
