package com.langmy.terminal.modules.device.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.PSpLock;


/**
 * 车位锁的DAO层
 * @author LuZixiang
 *
 */
public interface LockDao extends BaseDao<PSpLock> {

	/**
	 * 启用规则、记录启用时间
	 * @param id 规则的主键
	 * @return
	 */
	@Query("update PSpLock rule set rule.lockState=1 where rule.id= :id and rule.lineState = 1")
	@Modifying
	public int enable(@Param("id")int id);
	
	/**
	 * 启用多个规则、记录启用时间
	 * @param ids 规则的主键
	 * @return
	 */
	@Query("update PSpLock rule set rule.lockState=0 where rule.id in (:ids) and rule.lineState = 1")
	@Modifying
	public int enable(@Param("ids")List<Integer> ids);
	
	/**
	 * 禁用规则、记录禁用时间
	 * @param id 规则的主键
	 * @return
	 */
	@Query("update PSpLock rule set rule.lockState=1 where rule.id= :id and rule.lineState = 1")
	@Modifying
	public int disable(@Param("id")int id);
	
	/**
	 * 禁用多个规则、记录禁用时间
	 * @param ids 规则的主键 
	 * @return
	 */
	@Query("update PSpLock rule set rule.lockState=0 where rule.id in (:ids) and rule.lineState = 1")
	@Modifying
	public int disable(@Param("ids")List<Integer> ids);
	
	//根据ids获取多个车位锁实体的方法
	@Query("from PSpLock rule where rule.id in (:ids) ")
	public List<PSpLock> getLockByIds(@Param("ids")List<Integer> ids);
	
	/**
	 * 根据传入的车位ID查找车位锁
	 * @param pspId 车位ID
	 * @return 车位锁
	 */
	@Query("select psp.PSpLock from PSp psp where psp.pspId =:pspId ")
	public PSpLock getLockByPspId(@Param("pspId")String pspId);
	
	/**
	 * 根据传入的车位锁ID查找车位锁
	 * @param lockId 车位锁ID
	 * @return 车位锁
	 */
	public PSpLock findByLockId (String lockId);
	
	/**
	 * 逻辑删除多个显示屏
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update PSpLock pspLock set pspLock.delFlag=1 where pspLock.id in (:ids) ")
	@Modifying
	public int softdelete(@Param("ids") List<Integer> ids);
	
	/**
	 * 获得未删除的 车位锁
	 * 
	 * @return List<PSpLock>
	 */
	public List<PSpLock> findByDelFlagFalse();
	
	/**
	 * 逻辑删除单个车位锁
	 * 
	 * @param id
	 * @return
	 */
	@Query("update PSpLock pspLock set pspLock.delFlag=1 where pspLock.id =:id ")
	@Modifying
	public int softdelete(@Param("id")int id);
}
