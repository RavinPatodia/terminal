package com.langmy.terminal.modules.user.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.DelValidate;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.user.model.UGroupModel;
import com.langmy.terminal.modules.user.service.UGroupService;

/**
 * 客户组控制层
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/user/userGroup")
public class UserGroupController extends BaseController {

	@Autowired
	UGroupService uGroupService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "user/userGroup/userGroup";
	}

	/**
	 * 获得客户组列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	protected void list(String jsonParam, UGroupModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, uGroupService.list(model, dataTableParam));
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 * @throws IntrospectionException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	@RequestMapping(value = "/getGroup/{id}", method = { RequestMethod.GET })
	public void getGroup(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) throws InstantiationException,
			IllegalArgumentException, ClassNotFoundException,
			IntrospectionException, IOException {
		Json j = new Json();
		UGroupModel model = uGroupService.view(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("客户组信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("客户组信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 启用
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/enable/{ids}", method = { RequestMethod.GET })
	public void enable(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (uGroupService.enable(ids)) {
			j.setSuccess(true);
			j.setMsg("启用客户组成功");
		} else {
			j.setSuccess(false);
			j.setMsg("禁用客户组失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 禁用
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/disable/{ids}", method = { RequestMethod.GET })
	public void disable(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (uGroupService.disable(ids)) {
			j.setSuccess(true);
			j.setMsg("禁用客户组成功");
		} else {
			j.setSuccess(false);
			j.setMsg("禁用客户组失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (uGroupService.del(ids)) {
			j.setSuccess(true);
			j.setMsg("删除客户组成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除客户组失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 添加客户组
	 * 
	 * @param model
	 * @param response
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(UGroupModel model, HttpServletResponse response)
			throws ServiceException {
		Json j = new Json();
		if (!beanValidator(j, model)) {
			j.setSuccess(false);
			j.setMsg("客户组保存失败");
			super.writeJson(response, j);
			return;
		}
		if (uGroupService.add(model)) {
			j.setSuccess(true);
			j.setMsg("客户组保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("客户组保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 修改客户组
	 * 
	 * @param model
	 * @param response
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(UGroupModel model, HttpServletResponse response)
			throws ServiceException {
		Json j = new Json();
		if (!beanValidator(j, model)) {
			j.setSuccess(false);
			j.setMsg("客户组保存失败");
			super.writeJson(response, j);
			return;
		}
		if (uGroupService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("客户组修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("客户组修改失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delValidate/{ids}", method = { RequestMethod.GET })
	@ResponseBody
	public Json delValidate(@PathVariable(value = "ids") String ids) {
		Json j = new Json();
		DelValidate delValidate = uGroupService.validateDel(ids);
		j.setObject(delValidate);
		j.setSuccess(true);
		j.setMsg("验证是否允许被删除");
		return j;
	}

	/**
	 * 获得客户组下拉框
	 * 
	 * @param ugroupId
	 *            客户组编号
	 * @param response
	 */
	@RequestMapping(value = "/getNum")
	protected void getAllId(String ugroupId, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				uGroupService.getGroupByUGroupId(ugroupId), "id",
				"ugroupId");
		super.writeJson(response, selects);
	}

	/**
	 * 获得客户组下拉框
	 * 
	 * @param ugroupName
	 * @param response
	 */
	@RequestMapping(value = "/getName")
	protected void getUserGroup(String ugroupName, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				uGroupService.getGroupByName(ugroupName), "id", "name");
		super.writeJson(response, selects);
	}

	/**
	 * 获得普通客户组
	 * 
	 * @param ugroupName
	 * @param response
	 */
	@RequestMapping(value = "/getMemberUGroup")
	protected void getUserMemberGroup(String ugroupName,
			HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				uGroupService.getMemberGroupByName(ugroupName), "id", "name");
		super.writeJson(response, selects);
	}

	/**
	 * 获得长期客户组
	 * 
	 * @param ugroupName
	 * @param response
	 */
	@RequestMapping(value = "/getLongUGroup")
	protected void getLongMemberGroup(String groupName,
			HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				uGroupService.getLongMemberGroup(groupName), "id", "name");
		super.writeJson(response, selects);
	}

	/**
	 * 获得长期客户组
	 * 
	 * @param ugroupName
	 * @param response
	 */
	@RequestMapping(value = "/getTempUGroup")
	protected void getTempUGroupNameId(String groupName,
			HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				uGroupService.getTempGroup(groupName), "id", "name");
		super.writeJson(response, selects);
	}

	/**
	 * 获得非长期客户组
	 * 
	 * @param ugroupName
	 * @param response
	 */
	@RequestMapping(value = "/getNotlong")
	protected void getNotlongNameId(String groupName,
			HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				uGroupService.getNotLongGroup(groupName), "id", "name");
		super.writeJson(response, selects);
	}

}
