package com.langmy.terminal.modules.charge.controller;

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
import com.langmy.terminal.modules.charge.model.MeterChargeModel;
import com.langmy.terminal.modules.charge.service.MeterChargeService;

/**
 * 计次收费
 * @author lzy
 *
 */
@Controller
@RequestMapping(value = "charge/meterCharge")
public class MeterChargeController extends BaseController {

	@Autowired
	private MeterChargeService meterChargeService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "charge/meterCharge/meterCharge";
	}
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, MeterChargeModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, meterChargeService.list(model, dataTableParam));
	}

//	@RequestMapping(value = "/add", method = { RequestMethod.POST })
//	public void add(MeterChargeModel model, HttpServletResponse response) {
//		Json j = new Json();
//		if (!beanValidator(j,model)) {
//			j.setSuccess(false);
//			j.setMsg("计次收费规则保存失败");
//			//j.setObject(m);
//			super.writeJson(response, j);
//			return;
//		}
//		if (meterChargeService.add(model)) {
//			j.setSuccess(true);
//			j.setMsg("计次收费规则保存成功");
//		} else {
//			j.setSuccess(false);
//			j.setMsg("计次收费规则保存失败");
//		}
//		super.writeJson(response, j);
//	}


	@RequestMapping(value = "/getMeterCharge/{id}", method = { RequestMethod.GET })
	public void getMeterCharge(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		MeterChargeModel model = meterChargeService.getMeterChargeById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("计次收费规则信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("计次收费规则信息获取失败");
		}
		super.writeJson(response, j);
	}


	@RequestMapping(value = "/getMeterChargeId", method = { RequestMethod.GET })
	public void getMeterChargeId(HttpServletResponse response) {
		Json j = new Json();
		String meterChargeId = meterChargeService.getGenId("meterCharge");
		j.setObject(meterChargeId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}
//	@RequestMapping(value = "/select")
//	protected void list(String name,HttpServletResponse response){
//		super.writeJson(response, meterChargeService.list(name));
//	}
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (meterChargeService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("计次收费规则删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("计次收费规则删除失败");
		}
		super.writeJson(response, j);
	}
	

}
