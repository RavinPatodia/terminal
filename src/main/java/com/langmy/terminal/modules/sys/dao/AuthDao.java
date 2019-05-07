package com.langmy.terminal.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Auth;


/**
 * 资源DAO接口
 * 
 * @author Lin
 */
public interface AuthDao extends BaseDao<Auth> {

	/**
	 * 根据parentIds查找资源
	 * @param parentIds
	 * @return
	 */
	public List<Auth> findByParentIdsLike(String parentIds);
	
	/**
	 * 根据id查找资源
	 * @param id
	 * @return
	 */
	public Auth getById(Integer id);
	
	/**
	 * 根据操作员id查找所拥有的资源，并根据sort降序排列
	 * @param operId
	 * @return
	 */
	@Query("select distinct a from Auth a,Role r,Operater o "+
			"where a in elements(r.authList) and r in elements(o.roleList) and o.id = :operId order by sort DESC")
	public List<Auth> findByOperId(@Param("operId")Integer operId);
	
	/**
	 * 根据资源id查找子节点
	 * @param menuId
	 * @return
	 */
	@Query("select distinct a from Auth a where a.parentId = :menuId and a.isShow = 1")
	public List<Auth> findChildByMenuId(@Param("menuId")Integer menuId);
	
	/**
	 * 依据sort降序排列所有资源
	 * @return
	 */
	@Query("select distinct a from Auth a where 1 = 1 and a.type= 1 order by sort DESC")
	public List<Auth> findTerminalAuthList();
	
	/**
	 * 根据parentId查找资源
	 * @param parentId
	 * @return
	 */
	public List<Auth> findByParentId(Integer parentId);

}
