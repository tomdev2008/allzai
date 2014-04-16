package com.allzai.server.paypal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.allzai.form.paypal.DoExpressForm;
import com.allzai.form.paypal.GetExpressForm;
import com.allzai.form.paypal.SetExpressForm;
import com.allzai.util.Configuration;
import com.restfb.json.JsonObject;

public class CheckoutServer {

	private static final CheckoutServer checkoutServer = new CheckoutServer();
	
	public static CheckoutServer getInstance() {
		return checkoutServer;
	}

	public JsonObject doExpressCheckOut(DoExpressForm form) {
		
		Map<String,String> configurationMap =  Configuration.getAcctAndConfig();
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

		DoExpressCheckoutPaymentRequestType doCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
		DoExpressCheckoutPaymentRequestDetailsType details = new DoExpressCheckoutPaymentRequestDetailsType();
		
		details.setToken(req.getParameter("token"));
		details.setPayerID(req.getParameter("payerID"));
		
		double itemTotalAmt = 0.00;
		double orderTotalAmt = 0.00;
		
		String amt = req.getParameter("amount");
		String unit = req.getParameter("unit");
		String number = req.getParameter("number");
		String packageName = req.getParameter("packageName");
		
		itemTotalAmt = Double.parseDouble(amt);
		orderTotalAmt += itemTotalAmt;
		
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setValue(Double.toString(orderTotalAmt));
		orderTotal.setCurrencyID(CurrencyCodeType.fromValue(unit));
		paymentDetails.setOrderTotal(orderTotal);

		BasicAmountType itemTotal = new BasicAmountType();
		itemTotal.setValue(Double.toString(itemTotalAmt));
		itemTotal.setCurrencyID(CurrencyCodeType.fromValue(unit));
		paymentDetails.setItemTotal(itemTotal);
		paymentDetails.setInvoiceID(number);
		
		List<PaymentDetailsItemType> paymentItems = new ArrayList<PaymentDetailsItemType>();
		PaymentDetailsItemType paymentItem = new PaymentDetailsItemType();

		paymentItem.setName(packageName);
		paymentItem.setNumber(number);

		BasicAmountType amount = new BasicAmountType();

		amount.setValue(amt);
		amount.setCurrencyID(CurrencyCodeType.fromValue(unit));
		paymentItem.setAmount(amount);
		paymentItems.add(paymentItem);
		paymentDetails.setPaymentDetailsItem(paymentItems);

		List<PaymentDetailsType> payDetailType = new ArrayList<PaymentDetailsType>();
		payDetailType.add(paymentDetails);

		details.setPaymentDetails(payDetailType);

		doCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(details);
		DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
		doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doCheckoutPaymentRequestType);

		DoExpressCheckoutPaymentResponseType doCheckoutPaymentResponseType = service.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
		if (doCheckoutPaymentResponseType != null) {

			if (doCheckoutPaymentResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
				JsonObject json = new JsonObject();
				json.put("result", Boolean.TRUE);
				json.put("Ack", doCheckoutPaymentResponseType.getAck().toString());
				return json;
			} else {
				JsonObject json = new JsonObject();
				json.put("result", Boolean.FALSE);
				return json;
			}
		} else {
			JsonObject json = new JsonObject();
			json.put("result", Boolean.FALSE);
			return json;
		}
		return null;
	}

	public JsonObject getExpressCheckOut(GetExpressForm form) {
		Map<String,String> configurationMap =  Configuration.getAcctAndConfig();
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

		GetExpressCheckoutDetailsReq req = new GetExpressCheckoutDetailsReq();

		GetExpressCheckoutDetailsRequestType reqType = new GetExpressCheckoutDetailsRequestType(request.getParameter("token"));
		req.setGetExpressCheckoutDetailsRequest(reqType);
		GetExpressCheckoutDetailsResponseType resp = service.getExpressCheckoutDetails(req);
		if (resp != null) {
			if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {

				JsonObject json = new JsonObject();
				json.put("result", Boolean.TRUE);
				json.put("Ack", resp.getAck().toString());
				json.put("Token", resp
						.getGetExpressCheckoutDetailsResponseDetails()
						.getToken());
				json.put("PayerID", resp
						.getGetExpressCheckoutDetailsResponseDetails()
						.getPayerInfo().getPayerID());

				return json;
			} else {
				JsonObject json = new JsonObject();
				json.put("result", Boolean.FALSE);
				return json;
			}
		} else {
			JsonObject json = new JsonObject();
			json.put("result", Boolean.FALSE);
			return json;
		}
		return null;
	}

	public JsonObject setExpressCheckOut(SetExpressForm form) {
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
				return json;
			}
		} else {
			JsonObject json = new JsonObject();
			json.put("result", Boolean.FALSE);
			return json;
		}
		return null;
	}

}
