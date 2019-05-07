package com.langmy.terminal.modules.device.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.langmy.hardware.module.pspLock.utils.PspLockUtils;
import com.langmy.terminal.common.entity.PSpLock;
import com.langmy.terminal.common.exception.ServiceException;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.device.dao.LockDao;
import com.langmy.terminal.modules.device.model.PSpLockModel;
import com.langmy.terminal.modules.device.service.extend.LockExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;
import com.langmy.terminal.modules.psp.dao.PSpDao;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

@Service
public class LockService extends BaseService<PSpLock> {

	@Autowired
	private LockDao lockDao;
	
	@Autowired
	private PSpDao pSpDao;

	public LockService() {
		super.baseDao = SpringContextHolder.getBean("lockDao");
	}
	
	int  timeOut =  3000 ;

	/**
	 * 删除单条记录
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		int count = lockDao.softdelete(id);
		if(count ==1){
			return true;
		}
		else{
			return false;
		}

	}

	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            车位锁Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = lockDao.softdelete(idList);
		for(int i : idList){
			PSpUtils.updatePspByLockPK(i);
		}
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("摄像机管理", LogUtil.Option.DEL, "删除摄像机：" + id);
			}
		}

		return flag;
	}

	public boolean edit(PSpLockModel model) {
		boolean flag = true;
		PSpLock pSpLock = lockDao.findOne(model.getId());
		if (pSpLock == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		try {
			BeanUtils.copyProperties(model, pSpLock);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("硬件管理-车位锁管理-实体类与Model类转化失败", e);
			e.printStackTrace();
		}
		pSpLock = lockDao.save(pSpLock);
		if (pSpLock == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		logger.info("修改成功");
		LogUtil.save("车位锁模块", LogUtil.Option.ADD, "修改车位锁信息："+pSpLock.toString());
		return flag;
	}

	public PSpLock addByModel(PSpLockModel model) throws ServiceException{
		PSpLock pSpLock = new PSpLock();
		pSpLock.setLockId(model.getLockId());
		pSpLock.setIp(model.getIp());
		
		pSpLock.setBrand(model.getBrand());
		pSpLock.setModel(model.getModel());
		pSpLock.setPort(model.getPort());
		
		if(model.getLockState()==null){
			pSpLock.setLockState(0);
		}else{
			pSpLock.setLockState(model.getLockState());
		}
		
		
		PSpLock p = lockDao.save(pSpLock);
		int id = Integer.parseInt(model.getPspId());
//		PSpUtils.updatePspLock(p, id);
		return p;
	}
	
	public boolean add(PSpLockModel model) throws ServiceException{
		boolean flag = true;
		PSpLock device = addByModel(model);
		if (device == null) {
			flag = false;
		}
		LogUtil.save("车位锁模块", LogUtil.Option.ADD, "新增车位锁信息："+device.toString());
		return flag;
	}
	
	
	@Override
	protected List<BaseModel> getModelsByBeans(List<PSpLock> pSpLock) {
		return LockExtend.coverToModel(pSpLock);
	}

	@Override
	protected Specification<PSpLock> buildSpecification(BaseModel baseModel) {
		PSpLockModel lockModel = (PSpLockModel)baseModel;
		
		String lockId = lockModel.getLockId();
		Integer lockState = lockModel.getLockState();
		String brand = lockModel.getBrand();
		String model = lockModel.getModel();
		
		return new Specification<PSpLock>() {
			@Override
			public Predicate toPredicate(Root<PSpLock> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if (StringUtils.isNotNullAndEmpty(lockId)) {
					list.add(cb.like(root.get("lockId"), "%" + lockId + "%"));
				}
				if (lockState !=-1) {
					list.add(cb.equal(root.get("lockState"), lockState));
				}
				if (StringUtils.isNotNullAndEmpty(brand)) {
					list.add(cb.like(root.get("brand"), "%" + brand
							+ "%"));
				}
				if (StringUtils.isNotNullAndEmpty(model)) {
					list.add(cb.like(root.get("model"), "%" + model
							+ "%"));
				}
				list.add(cb.equal(root.get("delFlag"), 0));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
	
	
	public PSpLockModel getLockById(int id){
		PSpLock lock = lockDao.findOne(id);
		return LockExtend.getModelByLock(lock);
	}
	
	/**
	 * 根据传入的车位ID 获得车位锁
	 * @param pspId
	 * @return
	 */
	public PSpLockModel getPSpLockByPspId(String pspId){
		PSpLock lock = lockDao.getLockByPspId(pspId);
		return LockExtend.getModelByLock(lock);
	}

