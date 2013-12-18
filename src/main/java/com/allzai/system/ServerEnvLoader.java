package com.allzai.system;

import java.util.Date;

import org.apache.log4j.Logger;

import com.allzai.util.Constants;
import com.allzai.util.TimeHelpUtil;

public class ServerEnvLoader  
{
	private static final Logger logger = Logger.getLogger(ServerEnvLoader.class);
	
	private static String work_date = Constants.sdf_yMd.format(new Date());
	private static String work_month = Constants.sdf_yM.format(new Date());

	public static boolean initServer()
	{
		try 
		{
			//每日统计
			String next_date = TimeHelpUtil.getDateOfDay();
			boolean deal_daily = TimeHelpUtil.getDateCompare(work_date, next_date);
			if(deal_daily) {
				
				//做个0点判断, 避开发布时的重复运算
				
				//两个服务重复的问题?
				
				//处理完成后变更
				work_date = next_date;
			}
			
			//每月统计
			String next_month = TimeHelpUtil.getDateOfMonth();
			boolean deal_month = TimeHelpUtil.getDateCompare(work_month, next_month);
			if(deal_month) {
				//上月的日志文件上传
				
				//处理完成后变更
				work_month = next_month;
			}
			
			//每日计算
			//磁盘不够, 保留一周
			

			//System.out.println(work_date);
		//	System.out.println(work_month);
			
			
			Thread.sleep(Constants.SECOND);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("加载服务环境失败,详细信息如下:", e);
			return false;
		} 
		
		logger.debug("load all data finished;");
		return true;
	}

}
