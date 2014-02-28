package com.yeahmobi.gamelala.form.device;

import java.util.List;

import com.yeahmobi.gamelala.bean.UserAppBean;
import com.yeahmobi.gamelala.form.BasicForm;
import com.yeahmobi.gamelala.util.JsonUtil;
import com.yeahmobi.gamelala.util.StringUtil;

/**
 * 用户App装卸历史表单
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-10-11
 * @see     UserAppBean
 * @see     JsonUtil#getDTO(String, Class)
 * @since   JDK 1.6
 */
public class AppUseHistoryForm extends BasicForm 
{
	
	/** 类指纹 */
	private static final long serialVersionUID = 891154576751819307L;
	
	//手机设备识别码
	private String imei;

	//增量或者全量
	private String type;
	
	/** json格式 内含 appPackName, appName, appVersion, installTime*/
	private String allAppInfo; 
	

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.form.BaseForm#convertBaseBean()
	 */
	@Override
	public UserAppBean convertBaseBean() 
	{
		UserAppBean userApp = new UserAppBean();
		return userApp;
	}

	/**
	 * @return the imei
	 */
	public String getImei() 
	{
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) 
	{
		this.imei = imei;
	}

	
	
	/**
	 * @return the type
	 */
	public String getType() 
	{
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) 
	{
		this.type = type;
	}


	/**
	 * @return the allAppInfo
	 */
	public String getAllAppInfo() 
	{
		return allAppInfo;
	}


	/**
	 * @param allAppInfo the allAppInfo to set
	 */
	public void setAllAppInfo(String allAppInfo) 
	{
		this.allAppInfo = allAppInfo;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UserAppBean> parseJsonForListBean()
	{
		return StringUtil.isEmpty(this.allAppInfo) ? null : JsonUtil.getDTOList(this.allAppInfo, UserAppBean.class);
	}


	@Override
	public String toString() {
		return "AppUseHistoryForm [imei=" + imei + ", type=" + type
				+ ", allAppInfo=" + allAppInfo + "]";
	}
	

	/**
	public static void main(String[] args) 
	{
		try 
		{
			DatabaseManager.init();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUseHistoryForm form = new AppUseHistoryForm();
		form.setId(1);
		form.setImei("358197050659178");
		//1 REMOVE 2:UPDATE 3.0 A:ADD 
		String allAppInfo = "[" +
				"{\"appName\":\"AppName_2\",\"appPackName\":\"appPackName_2\", \"appVersion\":\"3.0\",\"installTime\":\"2013-10-17-10:03\"}," +
				"{\"appName\":\"AppName_B\",\"appPackName\":\"appPackName_B\", \"appVersion\":\"2.1\",\"installTime\":\"2013-10-17-10:03\", \"uninstallTime\":\"2014-10-14\"}," +
				"{\"appName\":\"AppName_1\",\"appPackName\":\"appPackName_1\",\"appVersion\":\"4.0\",\"installTime\":\"2014-10-17-10:03\"}" +
				"]";
		
		form.setAllAppInfo(allAppInfo);
		
		try 
		{
//			AppUseHistoryServer.getInstance().recordUserAppInfo(form);
			

			int userId = 2;
			System.out.println(DeviceUserDao.getInstance().getUserRegeistDevice(userId).toString());
			System.out.println(UserSlaveDao.getInstance().queryCPACountByUserId(userId).toString());
			
			MobileDeviceBean cpaDevice = new MobileDeviceBean();
			cpaDevice.setId(8);
			cpaDevice.setUsed(1);
			AutoBeanDao.getInstance().editSingleBean(cpaDevice);
		}
		catch (DaoException e) 
		{
			e.printStackTrace();
		}
		
		
	}
*/

}
