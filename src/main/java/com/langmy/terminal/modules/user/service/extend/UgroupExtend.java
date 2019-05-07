package com.langmy.terminal.modules.user.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.langmy.terminal.common.config.Constant.ChargeRuleType;
import com.langmy.terminal.common.config.Constant.UserGroupType;
import com.langmy.terminal.common.entity.AnchorCharge;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.DctRuleGroup;
import com.langmy.terminal.common.entity.MeterCharge;
import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.entity.TimeCharge;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.charge.model.AnchorChargeModel;
import com.langmy.terminal.modules.charge.model.DctRuleModel;
import com.langmy.terminal.modules.charge.model.MeterChargeModel;
import com.langmy.terminal.modules.charge.model.TimeChargeModel;
import com.langmy.terminal.modules.charge.utils.DetailRuleUtils;
import com.langmy.terminal.modules.user.dao.UGChargeRuleDao;
import com.langmy.terminal.modules.user.dao.UGroupDao;
import com.langmy.terminal.modules.user.model.UGroupModel;

/**
 * 客户组Model和实体转化类
 * 
 * @author zhaozhedan
 *
 */
public class UgroupExtend {

	protected static Logger logger = LoggerFactory
			.getLogger(UgroupExtend.class);
	private static UGroupDao uGroupDao = SpringContextHolder
			.getBean(UGroupDao.class);
	private static UGChargeRuleDao ugChargeRuleDao = SpringContextHolder
			.getBean(UGChargeRuleDao.class);

	public static UGroupModel covertToModel(UGroup group)
			throws InstantiationException, IllegalArgumentException,
			ClassNotFoundException, IntrospectionException, IOException {
		UGroupModel model = new UGroupModel();
		int id = group.getId();
		try {
			BeanUtils.copyProperties(group, model);
			model.setRuleType(-1);
			// 优惠规则组
			DctRuleGroup dctGroup = group.getDctRuleGroup();
			if (dctGroup != null) {
				model.setDctRuleGroupPK(dctGroup.getId());
				model.setDctGroupNum(dctGroup.getGroupId());
				model.setDctRuleGroupName(dctGroup.getName());
				model.setDctRuleGroupInfo(dctGroup.getDescribion());
				List<DctRuleModel> dctRuleModels = BeanUtils
						.copyListProperties(dctGroup.getDctRules(),
								DctRuleModel.class);
				model.setDctRuleModels(dctRuleModels);
			}
			// 长期收费规则
			ChargeRule longRule = ugChargeRuleDao.findChargeRuleByUgIdAndType(
					id, UserGroupType.LONGTREM.getValue());
			if (longRule != null) {
				model.setRuleName(longRule.getRuleName());
				model.setRuleNum(longRule.getRuleId());
				model.setChargeRuleLongPk(longRule.getId());
				model.setChargeRuleLongName(longRule.getName());
				AnchorCharge anchorCharge = (AnchorCharge) DetailRuleUtils
						.findDetailRuleByIdAndRentType(longRule);
				if (anchorCharge != null) {
					AnchorChargeModel anchorChargeModel = new AnchorChargeModel();
					BeanUtils.copyProperties(anchorCharge, anchorChargeModel);
					model.setAnchorChargeModel(anchorChargeModel);
					model.setRuleType(ChargeRuleType.ANCHOR_CHARGE.getValue());
				}
			}
			// 计时、计次收费规则
			ChargeRule countRule = ugChargeRuleDao
					.findChargeRuleByUgIdAndTypeNotEqual(id,
							UserGroupType.LONGTREM.getValue());
			if (countRule != null) {
				model.setRuleName(countRule.getRuleName());
				model.setRuleNum(countRule.getRuleId());
				if (countRule.getRentType() == ChargeRuleType.TIME_CHARGE
						.getValue()) {
					TimeCharge time = (TimeCharge) DetailRuleUtils
							.findDetailRuleByIdAndRentType(countRule);
					if (time != null) {
						TimeChargeModel timeChargeModel = new TimeChargeModel();
						BeanUtils.copyProperties(time, timeChargeModel);
						model.setTimeChargeModel(timeChargeModel);
						model.setRuleType(ChargeRuleType.TIME_CHARGE.getValue());
					}
				}
				if (countRule.getRentType() == ChargeRuleType.METER_CHARGE
						.getValue()) {
					MeterCharge meter = (MeterCharge) DetailRuleUtils
							.findDetailRuleByIdAndRentType(countRule);
					if (meter != null) {
						MeterChargeModel meteChargeModel = new MeterChargeModel();
						BeanUtils.copyProperties(meter, meteChargeModel);
						model.setMeteChargeModel(meteChargeModel);
						model.setRuleType(ChargeRuleType.METER_CHARGE
								.getValue());
					}
				}
				model.setChargeRuleCountOrTimePk(countRule.getId());
				model.setChargeRuleCountOrTimeName(countRule.getName());
			}
			// 到期变更客户组
			Integer changeGourpId = group.getChangeGroup();
			if (changeGourpId != null && changeGourpId != 0) {
				UGroup changeGroup = uGroupDao.findOne(changeGourpId);
				if (changeGroup != null) {
					model.setChangeGroup(changeGroup.getId());
					model.setChangeGroupName(changeGroup.getName());
				}
			}
			// 权限
			String authPKs = "";
			List<Park> parks = group.getParks();
			for (Park p : parks) {
				authPKs += p.getId() + ",";
			}
			model.setAuthPKs(authPKs);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("客户组管理模块-查看详细信息-实体类转成model类失败");
			return null;
		}
		return model;
	}
}
