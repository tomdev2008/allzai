package com.allzai.system;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.allzai.util.LangUtil;

/**
 * 装载系统初始资源
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
			
			LangUtil.initLangMap();
			
			logger.info("ServerListener Started.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ServerListener Error.");
			
			return;
		}
	}
}
