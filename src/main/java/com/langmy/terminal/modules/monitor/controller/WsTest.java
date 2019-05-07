package com.langmy.terminal.modules.monitor.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.modules.monitor.model.PlateRecognizeModel;

@Controller
@RequestMapping(value = "/wsTest")
public class WsTest extends BaseController{
 
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public  String hello(){
        return "hello";
 
    }
    
    @RequestMapping(value="/{name}",method = RequestMethod.GET)
    @ResponseBody
    public  String helloName(@PathVariable("name")String name){
        return name;
    }
    
    /**
	 * 摄像头抓拍到车牌后将信息通过这个WebService接口传过来
	 * 
	 * @param model
	 * @param response
	 * @throws ServiceException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/plateRecognize", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public boolean plateRecognize(@RequestBody PlateRecognizeModel model, HttpServletResponse response)
			throws ServiceException, UnknownHostException, IOException {
		System.out.println(JSONObject.toJSONString(model));
		return true;
	}
	
}
