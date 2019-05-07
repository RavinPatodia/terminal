package com.langmy.terminal.modules.report.service;

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
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.RechargeRec;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.report.DaoImpl.ReCharRecDaoImpl;
import com.langmy.terminal.modules.report.dao.RecharRecDao;
import com.langmy.terminal.modules.report.model.RechargeRecModel;

@Service
public class RecharRecService extends BaseService<RechargeRec> {
	@Autowired
	private RecharRecDao recharRecDao;

	@Autowired
	private ReCharRecDaoImpl reCharRecDaoImpl;

	public RecharRecService() {
		super.baseDao = SpringContextHolder.getBean(RecharRecDao.class);
	}

	public List<RechargeRecModel> exportData(RechargeRecModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<RechargeRec> rechargeRecs) {
		List<RechargeRecModel> reChargeRecModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(rechargeRecs)) {
			return Lists.newArrayList(reChargeRecModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "operater.operName");
		map.put("targetPro1", "operOperName");
		map.put("sourcePro2", "operater.name");
		map.put("targetPro2", "operName");
		map.put("sourcePro3", "user.UAcc");
		map.put("targetPro3", "userUacc");
		map.put("sourcePro4", "user.name");
		map.put("targetPro4", "userName");
		map.put("sourcePro5", "chargeRule.ruleName");
		map.put("targetPro5", "ruleName");
		try {
			reChargeRecModels = BeanUtils.copyListProperties(rechargeRecs,
					RechargeRecModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("报表模块-客户充值-实体类与Model类数组转化失败");

		}
		return Lists.newArrayList(reChargeRecModels);
	}

	@Override
	protected Specification<RechargeRec> buildSpecification(BaseModel baseModel) {
		String operOperName = ((RechargeRecModel) baseModel).getOperOperName();
		String userUacc = ((RechargeRecModel) baseModel).getUserUacc();
		Date startTime = ((RechargeRecModel) baseModel).getStartTime();
		Date endTime = ((RechargeRecModel) baseModel).getEndTime();
		Integer rechargeWay = ((RechargeRecModel) baseModel).getRechargeWay();
		return new Specification<RechargeRec>() {

			@Override
			public Predicate toPredicate(Root<RechargeRec> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (StringUtils.isNotNullAndEmpty(operOperName)) {
					list.add(cb.equal(root.get("operater").get("operName"),
							operOperName));
				}
				if (StringUtils.isNotNullAndEmpty(userUacc)) {
					list.add(cb.equal(root.get("user").get("UAcc"), userUacc));
				}
				if (startTime != null) {
					list.add(cb.greaterThanOrEqualTo(root.get("rechargeTime")
							.as(Date.class), DateUtils.getDateStart(startTime)));

				}
				if (rechargeWay != -1) {
					list.add(cb.equal(root.get("rechargeWay"), rechargeWay));

				}
				if (endTime != null) {
					list.add(cb.lessThanOrEqualTo(
							root.get("rechargeTime").as(Date.class),
							DateUtils.getDateEnd(endTime)));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}

}
