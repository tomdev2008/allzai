package com.allzai.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.user.LoginUserForm;
import com.allzai.server.user.LoginUserServer;

public class UserLoginAction extends BaseActionSupport {

	private static final Logger logger = Logger.getLogger(UserLoginAction.class);
	
	/**
	 * 用户登录
	 */
	private static final long serialVersionUID = -2437294960165906142L;

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		LoginUserForm form = (LoginUserForm) obj;
		
		logger.info("user login from imei : " + form.getImei() + ", login from ip : " + form.getIp());
		
		return LoginUserServer.getInstance().doLoginUser(form);
	}

	@Override
	public Class<LoginUserForm> getFromBean() {
		return LoginUserForm.class;
	}

}
