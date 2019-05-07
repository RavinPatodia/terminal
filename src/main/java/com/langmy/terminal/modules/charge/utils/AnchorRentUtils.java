package com.langmy.terminal.modules.charge.utils;

import com.langmy.terminal.common.entity.AnchorRent;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.dao.AnchorRentDao;

/**
 * 长期收费接口类
 * @author lzy
 *
 */
public class AnchorRentUtils {

	private static AnchorRentDao anchorRentDao = SpringContextHolder.getBean(AnchorRentDao.class);

	/**
	 * 根据Id获取相应的长期规则
	 * 
	 * @param id
	 *            长期规则编号
	 * @return
	 */
	public static AnchorRent findById(int id) {
		return anchorRentDao.findOne(id);
	}

}
