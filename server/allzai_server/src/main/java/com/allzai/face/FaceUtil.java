package com.allzai.face;

import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class FaceUtil {
	
	private static HttpRequests httpRequests = new HttpRequests("9308b580ef2cb5e7c23a5ee0e737acde", "9giJ71iggljsKLmj3Dstg_Yy2rr9gFU9", true, true);
	
	private static final FaceUtil faceUtil = new FaceUtil();
	
	public static FaceUtil getInstance() {
		return faceUtil;
	}
	
	/**
	 * 获取人脸识别信息
	 * @param path
	 * @return
	 */
	public JSONObject doFace(String path) {
		try {
			return httpRequests.detectionDetect(new PostParameters().setUrl(path));
		} catch (FaceppParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
