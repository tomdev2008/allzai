package com.allzai.server.user;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.allzai.bean.UserBean;
import com.allzai.dao.ManulBeanDao;
import com.allzai.dao.TransactionManager;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.form.user.LoginUserForm;
import com.allzai.form.user.RegeistUserForm;
import com.allzai.util.Constants;
import com.allzai.util.StringUtil;
import com.restfb.json.JsonObject;

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

	/**
	 * 用户转化
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public String changeRole(LoginUserForm form) throws Exception {
		
		JsonObject json = new JsonObject();
		
		if(StringUtil.isEmpty(form.getAccount()) || StringUtil.isEmpty(form.getPassword()) || form.getUserId() <= 0) {
			/**
			 * Gx0502:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Gx0502");
			json.put("info", "lost account or password.");
			return json.toString();
		}
		
		try {
			Map<String, Object> map = UserSlaveDao.getInstance().queryUserInfoByUserId(form.getUserId());
			if(map == null || map.size() <= 0) {
				/**
				 * Gx0505:用户名不存在
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Gx0505");
				json.put("info", "user not exists.");
				return json.toString();
			}
			
		    Pattern regex = Pattern.compile(Constants.EMAIL_REGEX);  
		    Matcher matcher = regex.matcher(form.getAccount());  
		    if(!matcher.matches()) {
		    	/**
				 * Gx0506:邮箱格式不正确
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Gx0506");
				json.put("info", "Matcher EMAIL ERROR");
				return json.toString();
		    } 
			
			map = UserSlaveDao.getInstance().queryUserInfoByUserAccount(form.getAccount());
			if(map != null && map.size() > 0) {
				/**
				 * Gx0504:用户名不能重复
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Gx0504");
				json.put("info", "user has exists.");
				return json.toString();
			}
			map = null;
			
			if(UserSlaveDao.getInstance().changeRole(form.getAccount(), form.getPassword(), form.getUserId())) {
				/**
				 * Gx0500:修改正确
				 */
				json.put("result", Boolean.TRUE);
				json.put("code", "Gx0500");
				json.put("info", "OK");
				return json.toString();
			} else {
				/**
				 * Gx0503:修改失败
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Gx0503");
				json.put("info", "unknow");
				return json.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Gx0501:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Gx0501");
			json.put("info", "System Error");
			return json.toString();
		}
	}

}
