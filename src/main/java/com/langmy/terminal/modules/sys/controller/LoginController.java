package com.langmy.terminal.modules.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.langmy.terminal.common.config.Constant.SysCache;
import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.utils.CacheUtils;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.modules.sys.service.SystemService;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 登录Controller
 * 
 * @author lxj
 */
@Controller
public class LoginController extends BaseController {
	public static final String CACHE_OPER = "oper";

	/**
	 * 管理登录
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Operater operater = OperaterUtils.getOperater();
		// 如果已经登录，则跳转到管理首页
		if (operater.getId() != null) {
			return "redirect:" + Global.getAdminPath();
		}
		return "login";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Operater operater = OperaterUtils.getOperater();
		String expMsg = (String) request.getAttribute("expMsg");
		if (!"".equals(expMsg) && operater.getId() == null) {
			model.addAttribute("errorMsg", expMsg);
			return "login";
		}
		return "redirect:" + Global.getAdminPath();
	}

	/**
	 * 锁屏
	 */
	@RequestMapping(value = "/admin/lock", method = RequestMethod.GET)
	public String lock(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			Session session = subject.getSession(); // session 会销毁
			session.stop();
		}
		return "lock";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "/admin/lockLogin", method = RequestMethod.POST)
	public String lockLogin(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String usernmae,
			@RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Operater operater = OperaterUtils.getOperByOperName(usernmae);
		// 如果已经登录，则跳转到管理首页
		if (operater.getId() != null) {
			if (operater.getOperPwd().equals(
					SystemService.validatePassword(password,
							operater.getOperPwd()))) {
				return "redirect:" + Global.getAdminPath();
			} else {
				model.addAttribute("errorMsg", "密码错误!");
				return "lock";
			}
		}
		return "lock";
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresUser
	@RequestMapping(value = "/admin")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Operater operater = OperaterUtils.getOperater();
		CacheUtils.put(SysCache.SYS_CACHE.getValue(), "ip", getIpAddr(request));
		// 未登录，则跳转到登录页
		if (operater.getId() == null) {
			return "redirect:" + Global.getAdminPath() + "/login";
		}
		return "index";
	}

	/**
	 * 获取登陆者的IP地址
	 * 
	 * @param request
	 * @return
	 */
	private static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
