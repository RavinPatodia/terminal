package com.langmy.terminal.module.ruleEngine;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import utils.BaseTest;

import com.langmy.terminal.common.config.Constant;
import com.langmy.terminal.modules.ruleEngine.fact.AdmissionRecModel;
import com.langmy.terminal.modules.ruleEngine.fact.UserModel;
import com.langmy.terminal.modules.ruleEngine.service.RuleEngineService;

public class RuleTest extends BaseTest{
	
	@Test
	public  void testRule() throws ParseException{
		String intime="2015-08-15 5:35:00";
		String outtime="2015-08-16 10:25:00";
		String carType="0";
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*		
		Date tmpTime = sdf.parse(intime);
		System.out.println(Double.valueOf(String.format("%.2f",0.777)));
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		System.out.println(Double.valueOf((df.format(1.1))));
	
		
		

		System.out.println(tmpTime.getHours());
		System.out.println(tmpTime.getMinutes());
		Calendar calendar=Calendar.getInstance();
		calendar.get(Calendar.MONTH);
		calendar.get(Calendar.DATE);
		calendar.getTime();*/
		//calendar.set(Calendar.MINUTE, value);

		
//		tmpTime.before(when)
//		tmpTime.getDay();
//		Calendar calendar=Calendar.getInstance();
//		calendar.setTime(tmpTime);
//		System.out.println(calendar);
//		calendar.add(Calendar.MINUTE, -30);
//		System.out.println(calendar);
//		for (int i = 0; i < 100; i++) {
//			calendar.setTime(tmpTime);
//			calendar.add(Calendar.MINUTE, 30);
//			tmpTime = calendar.getTime();
//			System.out.println(tmpTime);
//		}
		

		
	
//	

		AdmissionRecModel model = getAdmissionRec(intime,outtime,carType);
		UserModel userModel = getUserInfo();
		
		RuleEngineService service = ctx.getBean(RuleEngineService.class);
		service.executeRules(model, userModel);
	}
	
	private static UserModel getUserInfo() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date birthDay = sdf.parse("1993-1-3 00:00:00");
		
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
