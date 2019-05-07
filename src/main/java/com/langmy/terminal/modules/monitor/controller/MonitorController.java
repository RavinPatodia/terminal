package com.langmy.terminal.modules.monitor.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.langmy.terminal.common.config.Constant;
import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.entity.ParkingEnvir;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.cometd.EchoService;
import com.langmy.terminal.modules.monitor.model.EnterModel;
import com.langmy.terminal.modules.monitor.model.ExitModel;
import com.langmy.terminal.modules.monitor.model.PlateRecognizeModel;
import com.langmy.terminal.modules.monitor.service.EnterService;
import com.langmy.terminal.modules.monitor.service.ExitService;

/**
 * 出场
 * 
 * @author lzy
 *
 */
@Controller
@RequestMapping(value = "/monitor/monitor")
public class MonitorController extends BaseController{
	
	@Autowired
	private EnterService enterService;
	@Autowired
	private EchoService echoService;
	@Autowired
	private ExitService exitService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "monitor/monitor";
	}
	
	/**
	 * 入场
	 * 
	 * @param model
	 * @param response
	 * @throws ServiceException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getData", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void enter(EnterModel model, HttpServletResponse response)
			throws ServiceException, UnknownHostException, IOException {
		model.setInPicUrl("/terminal/images/admission1.PNG");
		model.setInRecoWay(0);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(model));
		EnterModel emodel = enterService.enter(model);
		if (emodel != null) 
			echoService.enterProcess(emodel);// 反向推送model信息
		else
			logger.error("入场流程失败");
	}
	
	
	/**
	 * 出场时首先获取数据，有车出去时首先触发这个方法
	 * 
	 * @param carId
	 * @param model
	 * @param response
	 * @throws ServiceException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getData/{carId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void getData(@PathVariable(value = "carId") Integer carId,
			ExitModel model, HttpServletResponse response)
			throws ServiceException, UnknownHostException, IOException {
		// carId不等与-1代表这个是智能匹配传来的车号
		if (carId != -1) {
			model = exitService.getCarByCarId(model, carId);
		}

		// TODO model的属性应是获取的
		model.setOutRecoWay(0);
		model.setOutPicUrl("/park/images/admission2.PNG");

		ExitModel emodel = exitService.matchingAdmission(model);

		// 若完整执行了matchingAdmission并返回了对象
		if (emodel.isSuccessflag()) {
			// 判断是否停车场是否会进行收费
			ParkingEnvir parkingEnvir = Global.getConfig();
			if (!parkingEnvir.isIsCharge()) {
				if (exitService.exit(emodel)) {
					emodel.setMsgFlag(true);
					emodel.setMsg("离场成功");
					echoService.exitProcess(emodel);// 推送model信息到前台
				}
				return;
			}
			// 如果是长期用户或者是已经付款完成的用户，则直接执行离场方法放行，否则将数据返回前台交由前台控制。
			if (emodel.getUserType() == Constant.UserGroupType.LONGTREM
					.getValue() || emodel.isPayFlag()) {
				if (exitService.exit(emodel)) {
					emodel.setMsgFlag(true);
					emodel.setMsg("离场成功");
					echoService.exitProcess(emodel);// 推送model信息到前台
				} else {
					emodel.setMsgFlag(false);
					emodel.setMsg("离场失败");
					echoService.exitProcess(emodel);
				}
			} else {
				emodel.setMsg("获取成功");
				emodel.setMsgFlag(true);
				echoService.exitProcess(emodel);
			}
		}
		// 未完整执行，意味着查不到对应的入场记录
		else {
			emodel.setMsgFlag(true);
			emodel.setMsg("没有对应入场记录,离场失败");
			echoService.exitProcess(emodel);
		}
	}

	/**
	 * 收费完成
	 * 
	 * @param model
	 * @param response
	 * @throws ServiceException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/complete", method = { RequestMethod.POST })
	public void complete(ExitModel model, HttpServletResponse response)
			throws ServiceException, UnknownHostException, IOException {
		Json j = new Json();
		if (exitService.exit(model)) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("离场完成");
		} else {
			j.setSuccess(false);
			j.setMsg("离场失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 黑名单处理
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/blacklist", method = { RequestMethod.POST })
	public void blacklist(ExitModel model, HttpServletResponse response) {
		Json j = new Json();
		j.setSuccess(true);
		j.setObject(model);
		j.setMsg("黑名单处理完成");
		super.writeJson(response, j);
	}

	/**
	 * 智能匹配
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/matching", method = RequestMethod.POST)
	protected void matching(String jsonParam, ExitModel model,
			HttpServletResponse response) {
		super.writeJson(response, exitService.matching(model));
	}
}
