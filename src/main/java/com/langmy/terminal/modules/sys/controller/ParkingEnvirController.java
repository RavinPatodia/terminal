package com.langmy.terminal.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.langmy.terminal.common.web.BaseController;

/**
 * 停车场环境变量 控制器
 * 
 * @author lxj
 *
 */
@Controller
@RequestMapping(value = "/admin/sys/parkingEnvir")
public class ParkingEnvirController extends BaseController {
//	@Autowired
//	private ParkingEnvirService parkingEnvirService;
//
//	private String getDataUrl = "/park/admin/sys/parkingEnvir/getParkingEnvir";
//	private String saveUrl = "/park/admin/sys/parkingEnvir/edit";
//	private String backupUrl = "/park/admin/sys/parkingEnvir/backup";
//	private String loadUrl = "/park/admin/sys/parkingEnvir/load";
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView getPage() {
//		Map<String, String> url = Maps.newHashMap();
//		url.put("getDataUrl", getDataUrl);
//		url.put("saveUrl", saveUrl);
//		url.put("backupUrl", backupUrl);
//		url.put("loadUrl", loadUrl);
//		return new ModelAndView("system/parkingEnvir/parkingEnvir", url);
//	}
//
//	/**
//	 * 获取停车场环境变量
//	 * 
//	 * @param response
//	 */
//	@RequestMapping(value = "/getParkingEnvir", method = { RequestMethod.GET })
//	public void getParkingEnvir(HttpServletResponse response) {
//		Json j = new Json();
//		ParkingEnvirModel model = parkingEnvirService.getData();
//		if (model != null) {
//			j.setSuccess(true);
//			j.setObject(model);
//			j.setMsg("停车场环境变量获取成功");
//		} else {
//			j.setSuccess(false);
//			j.setMsg("停车场环境变量获取失败");
//		}
//		super.writeJson(response, j);
//	}
//
//	/**
//	 * 修改停车场环境变量
//	 * 
//	 * @param model
//	 * @param response
//	 */
//	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
//	public void edit(String parkingEnvirJsonStr, String infoJsonStr,
//			HttpServletResponse response) {
//		Json j = new Json();
//		ParkingEnvirModel model = JSON.parseObject(parkingEnvirJsonStr,
//				ParkingEnvirModel.class);
//		JSONArray jsonArray = JSONArray.parseArray(infoJsonStr);
//		List<SmsRecModel> smsModels = new ArrayList<SmsRecModel>();
//		for (Object obj : jsonArray) {
//			SmsRecModel sms = JSON.parseObject(obj.toString(),
//					SmsRecModel.class);
//			smsModels.add(sms);
//		}
//		if (parkingEnvirService.edit(model, smsModels)) {
//			j.setSuccess(true);
//			j.setMsg("停车场环境变量保存成功");
//		} else {
//			j.setSuccess(false);
//			j.setMsg("停车场环境变量保存失败");
//		}
//		super.writeJson(response, j);
//	}
//
//	/**
//	 * 点击备份按钮 进行数据库备份
//	 * 
//	 * @param model
//	 * @param response
//	 */
//	@RequestMapping(value = "/backup", method = { RequestMethod.POST })
//	public void backup(ParkingEnvirModel model,HttpServletResponse response) {
//		Json j = new Json();
//		if (parkingEnvirService.backup(model.getBackupPath())) {
//			j.setSuccess(true);
//			j.setMsg("数据库备份成功");
//		} else {
//			j.setSuccess(false);
//			j.setMsg("数据库备份失败");
//		}
//		super.writeJson(response, j);
//	}
//
//	/**
//	 * 还原
//	 * 
//	 * @param model
//	 * @param response
//	 * @throws Exception 
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/load", method = { RequestMethod.POST })
//	public void load(ParkingEnvirModel model,@RequestParam MultipartFile[] myfiles, HttpServletResponse response) throws IOException, Exception {
//		Json j = new Json();
//		for (MultipartFile myfile : myfiles) {
//			if (!myfile.isEmpty()) {
//				String fileName = myfile.getOriginalFilename();
//				String fileType = fileName.substring(
//						fileName.lastIndexOf(".") + 1).toLowerCase();
//				String newFileName = UUID.randomUUID().toString()
//						.replaceAll("-", "")
//						+ "." + fileType;
//				String url = Global.getConfig("ftpUrl");
//				int port = Integer.parseInt(Global.getConfig("ftpPort"));
//				String username = Global.getConfig("ftpUserName");
//				String password = Global.getConfig("ftpPwd");
//				String path = Global.getConfig("ftpOperaterImgPath");
//				FtpTransfer.uploadFile(url, port, username, password, path,
//						newFileName, myfile.getInputStream());
//
//				String picUrl = path + "/" + newFileName;
//				model.setLoadPath(picUrl);
//			}
//		}
//		String path=model.getLoadPath();
//		if (parkingEnvirService.load(path)) {
//			j.setSuccess(true);
//			j.setMsg("数据库恢复成功");
//		} else {
//			j.setSuccess(false);
//			j.setMsg("数据库恢复失败");
//		}
//		super.writeJson(response, j);
//	}
}
