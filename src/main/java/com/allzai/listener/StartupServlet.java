package com.allzai.listener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;

public class StartupServlet extends HttpServlet {

	/**
	 * 自启动服务
	 */
	private static final long serialVersionUID = 1L;
	
	{
		//Process process = Runtime.getRuntime().exec(cmd);
		
	
		
	}
	
	public static void main(String[] args) throws IOException {
		
		try {
			URL url = new URL("http://127.0.0.1:8080/");
			URLConnection conn = url.openConnection();
			conn.connect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
