package com.allzai.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.allzai.isp.IPLocation;
import com.allzai.isp.IPSeeker;
import com.allzai.util.Constants;
import com.allzai.util.Hosts;
import com.allzai.util.LangUtil;
import com.allzai.util.StringUtil;
import com.restfb.json.JsonObject;

public abstract class BaseActionSupport extends HttpServlet 
{

	private static final long serialVersionUID = 7371056226740639176L;
	
	private static final Logger logger = Logger.getLogger(BaseActionSupport.class);

	public abstract JsonObject doAutoAction(Object obj, HttpServletRequest req, HttpServletResponse resp) throws Exception;

	private void initService(HttpServletRequest req, HttpServletResponse resp)
			throws Exception 
	{
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		String ip = Hosts.getIpAddr(req);
		IPLocation location = IPSeeker.getInstance().getIPLocation(ip);
		String area = location.getArea();
		String country = location.getCountry();
		String lang = LangUtil.defaultLang;

		Object obj = null;
		try 
		{
			if(getFromBean() != null) {
				obj = getFromBean().newInstance();
				Map<String, String> map = new HashMap<String, String>(req.getParameterMap());
				map.put("ip", ip);
				map.put("area", area);
				map.put("country", country);
				BeanUtils.populate(obj, map);
				logger.info("ip = " + ip + ", area = " + area + ", country = " + country + ", req = " + obj.toString());
			}
		} 
		catch (Exception e) 
		{
			logger.warn("Failed: getFromBean() Exception", e);
			
			/**
			 * -1x0001:内部解析异常
			 */
			resp.getWriter().append(Hosts.InvalidRequestResponse(ip, "-1x0001"));
			return;
		}

		try 
		{
			JsonObject json = doAutoAction(obj, req, resp);
			json.put("level", Constants.LEVEL_FUNCTION);
			json.put("info", LangUtil.getCodeInfoByLang(lang, json.getString("code")));
			
			//Ajax跨域
			String callback = req.getParameter("callback");
			boolean noback = StringUtil.isEmpty(callback);
			String ret = null;
			if(!noback) {
				ret = callback + "(" + json.toString() + ")";
			} else {
				ret = json.toString();
			}
			resp.getWriter().append(ret);
			
			logger.info("ip = " + ip + ", area = " + area + ", country = " + country + ", resp = " + ret);
		} 
		catch (Exception e) 
		{
			logger.error("Failed: doAutoAction() Exception", e);

			/**
			 * -1x0002:内部处理异常
			 */
			resp.getWriter().append(Hosts.InvalidRequestResponse(ip, "-1x0002"));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		try {this.initService(req, resp);} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		try {this.initService(req, resp);} catch (Exception e) {e.printStackTrace();}
	}

	/**
	 * 鍖归厤鎺ュ彛鍙傛暟绫诲瀷
	 * 
	 * @return
	 */
	public abstract Class<?> getFromBean();

}

