package com.langmy.terminal.modules.psp.extend;

import java.lang.reflect.InvocationTargetException;

import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.modules.charge.service.extend.BaseRuleExtend;
import com.langmy.terminal.modules.psp.model.PSpModel;

public class PspChargeRuleExtend extends BaseRuleExtend {

	/**
	 * 将规则组转化成前台显示的model
	 * 
	 * @return
	 */
	public static PSpModel covertModelByPsp(PSp psp) {
 		PSpModel model = new PSpModel();
		try {
			BeanUtils.copyProperties(psp, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("车位管理-实体类与Model类转化失败", e);
			return model;
		}
		if(psp.getArea()==null){
			model.setAreaName(null);
		}else if(psp.getArea().isDelFlag() == true){
			model.setAreaName(null); 
		}else {
			model.setAreaName(psp.getArea().getName());
		}
		if(psp.getCar()==null){
			model.setLicensePlate(null);
		}else {
			model.setLicensePlate(psp.getCar().getLicensePlate());
		}
		if(psp.getPark()==null){
			model.setParkName(null);
		}else{
			model.setParkName(psp.getPark().getName());
		}
		model.setEnableStateBoolean(psp.getIsEnable());
		return model;
	}
}
