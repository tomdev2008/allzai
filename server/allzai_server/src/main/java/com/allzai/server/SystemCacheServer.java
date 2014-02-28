package com.yeahmobi.gamelala.server;

import java.sql.Timestamp;
import java.util.Map;

import com.yeahmobi.gamelala.dao.SystemCacheSlaveDao;
import com.yeahmobi.gamelala.exception.DaoException;

/**
 * 提供系统基础信息的加载服务<p>
 * 比如：未删除广告,APP,礼物，系统配置信息缓存
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-7
 * @see     SystemCacheSlaveDao
 * @since   JDK 1.6
 */
public class SystemCacheServer 
{
	private static final SystemCacheServer sysCacgeService = new SystemCacheServer();

	public static SystemCacheServer getInstance()
	{
		return SystemCacheServer.sysCacgeService;
	}
	
	/**
	 * 加载系统配置数据
	 * 
	 * @param updateTimeToken
	 * @param configCacheMap
	 * @return
	 * @throws DaoException
	 */
	public void loadConfigData(Timestamp updateTimeToken, Map<String, Map<String, Object>> configCacheMap) throws DaoException
	{
		SystemCacheSlaveDao.getInstance().queryConfigData(updateTimeToken, configCacheMap);
	}
	

	/***
	 * 加载用户ip黑名单
	 * @param blackUpdateToken
	 * @param blackCacheMap
	 * @throws DaoException
	 */
	public void loadBlackData(Timestamp blackUpdateToken, Map<String, Map<String, Object>> blackCacheMap) throws DaoException {

		SystemCacheSlaveDao.getInstance().queryBlackData(blackUpdateToken, blackCacheMap);
		
	}
	
	/***
	 * 加载用户ip白名单
	 * @param whiteUpdateToken
	 * @param whiteCacheMap
	 * @throws DaoException
	 */
	public void loadWhiteData(Timestamp whiteUpdateToken, Map<String, Map<String, Object>> whiteCacheMap) throws DaoException {

		SystemCacheSlaveDao.getInstance().queryWhiteData(whiteUpdateToken, whiteCacheMap);
		
	}

}

