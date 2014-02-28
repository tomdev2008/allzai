package com.yeahmobi.gamelala.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yeahmobi.gamelala.util.Constants;
import com.yeahmobi.gamelala.util.StringUtil;

/**
 * 登录历史实体类
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-17
 * @since   JDK 1.6
 */
public class LoginHistory extends BaseBean implements Comparable<LoginHistory> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3193960809060503045L;
	
	private int id;
	
	private int userId;
	
	private int deviceId;
	
	private String loginIp;
	
	private Timestamp loginTime;
	
	private Timestamp quitTime;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(LoginHistory o) 
	{
		// TODO Auto-generated method stub
		return o.loginTime.compareTo(this.loginTime);
	}


	/**
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) 
	{
		this.id = id;
	}


	/**
	 * @return the userId
	 */
	public int getUserId() 
	{
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}


	/**
	 * @return the loginIp
	 */
	public String getLoginIp() 
	{
		return loginIp;
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
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) 
	{
		this.loginIp = loginIp;
	}


	/**
	 * @return the loginTime
	 */
	public Timestamp getLoginTime() 
	{
		return loginTime;
	}


	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Timestamp loginTime) 
	{
		this.loginTime = loginTime;
	}


	/**
	 * @return the quitTime
	 */
	public Timestamp getQuitTime() 
	{
		return quitTime;
	}


	/**
	 * @param quitTime the quitTime to set
	 */
	public void setQuitTime(Timestamp quitTime) 
	{
		this.quitTime = quitTime;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "LoginHistory [id=" + id + ", userId=" + userId + ", deviceId="
				+ deviceId + ", loginIp=" + loginIp + ", loginTime="
				+ loginTime + ", quitTime=" + quitTime + "]";
	}


	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getSaveCondition()
	 */
	@Override
	public Object[] getSaveCondition() 
	{
		Object[] params = new Object[2];
		
		if(this.userId > 0)
		{
			List<Object> tmpArray = new ArrayList<Object>();
			StringBuilder sb = new StringBuilder(55);
			
			sb.append(" userId,");
			tmpArray.add(this.userId);
			
			if(this.deviceId > 0)
			{
				sb.append(" deviceId, ");
				tmpArray.add(this.deviceId);
			}
			
			if(!StringUtil.isEmpty(this.loginIp))
			{
				sb.append(" loginIp,");
				tmpArray.add(this.loginIp);
			}
			if(this.loginTime != null)
			{
				sb.append(" loginTime,");
				tmpArray.add(this.loginTime);
			}
			if(this.quitTime != null)
			{
				sb.append(" quitTime,");
				tmpArray.add(this.quitTime);
			}
			
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
		
		if(this.id > 0)
		{
			List<Object> tmpArray = new ArrayList<Object>();
			StringBuilder sb = new StringBuilder(55);
			
			if(this.userId > 0)
			{
				sb.append(" userId = ? ,");
				tmpArray.add(this.userId);
			}
			
			if(this.deviceId > 0)
			{
				sb.append(" deviceId = ? ,");
				tmpArray.add(this.deviceId);
			}
			
			if(!StringUtil.isEmpty(this.loginIp))
			{
				sb.append(" loginIp = ? ,");
				tmpArray.add(this.loginIp);
			}
			if(this.loginTime != null)
			{
				sb.append(" loginTime = ? ,");
				tmpArray.add(this.loginTime);
			}
			if(this.quitTime != null)
			{
				sb.append(" quitTime = ? ,");
				tmpArray.add(this.quitTime);
			}
			
			if(tmpArray.size() > 0)
			{
				tmpArray.add(this.id);
				params[0] = "UPDATE " + this.getTableName() + " SET " + sb.substring(0, sb.lastIndexOf(",")) + " WHERE id = ? ";
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
		return Constants.LOGIN_LOG_TABLE_NAME;
	}
	

}
