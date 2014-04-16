package com.allzai.form;

import java.io.Serializable;

import com.allzai.bean.BaseBean;

/**
 * 映射请求参数的全局Form
 */
public abstract class BasicForm implements Serializable
{

	/**
	 * BaseForm
	 */
	private static final long serialVersionUID = -9180844218788693078L;

	private int userId;
	
	private String platform;
	
	private String ip;
	
	private String area;
	
	private String country;
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	private String imei;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public abstract BaseBean convertBaseBean();

	/**
	 * @return the platform
	 */
	public String getPlatform() 
	{
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) 
	{
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "BasicForm [userId=" + userId + ", platform=" + platform
				+ ", ip=" + ip + ", area=" + area + ", country=" + country
				+ ", imei=" + imei + "]";
	}
	
	
}
