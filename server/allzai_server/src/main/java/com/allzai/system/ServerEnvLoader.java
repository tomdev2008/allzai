package com.allzai.system;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.allzai.exception.DaoException;
import com.allzai.server.SystemCacheServer;
import com.allzai.util.Constants;

/**
 * 装载服务器基础环境
 */
public class ServerEnvLoader  
{
	//一级缓存 begin
	public static ConcurrentHashMap<String, Map<String, Object>> configCacheMap = new ConcurrentHashMap<String, Map<String, Object>>();
	public static ConcurrentHashMap<String, Map<String, Object>> gameCacheMap  = new ConcurrentHashMap<String, Map<String, Object>> ();
	
	//ip黑白名单
	public static ConcurrentHashMap<String, Map<String, Object>> blackCacheMap = new ConcurrentHashMap<String, Map<String, Object>>();
	public static ConcurrentHashMap<String, Map<String, Object>> whiteCacheMap = new ConcurrentHashMap<String, Map<String, Object>>();
	
	/** 刷新缓存标识 */
	private static boolean triggerFull = true;
	
	/** 刷新缓存时，同步已经修改的DB数据 begin */
	private static Timestamp blackUpdateToken   = new Timestamp(0);
	private static Timestamp whiteUpdateToken  = new Timestamp(0);
	private static Timestamp configUpdateToken = new Timestamp(0);
	private static Timestamp gameUpdateToken  = new Timestamp(0);
	/** 刷新缓存时，同步已经修改的DB数据 end */
	
	private static final Logger logger = Logger.getLogger(ServerEnvLoader.class);
	
	/**
	 * 服务初始化
	 * 
	 * @return
	 */
	public static String initServer()
	{
		try 
		{
			if (triggerFull) 
			{
				blackUpdateToken.setTime(0);
				whiteUpdateToken.setTime(0);
				configUpdateToken.setTime(0);
				gameUpdateToken.setTime(0);
				
				clearRootCache();
				
				logger.info("full update");
				triggerFull = false;
			} 
			else 
			{
				logger.debug("incremental update");
			}
			
			try 
			{
				ServerEnvLoader.loadBlackData();
			}
			catch (Exception e) 
			{
				logger.error("加载用户ip黑名单数据失败,详细信息如下", e);throw e;
			}
			
			try 
			{
				ServerEnvLoader.loadWhiteData();
			}
			catch (Exception e) 
			{
				logger.error("加载用户ip白名单数据失败,详细信息如下", e);throw e;
			}
			
			try 
			{
				ServerEnvLoader.loadConfigData();
			}
			catch (Exception e) 
			{
				logger.error("加载配置缓存数据失败,详细信息如下", e);throw e;
			}

			try 
			{
				ServerEnvLoader.loadGameData();
			}
			catch (Exception e) 
			{
				logger.error("加载遊戲缓存数据失败,详细信息如下", e);throw e;
			}
			
			Thread.sleep(Constants.sleepTime);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("加载服务环境失败,详细信息如下:", e);
			return "error";
		} 
		
		logger.debug("load all data finished;");
		return null;
	}

	/**
	 * 清理一级缓存
	 */
	private static void clearRootCache()
	{
		configCacheMap.clear();
		blackCacheMap.clear();
		whiteCacheMap.clear();
		gameCacheMap.clear();
	}
	

	/**
	 * @param triggerFull the triggerFull to set
	 */
	public static void setTriggerFull(boolean triggerFull) 
	{
		ServerEnvLoader.triggerFull = triggerFull;
	}


	/***
	 * 加载ip黑名单缓存
	 */
	private static void loadBlackData() throws DaoException
	{
		logger.debug("刷新前    black缓存总数据量为: " + blackCacheMap.size() + ", black修改时间为: " + blackUpdateToken.toString());
		SystemCacheServer.getInstance().loadBlackData(blackUpdateToken, blackCacheMap);

		Timestamp updateTime;
		Map<String, Object> itemMap;
		Entry<String, Map<String, Object>> entry = null;
		
		for (Iterator<Entry<String, Map<String, Object>>> iterator = blackCacheMap.entrySet().iterator(); iterator.hasNext();) 
		{
			entry = iterator.next();
			itemMap = entry.getValue();
			
			updateTime =(Timestamp)itemMap.get("updateTime");
			String isDeleted = String.valueOf(itemMap.get("isDeleted"));
			
			if(Boolean.parseBoolean(isDeleted) || "1".equals(isDeleted)) {
				iterator.remove();
			}
			
			if (blackUpdateToken.compareTo(updateTime) < 0) {
				blackUpdateToken = updateTime;
			}
		}
		logger.debug("刷新后    black缓存总数据量为: " + blackCacheMap.size() + ", black修改时间为: " + blackUpdateToken.toString());
		
	}
	
