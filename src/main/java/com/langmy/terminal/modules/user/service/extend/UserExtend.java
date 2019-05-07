package com.langmy.terminal.modules.user.service.extend;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.langmy.terminal.common.config.Constant.ConAddType;
import com.langmy.terminal.common.entity.Account;
import com.langmy.terminal.common.entity.ContactAddr;
import com.langmy.terminal.common.entity.ContactWay;
import com.langmy.terminal.common.entity.User;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.caradmission.model.CarModel;
import com.langmy.terminal.modules.caradmission.utils.CarUtils;
import com.langmy.terminal.modules.user.dao.AccountDao;
import com.langmy.terminal.modules.user.dao.ContactAddrDao;
import com.langmy.terminal.modules.user.dao.ContactWayDao;
import com.langmy.terminal.modules.user.model.UserModel;
import com.langmy.terminal.modules.user.utils.BlacklistUtils;

/**
 * 客户组Model和实体转化类
 * 
 * @author zhaozhedan
 *
 */
public class UserExtend {

	protected static Logger logger = LoggerFactory.getLogger(UserExtend.class);
	private static AccountDao accountDao = SpringContextHolder
			.getBean(AccountDao.class);
	private static ContactAddrDao contactAddrDao = SpringContextHolder
			.getBean(ContactAddrDao.class);
	private static ContactWayDao contactWayDao = SpringContextHolder
			.getBean(ContactWayDao.class);

	public static UserModel covertToModel(User user) {
		int id = user.getId();
		UserModel model = new UserModel();
		ContactWay con = contactWayDao.findByContactId(id);
		if (con != null) {
			model.setCompany(con.getCompany());
			model.setMobilePhone(con.getMobilePhone());
			model.setTel(con.getTel());
			model.setQq(con.getQq());
			model.setEmail(con.getEmail());
			model.setFax(con.getFax());
			model.setZipCode(con.getZipCode());
		//	model.setHasterminalingSpaces(user.getHasterminalingSpaces());
		}
		ContactAddr add = contactAddrDao.findByContactId(id);
		if (add != null) {
			model.setAddrStr(add.getProvince() + add.getCity() + add.getDist()
					+ add.getAddr());
			model.setProvince(add.getProvince());
			model.setCity(add.getCity());
			model.setDist(add.getDist());
			model.setAddr(add.getAddr());
		}
		Account account = accountDao.findByUserId(id);
		if (account != null) {
			// 账户信息
			model.setBalance(account.getBalance());
			model.setProcTime(account.getProcTime());
			model.setExpTime(account.getExpirationTime());
			model.setBeginTime(account.getBeginTime());
			model.setCreateTime(account.getCreateTime());
		}
		try {
			model.setUgroupName(user.getUGroup().getName());
			model.setUgroupPK(user.getUGroup().getId());
			model.setUgroupType(user.getUGroup().getType());
			BeanUtils.copyProperties(user, model);
			List<CarModel> carModels = BeanUtils.copyListProperties(
					CarUtils.findByUserId(id), CarModel.class);
		//	model.setCarModels(carModels);

		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("操作员模块-查看详细信息-实体类转成model类失败");
			return null;
		}
//		if (BlacklistUtils.isBlack(id)) {
//			model.setIsBlack(true);
//		} else {
//			model.setIsBlack(false);
//		}
		return model;
	}

	public static List<BaseModel> convertToSimpleModels(List<User> users) {
		List<UserModel> userModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(users)) {
			return Lists.newArrayList(userModels);
		}
		try {
			userModels = BeanUtils.copyListProperties(users, UserModel.class);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("客户管理模块-实体类与Model类数组转化失败", e);
			return null;
		}
		return Lists.newArrayList(userModels);
	}

	public static ContactWay convertToContactWay(ContactWay contactWay,
			UserModel model, int userId) {
		contactWay.setContactId(userId);
		contactWay.setType(ConAddType.TYPE.getValue());
		contactWay.setCompany(model.getCompany());
		contactWay.setMobilePhone(model.getMobilePhone());
		contactWay.setTel(model.getTel());
		contactWay.setQq(model.getQq());
		contactWay.setZipCode(model.getZipCode());
		contactWay.setFax(model.getFax());
		contactWay.setEmail(model.getEmail());
		return contactWay;
	}

	public static ContactAddr convertContactAddr(ContactAddr contactAddr,
			UserModel model, int userId) {
		contactAddr.setContactId(userId);
		contactAddr.setType(ConAddType.TYPE.getValue());
		contactAddr.setProvince(model.getProvince());
		contactAddr.setCity(model.getCity());
		contactAddr.setDist(model.getDist());
		contactAddr.setAddr(model.getAddr());
		return contactAddr;
	}

	public static List<UserModel> isBlackList(List<UserModel> models) {
		List<UserModel> userModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(models)) {
			return Lists.newArrayList(userModels);
		}
		for (UserModel model : models) {
//			if (BlacklistUtils.isBlack(model.getId())) {
//				model.setIsBlack(true);
//			} else {
//				model.setIsBlack(false);
//			}
//			userModels.add(model);
		}
		return userModels;
	}
}
