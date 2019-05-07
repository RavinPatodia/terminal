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
import com.langmy.terminal.common.entity.AnchorGroup;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.DelValidate;
import com.langmy.terminal.modules.charge.dao.AnchorGroupDao;
import com.langmy.terminal.modules.charge.dao.AnchorRentDao;
import com.langmy.terminal.modules.charge.dao.ChargeRuleDao;
import com.langmy.terminal.modules.charge.model.AnchorGroupModel;
import com.langmy.terminal.modules.charge.service.extend.AnchorGroupExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 长期收费规则组
 * 
 * @author lzy
 *
 */
@Service
public class AnchorGroupService extends BaseRuleService<AnchorGroup> {

	@Autowired
	private ChargeRuleService chargeRuleService;
	@Autowired
	private AnchorGroupDao anchorGroupDao;
	@Autowired
	private AnchorRentDao anchorRentDao;
	@Autowired
	private ChargeRuleDao chargeRuleDao;

	@Autowired
	public AnchorGroupService(AnchorGroupDao anchorGroupDao) {
		super(anchorGroupDao);
	}

	/**
	 * 长期收费规则组新增
	 * 
	 * @param model
	 * @return
	 */
	public boolean add(AnchorGroupModel model) {
		AnchorGroup anchorGroup = AnchorGroupExtend.covertToAnchorGroup(model);
		anchorGroup = anchorGroupDao.save(anchorGroup);
		if (anchorGroup == null){
			logger.error("新增长期收费规则组失败");
			return false;
		}
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"新增长期收费组规则：" + anchorGroup.toString());
		return true;
	}

	/**
	 * 长期收费规则组修改
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean edit(AnchorGroupModel model) throws ServiceException {
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(model));
		AnchorGroup anchorGroup = AnchorGroupExtend.covertToAnchorGroup(model);
		anchorGroup = anchorGroupDao.save(anchorGroup);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(anchorGroup));
		
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"修改长期收费组规则：" + anchorGroup.toString());
		return true;
	}

	/**
	 * 长期收费规则组根据名称查询列表
	 * 
	 * @param name
	 * @return
	 */
	public List<BaseModel> list(String name) {
		List<AnchorGroup> anchorGroupCharges;
		anchorGroupCharges = anchorGroupDao
				.findByNameContainingAndDelFlagFalse(name);
		List<BaseModel> model = getModelsByBeans(anchorGroupCharges);
		return model;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<AnchorGroup> anchorGroups) {
		return Lists.newArrayList(AnchorGroupExtend.coverToModel(anchorGroups));
	}


	/**
	 * 根据长期收费规则组的Id获得相应的规则
	 * 
	 * @param id
	 * @return
	 */
	public AnchorGroupModel findAnchorGroupById(int id) {
		AnchorGroup anchorGroup = anchorGroupDao.findOne(id);
		return AnchorGroupExtend.covertToModel(anchorGroup);
	}

	@Override
	protected Specification<AnchorGroup> buildSpecification(BaseModel model) {
		AnchorGroupModel anchorGroupModel = (AnchorGroupModel) model;

		String anchorGroupId = anchorGroupModel.getAnchorGroupId();
		String name = anchorGroupModel.getName();
		String describion = anchorGroupModel.getDescribion();

		return new Specification<AnchorGroup>() {
			@Override
			public Predicate toPredicate(Root<AnchorGroup> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				list.add(cb.equal(root.get("delFlag").as(boolean.class), false));
				if (StringUtils.isNotNullAndEmpty(anchorGroupId)) {
					list.add(cb.like(root.get("anchorGroupId"), "%"
							+ anchorGroupId + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(name)) {
					list.add(cb.like(root.get("name"), "%" + name + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(describion)) {
					list.add(cb.like(root.get("describion"), "%" + describion
							+ "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

	}

	/**
	 * 长期收费规则组组内长期规则的修改
	 * 
	 * @param modifiedModel
	 * @throws ServiceException
	 *//*
	public void editRulesInGroup(AnchorGroupModel modifiedModel)
			throws ServiceException {
		int id = modifiedModel.getId();
		String anchorRentPKs = modifiedModel.getAnchorRentPKs();
		AnchorGroup anchorGroup = anchorGroupDao.findOne(id);
		AnchorGroupModel model = AnchorGroupExtend.covertToModel(anchorGroup);
		model.setSelects(Lists.newArrayList());
		model.setAnchorRentPKs(anchorRentPKs);
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
		int count = anchorGroupDao.del(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("优惠规则管理", LogUtil.Option.DEL, "删除优惠规则：" + id);
			}
		}

		return flag;
	}

	/**
	 * @param ids
	 * @return
	 */
	public DelValidate validateDel(String ids) {
		List<Integer> idList = super.getIdList(ids);
		List<Integer> idPermission = Lists.newArrayList();
		List<String> nameNotPermission = Lists.newArrayList();
		List<String>  associateObjs = Lists.newArrayList();
		for(int id:idList){
			AnchorGroup group = anchorGroupDao.findOne(id);
			List<ChargeRule> chargeRules = chargeRuleDao.findByBaseRuleIdAndRentType(id, ChargeRuleService.RENT_TYPE_ANCHOR);
			if(ListUtils.isNullOrEmpty(chargeRules)){
				idPermission.add(id);
			}
			else{
				nameNotPermission.add(group.getName());
				for(ChargeRule chargeRule:chargeRules)
					associateObjs.add(chargeRule.getName());
			}
		}
		DelValidate delValidate = new DelValidate(idPermission, nameNotPermission, associateObjs);
		return delValidate;
	}
}
