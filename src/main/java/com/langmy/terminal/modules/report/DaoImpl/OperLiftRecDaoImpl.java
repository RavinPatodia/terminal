package com.langmy.terminal.modules.report.DaoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

/**
 * 手动抬杆记录Dao实现类
 * 
 * @author ZZD
 *
 */
@Repository
public class OperLiftRecDaoImpl {
	@PersistenceContext
	private EntityManager em;

	/**
	 * 查询一段时间内每个操作员的抬杆次数
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public List<Object[]> search(Date startTime, Date endTime) {
		StringBuilder hql = new StringBuilder(
				"select IFNULL(u.oper_name,'') operName,IFNULL(u.name,''), IFNULL(sSum, 0) operLiftSum from operater u LEFT JOIN (select r.OPER_ID, IFNULL(count(r.OPER_ID),0) sSum FROM oper_lift_rec r WHERE 1=1");
		if (startTime != null) {
			hql.append(" and r.open_time >= :startTime");
		}
		if (endTime != null) {
			hql.append(" and r.open_time <= :endTime");
		}
		hql.append(" group by r.OPER_ID) rec ON rec.OPER_ID = u.ID");
		Query query = em.createNativeQuery(hql.toString());
		if (startTime != null) {
			query.setParameter("startTime", startTime);
		}
		if (endTime != null) {
			query.setParameter("endTime", endTime);
		}
		return Lists.newArrayList(query.getResultList());
	}

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
				"select r.bayonet_id,r.device_id,r.open_time,r.open_reason,r.oper_id from oper_lift_rec r where 1=1");

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
