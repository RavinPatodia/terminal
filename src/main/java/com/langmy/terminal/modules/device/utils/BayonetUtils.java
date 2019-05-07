package com.langmy.terminal.modules.device.utils;

import java.util.List;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Bayonet;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.BayonetDao;
import com.langmy.terminal.modules.device.service.BayonetService;
import com.langmy.terminal.modules.device.service.extend.BayonetExtend;

/**
 * 卡口对外的接口
 * @author LuZixiang
 *
 */
public class BayonetUtils {
	private static BayonetDao bayonetDao = SpringContextHolder
			.getBean(BayonetDao.class);
	private static BayonetService bayonetService = SpringContextHolder
			.getBean(BayonetService.class);

	/**
	 * 根据卡口ID(string) 得到卡口信息
	 * @param bayonetId
	 * @return
	 */
	public static Bayonet getBayonet(String bayonetId) {
		return bayonetDao.findByBayonetId(bayonetId);
	}

	/**
	 * 根据卡口ID(int) 得到卡口信息
	 * @param id
	 * @return
	 */
	public static Bayonet getBayonetById(Integer id) {
		return bayonetDao.findOne(id);
	}
	
	/**
	 * 根据卡口名称(String) 得到卡口信息
	 * @param id
	 * @return
	 */
	public static Bayonet getBayonetByName(String name) {
		return bayonetDao.findByName(name);
	}


	/**
	 * 得到所有的卡口
	 * @return
	 */
	public static List<BaseModel> getAll() {
		return bayonetService.getAll();
	}

	/**
	 * 根据传入的卡口名称 得到所有的卡口
	 * @param name
	 * @return
	 */
	public static List<BaseModel> getAllByName(String name) {
		return bayonetService.getAllByName(name);
	}
	
	/**
	 * 根据传入的卡口类型和名称 得到卡口列表
	 * @param type 卡口类型
	 * @param name 卡口名称
	 * @return
	 */
	public static List<BaseModel> getBayonetByType(Integer type, String name) {
		List<Bayonet> bayonets = Lists.newArrayList();
		/*if (StringUtils.isNotNullAndEmpty(name)) {
			bayonets = bayonetDao.findByTypeEqualAndNameLike(type, "%" + name + "%");
		} else {
			bayonets = bayonetDao.findByType(type);
		}*/
		return BayonetExtend.coverToModel(bayonets);
	}

	public static List<BaseModel> findByDelFlagFalseAndParkId(int id){
		return bayonetService.findByDelFlagFalseAndParkId(id);
	}
}
