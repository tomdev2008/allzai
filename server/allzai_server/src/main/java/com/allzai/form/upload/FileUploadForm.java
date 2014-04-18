package com.allzai.form.upload;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class FileUploadForm extends BasicForm {

	/**
	 * dhhuang
	 */
	private static final long serialVersionUID = 208152304948294025L;
	
	private String gender;

	@Override
	public String toString() {
		return "FileUploadForm [gender=" + gender + "]";
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public BaseBean convertBaseBean() {
		return null;
	}

}
