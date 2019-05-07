package com.langmy.terminal.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Operater;

/**
 * 操作员DAO接口
 * 
 * @author lin
 */
public interface OperaterDao extends BaseDao<Operater> {
	/**
	 * 根据用户名查询
	 * 
	 * @param operName
	 *            用户名
	 * @return
	 */
	public Operater findByOperNameAndDelFlagFalse(String operName);

	/**
	 * 根据身份证号查询
	 * 
	 * @param idCard
	 *            身份证号
	 * @return
	 */
	public List<Operater> findByIdCardAndDelFlagFalse(String idCard);

	/**
	 * 根据id查询操作员
	 * 
	 * @param id
	 * @return
	 */
	public Operater findById(Integer id);

	/**
	 * 启用多个操作员、记录启用时间
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Operater oper set oper.enableFlag=1,oper.lastEnable= NOW() where oper.id in (:ids)")
	@Modifying
	public int enable(@Param("ids") List<Integer> ids);

	/**
	 * 禁用多个操作员、记录禁用时间
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Operater oper set oper.enableFlag=0,oper.lastDisable = NOW() where oper.id in (:ids) ")
	@Modifying
	public int disable(@Param("ids") List<Integer> ids);

	/**
	 * 获得未删除的操作员
	 * 
	 * @return List<Operater>
	 */
	public List<Operater> findByDelFlagFalse();

	/**
	 * 根据type查询
	 * 
	 * @param type
	 * @return
	 */
	public List<Operater> findByDelFlagFalseAndType(int type);

	/**
	 * 逻辑删除多个操作员
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Operater oper set oper.delFlag=1 where oper.id in (:ids) ")
	@Modifying
	public int softdelete(@Param("ids") List<Integer> ids);

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<Operater> findByNameLikeAndType(String name, int type);

	/**
	 * 根据name模糊查询
	 * 
	 * @param name
	 *            操作员姓名
	 * @return List<Operater>
	 */
	public List<Operater> findByNameLike(String name);

	/**
	 * 根据operName模糊查询
	 * 
	 * @param operName
	 *            操作员账户名
	 * @return List<Operater>
	 */
	public List<Operater> findByOperNameLike(String operName);

}
