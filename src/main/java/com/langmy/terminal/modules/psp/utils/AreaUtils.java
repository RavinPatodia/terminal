package com.langmy.terminal.modules.psp.utils;

import java.util.List;

import com.langmy.terminal.common.entity.Area;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.psp.dao.AreaDao;
import com.langmy.terminal.modules.psp.service.AreaService;

/**
 * 区域的utils
 * 
 * @author MC
 *
 */
public class AreaUtils {
	private static AreaService areaService = SpringContextHolder
			.getBean(AreaService.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);

	/**
	 * 获取区域
	 * 
	 * @return
	 */
	public static List<BaseModel> getAreaId() {
		return areaService.getAreaId();

	}

	/**
	 * 根据name获取区域
	 * 
	 * @param areaId
	 * @return
	 */
	public static List<BaseModel> getAreaIdByName(String areaId) {
		return areaService.getAreaIdByName(areaId);

	}

	/**
	 * 根据区域id获取区域
	 * 
	 * @param id
	 * @return
	 */
	public static Area findAreaById(Integer id) {
		return areaDao.findOne(id);
	}

	/**
	 * 获取所有的区域
	 * 
	 * @return
	 */
	public static List<Area> findAllArea() {
		return areaDao.findAll();
	}
	
	public static List<Area> getAllAreaWhereISNOTDEL(){
		return areaDao.getAllAreaWhereISNOTDEL();
	}

	/**
	 * 获取区域id不在ids里面的区域
	 * 
	 * @param ids
	 * @return
	 */
	public static List<Area> findAreaByIdNotIn(List<Integer> ids) {
		return areaDao.findByIdNotIn(ids);
	}

	/**
	 * 根据传入的Ids来查找id在数组之内的区域
	 * 
	 * @param ids
	 * @return
	 */
	public static List<Area> findAreaByIdIn(List<Integer> ids) {
		return areaDao.findByIdIn(ids);
	}
}
