package com.langmy.terminal.modules.report.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.ChargeRec;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ColorUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.modules.report.DaoImpl.CharRecDaoImpl;
import com.langmy.terminal.modules.report.dao.CharRecDao;
import com.langmy.terminal.modules.report.model.ChargeRecDataModel;
import com.langmy.terminal.modules.report.model.ChargeRecModel;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 操作员结账记录Service
 * 
 * @author ZZD
 *
 */
@Service
public class CharRecService extends BaseService<ChargeRec> {
	public static final Integer OPER_CHARGE = 1;

	@Autowired
	private CharRecDao charRecDao;

	@Autowired
	private CharRecDaoImpl charRecDaoImpl;

	public CharRecService() {
		super.baseDao = SpringContextHolder.getBean(CharRecDao.class);
	}

	/**
	 * 一段时间内操作员结账次数
	 * 
	 * @param model
	 * @return String
	 */
	public String search(ChargeRecModel model) {
		JSONObject json = null;
		List<Object[]> dataList = charRecDaoImpl.search(model.getStartTime(),
				model.getEndTime());
		JSONArray array = new JSONArray();
		for (int i = 0; i < dataList.size(); i++) {
			Object[] obj = dataList.get(i);
			json = new JSONObject();
			json.put("zColor", ColorUtils.getColor()[i]);
			json.put("bSum", obj[2].toString());
			json.put("aUser", obj[0].toString());
			array.add(json);
		}
		return array.toJSONString();
	}

	/**
	 * 一段时间内操作员结账次数
	 * 
	 * @param model
	 * @return
	 */
	public List<ChargeRecModel> searchForExport(ChargeRecModel model) {
		List<Object[]> dataList = charRecDaoImpl.search(model.getStartTime(),
				model.getEndTime());
		List<ChargeRecModel> modelList = Lists.newArrayList();
		for (Object[] obj : dataList) {
			ChargeRecModel m = new ChargeRecModel();
			m.setOperName(obj[0].toString());
			m.setName(obj[1].toString());
			m.setOperChargeSum(Double.parseDouble(obj[2].toString()));
			modelList.add(m);
		}
		return modelList;
	}

	/**
	 * 一段时间内操作员结账次数
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	public List<ChargeRecDataModel> searchForExportData(ChargeRecDataModel model)
			throws ParseException {
		List<Object[]> dataList = charRecDaoImpl.searchForExportData(
				model.getStartTime(), model.getEndTime(), model.getOperId());
		List<ChargeRecDataModel> modelList = Lists.newArrayList();
		for (Object[] obj : dataList) {
			ChargeRecDataModel m = new ChargeRecDataModel();
			Operater o = OperaterUtils.getOperaterById(Integer.parseInt(obj[0]
					.toString()));
			if (o != null) {
				m.setOperName(o.getOperName());
				m.setName(o.getName());
			}
			m.setActualPay(Double.parseDouble(obj[1].toString()));
			m.setAmoutPay(Double.parseDouble(obj[2].toString()));
			m.setPayTimeStr(obj[3].toString());
			String payType = obj[4].toString();
			if ("0".equals(payType)) {
				m.setPayType("账户扣款");
			} else if ("1".equals(payType)) {
				m.setPayType("手工收费");
			} else {
				m.setPayType("缴费机收费");
			}
			modelList.add(m);
		}
		return modelList;
	}

	/**
	 * 一段时间内操作员结账次数
	 * 
	 * @param model
	 * @return String
	 */
	public String getActualPayOfMouth(String year) {
		JSONObject json = null;
		List<Object[]> dataList = charRecDaoImpl.getActualPayOfMouth(year);
		JSONArray array = new JSONArray();
		if (dataList.size() <= 0) {
			for (int i = 0; i < 12; i++) {
				json = new JSONObject();
				json.put("lineColor", ColorUtils.getColor()[i]);
				json.put("bPay", "0.0");
				json.put("aMonth", year + "-" + i + 1);
				array.add(json);
			}
		}
		for (int i = 0; i < dataList.size(); i++) {
			Object[] obj = dataList.get(i);
			json = new JSONObject();
			json.put("lineColor", ColorUtils.getColor()[i]);
			json.put("bPay", obj[1].toString());
			json.put("aMonth", obj[0].toString());
			array.add(json);
		}
		return array.toJSONString();
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<ChargeRec> chargeRecs) {
		List<ChargeRecModel> chargeRecModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(chargeRecs)) {
			return Lists.newArrayList(chargeRecModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "operater.operName");
		map.put("targetPro1", "operName");
		map.put("sourcePro2", "operater.name");
		map.put("targetPro2", "name");
		try {
			chargeRecModels = BeanUtils.copyListProperties(chargeRecs,
					ChargeRecModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("报表模块-操作员结账-实体类与Model类数组转化失败");

		}
		return Lists.newArrayList(chargeRecModels);
	}

	@Override
	protected Specification<ChargeRec> buildSpecification(BaseModel baseModel) {
		Integer operId = ((ChargeRecModel) baseModel).getOperId();
		Date startTime = ((ChargeRecModel) baseModel).getStartTime();
		Date endTime = ((ChargeRecModel) baseModel).getEndTime();

		return new Specification<ChargeRec>() {

			@Override
			public Predicate toPredicate(Root<ChargeRec> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (operId != null) {
					list.add(cb.equal(root.get("operater").get("id"),
							operId));
				}
				if (startTime != null) {
					list.add(cb.greaterThanOrEqualTo(
							root.get("payTime").as(Date.class),
							DateUtils.getDateStart(startTime)));

				}
				if (endTime != null) {
					list.add(cb.lessThanOrEqualTo(
							root.get("payTime").as(Date.class),
							DateUtils.getDateEnd(endTime)));
				}
				list.add(cb.equal(root.get("payType"), OPER_CHARGE));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		};
	}
}
