package com.yeahmobi.gamelala.form.user;

import com.yeahmobi.gamelala.bean.BaseBean;
import com.yeahmobi.gamelala.form.BasicForm;

public class LoginUserForm extends BasicForm {
	
	@Override
	public String toString() {
		return "LoginUserForm [account=" + account + ", password=" + password
				+ ", packageName=" + packageName + "]";
	}
	/**
	 * dhhuang
	 */
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
	
	private String packageName;

	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
