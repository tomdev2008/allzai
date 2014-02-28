package com.yeahmobi.gamelala.action.device;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeahmobi.gamelala.action.BaseActionSupport;
import com.yeahmobi.gamelala.form.device.AppUseHistoryForm;
import com.yeahmobi.gamelala.server.user.AppUseHistoryServer;

/**
 * 用户APP装卸历史接口<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-10-11
 * @see     AppUseHistoryServer#recordUserAppInfo(AppUseHistoryForm)
 * @since   JDK 1.6
 */
public class AppUseHistoryAction extends BaseActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6130789374169942239L;

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.action.BaseActionSupport#doAutoAction(java.lang.Object, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception 
	{
		AppUseHistoryForm form = (AppUseHistoryForm)obj;
		
		return AppUseHistoryServer.getInstance().recordUserAppInfo(form);
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.action.BaseActionSupport#getFromBean()
	 */
	@Override
	public Class<AppUseHistoryForm> getFromBean() 
	{
		// TODO Auto-generated method stub
		return AppUseHistoryForm.class;
	}

}
