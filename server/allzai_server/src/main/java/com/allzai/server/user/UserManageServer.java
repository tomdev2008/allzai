package com.allzai.server.user;

import java.util.Map;

import com.allzai.bean.UserBean;
import com.allzai.dao.ManulBeanDao;
import com.allzai.dao.TransactionManager;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.des3.ThreeDESUtil;
import com.allzai.form.user.RegeistUserForm;
import com.allzai.util.Constants;

/**
 * 提供用户信息管理服务
 */
public class UserManageServer 
{
	private static final UserManageServer userService = new UserManageServer();

	public static UserManageServer getInstance() {
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

				//对用户密码进行加密处理
				user.setPassword(ThreeDESUtil.Encode(user.getPassword(), Constants.index_pw_deocde));
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
			e.printStackTrace();
			regisResult = e.toString();
			TransactionManager.rollback();
		}
		finally 
		{
			TransactionManager.close();
		}
		
		return regisResult;
	}

}
