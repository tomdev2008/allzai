package com.allzai.dao.paypal;

import java.sql.Connection;
import java.util.Map;

import com.allzai.bean.BaseBean;
import com.allzai.dao.BaseJdbcDaoSupport;
import com.allzai.form.paypal.SyncPayPalForm;
import com.allzai.system.DatabaseManager;

public class SyncPayPalDao extends BaseJdbcDaoSupport<BaseBean>  {
	
private static SyncPayPalDao syncPayPalDao = new SyncPayPalDao();
	
	public static SyncPayPalDao getInstance() 
	{
		return syncPayPalDao;
	}

	@Override
	public Connection getConnection() {
		return DatabaseManager.getMasterConn();
	}

	public Map<String, Object> getPayPalByInvoice(String invoice) {
		
		String sql = "SELECT invoice FROM paypal_ipn WHERE invoice = ?";
		
		return super.doQueryMap(sql, invoice);
	}

	public boolean savePayPal(SyncPayPalForm form) {
		
		String sql = "INSERT INTO paypal_ipn VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now())";
		
		return super.doChanage(sql, 
				form.getInvoice(), 
				form.getReceiver_email(), 
				form.getPayer_email(), 
				form.getFirst_name(), 
				form.getLast_name(), 
				form.getResidence_country(), 
				form.getNum_cart_items(), 
				form.getAddress_city(), 
				form.getPayer_id(), 
				form.getMc_fee(), 
				form.getTxn_id(), 
				form.getCustom(), 
				form.getPayment_date(), 
				form.getPayment_fee(), 
				form.getCharset(), 
				form.getAddress_country_code(), 
				form.getPayment_gross(), 
				form.getAddress_zip(), 
				form.getItem_name1(), 
				form.getIpn_track_id(),
				form.getMc_handling(), 
				form.getMc_handling1(),
				form.getTax(), 
				form.getTax1(),
				form.getAddress_name(), 
				form.getReceiver_id(), 
				form.getVerify_sign(), 
				form.getAddress_country(),
				form.getAddress_status(), 
				form.getPayment_status(),
				form.getProtection_eligibility(), 
				form.getTransaction_subject(), 
				form.getNotify_version(), 
				form.getTxn_type(), 
				form.getMc_gross(), 
				form.getMc_shipping(), 
				form.getPayer_status(),
				form.getMc_currency(), 
				form.getMc_gross_1(), 
				form.getMc_shipping1(), 
				form.getItem_number1(), 
				form.getQuantity1(), 
				form.getAddress_state(), 
				form.getPayment_type(), 
				form.getAddress_street());
	}
	
}
