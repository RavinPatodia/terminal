package com.langmy.terminal.modules.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.User;

/**
 * 客户Dao
 * 
 * @author ZZD
 *
 */
public interface UserDao extends BaseDao<User> {

	/**
	 * 获取最大的id
	 * 
	 * @return
	 */
	@Query("select max(u.id) from User u")
	public Integer getMaxId();

	/**
	 * 获得未加入黑名的客户
	 * 
	 * @return
	 */
	@Query("from User u where u.id not in( select b.user.id from Blacklist b where b.isEffect='"
			+ 1 + "') and u.state < 4 or u.state > 4")
	public List<User> getUserNotBlack();

	/**
	 * 获得未加入黑名单的客户
	 * 
	 * @param uacc
	 *            客户名
	 * @return
	 */
	@Query("from User u where u.id not in( select b.user.id from Blacklist b where b.isEffect='"
			+ 1 + "') and u.name like :name and u.state < 4 or u.state > 4")
	public List<User> getUserNotBlack(@Param("name") String name);

	/**
	 * 获得已加入黑名单客户
	 * 
	 * @param uacc
	 *            客户名
	 * @return
	 */
	@Query("from User u where u.name like :name and u.id in (select b.user.id from Blacklist b)")
	public List<User> getBlackNames(@Param("name") String name);

	/**
	 * 获得已加入黑名单客户
	 * 
	 * @return
	 */
	@Query("from User u where u.id in (select b.user.id from Blacklist b)")
	public List<User> getBlackNames();

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update User u set u.state=4 where u.id in (:ids)")
	@Modifying
	public int del(@Param("ids") List<Integer> ids);

	/**
	 * 根据车牌查询客户
	 * 
	 * @param licensePlate
	 * @return
	 */
	@Query("from User u where u.id= (select c.user.id from Car c where c.licensePlate = :licensePlate)")
	public User findBylicensePlate(@Param("licensePlate") String licensePlate);

	/**
	 * 根据所属客户组类型查询
	 * 
	 * @param ugroupType
	 * @return
	 */
	@Query("from User u where u.UGroup.type = :ugroupType and u.state < 4 or u.state >4")
	public List<User> findByUgroupType(@Param("ugroupType") int ugroupType);

	public List<User> findByNameLikeAndStateNot(String name, Integer state);

	public List<User> findByIdCardAndStateNot(String idCard, Integer state);

}
