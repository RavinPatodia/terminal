package com.langmy.terminal.modules.caradmission.daoImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.CarType;

/**
 * 车辆相关信息管理DaoImpl
 * 
 * @author ZZD
 *
 */
public class CarTypeDaoImpl {
	@PersistenceContext
	private EntityManager em;

	/**
	 * 获得车辆颜色列表
	 * 
	 * @param firstResult
	 * @param length
	 * @return
	 */
	public Map<String, Object> getColor(int firstResult, int length) {
		StringBuilder hql = new StringBuilder(
				"from CarType ct where ct.type = 1 and ct.delFlag=0 and ct.color is not null");
		Query query = em.createQuery(hql.toString());
		List<CarType> wholeResults = query.getResultList();
		int counts = wholeResults.size();
		List<CarType> results = query.getResultList();
		Map<String, Object> map = Maps.newHashMap();
		map.put("list", results);
		map.put("wholeCount", counts);
		return map;
	}

	/**
	 * 获得车辆型号列表
	 * 
	 * @param firstResult
	 * @param length
	 * @return
	 */
	public Map<String, Object> getModule(int firstResult, int length) {
		StringBuilder hql = new StringBuilder(
				"from CarType ct where ct.type = 2 and ct.delFlag=0 and ct.model is not null");
		Query query = em.createQuery(hql.toString());
		List<CarType> wholeResults = query.getResultList();
		int counts = wholeResults.size();
		List<CarType> results = query.getResultList();
		Map<String, Object> map = Maps.newHashMap();
		map.put("list", results);
		map.put("wholeCount", counts);
		return map;
	}

	/**
	 * 获得车牌类型列表
	 * 
	 * @param firstResult
	 * @param length
	 * @return
	 */
	public Map<String, Object> getType(int firstResult, int length) {
		StringBuilder hql = new StringBuilder(
				"from CarType ct where ct.type = 3 and ct.delFlag=0 and ct.typeName is not null and ct.isFree is not null");
		Query query = em.createQuery(hql.toString());
		List<CarType> wholeResults = query.getResultList();
		int counts = wholeResults.size();
		List<CarType> results = query.getResultList();
		Map<String, Object> map = Maps.newHashMap();
		map.put("list", results);
		map.put("wholeCount", counts);
		return map;
	}

}
