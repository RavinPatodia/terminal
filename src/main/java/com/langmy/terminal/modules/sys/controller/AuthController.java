package com.langmy.terminal.modules.sys.controller;

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
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.sys.model.AuthModel;
import com.langmy.terminal.modules.sys.service.AuthService;

/**
 * 资源的控制器
 * 
 * @author lxj
 *
 */
@Controller
@RequestMapping(value = "/admin/sys/auth")
public class AuthController extends BaseController{

	@Autowired
	private AuthService authService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "system/auth/auth";
	}
	
	/**
	 * 获取资源树，获取所有节点
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getTree", method = { RequestMethod.GET })
	public void getAuthTree(HttpServletResponse response) {
		super.writeJson(response, authService.getAuthTreeJson());
	}
	
	
	/**
	 * 获取资源树，获取需要显示的节点
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getTreeNodeShow", method = { RequestMethod.GET })
	public void getAuthTreeNodeShow(HttpServletResponse response) {
		super.writeJson(response, authService.getAuthTreeJsonNodeShow());
	}
	
	/**
	 * 添加资源
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(AuthModel model, HttpServletResponse response) {
		Json j = new Json();
		if (authService.add(model)) {
			j.setSuccess(true);
			j.setMsg("添加成功");
		} else {
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 修改资源
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(AuthModel model, HttpServletResponse response) {
		Json j = new Json();
		if (authService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("修改失败");
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 拖拽保存资源
	 * 
	 * @param node.id  parent.id
	 * @param response
	 */
	@RequestMapping(value = "/dndTree", method = { RequestMethod.POST })
	public void dnd(int nodeId,int parentId , HttpServletResponse response) {
		Json j = new Json();
		if (authService.dndSave(nodeId,parentId)) {
			j.setSuccess(true);
			j.setMsg("修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("修改失败");
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 获取资源信息
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/getAuth/{id}", method = { RequestMethod.GET })
	public void getAuth(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		AuthModel model = authService.view(id);
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
	
	/**
	 * 删除资源
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET })
	public void delete(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		if (authService.delete(id)) {
			j.setSuccess(true);
			j.setMsg("删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		super.writeJson(response, j);
	}

	
}
