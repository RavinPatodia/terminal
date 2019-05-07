package com.langmy.terminal.modules.device.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.device.model.PSpLockModel;
import com.langmy.terminal.modules.device.service.LockService;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

@Controller
@RequestMapping(value = "device/pSpLock")
public class LockController extends BaseController {

	@Autowired
	LockService lockService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "device/pSpLock/pSpLock";
	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, PSpLockModel model, HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, lockService.list(model, dataTableParam));
	}
	@RequestMapping(value = "/getPspId")
	protected void getAreaId(String pspId,HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				PSpUtils.getAllPspIdsByPspLock(pspId), "id", "pspId");
		super.writeJson(response, selects);
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(PSpLockModel model, HttpServletResponse response) throws ServiceException {
		Json j = new Json();
		if (!beanValidator(j,model)) {
			j.setSuccess(false);
			j.setMsg("车位锁保存失败");
			//j.setObject(m);
			super.writeJson(response, j);
			return;
		}
		if (lockService.add(model)) {
			j.setSuccess(true);
			j.setMsg("车位锁保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车位锁保存失败");
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(PSpLockModel model, HttpServletResponse response) {
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

	@RequestMapping(value = "/getLock/{id}", method = { RequestMethod.GET })
	public void getLock(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		Json j = new Json();
		PSpLockModel model = lockService.getLockById(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("车位锁信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车位锁信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 打开车位锁
	 * @param ids 
	 * @param response
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/openLock/{ids}", method = { RequestMethod.GET })
	public void openLock(@PathVariable(value="ids")String ids, HttpServletResponse response) throws UnknownHostException, IOException {
		Json j = new Json();
		String errorLock = lockService.openLock(ids);
		if (!StringUtils.isNotNullAndEmpty(errorLock)) {
			j.setSuccess(true);
			j.setMsg("开启成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车位锁连接失败,编号为："+errorLock);
		}
		super.writeJson(response, j);
	}

	/**
	 * 关闭车位锁
	 * @param ids
	 * @param response
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@RequestMapping(value = "/closeLock/{ids}", method = { RequestMethod.GET })
	public void closeLock(@PathVariable(value="ids")String ids, HttpServletResponse response) throws UnknownHostException, IOException {
		Json j = new Json();
		String errorLock = lockService.closeLock(ids);
		if (!StringUtils.isNotNullAndEmpty(errorLock)) {
			j.setSuccess(true);
			j.setMsg("关闭成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车位锁连接失败,编号为："+errorLock);
		}
		super.writeJson(response, j);
	}
	
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (lockService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("车位锁删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("车位锁删除失败");
		}
		super.writeJson(response, j);
	}
}
