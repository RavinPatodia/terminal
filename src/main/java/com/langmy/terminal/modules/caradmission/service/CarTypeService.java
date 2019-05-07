package com.langmy.terminal.modules.caradmission.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.CarType;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.DataTable;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.modules.caradmission.dao.CarTypeDao;
import com.langmy.terminal.modules.caradmission.daoImpl.CarTypeDaoImpl;
import com.langmy.terminal.modules.caradmission.model.CarTypeModel;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 车辆相关信息 管理业务层
 * 
 * @author ZZD
 *
 */
@Service
public class CarTypeService extends BaseService<CarType> {

	private final static Integer TYPE_CAR_COLOR = 1;
	private final static Integer TYPE_CAR_MODEL = 2;
	private final static Integer TYPE_TYPE_NAME = 3;

	@Autowired
	private CarTypeDao carTypeDao;
	@Autowired
	private CarTypeDaoImpl carTypeDaoImpl;

	public CarTypeService() {
		super.baseDao = SpringContextHolder.getBean(CarTypeDao.class);
	}

	/**
	 * 获得车辆颜色列表
	 * 
	 * @param dataTableParam
	 * @return
	 */
	public DataTable<BaseModel> colorList(DataTableParameter dataTableParam) {
		Map<String, Object> result = carTypeDaoImpl.getColor(
				dataTableParam.getiDisplayStart(),
				dataTableParam.getiDisplayLength());
		@SuppressWarnings("unchecked")
		List<CarType> color = (List<CarType>) result.get("list");
		int wholeCount = (int) result.get("wholeCount");
		List<BaseModel> models = getModelsByBeans(color);
		DataTable<BaseModel> dt = new DataTable<BaseModel>();
		dt.setAaData(models);
		dt.setiTotalDisplayRecords(wholeCount);
		dt.setiTotalRecords((int) baseDao.count());
		dt.setsEcho(dataTableParam.getsEcho() + 1);
		return dt;
	}

	/**
	 * 获得车辆型号列表
	 * 
	 * @param dataTableParam
	 * @return
	 */
	public DataTable<BaseModel> moduleList(DataTableParameter dataTableParam) {
		Map<String, Object> result = carTypeDaoImpl.getModule(
				dataTableParam.getiDisplayStart(),
				dataTableParam.getiDisplayLength());
		@SuppressWarnings("unchecked")
		List<CarType> color = (List<CarType>) result.get("list");
		int wholeCount = (int) result.get("wholeCount");
		List<BaseModel> models = getModelsByBeans(color);
		DataTable<BaseModel> dt = new DataTable<BaseModel>();
		dt.setAaData(models);
		dt.setiTotalDisplayRecords(wholeCount);
		dt.setiTotalRecords((int) baseDao.count());
		dt.setsEcho(dataTableParam.getsEcho() + 1);
		return dt;
	}

	/**
	 * 获得车牌类型列表
	 * 
	 * @param dataTableParam
	 * @return
	 */
	public DataTable<BaseModel> typeList(DataTableParameter dataTableParam) {
		Map<String, Object> result = carTypeDaoImpl.getType(
				dataTableParam.getiDisplayStart(),
				dataTableParam.getiDisplayLength());
		@SuppressWarnings("unchecked")
		List<CarType> color = (List<CarType>) result.get("list");
		int wholeCount = (int) result.get("wholeCount");
		List<BaseModel> models = getModelsByBeans(color);
		DataTable<BaseModel> dt = new DataTable<BaseModel>();
		dt.setAaData(models);
		dt.setiTotalDisplayRecords(wholeCount);
		dt.setiTotalRecords((int) baseDao.count());
		dt.setsEcho(dataTableParam.getsEcho() + 1);
		return dt;
	}

