package com.yeahmobi.gamelala.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yeahmobi.gamelala.action.BaseActionSupport;
import com.yeahmobi.gamelala.form.user.LoginUserForm;
import com.yeahmobi.gamelala.server.user.LoginUserServer;
import com.yeahmobi.gamelala.util.Hosts;

public class LoginUserAction extends BaseActionSupport {

	private static final Logger logger = Logger.getLogger(LoginUserAction.class);
	
	/**
	 * 用户登录
	 */
	private static final long serialVersionUID = -2437294960165906142L;

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		LoginUserForm form = (LoginUserForm) obj;
		
		logger.info("user login from imei : " + form.getImei() + ", login from ip : " + Hosts.getIpAddr(req));
		
		return LoginUserServer.getInstance().doLoginUser(form);
	}

	@Override
	public Class<LoginUserForm> getFromBean() {
		return LoginUserForm.class;
	}

}
