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
	public Class<?> getFromBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
