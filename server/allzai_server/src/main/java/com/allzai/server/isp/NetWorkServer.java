package com.allzai.server.isp;

import com.allzai.form.isp.NetWorkForm;
import com.allzai.isp.IPLocation;
import com.allzai.isp.IPSeeker;
import com.allzai.util.StringUtil;
import com.restfb.json.JsonObject;

public class NetWorkServer {

	private static final NetWorkServer netWorkServer = new NetWorkServer();

	public static NetWorkServer getInstance() {
		return netWorkServer;
	}

	public JsonObject doIspNetWork(NetWorkForm form) {
		
		JsonObject json = new JsonObject();

		if (StringUtil.isEmpty(form.getSip())) {
			form.setSip(form.getIp());
		}
		
		json.put("result", Boolean.TRUE);
		json.put("code", "Hx0000");
		
		JsonObject object = new JsonObject();
		IPLocation location = IPSeeker.getInstance().getIPLocation(form.getIp());
		object.put("ip", form.getIp());
		object.put("area", location.getArea());
		object.put("country", location.getCountry());
		json.put("ip", object);
		
		object = new JsonObject();
		location = IPSeeker.getInstance().getIPLocation(form.getSip());
		object.put("ip", form.getSip());
		object.put("area", location.getArea());
		object.put("country", location.getCountry());
		json.put("sip", object);
		
		return json;
	}

}
