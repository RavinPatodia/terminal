package com.langmy.terminal.common.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.langmy.terminal.common.persistence.IdEntity;

/**
 * 所有Dao的父借口
 * @author lzy
 *
 * @param <T> 实体类
 */
@NoRepositoryBean
public interface BaseDao<T extends IdEntity> extends Repository<T, Integer>{
	
	T save(T t);
	
	void delete(Integer id);
	
	public  Iterable<T> save(Iterable<T> entities);
	
	Page<T> findAll(Specification<T> spec, Pageable pageable);
	
	List<T> findAll();
	
	long count(Specification<T> spec);
	
	long count();
	
	T findOne(Integer id);
	
	void delete(T entity);
	
	@Query("from #{#entityName} t where t.id=(select max(id) from #{#entityName})")
	public T findMaxIdRec();
}
