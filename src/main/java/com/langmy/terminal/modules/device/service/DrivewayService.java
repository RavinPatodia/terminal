package com.langmy.terminal.modules.device.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Bayonet;
import com.langmy.terminal.common.entity.Camera;
import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.entity.Driveway;
import com.langmy.terminal.common.entity.LedScreen;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.device.dao.DrivewayDao;
import com.langmy.terminal.modules.device.model.DrivewayModel;
import com.langmy.terminal.modules.device.service.extend.DrivewayExtend;
import com.langmy.terminal.modules.device.utils.BayonetUtils;
import com.langmy.terminal.modules.device.utils.CameraUtils;
import com.langmy.terminal.modules.device.utils.DeviceUtils;
import com.langmy.terminal.modules.device.utils.LedScreenUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;

@Service
public class DrivewayService extends BaseService<Driveway> {

	@Autowired
	private DrivewayDao drivewayDao;
	
	public DrivewayService() {
		super.baseDao = SpringContextHolder.getBean(DrivewayDao.class);
	}

	public String getGenId(String entitySelfName){
		Driveway  driveway = baseDao.findMaxIdRec();
		int id = 0;
		if(driveway!=null){
			id = driveway.getId()+1;
		}
		return entitySelfName+id;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Driveway> entities) {
		return  Lists.newArrayList(DrivewayExtend.coverToModel(entities));
	}

	@Override
	protected Specification<Driveway> buildSpecification(BaseModel model) {
		return new Specification<Driveway>() {
			@Override
			public Predicate toPredicate(Root<Driveway> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("delFlag").as(boolean.class), false));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
	
	
	public boolean delete(int id) {
		Driveway driveway = drivewayDao.findOne(id);
		if (driveway == null) {
			return false;
		}
		drivewayDao.delete(id);
		return true;
	}
	
	
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            显示屏Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = drivewayDao.softdelete(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("车道管理", LogUtil.Option.DEL, "删除车道：" + id);
			}
		}
		return flag;
	}
	
	
	public boolean add(DrivewayModel model) {
		boolean flag = true;
		Driveway driveway = new Driveway();
		try {
			BeanUtils.copyProperties(model, driveway);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("硬件管理-车道管理-实体类与Model类转化失败", e);
		}
		Bayonet bayonet = BayonetUtils.getBayonetById(model.getBayonetPK());
		if(bayonet!=null){
			driveway.setBayonet(bayonet);
		}
		
		driveway = drivewayDao.save(driveway);
		if (driveway == null) {
			logger.error("新增失败");
			flag = false;
			return flag;
		}
		logger.info("新增成功");
		LogUtil.save("车道管理模块", LogUtil.Option.ADD, "新增车道信息："+driveway.toString());
		return flag;
	}
	
	public boolean edit(DrivewayModel model) {
		boolean flag = true;
		Driveway driveway = drivewayDao.findOne(model.getId());
		if (driveway == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		try {
			BeanUtils.copyProperties(model, driveway);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("硬件管理-车道管理-实体类与Model类转化失败", e);
			e.printStackTrace();
		}
		Bayonet bayonet = BayonetUtils.getBayonetById(model.getBayonetPK());
		if(bayonet!=null){
			driveway.setBayonet(bayonet);
		}
		driveway = drivewayDao.save(driveway);
		if (driveway == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		logger.info("修改成功");
		LogUtil.save("车道管理模块", LogUtil.Option.ADD, "修改车道信息："+driveway.toString());
		return flag;
	}
	
	public boolean config(DrivewayModel model) {
		boolean flag = true;
		Driveway driveway = drivewayDao.findOne(model.getId());
		if (driveway == null) {
			logger.error("配置失败");
			flag = false;
			return flag;
		}
		//配置摄像头
		List<String> cameraIdList = model.getCameraSelect();
		List<Integer> ids = Lists.newArrayList();
		if(cameraIdList!=null){
			for(String i : cameraIdList){
				ids.add(Integer.parseInt(i));	
			}
			List<Camera> cameras = CameraUtils.findCameraByIdIn(ids);
			driveway.setCamera(cameras);
		}
		//配置闸机
		if(model.getBrakePK()!=null){
			Device device = DeviceUtils.getDeviceById(model.getBrakePK());
			if(device!=null){
				device.setDriveway(driveway);
			}
		}
		//配置终端机
		if(model.getTerminalPK()!=null){
			Device device = DeviceUtils.getDeviceById(model.getTerminalPK());
			if(device!=null){
				driveway.setTerminal(device);
			}
		}
		
		//配置显示屏
		Integer screenId = null;
		if(model.getQdirection().equals("入口")){
			screenId = model.getScreenInPK();
		}else if(model.getQdirection().equals("出口")){
			screenId = model.getScreenOutPK();
		}
		if(screenId!=null){
			LedScreen ledScreen = LedScreenUtils.findById(screenId);
			if(ledScreen != null){
				ledScreen.setConfigureId(model.getId());
			}
		}

		logger.info("配置成功");
		LogUtil.save("车道管理模块", LogUtil.Option.ADD, "配置车道信息："+driveway.toString());
		return flag;
	}
	
	public DrivewayModel getDrivewayModelById(int id) {
		Driveway driveway = drivewayDao.findOne(id);
		return DrivewayExtend.getModelByDriveway(driveway);
	}
	
	public Driveway getDrivewayById(int id) {
		return drivewayDao.findOne(id);
	}
	
	public List<BaseModel> getDrivewayByNameLike(String name) {
		List<Driveway> driveways = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(name)) {
			driveways = drivewayDao.findByNameLikeAndDelFlagFalse("%" + name + "%");
		} else {
			driveways = drivewayDao.findByDelFlagFalse();
		}
		return Lists.newArrayList(DrivewayExtend.coverToModel(driveways));
	}
	
	public List<BaseModel> getDrivewayByNameLikeAndDirection(String name,int direction) {
		List<Driveway> driveways = Lists.newArrayList();
		if (StringUtils.isNotNullAndEmpty(name)) {
			driveways = drivewayDao.findByNameLikeAndDirectionAndDelFlagFalse("%" + name + "%",direction);
		} else {
			driveways = drivewayDao.findByDirectionAndDelFlagFalse(direction);
		}
		return Lists.newArrayList(DrivewayExtend.coverToModel(driveways));
	}
	
	public List<BaseModel> getDrivewayByDirection(int direction){
		List<Driveway> driveways = Lists.newArrayList();
		driveways = drivewayDao.findByDirectionAndDelFlagFalse(direction);
		return Lists.newArrayList(DrivewayExtend.coverToModel(driveways));
	}
	
	/**
	 * 获得所有的车道
	 */
	public List<Driveway> findAllDriveway(){
		return drivewayDao.findAll();
	}

	/**
	 * 获得所有没有配置的车道
	 */
	public List<Driveway> findNotConfigDriveway(){
		return drivewayDao.findByTerminalIdIsNull();
	}
	/**
	 *  获得终端机已经配置的车道
	 * @param terminalId 终端机ID
	 * @return
	 */
	public List<Driveway> findExistDriveway(int terminalId){
		return drivewayDao.findByTerminalId(terminalId);
	}
	
}
