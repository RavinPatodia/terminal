package com.langmy.terminal.modules.psp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.PSpLock;

/**
 * 车位的Dao
 * 
 * @author MC
 *
 */
public interface PSpDao extends BaseDao<PSp> {

	/**
	 * 启用车位、记录启用时间
	 * 
	 * @param id
	 *            车位的主键
	 * @return
	 */
	@Query("update PSp psp set psp.isEnable=1 where psp.id= :id")
	@Modifying
	public int enable(@Param("id") int id);

	/**
	 * 更改当前的状态
	 * 
	 * @param pspId
	 * @return
	 */
	@Query("update PSp psp set psp.nowState=1 where psp.pspId= :pspId")
	@Modifying
	public int updateNowStateOpen(@Param("pspId") String pspId);

	/**
	 * 更改当前的状态
	 * 
	 * @param pspId
	 * @return
	 */
	@Query("update PSp psp set psp.nowState=0 where psp. pspId= :pspId")
	@Modifying
	public int updateNowStateClose(@Param("pspId") String pspId);

	/**
	 * 根据psp的主键id修改当前车位上的车辆信息
	 * 
	 * @param licensePlate
	 * @param id
	 * @return
	 */
	@Query("update PSp psp set psp.car.licensePlate = :licensePlate where psp.id= :id")
	@Modifying
	public int updatePspCar(@Param("licensePlate") String licensePlate,
			@Param("id") int id);

	/**
	 * 根据唯一的pspId修改当前车位是否为被租赁状态
	 * 
	 * @param pspId
	 * @return
	 */
	@Query("update PSp psp set psp.isRent = 1 where psp.pspId= :pspId")
	@Modifying
	public int updatePspIsRent(@Param("pspId") String pspId);

	/**
	 * 根据唯一的主键id获取可用的psp实体
	 * 
	 * @param id
	 * @return
	 */
	public PSp findByIsEnableTrueAndId(int id);

	/**
	 * 更改当前车位的车位锁信息
	 * 
	 * @param PSpLock
	 * @param id
	 * @return
	 */
	@Query("update PSp psp set psp.PSpLock=:PSpLock where psp.id = :id")
	@Modifying
	public int updatePspByLock(@Param("PSpLock") PSpLock PSpLock,
			@Param("id") int id);

	/**
	 * 启用多个车位、记录启用时间
	 * 
	 * @param ids
	 *            车位的主键
	 * @return
	 */
	@Query("update PSp psp set psp.isEnable=1 where psp.id in (:ids)")
	@Modifying
	public int enable(@Param("ids") List<Integer> ids);

	/**
	 * 禁用车位、记录禁用时间
	 * 
	 * @param id
	 *            车位的主键
	 * @return
	 */
	@Query("update PSp psp set psp.isEnable=0 where psp.id= :id ")
	@Modifying
	public int disable(@Param("id") int id);

	/**
	 * 禁用多个车位、记录禁用时间
	 * 
	 * @param ids
	 *            车位的主键
	 * @return
	 */
	@Query("update PSp psp set psp.isEnable=0 where psp.id in (:ids) ")
	@Modifying
	public int disable(@Param("ids") List<Integer> ids);

	/**
	 * 根据车位的pspId禁用 车位
	 * 
	 * @param pspId
	 * @return
	 */
	@Query("update PSp psp set psp.isEnable=0 where psp.pspId= :pspId")
	@Modifying
	public int backupRule(@Param("pspId") String pspId);

	/**
	 * 获取所有未匹配区域的车位
	 * 
	 * @return
	 */
	@Query("from PSp psp where psp.area IS NULL and psp.delFlag=0")
	public List<PSp> getPspWhereAreaEmpty();

	/**
	 * 获取当前车位上没有车位锁的车位
	 * 
	 * @return
	 */
	@Query("from PSp psp where psp.PSpLock IS NULL")
	public List<PSp> getPspByLock();

	/**
	 * 获取当前车位上没有摄像头的车位
	 * 
	 * @return
	 */
	@Query("from PSp psp where psp.camera IS NULL")
	public List<PSp> getAllPspWherePspWithOutCamera();

	/**
	 * 获取当前车位上没有车的车位有参数
	 * 
	 * @param pspId
	 * @return
	 */
	@Query("from PSp psp where psp.pspId like :pspId and psp.car IS NULL")
	public List<PSp> getPspWhereCarIsNull(@Param("pspId") String pspId);

	/**
	 * 获取当前车位上停车的车位数
	 * 
	 * @param pspId
	 * @return
	 */
	@Query("from PSp psp where psp.car IS NOT NULL")
	public List<PSp> getPspWhereCarIsNOTNull();

	/**
	 * 根据lockPK查找车位psp
	 * 
	 * @param lockPK
	 * @return int
	 */
	@Query("from PSp psp where psp.PSpLock.id= :lockPK ")
	public PSp updatePspByLock(@Param("lockPK") int lockPK);

	/**
	 * 根据pspId查找psp.PSpLock为空的车位
	 * 
	 * @param pspId
	 * @return list
	 */
	@Query("from PSp psp where psp.PSpLock IS NULL and psp.pspId like :pspId")
	public List<PSp> getPspByLock(@Param("pspId") String pspId);

	/**
	 * 根据id查找所有id不包含在ids里面的psp实体
	 * 
	 * @param ids
	 * @return
	 */
	List<PSp> findByIdNotIn(List<Integer> ids);

	/**
	 * 根据id查找所有id包含在ids里面的psp实体
	 * 
	 * @param ids
	 * @return
	 */
	List<PSp> findByIdIn(List<Integer> ids);

	/**
	 * 车位入相应的车位组
	 * 
	 * @param ids
	 * @param groupId
	 * @return
	 */
	@Query(value = "UPDATE p_sp p set p.area_id = :areaId where p.id in (:ids)", nativeQuery = true)
	@Modifying
	public int updateAreaId(@Param("ids") List<Integer> ids,
			@Param("areaId") int areaId);

	/**
	 * 根据Id删除车位对应区域
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "UPDATE p_sp psp set psp.area_id = null where psp.area_id =:id", nativeQuery = true)
	@Modifying
	public int deletePspByAreaId(@Param("id") int id);

	/**
	 * 根据区域的id获取所有启用的车位
	 * 
	 * @param id
	 * @return
	 */
	@Query("from PSp psp where psp.area.id =:id and psp.isEnable=1")
	public List<PSp> getPspByAreaId(@Param("id") int id);

	/**
	 * 根据车位的唯一pspId获取车位
	 * 
	 * @param pspId
	 * @return
	 */
	public PSp findByPspId(String pspId);

	@Query("from PSp p where p.PSpLock.id = :lockId")
	public PSp findByPspLockId(@Param("lockId") int lockId);

	@Query("from PSp p where p.area.id = :areaPK")
	public List<PSp> findByArea(@Param("areaPK") int areaPK);

}
