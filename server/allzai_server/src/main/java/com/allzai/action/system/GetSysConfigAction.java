package com.allzai.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.config.SysConfigForm;
import com.allzai.server.config.SystemConfigServer;
import com.restfb.json.JsonObject;

/**
 * 获取系统配置处理类
 * 
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-10
 * @since   JDK 1.6
 */
public class GetSysConfigAction extends BaseActionSupport 
{
	/** 类指纹 */
	private static final long serialVersionUID = -8090111614472435948L;

	/* (non-Javadoc)
	 * @see com.yeahmobi.hasoffer.action.BaseActionSupport#doAutoAction(com.yeahmobi.hasoffer.bean.InputBean, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception 
	{
		SysConfigForm form = (SysConfigForm) obj;
		
		return SystemConfigServer.getInstance().getSysConfigListForJson(form);
	}

	/* (non-Javadoc)
	 * @see com.yeahmobi.hasoffer.action.BaseActionSupport#getFromBean()
	 */
	public Class<SysConfigForm> getFromBean() 
	{
		// TODO Auto-generated method stub
		return SysConfigForm.class;
	}
	

}
