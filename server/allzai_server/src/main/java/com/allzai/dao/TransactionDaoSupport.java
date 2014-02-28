package com.allzai.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import com.allzai.util.StringUtil;

/**
 * 支持JDBC事务操作<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-17
 * @see     ITransformDao
 * @since   JDK 1.6
 */
public abstract class TransactionDaoSupport<T> implements ITransformDao<T> 
{
	
	protected Connection conn;
	
	private static final Logger logger = Logger.getLogger(TransactionDaoSupport.class);
	
	public TransactionDaoSupport()
	{
		conn = getConnection();
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IMasterDao#doSave(java.lang.String, java.lang.Object[])
	 */
	public int doSave(String insertSql, Object ...params)
	{

		int primaryKey = 0;
		if(StringUtil.isEmpty(insertSql))
		{
			return primaryKey;
		}
		
		try 
		{
			if(conn == null)
			{
				primaryKey =  -1;
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
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		} 
		
		return primaryKey;
	
	}
	

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IMasterDao#doChanage(java.lang.String, java.lang.Object[])
	 */
	public boolean doChanage(String sql, Object ...params) 
	{

		if(StringUtil.isEmpty(sql))
		{
			return false;
		}
		
		int rowsCount = 0;
		try 
		{
			QueryRunner qr = new QueryRunner();
			rowsCount = qr.update(conn, sql, params);
			
		} 
		catch (SQLException e) 
		{
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return rowsCount > 0;
	
	
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.IMasterDao#doBatchChanage(java.lang.String, java.lang.Object[][])
	 */
	public boolean doBatchChanage(String sql, Object[][] params)
	{

		if(StringUtil.isEmpty(sql))
		{
			return false;
		}
		
		int rowsCount = 0;
		try 
		{
			QueryRunner qr = new QueryRunner();
			int[] rows = qr.batch(conn, sql, params);
		    rowsCount = (rows == null ? 0 : rows.length);
			
		} 
		catch (SQLException e) 
		{
			logger.error("执行SQL时发生未知异常，详细信息如下:", e);
			throw new RuntimeException(e.getMessage(), e);
		}
		return rowsCount > 0;
	
	}


}
