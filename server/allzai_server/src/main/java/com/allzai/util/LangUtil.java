package com.allzai.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LangUtil {
	
	private static final Properties properties = new Properties();
	private static final String CONFIG_FILE = "/lang.properties";
	
	private static Map<String, String> codeInfoMap = new HashMap<String, String>();
	private static Map<String, String> langMap = new HashMap<String, String>(1);
	
	public static String defaultLang = "zh_CN";
	
	/**
	 * 獲取錯誤提示消息
	 * @param lang
	 * @param code
	 * @return
	 */
	public static String getCodeInfoByLang(String lang, String code) {
		
		code  = chooseLang(lang) + "/" + code;
		
		if(!codeInfoMap.containsKey(code)) {
			codeInfoMap.put(code, properties.getProperty(code, "unknown"));
		}
		
		return codeInfoMap.get(code);
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
