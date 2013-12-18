package com.allzai.util;

import java.text.SimpleDateFormat;

public final class Constants
{
	/**
	 * 日志存放目录, 不可变更
	 */
	public static final String sdk_log_file_path = "/home/ubuntu/hasoffer_sdk_logs/";

	/** 秒*/
	public static final long SECOND = 1000;
	/** 分*/
	public static final long MINUTE = 60 * SECOND;
	/** 时*/
	public static final long HOUR = 60 * MINUTE;
	
	/**
	 * 分钟百分比
	 */
	public static final double perminute = 60.0;

	/**
	 * 格式化时间
	 */
	public static final SimpleDateFormat sdf_yMd_Hms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat sdf_yMd_Hm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat sdf_yMd_H = new SimpleDateFormat("yyyy-MM-dd HH");
	public static final SimpleDateFormat sdf_yMd = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat sdf_yM = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 正常响应
	 */
	public static final String SUCCESSResponse = "{\"result\":true,\"info\":\"ok\"}";
	/**
	 * 失败响应
	 */
	public static final String FAILUREResponse = "{\"result\":false,\"info\":\"Invalid request\"}";

}
