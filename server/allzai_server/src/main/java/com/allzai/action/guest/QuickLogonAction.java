package com.yeahmobi.gamelala.action.guest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yeahmobi.gamelala.action.BaseActionSupport;
import com.yeahmobi.gamelala.form.guest.QuickLogonForm;
import com.yeahmobi.gamelala.server.guest.QuickLogonServer;
import com.yeahmobi.gamelala.util.Hosts;

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
		
		logger.info("guest login from imei : " + form.getImei() + ", login from ip : " + Hosts.getIpAddr(req));
		
		return QuickLogonServer.getInstance().doQuickLogon(form);
	}

	@Override
	public Class<QuickLogonForm> getFromBean() {
		return QuickLogonForm.class;
	}

}
