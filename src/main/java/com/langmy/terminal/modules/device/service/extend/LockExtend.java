package com.langmy.terminal.modules.device.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.PSpLock;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.LockDao;
import com.langmy.terminal.modules.device.model.PSpLockModel;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

/**
 * 车位锁model和实体类之间的转换
 * @author LuZixiang
 *
 */
public class LockExtend extends BaseExtend{
	private static LockDao lockDao = SpringContextHolder.getBean("lockDao");

	public static LockDao getLockDao() {
		return lockDao;
	}

	public static void setLockDao(LockDao lockDao) {
		LockExtend.lockDao = lockDao;
	}
	
	/**
	 * lock 实体类到model的转换
	 * @param pSpLock
	 * @return
	 */
	public static List<BaseModel> coverToModel(List<PSpLock> pSpLock) {
		List<PSpLockModel> pSplockModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(pSpLock)) {
			return Lists.newArrayList(pSplockModels);
		}
		
		Map<String, String> map = Maps.newHashMap();
		
		try {
			
			pSplockModels = BeanUtils.copyListProperties(pSpLock,
					PSpLockModel.class,map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("硬件管理-车位锁管理-实体类与Model类转化失败", e);
		}
		for(int i=0;i<pSplockModels.size();i++){
			int lockId = pSplockModels.get(i).getId();
			String pspId = PSpUtils.findPSpByLockId(lockId);
			pSplockModels.get(i).setPspId(pspId);
		}
		return Lists.newArrayList(pSplockModels);
	}
	
	/**
	 * 单个Lock到model的转换
	 * @param lock
	 * @return
	 */
	public static PSpLockModel getModelByLock(PSpLock lock) {
		PSpLockModel model = new PSpLockModel();
		if(lock!=null){
			try {
				BeanUtils.copyProperties(lock, model);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("实体类与Model类转化失败",e);
				return null;
			}
			model.setPspId(model.getLockId());
			return model;
		}
		else return null;
	}
}
