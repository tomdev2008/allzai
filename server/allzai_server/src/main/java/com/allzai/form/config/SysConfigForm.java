package com.allzai.form.config;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class SysConfigForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String packageName;

	@Override
	public String toString() {
		return "SysConfigForm [packageName=" + packageName + "]";
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
