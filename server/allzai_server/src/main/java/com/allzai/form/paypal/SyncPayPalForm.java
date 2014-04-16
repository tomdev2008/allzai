package com.allzai.form.paypal;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class SyncPayPalForm extends BasicForm {

	/**
	 * paypal支付的同步消息
	 */
	private static final long serialVersionUID = 1L;
	
	private String tax1;
	private String residence_country;
	private String num_cart_items;
	private String invoice;
	private String address_city;
	private String payer_id;
	private String first_name;
	private String mc_fee;
	private String txn_id;
	private String receiver_email;
	private String custom;
	private String payment_date;
	private String payment_fee;
	private String charset;
	private String address_country_code;
	private String payment_gross;
	private String address_zip;
	private String item_name1;
	private String ipn_track_id;
	private String mc_handling;
	private String mc_handling1;
	private String tax;
	private String address_name;
	private String last_name;
	private String receiver_id;
	private String verify_sign;
	private String address_country;
	private String address_status;
	private String payment_status;
	private String protection_eligibility;
	private String transaction_subject;
	private String payer_email;
	private String notify_version;
	private String txn_type;
	private String mc_gross;
	private String mc_shipping;
	private String payer_status;
	private String mc_currency;
	private String mc_gross_1;
	private String mc_shipping1;
	private String item_number1;
	private String quantity1;
	private String address_state;
	private String payment_type;
	private String address_street;

	@Override
	public String toString() {
		return "SyncPayPalForm [tax1=" + tax1 + ", residence_country="
				+ residence_country + ", num_cart_items=" + num_cart_items
				+ ", invoice=" + invoice + ", address_city=" + address_city
				+ ", payer_id=" + payer_id + ", first_name=" + first_name
				+ ", mc_fee=" + mc_fee + ", txn_id=" + txn_id
				+ ", receiver_email=" + receiver_email + ", custom=" + custom
				+ ", payment_date=" + payment_date + ", payment_fee="
				+ payment_fee + ", charset=" + charset
				+ ", address_country_code=" + address_country_code
				+ ", payment_gross=" + payment_gross + ", address_zip="
				+ address_zip + ", item_name1=" + item_name1
				+ ", ipn_track_id=" + ipn_track_id + ", mc_handling="
				+ mc_handling + ", mc_handling1=" + mc_handling1 + ", tax="
				+ tax + ", address_name=" + address_name + ", last_name="
				+ last_name + ", receiver_id=" + receiver_id + ", verify_sign="
				+ verify_sign + ", address_country=" + address_country
				+ ", address_status=" + address_status + ", payment_status="
				+ payment_status + ", protection_eligibility="
				+ protection_eligibility + ", transaction_subject="
				+ transaction_subject + ", payer_email=" + payer_email
				+ ", notify_version=" + notify_version + ", txn_type="
				+ txn_type + ", mc_gross=" + mc_gross + ", mc_shipping="
				+ mc_shipping + ", payer_status=" + payer_status
				+ ", mc_currency=" + mc_currency + ", mc_gross_1=" + mc_gross_1
				+ ", mc_shipping1=" + mc_shipping1 + ", item_number1="
				+ item_number1 + ", quantity1=" + quantity1
				+ ", address_state=" + address_state + ", payment_type="
				+ payment_type + ", address_street=" + address_street + "]";
	}

	public String getTax1() {
		return tax1;
	}

	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}

	public String getResidence_country() {
		return residence_country;
	}

	public void setResidence_country(String residence_country) {
		this.residence_country = residence_country;
	}

	public String getNum_cart_items() {
		return num_cart_items;
	}

	public void setNum_cart_items(String num_cart_items) {
		this.num_cart_items = num_cart_items;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getAddress_city() {
		return address_city;
	}

	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}

	public String getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(String payer_id) {
		this.payer_id = payer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMc_fee() {
		return mc_fee;
	}

	public void setMc_fee(String mc_fee) {
		this.mc_fee = mc_fee;
	}

	public String getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}

	public String getReceiver_email() {
		return receiver_email;
	}

	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getPayment_fee() {
		return payment_fee;
	}

	public void setPayment_fee(String payment_fee) {
		this.payment_fee = payment_fee;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getAddress_country_code() {
		return address_country_code;
	}

	public void setAddress_country_code(String address_country_code) {
		this.address_country_code = address_country_code;
	}

	public String getPayment_gross() {
		return payment_gross;
	}

	public void setPayment_gross(String payment_gross) {
		this.payment_gross = payment_gross;
	}

	public String getAddress_zip() {
		return address_zip;
	}

	public void setAddress_zip(String address_zip) {
		this.address_zip = address_zip;
	}

	public String getItem_name1() {
		return item_name1;
	}

	public void setItem_name1(String item_name1) {
		this.item_name1 = item_name1;
	}

	public String getIpn_track_id() {
		return ipn_track_id;
	}

	public void setIpn_track_id(String ipn_track_id) {
		this.ipn_track_id = ipn_track_id;
	}

	public String getMc_handling() {
		return mc_handling;
	}

	public void setMc_handling(String mc_handling) {
		this.mc_handling = mc_handling;
	}

	public String getMc_handling1() {
		return mc_handling1;
	}

	public void setMc_handling1(String mc_handling1) {
		this.mc_handling1 = mc_handling1;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}

	public String getVerify_sign() {
		return verify_sign;
	}

	public void setVerify_sign(String verify_sign) {
		this.verify_sign = verify_sign;
	}

	public String getAddress_country() {
		return address_country;
	}

	public void setAddress_country(String address_country) {
		this.address_country = address_country;
	}

	public String getAddress_status() {
		return address_status;
	}

	public void setAddress_status(String address_status) {
		this.address_status = address_status;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getProtection_eligibility() {
		return protection_eligibility;
	}

	public void setProtection_eligibility(String protection_eligibility) {
		this.protection_eligibility = protection_eligibility;
	}

	public String getTransaction_subject() {
		return transaction_subject;
	}

	public void setTransaction_subject(String transaction_subject) {
		this.transaction_subject = transaction_subject;
	}

	public String getPayer_email() {
		return payer_email;
	}

	public void setPayer_email(String payer_email) {
		this.payer_email = payer_email;
	}

	public String getNotify_version() {
		return notify_version;
	}

	public void setNotify_version(String notify_version) {
		this.notify_version = notify_version;
	}

	public String getTxn_type() {
		return txn_type;
	}

	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}

	public String getMc_gross() {
		return mc_gross;
	}

	public void setMc_gross(String mc_gross) {
		this.mc_gross = mc_gross;
	}

	public String getMc_shipping() {
		return mc_shipping;
	}

	public void setMc_shipping(String mc_shipping) {
		this.mc_shipping = mc_shipping;
	}

	public String getPayer_status() {
		return payer_status;
	}

	public void setPayer_status(String payer_status) {
		this.payer_status = payer_status;
	}

	public String getMc_currency() {
		return mc_currency;
	}

	public void setMc_currency(String mc_currency) {
		this.mc_currency = mc_currency;
	}

	public String getMc_gross_1() {
		return mc_gross_1;
	}

	public void setMc_gross_1(String mc_gross_1) {
		this.mc_gross_1 = mc_gross_1;
	}

	public String getMc_shipping1() {
		return mc_shipping1;
	}

	public void setMc_shipping1(String mc_shipping1) {
		this.mc_shipping1 = mc_shipping1;
	}

	public String getItem_number1() {
		return item_number1;
	}

	public void setItem_number1(String item_number1) {
		this.item_number1 = item_number1;
	}

	public String getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
	}

	public String getAddress_state() {
		return address_state;
	}

	public void setAddress_state(String address_state) {
		this.address_state = address_state;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getAddress_street() {
		return address_street;
	}

	public void setAddress_street(String address_street) {
		this.address_street = address_street;
	}

	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
