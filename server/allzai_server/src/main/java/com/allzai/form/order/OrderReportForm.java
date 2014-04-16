package com.allzai.form.order;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class OrderReportForm extends BasicForm {

	/**
	 * 提交生成订单
	 */
	private static final long serialVersionUID = 1L;
	
	private String packageName;
	
	private double amount;
	
	private String target;
	
	private String unit;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "OrderReportForm [packageName=" + packageName + ", amount="
				+ amount + ", target=" + target + ", unit=" + unit + "]";
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
