package com.langmy.terminal.modules.sys.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.entity.Role;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.sys.dao.OperaterDao;
import com.langmy.terminal.modules.sys.dao.RoleDao;
import com.langmy.terminal.modules.sys.model.OperaterModel;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 操作员业务层
 * 
 * @author ZZD
 *
 */
@Service
public class OperaterService extends BaseService<Operater> {

	@Autowired
	private OperaterDao operaterDao;
	@Autowired
	private RoleDao roleDao;

	private final static String DEFAULT_PWD = "123";// 默认密码

	public OperaterService() {
		super.baseDao = SpringContextHolder.getBean("operaterDao");
	}

	/**
	 * 根据操作员账户名模糊查询操作员
	 * 
	 * @param operName
	 *            操作员账户名
	 * @return List<BaseModel>
	 */
	public List<BaseModel> getOperByOperName(String operName) {
		List<Operater> oper = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(operName)) {
			oper = operaterDao.findByOperNameLike("%" + operName + "%");
		} else {
			oper = operaterDao.findByDelFlagFalse();
		}
		return getModelsByBeans(oper);
	}

	/**
	 * 根据操作员姓名模糊查询操作员
	 * 
	 * @param name
	 *            操作员姓名
	 * @return List<BaseModel>
	 */
	public List<BaseModel> getOperByName(String name) {
		List<Operater> oper = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(name)) {
			oper = operaterDao.findByNameLike("%" + name + "%");
		} else {
			oper = operaterDao.findByDelFlagFalse();
		}
		return getModelsByBeans(oper);
	}

	/**
	 * 根据name查询
	 * 
	 * @param name
	 * @return
	 */
	public List<BaseModel> getOperByNameAndType(String name, int type) {
		List<Operater> oper = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(name)) {
			oper = operaterDao.findByNameLikeAndType("%" + name + "%", type);
		} else {
			oper = operaterDao.findByDelFlagFalseAndType(type);
		}
		return getModelsByBeans(oper);
	}

	/**
	 * 绑定角色
	 * 
	 * @param model
	 *            操作员模型类
	 * @return boolean
	 */
	public boolean bindRoles(OperaterModel model) {
		Operater oper = operaterDao.findByOperNameAndDelFlagFalse(model
				.getOperName());
		if (oper == null) {
			return false;
		}
		// 这里就只对应一个角色了，如果要有多个角色的话再来改
		Set<Role> roles = Sets.newHashSet();
		Role role = roleDao.findOne(model.getRolePK());
		roles.add(role);
		oper.setRoleList(roles);
		oper = operaterDao.save(oper);
		if (oper == null) {
			return false;
		}
		LogUtil.save("操作员管理", LogUtil.Option.EDIT,
				"绑定角色：[" + oper.getOperName() + "]绑定[" + role.getRoleName()
						+ "]");
		return true;
	}

	/**
	 * 添加操作员
	 * 
	 * @param model
	 *            操作员模型类
	 * @return boolean
	 */
	public boolean add(OperaterModel model) {
		Operater oper = new Operater();
		try {
			BeanUtils.copyProperties(model, oper);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("添加操作员-model类赋值给实体类失败");
			return false;
		}
		oper.setOperPwd(SystemService.entryptPassword(DEFAULT_PWD));
		oper.setLastEnable(new Date());
		oper = operaterDao.save(oper);
		if (oper == null) {
			return false;
		}
		LogUtil.save("操作员管理", LogUtil.Option.ADD, "新增操作员：" + oper.toString());
		return true;
	}

	/**
	 * 修改操作员
	 * 
	 * @param model
	 * @return
	 */
	public boolean edit(OperaterModel model) {
		Operater oper = operaterDao.findOne(model.getId());
		try {
			BeanUtils.copyProperties(model, oper);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("修改操作员-model类赋值给实体类失败");
			return false;
		}
		oper = operaterDao.save(oper);
		if (oper == null) {
			return false;
		}
		LogUtil.save("操作员管理", LogUtil.Option.EDIT, "修改操作员：" + oper.toString());
		return true;
	}

	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            操作员Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		List<Integer> idList = super.getIdList(ids);
		int count = operaterDao.softdelete(idList);
		if (count > 0) {
			for (int id : idList) {
				LogUtil.save("操作员管理", LogUtil.Option.DEL, "删除操作员：" + id);
			}
			return true;
		}

		return false;
	}

	/**
	 * 启用
	 * 
	 * @param ids
	 *            操作员Id {1,2,3}
	 * @return boolean
	 */
	public boolean enable(String ids) {
		List<Integer> idList = super.getIdList(ids);
		int count = operaterDao.enable(idList);
		if (count > 0) {
			for (int id : idList) {
				LogUtil.save("操作员管理", LogUtil.Option.START, "启用操作员：" + id);
			}
			return true;
		}

		return false;
	}

	/**
	 * 禁用
	 * 
	 * @param ids
	 *            操作员Id {1,2,3}
	 * @return boolean
	 */
	public boolean disable(String ids) {
		List<Integer> idList = super.getIdList(ids);
		int count = operaterDao.disable(idList);
		if (count > 0) {
			for (int id : idList) {
				LogUtil.save("操作员管理", LogUtil.Option.FORBIDDEN, "禁用操作员：" + id);
			}
			return true;
		}

		return false;
	}

	/**
	 * 修改密码
	 * 
	 * @param model
	 *            操作员模型类
	 * @return boolean
	 */
	public boolean changePwd(OperaterModel model) {
		boolean flag = false;
		Operater oper = OperaterUtils.getOperater();
		if (SystemService
				.validatePassword(model.getOldPwd(), oper.getOperPwd())) {
			oper.setOperPwd(SystemService.entryptPassword(model.getNewPwd()));
			oper = operaterDao.save(oper);
			flag = true;
		}
		return flag;

	}

	/**
	 * 根据id查询操作员
	 * 
	 * @param id
	 *            操作员id
	 * @return OperaterModel
	 */
	public OperaterModel view(int id) {
		Operater operater = operaterDao.findOne(id);
		OperaterModel model = new OperaterModel();

		try {
			BeanUtils.copyProperties(operater, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("操作员模块-查看详细信息-实体类转成model类失败");
			return null;
		}
		// ftp图片路径
		model.setFtpPicUrl(getOperImgUrl(operater));
		List<Role> roles = operater.getRoleLists();
		if (ListUtils.isNullOrEmpty(roles)) {
			model.setRoleName("");
		} else {
			model.setRoleName(roles.get(0).getRoleName());
		}
		return model;
	}

	/**
	 * 获得操作员图片路径
	 * 
	 * @param operater
	 * @return
	 */
	public String getOperImgUrl(Operater operater) {
		if (!StringUtils.isNotNullAndEmpty(operater.getPicUrl())) {
			return null;
		}
		String url = Global.getConfig("ftpUrl");
		String username = Global.getConfig("ftpUserName");
		String password = Global.getConfig("ftpPwd");
		String headFtpUrl = "ftp://" + username + ":" + password + "@" + url;
		String ftpUrl = headFtpUrl + operater.getPicUrl();
		return ftpUrl;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Operater> operaters) {
		List<OperaterModel> operaterModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(operaters)) {
			return Lists.newArrayList(operaterModels);
		}
		Map<String, String> map = Maps.newHashMap();

		map.put("modelSourcePro1", "roleLists");
		map.put("modelTargetPro1", "roleModels");

		try {
			operaterModels = BeanUtils.copyListProperties(operaters,
					OperaterModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("操作员管理模块-实体类与Model类数组转化失败", e);
			return null;
		}
		return Lists.newArrayList(operaterModels);
	}

	@Override
	protected Specification<Operater> buildSpecification(BaseModel model) {
		OperaterModel operaterModel = (OperaterModel) model;
		String operName = operaterModel.getOperName();
		String name = operaterModel.getName();
		String idCard = operaterModel.getIdCard();
		int genderInt = operaterModel.getGenderInt();
		String job = operaterModel.getJob();
		int enableFlag = operaterModel.getBsm().getSectEnableFlag();
		Date dtCreateTimeFrom = operaterModel.getBsm().getDtCreateTimeFrom();
		Date dtCreateTimeTo = operaterModel.getBsm().getDtCreateTimeTo();
		Date dtLastEnableFrom = operaterModel.getBsm().getDtLastEnableFrom();
		Date dtLastEnableTo = operaterModel.getBsm().getDtLastEnableTo();
		Date dtLastDisableFrom = operaterModel.getBsm().getDtLastDisableFrom();
		Date dtLastDisableTo = operaterModel.getBsm().getDtLastDisableTo();

		return new Specification<Operater>() {
			@Override
			public Predicate toPredicate(Root<Operater> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				list.add(cb.equal(root.get("delFlag"), false));
				if (StringUtils.isNotNullAndEmpty(operName)) {
					list.add(cb.like(root.get("operName"), "%" + operName + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(idCard)) {
					list.add(cb.like(root.get("idCard"), "%" + idCard + "%"));
				}
				if (genderInt != -1) {
					if (genderInt == 0)
						list.add(cb.equal(root.get("gender"), false));
					else
						list.add(cb.equal(root.get("gender"), true));
				}
				if (StringUtils.isNotNullAndEmpty(job)) {
					list.add(cb.like(root.get("job"), "%" + job + "%"));
				}

				if (StringUtils.isNotNullAndEmpty(name)) {
					list.add(cb.like(root.get("name"), "%" + name + "%"));
				}
				if (enableFlag != -1) {
					if (enableFlag == 0)
						list.add(cb.equal(root.get("enableFlag"), false));
					else
						list.add(cb.equal(root.get("enableFlag"), true));
				}
				if (dtCreateTimeFrom != null) {
					list.add(cb.greaterThan(root.get("createTime"),
							DateUtils.getDateStart(dtCreateTimeFrom)));
				}
				if (dtCreateTimeTo != null) {
					list.add(cb.lessThan(root.get("createTime"),
							DateUtils.getDateEnd(dtCreateTimeTo)));
				}
				if (dtLastEnableFrom != null) {
					list.add(cb.greaterThan(root.get("lastEnable"),
							DateUtils.getDateStart(dtLastEnableFrom)));
				}
				if (dtLastEnableTo != null) {
					list.add(cb.lessThan(root.get("lastEnable"),
							DateUtils.getDateEnd(dtLastEnableTo)));
				}
				if (dtLastDisableFrom != null) {
					list.add(cb.greaterThan(root.get("lastDisable"),
							DateUtils.getDateStart(dtLastDisableFrom)));
				}
				if (dtLastDisableTo != null) {
					list.add(cb.lessThan(root.get("lastDisable"),
							DateUtils.getDateEnd(dtLastDisableTo)));
				}
				list.add(cb.equal(root.get("delFlag"), 0));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};

	}
}
