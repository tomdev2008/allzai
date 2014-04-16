package com.allzai.action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.order.ReportOrderForm;
import com.allzai.server.order.OrderReportServer;
import com.restfb.json.JsonObject;

public class ReportOrderAction extends BaseActionSupport {

	/**
	 * 提交生成订单
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		ReportOrderForm form = (ReportOrderForm) obj;

		return OrderReportServer.getInstance().doReportOrder(form);
	}

	@Override
	public Class<ReportOrderForm> getFromBean() {
		return ReportOrderForm.class;
	}

}
