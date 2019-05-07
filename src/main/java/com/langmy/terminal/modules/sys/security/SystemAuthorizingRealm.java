package com.langmy.terminal.modules.sys.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import com.langmy.hardware.common.utils.Encodes;
import com.langmy.terminal.common.config.Constant.SysCache;
import com.langmy.terminal.common.entity.Auth;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.entity.Role;
import com.langmy.terminal.common.utils.CacheUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.sys.service.SystemService;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 系统安全认证实现类
 * 
 * @author lxj
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

	private SystemService systemService;

	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		if (systemService == null) {
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}

	/**
	 * 重写了方法，该方法用于登出后清空缓存，多了一句清空sysCache缓存
	 */
	protected void doClearCache(PrincipalCollection principals) {
		super.doClearCache(principals);
		clearCachedAuthorizationInfo(principals);

		CacheUtils.removeAll("sysCache");
	}

	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清空所有关联认证
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		Operater oper = getSystemService().getOperaterByOperName(
				token.getUsername());
		if (oper != null) {
			// 盐是用户密码的前16位
			byte[] salt = Encodes.decodeHex(oper.getOperPwd().substring(0, 16));
			return new SimpleAuthenticationInfo(new Principal(oper), oper
					.getOperPwd().substring(16), ByteSource.Util.bytes(salt),
					getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		Operater oper = getSystemService().getOperaterByOperName(
				principal.getLoginName());
		if (oper != null) {
			CacheUtils.putCache(SysCache.CACHE_OPER.getValue(), oper);
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Role> roles = new ArrayList<Role>();
			roles.addAll(oper.getRoleList());
			for (Role role : roles) {
				info.addRole(role.getRoleName());
			}

			List<Auth> list = OperaterUtils.getAuthList();
			for (Auth auth : list) {
				if (StringUtils.isNotBlank(auth.getPermission())) {
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(
							auth.getPermission(), ",")) {
						info.addStringPermission(permission);
					}
				}
			}
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
				SystemService.HASH_ALGORITHM);
		matcher.setHashIterations(SystemService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer id;
		private String loginName;
		// private String name;
		private Map<String, Object> cacheMap;

		public Principal(Operater oper) {
			this.id = oper.getId();
			this.loginName = oper.getOperName();
			// this.name = user.getName();
		}

		public Integer getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		/*
		 * public String getName() { return name; }
		 */
		public Map<String, Object> getCacheMap() {
			if (cacheMap == null) {
				cacheMap = new HashMap<String, Object>();
			}
			return cacheMap;
		}

	}

}
