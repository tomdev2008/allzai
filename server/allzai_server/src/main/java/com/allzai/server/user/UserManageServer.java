package com.allzai.server.user;

import java.util.Map;

import org.apache.log4j.Logger;

import com.allzai.bean.UserBean;
import com.allzai.dao.ManulBeanDao;
import com.allzai.dao.TransactionManager;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.form.user.RegeistUserForm;
import com.allzai.util.Constants;

/**
 * 提供用户信息管理服务
 * 
 * @author Eric
 * @version hasoffer-0.0.1, 2013-9-11
 * @see     UserSlaveDao
 * @since JDK 1.6
 */
public class UserManageServer 
{
	private static final UserManageServer userService = new UserManageServer();

	private static final Logger logger = Logger.getLogger(UserManageServer.class);

	public static UserManageServer getInstance() 
	{
		return UserManageServer.userService;
	}
	
	/**
	 * 注册用户 
	 * 
	 * @param  form
	 * @return 0:success, -1:failed, 1:alreday, 2: Email address is invalid, other: exception message
	 * @throws DaoException
	 */
	public String registerUser(RegeistUserForm form) 
	{
		String regisResult = Constants.MINUS_ONE_STR;

		UserBean user = form.convertBaseBean();
		try 
		{
			Map<String, Object> userMap = UserSlaveDao.getInstance().queryUserInfoByUserAccount(user.getAccount());
			
			// 没有此帐号
			if (userMap == null || userMap.size() <= 0) 
			{
				// 开启事务
				TransactionManager.startTransaction();
				ManulBeanDao dao = new ManulBeanDao();

				// 添加用户信息
				int id = dao.getAddSingleBean(user);

				if (id > 0) {
					user.setId(id);
					TransactionManager.commit();
					regisResult = Constants.ZERO_STR;
				} else {
					TransactionManager.rollback();
				}
			} 
			else 
			{
				regisResult = Constants.ONE_STR;
			}
			userMap = null;
		}
		catch (Exception e)
		{
			regisResult = e.toString();
			logger.warn("Registered users fail, detailed information is as follows:", e);
			TransactionManager.rollback();
		}
		finally 
		{
			TransactionManager.close();
		}
		
		return regisResult;
	}

}
