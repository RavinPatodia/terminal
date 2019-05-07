package com.langmy.terminal.modules.caradmission.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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
import com.langmy.terminal.common.entity.AdminssionRec;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.DelParkingRec;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.ParkingRec;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.caradmission.dao.AdmissionRecDao;
import com.langmy.terminal.modules.caradmission.dao.CarDao;
import com.langmy.terminal.modules.caradmission.dao.ParkingRecDao;
import com.langmy.terminal.modules.caradmission.model.ParkingRecModel;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

/**
 * 停车记录业务层
 * 
 * @author ZZD
 *
 */
@Service
public class ParkingRecService extends BaseService<ParkingRec> {

	@Autowired
	private ParkingRecDao parkingRecDao;
	@Autowired
	private CarDao carDao;
	@Autowired
	private AdmissionRecDao admissionRecDao;

	public ParkingRecService() {
		super.baseDao = SpringContextHolder.getBean(ParkingRecDao.class);
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @return
	 */
	public ParkingRecModel view(int id) {
		ParkingRec rec = parkingRecDao.findOne(id);
		ParkingRecModel model = new ParkingRecModel();
		try {
			model.setLicensePlate(rec.getCar().getLicensePlate());
			model.setLicenseType(rec.getCar().getType());
			model.setPspId(rec.getPSp().getPspId());
			BeanUtils.copyProperties(rec, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("出入场记录模块-查看详细信息-实体类转成model类失败");
			return null;
		}
		return model;
	}

	/**
	 * 添加
	 * 
	 * @param model
	 * @return
	 */
	public boolean add(ParkingRecModel model) {
		ParkingRec parkingRec = addByModel(model);
		if (parkingRec == null)
			return false;
		LogUtil.save("停车记录", LogUtil.Option.ADD,
				"添加停车记录：" + parkingRec.toString());
		return true;
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @return
	 */
	private ParkingRec addByModel(ParkingRecModel model) {
		ParkingRec parkingRec = new ParkingRec();
		try {
			BeanUtils.copyProperties(model, parkingRec);

		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("停车记录模块-根据Model得到相应的类-Bean之间相互赋值异常");
			return null;
		}
		Car car = carDao.findByLicensePlateAndDelFlagFalse(model
				.getLicensePlate());
		parkingRec.setCar(car);

		AdminssionRec admissionRec = admissionRecDao.findOne(model
				.getAdmissionRecModel().getId());
		parkingRec.setAdminssionRec(admissionRec);

		PSp psp = PSpUtils.findPSpByPSpId(model.getPspId());
		parkingRec.setPSp(psp);

		parkingRec = parkingRecDao.save(parkingRec);
		return parkingRec;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<ParkingRec> parkingRecs) {
		List<ParkingRecModel> parkingRecModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(parkingRecs)) {
			return Lists.newArrayList(parkingRecModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "PSp.pspId");
		map.put("targetPro1", "pspId");
		map.put("sourcePro2", "car.licensePlate");
		map.put("targetPro2", "licensePlate");
		map.put("sourcePro3", "car.type");
		map.put("targetPro3", "licenseType");
		try {
			parkingRecModels = BeanUtils.copyListProperties(parkingRecs,
					ParkingRecModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("停车记录模块-实体类与Model类数组转化失败", e);
			return null;
		}
		return Lists.newArrayList(parkingRecModels);
	}

	@Override
	protected Specification<ParkingRec> buildSpecification(BaseModel model) {
		ParkingRecModel parkRecModel = (ParkingRecModel) model;
		String licensePlate = parkRecModel.getLicensePlate();
		String licenseType = parkRecModel.getLicenseType();
		String pspId = parkRecModel.getPspId();

		return new Specification<ParkingRec>() {
			@Override
			public Predicate toPredicate(Root<ParkingRec> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.isNotNullAndEmpty(licensePlate)) {
					list.add(cb.like(root.get("car").get("licensePlate"), "%"
							+ licensePlate + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(licenseType)) {
					list.add(cb.like(root.get("car").get("type"), "%"
							+ licenseType + "%"));
				}
				if (StringUtils.isNotNullAndEmpty(pspId)) {
					list.add(cb.like(root.get("PSp").get("pspId"), "%" + pspId
							+ "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};

	}
}
