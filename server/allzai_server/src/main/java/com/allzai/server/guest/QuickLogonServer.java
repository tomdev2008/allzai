package com.yeahmobi.gamelala.server.guest;

import java.util.Map;

import com.restfb.json.JsonObject;
import com.yeahmobi.gamelala.bean.LoginHistory;
import com.yeahmobi.gamelala.bean.MobileDeviceBean;
import com.yeahmobi.gamelala.dao.AutoBeanDao;
import com.yeahmobi.gamelala.dao.decive.DeviceUserDao;
import com.yeahmobi.gamelala.dao.guest.QuickLogonDao;
import com.yeahmobi.gamelala.dao.notice.GameNoticeDao;
import com.yeahmobi.gamelala.dao.user.UserSlaveDao;
import com.yeahmobi.gamelala.des3.ThreeDESUtil;
import com.yeahmobi.gamelala.exception.DaoException;
import com.yeahmobi.gamelala.form.guest.QuickLogonForm;
import com.yeahmobi.gamelala.util.Constants;
import com.yeahmobi.gamelala.util.JsonUtil;
import com.yeahmobi.gamelala.util.StringUtil;

public class QuickLogonServer {

	private static final QuickLogonServer quickLogonServer = new QuickLogonServer();

	public static QuickLogonServer getInstance() {
		return quickLogonServer;
	}

	public String doQuickLogon(QuickLogonForm form) throws Exception {
		
		JsonObject json = new JsonObject();

		if (form == null || form.getImei() == null || form.getPackageName() == null) {
			/**
			 * Fx0103:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Fx0103");
			json.put("info", "Invalid request.");
			return json.toString();
		}

		Map<String, Object> userMap = null;
		try {
			Map<String, Object> map = null;
			
			userMap = UserSlaveDao.getInstance().queryGuestUserInfoByImei(form.getImei());
			if (userMap == null || userMap.size() <= 0) {
				
				// 若临时帐户不存在, 则直接创建再返回
				int userID = QuickLogonDao.getInstance().doRegisterGuest(form.getImei());
				if (userID > 0) {
					userMap = UserSlaveDao.getInstance().queryUserInfoByUserId(userID);
					
					//添加用户与设备的关系
					MobileDeviceBean device = DeviceUserDao.getInstance().getDeviceUser(form.getImei());
					if(device != null) {
						
						map = DeviceUserDao.getInstance().getDeviceUserLoginHistory(device.getId(), userID);
						if (map == null || StringUtil.toInt(String.valueOf(map.get("sum")), 1) == 0) {
							
							LoginHistory history = new LoginHistory();
							history.setDeviceId(device.getId());
							history.setUserId(userID);
							history.setLoginIp(form.getIp());
							AutoBeanDao.getInstance().addSingleBean(history);
						}
					}
				}
			}
			
			if (userMap == null || userMap.size() <= 0) {
				/**
				 * Fx0102:禁止登录
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Fx0102");
				json.put("info", "login forbbin");
				return json.toString();
			}
			
			//添加用户与游戏的关系
			try {
				map = GameNoticeDao.getInstance().getGameInfo(form.getPackageName());
				if(map == null || map.size() <= 0) {
					/**
					 * Fx0102:应用不存在, 不允许登录
					 */
					json.put("result", Boolean.FALSE);
					json.put("code", "Fx0102");
					json.put("info", "login forbbin");
					return json.toString();
				} else {
					int gameId = Integer.parseInt(String.valueOf(map.get("id")));
					map = GameNoticeDao.getInstance().getGamePlayHistory(Integer.parseInt(String.valueOf(userMap.get("id"))), gameId);
					if(map == null || map.size() <= 0) {
						GameNoticeDao.getInstance().setGamePlayHistory(Integer.parseInt(String.valueOf(userMap.get("id"))), gameId);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			map = null;
		} catch (DaoException e) {
			e.printStackTrace();
			
			/**
			 * Fx0101:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Fx0101");
			json.put("info", "System Error");
			return json.toString();
		}

		// 添加交互字段
		userMap.put("tk", ThreeDESUtil.Encode(userMap.get("id") + "_" + form.getImei()+ "_1", Constants.index_deocde));
		/**
		 * Fx0100:登录成功
		 */
		json.put("result", Boolean.TRUE);
		json.put("code", "Fx0100");
		json.put("info", "OK");
		json.put("user", JsonUtil.formatMapToJson(userMap));
		return json.toString();
	}

}
