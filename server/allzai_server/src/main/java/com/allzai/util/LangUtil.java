package com.allzai.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LangUtil {
	
	private static final Properties properties = new Properties();
	private static final String CONFIG_FILE = "/lang.properties";
	
	private static Map<String, String> langMap = new HashMap<String, String>(2);
	private static String defaultLang = "zh_CN";
	
	/**
	 * 獲取錯誤提示消息
	 * @param lang
	 * @param code
	 * @return
	 */
	public static String getCodeInfoByLang(String lang, String code) {
		
		return properties.getProperty((chooseLang(lang) + code), "unknown");
	}

	/**
	 * 初始化平台语言
	 * @throws IOException 
	 */
	public static void initLangMap() throws IOException {
		
		langMap.clear();
		
		//langMap.put("en", null);
		langMap.put("zh_CN", null);
		
		properties.load(LangUtil.class.getResourceAsStream(CONFIG_FILE));
	}
	
	/**
	 * 匹配平台支持的語言
	 * @return
	 */
	private static String chooseLang(String lang) {
		
		return langMap.containsKey(lang) ? lang : defaultLang;
	}

}
