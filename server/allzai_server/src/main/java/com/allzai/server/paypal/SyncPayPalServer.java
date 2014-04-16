package com.allzai.server.paypal;

import java.util.Map;

import org.apache.log4j.Logger;

import com.allzai.dao.order.OrderReportDao;
import com.allzai.dao.paypal.SyncPayPalDao;
import com.allzai.form.paypal.SyncPayPalForm;
import com.allzai.server.order.OrderReportServer;
import com.allzai.system.ServerEnvLoader;
import com.allzai.util.AmountUtil;
import com.allzai.util.CallUtil;

public class SyncPayPalServer {
	
private static final SyncPayPalServer syncPayPalServer = new SyncPayPalServer();
	
	private static final Logger logger = Logger.getLogger(SyncPayPalServer.class);

	public static SyncPayPalServer getInstance() {
		return syncPayPalServer;
	}

	public String doSyncPayPal(SyncPayPalForm form) {
		
		try {
			Map<String, Object> map = SyncPayPalDao.getInstance().getPayPalByInvoice(form.getInvoice());
			if(map != null && map.size() > 0) {
				logger.info("重复的PayPal消息, invoice="+form.getInvoice());
				return "200";
			}
			
			SyncPayPalDao.getInstance().savePayPal(form);
			
			String number = form.getInvoice();
			logger.info("订单编号="+form.getInvoice());
			map = OrderReportDao.getInstance().getOrderInfo(number);
			if(map == null || map.size() <= 0) {
				logger.info("订单不存在, invoice="+number);
				return "202";
			}
			
			String payType = String.valueOf(map.get("pay_type"));
			logger.info("支付方式="+payType);
			if(!AmountUtil.PAY_TYPE_PAL.equals(payType)) {
				logger.info("支付方式不匹配, payType="+payType);
				return "200";
			}
			
			if(Integer.parseInt(String.valueOf(map.get("order_status"))) == 1) {
				logger.info("已处理订单, invoice="+number);
				return "200";
			}
			
			double amount = Double.parseDouble(form.getMc_gross());
			
			//支付金额和订单金额的对比 ?
			
			int orderId = Integer.parseInt(String.valueOf(map.get("id")));
			int userId = Integer.parseInt(String.valueOf(map.get("userId")));
			int gameId = Integer.parseInt(String.valueOf(map.get("gameId")));
			String packageName = String.valueOf(map.get("packageName"));
			String target = String.valueOf(map.get("target"));
			String  unit = String.valueOf(map.get("unit"));
			
			boolean suc = "Completed".equals(form.getPayment_status());
			OrderReportServer.getInstance().setOrderInfoStatus(orderId, amount, suc ? AmountUtil.PAY_SUCC : AmountUtil.PAY_FAIL);
			logger.info("更新状态="+suc);
			
			if(suc) {
				map = ServerEnvLoader.gameCacheMap.get(packageName);
				if(map == null) {
					logger.info("应用不存在, packageName="+packageName);
					return "200";
				}
				
				Object callback = map.get("callback");
				if(callback != null) {
					StringBuffer query = new StringBuffer();
					query.append("packageName="+packageName+"&");
					query.append("userId="+userId+"&");
					query.append("number="+number+"&");
					query.append("target="+target+"&");
					query.append("amount="+amount+"&");
					query.append("unit="+unit);
					logger.info("请求参数=" + query.toString());

					if(CallUtil.execCmd(query.toString(), callback)) {
						OrderReportServer.getInstance().setOrderInfoNotice(orderId, AmountUtil.PAY_SUCC);
						logger.info("PayPal通知游戏成功, gameId="+gameId + ", target="+target + ", callback="+callback);
					} else {
						OrderReportServer.getInstance().setOrderInfoNotice(orderId, AmountUtil.PAY_FAIL);
						logger.info("PayPal通知游戏失败, gameId="+gameId + ", target="+target + ", callback="+callback);
					}
				} else {
					logger.info("未设置callback, PayPal无法通知到游戏, gameId="+gameId + ", target="+target);
				}
			} else {
				logger.info("失败的支付, invoice="+number);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			logger.error(e);
		}
		
		return "200";
	}

}
