package com.langmy.terminal.modules.user.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.config.Constant.UserGroupType;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.entity.UGChargeRule;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.DelValidate;
import com.langmy.terminal.modules.charge.utils.ChargeRuleUtils;
import com.langmy.terminal.modules.charge.utils.DctRuleGroupUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.user.dao.UGChargeRuleDao;
import com.langmy.terminal.modules.user.dao.UGroupDao;
import com.langmy.terminal.modules.user.model.UGroupModel;
import com.langmy.terminal.modules.user.service.extend.UgroupExtend;

/**
 * 客户组业务层
 * 
 * @author ZZD
 *
 */
@Service
public class UGroupService extends BaseService<UGroup> {
	@Autowired
	private UGroupDao uGroupDao;
	@Autowired
	private UGChargeRuleDao ugChargeRuleDao;

	public UGroupService() {
		super.baseDao = SpringContextHolder.getBean(UGroupDao.class);
	}

	/**
	 * 查看详细信息 根据id查询
	 * 
	 * @param id
	 *            用户组id
	 * @return
	 * @throws IOException
	 * @throws IntrospectionException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	public UGroupModel view(int id) throws InstantiationException,
			IllegalArgumentException, ClassNotFoundException,
			IntrospectionException, IOException {
		UGroup group = uGroupDao.findOne(id);
		return UgroupExtend.covertToModel(group);
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean add(UGroupModel model) throws ServiceException {

		UGroup ugroup = addByModel(model);
		if (ugroup == null)
			throw new ServiceException("客户组管理模块-添加客户组-保存客户组失败");
		if (!bindAuths(model, ugroup)) {
			throw new ServiceException("客户组管理模块-添加客户组-客户组绑定停车权限失败");
		}
		if (!bindChargeRule(model, ugroup)) {
			throw new ServiceException("客户组管理模块-添加客户组-客户组绑定规则失败");
		}
		LogUtil.save("客户组管理", LogUtil.Option.ADD, "添加客户组：" + ugroup.toString());
		return true;
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean edit(UGroupModel model) throws ServiceException {
		UGroup ugroup = editByModel(model);
		if (ugroup == null)
			throw new ServiceException("客户组管理模块-修改客户组-保存客户组失败");
		// 重新绑定停车权限
		ugroup = uGroupDao.save(ugroup);
		if (ugroup == null) {
			throw new ServiceException("客户组管理模块-添加客户组-客户组清除停车权限失败");
		}
		if (!bindAuths(model, ugroup)) {
			throw new ServiceException("客户组管理模块-添加客户组-客户组绑定停车权限失败");
		}
		// 重新绑定规则
		Set<UGChargeRule> ugChargeRules = ugroup.getUGChargeRules();
		for (UGChargeRule ugc : ugChargeRules) {
			ugChargeRuleDao.delete(ugc);
		}
		if (!bindChargeRule(model, ugroup)) {
			throw new ServiceException("客户组管理模块-修改客户组-客户组绑定规则失败");
		}
		LogUtil.save("客户组管理", LogUtil.Option.EDIT, "修改客户组：" + ugroup.toString());
		return true;
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @return
	 */
	private UGroup addByModel(UGroupModel model) {
		UGroup ugroup = new UGroup();
		DctRuleGroup dctRuleGroup = new DctRuleGroup();
		try {
			BeanUtils.copyProperties(model, ugroup);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("客户组管理模块-Model类与实体类转化失败", e);
			return null;
		}
		dctRuleGroup = DctRuleGroupUtils.findDctRuleGroupById(model
				.getDctRuleGroupPK());
		ugroup.setDctRuleGroup(dctRuleGroup);
		int num = uGroupDao.getMaxId() + 1;
		ugroup.setUgroupId("ugroup" + num);
		ugroup.setCreateTime(new Date());
		ugroup.setLastEnable(new Date());
		ugroup = uGroupDao.save(ugroup);
		return ugroup;
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @return
	 */
	private UGroup editByModel(UGroupModel model) {
		UGroup ugroup = uGroupDao.findOne(model.getId());
		DctRuleGroup dctRuleGroup = new DctRuleGroup();
		try {
			BeanUtils.copyProperties(model, ugroup);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("客户组管理模块-Model类与实体类转化失败", e);
			return null;
		}
		dctRuleGroup = DctRuleGroupUtils.findDctRuleGroupById(model
				.getDctRuleGroupPK());
		ugroup.setDctRuleGroup(dctRuleGroup);
		ugroup = uGroupDao.save(ugroup);
		return ugroup;
	}

	/**
	 * 绑定规则
	 * 
	 * @param model
	 * @param ugroup
	 */
	public boolean bindChargeRule(UGroupModel model, UGroup ugroup) {
		// 添加长期规则组
		Integer chargeRuleLongPk = model.getChargeRuleLongPk();
		if (chargeRuleLongPk != null) {
			UGChargeRule ugc = new UGChargeRule();
			ChargeRule rule = ChargeRuleUtils
					.findChargeRuleById(chargeRuleLongPk);
			if (rule != null) {
				ugc.setChargeRule(rule);
				ugc.setUGroup(ugroup);
				ugc.setType(rule.getRentType());
				if (ugChargeRuleDao.save(ugc) == null) {
					return false;
				}
				LogUtil.save("客户组管理", LogUtil.Option.EDIT,
						"绑定收费规则：" + rule.toString());
			}
		}
		// 添加计时、计次规则
		Integer chargeRuleCountOrTimePk = model.getChargeRuleCountOrTimePk();
		if (chargeRuleCountOrTimePk != null) {
			UGChargeRule ugc = new UGChargeRule();
			ChargeRule rule = ChargeRuleUtils
					.findChargeRuleById(chargeRuleCountOrTimePk);
			if (rule != null) {
				ugc.setChargeRule(rule);
				ugc.setUGroup(ugroup);
				ugc.setType(rule.getRentType());
				if (ugChargeRuleDao.save(ugc) == null) {
					return false;
				}
				LogUtil.save("客户组管理", LogUtil.Option.EDIT,
						"绑定收费规则：" + rule.toString());
			}
		}
		return true;
	}

	/**
	 * 绑定权限
	 * 
	 * @param model
	 * @return
	 */
	public boolean bindAuths(UGroupModel model, UGroup ugroup) {
		String authPKs = model.getAuthPKs();
		if (!StringUtils.isNotNullAndEmpty(authPKs)) {
			return true;
		}
//		List<terminal> terminalPKs = Lists.newArrayList();
//		for (String authPK : StringUtils.split(authPKs, ",")) {
//			Integer id = StringUtils.toInteger(authPK);
//			terminal terminal = terminalUtils.findById(id);
//			terminalPKs.add(terminal);
//			LogUtil.save("客户组管理", LogUtil.Option.EDIT,
//					"绑定权限：[" + ugroup.getName() + "]绑定[" + terminal.getName() + "]");
//		}
//		ugroup.setterminals(terminalPKs);
		ugroup = uGroupDao.save(ugroup);
		if (ugroup == null) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public DelValidate validateDel(String ids) {
		List<Integer> idList = super.getIdList(ids);
		List<Integer> idPermission = Lists.newArrayList();
		List<String> nameNotPermission = Lists.newArrayList();
		List<String> associateObjs = Lists.newArrayList();
		for (int id : idList) {
			UGroup group = uGroupDao.findOne(id);
			if (group.getType() != UserGroupType.DEFAULT_TEMPORARYUSER
					.getValue()) {
				idPermission.add(id);
			} else {
				nameNotPermission.add(group.getName());
				associateObjs.add("默认临时客户组");
			}
			for (UGroup ug : uGroupDao.findAll()) {
				if (group.getId() == ug.getChangeGroup()) {
					nameNotPermission.add(group.getName());
					associateObjs.add(ug.getName());
					break;
				}
			}

		}
		DelValidate delValidate = new DelValidate(idPermission,
				nameNotPermission, associateObjs);
		return delValidate;
	}

	/**
	 * 启用
	 * 
	 * @param ids
	 * 
	 * @return
	 */
	public boolean enable(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = uGroupDao.enable(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("客户组管理", LogUtil.Option.START, "启用客户组" + id);
			}
		}

		return flag;
	}

	/**
	 * 禁用
	 * 
	 * @param ids
	 * 
	 * @return
	 */
	public boolean disable(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = uGroupDao.disable(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("客户组管理", LogUtil.Option.FORBIDDEN, "禁用客户组" + id);
			}
		}

		return flag;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean del(String ids) {
		List<Integer> idList = getIdList(ids);
		if (uGroupDao.del(idList) > 0) {
			for (int id : idList) {
				LogUtil.save("客户组管理", LogUtil.Option.DEL, "删除客户组：" + id);
			}
			return true;
		}
		return false;
	}

	/**
	 * 根据groupId查询客户组
	 * 
	 * @param groupId
	 *            客户组编号
	 * @return
	 */
	public List<BaseModel> getGroupByUGroupId(String groupId) {
		List<UGroup> uGroup = Lists.newArrayList();
		uGroup = uGroupDao.findByUgroupIdLikeAndDelFlagFalse("%" + groupId
				+ "%");
		return getModelsByBeans(uGroup);
	}

	/**
	 * 根据name查询客户组
	 * 
	 * @param name
	 *            客户组名称
	 * @return
	 */
	public List<BaseModel> getGroupByName(String name) {
		List<UGroup> uGroup = Lists.newArrayList();
		uGroup = uGroupDao.findByNameLikeAndDelFlagFalse("%" + name + "%");
		return getModelsByBeans(uGroup);
	}

	/**
	 * 获得普通会员客户组 根据name查询
	 * 
	 * @param name
	 *            客户组名称
	 * @return
	 */
	public List<BaseModel> getMemberGroupByName(String name) {
		List<UGroup> uGroup = Lists.newArrayList();
		uGroup = uGroupDao
				.findByNameLikeAndTypeAndDelFlagFalseAndEnableFlagTrue("%"
						+ name + "%", UserGroupType.MEMBER_COMMON.getValue());
		return getModelsByBeans(uGroup);
	}

	/**
	 * 获得长期会员客户组 根据name查询
	 * 
	 * @param name
	 *            客户组名称
	 * @return
	 */
	public List<BaseModel> getLongMemberGroup(String name) {
		List<UGroup> uGroup = Lists.newArrayList();
		uGroup = uGroupDao
				.findByNameLikeAndTypeAndDelFlagFalseAndEnableFlagTrue("%"
						+ name + "%", UserGroupType.LONGTREM.getValue());
		return getModelsByBeans(uGroup);
	}

	/**
	 * 获得长期会员客户组 根据name查询
	 * 
	 * @param name
	 *            客户组名称
	 * @return
	 */
	public List<BaseModel> getTempGroup(String name) {
		List<UGroup> uGroup = Lists.newArrayList();
		uGroup = uGroupDao
				.findByNameLikeAndTypeAndDelFlagFalseAndEnableFlagTrue("%"
						+ name + "%", UserGroupType.TEMPORARYUSER.getValue());
		return getModelsByBeans(uGroup);
	}

	/**
	 * 获得长期会员客户组 根据name查询
	 * 
	 * @param name
	 *            客户组名称
	 * @return
	 */
	public List<BaseModel> getNotLongGroup(String name) {
		List<UGroup> uGroup = Lists.newArrayList();
		uGroup = uGroupDao
				.findByNameLikeAndTypeNotAndDelFlagFalseAndEnableFlagTrue("%"
						+ name + "%", UserGroupType.LONGTREM.getValue());
		return getModelsByBeans(uGroup);
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<UGroup> uGroups) {
		List<UGroupModel> uGroupModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(uGroups)) {
			return Lists.newArrayList(uGroupModels);
		}
		try {
			uGroupModels = BeanUtils.copyListProperties(uGroups,
					UGroupModel.class);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("客户组管理模块-实体类与Model类数组转化失败", e);
			return null;
		}
		return Lists.newArrayList(uGroupModels);
	}

	@Override
	protected Specification<UGroup> buildSpecification(BaseModel model) {
		UGroupModel groupModel = (UGroupModel) model;
		Integer ugroupPKId = groupModel.getUgroupPKId();
		Integer ugroupPK = groupModel.getUgroupPK();

		return new Specification<UGroup>() {
			@Override
			public Predicate toPredicate(Root<UGroup> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (ugroupPKId != null) {
					list.add(cb.equal(root.get("id"), ugroupPKId));
				}
				if (ugroupPK != null) {
					list.add(cb.equal(root.get("id"), ugroupPK));
				}
				list.add(cb.equal(root.get("delFlag"), 0));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};

	}
}
