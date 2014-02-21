package com.allzai.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MySqlUtil {

	//private static final String[] keys = new String[] { "h00", "h01", "h02", "h03", "h04", "h05", "h06", "h07", "h08", "h09", "h10", "h11", "h12", "h13", "h14", "h15", "h16", "h17", "h18", "h19", "h20", "h21", "h22", "h23" };

	/***
	 * 在线记录
	 * @param UUID
	 * @param beginTime
	 * @param finishTime
	 * @param reportTime
	 * @param receiveTime
	 * @return
	 */
	public static List<String> getAllOnLineSQL(String UUID, long beginTime, long finishTime, long reportTime, long receiveTime) {
		
		List<String> list = new ArrayList<String>(5);

		beginTime = beginTime + receiveTime - reportTime;	//调整时差
		finishTime = finishTime + receiveTime - reportTime;
		
		String beginDate = Constants.sdf_yMd_Hms.format(beginTime);
		String finishDate = Constants.sdf_yMd_Hms.format(finishTime);
		
		long duration = (finishTime - beginTime) / Constants.MINUTE;	//在线时长
		
		String beginDay = beginDate.split(" ")[0];	//登录日期
		String middleDay = Constants.sdf_yMd.format(((beginTime + finishTime) / 2));	//中段日期
		String finishDay = finishDate.split(" ")[0];	//退出日期
		
		String beginHour = "h" + beginDay.split(" ")[1].split(":")[0];	//登录小时
		String finishHour = "h" + finishDay.split(" ")[1].split(":")[0];	//退出小时
		
		String dailySQL = String.format("UPDATE daily SET startup=startup+1 WHERE UUID = '%s' AND date = '%s'", UUID, beginDay);
		list.add(dailySQL);
		dailySQL = String.format("UPDATE daily SET duration=duration+%s WHERE UUID = '%s' AND date = '%s'", duration, UUID, middleDay);
		list.add(dailySQL);
		dailySQL = String.format("UPDATE daily SET shutdown=shutdown+1 WHERE UUID = '%s' AND date = '%s'", UUID, finishDay);
		list.add(dailySQL);
		dailySQL = String.format("UPDATE startup SET %s=%s+1 WHERE UUID = '%s' AND date = '%s'", beginHour, beginHour, UUID, beginDay);
		list.add(dailySQL);
		dailySQL = String.format("UPDATE shutdown SET %s=%s+1 WHERE UUID = '%s' AND date = '%s'", finishHour, finishHour, UUID, finishDay);
		list.add(dailySQL);
		
		return list;
	}

	/***
	 * 注册记录
	 * @param UUID
	 * @param registerTime
	 * @param reportTime
	 * @param receiveTime
	 * @return
	 */
	public static List<String> getAllRegisterSQL(String UUID, long registerTime, long reportTime, long receiveTime) {

		List<String> list = new ArrayList<String>(1);

		registerTime = registerTime + receiveTime - reportTime;	//调整时差
		
		String registerDay = Constants.sdf_yMd.format(registerTime);	//注册日期
		String dailySQL = String.format("UPDATE daily SET register=register+1 WHERE UUID = '%s' AND date = '%s'", UUID, registerDay);
		list.add(dailySQL);
		
		return list;
	}

	public static List<String> getAllFinishSQL(String UUID, long long1, long long2, long reportTime, long receiveTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public static float getNumberFormat(int minute, double sum) {
		NumberFormat nf=NumberFormat.getNumberInstance() ; 
		nf.setMaximumFractionDigits(2); 
		return Float.parseFloat(nf.format(minute / sum));
	}

}
