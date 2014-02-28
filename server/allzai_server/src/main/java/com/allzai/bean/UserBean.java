package com.allzai.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.allzai.util.Constants;
import com.allzai.util.StringUtil;

/**
 * 用户实体类
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-7
 * @since   JDK 1.6
 */
public class UserBean extends BaseBean implements Comparable<UserBean> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095990524175637696L;

    /** 关键性数据 begin */
	private int id;
	
	private String account;
	private String password;
	/** 关键性数据 end */
	
	//角色
	private int role;
	
	/** 统计累数据 begin */
	private int credit;
	
	private int extendCount;
	
	private int exchangeCount;
	
	/** 统计累数据 end */
	
	private int deviceId;
	
	private int age;
	private int gender;
	private String nickName;
	private String firstName;
	private String lastName;
	private String headPortrait;
	private String headKey;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String contact;
	private String imei;
	
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	private Timestamp createTime;
	private Timestamp updateTime;
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(UserBean o)
	{
		// TODO Auto-generated method stub
		return o.updateTime.compareTo(this.updateTime);
	}
	
	
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

	public String getHeadKey() {
		return headKey;
	}

	public void setHeadKey(String headKey) {
		this.headKey = headKey;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * @return the account
	 */
	public String getAccount() 
	{
		return account;
	}


	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) 
	{
		this.account = account;
	}


	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}


	/**
	 * @return the credit
	 */
	public int getCredit() 
	{
		return credit;
	}


	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) 
	{
		this.credit = credit;
	}


	/**
	 * @return the extendCount
	 */
	public int getExtendCount() 
	{
		return extendCount;
	}


	/**
	 * @param extendCount the extendCount to set
	 */
	public void setExtendCount(int extendCount) 
	{
		this.extendCount = extendCount;
	}


	/**
	 * @return the exchangeCount
	 */
	public int getExchangeCount()
	{
		return exchangeCount;
	}


	/**
	 * @param exchangeCount the exchangeCount to set
	 */
	public void setExchangeCount(int exchangeCount) 
	{
		this.exchangeCount = exchangeCount;
	}


	/**
	 * @return the deviceId
	 */
	public int getDeviceId() 
	{
		return deviceId;
	}


	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(int deviceId)
	{
		this.deviceId = deviceId;
	}


	/**
	 * @return the nickName
	 */
	public String getNickName() 
	{
		return nickName;
	}


	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) 
	{
		this.nickName = nickName;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() 
	{
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() 
	{
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}


	/**
	 * @return the age
	 */
	public int getAge() 
	{
		return age;
	}


	/**
	 * @param age the age to set
	 */
	public void setAge(int age) 
	{
		this.age = age;
	}


	/**
	 * @return the gender
	 */
	public int getGender() 
	{
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) 
	{
		this.gender = gender;
	}


	/**
	 * @return the headPortrait
	 */
	public String getHeadPortrait() 
	{
		return headPortrait;
	}


	/**
	 * @param headPortrait the headPortrait to set
	 */
	public void setHeadPortrait(String headPortrait)
	{
		this.headPortrait = headPortrait;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() 
	{
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}


	/**
	 * @return the address
	 */
	public String getAddress() 
	{
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) 
	{
		this.address = address;
	}


	/**
	 * @return the city
	 */
	public String getCity() 
	{
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}


	/**
	 * @return the state
	 */
	public String getState() 
	{
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) 
	{
		this.state = state;
	}


	/**
	 * @return the country
	 */
	public String getCountry() 
	{
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}


	/**
	 * @return the zipcode
	 */
	public String getZipcode() 
	{
		return zipcode;
	}


	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) 
	{
		this.zipcode = zipcode;
	}


	/**
	 * @return the contact
	 */
	public String getContact() 
	{
		return contact;
	}


	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() 
	{
		return createTime;
	}


	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) 
	{
		this.createTime = createTime;
	}


	/**
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() 
	{
		return updateTime;
	}


	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) 
	{
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", account=" + account + ", password="
				+ password + ", role=" + role + ", credit=" + credit
				+ ", extendCount=" + extendCount + ", exchangeCount="
				+ exchangeCount + ", deviceId=" + deviceId + ", age=" + age
				+ ", gender=" + gender + ", nickName=" + nickName
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", headPortrait=" + headPortrait + ", headKey=" + headKey
				+ ", phone=" + phone + ", address=" + address + ", city="
				+ city + ", state=" + state + ", country=" + country
				+ ", zipcode=" + zipcode + ", contact=" + contact + ", imei="
				+ imei + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}


	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getSaveCondition()
	 */
	@Override
	public Object[] getSaveCondition() 
	{
		Object[] params = new Object[2];

		List<Object> tmpArray = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(175);

		if (!StringUtil.isEmpty(this.account)) 
		{
			sb.append(" account,");
			tmpArray.add(this.account);
		}

		if (!StringUtil.isEmpty(this.password)) 
		{
			sb.append(" password,");
			tmpArray.add(this.password);
		}

		if (this.credit != 0)
		{
			sb.append(" credit,");
			tmpArray.add(this.credit);
		}

		if (this.extendCount != 0) 
		{
			sb.append(" extendCount,");
			tmpArray.add(this.extendCount);
		}

		if (this.exchangeCount != 0) 
		{
			sb.append(" exChangeCount,");
			tmpArray.add(this.exchangeCount);
		}

		if (!StringUtil.isEmpty(this.nickName)) 
		{
			sb.append(" nickName,");
			tmpArray.add(this.nickName);
		}

		if (!StringUtil.isEmpty(this.firstName)) 
		{
			sb.append(" firstName,");
			tmpArray.add(this.firstName);
		}

		if (!StringUtil.isEmpty(this.lastName))
		{
			sb.append(" lastName,");
			tmpArray.add(this.lastName);
		}

		if (this.age != 0) 
		{
			sb.append(" age,");
			tmpArray.add(this.age);
		}

		// 性别: 默认值为0(女) ，1(男)
		if (this.gender != 0) 
		{
			sb.append(" gender,");
			tmpArray.add(this.gender);
		}

		if (!StringUtil.isEmpty(this.headPortrait)) 
		{
			sb.append(" headPortrait,");
			tmpArray.add(this.headPortrait);
		}

		if (!StringUtil.isEmpty(this.phone))
		{
			sb.append(" phone,");
			tmpArray.add(this.phone);
		}

		if (!StringUtil.isEmpty(this.address))
		{
			sb.append(" address,");
			tmpArray.add(this.address);
		}

		if (!StringUtil.isEmpty(this.city))
		{
			sb.append(" city,");
			tmpArray.add(this.city);
		}

		if (!StringUtil.isEmpty(this.state)) 
		{
			sb.append(" state,");
			tmpArray.add(this.state);
		}

		if (!StringUtil.isEmpty(this.country)) 
		{
			sb.append(" country,");
			tmpArray.add(this.country);
		}
		
		if (!StringUtil.isEmpty(this.zipcode)) 
		{
			sb.append(" zipcode,");
			tmpArray.add(this.zipcode);
		}

		if (this.deviceId > 0) 
		{
			sb.append(" deviceId,");
			tmpArray.add(this.deviceId);
		}

		if (this.createTime == null) 
		{
			// createTime = new
			// Timestamp(Calendar.getInstance().getTimeInMillis());
			sb.append(" createTime,");
			tmpArray.add(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		} 
		else 
		{
			sb.append(" createTime,");
			tmpArray.add(createTime);
		}

		if (updateTime != null) 
		{
			sb.append(" updateTime,");
			tmpArray.add(this.updateTime);
		}

		if (tmpArray.size() > 0) 
		{
			params[0] = "INSERT INTO " + this.getTableName() + " ("
					+ sb.substring(0, sb.lastIndexOf(",")) + " ) VALUES "
					+ super.builderValueParam(tmpArray);
			params[1] = tmpArray.toArray();

		}

		return params;

	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getEditCondition()
	 */
	@Override
	public Object[] getEditCondition() 
	{

        Object[] results = new Object[2];
		
        if(this.id > 0)
        {
        	List<Object> tmpArray = new ArrayList<Object>();
    		StringBuilder sb = new StringBuilder(175);
        		
    		if(!StringUtil.isEmpty(this.account))
    		{
    			sb.append(" account = ? ,");
    			tmpArray.add(this.account);
    		}
    		
    		if(!StringUtil.isEmpty(this.password))
    		{
    			sb.append(" password = ? ,");
    			tmpArray.add(this.password);
    		}
    		
    		if(this.credit != 0)
    		{
    			sb.append(" credit = ? ,");
    			tmpArray.add(this.credit);
    		}
    		
    		if(this.extendCount != 0)
    		{
    			sb.append(" extendCount = ? ,");
    			tmpArray.add(this.extendCount);
    		}
    		
    		if(this.exchangeCount != 0)
    		{
    			sb.append(" exChangeCount = ? ,");
    			tmpArray.add(this.exchangeCount);
    		}
    		
    		if(!StringUtil.isEmpty(this.nickName))
    		{
    			sb.append(" nickName = ? ,");
    			tmpArray.add(this.nickName);
    		}
    		
    		if(!StringUtil.isEmpty(this.firstName))
    		{
    			sb.append(" firstName = ? ,");
    			tmpArray.add(this.firstName);
    		}
    		
    		if(!StringUtil.isEmpty(this.lastName))
    		{
    			sb.append(" lastName = ? ,");
    			tmpArray.add(this.lastName);
    		}
    		
    		if(this.age != 0)
    		{
    			sb.append(" age = ? ,");
    			tmpArray.add(this.age);
    		}
    		
    		//性别: 默认值为0(女) ，1(男)
    		
    		sb.append(" gender = ? ,");
    		tmpArray.add(this.gender);
    		
    		if(!StringUtil.isEmpty(this.headPortrait))
    		{
    			sb.append(" headPortrait = ? ,");
    			tmpArray.add(this.headPortrait);
    		}
    		
    		if(!StringUtil.isEmpty(this.phone))
    		{
    			sb.append(" phone = ? ,");
    			tmpArray.add(this.phone);
    		}
    		
    		if(!StringUtil.isEmpty(this.address))
    		{
    			sb.append(" address = ? ,");
    			tmpArray.add(this.address);
    		}
    		
    		if(!StringUtil.isEmpty(this.city))
    		{
    			sb.append(" city = ? ,");
    			tmpArray.add(this.city);
    		}
    		
    		if(!StringUtil.isEmpty(this.state))
    		{
    			sb.append(" state = ? ,");
    			tmpArray.add(this.state);
    		}
    		
    		if(!StringUtil.isEmpty(this.country))
    		{
    			sb.append(" country = ? ,");
    			tmpArray.add(this.country);
    		}
    		
    		if(!StringUtil.isEmpty(this.zipcode))
    		{
    			sb.append(" zipcode = ? ,");
    			tmpArray.add(this.zipcode);
    		}
    		
    		if(this.deviceId > 0)
    		{
    			sb.append(" deviceId = ? ,");
    			tmpArray.add(this.deviceId);
    		}
    		
    		if(createTime != null)
    		{
    			sb.append(" createTime = ? ,");
    			tmpArray.add(this.createTime);
    		}
    		
    		if(updateTime == null)
    		{
    			updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    		}
    		
    		sb.append(" updateTime = ? ,");
			tmpArray.add(this.updateTime);
    		
    		if(tmpArray.size() > 0)
    		{
    			tmpArray.add(this.id);
    			results[0] = "UPDATE " + getTableName() + " SET" + sb.substring(0, sb.lastIndexOf(",")) + " WHERE id = ?";
    			results[1] = tmpArray.toArray();
    		}
        }
		
		return results;
	
	}


	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.bean.BaseBean#getTableName()
	 */
	@Override
	public String getTableName() 
	{
		return Constants.USER_TABLE_NAME;
	}

}
