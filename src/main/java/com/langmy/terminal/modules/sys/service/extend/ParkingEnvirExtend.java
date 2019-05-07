package com.langmy.terminal.modules.sys.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.langmy.terminal.common.entity.ParkingEnvir;
import com.langmy.terminal.common.entity.SmsRec;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.sys.dao.ParkingEnvirDao;
import com.langmy.terminal.modules.sys.dao.SmsRecDao;
import com.langmy.terminal.modules.sys.model.ParkingEnvirModel;
import com.langmy.terminal.modules.sys.model.SmsRecModel;
import com.langmy.terminal.modules.user.utils.UGroupUtils;

/**
 * 停车场运行参数的转化
 * 
 * @author lzy
 *
 */
public class ParkingEnvirExtend extends BaseExtend {
	private static ParkingEnvirDao parkingEnvirDao = SpringContextHolder
			.getBean(ParkingEnvirDao.class);
	private static SmsRecDao smsRecDao = SpringContextHolder
			.getBean(SmsRecDao.class);

	/**
	 * 将短信猫配置的实体类转化成Vo类
	 * 
	 * @param smsRecs
	 *            短信猫配置实体类
	 * @return
	 */
	public static List<SmsRecModel> convertToSmsRecModels(List<SmsRec> smsRecs) {
		List<SmsRecModel> smsRecModels = null;
		try {
			smsRecModels = BeanUtils.copyListProperties(smsRecs,
					SmsRecModel.class);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("短信猫实体类与Vo赋值出错", e);
		}
		return smsRecModels;
	}

	/**
	 * 将短信猫配置的实体类转化成Vo类
	 * 
	 * @param smsRecs
	 *            短信猫配置实体类
	 * @return
	 */
	public static ParkingEnvirModel convertToParkingEnvirModel(
			ParkingEnvir parkingEnvir) {
		ParkingEnvirModel model = new ParkingEnvirModel();
		try {
			BeanUtils.copyProperties(parkingEnvir, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("停车场运行参数实体类与Model之间赋值出错", e);
		}
		model.setUexpirReminder(parkingEnvir.getUExpirReminder());
		model.setPspExpirReminder(parkingEnvir.getPSpExpirReminder());
		model.setCountSpFlag(parkingEnvir.isIsCountSp());
		model.setChargeFlag(parkingEnvir.isIsCharge());
		model.setPspLockFlag(parkingEnvir.isPSpLockFlag());
		UGroup group = UGroupUtils.findOne(parkingEnvir.getDefaultGroup());
		if (group != null) {
			model.setDefaultGroupName(group.getName());
		}
		List<SmsRecModel> smsRecModels = ParkingEnvirExtend
				.convertToSmsRecModels(smsRecDao.findAll());
		model.setSmsRecModels(smsRecModels);
		if (logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(model));
		return model;
	}

	/**
	 * vo转po
	 * 
	 * @param model
	 * @return
	 */
	public static ParkingEnvir convertToEntity(ParkingEnvirModel model) {
		ParkingEnvir parkingEnvir = new ParkingEnvir();
		if (model.getId() != 0) {
			parkingEnvir = parkingEnvirDao.findOne(model.getId());
		}
		try {
			BeanUtils.copyProperties(model, parkingEnvir);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("停车场运行参数model类和实体类赋值出错", e);
		}
		String path = model.getBackupPath();
		// path.replaceAll("\\", File.separator);
		parkingEnvir.setBackupPath(path);
		parkingEnvir.setUExpirReminder(model.getUexpirReminder());
		parkingEnvir.setPSpExpirReminder(model.getPspExpirReminder());
		parkingEnvir.setIsCountSp(model.isCountSpFlag());
		parkingEnvir.setIsCharge(model.isChargeFlag());
		parkingEnvir.setPSpLockFlag(model.isPspLockFlag());
		if ("on".equals(model.getChangeCarFlagStr())) {
			parkingEnvir.setChangeCarFlag(true);
		} else {
			parkingEnvir.setChangeCarFlag(false);
		}
		if ("on".equals(model.getChargeFlagStr())) {
			parkingEnvir.setIsCharge(true);
		} else {
			parkingEnvir.setIsCharge(false);
		}
		if ("on".equals(model.getCountSpFlagStr())) {
			parkingEnvir.setIsCountSp(true);
		} else {
			parkingEnvir.setIsCountSp(false);
		}
		if ("on".equals(model.getPlateRecoCameraFlagStr())) {
			parkingEnvir.setPlateRecoCameraFlag(true);
		} else {
			parkingEnvir.setPlateRecoCameraFlag(false);
		}
		if ("on".equals(model.getPspLockFlagStr())) {
			parkingEnvir.setPSpLockFlag(true);
		} else {
			parkingEnvir.setPSpLockFlag(false);
		}
		if ("on".equals(model.getVideoShowFlagStr())) {
			parkingEnvir.setVideoShowFlag(true);
		} else {
			parkingEnvir.setVideoShowFlag(false);
		}
		if ("on".equals(model.getBackupFlagStr())) {
			parkingEnvir.setBackupFlag(true);
		} else {
			parkingEnvir.setBackupFlag(false);
		}
		return parkingEnvir;

	}

}
