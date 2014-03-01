package com.allzai.action.device;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.device.AppUseHistoryForm;
import com.allzai.server.user.AppUseHistoryServer;
import com.restfb.json.JsonObject;

/**
 * 用户APP装卸历史接口<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-10-11
 * @see     AppUseHistoryServer#recordUserAppInfo(AppUseHistoryForm)
 * @since   JDK 1.6
 */
public class InstallReportAction extends BaseActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6130789374169942239L;

	/* (non-Javadoc)
	 * @see com.yeahmobi.gamelala.action.BaseActionSupport#doAutoAction(java.lang.Object, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
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
		return AppUseHistoryForm.class;
	}

}
