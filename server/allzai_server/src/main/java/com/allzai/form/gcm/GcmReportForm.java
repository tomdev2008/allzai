package com.allzai.form.gcm;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class GcmReportForm extends BasicForm {

	/**
	 * 设备的GCM注册ID
	 */
	private static final long serialVersionUID = 1L;
	
	private String regId;
	
	private String packageName;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "GcmReportForm [regId=" + regId + ", packageName=" + packageName
				+ "]";
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
