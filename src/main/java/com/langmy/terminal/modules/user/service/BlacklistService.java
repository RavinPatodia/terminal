package com.langmy.terminal.modules.user.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Blacklist;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.log.utils.LogUtil.Option;
import com.langmy.terminal.modules.user.dao.BlacklistDao;
import com.langmy.terminal.modules.user.dao.UserDao;
import com.langmy.terminal.modules.user.model.BlacklistModel;
import com.langmy.terminal.modules.user.service.extend.BlackListExtend;
import com.langmy.terminal.modules.user.service.extend.UserExtend;

/**
 * 黑名单业务层
 * 
 * @author ZZD
 *
 */
@Service
public class BlacklistService extends BaseService<Blacklist> {

	@Autowired
	private BlacklistDao blacklistDao;
	@Autowired
	private UserDao userDao;

	public BlacklistService() {
		super.baseDao = SpringContextHolder.getBean("blacklistDao");
	}

	/**
	 * 查看详细信息 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public BlacklistModel view(int id) {
		Blacklist blackList = blacklistDao.findOne(id);
		return BlackListExtend.covertToModel(blackList);
	}

	/**
	 * 添加
	 * 
	 * @param blacklistModel
	 * @return
	 */
	public boolean add(BlacklistModel model) {
		Blacklist blacklist = new Blacklist();
		blacklist = addByModel(model);
		if (blacklist == null) {
			return false;
		}
		LogUtil.save("黑名单管理", LogUtil.Option.ADD,
				"添加黑名单：" + blacklist.toString());
		return true;
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @return
	 */
	public Blacklist addByModel(BlacklistModel model) {
		Blacklist blacklist = new Blacklist();
		blacklist = BlackListExtend.convertToEntity(model, blacklist);
		Integer idStr = blacklistDao.getMaxId();
		if (idStr == null) {
			idStr = 1;
		}
		blacklist.setBlacklistId("BlackList" + idStr);
		blacklist.setListTime(new Date());
		blacklist = blacklistDao.save(blacklist);
		return blacklist;
	}

	/**
	 * 修改
	 * 
	 * @param blacklistModel
	 * @return
	 */
	public boolean edit(BlacklistModel model) {
		Blacklist blacklist = blacklistDao.findOne(model.getId());
		blacklist = BlackListExtend.convertToEntity(model, blacklist);
		blacklist = blacklistDao.save(blacklist);
		if (blacklist == null) {
			return false;
		}
		LogUtil.save("黑名单管理", LogUtil.Option.EDIT,
				"修改黑名单：" + blacklist.toString());
		return true;
	}

	/**
	 * 解除黑名单
	 * 
	 * @param model
	 * @return
	 */
	public boolean relieve(BlacklistModel model) {
		Blacklist blacklist = blacklistDao.findOne(model.getId());
		if (blacklist == null) {
			return false;
		}
		blacklist.setRemoveWay(model.getRemoveWay());
		blacklist.setIsEffect(false);
		blacklist = blacklistDao.save(blacklist);
		if (blacklist == null) {
			return false;
		}
		LogUtil.save("黑名单管理", LogUtil.Option.EDIT,
				"解除黑名单：" + blacklist.toString());
		return true;
	}

	/**
	 * 根据name查询已拉黑客户
	 * 
	 * @param name
	 * @return
	 */
	public List<BaseModel> getBlackUserName(String name) {
		List<User> users = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(name)) {
			users = userDao.getBlackNames("%" + name + "%");
		} else {
			users = userDao.getBlackNames();
		}
		return UserExtend.convertToSimpleModels(users);

	}

	/**
	 * 根据黑名单编号balckIds查询黑名单
	 * 
	 * @param balckIds
	 *            黑名单编号
	 * @return
	 */
	public List<BaseModel> getBlackByBalckIds(String balckIds) {
		List<Blacklist> balck = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(balckIds)) {
			balck = blacklistDao.findByBlacklistIdLike("%" + balckIds + "%");
		} else {
			balck = blacklistDao.findAll();
		}
		return getModelsByBeans(balck);
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Blacklist> blacklists) {
		List<BlacklistModel> blacklistModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(blacklists)) {
			return Lists.newArrayList(blacklistModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "user.id");
		map.put("targetPro1", "userPK");
		map.put("sourcePro2", "user.name");
		map.put("targetPro2", "name");
		try {
			blacklistModels = BeanUtils.copyListProperties(blacklists,
					BlacklistModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("黑名单管理模块-实体类与Model类数组转化失败", e);
			return null;
		}
		return Lists.newArrayList(blacklistModels);
	}

	@Override
	protected Specification<Blacklist> buildSpecification(BaseModel baseModel) {
		BlacklistModel blackListModel = (BlacklistModel) baseModel;
		Integer userPK = blackListModel.getUserPK();
		String blackListId = blackListModel.getBlacklistId();
		Integer isEffect = blackListModel.getIsEffect();
		return new Specification<Blacklist>() {

			@Override
			public Predicate toPredicate(Root<Blacklist> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> list = new ArrayList<Predicate>();
				if (userPK != null) {
					list.add(cb.equal(root.get("user").get("id"), userPK));
				}
				if (StringUtils.isNotNullAndEmpty(blackListId)) {
					list.add(cb.like(root.get("blacklistId"), "%" + blackListId
							+ "%"));
				}
				if (isEffect != null && isEffect != -1) {
					if (isEffect == 0) {
						list.add(cb.equal(root.get("isEffect"), false));
					} else if (isEffect == 1) {
						list.add(cb.equal(root.get("isEffect"), true));
					}
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};
	}

	/**
	 * 黑名单截止时间到期
	 */
	@Scheduled(cron = "0 0 2 * * *")
	public void recoverBlack() {
		List<Blacklist> blacks = blacklistDao.findByIsEffectTrue();
		Date now = new Date();
		for (Blacklist bl : blacks) {
			Date endTime = bl.getEndTime();
			if (now.after(endTime)) {
				bl.setIsEffect(false);
				bl.setRemoveWay("截止时间到期");
				if (blacklistDao.save(bl) != null) {
					LogUtil.saveAutoOper("黑名单管理", Option.BLACKCOVER,
							bl.getBlacklistId() + "——黑名单到期");
				}
			}
		}
	}
}
