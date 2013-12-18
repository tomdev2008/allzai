package com.allzai.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.DataSources;

/**
 * DB连接管理类<p>
 * 提供Master,Slave数据的，连接，关闭，以及连接池销毁
 */
public class DatabaseManager 
{
	
	/** slave(只读)安全数据源 */
	private static DataSource slave = null;
	
	/** master(读写)数据源 */
	private static DataSource master = null;
	
	private static final String DB_POOL_FILE_NAME = "/c3p0";
	
	private static Logger logger = Logger.getLogger(DatabaseManager.class);
	
	private static JdbcPropertiesUtil jpu = new JdbcPropertiesUtil("/config");

	private DatabaseManager()
	{
		
	}

	public static boolean init() throws Exception
	{
		boolean initResult = false;

		logger.info("Conn manager init started");

		if(DbUtils.loadDriver(jpu.getMasterDriverClassName()))
		{
			// 只读数据库连接
			DataSource slavePool = DataSources.unpooledDataSource(jpu.getSlaveUrl(), jpu.getSlaveUsername(), jpu.getSlavePassword());
			
			//读写数据连接
			DataSource masterPool = DataSources.unpooledDataSource(jpu.getMasterUrl(), jpu.getMasterUsername(), jpu.getMasterPassword());
			
			Properties c3p0Config = initC3p0Properties();
			if(c3p0Config == null)
			{
				slave = DataSources.pooledDataSource(slavePool);
				master = DataSources.pooledDataSource(masterPool);
			}
			else
			{
				slave = DataSources.pooledDataSource(slavePool, c3p0Config);
				master = DataSources.pooledDataSource(masterPool, c3p0Config);
			}
			logger.info("Conn manager init finished");
			initResult = true;
		}

		return initResult;
	}

	public static Connection getMasterConn() 
	{
		
		try 
		{
			return master.getConnection();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			logger.error("获取master连接失败,详细信息如下: ", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static Connection getSlaveConn() 
	{
		try 
		{
			return slave.getConnection();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			logger.error("获取slave连接失败,详细信息如下: ", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * 销毁DB连接池
	 */
	public static void destroyDBPool()
	{
		try 
		{
			DataSources.destroy(slave);
		} 
		catch (SQLException e) 
		{
			logger.error(e);
		}

		try
		{
			DataSources.destroy(master);
		}
		catch (SQLException e) 
		{
			logger.error(e);
		}
		
	}
	
	/**
	 * 初始化C3PO池配置
	 * 
	 * @return
	 */
	private static Properties initC3p0Properties()
	{
		
		JdbcPropertiesUtil c3p0Config = null;
		
		try 
		{
			c3p0Config = new JdbcPropertiesUtil(DB_POOL_FILE_NAME);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
        
		return (c3p0Config == null ? null : c3p0Config.getProperties());
	}
}
