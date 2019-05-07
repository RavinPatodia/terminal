package com.langmy.terminal.modules.log.service;

import java.beans.IntrospectionException;
import java.io.IOException;
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
import com.langmy.terminal.common.entity.Log;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.log.dao.LogDao;
import com.langmy.terminal.modules.log.model.LogModel;

/**
 * 操作日志Service
 * 
 * @author ZZD
 *
 */
@Service
public class LogService extends BaseService<Log> {
	@Autowired
	private LogDao logDao;

	public LogService() {
		super.baseDao = SpringContextHolder.getBean("logDao");
	}

	/**
	 * 删除单条记录
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		Log log = logDao.findOne(id);
		if (log == null) {
			return false;
		}
		logDao.delete(id);
		return true;

	}

	/**
	 * 删除多条记录
	 * 
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

	@Override
	protected List<BaseModel> getModelsByBeans(List<Log> logs) {

		List<LogModel> logModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(logs)) {
			return Lists.newArrayList(logModels);
		}
		try {
			logModels = BeanUtils.copyListProperties(logs, LogModel.class);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("报表模块-日志-实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(logModels);

	}

	@Override
	protected Specification<Log> buildSpecification(BaseModel model) {
		String operMan = ((LogModel) model).getOperMan();
		String operModule = ((LogModel) model).getOperModule();
		Date startTime = ((LogModel) model).getStartTime();
		Date endTime = ((LogModel) model).getEndTime();
		String operOption = ((LogModel) model).getOperOption();
		return new Specification<Log>() {

			@Override
			public Predicate toPredicate(Root<Log> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (StringUtils.isNotNullAndEmpty(operMan)) {
					list.add(cb.like(root.get("operMan"), "%" + operMan + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(operModule)) {
					list.add(cb.like(root.get("operModule"), "%" + operModule
							+ "%"));
				}
				if (startTime != null) {
					list.add(cb.greaterThanOrEqualTo(
							root.get("operTime").as(Date.class),
							DateUtils.getDateStart(startTime)));
				}
				if (endTime != null) {
					list.add(cb.lessThanOrEqualTo(
							root.get("operTime").as(Date.class),
							DateUtils.getDateEnd(endTime)));
				}
				if (!"-1".equals(operOption)) {
					list.add(cb.like(root.get("operOption"), "%" + operOption
							+ "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

	}
}
