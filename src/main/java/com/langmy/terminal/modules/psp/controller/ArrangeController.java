package com.langmy.terminal.modules.psp.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.beanvalidator.BeanValidators;
import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.utils.excel.ExportExcel;
import com.langmy.terminal.common.utils.excel.ImportExcel;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.FtpTransfer;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.MutiSelectExtend;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.modules.caradmission.utils.CarUtils;
import com.langmy.terminal.modules.device.model.CameraModel;
import com.langmy.terminal.modules.device.model.LedScreenModel;
import com.langmy.terminal.modules.device.model.PSpLockModel;
import com.langmy.terminal.modules.device.service.CameraService;
import com.langmy.terminal.modules.device.service.LedScreenService;
import com.langmy.terminal.modules.device.service.LockService;
import com.langmy.terminal.modules.device.utils.ParkUtils;
import com.langmy.terminal.modules.psp.dao.DragDivDao;
import com.langmy.terminal.modules.psp.model.CameraPSpModel;
import com.langmy.terminal.modules.psp.model.DragDivModel;
import com.langmy.terminal.modules.psp.model.PSpModel;
import com.langmy.terminal.modules.psp.service.ArrangeService;
import com.langmy.terminal.modules.psp.service.PSpService;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

/**
 * 拖拽的controller
 * 
 * @author MC
 *
 */
@Controller
@RequestMapping(value = "/pSp/arrange")
public class ArrangeController extends BaseController {

