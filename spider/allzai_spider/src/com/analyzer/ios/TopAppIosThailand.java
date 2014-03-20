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
		
		String country = "̩��";
		String platform = "ƻ��";
		
		FileOutputStream fos = null;
		
		while(len < 500) {
			try {
				
				String date = Constants.getTodayDate(len);
				len++;
				String path = FileDir + date + "_thailand_ios.txt";
				File file = new File(path);
				//�ȼ������ļ���û�д���, ��������˵�����������ڱ������, �����������
				
				System.out.println("�ļ�["+path+"]��ʼ����...");
				if(file.exists() && file.length() > 0) {
					System.out.println("�ļ�["+path+"]�Ѵ���...");
					continue;
				}
				
				fos = new FileOutputStream(file);
				
				List<String> list = Constants.getResultLinesFromUrl(String.format(singapore_, date), 20000L, "GET");
				if(list == null || list.size() <= 0) {
					System.err.println("����:" + country + ", ����:" + date + ", ץȡҳ��ʧ��.");
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

							if(i == 1) {title = "�������";}
							if(i == 2) {title = "��������";}
							if(i == 3) {title = "��������";}
							if(i == 4) {title = "�������";}
							if(i == 5) {title = "���¸���";}
							
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
										
										if(j == 1) {abu.append("����ϵͳ:" + (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										if(j == 2) {abu.append("Size:"      + (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										if(j == 3) {abu.append("���:"      +  (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										if(j == 4) {abu.append("��������:" + (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										//if(j == 5) {abu.append("App�ڹ���:" + (acv.length == 2 ? acv[1] : acv[0]).trim() + "\t");}
										if(j == 6) {abu.append("��װ��:" +    (acv.length == 2 ? acv[1] : acv[0]).trim().replace("��װ", "") + "\t");}
									}
								}
							}
							buffer.append(title + "\t" + "����:" + name + "\t" + "����:" + packageName + "\t" + "������:" + opeart + "\t" + abu.toString() + "\t");
						}
						System.out.println("����:" + country + "\t����:" + date + "\tƽ̨:" + platform + "\t��" + count + "��\t" + buffer.toString());
						fos.write(("����:" + country + "\t����:" + date + "\tƽ̨:" + platform + "\t��" + count + "��\t" + buffer.toString() + "\n").getBytes());
					}
				}
				list = null;
				detail = null;
				System.out.println("�ļ�["+path+"]�����...");
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

		System.err.println("����:" + country + ", ƽ̨:" + platform + ", ��ϲ, ִ�����...");
		
	}

}
