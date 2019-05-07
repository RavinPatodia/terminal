package com.langmy.terminal.modules.charge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.persistence.IdEntity;

@NoRepositoryBean
public interface BaseRuleDao<T extends IdEntity> extends BaseDao<T>{
	/**
	 * 将相应的规则逻辑删除
	 * @param id 规则的主键
	 * @return
	 */
	@Query("update #{#entityName} rule set rule.delFlag=1 where rule.id in (:ids) ")
	@Modifying
	public int del(@Param("ids") List<Integer> ids);
	
	/**
	 * 将相应的规则逻辑删除
	 * @param id 规则的主键
	 * @return
	 */
	@Query("update #{#entityName} rule set rule.delFlag=1 where rule.id =:id ")
	@Modifying
	public int del(@Param("id")int id);
	
	
}
