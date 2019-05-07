package com.langmy.terminal.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.OperShift;

/**
 * 操作员交接班Dao
 * 
 * @author ZZD
 *
 */
public interface OperShiftDao extends BaseDao<OperShift> {
	/**
	 * 按上班操作员 上班日期查询
	 * 
	 * @param operId
	 * @param workDate
	 * @return
	 */
	@Query(value = "select * from  oper_shift os where os.off_work_oper_id  = :operId and DATE_FORMAT(os.work_time,'%Y-%m-%d')= :workDate", nativeQuery = true)
	public List<OperShift> findByOffWorkOperAndWorkTime(
			@Param("operId") Integer operId, @Param("workDate") String workDate);
}
