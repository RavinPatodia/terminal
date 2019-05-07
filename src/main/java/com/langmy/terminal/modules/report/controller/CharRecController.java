package com.langmy.terminal.modules.report.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.excel.ExportExcel;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.modules.report.model.ChargeRecDataModel;
import com.langmy.terminal.modules.report.model.ChargeRecModel;
import com.langmy.terminal.modules.report.service.CharRecService;

/**
 * 操作员结账记录Controller
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/report/charRec")
public class CharRecController extends BaseController {
	@Autowired
	CharRecService charRecService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "report/charRec/charRec";
	}

	/**
	 * 获得结账记录列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	protected void list(String jsonParam, ChargeRecModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, charRecService.list(model, dataTableParam));
	}

	/**
	 * 操作员结账记录统计
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	protected String search(String jsonParam, ChargeRecModel model,
			HttpServletResponse response) throws IOException {
		return charRecService.search(model);
	}

	/**
	 * 查询月收费记录
	 * 
	 * @param year
	 * @param jsonParam
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getActualPayOfMouth/{year}")
	@ResponseBody
	protected String getActualPayOfMouth(
			@PathVariable(value = "year") String year, String jsonParam,
			HttpServletResponse response) throws IOException {
		return charRecService.getActualPayOfMouth(year);
	}

	/**
	 * 导出统计数据表格
	 * 
	 * @param model
	 * @param jsonParam
	 * @param response
	 * @param redirectAttributes
	 */
	@RequestMapping(value = "/export")
	public void export(ChargeRecModel model, String jsonParam,
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
			String fileName = "操作员结账记录统计数据"
					+ DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<ChargeRecModel> operChargeData = charRecService
					.searchForExport(model);
			new ExportExcel(startTime + "——" + endTime + "操作员结账记录统计数据",
					ChargeRecModel.class).setDataList(operChargeData)
					.write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "操作员结账记录统计数据！失败信息：" + e.getMessage());
		}
	}

	/**
	 * 导出记录表格
	 * 
	 * @param model
	 * @param jsonParam
	 * @param response
	 * @param redirectAttributes
	 */
	@RequestMapping(value = "/exportData")
	public void exportData(ChargeRecDataModel model, String jsonParam,
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
			String fileName = "操作员结账记录" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";

			List<ChargeRecDataModel> operChargeData = charRecService
					.searchForExportData(model);

			new ExportExcel(startTime + "——" + endTime + "操作员结账记录",
					ChargeRecDataModel.class).setDataList(operChargeData)
					.write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "操作员结账记录！失败信息：" + e.getMessage());
		}
	}
}
