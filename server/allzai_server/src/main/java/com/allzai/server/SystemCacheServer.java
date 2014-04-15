package com.allzai.server;

import java.sql.Timestamp;
import java.util.Map;

import com.allzai.dao.SystemCacheSlaveDao;
import com.allzai.exception.DaoException;

/**
 * 提供系统基础信息的加载服务<p>
 */
public class SystemCacheServer 
{
	private static final SystemCacheServer sysCacgeService = new SystemCacheServer();

	public static SystemCacheServer getInstance() {
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
	public void loadConfigData(Timestamp updateTimeToken, Map<String, Map<String, Object>> configCacheMap) throws DaoException {
		
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

	/**
	 * 加載遊戲應用列表
	 * @param gameUpdateToken
	 * @param gameCacheMap
	 * @throws DaoException
	 */
	public void loadGameData(Timestamp gameUpdateToken, Map<String, Map<String, Object>> gameCacheMap) throws DaoException {

		SystemCacheSlaveDao.getInstance().queryGameData(gameUpdateToken, gameCacheMap);
		
	}

}

