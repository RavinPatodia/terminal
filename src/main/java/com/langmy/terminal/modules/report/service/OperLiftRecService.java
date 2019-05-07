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
import com.langmy.terminal.common.entity.Bayonet;
import com.langmy.terminal.common.entity.OperLiftRec;
import com.langmy.terminal.common.entity.Operater;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ColorUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.device.model.DeviceModel;
import com.langmy.terminal.modules.device.utils.BayonetUtils;
import com.langmy.terminal.modules.device.utils.DeviceUtils;
import com.langmy.terminal.modules.report.DaoImpl.OperLiftRecDaoImpl;
import com.langmy.terminal.modules.report.dao.OperLiftRecDao;
import com.langmy.terminal.modules.report.model.OperLiftRecDataModel;
import com.langmy.terminal.modules.report.model.OperLiftRecModel;
import com.langmy.terminal.modules.sys.utils.OperaterUtils;

/**
 * 手动抬杆记录Service
 * 
 * @author ZZD
 *
 */
@Service
public class OperLiftRecService extends BaseService<OperLiftRec> {
	@Autowired
	private OperLiftRecDao operLiftRecDao;
	@Autowired
	private OperLiftRecDaoImpl operLiftRecDaoImpl;

	public OperLiftRecService() {
		super.baseDao = SpringContextHolder.getBean("operLiftRecDao");
	}

	/**
	 * 一段时间内用户的抬杆次数
	 * 
	 * @param model
	 * @return String
	 */
	public String search(OperLiftRecModel model) {
		JSONObject json = null;
		List<Object[]> dataList = operLiftRecDaoImpl.search(
				model.getStartTime(), model.getEndTime());
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
	 * 一段时间内用户的抬杆次数
	 * 
	 * @param model
	 * @return
	 */
	public List<OperLiftRecModel> searchForExport(OperLiftRecModel model) {
		List<Object[]> dataList = operLiftRecDaoImpl.search(
				model.getStartTime(), model.getEndTime());
		List<OperLiftRecModel> modelList = Lists.newArrayList();
		for (Object[] obj : dataList) {
			OperLiftRecModel m = new OperLiftRecModel();
			m.setOperName(obj[0].toString());
			m.setName(obj[1].toString());
			m.setOperLiftSum(Integer.parseInt(obj[2].toString()));
			modelList.add(m);
		}
		return modelList;
	}

	/**
	 * 一段时间内用户抬杆记录
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	public List<OperLiftRecDataModel> searchForExportData(OperLiftRecDataModel model)
			throws ParseException {
		List<Object[]> dataList = operLiftRecDaoImpl.searchForExportData(
				model.getStartTime(), model.getEndTime(), model.getOperId());
		List<OperLiftRecDataModel> modelList = Lists.newArrayList();
		for (Object[] obj : dataList) {
			OperLiftRecDataModel m = new OperLiftRecDataModel();
			Bayonet b = BayonetUtils.getBayonetById(Integer.parseInt(obj[0]
					.toString()));
			if (b != null) {
				m.setBayonetName(b.getName());
			}
			DeviceModel d = DeviceUtils.getDeviceModelById(Integer
					.parseInt(obj[1].toString()));
			if (d != null) {
				m.setDeviceName(d.getName());
			}
			Operater o = OperaterUtils.getOperaterById(Integer.parseInt(obj[4]
					.toString()));
			if (o != null) {
				m.setOperName(o.getOperName());
				m.setName(o.getName());
			}
			m.setOperTimeStr(obj[2].toString());
			m.setOpenReason(obj[3].toString());
			modelList.add(m);
		}
		return modelList;
	}

	/**
	 * 删除单条记录
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		OperLiftRec log = operLiftRecDao.findOne(id);
		if (log == null) {
			return false;
		}
		operLiftRecDao.delete(id);
		return true;

	}

	/**
	 * 删除多条记录
	 * 
	 * @param ids
	 * @return
	 */
	public boolean delete(String ids) {
		boolean flag = true;
		List<Integer> idList = getIdList(ids);
		for (int id : idList) {
			if (!delete(id)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<OperLiftRec> operLiftRecs) {

		List<OperLiftRecModel> operLiftRecModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(operLiftRecs)) {
			return Lists.newArrayList(operLiftRecModels);
		}
		Map<String, String> map = Maps.newHashMap();
		map.put("sourcePro1", "bayonet.name");
		map.put("targetPro1", "bayonetName");
		map.put("sourcePro2", "device.name");
		map.put("targetPro2", "deviceName");
		map.put("sourcePro3", "operater.operName");
		map.put("targetPro3", "operName");
		map.put("sourcePro4", "operater.name");
		map.put("targetPro4", "name");
		try {
			operLiftRecModels = BeanUtils.copyListProperties(operLiftRecs,
					OperLiftRecModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("报表模块-抬杆记录-实体类与Model类数组转化失败");
		}
		return Lists.newArrayList(operLiftRecModels);

	}

	@Override
	protected Specification<OperLiftRec> buildSpecification(BaseModel baseModel) {
		Date startTime = ((OperLiftRecModel) baseModel).getStartTime();
		Date endTime = ((OperLiftRecModel) baseModel).getEndTime();
		Integer operId = ((OperLiftRecModel) baseModel).getOperId();
		String bayonetName = ((OperLiftRecModel) baseModel).getBayonetName();
		return new Specification<OperLiftRec>() {

			@Override
			public Predicate toPredicate(Root<OperLiftRec> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (operId != null) {
					list.add(cb.equal(root.get("operater").get("id"), operId));
				}
				if (StringUtils.isNotNullAndEmpty(bayonetName)) {
					list.add(cb.like(root.get("bayonet").get("name"), "%"
							+ bayonetName + "%"));
				}
				if (startTime != null) {
					list.add(cb.greaterThanOrEqualTo(
							root.get("openTime").as(Date.class),
							DateUtils.getDateStart(startTime)));
				}
				if (endTime != null) {
					list.add(cb.lessThanOrEqualTo(
							root.get("openTime").as(Date.class),
							DateUtils.getDateEnd(endTime)));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

	}

}
