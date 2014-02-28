package com.allzai.server.user;

import java.util.Map;

import com.allzai.bean.LoginHistory;
import com.allzai.bean.MobileDeviceBean;
import com.allzai.dao.AutoBeanDao;
import com.allzai.dao.decive.DeviceUserDao;
import com.allzai.dao.notice.GameNoticeDao;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.des3.ThreeDESUtil;
import com.allzai.form.user.LoginUserForm;
import com.allzai.util.Constants;
import com.allzai.util.JsonUtil;
import com.allzai.util.StringUtil;
import com.restfb.json.JsonObject;

public class LoginUserServer {
	
	 private static final LoginUserServer loginUserServer = new LoginUserServer();
		
	public static LoginUserServer getInstance()
	{
		return loginUserServer;
	}
	
	public String doLoginUser(LoginUserForm form) throws Exception {
		
		JsonObject json = new JsonObject();
		
		try {
			if(form == null || form.getAccount() == null  || form.getPassword() == null || form.getPackageName() == null) {
				/**
				 * Fx0004:参数错误
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Fx0004");
				json.put("info", "Wrong username / password. Please re-enter.");
				return json.toString();
			}

			Map<String, Object> users =  UserSlaveDao.getInstance().doLoginUser(form.getAccount(), form.getPassword());
			
			if(users == null || users.size() <= 0) {
				/**
				 * Fx0002:参数错误
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Fx0002");
				json.put("info", "Wrong username / password. Please re-enter.");
				return json.toString();
			}
			
			Map<String, Object> map = null;
			int userId = Integer.parseInt(String.valueOf(users.get("id")));

			//添加用户与设备的关系
			MobileDeviceBean device = DeviceUserDao.getInstance().getDeviceUser(form.getImei());
			if(device != null) {
				try {
					map = DeviceUserDao.getInstance().getDeviceUserLoginHistory(device.getId(), userId);
					if (map == null || StringUtil.toInt(String.valueOf(map.get("sum")), 1) == 0) {
						
						LoginHistory history = new LoginHistory();
						history.setDeviceId(device.getId());
						history.setUserId(userId);
						history.setLoginIp(form.getIp());
						AutoBeanDao.getInstance().addSingleBean(history);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			device = null;
			
			//添加用户与游戏的关系
			try {
				map = GameNoticeDao.getInstance().getGameInfo(form.getPackageName());
				if(map == null || map.size() <= 0) {
					/**
					 * Fx0003:应用不存在, 不允许登录
					 */
					json.put("result", Boolean.FALSE);
					json.put("code", "Fx0003");
					json.put("info", "login forbbin");
					return json.toString();
				} else {
					int gameId = Integer.parseInt(String.valueOf(map.get("id")));
					map = GameNoticeDao.getInstance().getGamePlayHistory(userId, gameId);
					if(map == null || map.size() <= 0) {
						GameNoticeDao.getInstance().setGamePlayHistory(userId, gameId);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			map = null;
			
			//添加交互字段
			users.put("tk", ThreeDESUtil.Encode(userId + "_" + form.getImei() + "_0", Constants.index_tk_deocde));
			/**
			 * Fx0000:登录成功
			 */
			json.put("result", Boolean.TRUE);
			json.put("code", "Fx0000");
			json.put("info", "ok");
			json.put("user", JsonUtil.formatMapToJson(users));
			return json.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Fx0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Fx0001");
			json.put("info", "System Error");
			return json.toString();
		}
	}

}
