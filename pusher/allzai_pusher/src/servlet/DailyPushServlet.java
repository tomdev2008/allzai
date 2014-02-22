package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.DailyPushServer;

public class DailyPushServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(DailyPushServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		logger.info("do timer : " + System.currentTimeMillis());
		
		String msg = DailyPushServer.getInstance().doDailyPush();
		if(msg == null) {
			return;
		}
		msg = URLEncoder.encode(msg);
		
		request.getRequestDispatcher(String.format("../secure/sendAll?type=%s&push=%s", "push_daily", msg)).forward(request, response);
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		//
		
	}

}
