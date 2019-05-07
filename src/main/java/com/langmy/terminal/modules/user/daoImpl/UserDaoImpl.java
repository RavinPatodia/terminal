package com.langmy.terminal.modules.user.daoImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.user.model.UserModel;

/**
 * 客户Dao实现类
 * 
 * @author ZZD
 *
 */
public class UserDaoImpl {
	@PersistenceContext
	private EntityManager em;

	/**
	 * 获得客户列表
	 * 
	 * @param user
	 * @param firstResult
	 * @param length
	 * @return
	 */
	public Map<String, Object> getNotBlackUsers(UserModel user,
			int firstResult, int length) {
		Integer userPK = user.getUserPK();
		Integer state = user.getState();
		String uGroup = user.getUgroupName();
		String licensePlate = user.getLicensePlate();
		StringBuilder hql = new StringBuilder("from User u where 1=1");
		if (userPK != null && userPK != -1) {
			hql.append(" and u.id = :userPK");
		}

		if (state != null && state != -1) {
			hql.append(" and u.state = :state");
		}

		if (StringUtils.isNotNullAndEmpty(uGroup)) {
			hql.append(" and u.UGroup.id = :uGroupId");
		}
		if (StringUtils.isNotNullAndEmpty(licensePlate)) {
			hql.append(" and u.id in( select c.user from Car c where c.licensePlate like :licensePlate)");
		}
		hql.append(" and u.state < 4 or u.state >4");

		Query query = em.createQuery(hql.toString());
		if (userPK != null && userPK != -1) {
			query.setParameter("userPK", userPK);
		}
		if (state != null && state != -1) {
			query.setParameter("state", state);
		}

		if (StringUtils.isNotNullAndEmpty(uGroup)) {
			query.setParameter("uGroupId", Integer.parseInt(uGroup));
		}
		if (StringUtils.isNotNullAndEmpty(licensePlate)) {
			query.setParameter("licensePlate", "%" + licensePlate + "%");
		}
		List<User> wholeResults = query.getResultList();
		int counts = wholeResults.size();
		query.setFirstResult(firstResult).setMaxResults(length);
		List<User> results = query.getResultList();
		Map<String, Object> map = Maps.newHashMap();
		map.put("list", results);
		map.put("wholeCount", counts);
		return map;
	}

}
