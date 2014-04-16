package com.allzai.action.paypal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.paypal.DoExpressForm;
import com.allzai.server.paypal.CheckoutServer;
import com.allzai.util.Configuration;
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
