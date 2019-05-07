package com.langmy.terminal.modules.sys.security;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import com.langmy.terminal.common.config.Constant.RoleType;
import com.langmy.terminal.common.config.Constant.SessionKey;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.modules.psp.utils.PSpUtils;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 表单验证（包含验证码）过滤类
 * 
 * @author lxj
 */
@Service
public class FormAuthenticationFilter extends
		org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	protected UsernamePasswordToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		String captcha = getCaptcha(request);
		return new UsernamePasswordToken(username, password.toCharArray(),
				rememberMe, host, captcha);
	}

	// 认证
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		UsernamePasswordToken token = createToken(request, response);
		Operater operater = OperaterUtils
				.getOperByOperName(token.getUsername());
		try {
			if (operater != null
					&& operater.getType() != RoleType.TERMINAL_ROLE.getValue()) {
				throw new AuthenticationException("对不起，您不是终端操作员！");
			}
		} catch (AuthenticationException ae) {
			return onLoginFailure(token, ae, request, response);
		}
		try {
			Subject subject = getSubject(request, response);
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException ae) {
			return onLoginFailure(token, ae, request, response);
		}
	}

	// 重写登陆成功方法
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		Date now = new Date();
		Session session = subject.getSession();
		Object obj = session.getAttribute(SessionKey.LOGINTIME.getValue());
		if (obj == null) {
			session.setAttribute(SessionKey.LOGINTIME.getValue(), now);
			int carnum = PSpUtils.getPspNumWherePpsCarIsNotNull();
			session.setAttribute(SessionKey.CARNUM.getValue(), carnum);
		}
		issueSuccessRedirect(request, response);
		return false;
	}

	// 认证
	public boolean changeLogin(UsernamePasswordToken token,
			ServletRequest request, ServletResponse response) throws Exception {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			return onChangeLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException ae) {
			return onLoginFailure(token, ae, request, response);
		}
	}

	protected boolean onChangeLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		Date now = new Date();
		Session session = subject.getSession();
		Object obj = session.getAttribute(SessionKey.LOGINTIME.getValue());
		if (obj == null) {
			session.setAttribute(SessionKey.LOGINTIME.getValue(), now);
			int carnum = PSpUtils.getPspNumWherePpsCarIsNotNull();
			session.setAttribute(SessionKey.CARNUM.getValue(), carnum);
		}
		return true;
	}

	// 重写登录失败方法
	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException authExp) {
		String expMsg = "";
		if (authExp != null) {
			if (authExp instanceof UnknownAccountException
					|| authExp instanceof IncorrectCredentialsException) {
				expMsg = "用户名或密码错误！";
			} else {
				expMsg = authExp.getMessage();
			}
		}
		request.setAttribute("expMsg", expMsg);
	}

}
