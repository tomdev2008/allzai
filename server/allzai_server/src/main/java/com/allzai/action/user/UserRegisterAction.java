package com.allzai.action.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.user.RegeistUserForm;
import com.allzai.server.user.UserManageServer;
import com.allzai.util.Constants;
import com.restfb.json.JsonObject;

/**
 * 注册用户
 * 
 * @author Eric
 * @version hasoffer-0.0.1, 2013-9-12
 * @see UserManageServer#registerUser(com.yeahmobi.gamelala.bean.UserBean)
 * @since JDK 1.6
 */
public class UserRegisterAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5400375127584759786L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yeahmobi.gamelala.action.BaseActionSupport#doAutoAction(com.yeahmobi
	 * .gamelala.bean.InputBean, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		JsonObject json = new JsonObject();

		// 0:success, -1:failed, 1:alreday
		try {
			RegeistUserForm form = (RegeistUserForm) obj;
			
			if(form.getAccount() == null  || form.getPassword() == null) {
				/**
				 * Fx0004:参数错误
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Fx0004");
				return json;
			}

			Pattern regex = Pattern.compile(Constants.EMAIL_REGEX);
			Matcher matcher = regex.matcher(form.getAccount());
			if (!matcher.matches()) {
				/**
				 * Ex0004:邮箱格式不正确
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Ex0004");
				return json;
			}

			String callResult = UserManageServer.getInstance().registerUser(form);

			if (Constants.ZERO_STR.equals(callResult)) {
				/**
				 * Ex0000:注册成功
				 */
				json.put("result", Boolean.TRUE);
				json.put("code", "Ex0000");
				return json;
			} else if (Constants.ONE_STR.equals(callResult)) {
				/**
				 * Ex0003:已经存在
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Ex0003");
				return json;
			} else {
				/**
				 * Ex0002:参数异常
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Ex0002");
				return json;
			}
		} catch (Exception e) {
			e.printStackTrace();

			json.put("result", Boolean.FALSE);
			json.put("code", "Ex0001");
			return json;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yeahmobi.gamelala.action.BaseActionSupport#getFromBean()
	 */
	@Override
	public Class<RegeistUserForm> getFromBean() {
		return RegeistUserForm.class;
	}

}
