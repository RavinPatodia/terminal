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
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.DataTable;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.modules.caradmission.dao.CarDao;
import com.langmy.terminal.modules.caradmission.daoImpl.CarDaoImpl;
import com.langmy.terminal.modules.caradmission.model.CarModel;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.user.utils.UserUtils;

/**
 * 车辆管理业务层
 * 
 * @author ZZD
 *
 */
@Service
public class CarService extends BaseService<Car> {

	@Autowired
	private CarDao carDao;
	@Autowired
	private CarDaoImpl carDaoImpl;

	public CarService() {
		super.baseDao = SpringContextHolder.getBean("carDao");
	}

	/**
	 * 获得车辆列表
	 * 
	 * @param model
	 * @param dataTableParam
	 * @return
	 */
	public DataTable<BaseModel> list(CarModel model,
			DataTableParameter dataTableParam) {
		Map<String, Object> result = carDaoImpl.getCarList(model,
				dataTableParam.getiDisplayStart(),
				dataTableParam.getiDisplayLength());
		@SuppressWarnings("unchecked")
		List<Car> cars = (List<Car>) result.get("list");
		int wholeCount = (int) result.get("wholeCount");
		List<BaseModel> models = getModelsByBeans(cars);
		DataTable<BaseModel> dt = new DataTable<BaseModel>();
		dt.setAaData(models);
		dt.setiTotalDisplayRecords(wholeCount);
		dt.setiTotalRecords((int) baseDao.count());
		dt.setsEcho(dataTableParam.getsEcho() + 1);
		return dt;
	}

	/**
	 * 删除多条记录
	 * 
	 * @param ids
	 * @return
	 */
	public boolean delete(String ids) {
		List<Integer> idList = getIdList(ids);
		if (carDao.del(idList) > 0) {
			for (int id : idList) {
				LogUtil.save("车辆管理", LogUtil.Option.DEL, "删除车辆：" + id);
			}
			return true;
		}
		return false;
	}

	public boolean delete(int id) {
		if (carDao.del(id) > 0) {
			LogUtil.save("车辆管理", LogUtil.Option.DEL, "删除车辆：" + id);
			return true;
		}
		return false;
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @return
	 */
	public boolean add(CarModel model) {
		Car car = addCarByModel(model);
		if (car == null)
			return false;
		LogUtil.save("车辆管理", LogUtil.Option.ADD, "添加车辆：" + car.toString());
		return true;
	}

	/**
	 * 保存车辆信息
	 * 
	 * @param model
	 * @return
	 */
	private Car addCarByModel(CarModel model) {

		Integer userPK = model.getUserPK();
		User user = UserUtils.findUserById(userPK);
		if (user == null) {
			logger.error("车辆管理模块-无法根据主键找到相应的用户");
			return null;
		}
		Car car = addCarByModelAndUser(model, user);
		if (car == null) {
			logger.error("车辆管理模块-新增车辆失败");
		}
		return car;
	}

	public Car addCarByModelAndUser(CarModel model, User user) {
		Car car = new Car();
		try {
			BeanUtils.copyProperties(model, car);

		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("车辆管理模块-根据Model得到相应的类-Bean之间相互赋值异常");
			return null;
		}
		car.setUser(user);
		car = carDao.save(car);
		return car;
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 */
	public boolean edit(CarModel model) {
		Car car = carDao.findOne(model.getId());
		try {
			BeanUtils.copyProperties(model, car);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("车辆管理模块-根据Model得到相应的类-Bean之间相互赋值异常");
			return false;
		}
		Integer userPK = model.getUserPK();
		User user = UserUtils.findUserById(userPK);
		if (user == null) {
			logger.error("车辆管理模块-无法根据主键找到相应的用户");
			return false;
		}
		car.setCarType(model.getCarType());
		car.setUser(user);
		car = carDao.save(car);
		if (car == null)
			return false;
		LogUtil.save("车辆管理", LogUtil.Option.EDIT, "修改车辆：" + car.toString());
		return true;
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @return
	 */
	public CarModel view(int id) {
		Car car = carDao.findOne(id);
		CarModel model = new CarModel();
		try {
			BeanUtils.copyProperties(car, model);
			User user = car.getUser();
			if (user != null) {
				model.setUserPK(user.getId());
				model.setState(user.getState());
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("车辆管理模块-实体类与Model类数组转化失败");
			return null;
		}
		return model;
	}

	/**
	 * 根据licensePlate查询
	 * 
	 * @param licensePlate
	 *            车牌号码
	 * @return
	 */

	public List<BaseModel> getLicensePlates(String licensePlate) {
		List<Car> cars = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(licensePlate)) {
			cars = carDao.findByLicensePlateLikeAndDelFlagFalse("%"
					+ licensePlate + "%");
		} else {
			cars = carDao.findByDelFlagFalse();
		}
		return getModelsByBeans(cars);
	}

	/**
	 * 判断是否已存在 根据licensePlate查询
	 * 
	 * @param licensePlate
	 *            车牌号码
	 * @return
	 */
	public boolean ifExist(String licensePlate) {
		Car car = carDao.findByLicensePlateAndDelFlagFalse(licensePlate);
		if (car == null) {
			return false;
		}
		return true;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Car> cars) {
		List<CarModel> carModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(cars)) {
			return Lists.newArrayList(carModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "user.id");
		map.put("targetPro1", "userPK");
		map.put("sourcePro2", "user.UAcc");
		map.put("targetPro2", "uacc");
		map.put("sourcePro3", "user.name");
		map.put("targetPro3", "name");
		map.put("sourcePro4", "user.idCard");
		map.put("targetPro4", "idCard");
		map.put("sourcePro5", "user.gender");
		map.put("targetPro5", "gender");
		map.put("sourcePro6", "user.birthday");
		map.put("targetPro6", "birthday");
		map.put("sourcePro7", "user.state");
		map.put("targetPro7", "state");

		try {
			carModels = BeanUtils.copyListProperties(cars, CarModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("车辆管理模块-实体类与Model类数组转化失败");
			return null;
		}
		return Lists.newArrayList(carModels);
	}

	@Override
	protected Specification<Car> buildSpecification(BaseModel baseModel) {
		String licensePlate = ((CarModel) baseModel).getLicensePlate();
		String type = ((CarModel) baseModel).getType();
		String carColor = ((CarModel) baseModel).getCarColor();
		String carModel = ((CarModel) baseModel).getCarModel();
		String name = ((CarModel) baseModel).getName();
		return new Specification<Car>() {

			@Override
			public Predicate toPredicate(Root<Car> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (StringUtils.isNotNullAndEmpty(licensePlate)) {
					list.add(cb.like(root.get("licensePlate"), "%"
							+ licensePlate + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(type)) {
					list.add(cb.like(root.get("type"), "%" + type + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(carColor)) {
					list.add(cb.like(root.get("carColor"), "%" + carColor + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(carModel)) {
					list.add(cb.like(root.get("carModel"), "%" + carModel + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(name)) {
					list.add(cb.equal(root.get("user").get("id"), name));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		};
	}
}
