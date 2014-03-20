package com.analyzer.ios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.Constants;

public class TopAppIosThailand {

	private static final String singapore_ = "http://www.appannie.com/apps/ios/top/thailand/?date=%s&device=iphone";
	private static final Pattern patternApp = Pattern.compile("<td class=\"app free\">.*</td>");
	private static final Pattern patternInfo = Pattern.compile(".*<p><b>.*</b>.*</p>.*");
	
	private static final String FileDir = "/home/ubuntu/app_spider_file/thailand_ios/";
	private static int len = 0;
	
	public static void main(String[] args) {
		
		String country = "泰国";
		String platform = "苹果";
		
		FileOutputStream fos = null;
		
		while(len < 500) {
			try {
				
				String date = Constants.getTodayDate(len);
				len++;
				String path = FileDir + date + "_thailand_ios.txt";
				File file = new File(path);
				//先检查这个文件有没有存在, 若存在则说明可能是日期变更引起, 跳过这个日期
				
				System.out.println("文件["+path+"]开始处理...");
				if(file.exists() && file.length() > 0) {
					System.out.println("文件["+path+"]已存在...");
					continue;
				}
				
				fos = new FileOutputStream(file);
				
				List<String> list = Constants.getResultLinesFromUrl(String.format(singapore_, date), 20000L, "GET");
				if(list == null || list.size() <= 0) {
					System.err.println("国家:" + country + ", 日期:" + date + ", 抓取页面失败.");
					continue;
				}
				
				int count = 0;
				String s = null;
				String name = null;
				String packageName = null;
				List<String> detail = null;
				StringBuffer buffer = null;
				String[] strs = null;
				String str = null;
				Matcher mFree = null;
				String opeart = null;
				String title = null;
				StringBuffer abu = null;
				String about_str = null;
				String[] about_strs = null;
				
				for(String line : list) {
					mFree = patternApp.matcher(line);
					if(mFree.matches()) {
						count++;
						str = mFree.group(0);

						buffer = new StringBuffer();
						strs = str.split("<div class=\"main-info\"><span title=\"");

						for(int i = 1; i<strs.length;i++) {
							s = strs[i];
							opeart = s.substring(s.indexOf("</span><span title=") + "</span><span title=".length(), s.indexOf(" class=\"oneline-info add-info\" data-items=\"1")).replace("\"", "");
							name = s.substring(0, s.indexOf("\"")).replace(" class=", "");
							packageName = s.substring(s.indexOf("/apps/google-play/app/") + "/apps/google-play/app/".length(), s.lastIndexOf(name) - 3);

							if(i == 1) {title = "免费排行";}
							if(i == 2) {title = "付费排行";}
							if(i == 3) {title = "畅销排行";}
							if(i == 4) {title = "最新免费";}
							if(i == 5) {title = "最新付费";}
							
							detail = Constants.getDetailLinesFromUrl("http://www.appannie.com/apps/google-play/app/" + packageName, 20000L, "GET");
							for(String about : detail) {
								mFree = patternInfo.matcher(about);
								if(mFree.matches()) {
									about_str = mFree.group(0);
									abu = new StringBuffer();
									about_strs = about_str.split("<p><b>");
									
									for(int j = 1; j < about_strs.length; j++) {
										about_str = about_strs[j].replace("</b>", "").replace("</p>", "").replace("</div>", "").replace("/p>", "");
										String[] acv = about_str.split(":");
										
										if(j == 1) {abu.append("操作系统:" + (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										if(j == 2) {abu.append("Size:"      + (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										if(j == 3) {abu.append("类别:"      +  (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										if(j == 4) {abu.append("内容评级:" + (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										//if(j == 5) {abu.append("App内购买:" + (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										if(j == 6) {abu.append("安装量:" +    (acv.length == 2 ? acv[1] : acv[0]).trim().replace("安装", "") + "\t");}
									}
								}
							}
							buffer.append(title + "\t" + "名称:" + name + "\t" + "包名:" + packageName + "\t" + "发行商:" + opeart + "\t" + abu.toString() + "\t");
						}
						System.out.println("国家:" + country + "\t日期:" + date + "\t平台:" + platform + "\t第" + count + "名\t" + buffer.toString());
						fos.write(("国家:" + country + "\t日期:" + date + "\t平台:" + platform + "\t第" + count + "名\t" + buffer.toString() + "\n").getBytes());
					}
				}
				list = null;
				detail = null;
				System.out.println("文件["+path+"]已完成...");
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			} finally {
				if(fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		System.err.println("国家:" + country + ", 平台:" + platform + ", 恭喜, 执行完毕...");
		
	}

}
