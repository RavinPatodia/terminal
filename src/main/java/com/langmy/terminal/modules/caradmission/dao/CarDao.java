package com.langmy.terminal.modules.caradmission.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Car;

/**
 * 车辆管理Dao
 * 
 * @author ZZD
 *
 */
public interface CarDao extends BaseDao<Car> {
	/**
	 * 根据车licensePlate查询
	 * 
	 * @param licensePlate
	 *            车牌号码
	 * @return
	 */
	public Car findByLicensePlateAndDelFlagFalse(String licensePlate);

	/**
	 * 根据车licensePlate模糊查询
	 * 
	 * @param licensePlate
	 *            车牌号码
	 * @return
	 */
	public List<Car> findByLicensePlateLikeAndDelFlagFalse(String licensePlate);

	/**
	 * 根据车userId查询
	 * 
	 * @param userId
	 *            客户id
	 * @return
	 */
	public List<Car> findByUserIdAndDelFlagFalse(int userId);

	/**
	 * 获得未删除的车辆
	 * 
	 * @return
	 */
	public List<Car> findByDelFlagFalse();

	/**
	 * 根据carId查询客户id
	 * 
	 * @param carId
	 *            车辆id
	 * @return
	 */
	@Query("select c.user.id from Car c where c.id = :carId")
	public int findUserIdByCar(@Param("carId") Integer carId);

	/**
	 * 根据licensePlate查询客户组类型
	 * 
	 * @param licensePlate
	 *            车俩号码
	 * @return
	 */
	@Query(value = "select ug.type from u_group ug,user u where u.u_group_id= ug.id and u.id in(select c.user_id from car c where c.license_plate = :licensePlate)", nativeQuery = true)
	public int getUgTypeByLicensePlat(@Param("licensePlate") String licensePlate);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Car c set c.delFlag=1 where c.id in (:ids)")
	@Modifying
	public int del(@Param("ids") List<Integer> ids);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Car c set c.delFlag=1 where c.id = :id")
	@Modifying
	public int del(@Param("id") int id);

}
