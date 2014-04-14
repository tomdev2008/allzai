package com.allzai.server.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.allzai.form.config.SysConfigForm;
import com.allzai.system.ServerEnvLoader;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;

/**
 * 提供系统配置管理服务
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-10
 * @see     ServerEnvLoader.configCacheMap
 * @since   JDK 1.6
 */
public class SystemConfigServer 
{
	/** 系统配置服务 */
	private static final SystemConfigServer configService = new SystemConfigServer();
	
	public static SystemConfigServer getInstance()
	{
		return SystemConfigServer.configService;
	}
	
	/**
	 * 获取系统配置列表Json格式数据
	 * @throws Exception 
	 */
	public JsonObject getSysConfigListForJson(SysConfigForm form) throws Exception
	{
		JsonObject json = new JsonObject();
		
		try {
			Map<String,Object> itemMap;
			Entry<String, Map<String, Object>> entry;
			
			Map<String, Map<String, Object>> tempMap = new HashMap<String, Map<String, Object>>();
			//拷贝系统配置内存
			tempMap.putAll(ServerEnvLoader.configCacheMap);
			
			JsonArray array = new JsonArray();
			JsonObject object = null;
			//处理配置数据
			for (Iterator<Entry<String, Map<String, Object>>> iterator = tempMap.entrySet().iterator(); iterator.hasNext();) 
			{
				object = new JsonObject();
				entry = iterator.next();
				itemMap = entry.getValue();
				object.put(String.valueOf(itemMap.get("key")), String.valueOf(itemMap.get("value")));
				array.put(object);
			}
			itemMap = null;
			entry = null;
			
			/**
			 * Dx0000:查询成功
			 */
			json.put("result", Boolean.TRUE);
			json.put("code", "Dx0000");
			json.put("configs", array);
			return json;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Dx0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Dx0001");
			return json;
		}
	}
}
