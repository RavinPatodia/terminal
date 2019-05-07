package com.langmy.terminal.modules.test;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.web.BaseController;

@Controller
@RequestMapping(value = "/test/test")
public class TestController extends BaseController {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage() {
		Map<String, String> url = Maps.newHashMap();
		
		return new ModelAndView("test/test", url);
	}
}
