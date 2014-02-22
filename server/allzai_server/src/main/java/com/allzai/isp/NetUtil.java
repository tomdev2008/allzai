package com.allzai.isp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.servlet.http.HttpServletRequest;

public class NetUtil {
	
	public static boolean checkIpOK(String IP) {
		return IP.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
	}
	
	public static boolean file_backup(File fromfile, File tofile) {
		FileChannel srcChannel = null;
		FileChannel dstChannel = null;
		try {
			srcChannel = new FileInputStream(fromfile).getChannel();
			dstChannel = new FileOutputStream(tofile).getChannel();
			dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
		} catch (Exception e) {
			close_FileReader(srcChannel, dstChannel);
			return false;
		}
		close_FileReader(srcChannel, dstChannel);
		return true;
	}
	
	private static void close_FileReader(FileChannel srcChannel, FileChannel dstChannel) {
		try {
			if(srcChannel != null) {
				srcChannel.close();
			}
			srcChannel = null;
			if(dstChannel != null) {
				dstChannel.close();
			}
			dstChannel = null;
		} catch (IOException e) {
			srcChannel = null;
			dstChannel = null;
		}
	}
	
	public static Long get32IntIpByIp(String ip) {
		Long ipInt = new Long(0L);
		String ps[] = ip.split("\\.");
		if (4 != ps.length) {
			return ipInt;
		} else {
			Long p1 = new Long(ps[0]);
			Long p2 = new Long(ps[1]);
			Long p3 = new Long(ps[2]);
			Long p4 = new Long(ps[3]);
			ipInt = Long.valueOf(p1.longValue() 
					* 256L * 256L * 256L + p2.longValue() 
					* 256L * 256L + p3.longValue() 
					* 256L + p4.longValue());
			return ipInt;
		}
	}
	
	public static String getRemortIP(HttpServletRequest req) {
		String xForwordIp = req.getHeader("x-forwarded-for");
		if (null != xForwordIp) {
			xForwordIp = xForwordIp.trim();
		}
		String clientIp = req.getHeader("client_ip");
		if (null != clientIp) {
			clientIp = clientIp.trim();
		}
		String remoteAddr = req.getRemoteAddr();
		
		return remoteAddr == null 
			? (clientIp == null 
					? (xForwordIp == null ? "127.0.0.1" : xForwordIp)
					: clientIp) 
			: remoteAddr;
    }  

	public static String getStrIpBy32Int(Long k) {
		String ip = "";
		Long p1 = Long.valueOf((k.longValue() & -16777216L) >> 24);
		Long p2 = Long.valueOf((k.longValue() & 16711680L) >> 16);
		Long p3 = Long.valueOf((k.longValue() & 65280L) >> 8);
		Long p4 = Long.valueOf(k.longValue() & 255L);
		ip = (new StringBuilder(String.valueOf(p1.toString())))
			.append(".").append(p2.toString())
			.append(".").append(p3.toString())
			.append(".").append(p4.toString()).toString();
		return ip;
	}

}
