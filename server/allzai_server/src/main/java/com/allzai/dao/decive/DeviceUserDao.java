package com.yeahmobi.gamelala.dao.decive;

import java.sql.Connection;
import java.util.Map;

import com.yeahmobi.gamelala.bean.BaseBean;
import com.yeahmobi.gamelala.bean.MobileDeviceBean;
import com.yeahmobi.gamelala.dao.BaseJdbcDaoSupport;
import com.yeahmobi.gamelala.system.DatabaseManager;

/**
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-11-28
 * @since   JDK 1.6
 */
public class DeviceUserDao extends BaseJdbcDaoSupport<BaseBean> 
{
	
	private static DeviceUserDao deviceUserDao = new DeviceUserDao();
	
	public static DeviceUserDao getInstance() 
	{
		return deviceUserDao;
	}

	private DeviceUserDao()
	{
		
	}
	
	public Connection getConnection() 
	{
		return DatabaseManager.getSlaveConn();
	}
	
	public MobileDeviceBean getDeviceUser(String imei) 
	{
		String querySql = "select * from mobile_device_info where imei = ?";
		
		return super.doQueryBean(querySql, MobileDeviceBean.class, imei);
	}
	
	public Map<String, Object> getDeviceUserLoginHistory(int deviceId, int userId) 
	{
		String querySql = "SELECT count(*) as sum FROM login_history WHERE userId = ? AND deviceId = ?";
		
		return super.doQueryMap(querySql, userId, deviceId);
	}

	public MobileDeviceBean getUserRegeistDevice(int userId)
	{
		return super.doQueryBean("SELECT m.* FROM login_history l, mobile_device_info m WHERE l.deviceId = m.id AND l.userId = ? LIMIT 1", MobileDeviceBean.class, userId);
	}

	public Map<String, Object> queryDeviceAppCount(int deviceId) 
	{
		String querySql = "SELECT count(*) as sum FROM device_app_info WHERE deviceId = ?";
		
		return super.doQueryMap(querySql, deviceId);
	}

}
