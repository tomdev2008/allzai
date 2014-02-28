package com.allzai.action.device;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.device.DeviceUserForm;
import com.allzai.server.device.DeviceUserServer;

public class DeviceReportAction extends BaseActionSupport {

	private static final long serialVersionUID = 5999635462092011193L;

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		DeviceUserForm form = (DeviceUserForm) obj;
		
		return DeviceUserServer.getInstance().doDeviceUser(form);
		
	}

	@Override
	public Class<DeviceUserForm> getFromBean() {
		return DeviceUserForm.class;
	}

}
