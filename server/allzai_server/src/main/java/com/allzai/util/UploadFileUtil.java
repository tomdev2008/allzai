package com.yeahmobi.gamelala.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UploadFileUtil {

	public static String getFileMD5String(File file) throws IOException, NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messageDigest.update(byteBuffer);
		ch.close();
		in.close();
		return byteArrayToHex(messageDigest.digest());
	}

	private static String byteArrayToHex(byte[] byteArray) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] resultCharArray = new char[byteArray.length * 2];
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		return new String(resultCharArray);
	}

	public static String getFileStorePath() {
		String path = System.getProperty("user.dir").replace("bin", "upload").replace("\\", "/") + "/store/";
		File file = new File(path);
		if (!file.exists()) {file.mkdirs();}
		return path;
	}
	
	public static String getFileTempPath() {
		String path = System.getProperty("user.dir").replace("bin", "upload").replace("\\", "/") + "/temp/";
		File file = new File(path);
		if (!file.exists()) {file.mkdirs();}
		return path;
	}
	
	public static boolean doFileCompress(String srcFileDir, String tarFileDir, String srcFileName, String tarFileName) {
		FileCompressUtil mypic = new FileCompressUtil();
		mypic.doFileCompress(srcFileDir, tarFileDir, srcFileName, tarFileName, Constants.FileOutputWidth, Constants.FileOutputHeight, false);
		return new File(tarFileDir + tarFileName).exists();
	}

}