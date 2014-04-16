package com.allzai.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.user.LoginUserForm;
import com.allzai.server.user.LoginUserServer;
import com.restfb.json.JsonObject;

public class UserLoginAction extends BaseActionSupport {

	private static final long serialVersionUID = -2437294960165906142L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		LoginUserForm form = (LoginUserForm) obj;
		
		return LoginUserServer.getInstance().doLoginUser(form);
	}

	@Override
	public Class<LoginUserForm> getFromBean() {
		return LoginUserForm.class;
	}

}
