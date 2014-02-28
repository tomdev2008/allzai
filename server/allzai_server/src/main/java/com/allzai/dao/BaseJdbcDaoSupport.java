package com.allzai.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import com.allzai.util.StringUtil;

/**
 * 基本JDBC数据操作类，自主事务<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-6
 * @see     IBaseDao
 * @since   JDK 1.6
 */
public abstract class BaseJdbcDaoSupport<T> implements IBaseDao<T>
{
	
	private static final Logger logger = Logger.getLogger(BaseJdbcDaoSupport.class);

	
	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IBaseDao#doSave(java.lang.String, java.lang.Object[])
	 */
	public int doSave(String insertSql, Object ...params)
	{

		int primaryKey = 0;
		if(StringUtil.isEmpty(insertSql))
		{
			return primaryKey;
		}
		
		Connection conn = null;
		try 
		{
			conn = getConnection();
			if(conn == null)
			{
				primaryKey = -1;
			}
			else
			{
				QueryRunner qr = new QueryRunner();
				qr.update(conn, insertSql, params);
				
				Number tmpNum = ((Number) qr.query(conn, "SELECT LAST_INSERT_ID()", new ScalarHandler())); 
				
				primaryKey = tmpNum.intValue();
				primaryKey = primaryKey > 0 ? primaryKey : -1;
			}
			
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		finally 
		{
			DbUtils.closeQuietly(conn);
		}
		return primaryKey;
	
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IBaseDao#doChanage(java.lang.String, java.lang.Object[])
	 */
	public boolean doChanage(String sql, Object ...params) 
	{

		if(StringUtil.isEmpty(sql))
		{
			return false;
		}
		
		int rowsCount = 0;
		Connection conn = null;
		try 
		{
			conn = getConnection();
			
			QueryRunner qr = new QueryRunner();
			rowsCount = qr.update(conn, sql, params);
			
		} 
		catch (SQLException e) 
		{
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		finally
		{
			DbUtils.closeQuietly(conn);
		}
		return rowsCount > 0;
	
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IBaseDao#doBatchChanage(java.lang.String, java.lang.Object[][])
	 */
	public boolean doBatchChanage(String sql, Object[][] params)
	{

		if(StringUtil.isEmpty(sql))
		{
			return false;
		}
		
		int rowsCount = 0;
		Connection conn = null;
		try 
		{
			conn = getConnection();
			
			QueryRunner qr = new QueryRunner();
			int[] rows = qr.batch(conn, sql, params);
		    rowsCount = (rows == null ? 0 : rows.length);
			
		} 
		catch (SQLException e) 
		{
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		finally
		{
			DbUtils.closeQuietly(conn);
		}
		return rowsCount > 0;
	
	
	}
	
	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IBaseDao#doQueryBean(java.lang.String, java.lang.Object[])
	 */
	@SuppressWarnings("hiding")
	public <T> T doQueryBean(String querySql, Class<T> classz, Object... params)
	{
		T result = null;
		if(StringUtil.isEmpty(querySql) || null == classz)
		{
			return result;
		}
		Connection conn = null;
		try 
		{
			conn = getConnection();
			QueryRunner qr = new QueryRunner(); 
			result = qr.query(conn, querySql, new BeanHandler<T>(classz), params);
		} 
		catch (SQLException e) 
		{
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		finally 
		{
			DbUtils.closeQuietly(conn);
		}
		
		return result;
	
	}

	
	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IBaseDao#doQueryBeanList(java.lang.String, java.lang.Object[])
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> doQueryBeanList(String querySql, Class<T> classz, Object... params)
	{

		List<T> result = null;
		if(StringUtil.isEmpty(querySql) || null == classz)
		{
			return result;
		}
		Connection conn = null;
		try 
		{
			conn = getConnection();
			QueryRunner qr = new QueryRunner(); 
			result = qr.query(conn, querySql, new BeanListHandler<T>(classz), params);
		} 
		catch (SQLException e) 
		{
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		finally 
		{
			DbUtils.closeQuietly(conn);
		}
		
		return result;
	
	
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IBaseDao#doQueryMap(java.lang.String, java.lang.Object[])
	 */
	public Map<String, Object> doQueryMap(String querySql, Object... params)
    {

		Map<String, Object> result = null;
		if(StringUtil.isEmpty(querySql))
		{
			return result;
		}
		Connection conn = null;
		try 
		{
			conn = getConnection();
			QueryRunner qr = new QueryRunner(); 
			result = qr.query(conn, querySql, new MapHandler(), params);
		} 
		catch (SQLException e) 
		{
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		finally 
		{
			DbUtils.closeQuietly(conn);
		}
		
		return result;
	
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IBaseDao#doQueryMapList(java.lang.String, java.lang.Object[])
	 */
	public List<Map<String, Object>> doQueryMapList(String querySql,
			Object... params) 
	{

		List<Map<String, Object>> result = null;
		if(StringUtil.isEmpty(querySql))
		{
			return result;
		}
		Connection conn = null;
		try 
		{
			conn = getConnection();
			QueryRunner qr = new QueryRunner(); 
			result = qr.query(conn, querySql, new MapListHandler(), params);
		} 
		catch (SQLException e) 
		{
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		finally 
		{
			DbUtils.closeQuietly(conn);
		}
		
		return result;
	}


}
