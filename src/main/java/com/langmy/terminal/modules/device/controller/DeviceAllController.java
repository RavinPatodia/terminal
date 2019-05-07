package com.langmy.terminal.modules.device.controller;

import java.awt.AWTException;
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
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.Select2;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.device.model.DeviceModel;
import com.langmy.terminal.modules.device.service.DeviceService;
import com.langmy.terminal.modules.device.utils.BayonetUtils;


@Controller
@RequestMapping(value = "/device/deviceAll")
public class DeviceAllController extends BaseController {

	@Autowired
	private DeviceService deviceService;

	private final static Integer BRAKE = 1;			// 闸机
	private final static Integer PAYMENT = 2;		// 缴费机
	private final static Integer TERMINAL = 3;		// 终端机
	private final static Integer INTEGRATED = 4;	// 一体机
	private final static Integer DATAMASTER = 5;	// 数据转换主机
		
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "device/deviceAll/deviceAll";
	}

	/**
	 * 四种设备的列表显示
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/brakeList", method = { RequestMethod.POST })
	protected void brakeList(String jsonParam, DeviceModel model, HttpServletResponse response) {
		model.setType(BRAKE);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, deviceService.list(model, dataTableParam));
	}
	@RequestMapping(value = "/paymentList", method = { RequestMethod.POST })
	protected void paymentList(String jsonParam, DeviceModel model, HttpServletResponse response) {
		model.setType(PAYMENT);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, deviceService.list(model, dataTableParam));
	}
	@RequestMapping(value = "/terminalList", method = { RequestMethod.POST })
	protected void terminalList(String jsonParam, DeviceModel model, HttpServletResponse response) {
		model.setType(TERMINAL);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, deviceService.list(model, dataTableParam));
	}
	@RequestMapping(value = "/integratedList", method = { RequestMethod.POST })
	protected void integratedList(String jsonParam, DeviceModel model, HttpServletResponse response) {
		model.setType(INTEGRATED);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, deviceService.list(model, dataTableParam));
	}
	@RequestMapping(value = "/dataMasterList", method = { RequestMethod.POST })
	protected void dataMasterList(String jsonParam, DeviceModel model, HttpServletResponse response) {
		model.setType(DATAMASTER);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, deviceService.list(model, dataTableParam));
	}
	
	
	/**
	 * 卡口的动态下拉框2
	 * @param name 卡口名称
	 * @param response
	 */
	@RequestMapping(value = "/getBayonet")
	protected void getBayonet(String name,HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				BayonetUtils.getAllByName(name), "name", "name");
		super.writeJson(response, selects);
	}
	
	/**
	 * 卡口的动态下拉框
	 * @param name 卡口名称
	 * @param response
	 */
	@RequestMapping(value = "/getBayonetPK")
	protected void getBayonetPK(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				BayonetUtils.getAllByName(name), "id", "name");
		super.writeJson(response, selects);
	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, DeviceModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, deviceService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(DeviceModel model, HttpServletResponse response) throws UnknownHostException, IOException {
		Json j = new Json();
		if (!beanValidator(j,model)) {
			j.setSuccess(false);
			j.setMsg("设备保存失败");
			//j.setObject(m);
			super.writeJson(response, j);
			return;
		}
		if (deviceService.add(model)) {
			j.setSuccess(true);
			j.setMsg("设备保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备保存失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(DeviceModel model, HttpServletResponse response) throws UnknownHostException, IOException {
		Json j = new Json();
		if (deviceService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("设备修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备修改失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getDevice/{id}", method = { RequestMethod.GET })
	public void getDevice(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		DeviceModel model = deviceService.getDeviceById(id);
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


	@RequestMapping(value = "/getDeviceId", method = { RequestMethod.GET })
	public void getDeviceId(HttpServletResponse response) {
		Json j = new Json();
		String deviceId = deviceService.getGenId("device");
		j.setObject(deviceId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}
	
	/**
	 * 根据传入的ID开启闸机
	 * @param id 设备ID
	 * @param response
	 * @throws AWTException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/enableBrake/{id}", method = { RequestMethod.GET })
	public void openBrake(@PathVariable(value="id")int id, HttpServletResponse response) throws AWTException, UnknownHostException, IOException {
		Json j = new Json();
		if (deviceService.openBrake(id)) {
			j.setSuccess(true);
			j.setMsg("开启设备成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备连接失败");
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 根据传入的ID关闭闸机
	 * @param id 设备ID
	 * @param response
	 * @throws AWTException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/disableBrake/{id}", method = { RequestMethod.GET })
	public void closeBrake(@PathVariable(value="id")int id, HttpServletResponse response) throws AWTException, UnknownHostException, IOException {
		Json j = new Json();
		if (deviceService.closeBrake(id)) {
			j.setSuccess(true);
			j.setMsg("关闭设备成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备连接失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (deviceService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("闸机删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("闸机删除失败");
		}
		super.writeJson(response, j);
	}
	
}