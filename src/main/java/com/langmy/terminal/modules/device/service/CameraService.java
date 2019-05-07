package com.langmy.terminal.modules.device.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.Camera;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.device.dao.CameraDao;
import com.langmy.terminal.modules.device.model.CameraModel;
import com.langmy.terminal.modules.device.service.extend.CameraExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.model.CameraPSpModel;
import com.langmy.terminal.modules.psp.utils.ArrangeUtils;

@Service
public class CameraService extends BaseService<Camera>{

	@Autowired
	private CameraDao cameraDao;
	@Autowired
	private PSpDao pSpDao;
	
	public CameraService(){
		super.baseDao = SpringContextHolder.getBean("cameraDao");
	}

	
	
	@Override
	protected Specification<Camera> buildSpecification(BaseModel model1) {
		CameraModel cameraModel = (CameraModel)model1;
	
		String cameraId = cameraModel.getCameraId();
		Integer type = cameraModel.getType();
		Integer nowState = cameraModel.getNowState();
		String brand = cameraModel.getBrand();
		String model = cameraModel.getModel();
		Integer cameraState = cameraModel.getCameraState();
		Integer lightState = cameraModel.getLightState();
		Integer drivewayId = cameraModel.getDrivewayId();
		return new Specification<Camera>() {
			@Override
			public Predicate toPredicate(Root<Camera> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if (StringUtils.isNotNullAndEmpty(cameraId)) {
					list.add(cb.like(root.get("cameraId"), "%" + cameraId + "%"));
				}
				if (type !=-1) {
					list.add(cb.equal(root.get("type"), type));
				}
				if (nowState !=-1) {
					list.add(cb.equal(root.get("nowState"), nowState));
				}
				if (StringUtils.isNotNullAndEmpty(brand)) {
					list.add(cb.like(root.get("brand"), "%" + brand
							+ "%"));
				}
				if (StringUtils.isNotNullAndEmpty(model)) {
					list.add(cb.like(root.get("model"), "%" + model
							+ "%"));
				}
				if (cameraState !=-1) {
					list.add(cb.equal(root.get("cameraState"),  cameraState
							));
				}
				if (lightState !=-1) {
					list.add(cb.equal(root.get("lightState"), lightState
							));
				}
				if (drivewayId != null) {
					list.add(cb.equal(root.get("driveway").get("id"),
							drivewayId));
				}
				list.add(cb.equal(root.get("delFlag"), 0));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
	
	public String getGenId(String entitySelfName){
		Camera  camera = baseDao.findMaxIdRec();
		int id = 0;
		if(camera!=null){
			id = camera.getId()+1;
		}
		return entitySelfName+id;
	}
	
	public CameraModel getCameraById(int id){
		Camera camera = cameraDao.findOne(id);
		return CameraExtend.getModelByCamera(camera);
	}
	
	
	public CameraModel getCameraByCameraId(String cameraId){
		Camera camera = cameraDao.findByCameraId(cameraId);
		return CameraExtend.getModelByCamera(camera);
	}
	
	
	public boolean enable(String ids) {
		boolean flag = false;
		List<Integer> idss = super.getIdList(ids);
		int count = 0;
		for(Integer id:idss){
			Camera camera = cameraDao.findOne(id);
			String cameraId = camera.getCameraId();
			ArrangeUtils.updateCameraPspOpen(cameraId);
			int updateCamera = cameraDao.enable(id);
			if(updateCamera==1)
				count++;
		}
		if (idss.size() ==count)
			flag = true;
		return flag;
	}
	
	public boolean disable(String ids) {
		boolean flag = false;
		List<Integer> idss = super.getIdList(ids);
		int count = 0;
		for(Integer id:idss){
			Camera camera = cameraDao.findOne(id);
			String cameraId = camera.getCameraId();
			ArrangeUtils.updateCameraPspClose(cameraId);
			int updateCamera = cameraDao.disable(id);
			if(updateCamera==1)
				count++;
		}
		if (idss.size() ==count)
			flag = true;
		return flag;
	}

	public boolean edit(CameraModel model) {
		boolean flag = true;
		Camera camera = cameraDao.findOne(model.getId());
		if (camera == null) {
			logger.error("");
			flag = false;
			return flag;
		}
		try {
			BeanUtils.copyProperties(model, camera);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		/*if(model.getDrivewayId()!=0){
			Driveway driveway = DrivewayUtils.getDrivewayById(model.getDrivewayId());
			camera.setDriveway(driveway);
		}*/
		camera = cameraDao.save(camera);
		if (camera == null) {
			logger.error("");
			flag = false;
			return flag;
		}
		logger.info("");
		LogUtil.save("摄像头模块", LogUtil.Option.ADD, "修改摄像头信息："+camera.toString());
		return flag;
	}
	public boolean add(CameraModel model) {
		boolean flag = true;
		Camera camera = new Camera();
		try {
			BeanUtils.copyProperties(model, camera);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("", e);
		}

		camera = cameraDao.save(camera);
		if (camera == null) {
			logger.error("");
			flag = false;
			return flag;
		}
		logger.info("");
		LogUtil.save("摄像头模块", LogUtil.Option.ADD, "新增摄像头信息："+camera.toString());
		return flag;
	}
	public boolean delete(int id) {
		Camera camera = cameraDao.findOne(id);
		if (camera == null) {
			return false;
		}
		cameraDao.delete(id);
		LogUtil.save("硬件模块", LogUtil.Option.ADD, "删除摄像头信息："+camera.toString());
		return true;

	}
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            摄像机Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = cameraDao.softdelete(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("摄像机管理", LogUtil.Option.DEL, "删除摄像机：" + id);
			}
		}

		return flag;
	}
	
	public void arrangeCmaeraAndPsp(CameraPSpModel model) throws ServiceException{
		String cameraId = model.getCameraId();
		Camera camera = cameraDao.findByCameraId(cameraId);
		String pspIds = model.getPspId();
		List<Integer> pspId = super.getIdList(pspIds);
		Set<PSp> pSps = new HashSet<PSp>();
		for(Integer id:pspId){
			PSp pSp = pSpDao.findOne(id);
			pSps.add(pSp);
		}
		camera.setpSp(pSps);
		cameraDao.save(camera);
	}



	@Override
	protected List<BaseModel> getModelsByBeans(List<Camera> entities) {
		return  Lists.newArrayList(CameraExtend.coverToModel(entities));
	}
}
