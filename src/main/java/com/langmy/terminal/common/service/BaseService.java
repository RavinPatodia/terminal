package com.langmy.terminal.common.service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.persistence.IdEntity;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.PropertiesLoader;
import com.langmy.terminal.common.utils.Reflections;
import com.langmy.terminal.common.web.DataTable;
import com.langmy.terminal.common.web.DataTableParameter;

/**
 * Service基类
 * @author ThinkGem
 */
public abstract class BaseService<T extends IdEntity> {

	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(BaseService.class);
	private Class<T> destinationClass;
	/**
	 * 通过子类对baseDao进行实例化
	 */
	protected BaseDao<T> baseDao;
	
	@SuppressWarnings("unchecked")
	public BaseService(BaseDao<T> baseDao){
		Type t = getClass().getGenericSuperclass();  
        ParameterizedType p = (ParameterizedType) t ;  
        destinationClass = (Class<T>) p.getActualTypeArguments()[0];
        this.baseDao = baseDao;
	}
	
	@SuppressWarnings("unchecked")
	public BaseService(){
		Type t = getClass().getGenericSuperclass();  
        ParameterizedType p = (ParameterizedType) t ;  
        destinationClass = (Class<T>) p.getActualTypeArguments()[0];
	}
	
	/*@SuppressWarnings("unused")
	private Class<T> entityClass;
	
	public BaseService(){
        entityClass = Reflections.getClassGenricType(this.getClass());
	}*/
	
	/**
	 * "1,2,3"->{1,2,3}
	 * @param ids
	 * @return
	 */
	protected List<Integer> getIdList(String ids) {
		List<Integer> idList = Lists.newArrayList();
		if(StringUtils.isNotBlank(ids)) {
			ids = ids.trim().replace("　", ",").replace(" ", ",").replace("，", ",");
			String[] arrId = ids.split(",");
			for(String id:arrId) {
				if(id.matches("\\d*")) {
					idList.add(Integer.valueOf(id));
				}
			}
		}
		return idList;
	}
	
	/**
	 * 获得自动增长的编号，形式为 前缀/停车场编号+entitySelfName+(数据库中最大主键Id+1)
	 * 
	 * @param entitySelfName 实体特殊名称
	 * @param prefix 前缀
	 * @return
	 */
	public String getGenId(String entitySelfName){
		T  entity = baseDao.findMaxIdRec();
		int id = 0;
		if(entity!=null){
			id = entity.getId()+1;
		}
		PropertiesLoader propertiesLoader = new PropertiesLoader("park.properties");
		String parkId = propertiesLoader.getProperty("parkId");
		return parkId+entitySelfName+id;
	}
	
	/**
	 * 构建Spring data jpa 分页以及排序的 对象
	 * @param dataTableParam 接收前台参数的对象
	 * @param entityClass 进行排序和验证的实体
	 * @return
	 */
	protected PageRequest buildPageRequest(DataTableParameter dataTableParam,Class<T> entityClass){
		int iDisplayStart = dataTableParam.getiDisplayStart();
		int iDisplayLength = dataTableParam.getiDisplayLength();
		List<String> iSortColsName = dataTableParam.getiSortColsName();
		List<String> sSortDirs = dataTableParam.getsSortDirs();
		
		Sort sort = null;
		if(ListUtils.notNullAndEmpty(iSortColsName)){
			validatorSortColumns(iSortColsName,entityClass);
			List<Order> orders = Lists.newArrayList();
			for(int i=0;i<iSortColsName.size();i++){
				String sortCol = iSortColsName.get(i);
				String dir = sSortDirs.get(i);
				orders.add(new Order(getDirectionByDirString(dir),sortCol));
			}
			sort = new Sort(orders);
		}
		return new PageRequest(iDisplayStart / iDisplayLength, iDisplayLength, sort);
	}
	
	/**
	 * @param direction 排序的方向 "asc"/"desc"
	 * @return
	 */
	protected Direction getDirectionByDirString(String direction){
		if(direction.equals("asc")){
			return Direction.ASC;
		}
		else{
			return Direction.DESC;
		}
	}
	
	/**
	 * 前台显示dataTable的list方法
	 * @param model 相应实体对应的model
	 * @param dataTableParam 接收前台参数的对象
	 * @return
	 */
	public DataTable<BaseModel> list(BaseModel model, DataTableParameter dataTableParam) {
		Page<T> page = getPageByModelAndDataTableParam(model, dataTableParam);
		List<T> entities = page.getContent();
		List<BaseModel> models = null;
		// PO到VO的转化
		models = getModelsByBeans(entities);
		int totalCount = (int) baseDao.count();

		DataTable<BaseModel> dt = new DataTable<BaseModel>();
		dt.setAaData(models);
		dt.setiTotalDisplayRecords((int)page.getTotalElements());
		dt.setiTotalRecords(totalCount);
		dt.setsEcho(dataTableParam.getsEcho() + 1);
		if(logger.isDebugEnabled()){
			logger.debug(JSON.toJSONString(dt));
		}
		return dt;
	}

	private Page<T> getPageByModelAndDataTableParam(BaseModel model,
			DataTableParameter dataTableParam) {
		Pageable pageRequest = null;
		pageRequest = buildPageRequest(dataTableParam, destinationClass);
		Specification<T> spec = buildSpecification(model);
		Page<T> page = baseDao.findAll(spec, pageRequest);
		return page;
	}
	
	public List<T> listEntities(BaseModel model,DataTableParameter dataTableParam){
		Page<T> page = getPageByModelAndDataTableParam(model, dataTableParam);
		List<T> entities = page.getContent();
		return entities;
	}
	
	
	/**
	 * 根据DataTable<BaseModel> list方法 中查询得到的实体类的List，转化成给前台显示的Model类的List
	 * @param entities 
	 * @return
	 */
	protected abstract List<BaseModel> getModelsByBeans(List<T> entities);

	/**
	 * 构建复杂查询以及条件查询的对象
	 * @param model
	 * @return
	 */
	protected abstract Specification<T> buildSpecification(BaseModel model);

	/**
	 * 验证排序的属性是否在实体类中中存在
	 * @param iSortColsName 对个排序的List
	 * @param class1 实体类的Class
	 */
	protected void validatorSortColumns(List<String> iSortColsName, Class<T> class1){
		for(String fieldName:iSortColsName){
			Field field = Reflections.getPropertyFieldByName(class1, fieldName);
			if(field==null){
				logger.error("该实体以及其父类中找不到相应的属性"+fieldName);
			}
		}
	}
}