	/**
	 * 根据传入的车位ID 获得车位锁
	 * @param pspId
	 * @return
	 */
	public PSpLockModel getPSpLockByLockId(String lockId){
		PSpLock lock = lockDao.findByLockId(lockId);
		return LockExtend.getModelByLock(lock);
	}
	
	/**
	 * 开启多个车位锁
	 * @param ids 车位锁Id {1,2,3}
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public String openLock(String ids) throws UnknownHostException, IOException {
		String errorLocks = "";
		List<PSpLock> pSpLocks = lockDao.getLockByIds(super.getIdList(ids));
		for(PSpLock psplock :pSpLocks){	
			String ip = psplock.getIp(); 
			Integer port = psplock.getPort();
			
			boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
			if(status == true){
				boolean state = PspLockUtils.openPspLock(ip, port);
				if(state!=true){
					errorLocks = psplock.getLockId()+",";
				}else lockDao.enable(psplock.getId());
			}else errorLocks = psplock.getLockId()+",";
		}
		return errorLocks;
	}
	
	/**
	 * 关闭多个车位锁
	 * @param ids 车位锁Id {1,2,3}
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public String closeLock(String ids) throws UnknownHostException, IOException {
		String errorLocks = "";
		List<PSpLock> pSpLocks = lockDao.getLockByIds(super.getIdList(ids));
		for(PSpLock psplock :pSpLocks){
			String ip = psplock.getIp();
			Integer port = psplock.getPort();
			boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
			if(status == true){
				if(!PspLockUtils.closePspLock(ip, port)){
					errorLocks = psplock.getLockId()+",";
				}else lockDao.disable(psplock.getId());
			}else errorLocks = psplock.getLockId()+",";
		}
		return errorLocks;
	}
	
	/**
	 * 开启车位锁
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public String openOneLock(String pspId) throws UnknownHostException, IOException {
		String message = "";
		PSpLock pSpLock = PSpUtils.findPSpByPSpId(pspId).getPSpLock();
		if(pSpLock.getLockState()==1){
			message = "当前为开启状态，无需开启！";
			return message;
		}
		String ip = pSpLock.getIp();
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		Integer port = pSpLock.getPort();
		if(status == true){
			boolean state = PspLockUtils.openPspLock(ip, port);
			if(state==true){
				int count = lockDao.enable(pSpLock.getId());
				if(count == 1){
					message = "车位锁开启成功！";
					return message;
				}
			}
			message = "车位锁开启失败！";
			return message;
		}else{
			message = "设备链接失败！";
			return message;
		}

		
	}
	
	/**
	 * 关闭车位锁
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public String closeOneLock(String pspId) throws UnknownHostException, IOException {
		String message = "";
		PSpLock pSpLock = PSpUtils.findPSpByPSpId(pspId).getPSpLock();
		if(pSpLock.getLockState()==0){
			message = "当前为关闭状态，无需关闭！";
			return message;
		}
		String ip = pSpLock.getIp();
		Integer port = pSpLock.getPort();
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		if(status == true){
			boolean state = PspLockUtils.openPspLock(ip, port);
			if(state==true){
				int count = lockDao.disable(pSpLock.getId());
				if(count == 1){
					message = "车位锁关闭成功！";
					return message;
				}
			}
			message = "车位锁关闭失败！";
			return message;
		} else {
			message = "设备链接失败！";
			return message;
		}
	}
	
	/**
	 * 开启车位锁根据车位ID
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public boolean openOneLockById(Integer pspId) throws UnknownHostException, IOException {
		boolean flag = false;
		PSpLock pSpLock = pSpDao.findOne(pspId).getPSpLock();
		String ip = pSpLock.getIp();
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		Integer port = pSpLock.getPort();
		if(status == true){
			boolean state = PspLockUtils.openPspLock(ip, port);
			if(state==true){
				int count = lockDao.enable(pSpLock.getId());
				if(count == 1){
					flag = true;
				}
			}
		}else flag = false;
		
		return flag;
	}
	
	/**
	 * 关闭一个车位锁根据ＩＤ
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public boolean closeOneLockById(Integer pspId) throws UnknownHostException, IOException {
		boolean flag = false;
		PSpLock pSpLock = pSpDao.findOne(pspId).getPSpLock();
		String ip = pSpLock.getIp();
		Integer port = pSpLock.getPort();
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		if(status == true){
			boolean state = PspLockUtils.closePspLock(ip, port);
			if(state==true){
				int count = lockDao.disable(pSpLock.getId());
				if(count == 1){
					flag = true;
				}
			}
		} else flag = false;
		return flag;
	}

	/**
	 * 获取所有车位锁的电量
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@Scheduled(cron = "0 40 22 * * *") 
	public void getAllElectricalQuantity() throws UnknownHostException, IOException{
		List<PSpLock> pSpLocks = lockDao.findAll();
		for(PSpLock pSpLock :pSpLocks){
			String ip = pSpLock.getIp();
			int port = pSpLock.getPort();
			boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
			if(status == true){
				Integer electricalQuantity = PspLockUtils.detectionEnergy(ip, port);
				pSpLock.setElectricalQuantity(electricalQuantity);
				if(logger.isDebugEnabled()){
					logger.debug(pSpLock.toString());
				}
				lockDao.save(pSpLock);
			}
		}
	}
/*	//带有返回值的的获取电量的方法，用于测试
	public String getAllElectrical(){
		String messageString = "";
		List<PSpLock> pSpLocks = lockDao.findAll();
		for(PSpLock pSpLock :pSpLocks){
			String ip = pSpLock.getIp();
			int port = Integer.parseInt(pSpLock.getVar2());
			Integer electricalQuantity = PspLockUtils.detectionEnergy(ip, port);
			pSpLock.setElectricalQuantity(electricalQuantity);
			lockDao.save(pSpLock);
			messageString  =electricalQuantity.toString() +",";
		}
		return messageString;
	}
	
*/	
	/**
	 * 获取所有车位锁在线离线状态
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@Scheduled(cron = "0 0 2 * * *") 
	public void getAllLineState() throws UnknownHostException, IOException{
		List<PSpLock> pSpLocks = lockDao.findAll();
		for(PSpLock pSpLock :pSpLocks){
			String ip = pSpLock.getIp();
			int port = pSpLock.getPort();
			boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
			if(status == true){
				boolean lineState = PspLockUtils.detectionOnlineOffline(ip, port);
				pSpLock.setLineState(lineState);
				lockDao.save(pSpLock);
			}
		}
	}
	
/*	//带有返回值的获取离线在线状态的方法，用于测试
 * public boolean getLineState(){
		boolean mess = false;
		List<PSpLock> pSpLocks = lockDao.findAll();
		for(PSpLock pSpLock :pSpLocks){
			String ip = pSpLock.getIp();
			int port = Integer.parseInt(pSpLock.getVar2());
			boolean lineState = PspLockUtils.detectionOnlineOffline(ip, port);
			pSpLock.setLineState(lineState);
			lockDao.save(pSpLock);
			mess = true;
		}
		return mess;
	}*/
}
