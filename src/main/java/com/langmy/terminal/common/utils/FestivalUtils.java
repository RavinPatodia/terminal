package com.langmy.terminal.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 通过定义在src/main/resource中的"节假日.xlsx"来定义节假日
 * @author lzy
 */
public class FestivalUtils {
	private final static String FILE_NAME = "节假日2015.xlsx";
	private static List<Date> festival = new ArrayList<Date>();// 节假日
	private static List<Date> workDay = new ArrayList<Date>();// 工作日

	private static void FestivalInit(){
		File excel = getExcel();
		try {
			FileInputStream fin = new FileInputStream(excel);
			XSSFWorkbook xssfworkbook = new XSSFWorkbook(fin);
			XSSFSheet sheet = xssfworkbook.getSheetAt(0);
			int last = sheet.getLastRowNum();
			int index = 1;
			Date dt = null;
			while (index <= last) {
				XSSFRow row = sheet.getRow(index);
				/* 读取法定节假日 */
				XSSFCell cell =  row.getCell(0);
				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						if (dt != null && dt.getTime() > 0) {
							festival.add(dt);
						}
					}
				}
				/* 读取特殊工作日 */
				cell = row.getCell(1);
				if (cell != null) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());

						if (dt != null && dt.getTime() > 0) {
							// System.out.println(this.getDate(dt));
							workDay.add(dt);
						}
					}
				}
				index++;
			}
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return Excel文件
	 */
	public static File getExcel() {
		File excel = null;
		try {
			URL url = FestivalUtils.class.getResource("/");
			url = new URL(url, FILE_NAME);
			excel = new File(url.getPath());
			return excel;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return excel;
	}

	/**
	 * 从EXCEL文件中读取节假日
	 * 
	 * @return
	 */
	public static List<Date> getFestival() {
		return festival;
	}

	/**
	 * 从Excle文件中读取法定工作日
	 * @return
	 */
	public List<Date> getSpecialWorkDay() {
		return workDay;
	}

	/**
	 * 判断一个日期是否日节假日 法定节假日只判断月份和天，不判断年
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isFestival(Date date) {
		FestivalInit();
		boolean festival = false;
		Calendar fcal = Calendar.getInstance();
		Calendar dcal = Calendar.getInstance();
		dcal.setTime(date);
		List<Date> list = getFestival();
		for (Date dt : list) {
			fcal.setTime(dt);

			// 法定节假日判断
			if (fcal.get(Calendar.MONTH) == dcal.get(Calendar.MONTH)
					&& fcal.get(Calendar.DATE) == dcal.get(Calendar.DATE)) {
				festival = true;
			}
		}
		return festival;
	}
	
	/**
	 * 判断今天是否是节假日
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isFestivalToDay() {
		FestivalInit();
		boolean festival = false;
		Calendar fcal = Calendar.getInstance();
		Calendar dcal = Calendar.getInstance();
		dcal.setTime(new Date());
		List<Date> list = getFestival();
		for (Date dt : list) {
			fcal.setTime(dt);
			// 法定节假日判断
			if (fcal.get(Calendar.MONTH) == dcal.get(Calendar.MONTH)
					&& fcal.get(Calendar.DATE) == dcal.get(Calendar.DATE)) {
				festival = true;
			}
		}
		return festival;
	}

	/**
	 * 周六周日判断
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		boolean weekend = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			weekend = true;
		}
		return weekend;
	}
	
	/**
	 * 周六
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSaturday(Date date) {
		boolean saturday = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			saturday = true;
		}
		return saturday;
	}
	
	/**
	 * 周六
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSaturday() {
		boolean saturday = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			saturday = true;
		}
		return saturday;
	}
	
	/**
	 * 周日
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSunDay(Date date) {
		boolean sunday = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			sunday = true;
		}
		return sunday;
	}
	
	/**
	 * 周日
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSunDay() {
		boolean sunday = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			sunday = true;
		}
		return sunday;
	}

	/**
	 * 是否是工作日 法定节假日和周末为非工作日
	 * 
	 * @param date
	 * @return
	 */
	public boolean isWorkDay(Date date) {
		boolean workday = true;
		if (FestivalUtils.isFestival(date) || FestivalUtils.isWeekend(date)) {
			workday = false;
		}

		/* 特殊工作日判断 */
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		for (Date dt : workDay) {
			cal2.setTime(dt);
			if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
					&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
					&& cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)) { // 年月日相等为特殊工作日
				workday = true;
			}
		}
		return workday;
	}

	public Date getDate(String str) {
		Date dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;

	}

	/**
	 * @param date yyyy-MM-dd格式format得到的字符串
	 * @return
	 */
	public String getDate(Date date) {
		String dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		dt = df.format(date);

		return dt;

	}

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = formatter.parse("2015-10-1");
		
		
		System.out.println(startTime.toString());

		System.out.println(FestivalUtils.isFestivalToDay());
	}

}
