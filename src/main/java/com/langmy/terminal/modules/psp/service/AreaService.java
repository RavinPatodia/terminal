package com.langmy.terminal.modules.psp.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.Area;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.psp.dao.AreaDao;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.model.AreaModel;
import com.langmy.terminal.modules.psp.service.extend.AreaExtend;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

/**
 * 区域的service
 * 
 * @author MC
 *
 */
@Service
public class AreaService extends BaseService<Area> {

	@Autowired
	private AreaDao areaDao;
	@Autowired
	private PSpDao pSpDao;

	public AreaService() {
		super.baseDao = SpringContextHolder.getBean("areaDao");
	}

	public Area addByModel(AreaModel model) {
		Area area = new Area();
		area.setAreaId(model.getAreaId());
		area.setName(model.getName());
		area.setPosit(model.getPosit());
		area = areaDao.save(area);
		List<String> pspIdList = model.getPspIds();
		for(String pspPk :pspIdList){
			PSpUtils.updatePspAreaByAreaAndPspPK(area, Integer.parseInt(pspPk));
		}
		return area;
	}

	/**
	 * 获取所有车位中区域ID为空的车位
	 * 
	 * @return
	 */
	public List<BaseModel> getPspByArea() {
		return PSpUtils.getAllPspIdsByArea();
	}

	public boolean add(AreaModel model) {
		boolean flag = true;
		Area area = addByModel(model);
		if (area == null) {
			flag = false;
		}
		LogUtil.save("车位模块", LogUtil.Option.ADD, "新增区域信息：" + area.toString());
		return flag;
	}

	/**
	 * 获取所有区域
	 * 
	 * @return
	 */
	public List<BaseModel> getAreaId() {
		List<Area> area = areaDao.findAll();
		return getModelsByBeans(area);
	}

	/**
	 * 根据name获取所有符合的区域
	 * 
	 * @param areaId
	 * @return
	 */
	public List<BaseModel> getAreaIdByName(String areaId) {
		if (StringUtils.isNotNullAndEmpty(areaId)) {
			List<Area> areas = areaDao.findByNameLike("%" + areaId + "%");
			return getModelsByBeans(areas);
		} else {
			return getModelsByBeans(areaDao.findAll());
		}
	}

	public boolean edit(AreaModel model) throws ServiceException {
		boolean flag = true;

		Area area = new Area();
		area.setId(model.getId());
		area.setAreaId(model.getAreaId());
		area.setName(model.getName());
		area.setPosit(model.getPosit());

		pSpDao.deletePspByAreaId(model.getId());
		List<String> pspIdList = model.getPspIds();
		for(String pspPk :pspIdList){
			PSpUtils.updatePspAreaByAreaAndPspPK(area, Integer.parseInt(pspPk));
		}
		area = areaDao.save(area);
		if (area == null) {
			throw new ServiceException("修改失败");
		}
		LogUtil.save("区域管理模块", LogUtil.Option.EDIT, "修改区域信息：" + area.toString());
		return flag;
	}

	/**
	 * 根据id获取区域
	 * 
	 * @param id
	 * @return
	 */
	public AreaModel getAreaById(int id) {
		Area area = areaDao.findOne(id);
		return AreaExtend.getModelByArea(area);
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Area> areas) {
		List<AreaModel> areaModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(areas)) {
			return Lists.newArrayList(areaModels);
		}
		Map<String, String> map = Maps.newHashMap();
		try {
			areaModels = BeanUtils.copyListProperties(areas, AreaModel.class,
					map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("实体类与Model类数组转化失败", e);
		}

		return Lists.newArrayList(areaModels);
	}

	/**
	 * 自动生成编码的方法
	 */
	public String getGenId(String entitySelfName) {
		Area area = baseDao.findMaxIdRec();
		int id = 0;
		if (area != null) {
			id = area.getId() + 1;
		}
		return entitySelfName + id;
	}

	@Override
	protected Specification<Area> buildSpecification(BaseModel model) {
		return new Specification<Area>() {
			@Override
			public Predicate toPredicate(Root<Area> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("delFlag"), false));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	public boolean deleteArea(String ids) {
		List<Integer> idss = super.getIdList(ids);
		for (Integer id : idss) {
			Area area = areaDao.findOne(id);
			PSpUtils.updatePspByArea(area);
			area.setDelFlag(true);
			area.setLedScreens(null);
			areaDao.save(area);
			LogUtil.save("区域模块", LogUtil.Option.DEL, "区域删除：" + area.toString());
		}
		return true;
	}
}
