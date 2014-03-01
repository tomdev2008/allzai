package com.allzai.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.allzai.des3.ThreeDESUtil;
import com.restfb.json.JsonObject;

public class Hosts {
	
	private static final Logger logger = Logger.getLogger(Hosts.class);

	/***
	 * 获得客户端真实的IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}
	
	/***
	 * 从已解密的请求串中封装参数
	 * @param gll
	 * @return
	 */
	public static Map<String, Object> getReqKeys(String gll) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isEmpty(gll)) {
			return map;
		}
		String[] keys = gll.split("&");
		String[] vals = null;
		for(String key : keys) {
			vals = key.split("=");
			if(vals.length > 1) {
				map.put(vals[0], vals[1]);
			} else {
				map.put(vals[0], "");
			}
		}
		return map;
	}
	
	/**
	 * 构建系统级别的错误响应
	 * @param result
	 * @param code
	 * @param info
	 * @return
	 * @throws Exception 
	 */
	public static String InvalidRequestResponse(String ip, String code) throws Exception {
		
		JsonObject json = new JsonObject();
		json.put("result", Boolean.FALSE);
		json.put("code", code);
		json.put("info", LangUtil.getCodeInfoByLang(LangUtil.defaultLang, code));
		
		logger.info("ip = " + ip + ", err = " + json.toString());
		return ThreeDESUtil.Encode(json.toString(), Constants.index_az_decode);
	}

}
