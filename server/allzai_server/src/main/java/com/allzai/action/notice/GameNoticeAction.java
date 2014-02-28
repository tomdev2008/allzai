package com.allzai.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.notice.GameNoticeForm;
import com.allzai.server.notice.GameNoticeServer;

public class GameNoticeAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		GameNoticeForm form = (GameNoticeForm) obj;
		
		return GameNoticeServer.getInstance().getGameNotice(form);
	}

	@Override
	public Class<GameNoticeForm> getFromBean() {
		return GameNoticeForm.class;
	}

}
