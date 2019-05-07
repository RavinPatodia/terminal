package com.langmy.terminal.modules.device.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.langmy.terminal.common.entity.BrandModel;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.modules.device.dao.BrandModelDao;
import com.langmy.terminal.modules.device.model.BrandModels;

public class BrandModelService extends BaseService<BrandModel>{

	@Autowired
	private BrandModelDao brandModelDao;

	public boolean add(BrandModels model) throws IllegalAccessException, InvocationTargetException {
		boolean flag = true;
		BrandModel brandModel = new BrandModel();
		BeanUtils.copyProperties(model, brandModel);
		brandModel = brandModelDao.save(brandModel);
		if(brandModel==null){
			logger.error("新增失败");
			flag = false;
			return flag;
		}
		logger.info("新增成功");
		return flag;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<BrandModel> entities) {
		return null;
	}

	@Override
	protected Specification<BrandModel> buildSpecification(BaseModel model) {
		return null;
	}
	
}
