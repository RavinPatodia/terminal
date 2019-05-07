package com.langmy.terminal.modules.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.Select2;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.user.model.BlacklistModel;
import com.langmy.terminal.modules.user.service.BlacklistService;
import com.langmy.terminal.modules.user.service.UserService;

/**
 * 黑名单控制层
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/user/blackList")
public class BlackListController extends BaseController {
	@Autowired
	BlacklistService blacklistService;

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "user/blackList/blackList";
	}

	/**
	 * 获得黑名单列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	protected void list(String jsonParam, BlacklistModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, blacklistService.list(model, dataTableParam));
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/getBlackList/{id}", method = { RequestMethod.GET })
	public void getBlackList(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		BlacklistModel model = blacklistService.view(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("黑名单信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("黑名单信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 添加黑名单
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(BlacklistModel model, HttpServletResponse response) {
		Json j = new Json();
		if (!beanValidator(j, model)) {
			j.setSuccess(false);
			j.setMsg("黑名单保存失败");
			super.writeJson(response, j);
			return;
		}
		if (blacklistService.add(model)) {
			j.setSuccess(true);
			j.setMsg("黑名单保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("黑名单保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 修改黑名单
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(BlacklistModel model, HttpServletResponse response) {
		Json j = new Json();
		blacklistService.edit(model);
		j.setSuccess(true);
		j.setMsg("黑名单修改成功");
		super.writeJson(response, j);
	}

	/**
	 * 解除黑名单
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/recover", method = { RequestMethod.POST })
	public void recover(BlacklistModel model, HttpServletResponse response) {
		Json j = new Json();
		if (blacklistService.relieve(model)) {
			j.setSuccess(true);
			j.setMsg("解除黑名单成功");
		} else {
			j.setSuccess(false);
			j.setMsg("解除黑名单失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 获得黑名单编号下拉框
	 * 
	 * @param blacklistId
	 *            黑名单编号
	 * @param response
	 */
	@RequestMapping(value = "/getBlackIds")
	protected void getBlackIds(String blacklistId, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				blacklistService.getBlackByBalckIds(blacklistId),
				"blacklistId", "blacklistId");
		super.writeJson(response, selects);
	}

	/**
	 * 获得已被拉黑客户下拉框
	 * 
	 * @param uacc
	 *            客户名
	 * @param response
	 */
	@RequestMapping(value = "/getBlackNames")
	protected void getBlackUName(String name, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				blacklistService.getBlackUserName(name), "id", "name");
		super.writeJson(response, selects);
	}
}