	/**
	 * 获得车辆颜色
	 * 
	 * @param carColor
	 * @return
	 */
	public List<BaseModel> getColor(String carColor) {

		List<CarType> carTypes = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(carColor)) {
			carTypes = carTypeDao.findByTypeAndColorLikeAndDelFlagFalse(
					TYPE_CAR_COLOR, "%" + carColor + "%");
		} else {
			carTypes = carTypeDao.findByType(TYPE_CAR_COLOR);
		}
		return getModelsByBeans(carTypes);
	}

	/**
	 * 获得车辆型号
	 * 
	 * @param carModel
	 * @return
	 */
	public List<BaseModel> getModel(String carModel) {
		List<CarType> carTypes = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(carModel)) {
			carTypes = carTypeDao.findByTypeAndModelLikeAndDelFlagFalse(
					TYPE_CAR_MODEL, "%" + carModel + "%");
		} else {
			carTypes = carTypeDao.findByType(TYPE_CAR_MODEL);
		}
		return getModelsByBeans(carTypes);
	}

	/**
	 * 获得车牌类型
	 * 
	 * @param typeName
	 * @return
	 */
	public List<BaseModel> getTypeName(String typeName) {
		List<CarType> carTypes = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(typeName)) {
			carTypes = carTypeDao.findByTypeAndTypeNameLikeAndDelFlagFalse(
					TYPE_TYPE_NAME, "%" + typeName + "%");
		} else {
			carTypes = carTypeDao.findByType(TYPE_TYPE_NAME);
		}
		return getModelsByBeans(carTypes);
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @param type
	 * @return
	 */
	private CarType addCarTypeByModel(CarTypeModel model, Integer type) {
		CarType cartype = new CarType();
		try {
			BeanUtils.copyProperties(model, cartype);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("车辆类型管理模块-根据Model得到相应的类-Bean之间相互赋值异常");
			return null;
		}
		cartype.setType(type);
		cartype.setDelFlag(false);
		cartype = carTypeDao.save(cartype);
		return cartype;
	}

	/**
	 * 添加车辆型号
	 * 
	 * @param model
	 * @return
	 */
	public boolean addCarModel(CarTypeModel model) {
		CarType carType = addCarTypeByModel(model, TYPE_CAR_MODEL);
		if (carType == null)
			return false;
		LogUtil.save("车辆相关信息管理", LogUtil.Option.ADD,
				"添加车辆型号：" + carType.toString());
		return true;
	}

	/**
	 * 添加车牌类型
	 * 
	 * @param model
	 * @return
	 */
	public boolean addTypeName(CarTypeModel model) {
		CarType carType = addCarTypeByModel(model, TYPE_TYPE_NAME);
		if (carType == null)
			return false;
		LogUtil.save("车辆相关信息管理", LogUtil.Option.ADD,
				"添加车牌类型：" + carType.toString());
		return true;
	}

	/**
	 * 添加车辆颜色
	 * 
	 * @param model
	 * @return
	 */
	public boolean addCarColor(CarTypeModel model) {
		CarType carType = addCarTypeByModel(model, TYPE_CAR_COLOR);
		if (carType == null)
			return false;
		LogUtil.save("车辆相关信息管理", LogUtil.Option.ADD,
				"添加车辆颜色：" + carType.toString());
		return true;
	}

	/**
	 * 修改车牌类型
	 * 
	 * @param model
	 * @return
	 */
	public boolean editTypeName(CarTypeModel model) {
		CarType carType = addCarTypeByModel(model, TYPE_TYPE_NAME);
		if (carType == null)
			return false;
		LogUtil.save("车辆相关信息管理", LogUtil.Option.EDIT,
				"修改车牌类型：" + carType.toString());
		return true;
	}

	/**
	 * 修改车辆型号
	 * 
	 * @param model
	 * @return
	 */
	public boolean editCarModel(CarTypeModel model) {
		CarType carType = addCarTypeByModel(model, TYPE_CAR_MODEL);
		if (carType == null)
			return false;
		LogUtil.save("车辆相关信息管理", LogUtil.Option.EDIT,
				"修改车辆型号：" + carType.toString());
		return true;
	}

	/**
	 * 修改车辆颜色
	 * 
	 * @param model
	 * @return
	 */
	public boolean editCarColor(CarTypeModel model) {
		CarType carType = addCarTypeByModel(model, TYPE_CAR_COLOR);
		if (carType == null)
			return false;
		LogUtil.save("车辆相关信息管理", LogUtil.Option.EDIT,
				"修改车辆颜色：" + carType.toString());
		return true;
	}

	/**
	 * 删除多条记录
	 * 
	 * @param ids
	 * @return
	 */
	public boolean delete(String ids) {
		List<Integer> idList = getIdList(ids);
		if (carTypeDao.del(idList) > 0) {
			for (int id : idList) {
				LogUtil.save("车辆相关信息管理", LogUtil.Option.DEL, "删除车辆相关信息：" + id);
			}
			return true;
		}
		return false;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<CarType> carTypes) {
		List<CarTypeModel> carTypeModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(carTypes)) {
			return Lists.newArrayList(carTypeModels);
		}

		try {
			carTypeModels = BeanUtils.copyListProperties(carTypes,
					CarTypeModel.class);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("车辆类型管理模块-实体类与Model类数组转化失败");
			return null;
		}
		return Lists.newArrayList(carTypeModels);
	}

	@Override
	protected Specification<CarType> buildSpecification(BaseModel baseModel) {
		CarTypeModel model = (CarTypeModel) baseModel;
		Integer type = model.getType();
		String color = model.getColor();
		String typeName = model.getTypeName();
		String module = model.getModel();
		return new Specification<CarType>() {
			@Override
			public Predicate toPredicate(Root<CarType> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (type != null) {
					list.add(cb.equal(root.get("type"), type));
				}
				if (StringUtils.isNotNullAndEmpty(color)) {
					list.add(cb.like(root.get("color"), "%" + color + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(typeName)) {
					list.add(cb.like(root.get("typeName"), "%" + typeName + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(module)) {
					list.add(cb.like(root.get("model"), "%" + module + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		};
	}

}
