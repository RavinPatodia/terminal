package com.langmy.terminal.modules.psp.service.extend;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Area;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.model.AreaModel;
import com.langmy.terminal.modules.psp.model.PSpModel;

public class AreaExtend extends BaseExtend{
	private static  PSpDao pSpDao= SpringContextHolder.getBean(PSpDao.class); 
	
	public static AreaModel getModelByArea(Area area) {
		AreaModel model = new AreaModel();
		try {
			BeanUtils.copyProperties(area, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("实体类与Model类转化失败", e);
			return null;
		}
		Set<PSp> psp = area.getPSps();
		List<PSp> pspList = Lists.newArrayList();
		String pspIds = "";
		for (PSp p : psp) {
			if(p.isDelFlag()==true){
				continue ;
			}
			pspIds += p.getPspId() + ",";
			pspList.add(p);
		}

		List<PSpModel> pspModelsInGroup = PspExtend.coverToModel(pspList);
		List<PSpModel> pspModelsNotInGroup;
		pspModelsNotInGroup = PspExtend.coverToModel(pSpDao
				.getPspWhereAreaEmpty());

		model.setPspModelsInGroup(pspModelsInGroup);
		model.setPspModelsNotInGroup(pspModelsNotInGroup);
		model.setPspId(pspIds);

		return model;
	}
	
}
