package com.langmy.terminal.modules.report.service;

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
import com.langmy.terminal.common.entity.DelAdmissionRec;
import com.langmy.terminal.common.entity.Driveway;
import com.langmy.terminal.common.entity.ParkingRec;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.caradmission.dao.AdmissionRecDao;
import com.langmy.terminal.modules.caradmission.dao.CarDao;
import com.langmy.terminal.modules.caradmission.model.AdmissionRecModel;
import com.langmy.terminal.modules.caradmission.model.ParkingRecModel;
import com.langmy.terminal.modules.device.utils.DrivewayUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;

/**
 * 出入场记录业务层
 * 
 * @author ZZD
 *
 */
@Service
public class OperLaundryListService extends BaseService<AdminssionRec> {

	@Autowired
	private AdmissionRecDao admissionRecDao;
	@Autowired
	private CarDao carDao;

	public OperLaundryListService() {
		super.baseDao = SpringContextHolder.getBean(AdmissionRecDao.class);
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 */
	public boolean edit(AdmissionRecModel model) {
		AdminssionRec admissionRec = addAdmissionRecByModel(model);
		if (admissionRec == null)
			return false;
		LogUtil.save("出入场记录管理", LogUtil.Option.EDIT,
				"修改出入场记录：" + admissionRec.toString());
		return true;
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @return
	 */
	public AdmissionRecModel view(int id) {
		AdminssionRec rec = admissionRecDao.findOne(id);
		AdmissionRecModel model = new AdmissionRecModel();
		try {
			BeanUtils.copyProperties(rec, model);
		} catch (IllegalAccessException | InvocationTargetException e1) {
			logger.error("出入场记录模块-查看详细信息-实体类转成model类失败");
		}
		model.setLicensePlate(rec.getCar().getLicensePlate());
		model.setLicenseType(rec.getCar().getType());
		model.setCarType(rec.getCar().getCarType());
		model.setColor(rec.getCar().getCarColor());
		model.setModule(rec.getCar().getCarModel());
		model.setInDriveWayName(rec.getInDriveWay().getName());
		model.setOutDriveWayName(rec.getOutDriveway().getName());
		model.setInDriveWayPK(rec.getInDriveWay().getId());
		model.setOutDriveWayPK(rec.getOutDriveway().getId());
		model.setBayonetEntranceName(rec.getInDriveWay().getBayonet().getName());
		model.setBayonetExitName(rec.getOutDriveway().getBayonet().getName());
		model.setParkName(rec.getInDriveWay().getBayonet().getPark().getName());
		// 获得停车记录表格
		List<ParkingRec> parkRecs = admissionRecDao
				.findParkingRecByAdmission(id);
		if (parkRecs.size() > 0) {
			Map<String, String> map = Maps.newHashMap();
			map.put("sourcePro1", "PSp.pspId");
			map.put("targetPro1", "pspId");
			try {
				List<ParkingRecModel> pakrRecModels = BeanUtils
						.copyListProperties(parkRecs, ParkingRecModel.class,
								map);
				model.setParkingRecModels(pakrRecModels);
			} catch (IllegalAccessException | InvocationTargetException
					| InstantiationException | IllegalArgumentException
					| ClassNotFoundException | IntrospectionException
					| IOException e) {
				logger.error("出入场记录模块-查看详细信息-停车记录实体类转成model类失败", e);
				return null;
			}
		}
		return model;
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @return
	 */
	private AdminssionRec addAdmissionRecByModel(AdmissionRecModel model) {
		AdminssionRec admissionRec = new AdminssionRec();
		try {
			BeanUtils.copyProperties(model, admissionRec);
		} catch (IllegalArgumentException | IllegalAccessException
				| InvocationTargetException e) {
			logger.error("出入场记录模块-根据Model得到相应的类-Bean之间相互赋值异常");
			return null;
		}
		AdminssionRec adm = admissionRecDao.findOne(model.getId());
		if (adm == null) {
			logger.error("出入场记录模块-无法根据主键找到相应的出入记录");
			return null;
		}
		Driveway inDriveWay = DrivewayUtils.getDrivewayById(model
				.getInDriveWayPK());
		Driveway outDriveway = DrivewayUtils.getDrivewayById(model
				.getOutDriveWayPK());
		admissionRec.setInDriveWay(inDriveWay);
		admissionRec.setOutDriveway(outDriveway);
		admissionRec.setCar(adm.getCar());
		admissionRec.setParkingRecs(adm.getParkingRecs());
		admissionRec = admissionRecDao.save(admissionRec);
		return admissionRec;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(
			List<AdminssionRec> adminssionRecs) {
		List<AdmissionRecModel> admissionRecModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(adminssionRecs)) {
			return Lists.newArrayList(admissionRecModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "inDriveWay.id");
		map.put("targetPro1", "inDriveWayPK");
		map.put("sourcePro2", "inDriveWay.name");
		map.put("targetPro2", "inDriveWayName");
		map.put("sourcePro3", "outDriveway.id");
		map.put("targetPro3", "outDriveWayPK");
		map.put("sourcePro4", "outDriveway.name");
		map.put("targetPro4", "outDriveWayName");
		map.put("sourcePro5", "car.licensePlate");
		map.put("targetPro5", "licensePlate");
		map.put("sourcePro6", "car.type");
		map.put("targetPro6", "licenseType");
		map.put("sourcePro7", "inDriveWay.bayonet.name");
		map.put("targetPro7", "bayonetEntranceName");
		map.put("sourcePro8", "outDriveway.bayonet.name");
		map.put("targetPro8", "bayonetExitName");
		map.put("sourcePro9", "inDriveWay.bayonet.park.name");
		map.put("targetPro9", "parkName");
		map.put("modelSourcePro1", "parkingRecList");
		map.put("modelTargetPro1", "parkingRecModels");

		try {
			admissionRecModels = BeanUtils.copyListProperties(adminssionRecs,
					AdmissionRecModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("出入场记录模块-实体类与Model类数组转化失败", e);
			return null;
		}
		return Lists.newArrayList(admissionRecModels);
	}

	@Override
	protected Specification<AdminssionRec> buildSpecification(BaseModel model) {
		AdmissionRecModel adminModel = (AdmissionRecModel) model;
		String licensePlate = adminModel.getLicensePlate();
		String licenseType = adminModel.getLicenseType();
		Integer isPayInt = adminModel.getIsPayInt();
		Integer inDriveWayPK = adminModel.getInDriveWayPK();
		Integer outDriveWayPK = adminModel.getOutDriveWayPK();
		Integer inRecoWay = adminModel.getInRecoWay();
		Integer outRecoWay = adminModel.getOutRecoWay();
		return new Specification<AdminssionRec>() {
			@Override
			public Predicate toPredicate(Root<AdminssionRec> root,
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
				if (isPayInt != null && isPayInt != -1) {
					if (isPayInt == 1) {
						list.add(cb.equal(root.get("isPay"), true));
					} else if (isPayInt == 0) {
						list.add(cb.equal(root.get("isPay"), false));
					}
				}
				if (outDriveWayPK != null && outDriveWayPK != -1) {
					list.add(cb.equal(root.get("outDriveway").get("id"),
							outDriveWayPK));
				}
				if (inDriveWayPK != null && inDriveWayPK != -1) {
					list.add(cb.equal(root.get("inDriveWay").get("id"),
							inDriveWayPK));
				}
				if (inRecoWay != null && inRecoWay != -1) {
					list.add(cb.equal(root.get("inRecoWay"), inRecoWay));
				}
				if (outRecoWay != null && outRecoWay != -1) {
					list.add(cb.equal(root.get("outRecoWay"), outRecoWay));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};

	}

}
