package com.allzai.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.user.GetOperationForm;
import com.allzai.server.user.UserOperationServer;

/**
 * 获取用户操作列表<p>
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-11
 * @see     UserOperationServer#getOperationListForXml(InputBean)
 * @since   JDK 1.6
 */
@SuppressWarnings("serial")
public class GetOperationListAction extends BaseActionSupport 
{

	@Override
	public Class<GetOperationForm> getFromBean() 
	{
		return GetOperationForm.class;
	}

	@Override
	public String doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception 
	{
		
		GetOperationForm form = (GetOperationForm) obj;
		
		return UserOperationServer.getInstance().getOperationListForJson(form);
	}

}
