package com.allzai.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.allzai.system.DatabaseManager;

/**
 * 事务管理
 */
public class TransactionManager 
{
	private static ThreadLocal<Connection> container = new ThreadLocal<Connection>();

	// 获取共享变量
	public static ThreadLocal<Connection> getContainer() 
	{
		return container;
	}

	/**
	 * 获取当前线程上的连接开启事务
	 * @throws DaoException 
	 */
	public static void startTransaction() 
	{
		// 首先获取当前线程的连接
		Connection conn = container.get();
		
		// 如果连接为空
		if (conn == null) 
		{
			//从连接池中获取连接
			conn = DatabaseManager.getMasterConn(); 
			
			// 将此连接放在当前线程上
			container.set(conn);
		}
		try 
		{
			// 开启事务
			conn.setAutoCommit(false);
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	
	/**
	 * 提交事务
	 */
	public static void commit() 
	{
		Connection conn = container.get();// 从当前线程上获取连接
		
		if (conn != null) 
		{
			try
			{
				conn.commit();
			} 
			catch (SQLException e) 
			{
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 回滚事务
	 */
	public static void rollback() 
	{
		// 检查当前线程是否存在连接
		Connection conn = container.get();
		if (conn != null) 
		{
			try 
			{
				conn.rollback();// 回滚事务
			} 
			catch (SQLException e) 
			{
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	/**
	 * 关闭连接
	 */
	public static void close() 
	{
		Connection conn = container.get();
		if (conn != null) 
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				throw new RuntimeException(e.getMessage(), e);
			} 
			finally
			{
				container.remove();// 从当前线程移除连接切记
			}
		}
	}

}
