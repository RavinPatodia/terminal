package com.langmy.terminal.modules.charge.utils;

import java.util.List;

import org.springframework.stereotype.Service;

import com.langmy.terminal.common.entity.ChargeRec;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.ChargeRecDao;

/**
 * 收费记录接口类
 * @author lzy
 */
@Service
public class ChargeRecUtils {

	private static ChargeRecDao chargeRecDao = (ChargeRecDao) SpringContextHolder
			.getBean(ChargeRecDao.class);

	/**
	 * 保存收费记录
	 * 
	 * @param chargeRec
	 * @return
	 */
	public static ChargeRec saveChargeRec(ChargeRec chargeRec) {
		return chargeRecDao.save(chargeRec);
	}

	/**
	 * 根据出入场记录编号id找到相应的收费记录
	 * 
	 * @param admissionRecId
	 *            出入场记录编号
	 * @return
	 */
	public static List<ChargeRec> findByAdmissionRec(Integer admissionRecId) {
		return chargeRecDao.findByAdminssionRecId(admissionRecId);
	}

}
