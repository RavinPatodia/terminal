package com.langmy.terminal.modules.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.sys.dao.OperaterDao;
import com.langmy.terminal.modules.sys.model.OperShiftModel;
import com.langmy.terminal.modules.sys.service.OperShiftService;
import com.langmy.terminal.modules.sys.service.RoleService;

/**
 * 操作员交接班控制层
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/admin/sys/shift")
public class ShiftController extends BaseController {

	@Autowired
	private OperShiftService operShiftService;
	@Autowired
	private OperaterDao operaterDao;
	@Autowired
	private RoleService roleService;

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 *            操作员id
	 * @param response
	 */
	@RequestMapping(value = "/getShiftRec", method = { RequestMethod.GET })
	public void getOperater(HttpServletRequest request,
			HttpServletResponse response) {
		Json j = new Json();
		OperShiftModel model = operShiftService.view(request);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
		} else {
			j.setSuccess(false);
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/shift", method = { RequestMethod.POST })
	public void shift(OperShiftModel model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Json j = operShiftService.shift(model, request, response);
		super.writeJson(response, j);
	}
}
