package com.allzai.isp;

public class TaskService {

	public static AclTree getAclTree() {
		return aclTree;
	}

	public static String getIpInfo(String ip) {
		String ipInfo = getAclTree().searchISP(ip.trim());
		return ipInfo;
	}
	
	public static AclTree aclTree = new AclTree();

}
