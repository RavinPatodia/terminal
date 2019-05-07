package com.langmy.terminal.modules.device.controller;

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
import com.langmy.terminal.modules.device.model.DrivewayModel;
import com.langmy.terminal.modules.device.service.DrivewayService;
import com.langmy.terminal.modules.device.utils.BayonetUtils;
import com.langmy.terminal.modules.device.utils.BrakeMachineUtils;
import com.langmy.terminal.modules.device.utils.DrivewayUtils;
import com.langmy.terminal.modules.device.utils.LedScreenUtils;
import com.langmy.terminal.modules.device.utils.TerminalUtils;


/**
 * 卡口 控制端
 * @author LuZixiang
 *
 */
@Controller
@RequestMapping(value = "/device/driveway")
public class DrivewayController extends BaseController {

	@Autowired
	private DrivewayService drivewayService;

	private final static int IN = 0;		// 进口
	private final static int OUT = 1;		// 出口
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "device/driveway/driveway";
	}
	
	/**
	 * 所有车道动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getDriveway")
	protected void getDriveway(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				DrivewayUtils.getDrivewayByNameLike(name), "id", "name");
		super.writeJson(response, selects);
	}
	
	/**
	 * 所有终端机动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getTerminal")
	protected void getTerminal(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				TerminalUtils.getTerminalByNameAndType(name), "id", "name");
		super.writeJson(response, selects);
	}
	
	/**
	 * 进口车道动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getDrivewayIn")
	protected void getDrivewayIn(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				DrivewayUtils.getDrivewayByNameLikeAndDirection(name, IN), "id", "name");
		super.writeJson(response, selects);
	}
	
	/**
	 * 出口车道动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getDrivewayOut")
	protected void getDrivewayOut(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				DrivewayUtils.getDrivewayByNameLikeAndDirection(name,OUT), "id", "name");
		super.writeJson(response, selects);
	}
	
	
	/**
	 * 卡口动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getBayonet")
	protected void getBayonet(String bayonetName,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				BayonetUtils.getAllByName(bayonetName), "id", "name");
		super.writeJson(response, selects);
	}
	/**
	 * 闸机动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getBrake")
	protected void getBrake(String brakeMachine,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				BrakeMachineUtils.getAllBrakeMachineByName(brakeMachine), "id", "name");
		super.writeJson(response, selects);
	}
	/**
	 * 卡口动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getScreen")
	protected void getScreen(String bayonetName,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				BayonetUtils.getAllByName(bayonetName), "id", "name");
		super.writeJson(response, selects);
	}
	
	
	/**
	 * 进口车道动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getScreenIn")
	protected void getScreenIn(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(LedScreenUtils.findMessageScreen(), "id", "name");
		super.writeJson(response, selects);
	}
	
	/**
	 * 出口车道动态下拉框
	 * @param response
	 */
	@RequestMapping(value = "/getScreenOut")
	protected void getScreenOut(String name,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(LedScreenUtils.findCostScreen(), "id", "name");
		super.writeJson(response, selects);
	}
	
	
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, DrivewayModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, drivewayService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(DrivewayModel model, HttpServletResponse response) {
		Json j = new Json();
		if (!beanValidator(j,model)) {
			j.setSuccess(false);
			j.setMsg("车道保存失败");
			//j.setObject(m);
			super.writeJson(response, j);
			return;
		}
		if (drivewayService.add(model)) {
			j.setSuccess(true);
			j.setMsg("车道保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车道保存失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(DrivewayModel model, HttpServletResponse response) {
		Json j = new Json();
		if (drivewayService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("车道修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车道修改失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getDriveway/{id}", method = { RequestMethod.GET })
	public void getDriveway(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		DrivewayModel model = drivewayService.getDrivewayModelById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("车道信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车道信息获取失败");
		}
		super.writeJson(response, j);
	}


	@RequestMapping(value = "/getDrivewayId", method = { RequestMethod.GET })
	public void getDrivewayId(HttpServletResponse response) {
		Json j = new Json();
		String drivewayId = drivewayService.getGenId("driveway");
		j.setObject(drivewayId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (drivewayService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("车道删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车道删除失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/getAllDrivewaySelect", method = { RequestMethod.GET })
	@ResponseBody
	public List<Select> getAllDrivewaySelect() {
		List<Select> dctRuleModels  = MutiSelectExtend.convertToSelect(drivewayService.findNotConfigDriveway(), "id", "name", false);
		return dctRuleModels;
	}	
	
	@RequestMapping(value = "/config", method = { RequestMethod.POST })
	public void config(DrivewayModel model,HttpServletResponse response) {
		Json j = new Json();
		if (drivewayService.config(model)) {
			j.setSuccess(true);
			j.setMsg("车道修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车道修改失败");
		}
		super.writeJson(response, j);
	}
}