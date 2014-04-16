package com.allzai.form.isp;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class NetWorkForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sip;

	@Override
	public String toString() {
		return "NetWorkForm [sip=" + sip + "]";
	}

	public String getSip() {
		return sip;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
