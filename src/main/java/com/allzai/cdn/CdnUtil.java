package com.allzai.cdn;

import java.util.Properties;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

public class CdnUtil {
	
	private static volatile CdnUtil cdnUtil = new CdnUtil();
	
	private static final Properties cdn_Properties = new Properties();
	private static final String cdn_CONFIG_FILE = "/cdn.properties";

	private String cdn_host = "http://allzai.qiniudn.com/";
	private String bucketName = "allzai";
	private String storeName = "cdn/";
	private String uptoken = null;
	private PutExtra extra = null;

	public static CdnUtil getInstance() {
		return cdnUtil;
	}

	private CdnUtil() {
		try {
			cdn_Properties.load(CdnUtil.class.getResourceAsStream(cdn_CONFIG_FILE));
			
			Config.ACCESS_KEY = cdn_Properties.getProperty("accessKey");
			Config.SECRET_KEY = cdn_Properties.getProperty("secretKey");
			
			Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
			
			PutPolicy putPolicy = new PutPolicy(this.bucketName);
			
			uptoken = putPolicy.token(mac);
			extra = new PutExtra();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String putFile2Cdn(String key, String file) {
		PutRet ret = IoApi.putFile(this.uptoken, key, file, this.extra);
		System.out.println(ret.getResponse());
		return this.cdn_host + this.storeName + key;
	}

	/**
	 * @param args
	 * @throws JSONException
	 * @throws AuthException
	 */
	public static void main(String[] args) throws AuthException, JSONException {

		String key = "abc.def";
		String file = "E:\\图片\\2004050408302426.gif";
		
		System.out.println(CdnUtil.getInstance().putFile2Cdn(key, file));

	}

}