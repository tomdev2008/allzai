package com.allzai.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class CmdUtil {
	
	private static final Logger logger = Logger.getLogger(CmdUtil.class);
	
	/**
	 * 获取脚本执行结果
	 * @param cmd
	 * @return
	 */
	public static boolean execCmdMethod(String path, String api, String query) {
		
		String ret = null;
		if(StringUtil.isEmpty(path) || StringUtil.isEmpty(api)) {return false;}
		
		try {
			String cmd = "sh " + path + " " + api + " " + "\""+query+"\"";
			logger.info("cmd = " + cmd);
			
			Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
			if(process != null) {
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                ret = bufferedreader.readLine();
                
                logger.info("返回内容=" + ret);
                
                bufferedreader.close();  
                process.waitFor(); 
                if (process.waitFor() == 0) {  
                    process.destroy();  
                }  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret == null ? false : "OK".equals(ret.toUpperCase());
	}

}
