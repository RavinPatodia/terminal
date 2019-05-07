package com.langmy.terminal.modules.sys.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.langmy.hardware.common.utils.Encodes;
import com.langmy.terminal.common.entity.Auth;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.security.Digests;
import com.langmy.terminal.modules.sys.dao.AuthDao;
import com.langmy.terminal.modules.sys.dao.OperaterDao;
import com.langmy.terminal.modules.sys.security.SystemAuthorizingRealm;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 系统管理，安全相关实体的管理类,包括操作员、角色、菜单.
 * @author lin
 */
@Service
public class SystemService   {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private OperaterDao operaterDao;
	@Autowired
	private AuthDao authDao;

	@Autowired
	private SystemAuthorizingRealm systemRealm;
	
	//-- Operater Service --//
	
	public Operater getOperater(Integer id) {
		return operaterDao.findById(id);
	}
	
	public Operater getOperaterByOperName(String OperName) {
		return operaterDao.findByOperNameAndDelFlagFalse(OperName);
	}
	
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	//-- Auth Service --//	
	public Auth getAuth(Integer id) {
		return authDao.getById(id);
	}

	public List<Auth> findAllAuth(){
		return OperaterUtils.getAuthList();
	}

	

}