	/***
	 * 加载ip白名单缓存
	 */
	private static void loadWhiteData() throws DaoException
	{
		logger.debug("刷新前    white缓存总数据量为: " + whiteCacheMap.size() + ", white修改时间为: " + whiteUpdateToken.toString());
		SystemCacheServer.getInstance().loadWhiteData(whiteUpdateToken, whiteCacheMap);

		Timestamp updateTime;
		Map<String, Object> itemMap;
		Entry<String, Map<String, Object>> entry = null;
		
		for (Iterator<Entry<String, Map<String, Object>>> iterator = whiteCacheMap.entrySet().iterator(); iterator.hasNext();) 
		{
			entry = iterator.next();
			itemMap = entry.getValue();
			
			updateTime =(Timestamp)itemMap.get("updateTime");
			String isDeleted = String.valueOf(itemMap.get("isDeleted"));
			
			if(Boolean.parseBoolean(isDeleted) || "1".equals(isDeleted)) {
				iterator.remove();
			}
			
			if (whiteUpdateToken.compareTo(updateTime) < 0) {
				whiteUpdateToken = updateTime;
			}
		}
		logger.debug("刷新后    white缓存总数据量为: " + whiteCacheMap.size() + ", white修改时间为: " + whiteUpdateToken.toString());
		
	}

	/**
	 * 加载系统配置缓存
	 * 
	 * @throws DaoException
	 */
	private static void loadConfigData() throws DaoException
	{
		logger.debug("刷新前    Config缓存总数据量为: " + configCacheMap.size() + ", config修改时间为: " + configUpdateToken.toString());
		SystemCacheServer.getInstance().loadConfigData(configUpdateToken, configCacheMap);

		Timestamp updateTime;
		Map<String, Object> itemMap;
		Entry<String, Map<String, Object>> entry = null;
		
		for (Iterator<Entry<String, Map<String, Object>>> iterator = configCacheMap.entrySet().iterator(); iterator.hasNext();) 
		{
			entry = iterator.next();
			itemMap = entry.getValue();
			
			updateTime =(Timestamp)itemMap.get("updateTime");
			
			if (configUpdateToken.compareTo(updateTime) < 0) 
			{
				configUpdateToken = updateTime;
			}
		}
		logger.debug("刷新后    config缓存总数据量为: " + configCacheMap.size() + ", config修改时间为: " + configUpdateToken.toString());
	}

	/**
	 * 加載遊戲應用列表
	 * @throws DaoException
	 */
	private static void loadGameData() throws DaoException {
		
		logger.debug("刷新前game缓存总数据量为: " + gameCacheMap.size() + ", game修改时间为: " + gameUpdateToken.toString());
		SystemCacheServer.getInstance().loadGameData(gameUpdateToken, gameCacheMap);

		Timestamp updateTime;
		Map<String, Object> itemMap;
		Entry<String, Map<String, Object>> entry = null;
		
		for (Iterator<Entry<String, Map<String, Object>>> iterator = gameCacheMap.entrySet().iterator(); iterator.hasNext();) 
		{
			entry = iterator.next();
			itemMap = entry.getValue();
			
			updateTime =(Timestamp)itemMap.get("updateTime");
			String isDeleted = String.valueOf(itemMap.get("isDeleted"));
			
			if(Boolean.parseBoolean(isDeleted) || "1".equals(isDeleted)) {
				iterator.remove();
			}
			
			if (gameUpdateToken.compareTo(updateTime) < 0) {
				gameUpdateToken = updateTime;
			}
		}
		logger.debug("刷新后game缓存总数据量为: " + gameCacheMap.size() + ", game修改时间为: " + gameUpdateToken.toString());
		
	}

}
