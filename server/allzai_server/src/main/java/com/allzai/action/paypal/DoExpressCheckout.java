package com.allzai.action.paypal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.paypal.DoExpressForm;
import com.allzai.server.paypal.CheckoutServer;
import com.restfb.json.JsonObject;

public class DoExpressCheckout extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		DoExpressForm form = (DoExpressForm) obj;
		
		return CheckoutServer.getInstance().doExpressCheckOut(form);
	}

	@Override
	public Class<DoExpressForm> getFromBean() {
		return DoExpressForm.class;
	}

}
