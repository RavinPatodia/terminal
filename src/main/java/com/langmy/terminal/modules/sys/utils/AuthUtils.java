package com.langmy.terminal.modules.sys.utils;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.langmy.terminal.common.entity.Auth;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.sys.dao.AuthDao;

/**
 * 资源工具类
 * 
 * @author lxj
 *
 */
public class AuthUtils {
	private static AuthDao authDao = SpringContextHolder.getBean(AuthDao.class);
	
	/**
	 * 根据选中的菜单的Auth，拿到所有父级菜单，并放入request中给前台用
	 * @param request
	 * @param auth
	 * @param name
	 */
	public static void putAuthsInRequest(ServletRequest request,Auth auth,String name){
		if(auth == null){
			return ;
		}
		
		//根据父级菜单有4个判断啊这是个三级菜单
		String[] auths = StringUtils.split(auth.getParentIds(),",");
		if(auths.length == 4 ){
			request.setAttribute(name, auth);
			Auth authparent = getAuthParent(auth);
			request.setAttribute(name+"parent", authparent );
			Auth authgrand = getAuthParent(authparent);
			request.setAttribute(name+"grand", authgrand);
		}
		//二级
		else if(auths.length == 3){
			request.setAttribute(name+"parent", auth);
			Auth authgrand = getAuthParent(auth);
			request.setAttribute(name+"grand", authgrand);
		}
		//1级
		else if(auths.length == 2){
			request.setAttribute(name+"grand", auth);
		}
		
		//把当前选中的菜单对象存入requestScope中供前台使用,这里不是给动态菜单栏显示当前选中用的，而是用于菜单页面中取当前选中菜单对象信息
		request.setAttribute("currentMenu", auth);
		
	}
	/**
	 * 获取资源父级
	 * @param auth
	 * @return
	 */
	private static Auth getAuthParent(Auth auth){
		Auth au = authDao.findOne(auth.getParentId());
		return au;
	}
}
