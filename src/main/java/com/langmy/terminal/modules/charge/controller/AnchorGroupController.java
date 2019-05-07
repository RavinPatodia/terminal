package com.langmy.terminal.modules.charge.controller;

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
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.DelValidate;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.charge.model.AnchorGroupModel;
import com.langmy.terminal.modules.charge.service.AnchorGroupService;
import com.langmy.terminal.modules.charge.service.AnchorRentService;

/**
 * 长期收费组
 * @author lzy
 *
 */
@Controller
@RequestMapping(value = "charge/anchorGroup")
public class AnchorGroupController extends BaseController {

	@Autowired
	private AnchorGroupService anchorGroupService;
	@Autowired
	private AnchorRentService anchorRentService;

	/**
	 * 跳转到长期收费组页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "charge/anchorGroup/anchorGroup";
	}

	/**
	 * 前台DataTable显示相应表格
	 * @param jsonParam 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, AnchorGroupModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, anchorGroupService.list(model, dataTableParam));
	}

	/**
	 * 新增一个长期收费组
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ResponseBody
	public Json add(AnchorGroupModel model) {
		Json j = new Json();
		if (anchorGroupService.add(model)) {
			j.setSuccess(true);
			j.setMsg("长期收费规则组保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("长期收费规则组保存失败");
		}
		return j;
	}

	/**
	 * 修改长期规则组
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(AnchorGroupModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			anchorGroupService.edit(model);
			j.setSuccess(true);
			j.setMsg("长期收费规则组修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("长期收费规则组修改失败");
			logger.error("长期收费规则组修改失败", e);
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 修改长期规则组中长期规则
	 * @param model
	 * @param response
	 */
	/*@RequestMapping(value = "/editRulesInGroup", method = { RequestMethod.POST })
	public void editRulesInGroup(AnchorGroupModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			anchorGroupService.editRulesInGroup(model);
			j.setSuccess(true);
			j.setMsg("组内长期规则规则修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("组内长期规则修改失败");
			logger.error("组内长期规则修改失败", e);
		}
		super.writeJson(response, j);
	}*/

	/**
	 * 根据Id获取相应的长期收费组
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/getAnchorGroup/{id}", method = { RequestMethod.GET })
	public void getAnchorGroup(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		AnchorGroupModel model = anchorGroupService.findAnchorGroupById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("长期收费规则组信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("长期收费规则组信息获取失败");
		}
		super.writeJson(response, j);
	}


	/**
	 * 动态生成相应的长期规则组编号
	 * @param response
	 */
	@RequestMapping(value = "/getAnchorGroupId", method = { RequestMethod.GET })
	public void getAnchorGroupId(HttpServletResponse response) {
		Json j = new Json();
		String anchorGroupId = anchorGroupService.getGenId("anchorGroup");
		j.setObject(anchorGroupId);
		j.setSuccess(true);
		super.writeJson(response, j);
	}
	
	/**
	 * 根据名称查询相应的长期收费组
	 * @param name 传入的名称
	 * @param response
	 */
	@RequestMapping(value = "/select")
	protected void list(String name,HttpServletResponse response){
		super.writeJson(response, anchorGroupService.list(name));
	}

	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (anchorGroupService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("长期收费规则删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("长期收费规则删除失败");
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 删除前对对象进行验证
	 * @param ids 需要删除验证的对象
	 * @return
	 */
	@RequestMapping(value = "/delValidate/{ids}", method = { RequestMethod.GET })
	@ResponseBody
	public Json delValidate(@PathVariable(value = "ids") String ids) {
		Json j = new Json();
		DelValidate delValidate = anchorGroupService.validateDel(ids) ;
		j.setObject(delValidate);
		j.setSuccess(true);
		j.setMsg("验证是否允许被删除");
		return j;
	}
}
