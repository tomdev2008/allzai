package com.allzai.util;

import java.text.SimpleDateFormat;

/**
 * 系统常量类
 */
public final class Constants
{

	/**
	 * 固定加密串的下标
	 */
	public static final int index_tk_deocde = 1;
	public static final int index_pw_deocde = 2;
	
	/**
	 * 数字定义串
	 */
	public static final int ZERO_NUMBER = 0;
	public static final int ONE_NUMBER = 1;
	public static final int TWO_NUMBER = 2;
	
	/** 字符 "0" */
	public static final String ZERO_STR = "0";
	/** 字符 "1" */
	public static final String ONE_STR = "1";
	/** 字符 "2" */
	public static final String TWO_STR = "2";
	/** 字符 "3" */
	public static final String THREE_STR = "3";
	/** 字符 "4" */
	public static final String FOUR_STR = "4";
	
	/** 字符 "-1" */
	public static final String MINUS_ONE_STR = "-1";
	/** 字符 "-2" */
	public static final String MINUS_TWO_STR = "-2";
	/** 字符 "-3" */
	public static final String MINUS_THREE_STR = "-3";
	/** 字符 "-4" */
	public static final String MINUS_FOUR_STR = "-4";
	
	public static final String EMAIL_REGEX = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";

	/** 默认休眠数(毫秒) */
	public static final int sleepTime = 1000;
	
	/** slave(只读)数据 */
	public static final String SLAVE = "slave";
	
	/** master(读写)数据库 */
	public static final String MASTER = "master";
	
	/** 数据库表名 begin */
	public static final String USER_TABLE_NAME = "user_info";
	public static final String USER_APP_TABLE_NAME = "device_app_info";
	public static final String MOBILE_DEVICE_TABLE_NAME = "mobile_device_info";
	/** 数据库表名 end */
	
	/** 秒*/
	public static final long SECOND = 1000;
	/** 分*/
	public static final long MINUTE = 60 * SECOND;
	/** 时*/
	public static final long HOUR = 60 * MINUTE;
	/**天*/
	public static final long DAY = 24 * HOUR;
	/**周*/
	public static final long WEEK = 7 * DAY;
	
	/**允许上传的最小图片大小1K*/
	public static final long MIN_FILE_SIZE = 1 * 1024;
	/**允许上传的最大图片大小10M*/
	public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
	
	/**文件压制的宽度*/
	public static final int FileOutputWidth = 75;
	/**文件压制的高度*/
	public static final int FileOutputHeight = 75;
	/**文件压制的后缀*/
	public static final String FileSuffix = ".jpg";

	/**日期的格式化处理*/
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**定义消息的级别*/
	public static final String LEVEL_SYSTEM = "system";
	public static final String LEVEL_FUNCTION = "function";
	
	/**用户类型的分类*/
	public static final int NORMAL_USER_ROLE = 0;
	public static final int GUEST_USER_ROLE = 1;
	
}
