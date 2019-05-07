package com.langmy.terminal.modules.device.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Bayonet;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.BayonetDao;
import com.langmy.terminal.modules.device.model.BayonetModel;

/**
 * 卡口model和实体类之间的转换
 * @author LuZixiang
 *
 */
public class BayonetExtend extends BaseExtend{
	private static BayonetDao bayonetDao = SpringContextHolder.getBean("bayonetDao");

	public static BayonetDao getBayonetDao() {
		return bayonetDao;
	}

	public static void setBayonetDao(BayonetDao bayonetDao) {
		BayonetExtend.bayonetDao = bayonetDao;
	}
	
	/**
	 * 卡口实体类到model类的转换
	 * @param bayonets
	 * @return
	 */
	public static List<BaseModel> coverToModel(List<Bayonet> bayonets){
		List<BayonetModel> bayonetModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(bayonets)) {
			return Lists.newArrayList(bayonetModels);
		}		
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "park.id");
		map.put("targetPro1", "parkId");
		try {
			bayonetModels = BeanUtils.copyListProperties(bayonets,
					BayonetModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			System.out.println(e);
		}
		return Lists.newArrayList(bayonetModels);
	}

	/**
	 * 获取单个model
	 * @param bayonet
	 * @return
	 */
	public static BayonetModel getModelByBayonet(Bayonet bayonet) {
		BayonetModel model = new BayonetModel();
		model.setId(bayonet.getId());
		model.setBayonetId(bayonet.getBayonetId());
		model.setName(bayonet.getName());
		model.setPosit(bayonet.getPosit());
		if(bayonet.getPark()!=null){
			model.setParkName(bayonet.getPark().getName());
		}
		return model;
	}
}
