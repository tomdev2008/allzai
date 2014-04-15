package com.allzai.dao.user;

import java.sql.Connection;
import java.util.List;

import com.allzai.bean.UserAppBean;
import com.allzai.dao.TransactionDaoSupport;
import com.allzai.dao.TransactionManager;

/**
 * 用户APP使用记录操作Dao
 */
public class UserAppUseHistoryMasterDao extends TransactionDaoSupport<UserAppBean> 
{
	/**
	 * 根据id修改用户APP的装卸记录
	 * 
	 * @param id 用户APP id
	 * @return
	 */
	public boolean updateBatchUserAppStatus(int deviceID, List<Integer> idList)
	{
		if(idList == null || idList.size() <= 0)
		{
			return true;
		}
		
		Object[] ids;
		
		String updateSql = "UPDATE device_app_info SET uninstallTime = SYSDATE(), STATUS = 1 WHERE deviceId = "+deviceID+"  and appPackName in(";
		
		ids = idList.toArray();
		
		StringBuilder sb = new StringBuilder(75);
		for (@SuppressWarnings("unused") Object id : ids) 
		{
			sb.append("?, ");
		}
		updateSql += sb.substring(0, sb.lastIndexOf(",")) + ')';
		
		return super.doChanage(updateSql, ids);
	}

	/**
	 * 更新用户一批APP信息
	 * 
	 * @param params
	 * @return
	 */
	public boolean updateBatchUserAppInfo(int deviceID, List<UserAppBean> appBeanList)
	{
		if(appBeanList == null || appBeanList.size() <=0 )
		{
			return true;
		}
		
		int size = appBeanList.size();
		Object[][] params = new Object[size][4];
		
		UserAppBean userApp;
		
		for (int i = 0; i < size; i++) 
		{
			userApp = appBeanList.get(i);
			params[i][0] = userApp.getAppPackName();
			params[i][1] = userApp.getAppName();
			params[i][2] = userApp.getAppVersion();
			params[i][3] = userApp.getAppPackName();
		}
		 
		String sql = "update device_app_info SET appPackName = ? , appName = ? , appVersion = ? , installTime = SYSDATE() WHERE deviceId = "+deviceID+" and appPackName = ? ";
		
		return super.doBatchChanage(sql, params);
	}
	
	/**
	 * 添加一批设备信息
	 * 
	 * @param params
	 * @return
	 */
	public boolean addBatchUserAppInfo(int deviceID, List<UserAppBean> appBeanList)
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
			
			params[i][0] = userApp.getDeviceId();
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
		return TransactionManager.getContainer().get();
	}

}
