package com.langmy.terminal.modules.sys.controller;

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
import com.langmy.terminal.modules.sys.model.RoleModel;
import com.langmy.terminal.modules.sys.service.RoleService;

/**
 * 角色控制层
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/admin/sys/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "system/role/role";
	}

	/**
	 * 获得角色列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, RoleModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, roleService.list(model, dataTableParam));
	}

	/**
	 * 根据roleName获得角色下拉框
	 * 
	 * @param roleName
	 *            角色名称
	 * @param response
	 */
	@RequestMapping(value = "/getRoleNames")
	protected void getRoleNames(String roleName, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				roleService.getRoleByName(roleName), "roleName", "roleName");
		super.writeJson(response, selects);
	}

	/**
	 * 根据roleId获得角色下拉框
	 * 
	 * @param roleId
	 *            角色编号
	 * @param response
	 */
	@RequestMapping(value = "/getRoleIds")
	protected void getRoleIds(String roleId, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				roleService.getRoleById(roleId), "roleId", "roleId");
		super.writeJson(response, selects);
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/getRole/{id}", method = { RequestMethod.GET })
	public void getRole(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		RoleModel model = roleService.view(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("角色信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("角色信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 添加角色
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(RoleModel model, HttpServletResponse response) {
		Json j = new Json();
		if (roleService.add(model)) {
			j.setSuccess(true);
			j.setMsg("角色保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("角色保存失败");
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
	public void delete(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (roleService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("删除角色成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除角色失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 编辑角色
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(RoleModel model, HttpServletResponse response) {
		Json j = new Json();
		if (roleService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("角色修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("角色修改失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 绑定权限
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/bindAuth", method = { RequestMethod.POST })
	public void bindAuth(RoleModel model, HttpServletResponse response) {
		Json j = new Json();
		if (roleService.bindAuths(model)) {
			j.setSuccess(true);
			j.setMsg("权限绑定成功");
		} else {
			j.setSuccess(false);
			j.setMsg("权限绑定失败");
		}
		super.writeJson(response, j);
	}

}
