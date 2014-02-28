package com.allzai.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Map;

import com.allzai.exception.DaoException;
import com.allzai.system.DatabaseManager;
import com.allzai.util.JsonUtil;

/**
 * 系统缓存读取
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-7
 * @see     BaseJdbcDaoSupport
 * @since   JDK 1.6
 */
public class SystemCacheSlaveDao extends BaseJdbcDaoSupport<Object> 
{
	private static SystemCacheSlaveDao cacheDao = new SystemCacheSlaveDao();

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.AbsJdbcTemplateSupport#getConnection()
	 */
	public Connection getConnection()
	{
		return DatabaseManager.getSlaveConn();
	}

	public static SystemCacheSlaveDao getInstance()
	{
		return SystemCacheSlaveDao.cacheDao;
	}
	
	/**
	 * 加载或刷新系统配置数据
	 * 
	 * @param  updateTimeToken
	 * @param  configCacheMap
	 * @throws DaoException
	 */
	public void queryConfigData(Timestamp updateTimeToken, Map<String, Map<String, Object>> configCacheMap) throws DaoException
	{
		String querySql = "SELECT * FROM sys_config WHERE updateTime >= ?";
		
		this.queryMapList("key", querySql, updateTimeToken, configCacheMap);
	}
	
	/**
	 * 刷新已更新缓存<P>
	 * 
	 * @param  converField 转换字段一般为主键（需是唯一键）  List converTo Map
	 * @param  querySql    
	 * @param  updateTimeToken
	 * @param  cacheMap
	 * @throws DaoException
	 */
	private void queryMapList(String converField, String querySql, Timestamp updateTimeToken, Map<String, Map<String, Object>> cacheMap) throws DaoException
	{
		//刷新所有更新过的缓存数据
		cacheMap.putAll(JsonUtil.convertMap(converField, super.doQueryMapList(querySql, updateTimeToken)));
	}

	/***
	 * 加载用户ip黑名单
	 * @param blackUpdateToken
	 * @param blackCacheMap
	 * @throws DaoException
	 */
	public void queryBlackData(Timestamp blackUpdateToken, Map<String, Map<String, Object>> blackCacheMap) throws DaoException 
	{
		String querySql = "SELECT * FROM ip_black_list WHERE updateTime >= ?";
		
		this.queryMapList("ip", querySql, blackUpdateToken, blackCacheMap);
		
	}
	
	/***
	 * 加载用户ip白名单
	 * @param whiteUpdateToken
	 * @param whiteCacheMap
	 * @throws DaoException
	 */
	public void queryWhiteData(Timestamp whiteUpdateToken, Map<String, Map<String, Object>> whiteCacheMap) throws DaoException 
	{
		String querySql = "SELECT * FROM ip_white_list WHERE updateTime >= ?";
		
		this.queryMapList("ip", querySql, whiteUpdateToken, whiteCacheMap);
		
	}

}
