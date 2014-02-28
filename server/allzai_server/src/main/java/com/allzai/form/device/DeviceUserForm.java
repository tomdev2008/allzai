package com.yeahmobi.gamelala.form.device;

import com.yeahmobi.gamelala.bean.MobileDeviceBean;
import com.yeahmobi.gamelala.form.BasicForm;

public class DeviceUserForm extends BasicForm 
{

	/**
	 * 设备信息
	 */
	private static final long serialVersionUID = 7262032574798564151L;

	@Override
	public String toString() {
		return "DeviceUserForm [ip=" + ip + ", imei=" + imei + ", iccid="
				+ iccid + ", brand=" + brand + ", model=" + model + ", osType="
				+ osType + ", version=" + version + ", market=" + market
				+ ", mac=" + mac + ", carrier=" + carrier + ", androidId="
				+ androidId + ", imsi=" + imsi + ", tel=" + tel + "]";
	}

	private String ip;
	
	private String imei;
	
	private String iccid;
	
	private String brand;
	
	private String model;
	
	private String osType;
	
	private String version;
	
	private String market;
	
	private String mac;
	
	private String carrier;
	
	private String androidId;
	
	private String imsi;
	
	private String tel;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.form.BaseForm#convertBaseBean()
	 */
	@Override
	public MobileDeviceBean convertBaseBean() 
	{
		MobileDeviceBean bean = new MobileDeviceBean();
		bean.setBrand(this.brand);
		bean.setIccid(this.iccid);
		bean.setImei(this.imei);
		bean.setTel(this.tel);
		bean.setAndroidId(this.androidId);
		bean.setImsi(this.imsi);
		bean.setMac(this.mac);
		bean.setModel(this.model);
		bean.setCarrier(this.carrier);
		bean.setOsType(this.osType);
		bean.setVersion(this.version);
		return bean;
	}
	
}
