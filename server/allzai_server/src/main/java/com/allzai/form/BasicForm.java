package com.yeahmobi.gamelala.form;

import java.io.Serializable;

import com.yeahmobi.gamelala.bean.BaseBean;

/**
 * 映射请求参数的全局Form
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-10-10
 * @since   JDK 1.6
 */
public abstract class BasicForm implements Serializable
{

	/**
	 * BaseForm
	 */
	private static final long serialVersionUID = -9180844218788693078L;

	private int userId;
	
	private String ver;
	
	private String imei;
	
	private String mac;
	
	private String platform;
	
	private String ip;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public abstract BaseBean convertBaseBean();

	/**
	 * @return the ver
	 */
	public String getVer() 
	{
		return ver;
	}

	/**
	 * @param ver the ver to set
	 */
	public void setVer(String ver) 
	{
		this.ver = ver;
	}

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

	
	/**
	 * @return the imei
	 */
	public String getImei() 
	{
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) 
	{
		this.imei = imei;
	}

	@Override
	public String toString() {
		return "BasicForm [userId=" + userId + ", ver=" + ver + ", imei="
				+ imei + ", mac=" + mac + ", platform=" + platform + ", ip="
				+ ip + "]";
	}
	
	
}
