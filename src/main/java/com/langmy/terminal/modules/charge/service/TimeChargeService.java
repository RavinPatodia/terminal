package com.langmy.terminal.modules.charge.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.langmy.terminal.common.entity.TimeCharge;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.modules.charge.dao.TimeChargeDao;
import com.langmy.terminal.modules.charge.model.TimeChargeModel;
import com.langmy.terminal.modules.charge.service.extend.TimeChargeExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 计时收费规则service
 * 
 * @author lzy
 *
 */
@Service
public class TimeChargeService extends BaseRuleService<TimeCharge>{

	@Autowired
	public TimeChargeService(TimeChargeDao timeChargeDao) {
		super(timeChargeDao);
	}

	public static Logger logger = LoggerFactory.getLogger(TimeChargeService.class);
	
	/**
	 * 模式——标准
	 */
	public static int BILLING_MODEL_STANDARD = 0;
	/**
	 * 模式——时段
	 */
	public static int BILLING_MODEL_PERIOD = 1;
	/**
	 * 模式——昼夜模式一
	 */
	public static int BILLING_MODEL_DAY_NIGHT = 2;
	/**
	 * 模式——昼夜模式二
	 */
	public static int BILLING_MODEL_DAY_NIGHT2 = 3;
	/**
	 * 收费金额处理——不足一单位记为一单位
	 */
	public static int DECIHANDLE_ROUNDING_UP = 0;
	/**
	 * 收费金额处理——不足一单位舍弃
	 */
	public static int DECIHANDLE_ABANDON = 1;
	/**
	 * 收费金额处理——不足一单位不处理
	 */
	public static int DECIHANDLE_NONE = 2;

	/**
	 * 时间处理——不足一单位时间记为一单位时间
	 */
	public static int TIMEHANDLE_ROUNDING_UP = 0;
	/**
	 * 时间处理——不足一单位时间舍弃
	 */
	public static int TIMEHANDLE_ABANDON = 1;

	@Autowired
	private TimeChargeDao timeChargeDao;

	public TimeCharge add(TimeChargeModel model) {
		TimeCharge timeCharge = TimeChargeExtend.convertToTimeCharge(model);
		if(logger.isDebugEnabled())
			logger.debug(timeCharge.toString());
		timeCharge = timeChargeDao.save(timeCharge);
		if (timeCharge == null) {
			logger.error("新增计时规则失败");
			return timeCharge;
		}
		LogUtil.save("收费模块", LogUtil.Option.ADD, "新增计时收费规则：" + timeCharge.toString());
		return timeCharge;
	}

	public boolean edit(TimeChargeModel model) {
		int id = model.getId();
		TimeCharge timeCharge =timeChargeDao.findOne(id);
		try {
			BeanUtils.copyProperties(model, timeCharge);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("计时收费规则model转化成实体失败",e);
		}
		if(logger.isDebugEnabled())
			logger.debug("属性从model copy到计时规则中后："+ JSONObject.toJSONString(model));
		timeCharge = timeChargeDao.save(timeCharge);
		if (timeCharge == null) {
			logger.error("修改计时收费规则失败");
			return false;
		}
		
		return true;
	}

	public TimeChargeModel getTimeChargeById(int id) {
		TimeCharge timeCharge = timeChargeDao.findOne(id);
		return TimeChargeExtend.covertToModel(timeCharge);
	}
	
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            计时收费规则Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = timeChargeDao.del(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("计时收费规则管理", LogUtil.Option.DEL, "删除计时收费规则：" + id);
			}
		}

		return flag;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<TimeCharge> entities) {
		return null;
	}

	@Override
	protected Specification<TimeCharge> buildSpecification(BaseModel model) {
		return null;
	}


}
