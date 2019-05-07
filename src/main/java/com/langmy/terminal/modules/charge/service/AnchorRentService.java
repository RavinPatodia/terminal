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
import com.langmy.terminal.common.entity.AnchorRent;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.charge.dao.AnchorRentDao;
import com.langmy.terminal.modules.charge.model.AnchorRentModel;
import com.langmy.terminal.modules.charge.service.extend.AnchorRentExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 长期收费
 * 
 * @author lzy
 *
 */
@Service
public class AnchorRentService extends BaseRuleService<AnchorRent> {

	@Autowired
	private AnchorRentDao anchorRentDao;
	@Autowired
	private AnchorGroupService anchorGroupService;

	@Autowired
	public AnchorRentService(AnchorRentDao anchorRentDao) {
		super(anchorRentDao);
	}

	/**
	 * 长期收费新增
	 * 
	 * @param model
	 * @return
	 */
	public boolean add(AnchorRentModel model) {
		AnchorRent anchorRent = AnchorRentExtend.convertToAnchorRent(model);
		logger.info(anchorRent.toString());
		anchorRent = anchorRentDao.save(anchorRent);
		if (anchorRent == null)
			return false;
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"新增长期收费规则：" + anchorRent.toString());
		return true;
	}

	/**
	 * 长期收费修改
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean edit(AnchorRentModel model) throws ServiceException {
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(model));
		AnchorRent anchorRent = AnchorRentExtend.convertToAnchorRent(model);
		if(logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(anchorRent));
		anchorRentDao.save(anchorRent);
		if(anchorRent==null){
			logger.error("修改长期收费规则保存失败");
			return false;
		}
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"修改长期收费规则：" + anchorRent.toString());
		return true;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<AnchorRent> anchorRents) {
		return Lists.newArrayList(AnchorRentExtend.covertToModel(anchorRents));
	}

	public AnchorRentModel findAnchorRentById(int id) {
		AnchorRent charge = anchorRentDao.findOne(id);
		return AnchorRentExtend.covertToModel(charge);
	}

	@Override
	protected Specification<AnchorRent> buildSpecification(BaseModel model) {
		AnchorRentModel anchorRentModel = (AnchorRentModel) model;

		String anchorRentId = anchorRentModel.getAnchorRentId();
		String name = anchorRentModel.getName();
		Double fee = anchorRentModel.getFee();
		Integer month = anchorRentModel.getMonth();
		Double addFee = anchorRentModel.getAddFee();

		return new Specification<AnchorRent>() {
			@Override
			public Predicate toPredicate(Root<AnchorRent> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				list.add(cb.equal(root.get("delFlag").as(boolean.class), false));
				if (StringUtils.isNotNullAndEmpty(anchorRentId)) {
					list.add(cb.like(root.get("anchorRentId"), "%"
							+ anchorRentId + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(name)) {
					list.add(cb.like(root.get("name"), "%" + name + "%"));
				}
				if (fee != null) {
					list.add(cb.equal(root.get("fee"), fee));
				}
				if (month != null) {
					list.add(cb.equal(root.get("month"), month));
				}
				if (addFee != null) {
					list.add(cb.equal(root.get("addFee"), addFee));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

	}

	/**
	 * 获得当前在运用的启用的长期收费规则
	 * 
	 * @return
	 */
	public List<AnchorRentModel> getEnableAndActiveAnchorRent() {
		return AnchorRentExtend.covertToModel(anchorRentDao
				.findByDelFlagFalse());
	}
	
	
	/**
	 * 逻辑删除长期收费规则并将长期规则对应规则组中联系断开
	 * 
	 * @param ids
	 *            长期收费规则Id {1,2,3}
	 * @return boolean
	 * @throws ServiceException 
	 */
	public void delete(String ids) throws ServiceException {
		List<Integer> idList = super.getIdList(ids);
		for(int id:idList){
			//逻辑删除长期收费规则
			int count = anchorRentDao.del(id);
			if(count==0)
				throw new ServiceException("删除长期收费规则:id为"+id+"失败");
			AnchorRent anchorRent = anchorRentDao.findOne(id);
			anchorRent.setGAnchorRents(Lists.newArrayList());
			anchorRent = anchorRentDao.save(anchorRent);
			if(anchorRent==null)
				throw new ServiceException("删除长期收费规则与长期规则组对应的联系失败");
			LogUtil.save("长期收费规则管理", LogUtil.Option.DEL, "删除长期收费规则：" + id);
		}
	}
}
