package com.yeahmobi.gamelala.action.device;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeahmobi.gamelala.action.BaseActionSupport;
import com.yeahmobi.gamelala.form.device.DeviceUserForm;
import com.yeahmobi.gamelala.server.device.DeviceUserServer;
import com.yeahmobi.gamelala.util.Hosts;

public class DeviceUserAction extends BaseActionSupport {

	/**
	 * dhhuang
	 */
	private static final long serialVersionUID = 5999635462092011193L;

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		DeviceUserForm form = (DeviceUserForm) obj;
		form.setIp(Hosts.getIpAddr(req));
		
		return DeviceUserServer.getInstance().doDeviceUser(form);
		
	}

	@Override
	public Class<DeviceUserForm> getFromBean() {
		return DeviceUserForm.class;
	}

}
