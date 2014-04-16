package com.allzai.action.paypal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.paypal.SyncPayPalForm;
import com.allzai.server.paypal.SyncPayPalServer;
import com.allzai.util.StringUtil;
import com.restfb.json.JsonObject;

public class SyncPayPalAction extends BaseActionSupport {

	/**
	 * 接收paypal消息
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {

		SyncPayPalForm form = (SyncPayPalForm) obj;

		try {
			if(StringUtil.isEmpty(form.getInvoice())) {
				/**
				 * 202:参数异常
				 */
				resp.getWriter().append("202");
			} else {
				/**
				 * 200:同步成功
				 */
				resp.getWriter().append(SyncPayPalServer.getInstance().doSyncPayPal(form));
			}
		} catch (Exception e) {
			e.printStackTrace();
			/**
			 * 202:参数异常
			 */
			resp.getWriter().append("202");
		}
		return null;
		
	}

	@Override
	public Class<SyncPayPalForm> getFromBean() {
		return SyncPayPalForm.class;
	}

}
