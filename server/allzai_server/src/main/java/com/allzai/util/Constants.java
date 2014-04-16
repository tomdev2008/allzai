package com.allzai.util;

import java.text.SimpleDateFormat;

public final class Constants
{
	
	public static final String GEETEST_ID = "de7689d1283867a3b72f3ee0da8641db";
	public static final String GEETEST_KEY = "3e81ef75d797e93c3496736b457ea3f6";

	public static final int index_tk_deocde = 1;
	public static final int index_pw_deocde = 2;

	public static final int ZERO_NUMBER = 0;
	public static final int ONE_NUMBER = 1;
	public static final int TWO_NUMBER = 2;
	
	public static final String ZERO_STR = "0";
	public static final String ONE_STR = "1";
	public static final String TWO_STR = "2";
	public static final String THREE_STR = "3";
	
	public static final String MINUS_ONE_STR = "-1";
	public static final String MINUS_TWO_STR = "-2";
	public static final String MINUS_THREE_STR = "-3";
	
	public static final String EMAIL_REGEX = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";

	public static final int sleepTime = 1000;
	
	public static final String SLAVE = "slave";
	public static final String MASTER = "master";
	
	public static final String USER_TABLE_NAME = "user_info";
	public static final String USER_APP_TABLE_NAME = "device_app_info";
	public static final String MOBILE_DEVICE_TABLE_NAME = "mobile_device_info";
	
	public static final long SECOND = 1000;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;
	public static final long DAY = 24 * HOUR;
	public static final long WEEK = 7 * DAY;
	
	public static final long MIN_FILE_SIZE = 1 * 1024;
	public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
	
	public static final int FileOutputWidth = 75;
	public static final int FileOutputHeight = 75;
	public static final String FileSuffix = ".jpg";

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat orderFormat = new SimpleDateFormat("yyyyMMddSS");
	
	public static final String LEVEL_SYSTEM = "system";
	public static final String LEVEL_FUNCTION = "function";
	
	public static final int NORMAL_USER_ROLE = 0;
	public static final int GUEST_USER_ROLE = 1;
	
}
