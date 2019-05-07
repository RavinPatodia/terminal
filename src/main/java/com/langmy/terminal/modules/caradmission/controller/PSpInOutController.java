package com.langmy.terminal.modules.caradmission.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.caradmission.model.PSpInOutModel;
import com.langmy.terminal.modules.caradmission.service.PSpInOutService;

/**
 * 出入车位
 * 
 * @author lxj
 *
 */
@Controller
@RequestMapping(value = "/caradmission/pspInOut")
public class PSpInOutController extends BaseController {
	@Autowired
	private PSpInOutService pspInOutService;

	/**
	 * 进入车位
	 * 
	 * @param model
	 * @param response
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/in", method = { RequestMethod.POST })
	public void inPSp(PSpInOutModel model, HttpServletResponse response)
			throws UnknownHostException, IOException {
		Json j = new Json();
		pspInOutService.PSpIn(model);
		j.setSuccess(true);
		j.setMsg("入车位成功");

		super.writeJson(response, j);
	}

	/**
	 * 离开车位
	 * 
	 * @param model
	 * @param response
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/out", method = { RequestMethod.POST })
	public void outPSp(PSpInOutModel model, HttpServletResponse response)
			throws UnknownHostException, IOException {
		Json j = new Json();
		pspInOutService.PSpOut(model);
		j.setSuccess(true);
		j.setMsg("出车位成功");

		super.writeJson(response, j);
	}
}
