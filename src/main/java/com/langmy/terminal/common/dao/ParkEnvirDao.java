package com.langmy.terminal.common.dao;

import org.springframework.stereotype.Repository;

import com.langmy.terminal.common.entity.ParkingEnvir;

/**
 * 停车场运行环境的Dao
 * @author lzy
 */
@Repository("parkEnvirDao")
public interface ParkEnvirDao extends BaseDao<ParkingEnvir>{
	
}
