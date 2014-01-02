package com.allzai.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.allzai.pool.DatabaseManager;
import com.allzai.system.SystemCacheManager;

public class ServerListener implements ServletContextListener 
{
	private static final Logger logger = Logger.getLogger(ServerListener.class);

	public void contextDestroyed(ServletContextEvent event) 
	{
		DatabaseManager.destroyDBPool();
		logger.info("allzai ended");
	}

	public void contextInitialized(ServletContextEvent event) 
	{
		try  {
			DatabaseManager.init();
			SystemCacheManager.init();
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("allzai load error.");
		}
	}

}
