package com.yeahmobi.gamelala.form.guest;

import com.yeahmobi.gamelala.bean.BaseBean;
import com.yeahmobi.gamelala.form.BasicForm;

public class QuickLogonForm extends BasicForm {

	/**
	 * 快速登录
	 */
	private static final long serialVersionUID = 1L;
	
	//使用imei作为用户登录用帐号
	
	private String packageName;

	@Override
	public String toString() {
		return "QuickLogonForm [packageName=" + packageName + "]";
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
