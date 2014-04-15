package com.allzai.server.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.allzai.bean.MobileDeviceBean;
import com.allzai.bean.UserAppBean;
import com.allzai.dao.TransactionManager;
import com.allzai.dao.decive.DeviceUserDao;
import com.allzai.dao.user.UserAppUseHistoryMasterDao;
import com.allzai.dao.user.UserManageMasterDao;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.exception.DaoException;
import com.allzai.form.device.AppUseHistoryForm;
import com.allzai.util.Constants;
import com.allzai.util.StringUtil;
import com.restfb.json.JsonObject;

/**
 * 用户APP装卸历史服务
 */
public class AppUseHistoryServer 
{
	private static final AppUseHistoryServer appHisServer = new AppUseHistoryServer();
	
	public static AppUseHistoryServer getInstance() {
		return AppUseHistoryServer.appHisServer;
	}
	
	/**
	 * 记录用户App信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws XMLStreamException 
	 */
	@SuppressWarnings("unchecked")
	public JsonObject recordUserAppInfo(AppUseHistoryForm form) throws Exception
	{
		JsonObject json = new JsonObject();
		
		boolean reslut = false;
		try 
		{
			//解析json为列表Bean
			List<UserAppBean> newAppList = form.parseJsonForListBean();
			
			//没有任何APP信息，直接成功。
			if(newAppList == null || newAppList.size() <= 0)
			{
				/**
				 * Bx0000:上报成功
				 */
				json.put("result", Boolean.TRUE);
				json.put("code", "Bx0000");
				return json;
			}
			else
			{
				MobileDeviceBean mdb = DeviceUserDao.getInstance().getDeviceUser(form.getImei());
				if(mdb != null)
				{
					UserAppBean userApp = form.convertBaseBean();
					//设置设备ID
					userApp.setDeviceId(mdb.getId());
					
					//查出用户设备上所有已安装的APP列表
					List<UserAppBean> oldAppList = UserSlaveDao.getInstance().queryUserInstallAppList(userApp.getDeviceId(), Constants.ZERO_NUMBER, false);
					
					//第一次， 直接录入
					if(oldAppList == null || oldAppList.size() <= 0) {
						UserManageMasterDao.getInstance().addBatchUserAppInfo(userApp.getDeviceId(), newAppList);
						reslut = true;
					}
					else
					{
						//分析用户APP的装卸情况
						Object[] params = null;
						
						//0：增量，1：全量 
						if(Constants.ONE_STR.equals(form.getType())) {
							params = analysisUserFullAppInfo(userApp.getDeviceId(), convertMap(newAppList), convertMap(oldAppList));
						} else {
							params = analysisUserIncreAppInfo(userApp.getDeviceId(), convertMap(newAppList), convertMap(oldAppList));
						}
						
						try 
						{
							TransactionManager.startTransaction(); 
							
							UserAppUseHistoryMasterDao uauhm = new UserAppUseHistoryMasterDao();
							
							//更改用户安装的APP状态
							uauhm.updateBatchUserAppStatus(mdb.getId(), (List<Integer>) params[0]);
							
							//更新用户安装的APP信息
							uauhm.updateBatchUserAppInfo(mdb.getId(), (List<UserAppBean>)params[1]);
							
							//添加用户新的APP信息
							uauhm.addBatchUserAppInfo(mdb.getId(), (List<UserAppBean>)params[2]);

							TransactionManager.commit();
							reslut = true;
						} 
						catch (Exception e) 
						{
							TransactionManager.rollback();
							throw new DaoException(e);
						}
						finally 
						{
							TransactionManager.close();
						}
					}
				} else {
					/**
					 * Bx0003:设备不存在
					 */
					json.put("result", Boolean.FALSE);
					json.put("code", "Bx0003");
					return json;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
			/**
			 * Bx0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Bx0001");
			return json;
		}
		
		/**
		 * Bx0000:上报成功
		 * Bx0002:处理失败
		 */
		if(reslut) {
			json.put("result", Boolean.TRUE);
			json.put("code", "Bx0000");
		} else {
			json.put("result", Boolean.FALSE);
			json.put("code", "Bx0002");
		}
		return json;
	}
	
	/**
	 * List数据转化为Map对象方式存储<p>
	 * 
	 * @param listBean
	 * @return
	 */
	private Map<String, UserAppBean> convertMap(List<UserAppBean> listBean)
	{
		Map<String, UserAppBean> userAppMap = new HashMap<String, UserAppBean>(listBean.size());
		
		for (UserAppBean userApp : listBean) 
		{
			userAppMap.put(userApp.getAppPackName(), userApp);
		}
		
		return userAppMap;
	}
	

	/**
	 * 分析用户全量APP信息<p>
	 * 
	 * @param userId
	 * @param deviceId
	 * @param newUserAppMap
	 * @param oldUserAppMap
	 * @return
	 */
	private Object[] analysisUserFullAppInfo(int deviceId, Map<String, UserAppBean> newUserAppMap, Map<String, UserAppBean> oldUserAppMap)
	{
		Object[] result = new Object[3];
		//卸载的列表 id
		List<String> uninstallIds = new ArrayList<String>();
		
		//需要更新的 Object[][] params
		List<UserAppBean> renewList = new ArrayList<UserAppBean>();
		
		//Object[][] params
		//新加的
		List<UserAppBean> addList = new ArrayList<UserAppBean>();
		
		UserAppBean oldUserApp;
		UserAppBean newUserApp;
		
		Entry<String, UserAppBean> entry;
		
		for (Iterator<Entry<String, UserAppBean>> it = newUserAppMap.entrySet().iterator(); it.hasNext();) 
		{
			entry = it.next();
			newUserApp = entry.getValue();
			
			oldUserApp = oldUserAppMap.get(entry.getKey());
			
			if(oldUserApp == null)
			{
				newUserApp.setDeviceId(deviceId);
				
				addList.add(newUserApp);
			}
			else
			{
				//版本不一样，则更新
				if(!newUserApp.getAppVersion().equals(oldUserApp.getAppVersion()))
				{
					newUserApp.setAppPackName(oldUserApp.getAppPackName());
					
					renewList.add(newUserApp);
				}
			}
			
		}

		for (Iterator<Entry<String, UserAppBean>> it = oldUserAppMap.entrySet().iterator(); it.hasNext();) 
		{
			entry = it.next();
			oldUserApp = entry.getValue();
			
			newUserApp = newUserAppMap.get(entry.getKey());
			
			if(newUserApp == null)
			{
				//记录要卸载的APPID
				uninstallIds.add(oldUserApp.getAppPackName());
			}
			
		}
		
		result[0] = uninstallIds;
		result[1] = renewList;
		result[2] = addList;
				
		return result;
	}
	
	/**
	 * 分析用户增量APP信息<p>
	 * 
	 * @param userId
	 * @param deviceId
	 * @param newUserAppMap
	 * @param oldUserAppMap
	 * @return
	 */
	private Object[] analysisUserIncreAppInfo(int deviceId, Map<String, UserAppBean> newUserAppMap, Map<String, UserAppBean> oldUserAppMap)
	{
		Object[] result = new Object[3];
		//卸载的列表 id
		List<String> uninstallIds = new ArrayList<String>();
		
		//需要更新的 Object[][] params
		List<UserAppBean> renewList = new ArrayList<UserAppBean>();
		
		//Object[][] params
		//新加的
		List<UserAppBean> addList = new ArrayList<UserAppBean>();
		
		UserAppBean oldUserApp;
		UserAppBean newUserApp;
		
		Entry<String, UserAppBean> entry;
		
		for (Iterator<Entry<String, UserAppBean>> it = newUserAppMap.entrySet().iterator(); it.hasNext();) 
		{
			entry = it.next();
			newUserApp = entry.getValue();
			
			oldUserApp = oldUserAppMap.get(entry.getKey());
			
			if(oldUserApp == null)
			{
				newUserApp.setDeviceId(deviceId);
				
				//新安装的App
				addList.add(newUserApp);
			}
			else
			{
				if(StringUtil.isEmpty(newUserApp.getUninstallTime()))
				{
					//版本不一样，则更新
					if(!newUserApp.getAppVersion().equals(oldUserApp.getAppVersion()))
					{
						newUserApp.setAppPackName(oldUserApp.getAppPackName());
						
						//版本更新的App
						renewList.add(newUserApp);
					}
				}
				else
				{
					//卸载的App的
					uninstallIds.add(oldUserApp.getAppPackName());
				}
				
			}
			
		}
		
		result[0] = uninstallIds;
		result[1] = renewList;
		result[2] = addList;
				
		return result;
	}
}
