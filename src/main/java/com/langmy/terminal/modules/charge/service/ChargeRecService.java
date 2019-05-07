package com.langmy.terminal.modules.charge.service;

import java.lang.reflect.InvocationTargetException;
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
import com.langmy.terminal.common.entity.ChargeRec;
import com.langmy.terminal.common.entity.DelChargeRec;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.charge.dao.ChargeRecDao;
import com.langmy.terminal.modules.charge.dao.DelChargeRecDao;
import com.langmy.terminal.modules.charge.model.ChargeRecModel;
import com.langmy.terminal.modules.charge.service.extend.ChargeRecExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 收费规则Service
 * @author lzy
 *
 */
@Service
public class ChargeRecService extends BaseService<ChargeRec> {

	@Autowired
	private ChargeRecDao chargeRecDao;
	@Autowired
	private DelChargeRecDao delChargeRecDao;
	
	@Autowired
	public ChargeRecService(ChargeRecDao chargeRecDao){
		super(chargeRecDao);
	}


	/**
	 * 尚未使用
	 * 删除该收费记录，但是将其转移到del_charge_rec表中
	 */
	public boolean delete(int id) {
		ChargeRec chargeRec = chargeRecDao.findOne(id);
		if (chargeRec == null) {
			return false;
		}
		DelChargeRec delChargeRec = new DelChargeRec();
		try {
			BeanUtils.copyProperties(chargeRec, delChargeRec);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("收费管理-收费记录管理-删除收费记录-Bean赋值转化失败");
			return false;
		}
		delChargeRec = delChargeRecDao.save(delChargeRec);
		if (delChargeRec == null) {
			logger.error("删除收费记录失败");
			return false;
		}
		chargeRecDao.delete(chargeRec);
		LogUtil.save("收费模块", LogUtil.Option.ADD, "删除收费记录规则："+chargeRec.toString());
		return true;
	}

	
	/**
	 * 尚未使用
	 * @param ids
	 * @return
	 */
	public boolean delete(String ids) {
		boolean flag = true;
		List<Integer> idList = getIdList(ids);
		for (int id : idList) {
			if (!delete(id)) {
				flag = false;
				break;
			}
		}
		return flag;
	}


	public ChargeRecModel getChargeRecById(int id){
		ChargeRec chargeRec = chargeRecDao.findOne(id);
		return ChargeRecExtend.covertToModel(chargeRec);
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<ChargeRec> entities) {
		return Lists.newArrayList(ChargeRecExtend.covertToModel(entities));
	}

	@Override
	protected Specification<ChargeRec> buildSpecification(BaseModel model) {
		ChargeRecModel chargeRecModel = (ChargeRecModel) model;

		String licensePlate = chargeRecModel.getCarModel().getLicensePlate();
		String type = chargeRecModel.getCarModel().getType();
		int payType = chargeRecModel.getPayType();
		Date payTimeFrom = chargeRecModel.getPayTimeFrom();
		Date payTimeTo = chargeRecModel.getPayTimeTo();
		String name = chargeRecModel.getDctRuleGroupModel().getName();

		return new Specification<ChargeRec>() {
			@Override
			public Predicate toPredicate(Root<ChargeRec> root, CriteriaQuery<?> query,
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
				if (payTimeFrom != null) {
					list.add(cb.greaterThan(root.get("payTime"), DateUtils.getDateStart(payTimeFrom)));
				}
				if (payTimeTo != null) {
					list.add(cb.lessThan(root.get("payTime"), DateUtils.getDateEnd(payTimeTo)));
				}
				if (StringUtils.isNotNullAndEmpty(name)) {
					list.add(cb.like(root.get("dctRuleGroup").get("name"), "%" + name + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
}
