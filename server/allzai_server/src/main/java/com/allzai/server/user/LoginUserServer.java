package com.allzai.server.user;

import java.util.Map;

import com.allzai.dao.user.UserSlaveDao;
import com.allzai.des3.ThreeDESUtil;
import com.allzai.form.user.LoginUserForm;
import com.allzai.util.Constants;
import com.allzai.util.JsonUtil;
import com.restfb.json.JsonObject;

public class LoginUserServer {
	
	 private static final LoginUserServer loginUserServer = new LoginUserServer();
		
	public static LoginUserServer getInstance() {
		return loginUserServer;
	}
	
	public JsonObject doLoginUser(LoginUserForm form) throws Exception {
		
		JsonObject json = new JsonObject();
		
		try {
			if(form.getAccount() == null  || form.getPassword() == null) {
				/**
				 * Fx0004:参数错误
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Fx0004");
				return json;
			}

			Map<String, Object> users =  UserSlaveDao.getInstance().doLoginUser(form.getAccount(), form.getPassword());
			
			if(users == null || users.size() <= 0) {
				/**
				 * Fx0002:参数错误
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Fx0002");
				return json;
			}
			
			int userId = Integer.parseInt(String.valueOf(users.get("id")));
			
			//添加交互字段
			users.put("tk", ThreeDESUtil.Encode(userId + "_" + form.getImei() + "_0", Constants.index_tk_deocde));
			/**
			 * Fx0000:登录成功
			 */
			json.put("result", Boolean.TRUE);
			json.put("code", "Fx0000");
			json.put("user", JsonUtil.formatMapToJson(users));
			return json;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Fx0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Fx0001");
			return json;
		}
	}

}
