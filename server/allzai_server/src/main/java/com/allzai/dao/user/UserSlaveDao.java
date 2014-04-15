package com.allzai.dao.user;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.allzai.bean.UserAppBean;
import com.allzai.bean.UserBean;
import com.allzai.dao.BaseJdbcDaoSupport;
import com.allzai.exception.DaoException;
import com.allzai.system.DatabaseManager;

/**
 * 用户管理读取Dao
 */
public class UserSlaveDao extends BaseJdbcDaoSupport<Map<String, Object>> 
{
	private static final UserSlaveDao userDao = new UserSlaveDao();

	public static UserSlaveDao getInstance()
	{
		return UserSlaveDao.userDao;
	}
	
	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.dao.AbsJdbcTemplateSupport#getConnection()
	 */
	public Connection getConnection()
	{
		return DatabaseManager.getSlaveConn();
	}
	
	/**
	 * 获根据用户名密码获取用户
	 * 
	 * @param name
	 * @param pass
	 * @return
	 */
	public Map<String, Object> doLoginUser(String name, String pass) 
	{
		String querySql = "select * from user_info where account = ? and password = ? and role = 0";
		return super.doQueryMap(querySql, name, pass);
	}
	

	/**
	 * 根据ID查询用户的全部信息
	 * 
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public Map<String, Object> queryUserInfoByUserId(int userId) throws DaoException
	{
		String querySql = "SELECT * FROM user_info WHERE id = ?";
		return super.doQueryMap(querySql, userId);
	}
	
	
	/**
	 * 根据账户查询用户的全部信息
	 * 
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public Map<String, Object> queryUserInfoByUserAccount(String userAccount) throws DaoException
	{
		String querySql = "SELECT * FROM user_info WHERE account = ?";
		return super.doQueryMap(querySql, userAccount);
	}
	
	/**
	 * 根据账户查询用户的全部信息
	 * 
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public Map<String, Object> queryGuestUserInfoByImei(String imei) throws DaoException
	{
		String querySql = "SELECT * FROM user_info WHERE imei = ? AND role = 1";
		return super.doQueryMap(querySql, imei);
	}
	
	
	/**
	 * 根据ID查询用户的全部信息
	 * 
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public UserBean queryUserByUserId(int userId) throws DaoException
	{
		String querySql = "SELECT * FROM user_info WHERE id = ?";
		return super.doQueryBean(querySql, UserBean.class, userId);
	}

	/**
	 * 获取当前游戏的开始试玩记录
	 * @param form
	 * @return
	 * @throws DaoException
	 */
	public Map<String, Object> queryGamePlayHistoryList(int  userId, int gameId) throws DaoException
	{
		String sql = "SELECT * FROM play_history WHERE userId = ? AND gameId = ? limit 1";
		
		return super.doQueryMap(sql, userId, gameId);
	}

	/**
	 * 查询用户安装的APP列表
	 * 
	 * @param userId
	 * @param deviceId
	 * @param status   
	 * @param isAll    true:忽略status
	 * @return
	 */
	public List<UserAppBean> queryUserInstallAppList(int deviceId, int status, boolean isAll)
	{
		String querySql = "SELECT * FROM device_app_info WHERE deviceId = ? ";
		
		if(!isAll)
		{
			querySql += " AND status = ?";
			return super.doQueryBeanList(querySql, UserAppBean.class, deviceId, status);
		}
		else
		{
			return super.doQueryBeanList(querySql, UserAppBean.class, deviceId);
		}
	}
	
	/**
	 * 临时用户的转化
	 * @param account
	 * @param password
	 * @param userId
	 * @return
	 */
	public boolean changeRole(String account, String password, int userId) {
		
		if(userId <= 0) {return false;}
		
		String updateSql = "UPDATE user_info SET account = ?, `password`=?, role=0 WHERE id = ? AND role = 1";
		
		return super.doChanage(updateSql, account, password, userId);
	}

}