	@Autowired
	private PSpService pSpService;
	@Autowired
	private CameraService cameraService;
	@Autowired
	private LockService lockService;
	@Autowired
	private LedScreenService ledScreenService;
	@Autowired
	private ArrangeService arrangeService;
	@Autowired
	private DragDivDao dragDivDao;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "pSp/arrange/arrange";
	}
	
	@RequestMapping(value = "/inputMap", method = { RequestMethod.POST })
	public void addMap(@RequestParam MultipartFile[] myfiles,int parkPK,
			HttpServletResponse response,MultipartHttpServletRequest request) throws IOException, Exception {
		Json j = new Json();
		for (MultipartFile myfile : myfiles) {
			if (!myfile.isEmpty()) {
				String fileName = myfile.getOriginalFilename();
				String fileType = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				String newFileName = UUID.randomUUID().toString()
						.replaceAll("-", "")
						+ "." + fileType;
				String url = Global.getConfig("ftpUrl");
				int port = Integer.parseInt(Global.getConfig("ftpPort"));
				String username = Global.getConfig("ftpUserName");
				String password = Global.getConfig("ftpPwd");
				String path = Global.getConfig("ftpOperaterImgPath");
				FtpTransfer.uploadFile(url, port, username, password, path,
						newFileName, myfile.getInputStream());
				String picUrl = path + "/" + newFileName;
				ParkUtils.saveParkMapPath(parkPK, picUrl);
				String headFtpUrl = "ftp://" + username + ":" + password + "@" + url;
				String ftpUrl = headFtpUrl + picUrl;
				j.setSuccess(true);
				j.setMsg(ftpUrl);
			}
			else {
				j.setSuccess(false);
				j.setMsg("上传失败");
			}
	            	
		}
		super.writeJson(response, j);
	}

	
	@RequestMapping(value = "/importTemplate")
	public void importFileTemplate(HttpServletResponse response,RedirectAttributes redirectAttributes) {
		try {
			String fileName = "数据导入模板.xlsx";
			List<BaseModel> list = arrangeService.getDragDiv();
			  /*title 表格标题，传“空值”，表示无标题
			  cls 实体对象，通过annotation.ExportField获取标题
			  type 导出类型（1:导出数据；2：导出模板）*/
			new ExportExcel("dragAndDrop数据", DragDivModel.class, 2).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
		}
	}
	
	
	@RequestMapping(value = "/importExcel", method = { RequestMethod.POST })
	public void importFile(MultipartFile excelInputFile,Integer parkPK,
			RedirectAttributes redirectAttributes,HttpServletResponse response) {
		Json j = new Json();
		try {
			int successNum = 0;
			int failureNum = 0;
			String failureMsg = "";
			ImportExcel ei = new ImportExcel(excelInputFile, 1, 0);
			List<DragDivModel> list = ei.getDataList(DragDivModel.class);
			for (DragDivModel dragDivModel : list) {
				try {
					//if (arrangeService.getSameDragDiv(dragDivModel.getDivId())) {
						//dragDivModel.setParkId(parkPK);
						arrangeService.add(dragDivModel);
						successNum++;
					//} else {
					//	failureMsg="编号" + dragDivModel.getDivId()+ " 已存在; ";
					//	failureNum++;
					//	j.setSuccess(false);
					//	j.setMsg(failureMsg.toString());
					//}
				} catch (ConstraintViolationException ex) {
					failureMsg="编号 " + dragDivModel.getDivId() + " 导入失败：";
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg=message + "; ";
						failureNum++;
					}
					j.setSuccess(false);
					j.setMsg(failureMsg.toString()+failureNum+"条失败");
				} catch (Exception ex) {
					j.setSuccess(false);
					j.setMsg(failureMsg.toString());
				}
			}
			j.setSuccess(true);
			j.setMsg("成功导入"+successNum+"条数据");
		} catch (Exception e) {
			j.setMsg("导入失败！失败信息："+e.getMessage());
		}
		super.writeJson(response, j);
	}

	/**
	 * 获取所有车位ID
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getPspId", method = { RequestMethod.GET })
	protected void getUser(HttpServletResponse response) {
		Json j = new Json();
		List<Select> pspModels = MutiSelectExtend.convertToSelect(
				PSpUtils.getAllPspIdsWithOutCamera(), "id", "pspId", false);
		if (pspModels != null) {
			j.setObject(pspModels);
			j.setSuccess(true);
		} else {
			j.setMsg("获取车位信息失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 获取当前摄像头的model
	 * 
	 * @param divId
	 * @param response
	 */
	@RequestMapping(value = "/showCamera")
	public void getCamera(String divId, HttpServletResponse response) {
		Json j = new Json();
		CameraModel model = cameraService.getCameraByCameraId(divId);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("摄像头信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("摄像头信息获取失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/getParkTree", method = { RequestMethod.GET })
	public void getParkTree(HttpServletResponse response) {
		super.writeJson(response, arrangeService.getParkTreeJson());
	}
	
	/**
	 * 修改摄像头信息
	 * 
	 * @param model
	 * @param response
	 */
	
	//获取当前车辆信息的model
	@RequestMapping(value = "/showCar")
	public void getCar(String licensePlate, HttpServletResponse response) {
		Json j = new Json();
		List<BaseModel> model = CarUtils.getLicensePlates(licensePlate);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("摄像头信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("摄像头信息获取失败");
		}
		super.writeJson(response, j);
	}
	
	//修改摄像头信息
	@RequestMapping(value = "/editCamera", method = { RequestMethod.POST })
	public void edit(CameraModel model, HttpServletResponse response) {
		Json j = new Json();
		if(cameraService.edit(model)){
			j.setSuccess(true);
			j.setMsg("摄像头信息修改成功");
		}else {
			
			j.setSuccess(false);
			j.setMsg("摄像头信息修改失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 保存div信息
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/addDiv", method = { RequestMethod.POST })
	public void add(DragDivModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			if (arrangeService.add(model)) {
				j.setSuccess(true);
			} else {
				j.setSuccess(false);
				j.setMsg("信息保存失败");
			}
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("信息保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 获取所有div信息
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getDiv", method = { RequestMethod.GET })
	public void getDiv(Integer parkPK,HttpServletResponse response) {
		Json j = new Json();
		if(parkPK == null){
			parkPK = 2;
		}
		List<BaseModel> dragDivModel = arrangeService.getDragDiv(parkPK);
		String url = Global.getConfig("ftpUrl");
		String username = Global.getConfig("ftpUserName");
		String password = Global.getConfig("ftpPwd");
		String headFtpUrl = "ftp://" + username + ":" + password + "@" + url;
		String mapPath = ParkUtils.getMapPath(parkPK);
		String ftpUrl = headFtpUrl + mapPath;
		j.setSuccess(true);
		j.setObject(dragDivModel);
		j.setMsg(ftpUrl);
		super.writeJson(response, j);
	}

	/**
	 * 根据divId删除div
	 * 
	 * @param divId
	 * @param response
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/delete")
	public void delete(String divId,String type, HttpServletResponse response)
			throws ServiceException {
		Json j = new Json();
		if (arrangeService.delete(divId,type)) {
			j.setSuccess(true);
			j.setMsg("删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 获取当前车位的model
	 * 
	 * @param divId
	 * @param response
	 */
	@RequestMapping(value = "/showPsp")
	public void showPsp(String divId, HttpServletResponse response) {
		Json j = new Json();
		PSpModel model = pSpService.getChargeRuleByEditPspId(divId);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 修改当前车位信息
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/editPsp", method = { RequestMethod.POST })
	public void editPsp(PSpModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			pSpService.edit(model);
			j.setSuccess(true);
			j.setMsg("规则组修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("规则组修改失败");
			logger.error("规则组修改失败", e);
		}
		super.writeJson(response, j);
	}

	/**
	 * 获取摄像头列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/listCamera", method = { RequestMethod.POST })
	protected void listCamera(String jsonParam, CameraModel model,
			HttpServletResponse response) {
		model.setCameraState(-1);
		model.setLightState(-1);
		model.setNowState(0);
		model.setType(-1);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, cameraService.list(model, dataTableParam));
	}

	/**
	 * 获取车位列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/listPsp", method = { RequestMethod.POST })
	protected void listPsp(String jsonParam, PSpModel model,
			HttpServletResponse response) {
		model.setIsEnable(-1);
		model.setIsRent(-1);
		model.setNowState(0);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, pSpService.list(model, dataTableParam));
	}

	@RequestMapping(value = "/listScreen", method = { RequestMethod.POST })
	protected void list(String jsonParam, LedScreenModel model, HttpServletResponse response) {
		model.setConfigureFlag(0);
		model.setType(-1);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, ledScreenService.list(model, dataTableParam));
	}
	/**
	 * 显示显示屏信息
	 * 
	 * @param divId
	 * @param response
	 */
	@RequestMapping(value = "/showScreen" , method = { RequestMethod.POST })
	public void showScreen(String divId, HttpServletResponse response) {
		Json j = new Json();
		LedScreenModel model = ledScreenService.getLedScreenByLedId(divId);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("显示屏信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("显示屏信息获取失败");
		}
		super.writeJson(response, j);
	}

	
	/**
	 * 修改车位锁信息
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/editLock", method = { RequestMethod.POST })
	public void editLock(PSpLockModel model, HttpServletResponse response) {
		Json j = new Json();
		if (lockService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("车位锁修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车位锁修改失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 显示车位锁信息
	 * 
	 * @param divId
	 * @param response
	 */
	@RequestMapping(value = "/showPspLock" , method = { RequestMethod.POST })
	public void showPspLock(String divId, HttpServletResponse response) {
		Json j = new Json();
		PSpLockModel model = lockService.getPSpLockByPspId(divId);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("车位锁信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("该车位未绑定车位锁");
		}
		super.writeJson(response, j);
	}

	/**
	 * 开启车位锁
	 * 
	 * @param divId
	 * @param response
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/openLock" , method = { RequestMethod.POST })
	public void open(String divId, HttpServletResponse response)
			throws UnknownHostException, IOException {
		Json j = new Json();
		String message = lockService.openOneLock(divId);
		if (StringUtils.isNotNullAndEmpty(message)) {
			j.setSuccess(true);
			j.setMsg(message);
		} else {
			j.setSuccess(false);
			j.setMsg("数据获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 关闭车位锁
	 * 
	 * @param divId
	 * @param response
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/closeLock")
	public void close(String divId, HttpServletResponse response)
			throws UnknownHostException, IOException {
		Json j = new Json();
		String message = lockService.closeOneLock(divId);
		if (StringUtils.isNotNullAndEmpty(message)) {
			j.setSuccess(true);
			j.setMsg(message);
		} else {
			j.setSuccess(true);
			j.setMsg("数据获取失败");
		}
		super.writeJson(response, j);
	}

	// 显示当前车位视频
	/*
	 * @RequestMapping(value = "/showVideo/{cameraId}", method = {
	 * RequestMethod.GET }) public void
	 * showVideo(@PathVariable(value="cameraId") String cameraId,
	 * HttpServletResponse response) { Json j = new Json(); CameraModel model =
	 * cameraService.getVideoByPspId(cameraId); if (model != null) {
	 * j.setSuccess(true); j.setObject(model); j.setMsg("车位视频信息获取成功"); } else {
	 * j.setSuccess(false); j.setMsg("车位视频信息获取失败"); } super.writeJson(response,
	 * j); }
	 */

	/**
	 * 车位摄像头配置
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/arrangePspAndCamera", method = { RequestMethod.POST })
	public void arrangePspAndCamera(CameraPSpModel model,
			HttpServletResponse response) {
		Json j = new Json();
		try {
			cameraService.arrangeCmaeraAndPsp(model);
			j.setSuccess(true);
			j.setMsg("规则组修改成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("规则组修改失败");
			logger.error("规则组修改失败", e);
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 根据编号查找车位高亮显示
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/getAllPspLikeParams", method = { RequestMethod.GET })
	public void queryPsp(String divId,HttpServletResponse response) {
		Json j = new Json();
		List<BaseModel> dragDivModels = arrangeService.getAllDragDivsByDivId(divId);
		if (dragDivModels.size()>0) {
			j.setSuccess(true);
			j.setObject(dragDivModels);
			j.setMsg("获取数据成功");
		} else {
			j.setSuccess(false);
			j.setMsg("没有数据或者获取错误");
		}
		super.writeJson(response, j);
	}
}
