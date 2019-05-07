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
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.device.model.BayonetModel;
import com.langmy.terminal.modules.device.service.BayonetService;
import com.langmy.terminal.modules.device.utils.BayonetUtils;


/**
 * 卡口 控制端
 * @author LuZixiang
 *
 */
@Controller
@RequestMapping(value = "/device/bayonet")
public class BayonetController extends BaseController {

	@Autowired
	private BayonetService bayonetService;

	private String listUrl = "/park/device/bayonet/list";
	private String editAndDblUrl = "/park/device/bayonet/getBayonet/";
	private String getBayonetIdUrl = "/park/device/bayonet/getBayonetId";
	private String addUrl = "/park/device/bayonet/add";
	private String editUrl = "/park/device/bayonet/edit";
	private String getSameBayonetIdUrl = "/park/device/bayonet/getSameBayonetId";
	private String delUrl = "/park/device/bayonet/del/";
	private String getBayonetUrl = "/park/device/bayonet/getBayonet?";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage() {
		Map<String, String> url = Maps.newHashMap();
		url.put("listUrl", listUrl);
		url.put("editAndDblUrl", editAndDblUrl);
		url.put("getBayonetIdUrl", getBayonetIdUrl);
		url.put("addUrl", addUrl);
		url.put("editUrl", editUrl);
		url.put("getSameBayonetIdUrl", getSameBayonetIdUrl);
		url.put("delUrl", delUrl);
		url.put("getBayonetUrl", getBayonetUrl);
		return new ModelAndView("device/bayonet/bayonet", url);
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
	 * 判断输入的卡口编号是否已存在
	 * @param bayonetId 卡口编号
	 * @param response
	 */
	@RequestMapping(value = "/getSameBayonetId", method = { RequestMethod.POST })
	public void getSameBayonetId(String bayonetId, HttpServletResponse response) {
		Json j = new Json();
		try {
			if (bayonetService.validateIsExist(bayonetId)) {
				j.setSuccess(true);
				j.setMsg("卡口可用");
			} else {
				j.setSuccess(false);
				j.setMsg("卡口已存在");
			}
		} catch (ServiceException e) {
			j.setSuccess(false);
		}
		super.writeJson(response, j);
	} 
	
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, BayonetModel model, HttpServletResponse response) {
		
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, bayonetService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(BayonetModel model, HttpServletResponse response) {
		Json j = new Json();
		if (!beanValidator(j,model)) {
			j.setSuccess(false);
			j.setMsg("设备保存失败");
			//j.setObject(m);
			super.writeJson(response, j);
			return;
		}
		if (bayonetService.add(model)) {
			j.setSuccess(true);
			j.setMsg("设备保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备保存失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(BayonetModel model, HttpServletResponse response) {
		Json j = new Json();
		if (bayonetService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("设备修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("设备修改失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/getBayonet/{id}", method = { RequestMethod.GET })
	public void getBayonet(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		BayonetModel model = bayonetService.getBayonetById(id);
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


	@RequestMapping(value = "/getBayonetId", method = { RequestMethod.GET })
	public void getBayonetId(HttpServletResponse response) {
		Json j = new Json();
		String bayonetId = bayonetService.getGenId("bayonet");
		j.setObject(bayonetId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (bayonetService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("卡口删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("卡口删除失败");
		}
		super.writeJson(response, j);
	}
}