package com.allzai.action.gcm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.gcm.GcmReportForm;
import com.allzai.server.gcm.GcmReportServer;
import com.restfb.json.JsonObject;

public class GcmReportAction extends BaseActionSupport {

	/**
	 * 上报GCM的注册ID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		GcmReportForm form = (GcmReportForm) obj;
		
		return GcmReportServer.getInstance().doGcmReport(form);
	}

	@Override
	public Class<GcmReportForm> getFromBean() {
		return GcmReportForm.class;
	}

}
