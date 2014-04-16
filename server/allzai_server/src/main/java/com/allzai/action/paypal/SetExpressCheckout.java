package com.allzai.action.paypal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.paypal.SetExpressForm;
import com.allzai.util.Configuration;
import com.restfb.json.JsonObject;

public class SetExpressCheckout extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		Map<String,String> configurationMap =  Configuration.getAcctAndConfig();
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

		SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
		SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

		StringBuffer url = new StringBuffer();
		url.append("http://");
		url.append(req.getServerName());
		url.append(":");
		url.append(req.getServerPort());
		url.append(req.getContextPath());

		String returnURL = url.toString() + "/EC/GetExpressCheckout";
		String cancelURL = url.toString() + "/index.html";

		double itemTotal = 0.00; // 鍗曠瑪
		double orderTotal = 0.00; // 鎬婚
		String paymentType = "Sale"; // 鏂瑰紡

		String amountItems = req.getParameter("amount"); // 閲戦
		String names = req.getParameter("packageName"); // 鍖呭悕
		details.setInvoiceID(req.getParameter("number")); // 璁㈠崟
		String unit = req.getParameter("unit"); // 璐у竵

		details.setReturnURL(returnURL + "?currencyCodeType=" + unit);
		details.setCancelURL(cancelURL);

		List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
		PaymentDetailsItemType item = new PaymentDetailsItemType();
		BasicAmountType amt = new BasicAmountType();
		amt.setCurrencyID(CurrencyCodeType.fromValue(unit));
		amt.setValue(amountItems);
		item.setName(names);
		item.setAmount(amt);
		lineItems.add(item);

		itemTotal += Double.parseDouble(amountItems);
		orderTotal += itemTotal;

		List<PaymentDetailsType> payDetails = new ArrayList<PaymentDetailsType>();
		PaymentDetailsType paydtl = new PaymentDetailsType();
		paydtl.setPaymentAction(PaymentActionCodeType.fromValue(paymentType));
		BasicAmountType itemsTotal = new BasicAmountType();
		itemsTotal.setValue(Double.toString(itemTotal));
		itemsTotal.setCurrencyID(CurrencyCodeType.fromValue(unit));
		paydtl.setOrderTotal(new BasicAmountType(CurrencyCodeType
				.fromValue(unit), Double.toString(orderTotal)));
		paydtl.setPaymentDetailsItem(lineItems);
		paydtl.setItemTotal(itemsTotal);
		payDetails.add(paydtl);
		details.setPaymentDetails(payDetails);

		setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);
		SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
		expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);
		SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);

		if (setExpressCheckoutResponse != null) {
			if (setExpressCheckoutResponse.getAck().toString()
					.equalsIgnoreCase("SUCCESS")) {

				JsonObject json = new JsonObject();
				json.put("result", Boolean.TRUE);
				json.put("Ack", setExpressCheckoutResponse.getAck().toString());
				json.put("Token", setExpressCheckoutResponse.getToken());
				json.put("RedirectURL",
						"https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
								+ setExpressCheckoutResponse.getToken());

				return json;
			} else {
				JsonObject json = new JsonObject();
				json.put("result", Boolean.FALSE);
				json.put("info", "鑾峰彇楠岃瘉澶辫触");
				return json;
			}
		} else {
			JsonObject json = new JsonObject();
			json.put("result", Boolean.FALSE);
			json.put("info", "璇锋眰娌℃湁鍝嶅簲");
			return json;
		}

	}

	@Override
	public Class<SetExpressForm> getFromBean() {
		return SetExpressForm.class;
	}

}
