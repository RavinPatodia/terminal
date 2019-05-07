package com.langmy.terminal.modules.sys.utils;

import java.util.List;

import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.entity.ParkingEnvir;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.sys.dao.ParkingEnvirDao;

/**
 * 停车场环境参数设置工具类
 * 
 * @author lxj
 *
 */
public class ParkingEnvirUtils {

	/**
	 * 获取停车场环境运行参数
	 * 
	 * @return parkingEnvir
	 */
	public static ParkingEnvir getParkingEnvir() {
		return Global.getConfig();
	}
}
