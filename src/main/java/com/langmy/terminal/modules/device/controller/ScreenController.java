package com.langmy.terminal.modules.device.controller;

import java.io.IOException;
import java.net.UnknownHostException;
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
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.device.model.LedScreenModel;
import com.langmy.terminal.modules.device.service.LedScreenService;
import com.langmy.terminal.modules.device.utils.DeviceUtils;
import com.langmy.terminal.modules.psp.utils.AreaUtils;


@Controller
@RequestMapping(value = "/device/screen")
public class ScreenController extends BaseController {

	@Autowired
	private LedScreenService ledScreenService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "device/screen/screen";
	}

	/**
	 * 获取卡口动态下拉框
	 * @param name
	 * @param response
	 */
	@RequestMapping(value = "/getDataMaster")
	protected void getDataMaster(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				DeviceUtils.getDataMaster(name), "id", "name");
		super.writeJson(response, selects);
	}
	
	/**
	 * 获取区域
	 * @param response
	 */
	@RequestMapping(value = "/getAreas" , method = { RequestMethod.GET })
	@ResponseBody
	public List<Select> getAreas() {
		List<Select> areaModels  = MutiSelectExtend.convertToSelect(AreaUtils.getAllAreaWhereISNOTDEL(), "id", "name", false);
		return areaModels;
	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, LedScreenModel model, HttpServletResponse response) {
		model.setConfigureFlag(-1);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, ledScreenService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(LedScreenModel model, HttpServletResponse response) throws UnknownHostException, IOException {
		Json j = new Json();
		if (!beanValidator(j,model)) {
			j.setSuccess(false);
			j.setMsg("设备保存失败");
			//j.setObject(m);
			super.writeJson(response, j);
			return;
		}
		if (ledScreenService.add(model)) {
			j.setSuccess(true);
			j.setMsg("设备保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备保存失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(LedScreenModel model, HttpServletResponse response) throws UnknownHostException, IOException {
		Json j = new Json();
		if (ledScreenService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("设备修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备连接失败");
		}
		super.writeJson(response, j);
	}

	
	
	@RequestMapping(value = "/getLedScreen/{id}", method = { RequestMethod.GET })
	public void getLedScreen(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		LedScreenModel model = ledScreenService.getLedScreenById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("设备信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备信息获取失败");
		}
		super.writeJson(response, j);
	}


	@RequestMapping(value = "/getLedScreenId", method = { RequestMethod.GET })
	public void getLedScreenId(HttpServletResponse response) {
		Json j = new Json();
		String deviceId = ledScreenService.getGenId("screen");
		j.setObject(deviceId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}
	
	
/*	*//**
	 * 区域配置保存区域信息
	 * @param model 显示屏model
	 * @param response
	 *//*
	@RequestMapping(value = "/configure", method = { RequestMethod.POST })
	public void configure(LedScreenModel model, HttpServletResponse response) {
		Json j = new Json();
		if (ledScreenService.configure(model)) {
			j.setSuccess(true);
			j.setMsg("设备修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备修改失败");
		}
		super.writeJson(response, j);
	}

	*//**
	 * 区域配置获取区域
	 * @param id 显示屏ID
	 * @param response
	 *//*
	@RequestMapping(value = "/getScreenArea/{id}", method = { RequestMethod.POST })
	public void config(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		LedScreenModel model = deviceService.getLedScreenById(id);
		model.setType(SCREEN_TYPE);
		if (deviceService.configure(model)) {
			j.setSuccess(true);
			j.setMsg("设备获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备获取失败");
		}
		super.writeJson(response, j);
	}*/
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (ledScreenService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("显示屏删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("显示屏删除失败");
		}
		super.writeJson(response, j);
	}
}