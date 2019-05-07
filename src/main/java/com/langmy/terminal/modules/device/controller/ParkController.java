package com.langmy.terminal.modules.device.controller;

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
import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.charge.utils.ChargeRuleUtils;
import com.langmy.terminal.modules.device.model.ParkModel;
import com.langmy.terminal.modules.device.service.ParkService;


@Controller
@RequestMapping(value = "/device/park")
public class ParkController extends BaseController{ 

	@Autowired
	private ParkService parkService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "device/park/park";
	}
	
	@RequestMapping(value = "/getChargeRule")
	protected void getUser(HttpServletResponse response,String ruleName) {
		List<Select> selects = SelectExtend.convertToSelect(
				ChargeRuleUtils.findEffectAnchorChargeRule(ruleName), "id", "ruleName");
		super.writeJson(response, selects);
	}
	
	@RequestMapping(value = "/getParkTree", method = { RequestMethod.GET })
	public void getParkTree(HttpServletResponse response) {
		super.writeJson(response, parkService.getParkTreeJson());
	}
	
	@RequestMapping(value = "/getTreeNodeShow", method = { RequestMethod.GET })
	public void getParkTreeNodeShow(HttpServletResponse response) {
		super.writeJson(response, parkService.getParkTreeJsonNodeShow());
	}
	
	/**
	 * 判断输入的停车场编号是否已存在
	 * 
	 * @param parkId
	 * @param response
	 */
	@RequestMapping(value = "/getSameParkName", method = { RequestMethod.POST })
	public void getSameParkId(String name, HttpServletResponse response) {
		Json j = new Json();
		try {
			if (parkService.getSameParkName(name)) {
				j.setSuccess(true);
				j.setMsg("停车场名称可用");
			} else {
				j.setSuccess(false);
				j.setMsg("停车场名称已存在");
			}
		} catch (ServiceException e) {
			j.setSuccess(false);
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(ParkModel model, HttpServletResponse response) {
		Json j = new Json();
		Park park = parkService.add(model);
		if ( park!= null) {
			j.setSuccess(true);
			j.setMsg(park.getId()+"");
		} else {
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(ParkModel model, HttpServletResponse response) {
		Json j = new Json();
		if (parkService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("修改失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/getPark/{id}", method = { RequestMethod.GET })
	public void getOperater(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		ParkModel model = parkService.view(id);
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
	
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET })
	public void delete(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		if (parkService.delete(id)) {
			j.setSuccess(true);
			j.setMsg("删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("该停车场已绑定车位，不能执行删除操作");
		}
		super.writeJson(response, j);
	}

	
}
