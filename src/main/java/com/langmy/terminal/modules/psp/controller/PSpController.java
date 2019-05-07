package com.langmy.terminal.modules.psp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.beanvalidator.BeanValidators;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.utils.excel.ExportExcel;
import com.langmy.terminal.common.utils.excel.ImportExcel;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.Select2;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.device.utils.ParkUtils;
import com.langmy.terminal.modules.psp.model.PSpModel;
import com.langmy.terminal.modules.psp.model.RechargeRecModel;
import com.langmy.terminal.modules.psp.service.PSpService;
import com.langmy.terminal.modules.psp.service.extend.PspExtend;

/**
 * 车位的controller
 * 
 * @author MC
 *
 */
@Controller
@RequestMapping(value = "/pSp/pSp")
public class PSpController extends BaseController {

	@Autowired
	private PSpService pSpService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "pSp/pSp/pSp";
	}

	@RequestMapping(value = "/export")
	public void exportFile(PSpModel model, String jsonParam,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "车位数据" + DateUtils.getDate("yyyyMMddHHmmss")	+ ".xlsx";
			List<BaseModel> pspList = pSpService.getAllPspWithoutParam();
			// 如果传递 -1 则为不分页，返回所有数据
			new ExportExcel("车位数据",PSpModel.class).setDataList(pspList).write(response,fileName)
					.dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出车位失败！失败信息：" + e.getMessage());
		}
	}

	@RequestMapping(value = "/import", method = { RequestMethod.POST })
	public void importFile(MultipartFile excelInputFile,
			RedirectAttributes redirectAttributes,HttpServletResponse response) {
		Json j = new Json();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(excelInputFile, 1, 0);
			List<PSp> list = ei.getDataList(PSp.class);
			for (PSp psp : list) {
				try {
					if (pSpService.getSamePspId(psp.getPspId())&&psp.getPark() != null) {
						successNum++;
					} else {
						failureMsg.append(psp.getPspId()+",");
						failureNum++;
						j.setSuccess(false);
						j.setMsg(failureMsg.toString());
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>车位编号 " + psp.getPspId() + " 导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
					j.setSuccess(false);
					j.setMsg(failureMsg.toString());
				} catch (Exception ex) {
					j.setSuccess(false);
					j.setMsg(failureMsg.toString());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条车位，错误车位编号如下：");
			}
			j.setSuccess(true);
			j.setMsg("成功导入"+successNum+"条数据,"+failureMsg);
		} catch (Exception e) {
			j.setMsg("导入失败！失败信息："+e.getMessage());
		}
		super.writeJson(response, j);
	}
 
	@RequestMapping(value = "/importTemplate")
	public void importFileTemplate(HttpServletResponse response,RedirectAttributes redirectAttributes) {
		try {
			String fileName = "车位数据导入模板.xlsx";
			List<BaseModel> list = pSpService.getAllPspWithoutParam();
			  /*title 表格标题，传“空值”，表示无标题
			  cls 实体对象，通过annotation.ExportField获取标题
			  type 导出类型（1:导出数据；2：导出模板）*/
			new ExportExcel("车位数据", PSpModel.class, 2).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
		}
	}

	/**
	 * 判断输入的车位编号是否已存在
	 * 
	 * @param pspId
	 * @param response
	 */
	@RequestMapping(value = "/getSamePspId", method = { RequestMethod.POST })
	public void getSamePspId(String pspId, HttpServletResponse response) {
		Json j = new Json();
		try {
			if (pSpService.getSamePspId(pspId)) {
				j.setSuccess(true);
				j.setMsg("车位编号可用");
			} else {
				j.setSuccess(false);
				j.setMsg("车位编号已存在");
			}
		} catch (ServiceException e) {
			j.setSuccess(false);
		}
		super.writeJson(response, j);
	}

	/**
	 * 获取车位的列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, PSpModel model,
			HttpServletResponse response) {
		model.setNowState(-1);
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, pSpService.list(model, dataTableParam));
	}

	/**
	 * 添加车位的方法
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(PSpModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			if (pSpService.add(model)) {
				j.setSuccess(true);
				j.setMsg("车位信息组保存成功");
			} else {
				j.setSuccess(false);
				j.setMsg("车位信息组保存失败");
			}
		} catch (ServiceException e) {
			logger.error("车位信息组新增失败", e);
			j.setSuccess(false);
			j.setMsg("车位信息组保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 批量添加车位的方法
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/batchAdd", method = { RequestMethod.POST })
	public void batchAdd(PSpModel model, HttpServletResponse response) {
		Json j = new Json();
		try {
			if (pSpService.batchAdd(model)) {
				j.setSuccess(true);
				j.setMsg("车位信息组保存成功");
			} else {
				j.setSuccess(false);
				j.setMsg("车位信息组保存失败");
			}
		} catch (ServiceException e) {
			logger.error("车位信息组新增失败", e);
			j.setSuccess(false);
			j.setMsg("车位信息组保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 车位租赁的方法
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/addRent", method = { RequestMethod.POST })
	public void addRent(RechargeRecModel model, HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			model.setRechargeTime(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e) {
			logger.error("时间转换失败", e);
		}
		model.setRechargeType("2");
		Json j = new Json();
		try {
			pSpService.addRent(model);
			j.setSuccess(true);
			j.setMsg("保存成功");
		} catch (ServiceException e) {
			j.setSuccess(false);
			j.setMsg("保存失败");
			logger.error("保存失败", e);
		}
		super.writeJson(response, j);
	}

	/**
	 * 编辑车位
	 * 
	 * @param model
	 * @param response
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(PSpModel model, HttpServletResponse response) throws ServiceException {
		Json j = new Json();
		if(pSpService.edit(model)){
			j.setSuccess(true);
			j.setMsg("车位信息修改成功");
		}else{
			j.setSuccess(false);
			j.setMsg("车位信息修改失败");
			logger.error("车位信息修改失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 根据id获取车位的model
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/getPSp/{id}", method = { RequestMethod.GET })
	public void getPSp(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		PSpModel model = pSpService.getPspById(id);

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
	 * 根据ids启用车位
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/enable/{ids}", method = { RequestMethod.GET })
	public void enable(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (!StringUtils.isNotNullAndEmpty(pSpService.enable(ids))) {
			j.setSuccess(true);
			j.setMsg("启用成功");
		} else {
			j.setSuccess(false);
			j.setMsg(pSpService.enable(ids));
		}
		super.writeJson(response, j);
	}

	/**
	 * 根据ids删除车位
	 * 
	 * @param ids
	 * @param response
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/delete/{ids}", method = { RequestMethod.GET })
	public void delete(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) throws ServiceException {
		Json j = new Json();
		if (pSpService.deletePsp(ids)) {
			j.setSuccess(true);
			j.setMsg("删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		super.writeJson(response, j);
	}
	
	/**
	 * 根据ids禁用车位
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/disable/{ids}", method = { RequestMethod.GET })
	public void disable(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		String message = pSpService.disable(ids);
		if (!StringUtils.isNotNullAndEmpty(message)) {
			j.setSuccess(true);
			j.setMsg("禁用成功");
		} else {
			j.setSuccess(false);
			j.setMsg(message);
		}
		super.writeJson(response, j);
	}
	/**
	 * 获取用户
	 * 
	 * @param uacc
	 * @param response
	 */
	@RequestMapping(value = "/getUser")
	protected void getUser(String uacc, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				pSpService.getUser(uacc), "id", "uacc");
		super.writeJson(response, selects);
	}

	/**
	 * 获取区域
	 * 
	 * @param areaId
	 * @param response
	 */
	@RequestMapping(value = "/getAreaId")
	protected void getAreaId(String areaId, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				pSpService.getAreaId(areaId), "name", "name");
		super.writeJson(response, selects);
	}
	
	/**
	 * 获取停车场
	 * 
	 * @param areaId
	 * @param response
	 */
	@RequestMapping(value = "/getPark")
	protected void getPark(String parkName, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				ParkUtils.getParks(parkName), "name", "name");
		super.writeJson(response, selects);
	}
	
	/**
	 * 获取park
	 * 
	 * @param name
	 * @param response
	 */
	@RequestMapping(value = "/getParks")
	protected void getParks(String name, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				pSpService.getParks(name), "id", "name");
		super.writeJson(response, selects);
	}

	/**
	 * 取得长期用户组的收费规则（套餐）
	 * 
	 * @param pspId
	 * @param userId
	 * @param response
	 */
	@RequestMapping(value = "/getAnchorRents", method = { RequestMethod.POST })
	public void getAnchorRents(String pspId,HttpServletResponse response) {
		Json j = new Json();
		RechargeRecModel rechargeRuleModel = PspExtend
				.getAnchorRentsByPspId(pspId);
		if (rechargeRuleModel != null) {
			j.setSuccess(true);
			j.setObject(rechargeRuleModel);
			j.setMsg("获取收费规则成功");
		} else {
			j.setSuccess(false);
			j.setMsg("获取收费规则失败");
		}
		super.writeJson(response, j);
	}

}
