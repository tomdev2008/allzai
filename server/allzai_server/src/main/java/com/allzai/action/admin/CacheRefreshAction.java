package com.allzai.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.allzai.system.ServerEnvLoader;
import com.allzai.util.Hosts;

/**
 * 刷新系统缓存<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-11
 * @see     ServerEnvLoader#setTriggerFull(boolean)
 * @since   JDK 1.6
 */
public class CacheRefreshAction extends HttpServlet
{
	/**
	 * 管理员刷新缓存的私有接口
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(CacheRefreshAction.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		boolean trigger = false;
		String req_ip = Hosts.getIpAddr(req);
		try {
			String[] ips_ = ServerEnvLoader.configCacheMap.get("admin_ip").get("value").toString().trim().split(";");
			for(String ip_ : ips_) 
			{
				if(req_ip.equals(ip_)) 
				{
					ServerEnvLoader.setTriggerFull(true);
					trigger = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.warn("admin try to refresh the cache, from ip : " + req_ip + ", result is : " + trigger);
	}

}
