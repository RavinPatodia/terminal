package com.langmy.terminal.modules.device.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.langmy.terminal.common.entity.Bayonet;
import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.device.dao.BayonetDao;
import com.langmy.terminal.modules.device.model.BayonetModel;
import com.langmy.terminal.modules.device.service.extend.BayonetExtend;
import com.langmy.terminal.modules.device.utils.ParkUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;

@Service
public class BayonetService extends BaseService<Bayonet> {

	@Autowired
	private BayonetDao bayonetDao;
	
	public BayonetService() {
		super.baseDao = SpringContextHolder.getBean(BayonetDao.class);
	}

	//判断输入的卡口编号是否已存在
	public boolean validateIsExist(String bayonetId) throws ServiceException {
		
		Bayonet bayonet = bayonetDao.findByName(bayonetId);
		//如果冲突返回false。
		if (bayonet!=null) {
			return false;
		}
		return true;
	}
	
	public List<BaseModel> getAll() {
		return BayonetExtend.coverToModel(bayonetDao.findAll());
	}
	
	public List<BaseModel> getAllByName(String name) {
		if(StringUtils.isNotNullAndEmpty(name)){
			return BayonetExtend.coverToModel(bayonetDao.findByNameLikeAndDelFlagFalse("%" + name + "%"));
		}
		else {
			return BayonetExtend.coverToModel(bayonetDao.findByDelFlagFalse());
		}
	}

	@Override
	protected Specification<Bayonet> buildSpecification(BaseModel model) {
		BayonetModel bayonetModel = (BayonetModel)model;
		
		Integer parkId = bayonetModel.getParkId();
		
		return new Specification<Bayonet>() {
			@Override
			public Predicate toPredicate(Root<Bayonet> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("delFlag"), 0));
				if (parkId !=0) {
					list.add(cb.equal(root.get("park").get("id"), parkId));
				}
				
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};

	}

	public BayonetModel getBayonetById(int id) {
		Bayonet bayonet = bayonetDao.findOne(id);
		return BayonetExtend.getModelByBayonet(bayonet);
	}


	public boolean edit(BayonetModel model) {
		boolean flag = true;
		Bayonet bayonet = bayonetDao.findOne(model.getId());
		if (bayonet == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		try {
			BeanUtils.copyProperties(model, bayonet);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("硬件管理-车位锁管理-实体类与Model类转化失败", e);
			e.printStackTrace();
		}
		Park park = ParkUtils.getPark(model.getParkName());
		bayonet.setPark(park);
		bayonet = bayonetDao.save(bayonet); 
		if (bayonet == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		logger.info("修改成功");
		LogUtil.save("卡口管理模块", LogUtil.Option.ADD, "修改卡口信息："+bayonet.toString());
		return flag;
	}

	public boolean add(BayonetModel model) {
		boolean flag = true;
		Bayonet bayonet = new Bayonet();
		try {
			BeanUtils.copyProperties(model, bayonet);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("硬件管理-车位锁管理-实体类与Model类转化失败", e);
		}
		Park park = ParkUtils.getPark(model.getParkName());
		bayonet.setPark(park);
		bayonet = bayonetDao.save(bayonet);
		if (bayonet == null) {
			logger.error("新增失败");
			flag = false;
			return flag;
		}
		logger.info("新增成功");
		LogUtil.save("卡口管理模块", LogUtil.Option.ADD, "新增卡口信息："+bayonet.toString());
		return flag;
	}

	public boolean delete(int id) {
		Bayonet bayonet = bayonetDao.findOne(id);
		if (bayonet == null) {
			return false;
		}
		bayonetDao.delete(id);
		return true;

	}

	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            卡口Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = bayonetDao.softdelete(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				Bayonet bayonet = bayonetDao.findOne(id);
				bayonet.setDriveway(null);
				bayonetDao.save(bayonet);
				LogUtil.save("摄像机管理", LogUtil.Option.DEL, "删除摄像机：" + id);
			}
		}

		return flag;
	}
	public String getGenId(String entitySelfName){
		Bayonet  bayonet = baseDao.findMaxIdRec();
		int id = 0;
		if(bayonet!=null){
			id = bayonet.getId()+1;
		}
		return entitySelfName+id;
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Bayonet> entities) {
		return BayonetExtend.coverToModel(entities);
	}
	
	public List<BaseModel> findByDelFlagFalseAndParkId(int id){
		return BayonetExtend.coverToModel(bayonetDao.findByDelFlagFalseAndParkId(id));
	}
}
