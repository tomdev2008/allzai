package com.allzai.cdn;

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

	private String cdn_host = "http://allzai.qiniudn.com/";
	private String bucketName = "allzai";
	private String uptoken = null;
	private PutExtra extra = null;

	public static CdnUtil getInstance() {
		return cdnUtil;
	}

	/**
	 * 初始化cdn的基本配置信息
	 */
	private CdnUtil() {
		try {
			if(uptoken == null || extra == null) {
				
				Config.ACCESS_KEY = "wrzZAJupsxE6d8743LLGCBLLWUMn_RkZL8BzqU4x";
				Config.SECRET_KEY = "Lv2WeGpo3pMJ_UiiyEsb5I8F9Qlb4mhLigBmsjOl";
				
				Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
				PutPolicy putPolicy = new PutPolicy(this.bucketName);
				
				uptoken = putPolicy.token(mac);
				extra = new PutExtra();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将文件上传到cdn, 并获取响应
	 * @param key
	 * @param file
	 * @return
	 */
	private String putFile2Cdn(String key, String file) {
		PutRet ret = IoApi.putFile(this.uptoken, key, file, this.extra);
		return ret.getResponse();
	}
	
	/**
	 * 从cdn获取到已上传文件的路径
	 * @param key
	 * @return
	 */
	private String getFileFromCdn(String key) {
		return this.cdn_host + key;
	}

	/**
	 * @param args
	 * @throws JSONException
	 * @throws AuthException
	 */
	public static void main(String[] args) throws AuthException, JSONException {

		String key = "cdn/abc.1";
		String file = "E:\\图片\\default_gift_logo.png";
		
		System.out.println(CdnUtil.getInstance().putFile2Cdn(key, file));
		System.out.println(CdnUtil.getInstance().getFileFromCdn(key));

	}

}