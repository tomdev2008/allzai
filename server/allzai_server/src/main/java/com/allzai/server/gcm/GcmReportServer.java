package com.yeahmobi.gamelala.server.gcm;

import java.util.Map;

import com.restfb.json.JsonObject;
import com.yeahmobi.gamelala.bean.MobileDeviceBean;
import com.yeahmobi.gamelala.dao.decive.DeviceUserDao;
import com.yeahmobi.gamelala.dao.gcm.GcmReportDao;
import com.yeahmobi.gamelala.dao.notice.GameNoticeDao;
import com.yeahmobi.gamelala.form.gcm.GcmReportForm;

public class GcmReportServer {

	private static final GcmReportServer gcmReportServer = new GcmReportServer();

	public static GcmReportServer getInstance() {
		return gcmReportServer;
	}

	public String doGcmReport(GcmReportForm form) throws Exception {
		
		JsonObject json = new JsonObject();
		
		if(form  == null || form.getRegId() == null || form.getPackageName() == null) {
			/**
			 * Ix0002:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Ix0002");
			json.put("info", "lost regId");
			return json.toString();
		}
		
		boolean ok = false;
		try {
			MobileDeviceBean device = DeviceUserDao.getInstance().getDeviceUser(form.getImei());
			if(device != null) {
				Map<String, Object> map = GameNoticeDao.getInstance().getGameInfo(form.getPackageName());
				//检测游戏是否存在
				if(map != null && map.size() > 0) {
					int gameId = Integer.parseInt(String.valueOf(map.get("id")));
					
					map = GcmReportDao.getInstance().getGcmRegId(device.getId(), gameId);
					
					int count = 0;
					if(map != null && map.size() > 0) {
						count = Integer.parseInt(String.valueOf(map.get("sum")));
					}
					if(count <= 0) {
						ok = GcmReportDao.getInstance().doGcmReport(device.getId(), gameId, form.getRegId());
					} else {
						ok = true;
					}
				}
				map = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Ix0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Ix0001");
			json.put("info", "System Error");
			return json.toString();
		}
		
		if(ok) {
			/**
			 * Ix0000:上报成功
			 */
			json.put("result", Boolean.TRUE);
			json.put("code", "Ix0000");
			json.put("info", "OK");
			return json.toString();
		} else {
			/**
			 * Ix0003:上报失败
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Ix0003");
			json.put("info", "unknow");
			return json.toString();
		}
	}

}
