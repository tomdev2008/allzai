package com.allzai.action.isp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.isp.NetWorkForm;
import com.allzai.server.isp.NetWorkServer;
import com.restfb.json.JsonObject;

public class NetWorkAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		NetWorkForm form = (NetWorkForm) obj;
		
		return NetWorkServer.getInstance().doIspNetWork(form);
	}

	@Override
	public Class<NetWorkForm> getFromBean() {
		return NetWorkForm.class;
	}

}
