package com.langmy.terminal.modules.charge.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.DelValidate;
import com.langmy.terminal.modules.charge.dao.DctRuleDao;
import com.langmy.terminal.modules.charge.dao.DctRuleGroupDao;
import com.langmy.terminal.modules.charge.model.DctRuleGroupModel;
import com.langmy.terminal.modules.charge.service.extend.DctRuleGroupExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 优惠规则组Service
 * 
 * @author lzy
 *
 */
@Service
public class DctRuleGroupService extends BaseRuleService<DctRuleGroup> {

	@Autowired
	private DctRuleGroupDao dctRuleGroupDao;
	@Autowired
	private DctRuleDao dctRuleDao;

	@Autowired
	public DctRuleGroupService(DctRuleGroupDao dctRuleGroupDao) {
		super(dctRuleGroupDao);
	}


	public boolean add(DctRuleGroupModel model) throws ServiceException {
		DctRuleGroup dctRuleGroup = DctRuleGroupExtend.covertToDctRuleGroup(model);
		dctRuleGroup = dctRuleGroupDao.save(dctRuleGroup);
		if (dctRuleGroup == null) {
			return false;
		}
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"新增优惠规则组规则：" + dctRuleGroup.toString());
		return true;
	}

	public boolean edit(DctRuleGroupModel model) throws ServiceException {
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(model));
		DctRuleGroup dctRuleGroup = DctRuleGroupExtend.covertToDctRuleGroup(model);
		dctRuleGroup = dctRuleGroupDao.save(dctRuleGroup);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(dctRuleGroup));
		if(dctRuleGroup==null){
			logger.error("保存优惠规则组失败");
			return false;
		}
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"修改优惠规则组：" + dctRuleGroup.toString());
		return true;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<DctRuleGroup> dctRuleGroups) {
		return Lists.newArrayList(DctRuleGroupExtend
				.covertToDctRuleModel(dctRuleGroups));
	}

	/**
	 * 根据优惠规则组的Id获取相应的优惠规则组
	 * 
	 * @param id
	 * @return
	 */
	public DctRuleGroupModel getDctRuleGroupById(int id) {
		DctRuleGroup dctRuleGroup = dctRuleGroupDao.findOne(id);
		return DctRuleGroupExtend.covertToModel(dctRuleGroup);
	}

	@Override
	protected Specification<DctRuleGroup> buildSpecification(BaseModel model) {
		DctRuleGroupModel dctRuleGroupModel = (DctRuleGroupModel) model;
		String groupId = dctRuleGroupModel.getGroupId();
		String name = dctRuleGroupModel.getName();

		return new Specification<DctRuleGroup>() {
			@Override
			public Predicate toPredicate(Root<DctRuleGroup> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				list.add(cb.equal(root.get("delFlag").as(boolean.class), false));
				if (StringUtils.isNotNullAndEmpty(groupId)) {
					list.add(cb.like(root.get("groupId"), "%" + groupId + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(name)) {
					list.add(cb.like(root.get("name"), "%" + name + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}

	/*public void editRulesInGroup(DctRuleGroupModel modifiedModel)
			throws ServiceException {
		int id = modifiedModel.getId();
		String dctRuleIds = modifiedModel.getDctRuleIds();
		DctRuleGroup dctRuleGroup = dctRuleGroupDao.findOne(id);
		DctRuleGroupModel model = DctRuleGroupExtend
				.covertToModel(dctRuleGroup);
		model.setSelects(Lists.newArrayList());
		model.setDctRuleIds(dctRuleIds);
		edit(model);
	}*/
	
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            优惠规则Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = dctRuleGroupDao.del(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("优惠规则组管理", LogUtil.Option.DEL, "删除优惠规则组：" + id);
			}
		}

		return flag;
	}
	
	public DelValidate validateDel(String ids){
		List<Integer> idList = super.getIdList(ids);
		List<Integer> idPermission = Lists.newArrayList();
		List<String> nameNotPermission = Lists.newArrayList();
		List<String>  associateObjs = Lists.newArrayList();
		for(int id:idList){
			DctRuleGroup group = dctRuleGroupDao.findOne(id);
			List<UGroup> uGroups = group.getUGroups();
			if(ListUtils.isNullOrEmpty(uGroups)){
				idPermission.add(id);
			} else{
				nameNotPermission.add(group.getName());
				for(UGroup uGroup:uGroups)
					associateObjs.add(uGroup.getName());
			}
		}
		DelValidate delValidate = new DelValidate(idPermission, nameNotPermission, associateObjs);
		return delValidate;
	}
}
