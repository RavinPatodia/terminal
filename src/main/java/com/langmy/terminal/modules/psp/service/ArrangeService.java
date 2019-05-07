package com.langmy.terminal.modules.psp.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.entity.DragDiv;
import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.dao.CameraDao;
import com.langmy.terminal.modules.device.dao.ParkDao;
import com.langmy.terminal.modules.device.utils.CameraUtils;
import com.langmy.terminal.modules.device.utils.LedScreenUtils;
import com.langmy.terminal.modules.psp.dao.DragDivDao;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.model.DragDivModel;
import com.langmy.terminal.modules.psp.service.extend.ArrangeExtend;

/**
 * 拖拽的service
 * 
 * @author MC
 *
 */
@Service
public class ArrangeService extends BaseService<DragDiv> {

	@Autowired
	private DragDivDao dragDivDao;
	@Autowired
	private PSpDao pSpDao;
	@Autowired
	private CameraDao cameraDao;
	@Autowired
	private ParkDao parkDao;

	public ArrangeService() {
		super.baseDao = SpringContextHolder.getBean("dragDivDao");
	}
	
	private String str ="";
	/**
	 * 添加方法
	 * 
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public boolean add(DragDivModel model) throws ServiceException {
		DragDiv dragDiv = ArrangeExtend.addByModel(model);
		if (dragDiv == null) {
			return false;
		}
		return true;
	}

	public String getParkTreeJson(){
		 str = "[";  
	     // 从根开始  
	     this.getTreeJson(1);
	     //去掉最后一个，符合json格式
	     str = str.substring(0, str.length()-1);
	     str += "]";  
	     return str; 
	}
	
	/**  
   * 无限递归获得jsTree的json字串  
   *   
   * @param parentId  
   *            父权限id  
   * @return  
   */  
   private void getTreeJson(Integer id)  
   {  
   	List<Park> ps = parkDao.findByParentId(id);
   	if(!ps.isEmpty()){
   		for(Park p:ps){
   			if(!p.isDelFlag()){
   				String c = p.getParentId()==1?"#":p.getParentId().toString();		
       			str += "{\"id\":\"" + p.getId()  
       	                + "\",\"parent\":\""+c+"\",\"icon\":\""+""+"\",\"text\":\"" + p.getName() + "\"},";
       			this.getTreeJson(p.getId());
   			}
   		}
   	}
   }
	
	/**
	 * 删除方法
	 * 
	 * @param divId
	 * @return
	 * @throws ServiceException
	 */
	public boolean delete(String divId,String type) throws ServiceException {
		DragDiv dragDiv = dragDivDao.findByDivId(divId);
		dragDivDao.delete(dragDiv);
		if(type.equals("camera open")){
			CameraUtils.updateStateClose(divId);
		}
		else if(type.equals("psp open")){
			pSpDao.updateNowStateClose(divId);
		}else if(type.equals("screen open")){
			LedScreenUtils.updateStateClose(divId);
		}
		return true;
	}

	/**
	 * 获取所有的DragDiv实体
	 * 
	 * @return
	 */
	public List<BaseModel> getDragDiv() {
		List<DragDiv> dragDivs = dragDivDao.findAll();
		return getModelsByBeans(dragDivs);
	}
	
	/**
	 * 根据divId获取所有的DragDiv实体
	 * 
	 * @return
	 */
	public List<BaseModel> getAllDragDivsByDivId(String divId) {
		List<DragDiv> dragDivs = dragDivDao.getDragDivs("%"+divId+"%");
		return getModelsByBeans(dragDivs);
	}
	
	/**
	 * 根据parkId获取所有的DragDiv实体
	 * 
	 * @return
	 */
	public List<BaseModel> getDragDiv(int parkId) {
		List<DragDiv> dragDivs = dragDivDao.findByParkId(parkId);
		return getModelsByBeans(dragDivs);
	}
	
	public boolean getSameDragDiv(String divId){
		DragDiv dragDiv = dragDivDao.findByDivId(divId);
		if(dragDiv != null){
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.langmy.park.common.service.BaseService#getModelsByBeans(java.util
	 * .List)
	 */
	@Override
	protected List<BaseModel> getModelsByBeans(List<DragDiv> dragDivs) {
		List<DragDivModel> dragDivModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(dragDivs)) {
			return Lists.newArrayList(dragDivModels);
		}
		try {
			dragDivModels = BeanUtils.copyListProperties(dragDivs,
					DragDivModel.class);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("实体类与Model类数组转化失败", e);
		}

		return Lists.newArrayList(dragDivModels);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.langmy.park.common.service.BaseService#buildSpecification(com.langmy
	 * .park.common.model.BaseModel)
	 */
	@Override
	protected Specification<DragDiv> buildSpecification(BaseModel model) {
		return null;
	}

	/**
	 * 修改状态的接口方法
	 * 
	 * @param divId
	 * @return
	 */
	public boolean updateCameraPspOpen(String divId) {
		int count = dragDivDao.updatePspCameraStart(divId);
		if (count <= 0) {
			return false;
		} else
			return true;
	}

	/**
	 * 修改状态的接口方法
	 * 
	 * @param divId
	 * @return
	 */
	public boolean updateCameraPspClose(String divId) {
		int count = dragDivDao.updatePspCameraStop(divId);
		if (count <= 0) {
			return false;
		} else
			return true;
	}

	/**
	 * 根据DivId改变当前车位上的车辆信息
	 * 
	 * @param licensePlate
	 * @param divId
	 */
	public void updatePspCar(String licensePlate, String divId) {
		DragDiv dragDiv = dragDivDao.findByDivId(divId);
		if (dragDiv != null) {
			dragDiv.setLicensePlate(licensePlate);
			dragDivDao.save(dragDiv);
		}
	}
}
