package com.allzai.dao.order;

import java.sql.Connection;
import java.util.Map;

import com.allzai.bean.BaseBean;
import com.allzai.dao.BaseJdbcDaoSupport;
import com.allzai.system.DatabaseManager;
import com.allzai.util.AmountUtil;

public class OrderReportDao extends BaseJdbcDaoSupport<BaseBean> {
	
	private static final OrderReportDao orderReportDao = new OrderReportDao();

	public static OrderReportDao getInstance() {
		return orderReportDao;
	}

	@Override
	public Connection getConnection() {
		return DatabaseManager.getMasterConn();
	}

	public int doOrderReport(String number, int userId, int gameId, String target, double amount, String ip, String country, String area, String unit, String type, String packageName) {

		String sql = "INSERT INTO order_info(number,userId,gameId,packageName,target,order_amount,ip,country,area,operate,unit,createTime) VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";
		
		return super.doSave(sql, number,userId,gameId,packageName,target,amount,ip,country,area,type,unit);
	}

	public Map<String, Object> getOrderInfo(String number) {

		String sql = "SELECT * FROM order_info WHERE number = ?";
		
		return super.doQueryMap(sql, number);
	}

	public boolean setOrderInfoStatus(int orderId, double amount, int status) {
		/**
		 * MSGx0003:还未支付
		 * MSGx0004:支付成功
		 * MSGx0005:支付失败
		 */
		String msg_code = (status == AmountUtil.PAY_SUCC) ?  "MSGx0004" : "MSGx0005";

		String sql = "UPDATE order_info SET order_status = ?, pay_amount = ?, payTime = now(), msg_code = ? WHERE id = ?";
		
		return super.doChanage(sql, status, amount, msg_code, orderId);
	}

	public boolean updateOrderPayType(String payType, String ops, String number) 
	{
		String sql = "update order_info set pay_type = ?, ops = ? where number = ?";
		
		return super.doChanage(sql, payType, ops, number);
	}

	public boolean setOrderInfoNotice(int orderId, int status) {

		String sql = "UPDATE order_info SET game_status = ? WHERE id = ?";
		
		return super.doChanage(sql, status, orderId);
	}

}
