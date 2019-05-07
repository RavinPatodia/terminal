package com.langmy.terminal.modules.sys.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.langmy.terminal.common.config.Constant.UserGroupType;

import com.langmy.terminal.common.config.Global;
import com.langmy.terminal.common.entity.ParkingEnvir;
import com.langmy.terminal.common.entity.SmsRec;
import com.langmy.terminal.common.entity.UGroup;
import com.langmy.terminal.common.utils.BeanUtils;
import com.langmy.terminal.common.utils.DateUtils;
import com.langmy.terminal.common.utils.PropertiesLoader;
import com.langmy.terminal.modules.sys.dao.ParkingEnvirDao;
import com.langmy.terminal.modules.sys.dao.SmsRecDao;
import com.langmy.terminal.modules.sys.model.ParkingEnvirModel;
import com.langmy.terminal.modules.sys.model.SmsRecModel;
import com.langmy.terminal.modules.sys.service.extend.ParkingEnvirExtend;
import com.langmy.terminal.modules.user.utils.UGroupUtils;

/**
 * 停车场环境参数业务层
 * 
 * @author lzy
 *
 */
@Service
public class ParkingEnvirService {

	private static Logger logger = LoggerFactory
			.getLogger(ParkingEnvirService.class);
	private final static String FILE_NAME = "../../../../../../../../"
			+ "park/operator";

	@Autowired
	private ParkingEnvirDao parkingEnvirDao;
	@Autowired
	private SmsRecDao smsRecDao;

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 */
	public boolean edit(ParkingEnvirModel model, List<SmsRecModel> smsModels) {
		ParkingEnvir parkingEnvir = ParkingEnvirExtend.convertToEntity(model);
		if (parkingEnvirDao.save(parkingEnvir) == null) {
			logger.error("环境变量保存失败");
			return false;
		}
		Integer groupId = model.getDefaultGroup();
		if (groupId != null) {
			if (!saveDefalutUgroup(groupId)) {
				logger.error("默认临时客户组保存失败");
				return false;
			}
		}
		if (!saveSms(smsModels)) {
			logger.error("短信猫保存失败");
			return false;
		}
		Global.setConfig(parkingEnvir);
		return true;

	}

	/**
	 * 保存默认临时客户组
	 * 
	 * @param id
	 * @return
	 */
	public boolean saveDefalutUgroup(int id) {
		UGroupUtils.changeType(UserGroupType.DEFAULT_TEMPORARYUSER.getValue(),
				UserGroupType.TEMPORARYUSER.getValue());
		UGroup ugroup = UGroupUtils.findOne(id);
		ugroup.setType(UserGroupType.DEFAULT_TEMPORARYUSER.getValue());
		ugroup = UGroupUtils.save(ugroup);
		if (ugroup == null) {
			return false;
		}
		return true;
	}

	/**
	 * 保存
	 * 
	 * @param smsModels
	 * @return
	 */
	public boolean saveSms(List<SmsRecModel> smsModels) {
		List<SmsRec> smsList = smsRecDao.findAll();
		for (SmsRec s : smsList) {
			smsRecDao.delete(s);
		}
		for (SmsRecModel model : smsModels) {
			SmsRec sms = new SmsRec();
			try {
				BeanUtils.copyProperties(model, sms);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("停车场运行参数-SmsRecModel类和实体类赋值出错", e);
			}
			sms = smsRecDao.save(sms);
			if (sms == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取数据
	 * 
	 * @return
	 */
	public ParkingEnvirModel getData() {
		ParkingEnvir parkingEnvir = new ParkingEnvir();
		List<ParkingEnvir> parkingEnvirList = parkingEnvirDao.findAll();
		if (parkingEnvirList.size() > 0) {
			parkingEnvir = parkingEnvirList.get(0);
		}
		if (parkingEnvir == null) {
			logger.error("停车场环境变量未初始化");
			return null;
		}
		return ParkingEnvirExtend.convertToParkingEnvirModel(parkingEnvir);

	}

	public boolean backup(String path) {
		String courseFile = "";
		// 使用mysqldump来备份数据库
		// 获得park的各种属性
		PropertiesLoader propertiesLoader = new PropertiesLoader(
				"park.properties");
		// 链接数据库服务器的IP地址
		String serverIp = propertiesLoader.getProperty("serverIp");
		// 链接数据库的数据库名
		String database = propertiesLoader.getProperty("database");
		// 链接数据库的用户名
		String username = propertiesLoader.getProperty("jdbc.username");
		// 链接数据库的用户密码
		String password = propertiesLoader.getProperty("jdbc.password");

		// 获得当前的路径
		try {
			URL url = this.getClass().getResource("/");
			url = new URL(url, FILE_NAME);
			courseFile = url.getPath().substring(1);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		// 调用当前路径下的mysqldump。exe文件，按照格式依次写入服务器地址，用户名，用户密码，数据库名，设置编码格式为utf-8
		String str = "cmd /c " + courseFile + "\\mysqldump -h" + serverIp
				+ " -u" + username + " -p" + password
				+ " --set-charset=utf8 --opt " + database;
		try {
			// 运行环境，调用进程执行运行环境下的语句
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec(str);
			// 将得到的进程中的输入流保存下来
			InputStream in = child.getInputStream();
			// 用InputStreamReader读取输入流的信息
			InputStreamReader inReader = new InputStreamReader(in, "utf8");
			String instr;
			String outstr;
			StringBuffer sb = new StringBuffer("");
			BufferedReader br = new BufferedReader(inReader);
			// br.lines();
			// 缓冲流按行读取输入流的数据并保存
			while ((instr = br.readLine()) != null) {

				sb.append(instr + "\r\n");
			}
			outstr = sb.toString();

			// 要用来做导入用的sql目标文件：
			path = path.replaceAll("\\\\", File.separator);
			String parkId = propertiesLoader.getProperty("parkId");
			String fileName = parkId + "数据库备份"
					+ DateUtils.getDate("yyyyMMddHHmmss") + ".sql";
			FileOutputStream fout = new FileOutputStream(path + fileName);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
			writer.write(outstr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 别忘记关闭输入输出流
			in.close();
			inReader.close();
			br.close();
			writer.close();
			fout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 导入 导入的时候需要数据库已经建好。
	 */
	public boolean load(String fPath) {
		try {
			// fPath = "D:/test2.sql";
			String courseFile = "";
			// 使用mysql来恢复数据库
			// 获得park的各种属性
			Runtime rt = Runtime.getRuntime();
			PropertiesLoader propertiesLoader = new PropertiesLoader(
					"park.properties");
			String serverIp = propertiesLoader.getProperty("serverIp");
			String database = propertiesLoader.getProperty("database");
			String username = propertiesLoader.getProperty("jdbc.username");
			String password = propertiesLoader.getProperty("jdbc.password");
			// 获得当前的路径
			try {
				URL url = this.getClass().getResource("/");
				url = new URL(url, FILE_NAME);
				courseFile = url.getPath().substring(1);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			// 调用当前路径下的mysql.exe文件，按照格式依次写入服务器地址，用户名，用户密码，数据库名，设置编码格式为utf-8
			// 注意恢复的数据库一定是同名现存的
			Process process = rt.exec(courseFile + "\\mysql -h" + serverIp
					+ " -u" + username + " -p" + password
					+ " --default-character-set=utf8 " + database);
			OutputStream outputStream = process.getOutputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fPath), "UTF-8"));
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n\r");
			}
			str = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(outputStream,
					"utf-8");
			writer.write(str);
			writer.flush();
			outputStream.close();
			br.close();
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
