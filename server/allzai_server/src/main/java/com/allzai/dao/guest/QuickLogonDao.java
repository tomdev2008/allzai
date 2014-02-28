package com.yeahmobi.gamelala.dao.guest;

import java.sql.Connection;

import com.yeahmobi.gamelala.bean.BaseBean;
import com.yeahmobi.gamelala.dao.BaseJdbcDaoSupport;
import com.yeahmobi.gamelala.system.DatabaseManager;

public class QuickLogonDao extends BaseJdbcDaoSupport<BaseBean> {
	
private static QuickLogonDao quickLogonDao = new QuickLogonDao();
	
	public static QuickLogonDao getInstance() 
	{
		return quickLogonDao;
	}

	public int doRegisterGuest(String imei) {
		String sql = "INSERT INTO user_info(imei,password,role,nickName,createTime) VALUES(?,'',1,'guest',now())";
		return super.doSave(sql, imei);
	}

	public Connection getConnection() 
	{
		return DatabaseManager.getMasterConn();
	}

	public boolean switchUserHistory(int id, int swid) {
		String sql = "UPDATE credit_history SET userId = ? WHERE userId = ?";
		return super.doChanage(sql, new Object[]{id, swid});
	}

	public boolean switchShareHistory(int id, int swid) {
		String sql = "UPDATE share_history SET userId = ? WHERE userId = ?";
		return super.doChanage(sql, new Object[]{id, swid});
	}

}
