package com.langmy.terminal.modules.device.controller;

import java.lang.reflect.InvocationTargetException;
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
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.device.model.CameraModel;
import com.langmy.terminal.modules.device.service.CameraService;
import com.langmy.terminal.modules.device.utils.DrivewayUtils;

@Controller
@RequestMapping(value = "/device/camera")
public class CameraController extends BaseController {

	@Autowired
	private CameraService cameraService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "device/camera/camera";
	}

	/**
	 * 车道动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getDriveway")
	protected void getDriveway(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				DrivewayUtils.getDrivewayByNameLike(name), "id", "name");
		super.writeJson(response, selects);
	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, CameraModel model, HttpServletResponse response) {
		model.setNowState(-1);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, cameraService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(CameraModel model, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		Json j = new Json();
		if (!beanValidator(j,model)) {
			j.setSuccess(false);
			j.setMsg("摄像头信息保存失败");
			//j.setObject(m);
			super.writeJson(response, j);
			return;
		}
		if (cameraService.add(model)) {
			j.setSuccess(true);
			j.setMsg("摄像头信息保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("摄像头信息保存失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(CameraModel model, HttpServletResponse response) {
		Json j = new Json();
		if(!beanValidator(j,model)){
			j.setSuccess(false);
			j.setMsg("摄像头信息修改失败");
			super.writeJson(response, j);
			return;
		}
		if(cameraService.edit(model)){
			j.setSuccess(true);
			j.setMsg("摄像头信息修改成功");
		}else {
			
			j.setSuccess(false);
			j.setMsg("摄像头信息修改失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getCamera/{id}", method = { RequestMethod.GET })
	public void getCamera(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		CameraModel model = cameraService.getCameraById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("摄像头信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("摄像头信息获取失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/enable/{ids}", method = { RequestMethod.GET })
	public void enable(@PathVariable(value="ids")String ids, HttpServletResponse response) {
		Json j = new Json();
		if (cameraService.enable(ids)) {
			j.setSuccess(true);
			j.setMsg("启用摄像头成功");
		} else {
			j.setSuccess(false);
			j.setMsg("禁用摄像头失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/disable/{ids}", method = { RequestMethod.GET })
	public void disable(@PathVariable(value="ids")String ids, HttpServletResponse response) {
		Json j = new Json();
		if (cameraService.disable(ids)) {
			j.setSuccess(true);
			j.setMsg("禁用摄像头成功");
			//LogUtil.save("收费模块", "禁用摄像头", "禁用摄像头,id="+ids);
		} else {
			j.setSuccess(false);
			j.setMsg("禁用摄像头失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getCameraId", method = { RequestMethod.GET })
	public void getCameraId(HttpServletResponse response) {
		Json j = new Json();
		String cameraId = cameraService.getGenId("c");
		j.setObject(cameraId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (cameraService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("摄像机删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("摄像机删除失败");
		}
		super.writeJson(response, j);
	}
}
