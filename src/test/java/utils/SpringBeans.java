/**
 * .java
 * 类名： 
 *
 *   ver     变更日                  变更人            变更内容
 * ──────────────────────────────────
 *  V1.0    2014-8-12    lzy     初始生成
 *
 * Copyright 2014 浙江科技学院肉制品供应链跟踪与质量溯源系统项目小组
 */
package utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 通过传入Bean的名称，返回相应的对象
 * @author lzy
 * @version Ver1.0 2014-8-12
 * @date（开发日期） 2014-8-12上午11:45:09
 */
public class SpringBeans {
    
    public static String BEANSXML="spring/beans.xml";
    
    public static Object getBeans(String BeansName){
        Object obj = null;
        ApplicationContext ctx = new ClassPathXmlApplicationContext(BEANSXML);
        obj = ctx.getBean(BeansName);
        return obj;
    }
    
    public static ApplicationContext getAppContext(){
    	return new ClassPathXmlApplicationContext(BEANSXML);
    }
}
