package com.allzai.dao.guest;

import java.sql.Connection;

import com.allzai.bean.BaseBean;
import com.allzai.dao.BaseJdbcDaoSupport;
import com.allzai.system.DatabaseManager;

public class QuickLogonDao extends BaseJdbcDaoSupport<BaseBean> {
	
	private static QuickLogonDao quickLogonDao = new QuickLogonDao();
	
	public static QuickLogonDao getInstance() {
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

}
