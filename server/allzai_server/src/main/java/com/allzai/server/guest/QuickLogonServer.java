package com.allzai.server.guest;

import java.util.Map;

import com.allzai.bean.LoginHistory;
import com.allzai.bean.MobileDeviceBean;
import com.allzai.dao.AutoBeanDao;
import com.allzai.dao.decive.DeviceUserDao;
import com.allzai.dao.guest.QuickLogonDao;
import com.allzai.dao.notice.GameNoticeDao;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.des3.ThreeDESUtil;
import com.allzai.exception.DaoException;
import com.allzai.form.guest.QuickLogonForm;
import com.allzai.util.Constants;
import com.allzai.util.JsonUtil;
import com.allzai.util.StringUtil;
import com.restfb.json.JsonObject;

public class QuickLogonServer {

	private static final QuickLogonServer quickLogonServer = new QuickLogonServer();

	public static QuickLogonServer getInstance() {
		return quickLogonServer;
	}

	public JsonObject doQuickLogon(QuickLogonForm form) throws Exception {
		
		JsonObject json = new JsonObject();

		if (form == null || form.getImei() == null || form.getPackageName() == null) {
			/**
			 * Fx0103:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Fx0103");
			return json;
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
				return json;
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
					return json;
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
			return json;
		}

		// 添加交互字段
		userMap.put("tk", ThreeDESUtil.Encode(userMap.get("id") + "_" + form.getImei()+ "_1", Constants.index_tk_deocde));
		/**
		 * Fx0100:登录成功
		 */
		json.put("result", Boolean.TRUE);
		json.put("code", "Fx0100");
		json.put("user", JsonUtil.formatMapToJson(userMap));
		return json;
	}

}
