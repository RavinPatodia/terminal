package com.langmy.terminal.common.utils;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendEmailUtils {
/**
 * @param args
* @throws EmailException 
* @throws MalformedURLException 
 */
public static void sendEmail(String message,String sendto,String userName) throws EmailException, MalformedURLException {
//  创建一个email   

    HtmlEmail multipartemail = new HtmlEmail();   
	try {
			multipartemail.setHostName("smtp.langmy.com");   

		    multipartemail.addTo(sendto, userName);   

		    multipartemail.setFrom("info@langmy.com", "郎米科技");   

		    multipartemail.setAuthentication("info@langmy.com", "ADmin123456");   //"XXXXX"是上面163邮箱的那个用户名,即去掉"@163.com","****"是163邮箱的密码

		    multipartemail.setSubject("邮件测试");   
		    // set the html message
		    multipartemail.setCharset("gb2312");

		    // set the alternative message
		    multipartemail.setTextMsg(message);

		    //发送邮件   

		    multipartemail.send();  
	} catch (Exception e) {
		 System.out.println(e.toString());
	}
    

   System.out.println("The attachmentEmail send sucessful!!!");   
 }

}
