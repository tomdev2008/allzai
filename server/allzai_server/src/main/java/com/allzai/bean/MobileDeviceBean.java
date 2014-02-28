package com.allzai.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.allzai.util.Constants;
import com.allzai.util.StringUtil;

/**
 * 手机设备实体
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-17
 * @since   JDK 1.6
 */
public class MobileDeviceBean extends BaseBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8167473037803430008L;
	
	private int id;
	
	private String imei;
	
	/** 集成电路卡识别码（固化在手机SIM卡中） ICCID为IC卡的唯一识别号码 */
	private String iccid;
	
	// 商标(手机品牌)
	private String brand;
	
	//型号
	private String model;
	
	// 系统类型 android ios
	private String osType;
	
	//版本
	private String version;
	
	//mac
	private String mac;
	
	private String imsi;
	
	private String carrier;
	
	private String tel;
	
	private String androidId;
	
	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	//完成过hasoffer推广 1 default:0
	private int used;
	
	//应用版本
	private String sdk_ver;
	
	public String getSdk_ver() {
		return sdk_ver;
	}

	public void setSdk_ver(String sdk_ver) {
		this.sdk_ver = sdk_ver;
	}

	//设备状况
	private boolean normal;

	public boolean isNormal() {
		return normal;
	}

	public void setNormal(boolean normal) {
		this.normal = normal;
	}
	
	//运营商
