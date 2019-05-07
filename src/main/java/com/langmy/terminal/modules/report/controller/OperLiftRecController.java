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
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.report.model.OperLiftRecDataModel;
import com.langmy.terminal.modules.report.model.OperLiftRecModel;
import com.langmy.terminal.modules.report.service.OperLiftRecService;

/**
 * 手动抬杆记录Controller
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/report/operLiftRec")
public class OperLiftRecController extends BaseController {
	@Autowired
	OperLiftRecService operLiftRecService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "report/operLiftRec/operLiftRec";
	}

	/**
	 * 获得抬杆记录列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, OperLiftRecModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response,
				operLiftRecService.list(model, dataTableParam));
	}

	/**
	 * 操作员抬杆记录统计
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
	protected String search(String jsonParam, OperLiftRecModel model,
			HttpServletResponse response) throws IOException {
		return operLiftRecService.search(model);
	}

	/**
	 * 删除记录
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void delete(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (operLiftRecService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("删除抬杆记录成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除抬杆记录失败");
		}
		super.writeJson(response, j);
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
	public void export(OperLiftRecModel model, String jsonParam,
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
			String fileName = "操作员抬杆记录统计数据"
					+ DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<OperLiftRecModel> operLiftData = operLiftRecService
					.searchForExport(model);
			new ExportExcel(startTime + "——" + endTime + "操作员抬杆记录统计数据",
					OperLiftRecModel.class).setDataList(operLiftData)
					.write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "操作员抬杆记录统计数据！失败信息：" + e.getMessage());
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
	public void exportData(OperLiftRecDataModel model, String jsonParam,
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
			String fileName = "操作员抬杆记录" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";

			List<OperLiftRecDataModel> operLiftData = operLiftRecService
					.searchForExportData(model);

			new ExportExcel(startTime + "——" + endTime + "操作员抬杆记录",
					OperLiftRecDataModel.class).setDataList(operLiftData)
					.write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "操作员抬杆记录！失败信息：" + e.getMessage());
		}
	}
}
