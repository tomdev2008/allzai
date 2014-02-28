package com.yeahmobi.gamelala.system;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 实时刷新系统缓存信息<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-7
 * @since   JDK 1.6
 */
public class SystemCacheManager 
{
	private static boolean lock = false;
	
	private static ScheduledExecutorService execService = Executors.newScheduledThreadPool(1);

	private static Logger logger = Logger.getLogger(SystemCacheManager.class);

	private SystemCacheManager() 
	{
		
	}

	public static void init()
	{
		logger.info("data manager init started");
		onUpdate();
		execService.scheduleWithFixedDelay(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				onUpdate();
			}
		}, 3, 1, TimeUnit.SECONDS);
		logger.info("data manager init finished");
	}

	public static boolean onUpdate()
	{
		logger.debug("trying to load data");
		try 
		{
			if (!lock) 
			{
				lock = true;
				String errorMsg = ServerEnvLoader.initServer();
				lock = false;
				if (errorMsg == null) 
				{
					logger.debug("data loaded");
					return true;
				} 
				else 
				{
					logger.error("Failed: data not loaded" + errorMsg);
					ServerEnvLoader.setTriggerFull(true);
					return false;
				}
			}
		}
		catch (Exception e) 
		{
			logger.error(e);
		}
		finally 
		{
			lock = false;
		}

		return false;
	}

}