package com.langmy.terminal.modules.caradmission.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.ParkingRec;

/**
 * 停车记录Dao
 * 
 * @author ZZD
 *
 */
public interface ParkingRecDao extends BaseDao<ParkingRec> {
	/**
	 * 根据车位Id查询当前停的停车记录，同时要求只有入信息而无出信息,并且要取最近一次的记录
	 * 
	 * @param pspId
	 * @return
	 */
	@Query("from ParkingRec p where p.PSp.pspId = :pspId and p.outTime IS NULL order by p.inTime desc")
	public List<ParkingRec> getCurrentParkingRecsByPspId(
			@Param("pspId") String pspId);
}
