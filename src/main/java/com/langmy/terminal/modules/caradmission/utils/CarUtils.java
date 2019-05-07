package com.langmy.terminal.modules.caradmission.utils;

import java.util.List;

import com.langmy.terminal.common.config.Constant.UserGroupType;
import com.langmy.terminal.common.entity.Blacklist;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.caradmission.dao.CarDao;
import com.langmy.terminal.modules.caradmission.service.CarService;
import com.langmy.terminal.modules.user.utils.BlacklistUtils;

/**
 * 车辆管理Util
 * 
 * @author ZZD
 *
 */
public class CarUtils {
	private static CarDao carDao = SpringContextHolder.getBean(CarDao.class);
	private static CarService carService = SpringContextHolder
			.getBean(CarService.class);

	/**
	 * 根据车牌号码查询
	 * 
	 * @param licensePlate
	 * @return
	 */
	public static Car getCarByLicensePlate(String licensePlate) {
		return carDao.findByLicensePlateAndDelFlagFalse(licensePlate);
	}

	/**
	 * 根据id获得车辆
	 * 
	 * @param id
	 *            车辆id
	 * @return
	 */
	public static Car getCarById(Integer id) {
		return carDao.findOne(id);
	}

	/**
	 * 保存车辆
	 * 
	 * @param car
	 * @return
	 */
	public static Car saveCar(Car car) {
		return carDao.save(car);
	}

	/**
	 * 获得车牌号码
	 * 
	 * @param licensePlate
	 * @return
	 */
	public static List<BaseModel> getLicensePlates(String licensePlate) {
		return carService.getLicensePlates(licensePlate);
	}

	public static int getUgTypeByLicensePlat(String licensePlate) {
		return carDao.getUgTypeByLicensePlat(licensePlate);
	}

	public static boolean delete(int id) {
		return carService.delete(id);
	}

	public static List<Car> findByUserId(int userId) {
		return carDao.findByUserIdAndDelFlagFalse(userId);
	}

	/**
	 * 根据车牌号码判断用户是否是黑名单
	 * 
	 * @param licensePlate
	 *            车牌号码
	 * @return
	 */
	public static boolean isUserBlack(String licensePlate) {
		Car car = carDao.findByLicensePlateAndDelFlagFalse(licensePlate);
		if (car == null) {
			return false;
		} else {
			User user = car.getUser();
			if (user == null) {
				return false;
			} else {
				Blacklist blackList = BlacklistUtils.getEffectBlacklist(user);
				if (blackList == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 根据车牌号码获得生效的黑名单
	 * 
	 * @param licensePlate
	 *            车牌号码
	 * @return
	 */
	public static Blacklist getBlackList(String licensePlate) {
		Car car = carDao.findByLicensePlateAndDelFlagFalse(licensePlate);
		Blacklist blackList = null;
		if (car == null) {
			return null;
		} else {
			User user = car.getUser();
			if (user == null) {
				return null;
			} else {
				blackList = BlacklistUtils.getEffectBlacklist(user);
				if (blackList == null) {
					return null;
				}
			}
		}
		return blackList;
	}

	/**
	 * 根据车牌来判断用户是什么类型
	 * 
	 * @param licensePlate
	 * @return
	 */
	public static Integer getUserTypeByLicensePlate(String licensePlate) {
		Car car = carDao.findByLicensePlateAndDelFlagFalse(licensePlate);
		if (car == null) {
			return UserGroupType.TEMPORARYUSER.getValue();
		}
		User user = car.getUser();
		if (user == null) {
			return UserGroupType.TEMPORARYUSER.getValue();
		}
		UGroup ugroup = user.getUGroup();
		return ugroup.getType();
	}
}
