package com.allzai.server.order;

import java.util.HashMap;
import java.util.Map;

import com.allzai.dao.order.OrderReportDao;
import com.allzai.dao.user.UserSlaveDao;
import com.allzai.exception.DaoException;
import com.allzai.form.order.OrderReportForm;
import com.allzai.form.order.ReportOrderForm;
import com.allzai.system.ServerEnvLoader;
import com.allzai.util.AmountUtil;
import com.allzai.util.JsonUtil;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;

public class OrderReportServer {
	
	private static final OrderReportServer orderReportServer = new OrderReportServer();
	
	public static OrderReportServer getInstance() {
		return orderReportServer;
	}

	public JsonObject doOrderReport(OrderReportForm form) {
		
		JsonObject json = new JsonObject();
		
		if(form.getPackageName() == null || form.getTarget() == null || form.getAmount() <= 0 || form.getUnit() == null) {
			/**
			 * Lx0002:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Lx0002");
			return json;
		}
		
		String unit = form.getUnit().toUpperCase();
		
		try {
			String PAYPAL_ = ServerEnvLoader.configCacheMap.get("PAY_SUPPORT_PAYPAL_LIST").get("value").toString().trim();
			if(!(PAYPAL_.contains(unit + ";"))) {
				/**
				 * Lx0006:不支持的结算币种
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Lx0006");
				return json;
			}
			
			if(!ServerEnvLoader.gameCacheMap.containsKey(form.getPackageName())) {
				/**
				 * Lx0003:应用不存在
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Lx0003");
				return json;
			} else {
				Map<String, Object> map = UserSlaveDao.getInstance().queryUserInfoByUserId(form.getUserId());
				if(map == null || map.size() <= 0) {
					/**
					 * Lx0004:用户不存在
					 */
					json.put("result", Boolean.FALSE);
					json.put("code", "Lx0004");
					return json;
				}

				map = ServerEnvLoader.gameCacheMap.get(form.getPackageName());
				int gameId = Integer.parseInt(String.valueOf(map.get("id")));
				map = null;

				//生成订单序号
				String number = AmountUtil.getOrderNumber();
				int orderId = OrderReportDao.getInstance().doOrderReport(number, form.getUserId(), gameId , form.getTarget(), form.getAmount(), form.getIp(), form.getCountry(), form.getArea(), unit, "buy", form.getPackageName());

				if(orderId > 0) {
					/**
					 * Lx0000:订单提交成功
					 */
					json.put("result", Boolean.TRUE);
					json.put("code", "Lx0000");
					json.put("number", number);
					json.put("amount", form.getAmount());
					json.put("unit", unit);
					return json;
				} else {
					/**
					 * Lx0005:订单生成失败
					 */
					json.put("result", Boolean.FALSE);
					json.put("code", "Lx0005");
					return json;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			/**
			 * Lx0001:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Lx0001");
			return json;
		}
	}

	/**
	 * 为订单选择支付方案
	 * @param form
	 * @return
	 */
	public JsonObject doReportOrder(ReportOrderForm form) {
		
		JsonObject json = new JsonObject();
		
		if(form.getPayType() == null || form.getNumber() == null) {
			/**
			 * Lx0102:参数错误
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Lx0102");
			return json;
		}
		
		try {
			Map<String, Object> map = UserSlaveDao.getInstance().queryUserInfoByUserId(form.getUserId());
			if(map == null || map.size() <= 0) {
				/**
				 * Lx0104:用户不存在
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Lx0104");
				return json;
			}
			
			map = OrderReportServer.getInstance().getOrderInfoByContent(form.getNumber());
			if(map == null || map.size() <= 0) {
				/**
				 * Lx0105:订单不存在
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Lx0105");
				return json;
			}
			
			int order_status = Integer.parseInt(String.valueOf(map.get("order_status")));
			if(order_status == AmountUtil.PAY_SUCC) {
				/**
				 * Lx0108:订单已支付
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Lx0108");
				return json;
			}
			
			if(AmountUtil.PAY_TYPE_PAL.equals(form.getPayType())) {
				
				double amount = Double.parseDouble(String.valueOf(map.get("order_amount")));
				String unit = String.valueOf(map.get("unit"));
				
				String PAYPAL_ = ServerEnvLoader.configCacheMap.get("PAY_SUPPORT_PAYPAL_LIST").get("value").toString().trim();
				if(PAYPAL_.contains(unit + ";")) {
					
					json.put("payType", AmountUtil.PAY_TYPE_PAL);
					
					JsonArray array = new JsonArray();
					Map<String, Object> orders = new HashMap<String, Object>();
					
					orders.put("SetExpressCheckout", AmountUtil.SetExpressCheckout);
					orders.put("GetExpressCheckout", AmountUtil.GetExpressCheckout);
					orders.put("DoExpressCheckout", AmountUtil.DoExpressCheckout);
					
					orders.put("amount", amount);
					orders.put("unit", unit);
					orders.put("number", form.getNumber());
							
					array.put(JsonUtil.formatMapToJson(orders));
					
					OrderReportDao.getInstance().updateOrderPayType(AmountUtil.PAY_TYPE_PAL, "op=paypal;", form.getNumber());
				
					/**
					 * Lx0100:支付方案生成成功
					 */
					json.put("result", Boolean.TRUE);
					json.put("code", "Lx0100");
					json.put("cmd", array);
					return json;
				} else {
					/**
					 * Lx0107:没有其它支付币种
					 */
					json.put("result", Boolean.FALSE);
					json.put("code", "Lx0107");
					return json;
				}
			} else {
				/**
				 * Lx0103:支付方式不支持
				 */
				json.put("result", Boolean.FALSE);
				json.put("code", "Lx0103");
				return json;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			
			/**
			 * Lx0101:内部异常
			 */
			json.put("result", Boolean.FALSE);
			json.put("code", "Lx0101");
			return json;
		}
		
	}
	
	/**
	 * 修改订单的处理状态
	 * @param orderId
	 * @param status
	 * @return
	 */
	public boolean setOrderInfoStatus(int orderId, double amount, int status) {
		
		return OrderReportDao.getInstance().setOrderInfoStatus(orderId, amount, status);
	}

	/**
	 * 通过指令查找原订单
	 * @param content
	 * @return
	 */
	public Map<String, Object> getOrderInfoByContent(String number) {
		
		return OrderReportDao.getInstance().getOrderInfo(number);
	}
	
	/**
	 * 修改订单的通知状态
	 * @param orderId
	 * @param status
	 * @return
	 */
	public boolean setOrderInfoNotice(int orderId, int status) {

		return OrderReportDao.getInstance().setOrderInfoNotice(orderId, status);
	}

}
