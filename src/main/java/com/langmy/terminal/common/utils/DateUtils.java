package com.langmy.terminal.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	public static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm","HH:mm"};
	
	public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getTime(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse("0000-00-00 "+formatDate(date, "HH:mm:ss"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
	/**
	 * 获取剩余的天数
	 * @param date
	 * @return
	 */
	public static long remainDays(Date date){
		long t = date.getTime()-new Date().getTime();
		return t/(24*60*60*1000);
	}
	
	/**
	 * 传入的date时间+传入的月数=返回的日期
	 * @param 
	 * @return
	 */
	public static Date dateAddMonths(Date date,int months){	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH,months);
		Date result = cal.getTime();
		return result;
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		if(date==null){
			logger.error("传入空值日期");
			return "";
		}
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}
	
	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}
	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay(Date d) {
		return formatDate(d, "dd");
	}
	/**
	 * 得到小时字符串 格式（HH）
	 */
	public static String getHour(Date d) {
		return formatDate(d, "HH");
	}
	/**
	 * 得到分钟字符串 格式（HH）
	 */
	public static String getMinute(Date d) {
		return formatDate(d, "mm");
	}
	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 判断当前时间是否在两个日期之间，忽略时分秒
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean isTodayBetweenTwoDay(Date begin, Date end){
		Date now = new Date();
		if(now.before(getDateEnd(end))&&now.after(getDateStart(begin)))
			return true;
		return false;
	}
	
	/**
	 * 判断当前时间是否在两个时间之间，忽略年月日
	 * @param begin 
	 * @param end
	 * @return
	 */
	public static boolean isNowWithinTwoTime(Date begin, Date end){
		Date now = getTime(new Date());
		if(now.before(getTime(end))&&now.after(getTime(begin)))
			return true;
		return false;
	}
	
	/*
	 * public static void main(String[] args) throws ParseException {
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date
	 * inTime = sdf.parse("2015-04-3 11:10:00"); Date outTime =
	 * sdf.parse("2015-04-3 11:13:00");
	 * 
	 * System.out.println(isTodayBetweenTwoDay(inTime,outTime)); }
	 */
}
