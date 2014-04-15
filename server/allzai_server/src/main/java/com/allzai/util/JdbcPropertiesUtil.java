package com.allzai.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置文件中的数据类
 */
public class JdbcPropertiesUtil 
{
	private Properties properties = new Properties();

	private static final String DEFAULT_MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	

	/**
	 * 定义一个制定配置文件名的构造函数
	 * 
	 * @param propertiesName 需要获取的配置文件名，不包括后缀
	 */
	public JdbcPropertiesUtil(String propertiesName) 
	{
		InputStream inputStream = null;
		try
		{
			inputStream = JdbcPropertiesUtil.class.getResourceAsStream(propertiesName + ".properties");
			properties.load(inputStream);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() 
	{
		return properties;
	}
	
	/**
	 * 获取master配置文件中数据库的加载class
	 * 
	 * @return 加载class的字符串
	 */
	public String getMasterDriverClassName() 
	{
		return properties.getProperty("db.master.driver", DEFAULT_MYSQL_DRIVER);
	}

	/**
	 * 获取配置文件中master数据库连接的url
	 * 
	 * @return url的字符串
	 */
	public String getMasterUrl() 
	{
		return properties.getProperty("db.master.path");
	}

	/**
	 * 获取配置文件中master数据库连接的用户名
	 * 
	 * @return 用户名的字符串
	 */
	public String getMasterUsername()
	{
		return properties.getProperty("db.master.username");
	}

	/**
	 * 获取配置文件中master数据库连接的密码
	 * 
	 * @return 密码的字符串
	 */
	public String getMasterPassword()
	{
		return properties.getProperty("db.master.password");
	}
	
	
	/**
	 * 获取配置文件中slave数据库的加载class
	 * 
	 * @return 加载class的字符串
	 */
	public String getSlaveDriverClassName() 
	{
		return properties.getProperty("db.slave.driver", DEFAULT_MYSQL_DRIVER);
	}

	/**
	 * 获取配置文件中slave数据库连接的url
	 * 
	 * @return url的字符串
	 */
	public String getSlaveUrl() 
	{
		return properties.getProperty("db.slave.path");
	}

	/**
	 * 获取配置文件中slave数据库连接的用户名
	 * 
	 * @return 用户名的字符串
	 */
	public String getSlaveUsername()
	{
		return properties.getProperty("db.slave.username");
	}

	/**
	 * 获取配置文件中slave数据库连接的密码
	 * 
	 * @return 密码的字符串
	 */
	public String getSlavePassword() 
	{
		return properties.getProperty("db.slave.password");
	}
}
