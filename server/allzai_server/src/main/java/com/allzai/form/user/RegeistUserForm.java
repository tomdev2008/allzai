package com.allzai.form.user;

import java.io.Serializable;

import com.allzai.bean.UserBean;
import com.allzai.form.BasicForm;

/**
 * 注册用户Form<p>
 * convertUserBean需要调整
 */
public class RegeistUserForm extends BasicForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1180232489751657148L;
	
	//(0:官网, 1:googlePlay 2:facebook 3:twitter 4:未知)
	private int type;
	
	private String name;
	
	private String account;
	
	private String password;
	
	private String firstName;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private String lastName;

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.form.BaseForm#convertBaseBean()
	 */
	@Override
	public UserBean convertBaseBean() 
	{
		UserBean ub = new UserBean();
		ub.setAccount(this.account);
		ub.setPassword(this.password);
		ub.setFirstName(this.firstName);
		ub.setLastName(this.lastName);
		ub.setNickName(this.lastName + " " + this.firstName);
		return ub;
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
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}


	/**
	 * @return the type
	 */
	public int getType() 
	{
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(int type) 
	{
		this.type = type;
	}

	@Override
	public String toString() {
		return "RegeistUserForm [type=" + type + ", name=" + name
				+ ", account=" + account + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
