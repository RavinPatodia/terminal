package com.langmy.terminal.modules.caradmission.utils;

import java.util.List;

import com.langmy.terminal.common.entity.AdminssionRec;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.caradmission.dao.AdmissionRecDao;

/**
 * 车辆管理Util
 * 
 * @author ZZD
 *
 */
public class AdmissionRecUtils {
	private static AdmissionRecDao admissionRecDao = SpringContextHolder
			.getBean(AdmissionRecDao.class);

	/**
	 * 保存出入场记录
	 * 
	 * @param admissionRec
	 * @return
	 */
	public static AdminssionRec save(AdminssionRec admissionRec) {
		return admissionRecDao.save(admissionRec);
	}

	/**
	 * 根据车牌号码查找入场信息
	 * 
	 * @param licensePlate
	 * @return
	 */
	public static List<AdminssionRec> findEnterAdmissionRec(String licensePlate) {
		return admissionRecDao.findEnterAdmissionRec(licensePlate);
	}

	/**
	 * 获得所有正在车场内部的车的出入记录
	 * 
	 * @return
	 */
	public static List<AdminssionRec> findParkingCar() {
		return admissionRecDao.findParkingCar();
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public static AdminssionRec findOne(Integer id) {
		return admissionRecDao.findOne(id);
	}

}
