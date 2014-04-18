package com.chat.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import com.restfb.json.JsonObject;

public class Constancts {
	
	//消息
	public static final Map<Integer, Queue<JsonObject>> MSG = new HashMap<Integer, Queue<JsonObject>>();
	
	//时间
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//平台
	public static final String platform_APP = "APP";
	
	//动作
	public static final String action_CHECK = "CHECK";
	public static final String action_SEND = "SEND";

}
