package com.langmy.terminal.modules.sys.security;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.servlet.AdviceFilter;

import com.langmy.terminal.common.config.Constant.SysCache;
import com.langmy.terminal.common.entity.Auth;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.utils.CacheUtils;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.sys.utils.AuthUtils;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 为了获取选中的菜单信息从而可以在不同的页面可以展开相应的菜单而做的拦截器
 * 
 * @author Lin
 *
 */
public class MenuInfoFilter extends AdviceFilter {
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		String requestServletPath = req.getServletPath();

		// menuFlag用来标志是否是第一次进入到首页，用于第一次进入时菜单样式自动选择第一级
		request.setAttribute("menuFlag", "yes");

		// 当不存在操作员时，跳过该拦截器，从而避免在登录或无操作员信息时抛错
		Operater operater = (Operater) CacheUtils.getObj(SysCache.CACHE_OPER
				.getValue());
		if (operater == null)
			return true;

		// 把选中的菜单对应的路径和auth的href进行对比，拿到匹配的且href最长的那个菜单，就是界面上选中的菜单，把该菜单和它父级菜单都放入request中
		List<Auth> lists = OperaterUtils.getAuthList();
		Auth biggestAuth = null;
		Integer length = 0;
		for (Auth auth : lists) {
			if (StringUtils.isNotNullAndEmpty(auth.getHref())
					&& (requestServletPath.indexOf(auth.getHref()) >= 0)) {
				if (auth.getHref().length() > length) {
					length = auth.getHref().length();
					biggestAuth = auth;
				}
			}
		}
		AuthUtils.putAuthsInRequest(request, biggestAuth, "selectingMenu");
		request.setAttribute("menuFlag", "no");
		return true;
	}
}
