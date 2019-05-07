package com.langmy.terminal.modules.report.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.excel.ExportExcel;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.modules.report.model.RechargeRecModel;
import com.langmy.terminal.modules.report.service.RecharRecService;

/**
 * 充值记录Controller
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/report/reCharRec")
public class ReCharRecController extends BaseController {
	@Autowired
	RecharRecService recharRecService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "report/reCharRec/reCharRec";
	}

	/**
	 * 获得充值记录列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	protected void list(String jsonParam, RechargeRecModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, recharRecService.list(model, dataTableParam));
	}

	/**
	 * 导出充值记录
	 * 
	 * @param model
	 * @param jsonParam
	 * @param response
	 * @param redirectAttributes
	 */
	@RequestMapping(value = "/export")
	public void export(RechargeRecModel model, String jsonParam,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = "";
			String endTime = "";
			if (model.getStartTime() != null) {
				startTime = sdf.format(model.getStartTime());
			}
			if (model.getEndTime() != null) {
				endTime = sdf.format(model.getEndTime());
			}
			String fileName = "充值记录统计数据" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			List<RechargeRecModel> operChargeData = recharRecService
					.exportData(model);
			new ExportExcel(startTime + "——" + endTime + "充值记录统计数据",
					RechargeRecModel.class).setDataList(operChargeData)
					.write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "充值记录统计数据！失败信息：" + e.getMessage());
		}
	}

}
