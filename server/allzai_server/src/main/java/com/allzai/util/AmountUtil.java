package com.allzai.util;

import java.util.Date;
import java.util.Random;

public class AmountUtil {
	
	//paypal支付的地址
	public static final String SetExpressCheckout = "http://127.0.0.1:8080/paypal/EC/SetExpressCheckout";
	public static final String GetExpressCheckout = "http://127.0.0.1:8080/paypal/EC/GetExpressCheckout";
	public static final String DoExpressCheckout  = "http://127.0.0.1:8080/paypal/EC/DoExpressCheckout";

	//订单状态
	public static final int PAY_SUCC = 1; //支付成功
	public static final int PAY_INIT = 0;	//还未支付
	public static final int PAY_FAIL = -1;	//支付失败

	public static final String PAY_TYPE_PAL = "paypal";
	
	/**
	 * 订单号生成规则
	 * @return
	 */
	public static String getOrderNumber() {
		
		//去掉年份前两位, 保证长度在10位以内
		
		String orderHeader = Constants.orderFormat.format(new Date()).substring(2);
		
		if(orderHeader.length() == 9) {
			return orderHeader + (new Random().nextInt(10));
		}
		
		if(orderHeader.length() == 8) {
			return orderHeader + "0" + (new Random().nextInt(10));
		}
		
		if(orderHeader.length() == 7) {
			return orderHeader + "00" + (new Random().nextInt(10));
		}

		return orderHeader;
	}

}
