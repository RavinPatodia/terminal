package com.langmy.terminal.modules.charge.utils;

import java.util.List;

import org.springframework.stereotype.Service;

import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.charge.dao.DctRuleGroupDao;

/**
 * 优惠规则组接口类
 * @author lzy
 */
@Service
public class DctRuleGroupUtils {

	private static DctRuleGroupDao dctRuleGroupDao = (DctRuleGroupDao) SpringContextHolder
			.getBean(DctRuleGroupDao.class);

	/**
	 * 根据id获取相应优惠规则组
	 * 
	 * @param id
	 * @return
	 */
	public static DctRuleGroup findDctRuleGroupById(Integer id) {
		return dctRuleGroupDao.findOne(id);
	}

	/**
	 * 根据传入的名称找出所有生效的优惠规则组
	 * 
	 * @param name
	 * @return
	 */
	public static List<DctRuleGroup> findAllEffectRules(String name) {
		// Assert.assertNotNull(name);
		if (StringUtils.isNotNullAndEmpty(name))
			return dctRuleGroupDao.findByDelFlagFalseAndNameLike("%" + name
					+ "%");
		else
			return dctRuleGroupDao.findByDelFlagFalse();
	}

}
