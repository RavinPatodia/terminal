package com.langmy.terminal.modules.sys.utils;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import com.langmy.terminal.common.config.Constant.SysCache;
import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.entity.Auth;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.utils.CacheUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.sys.dao.AuthDao;
import com.langmy.terminal.modules.sys.dao.OperaterDao;
import com.langmy.terminal.modules.sys.security.SystemAuthorizingRealm.Principal;

/**
 * 用户工具类
 * 
 * @author Lin
 */
public class OperaterUtils {

	private static OperaterDao operaterDao = SpringContextHolder
			.getBean(OperaterDao.class);
	private static AuthDao authDao = SpringContextHolder.getBean(AuthDao.class);

	/**
	 * 获取当前登录的用户
	 * 
	 * @return 当前登陆的用户
	 */
	public static Operater getOperater() {
		// 缓存中是否存有Operater对象
		Operater operater = (Operater) CacheUtils.getObj(SysCache.CACHE_OPER
				.getValue());
		if (operater == null) {
			try {
				Subject subject = SecurityUtils.getSubject();
				Principal principal = (Principal) subject.getPrincipal();
				if (principal != null) {
					operater = operaterDao.findById(principal.getId());
					CacheUtils.putCache(SysCache.CACHE_OPER.getValue(),
							operater);
				}
			} catch (UnavailableSecurityManagerException e) {

			} catch (InvalidSessionException e) {

			}
		}
		if (operater == null) {
			operater = new Operater();
			try {
				SecurityUtils.getSubject().logout();
			} catch (UnavailableSecurityManagerException e) {

			} catch (InvalidSessionException e) {

			}
		}
		return operater;
	}

	/**
	 * 
	 * @param operName
	 * @return
	 */
	public static Operater getOperByOperName(String operName) {
		return operaterDao.findByOperNameAndDelFlagFalse(operName);
	}

	/**
	 * 通过Id获取操作员
	 * 
	 * @param id
	 * @return
	 */
	public static Operater getOperaterById(Integer id) {
		if (id != null) {
			return operaterDao.findById(id);
		} else {
			return null;
		}
	}

	/**
	 * 获取资源列表
	 * 
	 * @return
	 */
	public static List<Auth> getAuthList() {
		Operater operater = (Operater) CacheUtils.getObj(SysCache.CACHE_OPER
				.getValue());
		if (operater == null)
			return null;

		@SuppressWarnings("unchecked")
		List<Auth> authList = (List<Auth>) CacheUtils.get(
				SysCache.SYS_CACHE.getValue(),
				SysCache.CACHE_AUTH_LIST.getValue());
		if (authList == null) {
			if (operater.isAdmin()) {
				authList = authDao.findTerminalAuthList();
			} else {
				authList = authDao.findByOperId(operater.getId());
			}
			CacheUtils.put(SysCache.SYS_CACHE.getValue(),
					SysCache.CACHE_AUTH_LIST.getValue(), authList);
		}
		return authList;
	}

	/**
	 * 是否拥有子节点
	 * 
	 * @param menuId
	 * @return
	 */
	public static Boolean haveChildAuth(Integer menuId) {
		List<Auth> childAuthList = authDao.findChildByMenuId(menuId);
		if (childAuthList.isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * 获取当前操作员的头像对应的ftp图片路径
	 * 
	 * @return
	 */
	public static String getOperFtpImgUrl() {
		Operater oper = getOperater();
		oper = operaterDao.findById(oper.getId());
		if (oper != null) {
			if (!StringUtils.isNotNullAndEmpty(oper.getPicUrl())) {
				return null;
			}
			String url = Global.getConfig("ftpUrl");
			String username = Global.getConfig("ftpUserName");
			String password = Global.getConfig("ftpPwd");
			String headFtpUrl = "ftp://" + username + ":" + password + "@"
					+ url;
			String ftpUrl = headFtpUrl + oper.getPicUrl();
			return ftpUrl;
		}
		return "";
	}

	/**
	 * 获取当前操作员名称
	 * 
	 * @return
	 */
	public static String getOperName() {
		Operater oper = getOperater();
		oper = operaterDao.findById(oper.getId());
		if (oper != null) {
			return oper.getName();
		}
		return "";
	}

	/**
	 * 从缓存中获取当前登录者的客户端的ip
	 * 
	 * @return
	 */
	public static String getClientIp() {
		String ip = CacheUtils.get(SysCache.SYS_CACHE.getValue(), "ip")
				.toString();
		return ip;
	}

}
