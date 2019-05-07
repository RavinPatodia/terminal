package com.langmy.terminal.modules.device.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Area;
import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.entity.LedScreen;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.LedScreenDao;
import com.langmy.terminal.modules.device.model.AreaModel;
import com.langmy.terminal.modules.device.model.LedScreenModel;
import com.langmy.terminal.modules.device.utils.DeviceUtils;
import com.langmy.terminal.modules.psp.utils.AreaUtils;

/**
 * 卡口model和实体类之间的转换
 * @author LuZixiang
 *
 */
public class LedScreenExtend extends BaseExtend{
	private static LedScreenDao ledScreenDao = SpringContextHolder.getBean("ledScreenDao");
	private final static Integer AREA_SCREEN = 1;			// 设备类型:终端机
	
	public static LedScreenDao getLedScreenDao() {
		return ledScreenDao;
	}

	public static void setLedScreenDao(LedScreenDao ledScreenDao) {
		LedScreenExtend.ledScreenDao = ledScreenDao;
	}
	
	/**
	 * 卡口实体类到model类的转换
	 * @param ledScreens
	 * @return
	 */
	public static List<BaseModel> coverToModel(List<LedScreen> ledScreens){
		List<LedScreenModel> ledScreenModels = Lists.newArrayList();
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "dataMaster.name");
		map.put("targetPro1", "dataMasterName");
		if (ListUtils.isNullOrEmpty(ledScreens)) {
			return Lists.newArrayList(ledScreenModels);
		}
		try {
			ledScreenModels = BeanUtils.copyListProperties(ledScreens,LedScreenModel.class,map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			System.out.println(e);
		}
		return Lists.newArrayList(ledScreenModels);
	}

	/**
	 * 获取单个model
	 * @param ledScreen
	 * @return
	 */
	public static LedScreenModel getModelByLedScreen(LedScreen ledScreen) {
		LedScreenModel model = new LedScreenModel();
		try {
			BeanUtils.copyProperties(ledScreen, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("硬件模块-显示屏管理-实体类与Model类转化失败",e);
			return null;
		}
		if(ledScreen.getDataMaster()!=null){
			model.setDataMasterName(ledScreen.getDataMaster().getName());
			model.setDataMasterId(ledScreen.getDataMaster().getId());
		}
		
		if(ledScreen.getType()==AREA_SCREEN){
			String areaIds = "";
			List<AreaModel> areaModelsInGroup =Lists.newArrayList();
			List<AreaModel> areaModelsNotInGroup = Lists.newArrayList();		
			if(ledScreen.getAreas().size()!=0){
				List<Area> areaList = ledScreen.getAreas();
				List<Integer> ids = Lists.newArrayList();
				for (Area a : areaList) {
					areaIds += a.getName() + ",";
					ids.add(a.getId());
				}
				areaModelsInGroup = AreaExtend.coverToModel(areaList);
				areaModelsNotInGroup = AreaExtend.coverToModel(AreaUtils.findAreaByIdNotIn(ids));
			}
			else{
				areaModelsNotInGroup = AreaExtend.coverToModel(AreaUtils.findAllArea());
			}
			model.setAreaModelsInGroup(areaModelsInGroup);
			model.setAreaModelsNotInGroup(areaModelsNotInGroup);
			model.setAreaNames(areaIds);
		}
		return model;
	}

	public static LedScreen covertToEntity(LedScreenModel model){
		LedScreen ledScreen = new LedScreen();
		try {
			BeanUtils.copyProperties(model, ledScreen);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("硬件模块-显示屏管理-实体类与Model类转化失败",e);
			return null;
		}
		if(model.getDataMasterId()!=null){
			Device dataMaster = DeviceUtils.getDeviceById(model.getDataMasterId());
			ledScreen.setDataMaster(dataMaster);
		}
		if(model.getType()==AREA_SCREEN){
			List<String> areaIdList = model.getAreaSelect();
			if(areaIdList ==null){
				ledScreen.setAreas(null);
			}else{
				List<Integer> ids = Lists.newArrayList();
				for(String i : areaIdList){
					ids.add(Integer.parseInt(i));
				}
				List<Area> areas = AreaUtils.findAreaByIdIn(ids);
				ledScreen.setAreas(areas);
			}
		}
		return ledScreen;
	}
	
}
