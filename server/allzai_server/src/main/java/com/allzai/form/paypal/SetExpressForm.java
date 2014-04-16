package com.allzai.form.paypal;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class SetExpressForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String amount;
	
	private String unit;
	
	private String itemName;
	
	private String number;

	@Override
	public String toString() {
		return "SetExpressForm [amount=" + amount + ", unit=" + unit
				+ ", itemName=" + itemName + ", number=" + number + "]";
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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
