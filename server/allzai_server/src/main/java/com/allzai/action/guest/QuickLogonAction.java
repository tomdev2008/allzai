package com.allzai.action.guest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.guest.QuickLogonForm;
import com.allzai.server.guest.QuickLogonServer;

public class QuickLogonAction extends BaseActionSupport {
	
	private static final Logger logger = Logger.getLogger(QuickLogonAction.class);

	/**
	 * 快速登录
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		QuickLogonForm form = (QuickLogonForm) obj;
		
		logger.info("guest login from imei : " + form.getImei() + ", login from ip : " + form.getIp());
		
		return QuickLogonServer.getInstance().doQuickLogon(form);
	}

	@Override
	public Class<QuickLogonForm> getFromBean() {
		return QuickLogonForm.class;
	}

}
