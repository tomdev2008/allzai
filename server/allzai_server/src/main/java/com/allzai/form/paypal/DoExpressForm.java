package com.allzai.form.paypal;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class DoExpressForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String token;
	
	private String payerID;
	
	private String amount;
	
	private String unit;
	
	private String number;
	
	private String itemName;

	@Override
	public String toString() {
		return "DoExpressForm [token=" + token + ", payerID=" + payerID
				+ ", amount=" + amount + ", unit=" + unit + ", number="
				+ number + ", itemName=" + itemName + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPayerID() {
		return payerID;
	}

	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
