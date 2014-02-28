package com.allzai.system;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 装载系统初始资源
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-7
 * @see     DatabaseManager
 * @since   JDK 1.6
 */
public class ServerListener implements ServletContextListener 
{
	private static Logger logger = Logger.getLogger(ServerListener.class);

	public void contextDestroyed(ServletContextEvent event) 
	{
		DatabaseManager.destroyDBPool();
		logger.info("Lalasdk Service ended");
	}

	public void contextInitialized(ServletContextEvent event) 
	{
		try 
		{
			DatabaseManager.init();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Lalasdk service load error.");
			
			//非正常退出虚拟机
			System.exit(1);
		}
		
		CacheManager.init();
		logger.info("Lalasdk Service started");
	}
}
