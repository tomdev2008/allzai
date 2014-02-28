package com.allzai.dao.user;

import java.sql.Connection;
import java.util.List;

import com.allzai.bean.UserAppBean;
import com.allzai.dao.BaseJdbcDaoSupport;
import com.allzai.system.DatabaseManager;

/**
 * 用户管理操作dao<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-10-17
 * @since   JDK 1.6
 */
public class UserManageMasterDao extends BaseJdbcDaoSupport<UserAppBean> 
{
	private static final UserManageMasterDao userDao = new UserManageMasterDao();
	
	private UserManageMasterDao()
	{
		
	}
	
	public static UserManageMasterDao getInstance()
	{
		return UserManageMasterDao.userDao;
	}

	/**
	 * 添加一批设备信息
	 * 
	 * @param params
	 * @return
	 */
	public boolean addBatchUserAppInfo(int deviceId, List<UserAppBean> appBeanList)
	{
		if(appBeanList == null || appBeanList.size() <=0)
		{
			return true;
		}
		
		int size = appBeanList.size();
		Object[][] params = new Object[size][4];
		
		UserAppBean userApp;
		
		for (int i = 0; i < size; i++) 
		{
			userApp = appBeanList.get(i);
			
			params[i][0] = deviceId;
			params[i][1] = userApp.getAppPackName();
			params[i][2] = userApp.getAppName();
			params[i][3] = userApp.getAppVersion();
		}
		
		String sql = "INSERT INTO device_app_info (deviceId, appPackName, appName, appVersion) VALUES (?, ?, ?, ?) ";
		
		return super.doBatchChanage(sql, params);
	}
	
	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.ITransformDao#getConnection()
	 */  
	public Connection getConnection() 
	{
		// TODO Auto-generated method stub
		return DatabaseManager.getMasterConn();
	}

}
