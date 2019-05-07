package com.langmy.terminal.modules.report.controller;

import java.io.IOException;
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
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.modules.report.dao.CharRecDao;
import com.langmy.terminal.modules.report.service.CharRecService;

/**
 * 月收费统计Controller
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/report/charRecOfMonth")
public class CharRecOfMonthController extends BaseController {
	@Autowired
	CharRecService charRecService;
	@Autowired
	CharRecDao charRecDao;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "report/charRec/charRecOfMonth";
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
		System.out.println("gfsdf");
		return charRecService.getActualPayOfMouth(year);
	}

	@RequestMapping(value = "/getMaxYear")
	@ResponseBody
	protected String getMaxYear(String jsonParam, HttpServletResponse response)
			throws IOException {
		return charRecDao.getMaxYear();
	}

}
