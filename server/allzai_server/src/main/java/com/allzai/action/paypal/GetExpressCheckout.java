package com.allzai.action.paypal;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;

import com.allzai.action.BaseActionSupport;
import com.allzai.util.Configuration;
import com.restfb.json.JsonObject;

public class GetExpressCheckout extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
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
