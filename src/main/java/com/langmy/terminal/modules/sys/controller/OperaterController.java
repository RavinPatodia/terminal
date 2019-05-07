package com.langmy.terminal.modules.sys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.config.Constant.SystemType;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.FtpTransfer;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.Select2;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.sys.dao.OperaterDao;
import com.langmy.terminal.modules.sys.model.OperaterModel;
import com.langmy.terminal.modules.sys.service.OperaterService;
import com.langmy.terminal.modules.sys.service.RoleService;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 操作员控制层
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/admin/sys/operater")
public class OperaterController extends BaseController {

	@Autowired
	private OperaterService operaterService;
	@Autowired
	private OperaterDao operaterDao;
	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "system/operater/operater";
	}

	/**
	 * 添加操作员
	 * 
	 * @param model
	 *            操作员模型类
	 * @param response
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(OperaterModel model, @RequestParam MultipartFile[] myfiles,
			HttpServletResponse response) throws IOException, Exception {
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
				model.setPicUrl(picUrl);
			}
		}

		if (operaterService.add(model)) {
			j.setSuccess(true);
			j.setMsg("操作员保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("操作员保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 编辑操作员
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(OperaterModel model,
			@RequestParam MultipartFile[] myfiles, HttpServletResponse response)
			throws IOException, Exception {
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
				model.setPicUrl(picUrl);
			}
		}
		if (operaterService.edit(model)) {
			j.setSuccess(true);
			j.setMsg("操作员修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("操作员修改失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 删除操作员
	 * 
	 * @param ids
	 *            操作员id字符串
	 * @param response
	 */
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (operaterService.delete(ids)) {
			j.setSuccess(true);
			j.setMsg("操作员删除成功");
		} else {
			j.setSuccess(false);
			j.setMsg("操作员删除失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 启用操作员
	 * 
	 * @param ids
	 *            操作员id字符串
	 * @param response
	 */
	@RequestMapping(value = "/enable/{ids}", method = { RequestMethod.GET })
	public void enable(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (operaterService.enable(ids)) {
			j.setSuccess(true);
			j.setMsg("启用操作员成功");
		} else {
			j.setSuccess(false);
			j.setMsg("禁用操作员失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 禁用操作员
	 * 
	 * @param ids
	 *            操作员id字符串
	 * @param response
	 */
	@RequestMapping(value = "/disable/{ids}", method = { RequestMethod.GET })
	public void disable(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (operaterService.disable(ids)) {
			j.setSuccess(true);
			j.setMsg("禁用操作员成功");
		} else {
			j.setSuccess(false);
			j.setMsg("禁用操作员失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 *            操作员id
	 * @param response
	 */
	@RequestMapping(value = "/getOperater/{id}", method = { RequestMethod.GET })
	public void getOperater(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) {
		Json j = new Json();
		OperaterModel model = operaterService.view(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("操作员信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("操作员信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 操作员绑定角色
	 * 
	 * @param model
	 *            操作员模型类
	 * @param response
	 */
	@RequestMapping(value = "/bindRole", method = { RequestMethod.POST })
	public void bindRole(OperaterModel model, HttpServletResponse response) {
		Json j = new Json();
		if (operaterService.bindRoles(model)) {
			j.setSuccess(true);
			j.setMsg("角色绑定成功");
		} else {
			j.setSuccess(false);
			j.setMsg("角色绑定失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 获得所有角色
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getRolesUrl")
	protected void getRoles(HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				roleService.getRoles(), "id", "roleName");
		super.writeJson(response, selects);
	}

	/**
	 * 获得操作员列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	// @RequiresPermissions("sys:operater:view")
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	protected void list(String jsonParam, OperaterModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		super.writeJson(response, operaterService.list(model, dataTableParam));
	}

	/**
	 * 修改密码
	 * 
	 * @param model
	 *            操作员模型类
	 * @param response
	 */
	@RequestMapping(value = "/changePwd", method = { RequestMethod.POST })
	public void changePwd(OperaterModel model, HttpServletResponse response) {
		Json j = new Json();
		if (operaterService.changePwd(model)) {
			j.setSuccess(true);
			j.setMsg("修改密码成功");
		} else {
			j.setSuccess(false);
			j.setMsg("密码错误");
		}
		super.writeJson(response, j);
	}

	/**
	 * 查看当前登录操作员详细信息
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getLoginOperater", method = { RequestMethod.GET })
	public void getLoginOperater(HttpServletResponse response) {
		Json j = new Json();
		Operater oper = OperaterUtils.getOperater();
		OperaterModel model = operaterService.view(oper.getId());
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("操作员信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("操作员信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 验证操作员账户名唯一性
	 * 
	 * @param operName
	 * @param response
	 */
	@RequestMapping(value = "/validateOperName", method = { RequestMethod.GET })
	public void validateOperName(String operName, HttpServletResponse response) {
		Operater oper = operaterDao.findByOperNameAndDelFlagFalse(operName);
		if (oper == null) {
			super.writeJson(response, true);
		} else {
			super.writeJson(response, false);
		}
	}

	/**
	 * 验证操作员身份证唯一性
	 * 
	 * @param idCard
	 * @param response
	 */
	@RequestMapping(value = "/validateIdCard", method = { RequestMethod.GET })
	public void validateIdCard(String idCard, HttpServletResponse response) {
		List<Operater> opers = operaterDao.findByIdCardAndDelFlagFalse(idCard);
		if (opers == null || opers.size() <= 0) {
			super.writeJson(response, true);
		} else {
			super.writeJson(response, false);
		}
	}

	/**
	 * 验证操作员身份证唯一性
	 * 
	 * @param idCard
	 * @param response
	 */
	@RequestMapping(value = "/validateIdCardEdit", method = { RequestMethod.GET })
	public void validateIdCardEdit(String idCard, String operName,
			HttpServletResponse response) {
		List<Operater> opers = operaterDao.findByIdCardAndDelFlagFalse(idCard);

		if (opers == null || opers.size() <= 0) {
			super.writeJson(response, true);
		} else if (opers.get(0).getOperName().equals(operName)) {
			super.writeJson(response, true);
		} else {
			super.writeJson(response, false);
		}
	}

	/**
	 * 根据操作员账户名模糊查询操作员
	 * 
	 * @param operName
	 *            操作员账户名
	 * @param response
	 */
	@RequestMapping(value = "/getOperNames")
	protected void getOperNames(String operName, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				operaterService.getOperByOperName(operName), "operName",
				"operName");
		super.writeJson(response, selects);
	}

	/**
	 * 获得下拉框
	 * 
	 * @param operName
	 *            账户名
	 * @param response
	 */
	@RequestMapping(value = "/getOperIds")
	protected void getOperIds(String operName, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				operaterService.getOperByOperName(operName), "id", "operName");
		super.writeJson(response, selects);
	}

	/**
	 * 根据操作员姓名模糊查询操作员
	 * 
	 * @param name
	 *            操作员姓名
	 * @param response
	 */
	@RequestMapping(value = "/getNames")
	protected void getNames(String name, HttpServletResponse response) {
		List<Select2> selects = SelectExtend.convertToSelect2(
				operaterService.getOperByName(name), "name", "name");
		super.writeJson(response, selects);
	}

	/**
	 * 根据操作员姓名模糊查询操作员
	 * 
	 * @param name
	 *            操作员姓名
	 * @param response
	 */
	@RequestMapping(value = "/getOperNameIdTerminal")
	protected void getNameIdByType(String name, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				operaterService.getOperByNameAndType(name,
						SystemType.PALTE.getValue()), "id", "operName");
		super.writeJson(response, selects);
	}
}
