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
		
		logger.info("ServerListener Ended.");
	}

	public void contextInitialized(ServletContextEvent event) 
	{
		try 
		{
			DatabaseManager.init();
			
			CacheManager.init();
			
			logger.info("ServerListener Started.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ServerListener Error.");
			
			return;
		}
	}
}
