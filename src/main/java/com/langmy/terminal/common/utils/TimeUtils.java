package com.langmy.terminal.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param date1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param date2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(Date date1, Date date2) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		day = diff / (24 * 60 * 60 * 1000);
		hour = (diff / (60 * 60 * 1000) - day * 24);
		min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long[] times = { day, hour, min, sec };
		return times;
	}
	
	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param date1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param date2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static double getDistanceHours(Date date1, Date date2) {
		long[] times = getDistanceTimes(date1,date2);
		long day = times[0];
		long hour = times[1];
		long min = times[2];
		double sumHour = 0;
		double minHour = min/60.0;
		sumHour+=day*24+hour+minHour;
		return sumHour;
	}
	
	/**
	 * 两个时间相差距离多少分钟
	 * 
	 * @param date1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param date2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static int getDistanceMin(Date date1, Date date2) {
		long[] times = getDistanceTimes(date1,date2);
		long day = times[0];
		long hour = times[1];
		long min = times[2];
		int sumMin = 0;
		sumMin+=day*24*60+hour*60+min;
		return sumMin;
	}
	
	
}
