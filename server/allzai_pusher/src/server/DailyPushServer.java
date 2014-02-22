package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import util.XMLUtilTool;

public class DailyPushServer {
	
	private static final Logger logger = Logger.getLogger(DailyPushServer.class);
	
	String get_Daily = "http://api.hasoffer.com/hasoffer/public/dailyPush?tech=gcm&action=timer&push_date=%s&push_hour=%s";
	
	private static DailyPushServer server = new DailyPushServer();
	
	public static DailyPushServer getInstance() {
		return server;
	}

	public String doDailyPush() {
		//服务器在+0时区, 美国在-5区	
		String[] time = getDate().split(" ");
		return loadDaily(time[0],  time[1]);
	}

	public static String getDate(){  
		
        Date d = new Date();  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) - 5);  
        //now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + 0);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");  
        
        return sdf.format(now.getTime());  
    }  
	
	private String loadDaily(String date, String hour) {
		
		String msg = null;
		try {
			URL url = new URL(String.format(get_Daily, date, Integer.parseInt(hour)));
			
			logger.info("URL : " + url.toString());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();

			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();

			if (line != null) {
				logger.info("line : " + line);
				msg = XMLUtilTool.dailyStringXmlOut(line);
				logger.info("msg : " + msg);
			}

			br.close();
			isr.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

}
