package com.allzai.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.allzai.isp.IPLocation;
import com.allzai.isp.IPSeeker;
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
		json.put("level", Constants.LEVEL_SYSTEM);
		json.put("code", code);
		json.put("info", LangUtil.getCodeInfoByLang(LangUtil.defaultLang, code));
		
		IPLocation location = IPSeeker.getInstance().getIPLocation(ip);
		logger.info("ip = " + ip + ", area = " + location.getArea() + ", country = " + location.getCountry() + ", err = " + json.toString());
		return json.toString();
	}

}
