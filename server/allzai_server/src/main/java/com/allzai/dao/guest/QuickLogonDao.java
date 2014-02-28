package com.allzai.dao.guest;

import java.sql.Connection;

import com.allzai.system.DatabaseManager;
import com.allzai.bean.BaseBean;
import com.allzai.dao.BaseJdbcDaoSupport;

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
