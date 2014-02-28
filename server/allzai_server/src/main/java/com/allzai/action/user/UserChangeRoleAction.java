package com.yeahmobi.gamelala.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeahmobi.gamelala.action.BaseActionSupport;
import com.yeahmobi.gamelala.form.user.LoginUserForm;
import com.yeahmobi.gamelala.server.user.UserManageServer;

public class UserChangeRoleAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		LoginUserForm form = (LoginUserForm) obj;
		
		return UserManageServer.getInstance().changeRole(form);
	}

	@Override
	public Class<LoginUserForm> getFromBean() {
		return LoginUserForm.class;
	}

}
