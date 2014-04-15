package com.allzai.action;

import java.io.IOException;

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
import com.restfb.json.JsonObject;

/**
 * 响应动作支持类，返回类型为JSON<p>
 */
public abstract class BaseActionSupport extends HttpServlet 
{

	private static final long serialVersionUID = 7371056226740639176L;
	
	private static final Logger logger = Logger.getLogger(BaseActionSupport.class);
	
	/**
	 * 自动响应执行结果<p>
	 * 
	 * @param  input
	 * @param  req
	 * @param  resp 
	 * @return String 执行结果
	 * @throws Exception
	 */
	public abstract JsonObject doAutoAction(Object obj, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		/**
		 * -10001:不支持GET请求
		 */
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/json");
			
			resp.getWriter().append(Hosts.InvalidRequestResponse(Hosts.getIpAddr(req),  "-1x0001"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		try {
			this.initService(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initService(HttpServletRequest req, HttpServletResponse resp)
			throws Exception 
	{
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		
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
				BeanUtils.populate(obj, req.getParameterMap());
				logger.info("ip = " + ip + ", area = " + area + ", country = " + country + ", req = " + obj.toString());
			}
		} 
		catch (Exception e) 
		{
			logger.warn("Failed: getFromBean() Exception", e);
			
			/**
			 * -1x0002:内部解析异常
			 */
			resp.getWriter().append(Hosts.InvalidRequestResponse(ip, "-1x0002"));
			return;
		}

		try 
		{
			JsonObject json = doAutoAction(obj, req, resp);
			json.put("level", Constants.LEVEL_FUNCTION);
			json.put("info", LangUtil.getCodeInfoByLang(lang, json.getString("code")));
			
			logger.info("ip = " + ip + ", area = " + area + ", country = " + country + ", resp = " + json.toString());
			
			 resp.getWriter().append(json.toString());
		} 
		catch (Exception e) 
		{
			logger.error("Failed: doAutoAction() Exception", e);

			/**
			 * -1x0003:内部处理异常
			 */
			resp.getWriter().append(Hosts.InvalidRequestResponse(ip, "-1x0003"));
		}
	}

	/**
	 * 匹配接口参数类型
	 * 
	 * @return
	 */
	public abstract Class<?> getFromBean();

}

