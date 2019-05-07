package com.langmy.terminal.modules.device.service;

import java.io.IOException;
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
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.langmy.hardware.module.led.utils.LedAddress;
import com.langmy.hardware.module.led.utils.LedInfo;
import com.langmy.hardware.module.led.utils.LedUtils;
import com.langmy.terminal.common.entity.LedScreen;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.device.dao.BayonetDao;
import com.langmy.terminal.modules.device.dao.LedScreenDao;
import com.langmy.terminal.modules.device.model.LedScreenModel;
import com.langmy.terminal.modules.device.service.extend.LedScreenExtend;
import com.langmy.terminal.modules.log.utils.LogUtil;

@Service
public class LedScreenService extends BaseService<LedScreen> {

	@Autowired
	private LedScreenDao ledScreenDao;
	@Autowired
	private BayonetDao bayonetDao;
	
	private final static int MESSAGE_SCREEN = 0;	// 信息显示屏
	private final static int AREA_SCREEN = 1;		// 区位屏
	private final static int LEADING_SCREEN = 2;	// 车位引导屏
	private final static int COST_SCREEN = 3;		// 收费屏
	
	int  timeOut =  3000 ;

	public LedScreenService() {
		super.baseDao = SpringContextHolder.getBean("ledScreenDao");
	}
	
	@Override
	protected List<BaseModel> getModelsByBeans(List<LedScreen> entities) {
		return Lists.newArrayList(LedScreenExtend.coverToModel(entities));
	}

