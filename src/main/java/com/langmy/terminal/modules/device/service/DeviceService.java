package com.langmy.terminal.modules.device.service;

import java.awt.AWTException;
import java.awt.Robot;
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

import com.google.common.collect.Lists;
import com.langmy.hardware.module.led.utils.LedAddress;
import com.langmy.hardware.module.led.utils.LedUtils;
import com.langmy.hardware.module.led.utils.LedUtils.Color;
import com.langmy.hardware.module.led.utils.LedUtils.Direction;
import com.langmy.hardware.module.relay.util.RelayUtils;
import com.langmy.terminal.common.entity.Device;
import com.langmy.terminal.common.model.BaseModel;
import com.langmy.terminal.common.service.BaseService;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.SpringContextHolder;
import com.langmy.terminal.common.utils.StringUtils;
import com.langmy.terminal.modules.device.dao.DeviceDao;
import com.langmy.terminal.modules.device.model.DeviceModel;
import com.langmy.terminal.modules.device.service.extend.DeviceExtend;
import com.langmy.terminal.modules.device.utils.DrivewayUtils;
import com.langmy.terminal.modules.log.utils.LogUtil;

@Service
public class DeviceService extends BaseService<Device> {

	@Autowired
	private DeviceDao deviceDao;

	private final static Integer BRAKEMACHINE = 1;			// 设备类型:闸机
	private final static Integer TERMINAL_TYPE = 3;			// 设备类型:终端机
	private final static Integer DATAMASTER = 5;			// 设备类型:数据转换主机
	
	int  timeOut =  3000 ;
	
	public static int SCREEN_AREA_TYPE_AREA = 1;

	public DeviceService() {
		super.baseDao = SpringContextHolder.getBean(DeviceDao.class);
	}

	public Device addByModel(DeviceModel model) {
		Device device = new Device();
		device.setDeviceId(model.getDeviceId());
		device.setType(model.getType());
		device.setName(model.getName());
		device.setPosit(model.getPosit());
		device.setIp(model.getIp());
		device.setOpen(model.getOpen());
		device.setClose(model.getClose());
		device.setPort(model.getPort());
		Device d = deviceDao.save(device);
		return d;
	}

	public boolean add(DeviceModel model) {
		boolean flag = true;
		Device device = addByModel(model);
		if (device == null) {
			flag = false;
		}
		if(device.getType()==TERMINAL_TYPE){
			addDriveway(model.getDrivewaySelect(),device);
		}
		LogUtil.save("设备管理模块", LogUtil.Option.ADD, "新增设备信息：" + device.toString());
		return flag;
	}

	public void addDriveway(List<String> drivewaySelect,Device terminal){
		List<Integer> driveayIds = Lists.newArrayList();
		if(drivewaySelect!=null){
			for(String select:drivewaySelect){
				driveayIds.add(Integer.parseInt(select));
			}
			DrivewayUtils.resetTerminal(terminal.getId());
			DrivewayUtils.addTerminal(driveayIds, terminal);
		}
	}
	
