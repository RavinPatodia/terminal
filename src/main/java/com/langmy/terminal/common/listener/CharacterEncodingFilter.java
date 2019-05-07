package com.langmy.terminal.common.listener;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
  
/** 
 * 项目启动时，配置请求编码的过滤器 
 */  
@WebFilter(filterName = "characterEncodingFilter"  
            , urlPatterns = { "/*" }  
            ,asyncSupported=true  
            ,initParams={  
                @WebInitParam(name="encoding" ,value="UTF-8"),  
                @WebInitParam(name="forceEncoding",value="true")  
            })  
public class CharacterEncodingFilter extends org.springframework.web.filter.CharacterEncodingFilter {  
  
}  