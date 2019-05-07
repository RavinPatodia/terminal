package com.langmy.terminal.modules.device.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.Park;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.charge.utils.ChargeRuleUtils;
import com.langmy.terminal.modules.device.dao.ParkDao;
import com.langmy.terminal.modules.device.model.ParkModel;

@Service
public class ParkService extends BaseService<Park>{
	
	private static Logger logger = LoggerFactory.getLogger(ParkService.class);
	
	@Autowired
	private ParkDao parkDao;
	
	private String str ="";
	
	public String getParkTreeJson(){
		 str = "[";  
	     // 从根开始  
	     this.getTreeJson(1);
	     //去掉最后一个，符合json格式
	     str = str.substring(0, str.length()-1);
	     str += "]";  
	     return str; 
	}
	
	/**  
    * 无限递归获得jsTree的json字串  
    *   
    * @param parentId  
    *            父权限id  
    * @return  
    */  
    private void getTreeJson(Integer id)  
    {  
    	List<Park> ps = parkDao.findByParentId(id);
    	if(!ps.isEmpty()){
    		for(Park p:ps){
    			if(!p.isDelFlag()){
    				String c = p.getParentId()==1?"#":p.getParentId().toString();		
        			str += "{\"id\":\"" + p.getId()  
        	                + "\",\"parent\":\""+c+"\",\"icon\":\""+""+"\",\"text\":\"" + p.getName() + "\"},";
        			this.getTreeJson(p.getId());
    			}
    		}
    	}
    }
    
    public String getParkTreeJsonNodeShow(){
		 str = "[";  
	     // 从根开始  
	     this.getTreeJsonNodeShow(1);
	     //去掉最后一个，符合json格式
	     str = str.substring(0, str.length()-1);
	     str += "]";  
	     return str; 
	}
    
    /**  
     * 无限递归获得jsTree的json字串  ,对于设置不显示的节点将其禁用，用于资源分配
     *   
     * @param parentId  
     *            父权限id  
     * @return  
     */  
     private void getTreeJsonNodeShow(Integer id)  
     {  
     	List<Park> as = parkDao.findByParentId(id);
     	if(!as.isEmpty()){
     		for(Park a:as){
     			boolean disabledflag = a.isDelFlag()?false:true;
     			String c = a.getParentId()==1?"#":a.getParentId().toString();		
     			str += "{\"id\":\"" + a.getId()  
     	                + "\",\"parent\":\""+c+"\",\"state\":{\"disabled\":"+disabledflag+"}"+",\"icon\":\""+""+"\",\"text\":\"" + a.getName() + "\"},";
     			this.getTreeJsonNodeShow(a.getId());
     		}
     	}
     }
    
    public Park add(ParkModel parkModel){
    	Park park = new Park();
		try {
			BeanUtils.copyProperties(parkModel, park);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("model类赋值给实体类失败");
			return null;
		}
		Integer chargeRulePK = parkModel.getRulePK();
		if(chargeRulePK!=null){
			ChargeRule chargeRule = ChargeRuleUtils.findChargeRuleById(chargeRulePK);
			park.setChargeRule(chargeRule);
		}
		park = parkDao.save(park);
		if(park == null)
			return null;
		return park;
    }
    
    public boolean edit(ParkModel parkModel){
    	Park park = parkDao.findOne(parkModel.getId());
		try {
			BeanUtils.copyProperties(parkModel, park);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("修改资源-model类赋值给实体类失败");
			return false;
		}
		Integer chargeRulePK = parkModel.getRulePK();
		if(chargeRulePK!=null){
			ChargeRule chargeRule = ChargeRuleUtils.findChargeRuleById(chargeRulePK);
			park.setChargeRule(chargeRule);
		}else{
			park.setChargeRule(null);
		}
		
		park = parkDao.save(park);
		if(park == null)
			return false;
		return true;
    }
    
    // 查看详细信息
 	public ParkModel view(int id) {
 		Park park = parkDao.findOne(id);
 		ParkModel model = new ParkModel();
 		try {
 			BeanUtils.copyProperties(park, model);
 		} catch (IllegalAccessException | InvocationTargetException e) {
 			logger.error("查看详细信息-实体类转成model类失败");
 			return null;
 		}
 		if(park.getChargeRule()!=null){
 			model.setRuleName(park.getChargeRule().getRuleName());
 			model.setRulePK(park.getChargeRule().getId());
 		}
 		return model;
 	}

 	public boolean delete(int id){
 		Park park = parkDao.findOne(id);
 		if(park == null){
 			return false;
 		}
 		if(park.getPSps().size()>0){
 			return false;
 		}
 		park.setDelFlag(true);
 		parkDao.save(park);
 		return true;
 	}
 	
	/**
	 * 根据name获取所有符合的停车场
	 * 
	 * @param areaId
	 * @return
	 */
	public List<BaseModel> getParkIdByName(String name) {
		if (StringUtils.isNotNullAndEmpty(name)) {
			List<Park> parks = parkDao.findByDelFlagFalseAndNameLike("%" + name + "%");
			return getModelsByBeans(parks);
		} else {
			return getModelsByBeans(parkDao.findAll());
		}
	}
	
	/**
	 * 根据name获取停车场
	 * 
	 * @param areaId
	 * @return
	 */
	public Park getParkByName(String name) {
		if (StringUtils.isNotNullAndEmpty(name)) {
			Park park = parkDao.findByNameAndDelFlagFalse(name);
			return park;
		} else {
			return null;
		}
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Park> entities) {
		List<ParkModel> parkModels = Lists.newArrayList();
		if (ListUtils.isNullOrEmpty(entities)) {
			return Lists.newArrayList(parkModels);
		}
		Map<String, String> map = Maps.newHashMap();
		try {
			parkModels = BeanUtils.copyListProperties(entities, ParkModel.class, map);
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException
				| ClassNotFoundException | IntrospectionException | IOException e) {
			logger.error("实体类与Model类数组转化失败", e);
		}
		return Lists.newArrayList(parkModels);
	}

	@Override
	protected Specification<Park> buildSpecification(BaseModel model) {
		return null;
	}
 	
	/**
	 * 判断输入的停车场编号是否已存在
	 * 
	 * @param pspId
	 * @return
	 * @throws ServiceException
	 */
	public boolean getSameParkName(String parkName) throws ServiceException {

		Park park = parkDao.findByNameAndDelFlagFalse(parkName);
		if (park != null) {
			return false;
		}
		return true;
	}
	
	public String getMapPath(int id){
		Park park = parkDao.findOne(id);
		if(park!=null){
			return park.getMapPath();
		}
		else
			return "";
	}
	
	public int saveParkMapPath(int id,String mapPath) {
		return parkDao.saveParkMapPath(id, mapPath);
	}

	/**
	 * 根据传入的停车场获得其下级子节点
	 * @param park 停车场
	 * @return
	 */
	public List<Park> getParkChildrenByPark(Park park) {
		List<Park> allParks = Lists.newArrayList();
		getParkTree(allParks,park.getId());
		if(logger.isDebugEnabled())
			logger.debug(allParks.toString());
		return allParks;
	}
	
	/**
	 * 无限递归获得停车场的子节点
	 * @param childrens
	 * @param parkId
	 */
	public void getParkTree(List<Park> childrens,int parkId){
		List<Park> childrenNode = parkDao.findByParentId(parkId);
		if(ListUtils.notNullAndEmpty(childrenNode)){
			childrens.addAll(childrenNode);
			for(Park p:childrenNode){
				if(logger.isDebugEnabled())
					logger.debug(p.toString());
				getParkTree(childrens,p.getId());
			}
		}
	}

}
