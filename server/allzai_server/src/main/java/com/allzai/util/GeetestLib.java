package com.allzai.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeetestLib {
	private String privateKey;
	public GeetestLib(String privateKey) {
		this.privateKey=privateKey;
	}
    public boolean validate(String challenge, String validate, String seccode){
    	String host="api.geetest.com";
    	String path="/validate.php";
    	int port=80;
    	if(validate.length()>0 && checkResultByPrivate(challenge,validate)){
    		String query="seccode="+seccode;
    		String response = "";
			try {
				response = postValidate(host, path, query, port);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		if(response.equals(md5Encode(seccode))){
    			return true;
    		}
    	}
    	return false;
    	
    }
    private boolean checkResultByPrivate(String origin, String validate){
    	String encodeStr=md5Encode(privateKey+"geetest"+origin);
    	return validate.equals(encodeStr);
    }
    private String postValidate(String host, String path, String data, int port) throws Exception{
    	String response="error";
    	//data=fixEncoding(data);
        InetAddress addr = InetAddress.getByName(host);
        Socket socket = new Socket(addr, port);
        BufferedWriter wr = new BufferedWriter(
        		new OutputStreamWriter(socket.getOutputStream(),"UTF8"));
        wr.write("POST " + path + " HTTP/1.0\r\n");
        wr.write("Host: "+host+"\r\n");
        wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
        wr.write("Content-Length: " + data.length() + "\r\n");
        wr.write("\r\n"); // 浠ョ┖琛屼綔涓哄垎鍓�        // 鍙戦�鏁版嵁
        wr.write(data);
        wr.flush();
        // 璇诲彇杩斿洖淇℃伅
        BufferedReader rd = new BufferedReader(
        		new InputStreamReader(socket.getInputStream(),"UTF-8"));
        String line;
        while ((line = rd.readLine()) != null) {
          System.out.println(line);
          response=line;
        }
        wr.close();
        rd.close();
        return response;
    }

    //md5 鍔犲瘑
    public String md5Encode(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
 
            re_md5 = buf.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

   
}
