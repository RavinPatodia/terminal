package com.langmy.terminal.modules.sys.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.langmy.terminal.common.entity.Auth;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.modules.sys.dao.AuthDao;
import com.langmy.terminal.modules.sys.model.AuthModel;

/**
 * 资源业务层
 * 
 * @author lxj
 *
 */
@Service
public class AuthService{
	
	private static Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private AuthDao authDao;
	
	private String str ="";
	
	/**
	 * 获取资源树json格式数据
	 * @return 
	 */
	public String getAuthTreeJson(){
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
    	List<Auth> as = authDao.findByParentId(id);
    	if(!as.isEmpty()){
    		for(Auth a:as){
    			if(!a.isDelFlag()){
        			String c = a.getParentId()==1?"#":a.getParentId().toString();		
        			str += "{\"id\":\"" + a.getId()  
        	                + "\",\"parent\":\""+c+"\",\"icon\":\""+a.getIcon()+"\",\"text\":\"" + a.getName() + "\"},";
        			this.getTreeJson(a.getId());
        		}
    		}
    	}
    }
    
    /**
     * 获取资源树json格式数据，对于不显示的节点将其禁用
     * 
     * @return
     */
    public String getAuthTreeJsonNodeShow(){
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
     	List<Auth> as = authDao.findByParentId(id);
     	if(!as.isEmpty()){
     		for(Auth a:as){
     			boolean disabledflag = a.getIsShow()?false:true;
     			String c = a.getParentId()==1?"#":a.getParentId().toString();		
     			str += "{\"id\":\"" + a.getId()  
     	                + "\",\"parent\":\""+c+"\",\"state\":{\"disabled\":"+disabledflag+"}"+",\"icon\":\""+a.getIcon()+"\",\"text\":\"" + a.getName() + "\"},";
     			this.getTreeJsonNodeShow(a.getId());
     		}
     	}
     }
    
    /**
     * 添加资源
     * 
     * @param authModel
     * @return
     */
    public boolean add(AuthModel authModel){
    	if(authModel.getParentId()==null){
    		authModel.setParentId(1);
    	}
    	Auth auth = new Auth();
		try {
			BeanUtils.copyProperties(authModel, auth);
			auth.setIsShow(authModel.isShowFlag());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("添加资源-model类赋值给实体类失败");
			return false;
		}
		Auth parentAuth = authDao.findOne(authModel.getParentId());
		String parentIds = parentAuth.getParentIds()+authModel.getParentId()+",";
		auth.setParentIds(parentIds);
		
		auth = authDao.save(auth);
		if(auth == null)
			return false;
		return true;
    }
    
    /**
     * 修改资源
     * 
     * @param authModel
     * @return
     */
    public boolean edit(AuthModel authModel){
    	Auth auth = new Auth();
		try {
			BeanUtils.copyProperties(authModel, auth);
			auth.setIsShow(authModel.isShowFlag());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("修改资源-model类赋值给实体类失败");
			return false;
		}
		auth = authDao.save(auth);
		if(auth == null)
			return false;
		return true;
    }
    
    /**
     * 拖拽保存资源
     * 
     * @param authModel
     * @return
     */
    public boolean dndSave(int nodeId,int parentId){
    	Auth auth = authDao.findOne(nodeId);
		auth.setParentId(parentId);
		Auth auth2 = authDao.save(auth);
		if(auth2 == null){
			return false;
		}
		return true;
    }
    
 	/**
 	 * 查看详细信息
 	 * @param id
 	 * @return
 	 */
 	public AuthModel view(int id) {
 		Auth auth = authDao.findOne(id);
 		AuthModel model = new AuthModel();
 		try {
 			BeanUtils.copyProperties(auth, model);
 			model.setShowFlag(auth.getIsShow());
 		} catch (IllegalAccessException | InvocationTargetException e) {
 			logger.error("权限模块-查看详细信息-实体类转成model类失败");
 			return null;
 		}
 		return model;
 	}

 	/**
 	 * 删除资源
 	 * 
 	 * @param id
 	 * @return
 	 */
 	public boolean delete(int id){
 		Auth auth = authDao.findOne(id);
 		if(auth == null){
 			return false;
 		}
 		auth.setDelFlag(true);
 		auth.setIsShow(false);
 		authDao.save(auth);
 		return true;
 	}
 	
}
