package com.allzai.server.gcm;

import java.util.Map;

import com.allzai.bean.MobileDeviceBean;
import com.allzai.dao.decive.DeviceUserDao;
import com.allzai.dao.gcm.GcmReportDao;
import com.allzai.dao.notice.GameNoticeDao;
import com.allzai.form.gcm.GcmReportForm;
import com.restfb.json.JsonObject;

public class GcmReportServer {

	private static final GcmReportServer gcmReportServer = new GcmReportServer();

	public static GcmReportServer getInstance() {
		return gcmReportServer;
	}

	public JsonObject doGcmReport(GcmReportForm form) throws Exception {
		
		JsonObject json = new JsonObject();
		
		if(form  == null || form.getRegId() == null || form.getPackageName() == null) {
			/**
			 * Ix0002:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Ix0002");
			return json;
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
			device = null;
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Ix0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Ix0001");
			return json;
		}
		
		if(ok) {
			/**
			 * Ix0000:上报成功
			 */
			json.put("result", Boolean.TRUE);
			json.put("code", "Ix0000");
			return json;
		} else {
			/**
			 * Ix0003:上报失败
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Ix0003");
			return json;
		}
	}

}
