package com.allzai.form.paypal;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class GetExpressForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;

	@Override
	public String toString() {
		return "GetExpressForm [token=" + token + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
