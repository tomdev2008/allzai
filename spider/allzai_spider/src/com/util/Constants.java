package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Constants {
	
	private static List<String> cookieList = null;
	private static List<String> agentList = null;
	
	public static void inintCookieList() {
		
		cookieList = new ArrayList<String>();
		agentList = new ArrayList<String>();
		
		cookieList.add("hsfirstvisit=http%3A%2F%2Fwww.appannie.com%2Fi18n%2Factivate%2Fzh-cn%2F%3Fnext%3D%2Faccount%2Flogout%2F|http%3A%2F%2Fwww.appannie.com%2Fapps%2Fgoogle-play%2Ftop%2Findonesia%2Fgame%2F|1395124126997; __qca=P0-812861148-1395124127567; __unam=8f679e8-144d4069ed2-31b5efde-6; aa_language=cn; django_language=zh-cn; sessionId=\".eJxNjUFKA0EQRccZNbFjUHTrAZLN4MoDJCuVbIIN7pqa7iJp0takUtVKBMFVwKu58gRewEtoIAR3jw_vv_fyjQ8Gtu8g69xlwZWLgcu70VdV2G4CmmWYIVePZVEUSHxozwRFYksOCZqEgY_uS9vTVlxeBtC_4fjDXvz7a8AvkAJ37PULNkCQ1hq91OB9m0nrMQjekiBJ1PiMkzZgGu2crj2HhCt1fo5-4TQ-IZ_4bWaLZg9srOl8V5fV1Y35_PHLtb4aZx_GhnvDKZ9uptzP9S-0QFG7:1WQ7Ba:jswbdrDQnUSAepJaZGDp5QIzdQo\"; csrftoken=9a22505c481d6d32f9cd95ca7e37099d; __utma=143309285.2093703832.1395124127.1395191804.1395199758.5; __utmb=143309285.10.8.1395200033255; __utmc=143309285; __utmz=143309285.1395124127.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=143309285.|2=UserID=249922=1^3=Account%20Type=Platform%20-%20Other=1^4=Account%20Type=Less%20than%205%20employees=1; __hstc=45742459.43cf4601cdd2ed60032a181a2b1d4af0.1395124126999.1395191847274.1395199758322.5; __hssrc=1; __hssc=45742459.3.1395199758322; hubspotutk=43cf4601cdd2ed60032a181a2b1d4af0; __atuvc=11%7C12; __ar_v4=HIOYWRB44ZGAPB3KUNXVWY%3A20140317%3A1%7CT55U6UJPX5AUHHNTMJPY57%3A20140317%3A18%7CPSAAGT4MFBB2ROIM33X7L2%3A20140317%3A20%7C4IJGT2ZDGJFZLAACNH4TVF%3A20140317%3A20%7CBB2N64XFYJHWTKHTLKBWCY%3A20140317%3A1");
		cookieList.add("hsfirstvisit=http%3A%2F%2Fwww.appannie.com%2Fi18n%2Factivate%2Fzh-cn%2F%3Fnext%3D%2Faccount%2Flogout%2F|http%3A%2F%2Fwww.appannie.com%2Fapps%2Fgoogle-play%2Ftop%2Findonesia%2Fgame%2F|1395124126997; __qca=P0-812861148-1395124127567; __unam=8f679e8-144d4069ed2-31b5efde-6; aa_language=cn; django_language=zh-cn; sessionId=\".eJxNzU1KA0EQhuHOTDRxJPh3CUUcPINZadBFsMBdU9NdJE3GmumpaiWC4ErIxsvkJF7AG3gHCURx9_LBw_eWvcbeKRwJiYSGW-okiBLrCkbbzYpipxMDwxp5lnBGMXvIjDGOYw4ji0nnNgl1Nvib9Wdu4OBXEmNVk59k0EcJ_g72tRGbWo9KPvZXcPxPV-gWxB4un6lCxnqpwUmJzjWJtRyj0DULsQQNT3TbeKqvtuIQa-rUujm5hdXwSHHHbS42WfxF3IVi8JWf5Bfnve8P1y71pbBwPy7i4Gwah-_TuJfKHztjYMY:1WQ7Rn:RB8_51MkfsGwC9Q3BYkCv7mgaxU\"; csrftoken=9a22505c481d6d32f9cd95ca7e37099d; __hstc=45742459.43cf4601cdd2ed60032a181a2b1d4af0.1395124126999.1395191847274.1395199758322.5; __hssrc=1; __hssc=45742459.8.1395199758322; hubspotutk=43cf4601cdd2ed60032a181a2b1d4af0; __utma=143309285.2093703832.1395124127.1395191804.1395199758.5; __utmb=143309285.26.9.1395200814575; __utmc=143309285; __utmz=143309285.1395124127.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=143309285.|2=UserID=250291=1^3=Account%20Type=Publisher%20-%20Game=1^4=Account%20Type=Less%20than%205%20employees=1; __atuvc=14%7C12; __ar_v4=G5KB2UD2SZE5XBYOIK5SFG%3A20140318%3A1%7CBB2N64XFYJHWTKHTLKBWCY%3A20140317%3A1%7C4IJGT2ZDGJFZLAACNH4TVF%3A20140317%3A25%7CPSAAGT4MFBB2ROIM33X7L2%3A20140317%3A25%7CT55U6UJPX5AUHHNTMJPY57%3A20140317%3A22%7CHIOYWRB44ZGAPB3KUNXVWY%3A20140317%3A1");
		cookieList.add("hsfirstvisit=http%3A%2F%2Fwww.appannie.com%2Fi18n%2Factivate%2Fzh-cn%2F%3Fnext%3D%2Faccount%2Flogout%2F|http%3A%2F%2Fwww.appannie.com%2Fapps%2Fgoogle-play%2Ftop%2Findonesia%2Fgame%2F|1395124126997; __qca=P0-812861148-1395124127567; __unam=8f679e8-144d4069ed2-31b5efde-6; aa_language=cn; django_language=zh-cn; sessionId=\".eJxNzU1KA0EQhuHOTDRxJPh7CN0MgifQrDToIljgrqnpLpImY830VLUSQcgqkAN5EC_gWSQQxd3LBw_fKvuIvQs4ERIJDbfUSRAl1g2MdpsVxU4nBoY18izhjGL2nBljHMccRhaTzm0S6mzw959fuYGjX0mMVU1-kkEfJfhHONRGbGo9KvnY38DpP12hWxB7uHqjChnrpQYnJTrXJNZyjEJ3LMQSNLzSQ-Opvt2JY6ypU-vm5BZWwwvFPbe92GbxF3EfisF3fpZfn_fsjWuX-l5YeBoXcXA5jcP1NB6k8gcmCF_c:1WQ7XE:YNs3F2YOMQs21xHVw7tYuY4fJ-s\"; csrftoken=9a22505c481d6d32f9cd95ca7e37099d; __hstc=45742459.43cf4601cdd2ed60032a181a2b1d4af0.1395124126999.1395191847274.1395199758322.5; __hssrc=1; __hssc=45742459.13.1395199758322; hubspotutk=43cf4601cdd2ed60032a181a2b1d4af0; __utma=143309285.2093703832.1395124127.1395191804.1395199758.5; __utmb=143309285.41.9.1395201150407; __utmc=143309285; __utmz=143309285.1395124127.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=143309285.|2=UserID=250293=1^3=Account%20Type=VC%20%2F%20PE%20%2F%20Hedge%20Fund%20%2F%20Financial%20Analyst=1^4=Account%20Type=150%20to%20499%20employees=1; __atuvc=16%7C12; __ar_v4=HIOYWRB44ZGAPB3KUNXVWY%3A20140317%3A1%7CT55U6UJPX5AUHHNTMJPY57%3A20140317%3A25%7CPSAAGT4MFBB2ROIM33X7L2%3A20140317%3A29%7C4IJGT2ZDGJFZLAACNH4TVF%3A20140317%3A29%7CBB2N64XFYJHWTKHTLKBWCY%3A20140317%3A1%7CG5KB2UD2SZE5XBYOIK5SFG%3A20140318%3A2");
		cookieList.add("django_language=zh-cn; __qca=P0-160601039-1395201605847; __utma=143309285.1005770718.1395201606.1395201606.1395201606.1; __utmb=143309285.7.9.1395201609203; __utmc=143309285; __utmz=143309285.1395201606.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __ar_v4=%7C4IJGT2ZDGJFZLAACNH4TVF%3A20140318%3A1%7CPSAAGT4MFBB2ROIM33X7L2%3A20140318%3A1%7CG5KB2UD2SZE5XBYOIK5SFG%3A20140318%3A1; __hstc=45742459.126444f69b7e1ca4f5c94baf01d70b5e.1395201629893.1395201629893.1395201629893.1; __hssrc=1; __hssc=45742459.1.1395201629893; hsfirstvisit=https%3A%2F%2Fwww.appannie.com%2Faccount%2Flogin%2F%3Fnext%3D%252Fapps%252Fgoogle-play%252Ftop%252Fchina%252F%253Fdate%253D2014-03-18|http%3A%2F%2Fwww.appannie.com%2Fapps%2Fgoogle-play%2Ftop%2Fchina%2F%3Fdate%3D2014-03-18|1395201629892; hubspotutk=126444f69b7e1ca4f5c94baf01d70b5e; csrftoken=c8597b3d65304b6b2523a0f60f0fbeac; sessionId=\".eJxNjUFqAkEQRXXGKIxIol5CN4MbQXCnq0TiQlLrpqa7wMaxZ3qqOsGA4CroeXKSXCDkKEFQcfd4_Mc_RHtfH0CXidkWrqSKLQs5OULn4hQLVrKoQUdhkLUKTJWy5uX7J67B43VEDrOczCKCBrI1S2hLwSqUBoWMj07Qu6sz1BtyBkYflKHDfCdWc4paF8FJOkemZ8fk2Ip9p9fCUD67FE-YUyVKr0lvlNgt-VifL86Y3MA3IGn9xv14Oon-xrrcyWei4G2e-Ifhyje_Vr4V0n_3lVq8:1WQ7fY:zYR5r49CNfvPBTwMMjxH0tLNTS4\"");
		cookieList.add("django_language=zh-cn; __qca=P0-160601039-1395201605847; hsfirstvisit=https%3A%2F%2Fwww.appannie.com%2Faccount%2Flogin%2F%3Fnext%3D%252Fapps%252Fgoogle-play%252Ftop%252Fchina%252F%253Fdate%253D2014-03-18|http%3A%2F%2Fwww.appannie.com%2Fapps%2Fgoogle-play%2Ftop%2Fchina%2F%3Fdate%3D2014-03-18|1395201629892; sessionId=\".eJxNzUFKA0EQheHOzJjEkaDGS-hmFLyBWWnQRbAgu6amu0iatD3TU9VKBMGVkAN5kFzAO3gDCURx9_Pg471nb7F3DqdMzK4JLXXsWCjIBkb7TbNgJ1MFQ49hkXBBMZtnSikTYg4jjUmWOjF12tm7z22u4PhXUsDak51mUCA7-wBH0rBOrUUhG4sNjP_pGs2KgoWrF6oxoF-LM1yhMU0KUk2Q6TYwBXbinum-seRv9uIEPXWizZLMSot7onhgdhe7LP8i9qEcfOVnRe-6_31p2rW8lhoeJ2UcXMzi8GMWD1P1AzEGYE8:1WQ7hP:Boih7o9k1yS8SXVZP3tILPkZsv0\"; csrftoken=c8597b3d65304b6b2523a0f60f0fbeac; __hstc=45742459.126444f69b7e1ca4f5c94baf01d70b5e.1395201629893.1395201629893.1395201629893.1; __hssrc=1; __hssc=45742459.7.1395201629893; hubspotutk=126444f69b7e1ca4f5c94baf01d70b5e; __utma=143309285.1005770718.1395201606.1395201606.1395201606.1; __utmb=143309285.26.9.1395201776456; __utmc=143309285; __utmz=143309285.1395201606.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=143309285.|2=UserID=250293=1^3=Account%20Type=VC%20%2F%20PE%20%2F%20Hedge%20Fund%20%2F%20Financial%20Analyst=1^4=Account%20Type=150%20to%20499%20employees=1; __atuvc=3%7C12; __ar_v4=T55U6UJPX5AUHHNTMJPY57%3A20140318%3A5%7C4IJGT2ZDGJFZLAACNH4TVF%3A20140318%3A7%7CPSAAGT4MFBB2ROIM33X7L2%3A20140318%3A7%7CG5KB2UD2SZE5XBYOIK5SFG%3A20140318%3A2");
		
		agentList.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
		agentList.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.149 Safari/537.36");
		agentList.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");
		agentList.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1");
		
	}

	public static List<String> getResultLinesFromUrl(String urlStr, long skipLen, String mothed) {
		
		if(cookieList == null) {inintCookieList();}
		if(cookieList.size() <= 0) {return new ArrayList<String>(0);}
		
		try {
			Thread.sleep(30000 + (new Random().nextInt(30000)));
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		List<String> resultLines = new ArrayList<String>(3000);
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlStr);
			
			int index_cookie  = new Random().nextInt(cookieList.size());
			int index_agent  = new Random().nextInt(agentList.size());
			System.out.println("使用cookie="+index_cookie + ", 使用agent="+index_agent + ", 列表url=" + urlStr);
			
			connection = (HttpURLConnection) url.openConnection();  
			
			connection.setRequestProperty("User-agent", agentList.get(index_agent));
			connection.setRequestProperty("Cookie", cookieList.get(index_cookie));
			
			connection.setDoInput(true);
			connection.setRequestMethod(mothed);
			connection.setReadTimeout(30000);
			connection.setInstanceFollowRedirects(false);
			
			connection.connect();
			
			InputStream is = connection.getInputStream();
			// 读取偏移位
			is.skip(skipLen);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				if (!strLine.trim().isEmpty()) {
					
					strLine = strLine.trim();
					strLine =  new String(strLine.getBytes("gbk"), "utf-8");
					
					resultLines.add(strLine);
				}
			}
			br.close();
			isr.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

		return resultLines;
	}
	
	public static List<String> getDetailLinesFromUrl(String urlStr, long skipLen, String mothed) {

		try {
			Thread.sleep(30000 + (new Random().nextInt(30000)));
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		List<String> resultLines = new ArrayList<String>(1000);
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlStr);

			int index_cookie  = new Random().nextInt(cookieList.size());
			int index_agent  = new Random().nextInt(agentList.size());
			System.out.println("使用cookie:"+index_cookie + ", 使用agent:"+index_agent + ", 详情url:" + urlStr);
			
			connection = (HttpURLConnection) url.openConnection();  
			
			connection.setRequestProperty("User-agent", agentList.get(index_agent));
			connection.setRequestProperty("Cookie", cookieList.get(index_cookie));			
			connection.setDoInput(true);
			connection.setRequestMethod(mothed);
			connection.setReadTimeout(30000);
			connection.setInstanceFollowRedirects(true);
			
			connection.connect();

			InputStream is = connection.getInputStream();
			// 读取偏移位
			is.skip(skipLen);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				if (!strLine.trim().isEmpty()) {
					
					strLine = strLine.trim();
					strLine =  new String(strLine.getBytes("gbk"), "utf-8");
					
					resultLines.add(strLine);
				}
			}
			br.close();
			isr.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return resultLines;
	}
	
	public static String getTodayDate(int diffDay){  
		
        Date d = new Date();  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - diffDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        
        return sdf.format(now.getTime());  
    } 
	
}
