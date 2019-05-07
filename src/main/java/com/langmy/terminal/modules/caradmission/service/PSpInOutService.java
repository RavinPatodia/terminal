package com.langmy.terminal.modules.caradmission.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.langmy.terminal.common.entity.AdminssionRec;
import com.langmy.terminal.common.entity.Car;
import com.langmy.terminal.common.entity.PSp;
import com.langmy.terminal.common.entity.ParkingRec;
import com.langmy.terminal.common.utils.ListUtils;
import com.langmy.terminal.modules.caradmission.dao.AdmissionRecDao;
import com.langmy.terminal.modules.caradmission.dao.CarDao;
import com.langmy.terminal.modules.caradmission.dao.ParkingRecDao;
import com.langmy.terminal.modules.caradmission.model.PSpInOutModel;
import com.langmy.terminal.modules.device.utils.LockUtils;
import com.langmy.terminal.modules.psp.utils.PSpUtils;

/**
 * 出入车位的业务层
 * 
 * @author lxj
 *
 */
@Service
public class PSpInOutService {

	public static Logger logger = LoggerFactory.getLogger(PSpInOutService.class);
	
	@Autowired
	private ParkingRecDao parkingRecDao;
	@Autowired
	private AdmissionRecDao admissionRecDao;
	@Autowired
	private CarDao carDao;

	/**
	 * 进入车位
	 * 
	 * @param model
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void PSpIn(PSpInOutModel model) throws UnknownHostException,
			IOException {
		ParkingRec parkingRec = new ParkingRec();

		Car car = carDao.findByLicensePlateAndDelFlagFalse(model
				.getLicensePlate());
		parkingRec.setCar(car);

		AdminssionRec admissionRec = new AdminssionRec();
		List<AdminssionRec> admissionRecs = admissionRecDao
				.findEnterAdmissionRec(model.getLicensePlate());
		if (ListUtils.notNullAndEmpty(admissionRecs)) {
			admissionRec = admissionRecs.get(0);
		}
		parkingRec.setAdminssionRec(admissionRec);

		PSp psp = PSpUtils.findPSpByPSpId(model.getPspId());
		parkingRec.setPSp(psp);

		parkingRec.setInTime(new Date());

		parkingRec = parkingRecDao.save(parkingRec);

		PSpUtils.carInPsp(model.getPspId(), car);

	}

	/**
	 * 离开车位
	 * 
	 * @param model
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void PSpOut(PSpInOutModel model) throws UnknownHostException,
			IOException {
		ParkingRec parkingRec = new ParkingRec();
		List<ParkingRec> parkingRecs = parkingRecDao
				.getCurrentParkingRecsByPspId(model.getPspId());
		if (ListUtils.notNullAndEmpty(parkingRecs)) {
			parkingRec = parkingRecs.get(0);
		} else {
			logger.error("查不到停车记录");
			return;
		}
		parkingRec.setOutTime(new Date());
		parkingRec = parkingRecDao.save(parkingRec);

		LockUtils.closeLockById(parkingRec.getPSp().getId());
		PSpUtils.carOutPsp(model.getPspId());

	}

}
