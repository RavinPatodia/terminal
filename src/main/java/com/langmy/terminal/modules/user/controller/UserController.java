package com.langmy.terminal.modules.user.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.langmy.terminal.common.config.Constant.UserState;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.web.BaseController;
import com.langmy.terminal.common.web.DataTable;
import com.langmy.terminal.common.web.DataTableParameter;
import com.langmy.terminal.common.web.Json;
import com.langmy.terminal.common.web.Select;
import com.langmy.terminal.common.web.SelectExtend;
import com.langmy.terminal.modules.user.dao.UserDao;
import com.langmy.terminal.modules.user.model.CarModel;
import com.langmy.terminal.modules.user.model.UserModel;
import com.langmy.terminal.modules.user.service.UGroupService;
import com.langmy.terminal.modules.user.service.UserService;

/**
 * 用户Controller
 * 
 * @author ZZD
 *
 */
@Controller
@RequestMapping(value = "/user/user")
public class UserController extends BaseController {
	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService;
	@Autowired
	UGroupService uGroupService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "user/user/user";
	}

	/**
	 * 获得客户列表
	 * 
	 * @param jsonParam
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	protected void list(String jsonParam, UserModel model,
			HttpServletResponse response) {
		DataTableParameter dataTableParam = getDataTableParameterByJsonParam(jsonParam);
		DataTable<BaseModel> dt = userService.list(model, dataTableParam);
		if (logger.isDebugEnabled())
			logger.debug(JSONObject.toJSONString(dt));
		super.writeJson(response, dt);
	}

	/**
	 * 添加客户
	 * 
	 * @param request
	 * @param response
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException {
		Json j = new Json();
		String carStr = request.getParameter("carStr");
		String userStr = request.getParameter("userStr");
		List<CarModel> carModels = JSON.parseArray(carStr, CarModel.class);
		UserModel userModel = JSON.parseObject(userStr, UserModel.class);
		if (userService.add(userModel, carModels)) {
			j.setSuccess(true);
			j.setMsg("添加用户成功");
		} else {
			j.setSuccess(false);
			j.setMsg("添加用户失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param response
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException {
		Json j = new Json();
		String carStr = request.getParameter("carStr");
		String userStr = request.getParameter("userStr");
		List<CarModel> carModels = JSON.parseArray(carStr, CarModel.class);
		UserModel userModel = JSON.parseObject(userStr, UserModel.class);
		if (userService.edit(userModel, carModels)) {
			j.setSuccess(true);
			j.setMsg("用户信息修改成功");
		} else {
			j.setSuccess(false);
			j.setMsg("用户信息修改失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 查看详细信息
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 * @throws IntrospectionException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	@RequestMapping(value = "/getUser/{id}", method = { RequestMethod.GET })
	public void getUser(@PathVariable(value = "id") Integer id,
			HttpServletResponse response) throws InstantiationException,
			IllegalArgumentException, ClassNotFoundException,
			IntrospectionException, IOException {
		Json j = new Json();
		UserModel model = userService.view(id);
		if (model != null) {
			j.setSuccess(true);
			j.setObject(model);
			j.setMsg("用户信息获取成功");
		} else {
			j.setSuccess(false);
			j.setMsg("用户信息获取失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "/del/{ids}", method = { RequestMethod.GET })
	public void del(@PathVariable(value = "ids") String ids,
			HttpServletResponse response) {
		Json j = new Json();
		if (userService.del(ids)) {
			j.setSuccess(true);
			j.setMsg("删除客户成功");
		} else {
			j.setSuccess(false);
			j.setMsg("删除客户失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 加入黑名单
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/addBlackList")
	public void addBlackList(UserModel model, HttpServletResponse response) {
		Json j = new Json();
		if (!beanValidator(j, model)) {
			j.setSuccess(false);
			j.setMsg("黑名单保存失败");
			super.writeJson(response, j);
			return;
		}
		if (userService.addBlickList(model)) {
			j.setSuccess(true);
			j.setMsg("黑名单保存成功");
		} else {
			j.setSuccess(false);
			j.setMsg("黑名单保存失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 取得长期用户组的收费规则（套餐）
	 * 
	 * @param uGroupId
	 * @param response
	 */
	@RequestMapping(value = "/getAnchorRents/{uGroupId}")
	public void getAnchorRents(
			@PathVariable(value = "uGroupId") Integer uGroupId,
			HttpServletResponse response) {
		Json j = new Json();
		UserModel userModel = userService.getAnchorRentsByUGId(uGroupId);
		if (userModel != null) {
			j.setSuccess(true);
			j.setObject(userModel);
			j.setMsg("获取长期用户组的收费规则成功");
		} else {
			j.setSuccess(false);
			j.setMsg("获取长期用户组的收费规则失败");
		}
		super.writeJson(response, j);
	}

	@RequestMapping(value = "/recharge", method = { RequestMethod.POST })
	public void recharge(UserModel model, HttpServletResponse response)
			throws ServiceException {
		Json j = new Json();
		if (userService.recharge(model)) {
			j.setSuccess(true);
			j.setMsg("充值成功");
		} else {
			j.setSuccess(false);
			j.setMsg("充值失败");
		}
		super.writeJson(response, j);
	}

	/**
	 * 验证操作员身份证唯一性
	 * 
	 * @param idCard
	 * @param response
	 */
	@RequestMapping(value = "/validateIdCard", method = { RequestMethod.GET })
	public void validateIdCard(String idCard, HttpServletResponse response) {
		List<User> users = userDao.findByIdCardAndStateNot(idCard,
				UserState.DELETED.getValue());
		if (users == null || users.size() <= 0) {
			super.writeJson(response, true);
		} else {
			super.writeJson(response, false);
		}
	}

	@RequestMapping(value = "/getName")
	protected void getName(String name, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(userDao
				.findByNameLikeAndStateNot("%" + name + "%",
						UserState.DELETED.getValue()), "id", "name");
		super.writeJson(response, selects);
	}

	/**
	 * 获得未拉黑客户下拉框
	 * 
	 * @param name
	 *            姓名
	 * @param response
	 */
	@RequestMapping(value = "/getUserNotBlack")
	protected void getUserNotBlack(String name, HttpServletResponse response) {
		List<Select> selects = SelectExtend.convertToSelect(
				userService.getUserNotBlack("%" + name + "%"), "id", "name");
		super.writeJson(response, selects);
	}

}
