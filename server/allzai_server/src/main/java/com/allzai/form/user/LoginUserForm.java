package com.allzai.form.user;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class LoginUserForm extends BasicForm {
	
	@Override
	public String toString() {
		return "LoginUserForm [account=" + account + ", password=" + password
				+ ", captcha=" + captcha + "]";
	}

	private static final long serialVersionUID = 5061857746760461705L;
	
	private String account;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	private String password;

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	private String captcha;
	
	public String getCaptcha() {
		return captcha;
	}
	
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.form.BaseForm#convertBaseBean()
	 */
	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}


}
