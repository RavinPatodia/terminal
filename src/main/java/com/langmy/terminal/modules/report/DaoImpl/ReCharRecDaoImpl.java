package com.langmy.terminal.modules.report.DaoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

/**
 * 充值记录Dao实现类
 * 
 * @author ZZD
 *
 */
@Repository
public class ReCharRecDaoImpl {
	@PersistenceContext
	private EntityManager em;


	/**
	 * 多条件查询记录
	 * 
	 * @param startTime
	 * @param endTime
	 * @param oper
	 * @return
	 */
	public List<Object[]> searchForExportData(Date startTime, Date endTime,
			Integer oper) {
		StringBuilder hql = new StringBuilder(
				"select r.oper_id,r.actual_pay,r.amout_pay,r.pay_time,r.pay_type from charge_rec r where r.pay_type=1");

		if (oper != null) {
			hql.append(" and r.oper_id = :oper");
		}
		if (startTime != null) {
			hql.append(" and r.open_time >= :startTime");
		}
		if (endTime != null) {
			hql.append(" and r.open_time <= :endTime");
		}
		Query query = em.createNativeQuery(hql.toString());
		if (oper != null) {
			query.setParameter("oper", oper);
		}
		if (startTime != null) {
			query.setParameter("startTime", startTime);
		}
		if (endTime != null) {
			query.setParameter("endTime", endTime);
		}
		return Lists.newArrayList(query.getResultList());
	}

}
