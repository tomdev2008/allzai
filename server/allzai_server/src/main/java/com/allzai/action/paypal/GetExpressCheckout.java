package com.allzai.action.paypal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.paypal.GetExpressForm;
import com.allzai.server.paypal.CheckoutServer;
import com.restfb.json.JsonObject;

public class GetExpressCheckout extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		GetExpressForm form = (GetExpressForm) obj;

		return CheckoutServer.getInstance().getExpressCheckOut(form);
	}

	@Override
	public Class<GetExpressForm> getFromBean() {
		return GetExpressForm.class;
	}

}
