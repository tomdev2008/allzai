package com.allzai.system;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class SystemCacheManager {
	
	private static boolean lock_run = false;

	private static ScheduledExecutorService execService = Executors.newScheduledThreadPool(1);

	private static Logger logger = Logger.getLogger(SystemCacheManager.class);

	private SystemCacheManager() {

	}

	public static void init() {
		logger.info("data manager init started");
		onUpdate();
		execService.scheduleWithFixedDelay(new Runnable() {

			public void run() {
				onUpdate();
			}
		}, 3, 1, TimeUnit.SECONDS);
		logger.info("data manager init finished");
	}

	public static boolean onUpdate() {
		logger.debug("trying to load data");
		try {
			if (!lock_run) {
				lock_run = true;
				boolean init = ServerEnvLoader.initServer();
				lock_run = false;
				return init;
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			lock_run = false;
		}
		return false;
	}

}