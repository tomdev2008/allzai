package com.allzai.server.guest;

import java.util.Map;

import com.allzai.dao.guest.QuickLogonDao;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.des3.ThreeDESUtil;
import com.allzai.exception.DaoException;
import com.allzai.form.guest.QuickLogonForm;
import com.allzai.util.Constants;
import com.allzai.util.JsonUtil;
import com.restfb.json.JsonObject;

public class QuickLogonServer {

	private static final QuickLogonServer quickLogonServer = new QuickLogonServer();

	public static QuickLogonServer getInstance() {
		return quickLogonServer;
	}

	public JsonObject doQuickLogon(QuickLogonForm form) throws Exception {
		
		JsonObject json = new JsonObject();

		if (form == null || form.getPackageName() == null) {
			/**
			 * Fx0103:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Fx0103");
			return json;
		}

		Map<String, Object> userMap = null;
		try {
			userMap = UserSlaveDao.getInstance().queryGuestUserInfoByImei(form.getImei());
			if (userMap == null || userMap.size() <= 0) {
				
				// 若临时帐户不存在, 则直接创建再返回
				int userID = QuickLogonDao.getInstance().doRegisterGuest(form.getImei());
				if (userID > 0) {
					userMap = UserSlaveDao.getInstance().queryUserInfoByUserId(userID);
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
		userMap.put("tk", ThreeDESUtil.Encode(userMap.get("id") + "_" + Constants.GUEST_USER_ROLE, Constants.index_tk_deocde));
		/**
		 * Fx0100:登录成功
		 */
		json.put("result", Boolean.TRUE);
		json.put("code", "Fx0100");
		json.put("user", JsonUtil.formatMapToJson(userMap));
		return json;
	}

}
