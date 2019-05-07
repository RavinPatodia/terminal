package com.langmy.terminal.modules.charge.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.langmy.terminal.common.entity.MeterCharge;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.modules.charge.dao.MeterChargeDao;
import com.langmy.terminal.modules.charge.model.MeterChargeModel;
import com.langmy.terminal.modules.charge.service.extend.MeterChargeExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 计次收费规则service
 * 
 * @author lzy
 *
 */
@Service
public class MeterChargeService extends BaseRuleService<MeterCharge>{

	@Autowired
	public MeterChargeService(MeterChargeDao meterChargeDao) {
		super(meterChargeDao);
	}

	public static Logger logger = LoggerFactory.getLogger(MeterChargeService.class);
	
	@Autowired
	private MeterChargeDao meterChargeDao;


	public MeterCharge add(MeterChargeModel model) {
		MeterCharge meterCharge = MeterChargeExtend.covertToMeterCharge(model);
		if(logger.isDebugEnabled())
			logger.debug(meterCharge.toString());
		meterCharge = meterChargeDao.save(meterCharge);
		if (meterCharge == null) {
			logger.error("新增计次收费失败");
			return null;
		}
		LogUtil.save("收费模块", LogUtil.Option.ADD,
				"新增计次收费规则：" + meterCharge.toString());
		return meterCharge;
	}

	public boolean edit(MeterChargeModel model){
		int id = model.getId();
		MeterCharge meterCharge = meterChargeDao.findOne(id);
		try {
			BeanUtils.copyProperties(model, meterCharge);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("计次收费规则model复制属性到实体失败",e);
			e.printStackTrace();
		}
		if(logger.isDebugEnabled())
			logger.debug(meterCharge.toString());
		meterCharge = meterChargeDao.save(meterCharge);
		if(meterCharge==null){
			logger.error("修改计次规则失败");
			return false;
		}
		return true;
	}

	public MeterChargeModel getMeterChargeById(int id) {
		MeterCharge meterCharge = meterChargeDao.findOne(id);
		return MeterChargeExtend.covertToModel(meterCharge);
	}
	
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            优惠规则Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = meterChargeDao.del(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("计次收费规则管理", LogUtil.Option.DEL, "删除计次收费规则：" + id);
			}
		}

		return flag;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<MeterCharge> entities) {
		return null;
	}

	@Override
	protected Specification<MeterCharge> buildSpecification(BaseModel model) {
		return null;
	}

}
