package com.allzai.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allzai.action.BaseActionSupport;
import com.allzai.form.config.SysConfigForm;
import com.allzai.server.config.SystemConfigServer;
import com.restfb.json.JsonObject;

/**
 * 获取系统配置处理类
 */
public class GetSysConfigAction extends BaseActionSupport {
	
	/**
	 * 系统配置
	 */
	private static final long serialVersionUID = -8090111614472435948L;

	public JsonObject doAutoAction(Object obj, HttpServletRequest req,
			HttpServletResponse resp) throws Exception 
	{
		SysConfigForm form = (SysConfigForm) obj;
		
		return SystemConfigServer.getInstance().getSysConfigListForJson(form);
	}

	public Class<SysConfigForm> getFromBean() {
		return SysConfigForm.class;
	}
	

}
