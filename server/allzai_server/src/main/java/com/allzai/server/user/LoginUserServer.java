package com.allzai.server.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.allzai.dao.user.UserSlaveDao;
import com.allzai.des3.ThreeDESUtil;
import com.allzai.form.user.LoginUserForm;
import com.allzai.util.Constants;
import com.allzai.util.GeetestLib;
import com.allzai.util.JsonUtil;
import com.allzai.util.StringUtil;
import com.restfb.json.JsonObject;

public class LoginUserServer {
	
	 private static final LoginUserServer loginUserServer = new LoginUserServer();
		
	public static LoginUserServer getInstance() {
		return loginUserServer;
	}
	
	public JsonObject doLoginUser(LoginUserForm form, HttpServletRequest req) throws Exception {
		
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
			
			boolean result = false;
			if(!StringUtil.isEmpty(form.getCaptcha())) {
				if(Constants.CAPTCHA_GEETEST.equals(form.getCaptcha())) {
					try {
						GeetestLib geetest = new GeetestLib(Constants.GEETEST_KEY);
						result = geetest.validate(
								req.getParameter("geetest_challenge"),
								req.getParameter("geetest_validate"),
								req.getParameter("geetest_seccode"));
					} catch (Exception ex) {ex.printStackTrace();}
				}
			}
			if (!result) {
				/**
				 * Fx0005:验证图片错误
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Fx0005");
				return json;
			}

			//对用户密码进行加密处理
			form.setPassword(ThreeDESUtil.Encode(form.getPassword(), Constants.index_pw_deocde));
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
			users.put("tk", ThreeDESUtil.Encode(userId + "_" + Constants.NORMAL_USER_ROLE, Constants.index_tk_deocde));
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
