package com.langmy.terminal.modules.psp.service.extend;

import java.lang.reflect.InvocationTargetException;

import com.langmy.terminal.common.entity.Camera;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.DragDiv;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.PSpLock;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.extend.BaseExtend;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.device.utils.CameraUtils;
import com.langmy.terminal.modules.device.utils.LedScreenUtils;
import com.langmy.terminal.modules.psp.dao.DragDivDao;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.model.DragDivModel;

public class ArrangeExtend extends BaseExtend{
	private static  DragDivDao dragDivDao= SpringContextHolder.getBean(DragDivDao.class); 
	private static PSpDao pspDao = SpringContextHolder.getBean(PSpDao.class);

	/**
	 * model类转实体类
	 * 
	 * @param dragModel
	 * @return
	 * @throws ServiceException
	 */
	public static DragDiv addByModel(DragDivModel dragModel) throws ServiceException {
		DragDiv dragDiv = new DragDiv();
		String divId = dragModel.getDivId();
		DragDiv deleteDragDiv = dragDivDao.findByDivId(divId);
		if (deleteDragDiv != null) {
			String divType = deleteDragDiv.getType();
			if (divType.equals("1")) {
				pspDao.updateNowStateOpen(divId);
				PSp pSp = pspDao.findByPspId(divId);
				boolean enableBoolean = pSp.getIsEnable();
				if (enableBoolean == true) {
					deleteDragDiv.setEnable(1);
				} else {
					deleteDragDiv.setEnable(0);
				}
				Car car = pSp.getCar();
				if (car != null) {
					String divVar2 = car.getLicensePlate();
					deleteDragDiv.setLicensePlate(divVar2);
				} else{
					deleteDragDiv.setLicensePlate(null);
				}
				PSpLock pSpLock = pSp.getPSpLock();
				if (pSpLock != null) {
					String pspLock = pSpLock.getLockId();
					deleteDragDiv.setPspLockId(pspLock);
				} else{
					deleteDragDiv.setPspLockId(null);
				}
				deleteDragDiv.setParkId(pSp.getPark().getId());
			} else if(divType.equals("0")) {
				CameraUtils.updateStateOpen(divId);
				Camera camera = CameraUtils.findByCameraId(divId);
				Integer enable = camera.getCameraState();
				deleteDragDiv.setEnable(enable);
			}else if(divType.equals("2")) {
				LedScreenUtils.updateStateOpen(divId);
			}
			deleteDragDiv.setDivX(dragModel.getDivX());
			deleteDragDiv.setDivY(dragModel.getDivY());
			dragDiv = dragDivDao.save(deleteDragDiv);
		}
		else{
			try {
				BeanUtils.copyProperties(dragModel, dragDiv);
			} catch (IllegalAccessException | InvocationTargetException
					| IllegalArgumentException e) {
				logger.error("实体类转化失败", e);
			}
			String divType = dragDiv.getType();
			if (divType.equals("1")) {
				pspDao.updateNowStateOpen(divId);
				PSp pSp = pspDao.findByPspId(divId);
				boolean enableBoolean = pSp.getIsEnable();
				if (enableBoolean == true) {
					dragDiv.setEnable(1);
				} else {
					dragDiv.setEnable(0);
				}
				Car car = pSp.getCar();
				if (car != null) {
					String divVar2 = car.getLicensePlate();
					dragDiv.setLicensePlate(divVar2);
				} else{
					dragDiv.setLicensePlate(null);
				}
				PSpLock pSpLock = pSp.getPSpLock();
				if (pSpLock != null) {
					String pspLock = pSpLock.getLockId();
					dragDiv.setPspLockId(pspLock);
				} else{
					dragDiv.setPspLockId(null);
				}
			} else if(divType.equals("0")) {
				CameraUtils.updateStateOpen(divId);
				Camera camera = CameraUtils.findByCameraId(divId);
				Integer enable = camera.getCameraState();
				dragDiv.setEnable(enable);
			}else if(divType.equals("2")) {
				LedScreenUtils.updateStateOpen(divId);
			}
			dragDiv = dragDivDao.save(dragDiv);
		}
		return dragDiv;
	}

}
