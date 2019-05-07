package com.langmy.terminal.modules.sys.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import com.google.common.collect.Sets;
import com.langmy.terminal.common.entity.Auth;
import com.langmy.terminal.common.entity.Role;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.sys.dao.AuthDao;
import com.langmy.terminal.modules.sys.dao.RoleDao;
import com.langmy.terminal.modules.sys.model.RoleModel;
import com.langmy.terminal.modules.sys.utils.RoleUtils;

/**
 * 角色业务层
 * 
 * @author ZZD
 *
 */
@Service
public class RoleService extends BaseService<Role> {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private AuthDao authDao;

	public RoleService() {
		super.baseDao = SpringContextHolder.getBean(RoleDao.class);
	}

	/**
	 * 根据roleName模糊查询
	 * 
	 * @param roleName
	 *            角色名称
	 * @return
	 */
	public List<BaseModel> getRoleByName(String roleName) {
		List<Role> role = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(roleName)) {
			role = roleDao.findByRoleNameLikeAndDelFlagFalse("%" + roleName
					+ "%");
		} else {
			role = roleDao.findByDelFlagFalse();
		}
		return getModelsByBeans(role);
	}

	/**
	 * 根据roleId模糊查询
	 * 
	 * @param roleId
	 *            角色编号
	 * @return
	 */
	public List<BaseModel> getRoleById(String roleId) {
		List<Role> role = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(roleId)) {
			role = roleDao.findByRoleIdLikeAndDelFlagFalse("%" + roleId + "%");
		} else {
			role = roleDao.findByDelFlagFalse();
		}
		return getModelsByBeans(role);
	}

	/**
	 * 绑定权限
	 * 
	 * @param model
	 * @return
	 */
	public boolean bindAuths(RoleModel model) {
		Role role = roleDao.findOne(model.getId());
		if (role == null) {
			return false;
		}
		Set<Auth> authPKs = Sets.newHashSet();
		for (String authPK : StringUtils.split(model.getAuthPKs(), ",")) {
			Integer id = StringUtils.toInteger(authPK);
			Auth auth = authDao.findOne(id);
			authPKs.add(auth);
			LogUtil.save("角色管理", LogUtil.Option.EDIT,
					"绑定权限：[" + role.getRoleName() + "]绑定[" + auth.getName()
							+ "]");
		}
		role.setAuthList(authPKs);
		role = roleDao.save(role);
		if (role == null) {
			return false;
		}

		return true;
	}

	/**
	 * 添加角色
	 * 
	 * @param model
	 * @return
	 */
	public boolean add(RoleModel model) {
		Role role = new Role();
		try {
			BeanUtils.copyProperties(model, role);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("角色管理模块-新增角色-实体类转成model类失败");
		}
		role.setRoleId(RoleUtils.getRoleNum());
		role.setDelFlag(false);
		if (roleDao.save(role) == null)
			return false;
		LogUtil.save("角色管理", LogUtil.Option.ADD, "新增角色：" + role.toString());
		return true;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean delete(String ids) {
		List<Integer> idList = getIdList(ids);
		if (roleDao.del(idList) > 0) {
			roleDao.delAuthOfRole(idList);
			roleDao.delOperaterOfRole(idList);
			for (int id : idList) {
				LogUtil.save("角色管理", LogUtil.Option.DEL, "删除角色：" + id);
			}
			return true;
		}
		return false;
	}

	/**
	 * 编辑角色
	 * 
	 * @param model
	 * @return
	 */
	public boolean edit(RoleModel model) {
		Role role = roleDao.findOne(model.getId());
		role.setRoleDesc(model.getRoleDesc());
		role = roleDao.save(role);
		if (role == null) {
			return false;
		}
		LogUtil.save("角色管理", LogUtil.Option.EDIT, "修改角色：" + role.toString());
		return true;
	}

	/**
	 * 获得所有角色
	 * 
	 * @return
	 */
	public List<BaseModel> getRoles() {
		return getModelsByBeans(roleDao.findByDelFlagFalse());
	}

	/**
	 * 查看详细信息 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public RoleModel view(int id) {
		Role role = roleDao.findOne(id);
		RoleModel model = new RoleModel();
		try {
			BeanUtils.copyProperties(role, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("角色管理模块-查看详细信息-实体类转成model类失败");
			return null;
		}
		String authPKs = "";
		Set<Auth> authList = role.getAuthList();
		for(Auth auth :authList){
			authPKs += auth.getId()+",";
		}
		model.setAuthPKs(authPKs);
		return model;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Role> roles) {
		List<RoleModel> roleModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(roles)) {
			return Lists.newArrayList(roleModels);
		}
		try {
			roleModels = BeanUtils.copyListProperties(roles, RoleModel.class);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("角色管理模块-实体类与Model类数组转化失败", e);
			return null;
		}
		return Lists.newArrayList(roleModels);
	}

	@Override
	protected Specification<Role> buildSpecification(BaseModel model) {
		RoleModel roleModel = (RoleModel) model;
		String roleId = roleModel.getRoleId();
		String roleName = roleModel.getRoleName();

		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.isNotNullAndEmpty(roleId)) {
					list.add(cb.like(root.get("roleId"), "%" + roleId + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(roleName)) {
					list.add(cb.like(root.get("roleName"), "%" + roleName + "%"));
				}
				list.add(cb.equal(root.get("delFlag"), 0));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};

	}
}