	public boolean edit(DeviceModel model) throws UnknownHostException, IOException {
		boolean flag = true;
		Device device = deviceDao.findOne(model.getId());
		if (device == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		try {
			BeanUtils.copyProperties(model, device);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("硬件管理-设备管理-实体类与Model类转化失败", e);
			e.printStackTrace();
		}
		device = deviceDao.save(device);
		if (device == null) {
			logger.error("修改失败");
			flag = false;
			return flag;
		}
		if(device.getType()==TERMINAL_TYPE){
			addDriveway(model.getDrivewaySelect(),device);
		}
		LogUtil.save("设备管理模块", LogUtil.Option.ADD, "修改设备信息：" + device.toString());
		return flag;
	}
	
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 *            摄像机Id {1,2,3}
	 * @return boolean
	 */
	public boolean delete(String ids) {
		boolean flag = false;
		List<Integer> idList = super.getIdList(ids);
		int count = deviceDao.softdelete(idList);
		if (count > 0) {
			flag = true;
			for (int id : idList) {
				LogUtil.save("摄像机管理", LogUtil.Option.DEL, "删除摄像机：" + id);
			}
		}

		return flag;
	}


	public DeviceModel getDeviceById(int id) {
		Device device = deviceDao.findOne(id);
		return DeviceExtend.getModelByDevice(device);
	}

	/**
	 * 查找所有显示屏
	 * @return
	 */
	public List<BaseModel> getDeviceByScreen() {
		List<Device> device = deviceDao.findByTypeAndDelFlagFalse(2);
		return DeviceExtend.coverToModel(device);
	}

	

	@Override
	protected Specification<Device> buildSpecification(BaseModel model) {
		DeviceModel deviceModel = (DeviceModel) model;
		Integer type = deviceModel.getType();
		String deviceId = deviceModel.getDeviceId();
		String name = deviceModel.getName();

		return new Specification<Device>() {

			@Override
			public Predicate toPredicate(Root<Device> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("type"), type));
				if (type == TERMINAL_TYPE) {
					if (StringUtils.isNotNullAndEmpty(deviceId)) {
						list.add(cb.like(root.get("deviceId"), "%" + deviceId
								+ "%"));
					}
					if (StringUtils.isNotNullAndEmpty(name)) {
						list.add(cb.like(root.get("name"), "%" + name + "%"));
					}
				}
				list.add(cb.equal(root.get("delFlag"), 0));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

	}
	
	public Direction getDirection(Integer dir){
		if (dir == 0) {
			return LedUtils.Direction.UP;
		} else if (dir == 1) {
			return LedUtils.Direction.DOWN;
		} else if (dir == 2) {
			return LedUtils.Direction.LEFT;
		} else {
			return LedUtils.Direction.RIGHT;
		}
	}
	
	public Color getColor(Integer col){
		if (col == 0) {
			return LedUtils.Color.RED;
		} else if (col == 1) {
			return LedUtils.Color.GREEN;
		} else{
			return LedUtils.Color.YELLOW;
		} 
	}

	/**
	 * 启用设备
	 * 
	 * @param brakeId
	 *            设备Id
	 * @return
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public boolean openBrake(int brakeId) throws AWTException, UnknownHostException, IOException {
		boolean flag = false;
		boolean flag1 = false;
		DeviceModel brake = getDeviceById(brakeId);
		String ip = brake.getIp();
		Integer open = brake.getOpen();
		Integer port = brake.getPort();
		Robot robot = new Robot();
		//根据Ip ping当前设备的状态
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		if(status == true){
			flag1 = openOrCloseBrake(open,ip,port,robot);
			if ( flag1 == true) {
				flag = true;
				LogUtil.save("硬件模块", LogUtil.Option.OPENBRAKEMACHINE, "开启闸机信息：" + brake.toString());
			} else {
				flag = false;
			}
		}else flag = false;
		return flag;
	}
	
	/**
	 * 开启关闭闸机 硬件控制
	 * @param open
	 * @param ip
	 * @param port
	 * @param robot
	 * @return
	 */
	public boolean openOrCloseBrake(Integer state,String ip,Integer port,Robot robot){
		if (state == 1) {
			if (RelayUtils.openFirst(ip, port)) {
				logger.info("开信号成功");
				robot.delay(200);
				if (RelayUtils.closeFirst(ip, port)) {
					logger.info("关信号成功");
					return true;
				} else {
					logger.info("关信号失败");
					return false;
				}
			} else {
				logger.info("开信号失败");
				return false;
			}
		} else if (state == 2) {
			if (RelayUtils.openSecond(ip, port)) {
				logger.info("开信号成功");
				robot.delay(200);
				if (RelayUtils.closeSecond(ip, port)) {
					logger.info("关信号成功");
					return true;
				} else {
					logger.info("关信号失败");
					return false;
				}
			} else {
				logger.info("开信号失败");
				return false;
			}
		} else if (state == 3) {
			if (RelayUtils.openThird(ip, port)) {
				logger.info("开信号成功");
				robot.delay(200);
				if (RelayUtils.closeThird(ip, port)) {
					logger.info("关信号成功");
					return true;
				} else {
					logger.info("关信号失败");
					return false;
				}
			} else {
				logger.info("开信号失败");
				return false;
			}
		} else {
			if (RelayUtils.openFourth(ip, port)) {
				logger.info("开信号成功");
				robot.delay(200);
				if (RelayUtils.closeFourth(ip, port)) {
					logger.info("关信号成功");
					return true;
				} else {
					logger.info("关信号失败");
					return false;
				}
			} else {
				logger.info("开信号失败");
				return false;
			}
		}
	}
	
	/**
	 * 启用设备
	 * 
	 * @param brakeId
	 *            设备Id
	 * @return
	 * @throws AWTException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public boolean closeBrake(int brakeId) throws AWTException, UnknownHostException, IOException {
		boolean flag = false;
		boolean flag1 = false;
		DeviceModel brake = getDeviceById(brakeId);
		String ip = brake.getIp();
		Integer close = brake.getClose();
		Integer port = brake.getPort();
		Robot robot = new Robot();
		boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
		if(status == true){
			flag1 = openOrCloseBrake(close,ip,port,robot);
			if (flag1 == true)
				flag = true;
			LogUtil.save("硬件模块", LogUtil.Option.CLOSEBRAKEMACHINE, "关闭闸机信息：" + brake.toString());
		}else flag = false;
		return flag;
	}

	public String getGenId(String entitySelfName) {
		Device device = baseDao.findMaxIdRec();
		int id = 0;
		if (device != null) {
			id = device.getId() + 1;
		}
		return entitySelfName + id;
	}

	/**
	 * 获取所有显示屏、闸机的在线离线状态
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@Scheduled(cron = "0 0 02 * * *")
	public void getScreenLineState() throws UnknownHostException, IOException{
 		List<Device> devices = deviceDao.findAll();
		for(Device device :devices){
			Integer type = device.getType();
			String ip = device.getIp();
			Integer port = device.getPort();
			boolean status = InetAddress.getByName(ip).isReachable(timeOut); 
			if(status == true){
				//LED
				if(type==2){
					Integer icNum = device.getOpen();//LED的控制卡地址
					LedAddress address = new LedAddress(ip, port,icNum); 
					boolean lineState = LedUtils.detectionState(address);//获取当前状态的方法
					if(lineState==true){
						//TODO
					//	deviceDao.enableLineState(device.getDeviceId());
					}
				}
				//闸机
				else if(type==1){
					boolean lineState = RelayUtils.detectionState(ip,port);//获取当前状态的方法
					if(lineState==true){
						//TODO
					//	deviceDao.enableLineState(device.getDeviceId());
					}
				}
			}
		}
	}

	@Override
	protected List<BaseModel> getModelsByBeans(List<Device> entities) {
		return DeviceExtend.coverToModel(entities);
	}
	
     
 	public List<BaseModel> getAllBrakeMachineByName(String name) {
		if(StringUtils.isNotNullAndEmpty(name)){
			return DeviceExtend.coverToModel(deviceDao.findByNameLikeAndTypeAndDelFlagFalse("%" + name + "%",BRAKEMACHINE));
		}
		else {
			return DeviceExtend.coverToModel(deviceDao.findByTypeAndDelFlagFalse(BRAKEMACHINE));
		}
	}
 	
 	public List<BaseModel> getAllDataMasterByName(String name) {
		if(StringUtils.isNotNullAndEmpty(name)){
			return DeviceExtend.coverToModel(deviceDao.findByNameLikeAndTypeAndDelFlagFalse("%" + name + "%",DATAMASTER));
		}
		else {
			return DeviceExtend.coverToModel(deviceDao.findByTypeAndDelFlagFalse(DATAMASTER));
		}
	}
    
 	
 	public Device getBrakeMachineByDriveway(int id){
 		return deviceDao.findByDrivewayIdAndType(id,BRAKEMACHINE);
 	}
}
