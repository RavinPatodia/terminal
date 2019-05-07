package com.langmy.terminal.modules.testDrools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.langmy.terminal.common.config.Constant;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.modules.ruleEngine.fact.AdmissionRecModel;
import com.langmy.terminal.modules.ruleEngine.fact.UserModel;
import com.langmy.terminal.modules.ruleEngine.utils.RuleUtils;

@Controller
@RequestMapping(value = "/testDrools")
public class TestDrools extends BaseController {
	

	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		
		return "testDrools/testDrools";
	}
	
	/**
	 * 添加功能
	 * 
	 * @param model
	 * @param response
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/submitData", method = { RequestMethod.GET })
	public void add(String carInTime,String carOutTime,String carType,String birthday,HttpServletResponse response) throws ParseException {
		Json j = new Json();
	//	System.out.println(carInTime+"--"+carOutTime+"----"+carType);
		testRule(carInTime, carOutTime, birthday,carType);
			j.setSuccess(true);
			j.setMsg("保存成功");
		System.out.println(birthday);
		super.writeJson(response, j);
	//	System.out.println(carInTime+"--"+carOutTime+"----"+carType);
	}
	
	public  void testRule(String intime,String outtime,String birthday,String carType) throws ParseException{
//		String intime="2005-1-1 00:00:00";
//		String outtime="2005-1-1 10:00:00";
//		String carType="0";
		UserModel userModel = getUserInfo(birthday);
		AdmissionRecModel model = getAdmissionRec(intime,outtime,carType);
		
		RuleUtils.executeRules(model, userModel);
	}
	
	private static UserModel getUserInfo(String birthday) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDay = sdf.parse(birthday.trim());
		
		UserModel userInfo = new UserModel();
		userInfo.setBirthDay(birthDay);
		userInfo.setUserGroupId(1);
		return userInfo;
	}
	
	public static AdmissionRecModel getAdmissionRec(String intime,String outtime,String carType) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = sdf.parse(intime);
		Date endTime = sdf.parse(outtime);
		
		AdmissionRecModel admissionRec = new AdmissionRecModel(startTime, endTime);
		if(carType.equals("0")){
			admissionRec.setCarType(Constant.CarType.Car.getValue());
		} else if(carType.equals("1")){
			admissionRec.setCarType(Constant.CarType.TRUCK.getValue());
		}
		
		return admissionRec;
	}
	
	
}
