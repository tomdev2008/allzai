package com.allzai.form.user;

import java.io.Serializable;

import com.allzai.bean.UserBean;
import com.allzai.form.BasicForm;

/**
 * 注册用户Form<p>
 * convertUserBean需要调整
 */
public class RegeistUserForm extends BasicForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1180232489751657148L;
	
	//(0:官网, 1:googlePlay 2:facebook 3:twitter 4:未知)
	
	private String account;
	
	private String password;
	
	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.form.BaseForm#convertBaseBean()
	 */
	@Override
	public UserBean convertBaseBean() 
	{
		UserBean ub = new UserBean();
		ub.setAccount(this.account);
		ub.setPassword(this.password);
		return ub;
	}
	
	/**
	 * @return the account
	 */
	public String getAccount() 
	{
		return account;
	}


	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) 
	{
		this.account = account;
	}


	/**
	 * @return the password
	 */
	public String getPassword() 
	{
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
	public String toString() {
		return "RegeistUserForm [account=" + account + ", password=" + password
				+ ", captcha=" + captcha + "]";
	}

}
