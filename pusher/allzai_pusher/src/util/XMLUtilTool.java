package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @description 解析xml字符串
 */
public class XMLUtilTool {

	/**
	 * @description 将xml字符串转换成map
	 * @param xml
	 * @return Map
	 */
	public static Map<String, String> readStringXmlOut(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator iter = rootElt.elementIterator("config"); // 获取根节点下的子节点config

			// 遍历head节点

			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("key"); // 拿到config节点下的子节点key值
				
				if(!"admin_ip".equals(title)) {
					continue;
				}
				Iterator iters = recordEle.elementIterator("value"); // 获取子节点config下的子节点value

				// 遍历Header节点下的Response节点
				while (iters.hasNext()) {
					Element itemEle = (Element) iters.next();
					
					String[]  ip_ = itemEle.getText().toString().split(";");
					for(String ip : ip_) {
						map.put(ip, null);
					}
				}
				break;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String dailyStringXmlOut(String xml) {
		
		String msg = null;
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator iter = rootElt.elementIterator("msg");
			if (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				msg = recordEle.getTextTrim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public static void main(String[] args) {

		// 下面是需要解析的xml字符串例子

		String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><configs><config><createTime>2013-09-10 16:01:19.0</createTime><updateTime>2013-09-10 08:01:23.0</updateTime><value>3</value><key>eirc_test</key></config><config><createTime>2013-10-18 14:57:49.0</createTime><updateTime>2013-11-25 02:13:55.0</updateTime><value>0</value><key>user_share_credit</key></config><config><createTime>2013-10-19 13:50:04.0</createTime><updateTime>2013-11-25 02:13:35.0</updateTime><value>market://details?id=com.android.hasoffer</value><key>sys_down_url</key></config><config><createTime>2013-09-03 15:55:47.0</createTime><updateTime>2013-09-10 07:55:52.0</updateTime><value>https://play.google.com/store/apps/details?id=com.yeahmobi.hasoffer&amp;referrer=1</value><key>android_refer_url</key></config><config><createTime>2013-11-14 13:38:15.0</createTime><updateTime>2013-11-26 06:00:05.0</updateTime><value>58.246.216.242</value><key>admin_ip</key></config><config><createTime>2013-06-20 00:00:00.0</createTime><updateTime>2013-06-23 18:35:11.0</updateTime><value>Complete Task and Get Credits</value><key>app_list</key></config><config><createTime>2013-06-24 15:01:05.0</createTime><updateTime>2013-06-25 00:26:51.0</updateTime><value>https://play.google.com/store/apps/details?id=com.yeahmobi.hasoffer&amp;referrer=1</value><key>ios_refer_url</key></config><config><createTime>2013-11-23 11:11:54.0</createTime><updateTime>2013-11-23 03:12:02.0</updateTime><value>300</value><key>VERIFY_CODE_VALID_TIME</key></config><config><createTime>2013-10-19 13:49:35.0</createTime><updateTime>2013-11-21 09:26:58.0</updateTime><value>1.0.0</value><key>sys_new_version</key></config><config><createTime>2013-06-24 15:01:37.0</createTime><updateTime>2013-11-28 07:27:19.0</updateTime><value>0</value><key>refer_credit</key></config><config><createTime>2013-11-28 07:26:28.0</createTime><updateTime>2013-11-28 07:26:28.0</updateTime><value>3</value><key>ONESELF_CPA_ACTION_COUNT</key></config><config><createTime>2013-06-15 11:33:22.0</createTime><updateTime>2013-06-19 08:48:45.0</updateTime><value>2</value><key>daily_gift</key></config><config><createTime>2013-10-18 14:57:34.0</createTime><updateTime>2013-11-25 02:14:03.0</updateTime><value>0</value><key>user_share_count</key></config></configs>";

		Map<String, String> map = readStringXmlOut(xmlString);
		System.out.println(map.keySet().toString());

	}

}