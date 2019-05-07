package com.langmy.terminal.modules.charge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.DelChargeRec;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.charge.dao.DelChargeRecDao;
import com.langmy.terminal.modules.charge.model.DelChargeRecModel;
import com.langmy.terminal.modules.charge.service.extend.DelChargeRecExtend;

/**
 * 已删除收费记录Service
 * @author lzy
 *
 */
@Service
public class DelChargeRecService extends BaseService<DelChargeRec> {

	@Autowired
	private DelChargeRecDao delChargeRecDao;

	public DelChargeRecService(){
		super.baseDao = SpringContextHolder.getBean("delChargeRecDao");
	}
	
	public void delete(String ids) {
		List<Integer> idList = getIdList(ids);
		for (int id : idList) {
			delete(id);
		}
	}

	public void delete(int id) {
		delChargeRecDao.delete(id);
	}

	public DelChargeRecModel getDelChargeRecById(int id) {
		DelChargeRec delChargeRec = delChargeRecDao.findOne(id);
		return DelChargeRecExtend.covertToModel(delChargeRec);
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<DelChargeRec> entities) {
		return Lists.newArrayList(DelChargeRecExtend.covertToModel(entities));
	}

	@Override
	protected Specification<DelChargeRec> buildSpecification(BaseModel model) {
		DelChargeRecModel delChargeRecModel = (DelChargeRecModel) model;

		String licensePlate = delChargeRecModel.getCarModel().getLicensePlate();
		String type = delChargeRecModel.getCarModel().getType();
		int payType = delChargeRecModel.getPayType();
		Date payTimeFrom = delChargeRecModel.getPayTimeFrom();
		Date payTimeTo = delChargeRecModel.getPayTimeTo();
		String name = delChargeRecModel.getDctRuleGroupModel().getName();

		Date delTimeFrom = delChargeRecModel.getDelTimeFrom();
		Date delTimeTo = delChargeRecModel.getDelTimeTo();

		return new Specification<DelChargeRec>() {
			@Override
			public Predicate toPredicate(Root<DelChargeRec> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (StringUtils.isNotNullAndEmpty(licensePlate)) {
					list.add(cb.like(root.get("adminssionRec").get("car").get("licensePlate"), "%" + licensePlate + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(type)) {
					list.add(cb.like(root.get("adminssionRec").get("car").get("type"), "%" + type + "%"));
				}
				if (payType != -1) {
					list.add(cb.equal(root.get("payType"), payType));
				}
				if (StringUtils.isNotNullAndEmpty(name)) {
					list.add(cb.like(root.get("dctRuleGroup").get("name"), "%" + name + "%"));
				}
				if (payTimeFrom != null) {
					list.add(cb.greaterThan(root.get("payTime"), DateUtils.getDateStart(payTimeFrom)));
				}
				if (payTimeTo != null) {
					list.add(cb.lessThan(root.get("payTime"), DateUtils.getDateEnd(payTimeTo)));
				}
				if (delTimeFrom != null) {
					list.add(cb.greaterThan(root.get("delTime"), DateUtils.getDateStart(delTimeFrom)));
				}
				if (delTimeTo != null) {
					list.add(cb.lessThan(root.get("delTime"), DateUtils.getDateEnd(delTimeTo)));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

	}
}
