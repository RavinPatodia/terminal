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
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select2;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.caradmission.dao.CarDao;
import com.langmy.terminal.modules.caradmission.model.CarModel;
import com.langmy.terminal.modules.caradmission.service.CarService;
import com.langmy.terminal.modules.caradmission.service.CarTypeService;
import com.langmy.terminal.modules.user.model.UserModel;
import com.langmy.terminal.modules.user.utils.UserUtils;

/**
 * 车辆管理控制层
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/caradmission/car")
public class CarController extends BaseController {
	@Autowired
	private CarService carService;
	@Autowired
	private CarDao carDao;
	@Autowired
	private CarTypeService carTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "caradmission/car/car";
	}

	/**
	 * 车辆列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	protected void list(String jsonParam, CarModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, carService.list(model, dataTableParam));
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
		if (carService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("删除车辆成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除车辆失败");
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
	public void add(CarModel model, HttpServletResponse response) {
		Json j = new Json();
		if (!beanValidator(j, model)) {
			j.setSuccess(false);
			j.setMsg("车辆信息保存失败");
			super.writeJson(response, j);
			return;
		}
		if (carService.add(model)) {
			j.setSuccess(true);
			j.setMsg("车辆信息保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车辆信息保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 通过id查找客户
	 * 
	 * @param id
	 *            车辆id
	 * @param response
	 */
	@RequestMapping(value = "/getUser/{id}", method = { RequestMethod.GET })
	public void getUser(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		int userId = carDao.findUserIdByCar(id);
		UserModel model = UserUtils.getUser(userId);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("用户信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("用户信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 查看车辆详细信息
	 * 
	 * @param id
	 *            车辆id
	 * @param response
	 */
	@RequestMapping(value = "/getCar/{id}", method = { RequestMethod.GET })
	public void getCar(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		CarModel model = carService.view(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("车辆信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车辆信息获取失败");
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
	public void edit(CarModel model, HttpServletResponse response) {
		Json j = new Json();
		carService.edit(model);
		j.setSuccess(true);
		j.setMsg("车辆信息修改成功");
		super.writeJson(response, j);
	}

	/**
	 * 判断车辆是否已存在 根据licensePlate查询
	 * 
	 * @param licensePlate
	 *            车牌号码
	 * @param response
	 */
	@RequestMapping(value = "/ifExist")
	public void ifExit(String licensePlate, HttpServletResponse response) {
		Json j = new Json();
		if (carService.ifExist(licensePlate)) {
			j.setSuccess(true);
		} else {
			j.setSuccess(false);
		}
		super.writeJson(response, j);
	}

	/**
	 * 获得车牌号码下拉框 根据licensePlate查询
	 * 
	 * @param licensePlate
	 *            车牌号码
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

}
