package com.langmy.terminal.modules.device.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.Bayonet;

/**
 * 卡口的DAO层
 * @author LuZixiang
 *
 */
public interface BayonetDao extends BaseDao<Bayonet>{
	
	/**
	 * 根据传入的卡口ID找到卡口
	 * @param bayonetId 卡口ID(String)
	 * @return
	 */
	public Bayonet findByBayonetId (String bayonetId);
	
	/**
	 * 根据传入的卡口类型找到卡口列表
	 * @param type 卡口类型。出口/入口
	 * @return
	 */
//	public List<Bayonet> findByType(int type);
/*	@Query("from Bayonet rule where rule.type= :type ")
	public List<Bayonet> getBayonetByType(@Param("type")Integer type);*/
	
	/**
	 * 根据传入的卡口类型和卡口名称找到卡口列表
	 * @param type 卡口类型。出口/入口
	 * @param name 卡口名称 %12%
	 * @return
	 */
//	public List<Bayonet> findByTypeAndNameContaining(int type,String name);
/*	@Query("from Bayonet rule where rule.type= :type and rule.name like :name")
	public List<Bayonet> getBayonetByName(@Param("type")Integer type,@Param("name")String name);*/
	
	
	/**
	 * 根据传入的卡口名称找到卡口列表
	 * @param name 卡口模糊名称
	 * @return
	 */
	public List<Bayonet> findByNameLikeAndDelFlagFalse(String name);
	
	/**
	 * 根据传入的唯一卡口名称找到卡口
	 * @param name 卡口精确名称
	 * @return
	 */
	public Bayonet findByName(String name);
	
	/**
	 * 逻辑删除多个卡口
	 * 
	 * @param ids
	 * @return
	 */
	@Query("update Bayonet bayonet set bayonet.delFlag=1 where bayonet.id in (:ids) ")
	@Modifying
	public int softdelete(@Param("ids") List<Integer> ids);
	
	/**
	 * 获得未删除的卡口
	 * 
	 * @return List<Bayonet>
	 */
	public List<Bayonet> findByDelFlagFalse();
	
	/**
	 * 根据停车场Id获得对应的卡口列表
	 * @param id 停车场Id
	 * @return
	 */
	public List<Bayonet> findByDelFlagFalseAndParkId(int id);
}
