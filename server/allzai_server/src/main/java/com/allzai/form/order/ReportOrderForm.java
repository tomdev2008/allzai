package com.allzai.form.order;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class ReportOrderForm extends BasicForm {

	/**
	 * 提交生成订单
	 */
	private static final long serialVersionUID = 1L;
	
	private String payType;
	
	private String number;
	
	@Override
	public String toString() {
		return "ReportOrderForm [payType=" + payType + ", number=" + number
				+ "]";
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
