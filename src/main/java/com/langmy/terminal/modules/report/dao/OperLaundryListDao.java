package com.langmy.terminal.modules.report.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.AdminssionRec;
import com.langmy.terminal.common.entity.ParkingRec;

/**
 * 值班流水账Dao
 * 
 * @author ZZD
 *
 */
public interface OperLaundryListDao extends BaseDao<AdminssionRec> {
	/**
	 * 根据车牌查询出入记录中匹配该车牌的记录，同时要求只有入场信息而无出场信息,并且要取最近一次的记录
	 * 
	 * @param licensePlate
	 * @return
	 */
	@Query("from AdminssionRec ad where ad.car.licensePlate = :licensePlate and ad.outTime IS NULL order by ad.inTime desc")
	public List<AdminssionRec> findEnterAdmissionRec(
			@Param("licensePlate") String licensePlate);

	/**
	 * 查找所有正在车场内部的车的出入记录 以便拿到车的记录
	 * 
	 * @return
	 */

	@Query("from AdminssionRec ad where ad.outTime IS NULL")
	public List<AdminssionRec> findParkingCar();

	/**
	 * 获得停车记录 根据adminssionId查询
	 * 
	 * @param adminssionId
	 *            出入场记录id
	 * @return
	 */
	@Query("from ParkingRec p where p.adminssionRec.id = :adminssionId")
	public List<ParkingRec> findParkingRecByAdmission(
			@Param("adminssionId") Integer adminssionId);

}
