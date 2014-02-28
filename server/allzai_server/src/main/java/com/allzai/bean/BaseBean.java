package com.yeahmobi.gamelala.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author  Eric
 * @version hasoffer-0.0.1, 2013-9-17
 * @since   JDK 1.6
 */
public abstract class BaseBean implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4403683767045800514L;

	/**
	 * 获取新增SQL以及参数
	 * object[0] = sql;
	 * object[1] = params;
	 * @return
	 */
	public abstract Object[] getSaveCondition();
	
	/**
	 * 获取修改SQL以及参数
	 * object[0] = sql;
	 * object[1] = params;
	 * @return
	 */
	public abstract Object[] getEditCondition();
	
	public abstract String getTableName();
	
	protected String builderValueParam(List<Object> tmpArray)
	{
		String tmpStr = "";
		if(tmpArray == null)
		{
			return tmpStr;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tmpArray.size(); i++) 
		{
			sb.append(" ?,");
		}
		tmpStr = '(' + sb.substring(0, sb.length() - 1) +')';
				
		return tmpStr;
		
	}
}
