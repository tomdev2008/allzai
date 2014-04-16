package com.allzai.action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.order.OrderReportForm;
import com.allzai.server.order.OrderReportServer;
import com.restfb.json.JsonObject;

public class OrderReportAction extends BaseActionSupport {

	/**
	 * 提交生成订单
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		OrderReportForm form = (OrderReportForm) obj;

		return OrderReportServer.getInstance().doOrderReport(form);
	}

	@Override
	public Class<OrderReportForm> getFromBean() {
		return OrderReportForm.class;
	}

}
