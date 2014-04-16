package com.allzai.action.guest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.guest.QuickLogonForm;
import com.allzai.server.guest.QuickLogonServer;
import com.restfb.json.JsonObject;

public class QuickLogonAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		QuickLogonForm form = (QuickLogonForm) obj;
		
		return QuickLogonServer.getInstance().doQuickLogon(form);
	}

	@Override
	public Class<QuickLogonForm> getFromBean() {
		return QuickLogonForm.class;
	}

}
