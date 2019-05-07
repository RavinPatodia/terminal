package com.langmy.terminal.modules.device.utils;

import java.io.IOException;
import java.net.UnknownHostException;

import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.model.PSpLockModel;
import com.langmy.terminal.modules.device.service.LockService;

/**
 * 车位锁对外的接口
 * @author LuZixiang
 *
 */
public class LockUtils {
	private static LockService lockService = SpringContextHolder
			.getBean(LockService.class);


	/**
	 * 根据传入的车位ID打开对应的车位锁
	 * @param pspPK 车位ID
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean  openLockById(Integer pspPK) throws UnknownHostException, IOException {
		return lockService.openOneLockById(pspPK);
	}
	
	/**
	 * 根据传入的车位ID打开对应的车位锁
	 * @param pspPK 车位ID
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static boolean  closeLockById(Integer pspPK) throws UnknownHostException, IOException {
		return lockService.closeOneLockById(pspPK);
	}
	
	public static PSpLockModel getModelById(String lockId) throws UnknownHostException, IOException{
		return lockService.getPSpLockByLockId(lockId);
	}
	
	public static boolean deleteLockById(int id){
		return lockService.delete(id);
	}
}
