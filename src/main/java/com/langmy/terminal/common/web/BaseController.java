package com.langmy.terminal.common.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//import javax.validation.ConstraintViolationException;
//import javax.validation.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.beanvalidator.BeanValidators;

//import com.langmy.park.common.beanvalidator.BeanValidators;

/**
 * 控制器支持类
 * @author lzy
 */
public class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;
	
	
	/**
	 * 向Json对象添加验证错误信息
	 * @param messages 消息
	 */
	protected void addMessage(Json json, String... messages) {
		Map<String,String> msgMap = Maps.newHashMap();
		//StringBuilder sb = new StringBuilder();
		for (String message : messages){
			String[] msg =message.split(":");
			msgMap.put(msg[0], msg[1]);
			//sb.append(message).append(messages.length>1?"<br/>":"");
		}
		json.setValidateMsg(msgMap);
		//model.addAttribute("message", sb.toString());
	}

	/**
	 * 添加Flash消息
     * @param messages 消息
    **/	 
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Json json, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			json.setValidateResult(false); 
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			//list.add(0, "数据验证失败：");
			addMessage(json, list.toArray(new String[]{}));
			return false;
		}
		json.setValidateResult(true);//验证成果无错误
		return true;
	}
	
	
	/**
	 * 转化JsonArray数组成为HashMap
	 * @param jsonParam
	 * @return
	 */
	protected Map<String,Object> covertJsonStringToHashMap(String jsonParam){
		JSONArray jsonArray = JSONArray.parseArray(jsonParam);
		Map<String,Object> map = Maps.newHashMap();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			map.put(jsonObj.getString("name"), jsonObj.get("value"));
		}
		return map;
	}
	
	/**
	 * 将前台DataTable插件传回的参数转化成Service能使用的Bean
	 * @param jsonParam 前台DataTable插件传回的参数,JSonArray形式
	 * @return
	 */
	protected DataTableParameter getDataTableParameterByJsonParam(String jsonParam){
		Map<String,Object> map = covertJsonStringToHashMap(jsonParam);
		int sEcho = (int) map.get("sEcho"); 
		int iDisplayStart = (int) map.get("iDisplayStart");
		int iDisplayLength = (int) map.get("iDisplayLength");
		int iColumns = (int)map.get("iColumns");
		int iSortingCols = (int)map.get("iSortingCols");
		
		List<String> mDataProps = Lists.newArrayList();
		List<Boolean> bSortables = Lists.newArrayList();
		for(int i=0;i<iColumns;i++){
			String dataProp = (String) map.get("mDataProp_"+i);
			Boolean sortable = (Boolean) map.get("bSortable_"+i);
			mDataProps.add(dataProp);
			bSortables.add(sortable);
		}
		
		List<Integer> iSortCols = Lists.newArrayList();
		List<String> sSortDirs = Lists.newArrayList();
		List<String> iSortColsName = Lists.newArrayList();
		for(int i=0;i<iSortingCols;i++){
			Integer sortCol = (Integer) map.get("iSortCol_"+i);
			String sortColName = mDataProps.get(sortCol);
			String sortDir = (String) map.get("sSortDir_"+i);
			iSortCols.add(sortCol);
			sSortDirs.add(sortDir);
			iSortColsName.add(sortColName);
		}
		
		return new DataTableParameter(sEcho, iDisplayStart, iDisplayLength, iColumns, mDataProps, bSortables, iSortingCols, iSortCols, sSortDirs,iSortColsName);
	}
	
	@ExceptionHandler
    public String exception(HttpServletRequest request, Exception e) {  
          
        request.setAttribute("exceptionMessage", e.getMessage());  
        logger.error(e.getMessage());  
        // 根据不同错误转向不同页面  
        if(e instanceof SQLException) 
            return "testerror";
        else if(e instanceof NoSuchFieldException){
        	return "error";
        }
        else
            return "error";  
    }
	
	/**
	 * 将对象写成json传给前台
	 * @param response 相应页面的response
	 * @param obj 转化成json的对象
	 */
	protected void writeJson(HttpServletResponse response,Object obj){
		try {
			//System.out.println(JSONObject.toJSONString(obj));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(JSONObject.toJSONString(obj));
			logger.debug(JSONObject.toJSONString(obj));
		} catch (IOException e) {
			logger.error("Response获取页面的Writer失败",e);
		}
	}
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}

}
