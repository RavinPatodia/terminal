package com.langmy.terminal.modules.user.service.extend;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.langmy.terminal.common.entity.Blacklist;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.user.dao.UserDao;
import com.langmy.terminal.modules.user.model.BlacklistModel;

/**
 * 客户组Model和实体转化类
 * 
 * @author zhaozhedan
 *
 */
public class BlackListExtend {

	protected static Logger logger = LoggerFactory
			.getLogger(BlackListExtend.class);
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

	public static BlacklistModel covertToModel(Blacklist blackList) {
		BlacklistModel model = new BlacklistModel();
		try {
			BeanUtils.copyProperties(blackList, model);
			model.setName(blackList.getUser().getName());
			model.setUserPK(blackList.getUser().getId());
			model.setBlacklistId(blackList.getBlacklistId());
			model.setListTime(blackList.getListTime());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("黑名单管理模块-Model类与实体类转化失败");
			return null;
		}
		return model;
	}

	public static Blacklist convertToEntity(BlacklistModel model,
			Blacklist blacklist) {
		Date listTime = blacklist.getListTime();
		try {
			BeanUtils.copyProperties(model, blacklist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("黑名单管理模块-Model类与实体类转化失败", e);
			return null;
		}
		if (model.getEndTime().after(DateUtils.getDateEnd(new Date()))) {
			blacklist.setIsEffect(true);
		} else {
			blacklist.setIsEffect(false);
		}
		User user = userDao.findOne(model.getUserPK());
		blacklist.setUser(user);
		blacklist.setListTime(listTime);
		return blacklist;
	}
}
