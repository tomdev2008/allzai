package com.allzai.dao.gcm;

import java.sql.Connection;
import java.util.Map;

import com.allzai.bean.BaseBean;
import com.allzai.dao.BaseJdbcDaoSupport;
import com.allzai.system.DatabaseManager;

public class GcmReportDao extends BaseJdbcDaoSupport<BaseBean> {
	
	private static final GcmReportDao gcmReportDao = new GcmReportDao();

	public GcmReportDao() {
	}

	public static GcmReportDao getInstance() {
		return gcmReportDao;
	}

	/**
	 * 保存已经注册的GCM的ID信息
	 * @param deviceId
	 * @param regId
	 * @return
	 */
	public boolean doGcmReport(int deviceId, int gameId, String regId) 
	{
		String querySql = "INSERT INTO push_key(deviceId,gameId,gcmId) VALUES(?,?,?)";
		
		return super.doChanage(querySql, deviceId, gameId, regId);
	}

	/**
	 * 查询上报设备是否已经完成GCM注册
	 * @param deviceId
	 * @param regId
	 * @return
	 */
	public Map<String, Object> getGcmRegId(int deviceId, int gameId) 
	{
		String querySql = "SELECT count(*) as sum FROM push_key WHERE deviceId = ? AND gameId = ?";
		
		return super.doQueryMap(querySql, deviceId, gameId);
	}

	@Override
	public Connection getConnection() {
		return DatabaseManager.getMasterConn();
	}

}
