package com.langmy.terminal.modules.log.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.langmy.terminal.common.entity.Log;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.log.dao.LogDao;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 日志工具类
 * 
 * @author ZZD
 *
 */
public class LogUtil {

	protected static Logger logger = LoggerFactory.getLogger(LogUtil.class);

	private static LogDao logDao = (LogDao) SpringContextHolder
			.getBean(LogDao.class);

	public enum Option {
		ADD("新增"), EDIT("编辑"), DEL("删除"), RECOVER("数据还原"), FORBIDDEN("禁用"), START(
				"启用"), OPENBRAKEMACHINE("开启闸机"), CLOSEBRAKEMACHINE("关闭闸机"), OPENLOCK(
				"开启车位锁"), CLOSELOCK("关闭车位锁"), RENT("车位租赁"), COPY("数据备份"), CHANGEUG(
				"客户组变更"), BLACKCOVER("解除黑名单"), RECOVERREC("恢复记录"), RECHARGE(
				"业务办理");
		private final String value;

		Option(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 添加日志
	 * 
	 * @param operModule
	 * @param option
	 * @param operCon
	 * @return
	 */
	@Transactional(readOnly = false)
	public static Log save(String operModule, LogUtil.Option option,
			String operCon) {
		String operName = OperaterUtils.getOperater().getOperName();
		if (operName == null) {
			operName = "admin";
		}
		return logDao.save(new Log(operName, new Date(), operModule, option
				.getValue(), operCon));
	}

	public static Log saveAutoOper(String operModule, LogUtil.Option option,
			String operCon) {
		return logDao.save(new Log("系统自动", new Date(), operModule, option
				.getValue(), operCon));
	}
}
