package com.allzai.action.paypal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.paypal.SetExpressForm;
import com.allzai.server.paypal.CheckoutServer;
import com.restfb.json.JsonObject;

public class SetExpressCheckout extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		SetExpressForm form = (SetExpressForm) obj;

		return CheckoutServer.getInstance().setExpressCheckOut(form, req.getServerName(), req.getServerPort(), req.getContextPath());
	}

	@Override
	public Class<SetExpressForm> getFromBean() {
		return SetExpressForm.class;
	}

}
