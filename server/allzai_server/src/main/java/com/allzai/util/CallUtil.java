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
				
				shellMap.put("curl_call_path", properties.getProperty("curl_call_path"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return CmdUtil.execCmdMethod(shellMap.get("curl_call_path"), String.valueOf(callback), query);
		
	}

}
