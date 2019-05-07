package com.langmy.terminal.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.dao.ParkEnvirDao;
import com.langmy.terminal.common.entity.ParkingEnvir;
import com.langmy.terminal.common.utils.SpringContextHolder;

/**
 * @author lzy
 * 程序启动执行的方法
 */
@Component
public class InitParkListener implements ServletContextListener{
	
	private ParkEnvirDao parkEnvirDao;
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		dateConverterRegister();
		globalConfigInitialized();
	}

	private void globalConfigInitialized() {
		parkEnvirDao = 	SpringContextHolder.getBean("parkEnvirDao");
		ParkingEnvir parkEnvir = parkEnvirDao.findOne(1);
		Global.setConfig(parkEnvir);
		LOG.info("项目配置初始化成功");
	}

	/**
	 * BeanUtils.copyProperty中若Date为空则会出现异常。这里注册自己的日期转化类
	 */
	private void dateConverterRegister() {
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
