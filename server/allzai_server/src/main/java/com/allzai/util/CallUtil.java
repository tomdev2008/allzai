package com.allzai.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CallUtil {
	
	private static final Properties properties = new Properties();
	private static final String CONFIG_FILE = "/call.properties";

	private static Map<String, String> shellMap = new HashMap<String, String>();

	public static boolean execCmd(String query, Object callback) {
		
		if(shellMap.size() <= 0) {
			try {
				properties.load(CallUtil.class.getResourceAsStream(CONFIG_FILE));
				
				shellMap.put("sdk_call_game_path", properties.getProperty("sdk_call_game_path"));			//助手通知游戏的脚本路径
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return CmdUtil.execCmdMethod(shellMap.get("sdk_call_game_path"), String.valueOf(callback), query);
		
	}

}
