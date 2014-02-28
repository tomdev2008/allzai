package com.yeahmobi.gamelala.form.user;

import com.yeahmobi.gamelala.bean.BaseBean;
import com.yeahmobi.gamelala.form.BasicForm;

public class GetOperationForm extends BasicForm {

	@Override
	public String toString() {
		return "GetOperationForm [start=" + start + ", end=" + end
				+ ", packageName=" + packageName + "]";
	}

	/**
	 * dhhaung
	 */
	private static final long serialVersionUID = 7165090684154786701L;

	private int start;

	private int end;
	
	private String packageName;
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.form.BaseForm#convertBaseBean()
	 */
	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}


}
