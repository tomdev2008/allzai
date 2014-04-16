package com.allzai.action.device;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.device.AppUseHistoryForm;
import com.allzai.server.device.AppUseHistoryServer;
import com.restfb.json.JsonObject;

public class InstallReportAction extends BaseActionSupport {

	private static final long serialVersionUID = -6130789374169942239L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception 
	{
		AppUseHistoryForm form = (AppUseHistoryForm)obj;
		
		return AppUseHistoryServer.getInstance().recordUserAppInfo(form);
	}

	@Override
	public Class<AppUseHistoryForm> getFromBean() {
		return AppUseHistoryForm.class;
	}

}
