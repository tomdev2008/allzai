package com.yeahmobi.gamelala.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yeahmobi.gamelala.util.Constants;
import com.yeahmobi.gamelala.util.StringUtil;

/**
 * 用户APP信息实体
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-17
 * @since   JDK 1.6
 */
public class UserAppBean extends BaseBean implements Comparable<UserAppBean> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3909671936071788437L;
	
	private int deviceId;
	
	private int status;
	
	private String appPackName;
	
	private String appName;
	
	private String appVersion;
	
	/** Timestamp 类型 */
	private String installTime;
	
	/** Timestamp 类型 */
	private String uninstallTime;
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(UserAppBean o) 
	{
		// TODO Auto-generated method stub
		return Timestamp.valueOf(o.uninstallTime).compareTo(Timestamp.valueOf(this.uninstallTime));
	}

	/**
	 * @return the deviceId
	 */
	public int getDeviceId() 
	{
		return deviceId;
	}


	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(int deviceId) 
	{
		this.deviceId = deviceId;
	}


	/**
	 * @return the status
	 */
	public int getStatus() 
	{
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) 
	{
		this.status = status;
	}


	/**
	 * @return the appPackName
	 */
	public String getAppPackName() 
	{
		return appPackName;
	}


	/**
	 * @param appPackName the appPackName to set
	 */
	public void setAppPackName(String appPackName) 
	{
		this.appPackName = appPackName;
	}


	/**
	 * @return the appName
	 */
	public String getAppName() 
	{
		return appName;
	}


	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) 
	{
		this.appName = appName;
	}


	/**
	 * @return the appVersion
	 */
	public String getAppVersion() 
	{
		return appVersion;
	}


	/**
	 * @param appVersion the appVersion to set
	 */
	public void setAppVersion(String appVersion)
	{
		this.appVersion = appVersion;
	}


	/**
	 * Timestamp 类型 
	 * 
	 * @return the installTime
	 */
	public String getInstallTime() 
	{
		return installTime;
	}


	/**
	 * Timestamp 类型 
	 * 
	 * @param installTime the installTime to set
	 */
	public void setInstallTime(String installTime) 
	{
		this.installTime = installTime;
	}


	/**
	 * Timestamp 类型 
	 * 
	 * @return the uninstallTime
	 */
	public String getUninstallTime() 
	{
		return uninstallTime;
	}


	/**
	 * Timestamp 类型 
	 * 
	 * @param uninstallTime the uninstallTime to set
	 */
	public void setUninstallTime(String uninstallTime) 
	{
		this.uninstallTime = uninstallTime;
	}


	@Override
	public String toString() {
		return "UserAppBean [deviceId=" + deviceId + ", status=" + status
				+ ", appPackName=" + appPackName + ", appName=" + appName
				+ ", appVersion=" + appVersion + ", installTime=" + installTime
				+ ", uninstallTime=" + uninstallTime + "]";
	}


	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getSaveCondition()
	 */
	@Override
	public Object[] getSaveCondition() 
	{
		Object[] params = new Object[2];
		
		List<Object> tmpArray = new ArrayList<Object>();
        
		StringBuilder sb = new StringBuilder(55);
		
		if(this.deviceId > 0)
		{
			sb.append(" deviceId,");
			tmpArray.add(this.deviceId);
		}
		
		if(!StringUtil.isEmpty(this.appPackName))
		{
			sb.append(" appPackName,");
			tmpArray.add(this.appPackName);
		}
		
		if(!StringUtil.isEmpty(this.appName))
		{
			sb.append(" appName,");
			tmpArray.add(this.appName);
		}
		
		if(!StringUtil.isEmpty(this.appVersion))
		{
			sb.append(" appVersion,");
			tmpArray.add(this.appVersion);
		}
		
		if(this.installTime != null)
		{
			sb.append(" installTime,");
			tmpArray.add(Timestamp.valueOf(this.installTime));
		}
		
		if(this.uninstallTime != null)
		{
			sb.append(" uninstallTime,");
			tmpArray.add(Timestamp.valueOf(this.uninstallTime));
		}
		
		if(tmpArray.size() > 0 )
		{
			params[0] = "INSERT INTO " + this.getTableName() + " (" + sb.substring(0, sb.lastIndexOf(",")) + " ) VALUES " + super.builderValueParam(tmpArray);
			params[1] = tmpArray.toArray();
			
		}
		
		return params;
	
	}


	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getEditCondition()
	 */
	@Override
	public Object[] getEditCondition() 
	{
		Object[] params = new Object[2];
		
		if(!StringUtil.isEmpty(this.appPackName) )
		{
			List<Object> tmpArray = new ArrayList<Object>();
	        
			StringBuilder sb = new StringBuilder(55);
			
			if(this.deviceId > 0)
			{
				sb.append(" deviceId = ? ,");
				tmpArray.add(this.deviceId);
			}
			
			sb.append(" status = ? ,");
			tmpArray.add(this.status);
			
			if(!StringUtil.isEmpty(this.appPackName))
			{
				sb.append(" appPackName = ? ,");
				tmpArray.add(this.appPackName);
			}
			
			if(!StringUtil.isEmpty(this.appName))
			{
				sb.append(" appName = ? ,");
				tmpArray.add(this.appName);
			}
			
			if(!StringUtil.isEmpty(this.appVersion))
			{
				sb.append(" appVersion = ? ,");
				tmpArray.add(this.appVersion);
			}
			
			if(this.installTime != null)
			{
				sb.append(" installTime = ? ,");
				tmpArray.add(Timestamp.valueOf(this.installTime));
			}
			
			if(this.uninstallTime != null)
			{
				sb.append(" uninstallTime = ? ,");
				tmpArray.add(Timestamp.valueOf(this.uninstallTime));
			}

			if(tmpArray.size() > 0)
			{
				tmpArray.add(this.appPackName);
				params[0] = "UPDATE " + this.getTableName() + " SET " + sb.substring(0, sb.lastIndexOf(",")) + " WHERE deviceId = "+this.deviceId+" and  appPackName = ? ";
				params[1] = tmpArray.toArray();
			}
		}
		
		return params;
	
	}

	

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getTableName()
	 */
	@Override
	public String getTableName() 
	{
		// TODO Auto-generated method stub
		return Constants.USER_APP_TABLE_NAME;
	}
}
