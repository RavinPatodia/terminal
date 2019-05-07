package com.langmy.terminal.modules.report.DaoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.utils.StringUtils;

/**
 * 操作员结账记录Dao实现类
 * 
 * @author ZZD
 *
 */
@Repository
public class CharRecDaoImpl {
	@PersistenceContext
	private EntityManager em;

	/**
	 * 获得一段时间内每个操作员收费总额
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public List<Object[]> search(Date startTime, Date endTime) {
		StringBuilder hql = new StringBuilder(
				"select IFNULL(u.oper_name,''), IFNULL(u.name,''),IFNULL(sum(rec.pay),0) from operater u LEFT JOIN (SELECT r.OPER_ID, r.actual_pay  pay FROM charge_rec r where r.pay_type=1");
		if (startTime != null) {
			hql.append(" and r.PAY_TIME >= :startTime");
		}
		if (endTime != null) {
			hql.append(" and r.PAY_TIME <= :endTime");
		}
		hql.append(") rec ON rec.OPER_ID = u.ID group by u.id");
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

	/**
	 * 根据年份查询每个月的收费总额
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public List<Object[]> getActualPayOfMouth(String year) {
		StringBuilder hql = new StringBuilder(
				"select DATE_FORMAT(c.pay_time,'%Y-%m'),sum(c.actual_pay) from charge_rec c where 1=1");
		if (StringUtils.isNotNullAndEmpty(year)) {
			hql.append(" and DATE_FORMAT(c.pay_time,'%Y') = :year");
		}
		hql.append(" group by DATE_FORMAT(c.pay_time,'%Y-%m')");
		Query query = em.createNativeQuery(hql.toString());

		if (StringUtils.isNotNullAndEmpty(year)) {
			query.setParameter("year", year);
		}
		return Lists.newArrayList(query.getResultList());
	}
}
