package utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.langmy.terminal.common.utils.DateConverter;

public class BaseTest {
	
	protected ApplicationContext ctx = null;
	
	@Before
	public void setUp(){
		ctx= SpringBeans.getAppContext();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class); 
	}
	
	@ExceptionHandler
    public void exception(Exception e) {  
		e.printStackTrace();
    }  
	
}
