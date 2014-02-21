package com.allzai.isp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class StartCron extends Thread {

	public static final String filename = "/net-work-file/operator.txt";
	public static final String fileback = "/net-work-file/operator-back.txt";
	
	private static StartCron autoCron = null;

	public static void Init() {
		autoCron = new StartCron();
		autoCron.start();
	}

	public void run() {
		
		reload_NetWork_Info(new File(filename));
		
	}
	
	/**
	 * ��vvcdn����,������SQL,������ݵ�operator.txt�ļ���
	 * @param file_
	 */
	//SQL:select a.ip, a.mask, c.name, d.name from isp_ip a, isp b, isp_network c, isp_region d where a.isp_id = b.id and b.network_id = c.id and b.region_id = d.id;
	public static void reload_NetWork_Info(File file_) {
		if(true) {
			AclTree aclTree = new AclTree();
			FileInputStream in = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				in = new FileInputStream(file_);
				try {
					isr = new InputStreamReader(in, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				br = new BufferedReader(isr);
				try {
					String result = br.readLine();
					while (result != null 
							&& !result.trim().isEmpty()) {
						try {
							String[] Parameter = result.trim().split("\t");
							if(Parameter == null 
									|| Parameter.length != 4) {
								result = br.readLine();
								continue;
							}
							String ip_ = Parameter[0].trim();
							String mask_ = Parameter[1].trim();
							String center_ = Parameter[2].trim() + "_" + Parameter[3].trim();
							if (ip_.isEmpty() 
									|| mask_.isEmpty() 
									|| center_.length() <= 1) {
								result = br.readLine();
								continue;
							}
							Long ipint = aclTree.get32IntIpByIp(ip_);
							aclTree.insert(ipint.toString(), center_, Integer.parseInt(mask_));
							Parameter = null;
						} catch (Exception e) {
							result = br.readLine();
							continue;
						}
						result = br.readLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//HelpUtil.close_StreamReader(br, isr, in);
			TaskService.aclTree = aclTree;
		}
	}
	
}