	@Override
	protected Specification<LedScreen> buildSpecification(BaseModel model) {
		LedScreenModel ledscreenModel = (LedScreenModel)model;
		String ledId = ledscreenModel.getLedId();
		int type = ledscreenModel.getType();
		String name = ledscreenModel.getName();
		Integer dataMasterId = ledscreenModel.getDataMasterId();
		int configureFlag = ledscreenModel.getConfigureFlag();
		
		return new Specification<LedScreen>() {
			@Override
			public Predicate toPredicate(Root<LedScreen> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("delFlag"), 0));
				
				if (StringUtils.isNotNullAndEmpty(ledId)) {
					list.add(cb.like(root.get("ledId"), "%" + ledId + "%"));
				}
				if (type !=-1) {
					list.add(cb.equal(root.get("type"), type));
				}
				if (StringUtils.isNotNullAndEmpty(name)) {
					list.add(cb.like(root.get("name"), "%" + name + "%"));
				}
				if (dataMasterId != null) {
					list.add(cb.equal(root.get("dataMaster").get("id"),dataMasterId));
				}
				if (configureFlag != -1) {
					if (configureFlag == 0) {
						list.add(cb.equal(root.get("configureFlag"), false));
					} else if (configureFlag == 1) {
						list.add(cb.equal(root.get("configureFlag"), true));
					}
				}
				
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));

			}
		};

	}
	
	/**
	 * 保存新的显示屏，并记录日志
	 * 
	 * @param model
	 *            前台传入的包含收费规则属性的model
	 * @return
	 */
	public boolean add(LedScreenModel model) {
		LedScreen ledScreen = addByModel(model);
		if (ledScreen == null)
			return false;
		LogUtil.save("硬件模块", LogUtil.Option.ADD,
				"新增显示屏：" + ledScreen.toString());
		return true;
	}

	/**
	 * 保存新的显示屏
	 * 
	 * @param model
	 * @return
	 */
	private LedScreen addByModel(LedScreenModel model) {
		LedScreen ledScreen = LedScreenExtend.covertToEntity(model);
		ledScreen = ledScreenDao.save(ledScreen);
		return ledScreen;
	}
	
	public boolean edit(LedScreenModel model) {
		LedScreen ledScreen = addByModel(model);
		if (ledScreen == null) {
			return false;
		}
		LogUtil.save("硬件模块", LogUtil.Option.ADD,
				"修改显示屏：" + ledScreen.toString());
		return true;
	}
	
	public LedScreenModel getLedScreenById(int id) {
		LedScreen ledScreen = ledScreenDao.findOne(id);
		return LedScreenExtend.getModelByLedScreen(ledScreen);
	}
	
	public LedScreenModel getLedScreenByLedId(String ledId) {
		LedScreen ledScreen = ledScreenDao.findByLedId(ledId);
		return LedScreenExtend.getModelByLedScreen(ledScreen);
	}
	
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            显示屏Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = ledScreenDao.softdelete(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("显示屏管理", LogUtil.Option.DEL, "删除显示屏：" + id);
			}
		}

		return flag;
	}
	
	/**
	 * 显示当前显示屏的信息(单行)
	 * @param bayonetId
	 * @param message
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public boolean setOneLineMessage(LedScreen ledScreen ,String message) throws UnknownHostException,
			IOException {
		boolean  Flag= false;
		if(ledScreen.getDataMaster() == null){
			return Flag;
		}else {
			int g_iCardNum = ledScreen.getCardNum();
			int width = ledScreen.getWidth();
			int height = ledScreen.getHeight();
			String ip = ledScreen.getDataMaster().getIp();
			Integer port = ledScreen.getDataMaster().getPort();
			LedInfo ledInfo  = new LedInfo(0,0,width,height);
			LedAddress address  = new LedAddress(ip, port, g_iCardNum);
			boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
			if(status == true){
				if (LedUtils.sendOneLineMsg(address,message , ledInfo)) {
					logger.info("显示屏设置成功成功");
					Flag = true;
				} else {
					Flag = false;
				}
			}else Flag = false;
			return Flag;
		}
	}
	
	/**
	 * 获得车道Id对应的显示屏
	 * @param drivewayId 车道Id
	 * @return 对应的显示屏列表
	 */
	public List<LedScreen> getByDrivewayId(int drivewayId){
		List<Integer> types = Lists.newArrayList();
		types.add(MESSAGE_SCREEN);
		types.add(COST_SCREEN);
		return ledScreenDao.findByDelFlagFalseAndTypeInAndConfigureId(types, drivewayId);
	}

	
	
	/**
	 * 显示当前显示屏所对应的区域的车位数
	 * @param deviceId
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public boolean setMessageAboutNum(int screenId) throws UnknownHostException,
			IOException {
		boolean Flag = false;
	/*	Device device = deviceDao.findByDeviceId(deviceId);
		Integer dir = device.getDirection();
		Integer col = device.getColor();
		String ip = "localhost";
		int port = device.getPort();
		int g_iCardNum = device.getOpen();
		Color color;	//颜色的枚举类型
		Direction direction;	//方向的枚举类型
		int id1 = device.getId();
		//得到device对应的显示屏区域
		List<ScreenArea> screenArea = ScreenAreaUtils.getScreenArea(id1);
		int j = 0;
		for (ScreenArea screa : screenArea) {
			if (screa.getType() == SCREEN_AREA_TYPE_AREA) {
				int id = screa.getPSpAreaId();
				List<PSp> pSps = PSpUtils.getPspByAreaId(id);
				for (PSp pSp : pSps) {
					if (pSp.getIsEnable() == true&&pSp.getCar()==null) {
						j++;
					}
				}
			}
		}
		String pspNum = j + "";
		//转化成枚举类型
		direction = getDirection(dir);
		color = getColor(col);
		LedAddress address = new LedAddress(ip, port, g_iCardNum);
		//查看设备是否在线
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		if(status == true){
			if (LedUtils.changePSPNum(address, direction, pspNum, color)) {
				logger.info("显示屏设置成功成功");
				Flag = true;
			} else {
				Flag = false;
			}
		}else Flag = false;*/
		return Flag;
	}
	
	
	/**
	 * 显示当前显示屏的信息(单行)
	 * @param deviceId
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public boolean setMessage(String deviceId) throws UnknownHostException,
			IOException {
		boolean Flag = false;
/*		Device device = deviceDao.findByDeviceId(deviceId);
		String message = device.getMessage();
		String ip = device.getIp();
		int port = device.getPort();
		int g_iCardNum = ledScreen.getCardNum();
		int width = ledScreen.getWidth();
		int height = ledScreen.getHeight();
		LedInfo ledInfo  = new LedInfo(0,0,width,height);
		LedAddress address = new LedAddress(ip, port, g_iCardNum);
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		if(status == true){
			if (LedUtils.sendOneLineMsg(address,message , ledInfo)) {
				logger.info("显示屏设置成功成功");
				Flag = true;
			} else {
				Flag = false;
			}
		}else Flag = false;*/
		return Flag;
	}
	
	/**
	 * 显示当前显示屏的信息(单行)
	 * @param bayonetId
	 * @param message
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	/*public boolean setOneLineMessage(String bayonetId ,String message) throws UnknownHostException,
			IOException {
		boolean Flag = false;
		Device device = deviceDao.getDeviceByBayonetId(bayonetId,2);
		String ip = device.getIp();
		int port = device.getPort();
		int g_iCardNum = ledScreen.getCardNum();
		int width = ledScreen.getWidth();
		int height = ledScreen.getHeight();
		LedInfo ledInfo  = new LedInfo(0,0,width,height);
		LedAddress address = new LedAddress(ip, port, g_iCardNum);
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		if(status == true){
			if (LedUtils.sendOneLineMsg(address,message , ledInfo)) {
				logger.info("显示屏设置成功成功");
				Flag = true;
			} else {
				Flag = false;
			}
		}else Flag = false;
		return Flag;
	}*/
	
	/**
	 * 显示当前显示屏的信息(多行)
	 * @param bayonetId
	 * @param msgs
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	/*public boolean setMutilneMessage(String bayonetId,String msgs[]) throws UnknownHostException,
			IOException {
		boolean Flag = false;
		Device device = deviceDao.getDeviceByBayonetId(bayonetId,2);
		String ip = device.getIp();
		int port = device.getPort();
		int g_iCardNum = ledScreen.getCardNum();
		int width = ledScreen.getWidth();
		int height = ledScreen.getHeight();
		LedInfo ledInfo  = new LedInfo(0,0,width,height);
		LedAddress address = new LedAddress(ip, port, g_iCardNum);
		if (LedUtils.sendMutiLineMsg(address,msgs , ledInfo,16)) {
			Flag = true;
		} else {
			Flag = false;
		}
		return Flag;
	}*/
	
	/**
	 * 显示当前显示屏的信息(单行)
	 * @param bayonetId
	 * @param message
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	/*public boolean setOneLineMessage(Integer bayonetId ,String message) throws UnknownHostException,
			IOException {
		boolean Flag = false;
		List<Device> deviceList = deviceDao.findByBayonetIdAndType(bayonetId,2);
		Device device = deviceList.get(0);
		String ip = device.getIp();
		int port = device.getPort();
		int g_iCardNum = ledScreen.getCardNum();
		int width = ledScreen.getWidth();
		int height = ledScreen.getHeight();
		LedInfo ledInfo  = new LedInfo(0,0,width,height);
		LedAddress address = new LedAddress(ip, port, g_iCardNum);
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		if(status == true){
			if (LedUtils.sendOneLineMsg(address,message ,ledInfo)) {
				logger.info("显示屏设置成功成功");
				Flag = true;
			} else {
				Flag = false;
			}
		}else Flag = false;
		return Flag;
	}*/
	
	/**
	 * 显示当前显示屏的信息(多行)
	 * @param bayonetId
	 * @param msgs
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
/*	public boolean setMutilneMessage(Integer bayonetId,String msgs[]) throws UnknownHostException,
			IOException {
		boolean Flag = false;
		List<Device> deviceList = deviceDao.findByBayonetIdAndType(bayonetId,SCREEN_TYPE);
		Device device = deviceList.get(0);
		String ip = device.getIp();
		int port = device.getPort();
		int g_iCardNum = ledScreen.getCardNum();
		int width = ledScreen.getWidth();
		int height = ledScreen.getHeight();
		LedInfo ledInfo  = new LedInfo(0,0,width,height);
		LedAddress address = new LedAddress(ip, port, g_iCardNum);
		if (LedUtils.sendMutiLineMsg(address,msgs , ledInfo,16)) {
			Flag = true;
		} else {
			Flag = false;
		}
		return Flag;
	}*/

	
	public List<BaseModel> findMessageScreen() {
		return LedScreenExtend.coverToModel(ledScreenDao.findByDelFlagFalseAndType(MESSAGE_SCREEN));
	}
	
	public List<BaseModel> findCostScreen() {
		return LedScreenExtend.coverToModel(ledScreenDao.findByDelFlagFalseAndType(COST_SCREEN));
	}
}
