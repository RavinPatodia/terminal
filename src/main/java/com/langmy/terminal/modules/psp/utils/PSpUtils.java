package com.langmy.terminal.modules.psp.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.google.protobuf.ServiceException;
import com.langmy.terminal.common.entity.Area;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.LedScreen;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.PSpLock;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.utils.DeviceUtils;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.service.PSpService;

/**
 * 车位的utils
 * 
 * @author MC
 *
 */
/**
 * @author MC
 *
 */
public class PSpUtils {
	private static PSpDao PSpDao = SpringContextHolder.getBean(PSpDao.class);
	private static PSpService pspService = SpringContextHolder
			.getBean(PSpService.class);

	/**
	 * 根据id获取车位的实体
	 * 
	 * @param id
	 * @return PSp
	 */
	public static PSp findPSpById(Integer id) {
		return PSpDao.findOne(id);
	}

	/**
	 * 根据车位编号获取车位的收费规则
	 * 
	 * @param pspId
	 * @return ChargeRule
	 */
	public static String findPSpByLockId(int lockId) {
		PSp p = PSpDao.findByPspLockId(lockId);
		return p.getPspId();
	}

	/**
	 * 更改当前车位上车辆的方法
	 * 
	 * @param licensePlate
	 * @param id
	 * @return
	 */
	public static int updatePsp(String licensePlate, int id) {
		return PSpDao.updatePspCar(licensePlate, id);
	}

	/**
	 * 根据Id获取psp
	 * 
	 * @param id
	 * @return
	 */
	public static PSp getPsp(int id) {
		return PSpDao.findOne(id);
	}

	public static void updatePspByArea(Area area) {
		List<PSp> pSps = PSpDao.findByArea(area.getId());
		for (PSp pSp : pSps) {
			pSp.setArea(null);
			PSpDao.save(pSp);
		}
	}

	/**
	 * 根据id获取车位的list
	 * 
	 * @param id
	 * @return List<PSp>
	 */
	public static List<PSp> getPspByAreaId(int id) {
		return PSpDao.getPspByAreaId(id);
	}

	/**
	 * 获取所有车位ID
	 * 
	 * @return
	 */
	public static List<BaseModel> getAllPspIds() {
		return pspService.getAllPsp();
	}

	/**
	 * 获取所有未绑定摄像头的车位ID
	 * 
	 * @return
	 */
	public static List<BaseModel> getAllPspIdsWithOutCamera() {
		return pspService.getAllPspWherePspWithOutCamera();
	}

	/**
	 * 获取所有未绑定区域的车位
	 * 
	 * @return
	 */
	public static List<BaseModel> getAllPspIdsByArea() {
		return pspService.getAllPspByArea();
	}

	/**
	 * 获取所有未绑定车位锁的车位
	 * 
	 * @param pspId
	 * @return
	 */
	public static List<BaseModel> getAllPspIdsByPspLock(String pspId) {
		return pspService.getAllPspByPspLock(pspId);
	}

	/**
	 * 获取所有未没有车辆的车位
	 * 
	 * @param pspId
	 * @return
	 */
	public static List<BaseModel> getAllPspWherePspInAndOut(String pspId) {
		return pspService.getAllPspWherePspInAndOut(pspId);
	}

	/**
	 * 根据pspID获取车位
	 * 
	 * @param pspId
	 * @return
	 */
	public static List<BaseModel> getPspBypspId(String pspId) {
		return pspService.getPspBypspId(pspId);
	}

	/**
	 * 更改当前车位的车位锁状态
	 * 
	 * @param pSpLock
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws com.langmy.terminal.common.exception.ServiceException
	 */
	public static boolean updatePspLock(PSpLock pSpLock, int id)
			throws ServiceException,
			com.langmy.terminal.common.exception.ServiceException {
		return pspService.updatePspLock(id, pSpLock);
	}

	/**
	 * 根据车位编号查找psp
	 * 
	 * @param pspId
	 * @return
	 */
	public static PSp findPSpByPSpId(String pspId) {
		return PSpDao.findByPspId(pspId);
	}

	public static boolean updatePspIsRent(int pspPK) {
		PSp pSp = PSpDao.findOne(pspPK);
		pSp.setIsRent(false);
		if (PSpDao.save(pSp) == null) {
			return false;
		}
		return true;
	}

	/**
	 * 车辆进入车位
	 * 
	 * @param pspId
	 * @param car
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void carInPsp(String pspId, Car car)
			throws UnknownHostException, IOException {
		pspService.changePspCarIn(pspId, car);
		String licensePlate = car.getLicensePlate();
		ArrangeUtils.updatePspCar(pspId, licensePlate);
		PSp psp = PSpDao.findByPspId(pspId);
		Area area = psp.getArea();
		if (area != null) {
			int areaPK = area.getId();
			List<LedScreen> ledScreens = AreaUtils.findAreaById(areaPK)
					.getLedScreens();
			if (ledScreens.size() > 0) {
				for (LedScreen ledScreen : ledScreens) {
					String ledScreenId = ledScreen.getLedId();
					DeviceUtils.changePspNum(ledScreenId);
				}
			}
		}
	}

	/**
	 * 车辆出车位
	 * 
	 * @param pspId
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void carOutPsp(String pspId) throws UnknownHostException,
			IOException {
		pspService.changePspCarOut(pspId);
		ArrangeUtils.updatePspCar(pspId, "");
		PSp psp = PSpDao.findByPspId(pspId);
		Area area = psp.getArea();
		if (area != null) {
			int areaPK = area.getId();
			List<LedScreen> ledScreens = AreaUtils.findAreaById(areaPK)
					.getLedScreens();
			if (ledScreens.size() > 0) {
				for (LedScreen ledScreen : ledScreens) {
					String ledScreenId = ledScreen.getLedId();
					DeviceUtils.changePspNum(ledScreenId);
				}
			}
		}
	}

	public static boolean updatePspByLockPK(int lockPK) {
		PSp psp = PSpDao.updatePspByLock(lockPK);
		if (psp == null) {
			return false;
		}
		psp.setPSpLock(null);
		if (PSpDao.save(psp) == null) {
			return false;
		}
		return true;
	}

	public static void updatePspAreaByAreaAndPspPK(Area area, int pspPk) {
		PSp pSp = PSpDao.findOne(pspPk);
		pSp.setArea(area);
		PSpDao.save(pSp);
	}

	public static Integer getPspNumWherePpsCarIsNotNull() {
		return PSpDao.getPspWhereCarIsNOTNull().size();
	}
}
