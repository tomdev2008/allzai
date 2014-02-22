package com.allzai.util;

import java.util.Calendar;
import java.util.Date;

public class TimeHelpUtil {

	/**
	 * @return 当前时间
	 */
	public static String getDateOfTime() {
		return Constants.sdf_yMd_Hms.format(new Date());
	}

	/**
	 * @return 当前日期
	 */
	public static String getDateOfDay() {
		return Constants.sdf_yMd.format(new Date());
	}

	/**
	 * @return 当前日期
	 */
	public static String getDateOfMonth() {
		return Constants.sdf_yM.format(new Date());
	}

	/**
	 * @param prev_date
	 * @param next_date
	 * @return 比较时间
	 */
	public static boolean getDateCompare(String prev_date, String next_date) {
		return next_date.compareTo(prev_date) > 0;
	}

	/**
	 * @param diff
	 * @return 指定日期
	 */
	public static String getDateDiffDay(int diff) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(calendar.DATE, diff);
		date = calendar.getTime(); 
		return  Constants.sdf_yMd.format(date);
	}

}
