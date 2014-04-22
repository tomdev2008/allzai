package com.allzai.form.topic;

import com.allzai.bean.BaseBean;
import com.allzai.form.BasicForm;

public class GameTopicForm extends BasicForm {

	/**
	 * 获取游戏通知
	 */
	private static final long serialVersionUID = 1L;
	
	private String packageName;
	
	private int start;
	
	@Override
	public String toString() {
		return "GameNoticeForm [packageName=" + packageName + ", start="
				+ start + ", end=" + end + "]";
	}

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

	private int end;

	@Override
	public BaseBean convertBaseBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