//	private String carrier;
	
	/**
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) 
	{
		this.id = id;
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


	/**
	 * @return the iccid
	 */
	public String getIccid() 
	{
		return iccid;
	}


	/**
	 * @param iccid the iccid to set
	 */
	public void setIccid(String iccid)
	{
		this.iccid = iccid;
	}


	/**
	 * @return the brand
	 */
	public String getBrand() 
	{
		return brand;
	}


	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) 
	{
		this.brand = brand;
	}


	/**
	 * @return the model
	 */
	public String getModel() 
	{
		return model;
	}


	/**
	 * @param model the model to set
	 */
	public void setModel(String model) 
	{
		this.model = model;
	}


	/**
	 * @return the osType
	 */
	public String getOsType() 
	{
		return osType;
	}


	/**
	 * @param osType the osType to set
	 */
	public void setOsType(String osType) 
	{
		this.osType = osType;
	}


	/**
	 * @return the version
	 */
	public String getVersion() 
	{
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(String version)
	{
		this.version = version;
	}
	

	/**
	 * @return the used
	 */
	public int getUsed() 
	{
		return used;
	}


	/**
	 * @param used the used to set
	 */
	public void setUsed(int used) 
	{
		this.used = used;
	}

	@Override
	public String toString() {
		return "MobileDeviceBean [id=" + id + ", imei=" + imei + ", iccid="
				+ iccid + ", brand=" + brand + ", model=" + model + ", osType="
				+ osType + ", version=" + version + ", mac=" + mac + ", imsi="
				+ imsi + ", carrier=" + carrier + ", tel=" + tel
				+ ", androidId=" + androidId + ", used=" + used + ", sdk_ver="
				+ sdk_ver + ", normal=" + normal + "]";
	}


	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getSaveCondition()
	 */
	@Override
	public Object[] getSaveCondition() 
	{
		Object[] params = new Object[2];
		
		List<Object> tmpArray = new ArrayList<Object>();
        
		StringBuilder sb = new StringBuilder(55);
		
		if(!StringUtil.isEmpty(this.imei))
		{
			sb.append(" imei,");
			tmpArray.add(this.imei);
		}
		
		if(!StringUtil.isEmpty(this.tel))
		{
			sb.append(" tel,");
			tmpArray.add(this.tel);
		}
		
		if(!StringUtil.isEmpty(this.androidId))
		{
			sb.append(" androidId,");
			tmpArray.add(this.androidId);
		}
		
		if(!StringUtil.isEmpty(this.iccid))
		{
			sb.append(" iccid,");
			tmpArray.add(this.iccid);
		}
		
		if(!StringUtil.isEmpty(this.imsi))
		{
			sb.append(" imsi,");
			tmpArray.add(this.imsi);
		}
		
		if(!StringUtil.isEmpty(this.brand))
		{
			sb.append(" brand,");
			tmpArray.add(this.brand);
		}
		
		if(!StringUtil.isEmpty(this.model))
		{
			sb.append(" model,");
			tmpArray.add(this.model);
		}
		
		if(!StringUtil.isEmpty(this.carrier))
		{
			sb.append(" carrier,");
			tmpArray.add(this.carrier);
		}
		
		if(!StringUtil.isEmpty(this.osType))
		{
			sb.append(" osType,");
			tmpArray.add(this.osType);
		}
		
		if(!StringUtil.isEmpty(this.sdk_ver))
		{
			sb.append(" sdk_ver,");
			tmpArray.add(this.sdk_ver);
		}
		
		if(!StringUtil.isEmpty(this.mac))
		{
			sb.append(" mac,");
			tmpArray.add(this.mac);
		}

		if(!StringUtil.isEmpty(this.version))
		{
			sb.append(" version,");
			tmpArray.add(this.version);
		}
		
		params[0] = "INSERT INTO " + this.getTableName() + " (" + sb.substring(0, sb.lastIndexOf(",")) + " ) VALUES " + super.builderValueParam(tmpArray);
		params[1] = tmpArray.toArray();
		
		return params;
	
	}


	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getEditCondition()
	 */
	@Override
	public Object[] getEditCondition() 
	{
		Object[] params = new Object[2];
		
		if(this.id > 0 )
		{
			List<Object> tmpArray = new ArrayList<Object>();
	        
			StringBuilder sb = new StringBuilder(55);
			
			if(!StringUtil.isEmpty(this.imei))
			{
				sb.append(" imei = ? ,");
				tmpArray.add(this.imei);
			}
			
			if(!StringUtil.isEmpty(this.tel))
			{
				sb.append(" tel,");
				tmpArray.add(this.tel);
			}
			
			if(!StringUtil.isEmpty(this.androidId))
			{
				sb.append(" androidId,");
				tmpArray.add(this.androidId);
			}
			
			if(!StringUtil.isEmpty(this.iccid))
			{
				sb.append(" iccid = ? ,");
				tmpArray.add(this.iccid);
			}
			
			if(!StringUtil.isEmpty(this.imsi))
			{
				sb.append(" imsi = ? ,");
				tmpArray.add(this.imsi);
			}
			
			if(!StringUtil.isEmpty(this.brand))
			{
				sb.append(" brand = ? ,");
				tmpArray.add(this.brand);
			}
			
			if(!StringUtil.isEmpty(this.model))
			{
				sb.append(" model = ? ,");
				tmpArray.add(this.model);
			}
			
			if(!StringUtil.isEmpty(this.carrier))
			{
				sb.append(" carrier,");
				tmpArray.add(this.carrier);
			}
			
			if(!StringUtil.isEmpty(this.osType))
			{
				sb.append(" osType = ? ,");
				tmpArray.add(this.osType);
			}
			
			if(!StringUtil.isEmpty(this.sdk_ver))
			{
				sb.append(" sdk_ver = ? ,");
				tmpArray.add(this.sdk_ver);
			}
			
			if(!StringUtil.isEmpty(this.mac))
			{
				sb.append(" mac = ? ,");
				tmpArray.add(this.mac);
			}
			
			if(!StringUtil.isEmpty(this.version))
			{
				sb.append(" version = ? ,");
				tmpArray.add(this.version);
			}
			
			sb.append(" used = ? ,");
			tmpArray.add(this.used);

			if(tmpArray.size() > 0)
			{
				tmpArray.add(this.id);
				params[0] = "UPDATE " + this.getTableName() + " SET " + sb.substring(0, sb.lastIndexOf(",")) + " WHERE id = ?";
				params[1] = tmpArray.toArray();
			}
		}
		
		return params;
	
	}


	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getTableName()
	 */
	@Override
	public String getTableName() 
	{
		return Constants.MOBILE_DEVICE_TABLE_NAME;
	}
	
}
