package com.langmy.terminal.modules.caradmission.daoImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.caradmission.model.CarModel;

/**
 * 车辆管理DaoImpl
 * 
 * @author ZZD
 *
 */
public class CarDaoImpl {
	@PersistenceContext
	private EntityManager em;

	/**
	 * 获得车辆列表
	 * 
	 * @param model
	 * @param firstResult
	 * @param length
	 * @return
	 */
	public Map<String, Object> getCarList(CarModel model, int firstResult,
			int length) {
		String licensePlate = model.getLicensePlate();
		String type = model.getType();
		String carColor = model.getCarColor();
		int carType = model.getCarType();
		String carModel = model.getCarModel();
		int isFree = model.getIsFree();
		String uacc = model.getUacc();
		StringBuilder hql = new StringBuilder("from Car u where 1=1");
		if (StringUtils.isNotNullAndEmpty(licensePlate)) {
			hql.append(" and u.licensePlate like :licensePlate");
		}
		if (StringUtils.isNotNullAndEmpty(type)) {
			hql.append(" and u.type like :type");
		}
		if (StringUtils.isNotNullAndEmpty(carColor)) {
			hql.append(" and u.carColor like :carColor");
		}
		if (carType != -1) {
			hql.append(" and u.carType = :carType");
		}
		if (StringUtils.isNotNullAndEmpty(carModel)) {
			hql.append(" and u.carModel like :carModel");
		}
		if (StringUtils.isNotNullAndEmpty(uacc)) {
			hql.append(" and u.user.id = :uacc");
		}
		if (isFree != -1) {
			hql.append(" and u.type in( select c.typeName from CarType c where c.isFree = :isFree)");
		}
		hql.append(" and u.delFlag = 0");
		Query query = em.createQuery(hql.toString());

		if (StringUtils.isNotNullAndEmpty(licensePlate)) {
			query.setParameter("licensePlate", "%" + licensePlate + "%");
		}
		if (StringUtils.isNotNullAndEmpty(type)) {
			query.setParameter("type", "%" + type + "%");
		}
		if (StringUtils.isNotNullAndEmpty(carColor)) {
			query.setParameter("carColor", "%" + carColor + "%");
		}
		if (carType != -1) {
			query.setParameter("carType", carType);
		}
		if (StringUtils.isNotNullAndEmpty(carModel)) {
			query.setParameter("carModel", "%" + carModel + "%");
		}
		if (StringUtils.isNotNullAndEmpty(uacc)) {
			query.setParameter("uacc", Integer.parseInt(uacc));
		}
		if (isFree != -1) {
			if (isFree == 1) {
				query.setParameter("isFree", true);
			} else if (isFree == 0) {
				query.setParameter("isFree", false);
			}
		}
		List<Car> wholeResults = query.getResultList();
		int counts = wholeResults.size();
		query.setFirstResult(firstResult).setMaxResults(length);
		List<Car> results = query.getResultList();
		Map<String, Object> map = Maps.newHashMap();
		map.put("list", results);
		map.put("wholeCount", counts);
		return map;
	}

}
