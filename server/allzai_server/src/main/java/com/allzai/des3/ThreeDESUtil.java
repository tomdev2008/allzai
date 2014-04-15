package com.allzai.des3;

import java.security.InvalidParameterException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class ThreeDESUtil 
{

	private static final String KEY[] = { "16806C5976656F6C",
			"25C9FDAEDA40F86BF71C74292516924A294FC8BA31B6E9EB",	//Constants.index_tk_deocde = 1
			"39128A7698EF4C6D3D252G02F4F79D5815389DF18525D327",	//Constants.index_pw_deocde = 2
			"E056E6B6A4A85EB6C44C74372A0D5DF1AE76405173B3D5ED",
			"536229C8F79831131923F28C5DE32F253E2AF2AD348C4616",
			"0B3915A72F8329A2FE6B691C8AAE1F97ABA8D9D58576AB21",
			"C3C0CD830D92CB3720A14EF4D93B1A133DA4497667F75192",
			"BD427AFB5E19D023150E392F6D3B3EB5B6319120649D31F9",
			"D43F31B008BF257067ABF215E0346E292313C746B3581FB1",
			"630B75BAE0CE2038466704B86D985E1C2557230DDF311ABD",
			"9A629D5DCE91FEE39E9EE9645DF42C3D9DEC2F767C89CEAC",
			"35D9FDAEDA40F86BF71C74292516924A294FC8BA31B6E9EC",
			"49228A7698EF4C6D3D252G02F4F79D5815389DF18525D328",
			"F066E6B6A4A85EB6C44C74372A0D5DF1AE76405173B3D5EE",
			"637229C8F79831131923F28C5DE32F253E2AF2AD348C4617",
			"1B4915A72F8329A2FE6B691C8AAE1F97ABA8D9D58576AB22",
			"D3D0CD830D92CB3720A14EF4D93B1A133DA4497667F75193",
			"CD527AFB5E19D023150E392F6D3B3EB5B6319120649D31F8",
			"E44F31B008BF257067ABF215E0346E292313C746B3581FB2",
			"731B75BAE0CE2038466704B86D985E1C2557230DDF311ABE",
			"8A729D5DCE91FEE39E9EE9645DF42C3D9DEC2F767C89CEAD",
			};

	private static final String CRYPTALGORITHM = "DESede/CBC/PKCS5Padding";

	private static final String CODINGTYPE = "UTF-8";

	private static final byte DEFAULTIV[] = { 1, 2, 3, 4, 5, 6, 7, 8 };

	private static final String KEYALGORITHM = "DESede";

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	public static int get3desKeyLength() {
		return KEY.length;
	}

	public static String Encode(String param, int keyIndex) throws Exception {
		if (keyIndex <= 0 || keyIndex >= KEY.length) {
			throw new InvalidParameterException();
		}

		String key = KEY[keyIndex];
		byte[] byteIV = hex2byte(KEY[0]);
		byte input[] = Hex.decode(key);
		Cipher cipher = Cipher.getInstance(CRYPTALGORITHM);
		DESedeKeySpec desKeySpec = new DESedeKeySpec(input);
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance(KEYALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivGenerator(byteIV));

		byte[] output = cipher.doFinal(param.getBytes(CODINGTYPE));
		String strDesEnc = new String(Base64.encodeBase64(output), CODINGTYPE);
		return strDesEnc;
	}

	public static String Decode(String param, int keyIndex) throws Exception {
		if (keyIndex <= 0 || keyIndex >= KEY.length) {
			throw new InvalidParameterException();
		}
		String key = KEY[keyIndex];
		byte[] byteIV = hex2byte(KEY[0]);
		String reponseDecrpt = decrypt(param, key, byteIV);

		return reponseDecrpt;
	}

	public static String toHexString(byte[] b) { // String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	private static Key keyGenerator(String keyStr) throws Exception {
		byte input[] = Hex.decode(keyStr);
		DESedeKeySpec keySpec = new DESedeKeySpec(input);
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance(KEYALGORITHM);
		return keyFactory.generateSecret(keySpec);
	}

	private static IvParameterSpec ivGenerator(byte b[]) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(b);
		return iv;
	}

	private static byte[] base64Decode(String s) throws Exception {
		return Base64.decodeBase64(s);
	}

	private static String decrypt(String strTobeDeCrypted, String strKey,
			byte byteIV[]) throws Exception {
		byte input[] = base64Decode(strTobeDeCrypted);
		Key k = keyGenerator(strKey);
		IvParameterSpec ivSpec = byteIV.length != 0 ? ivGenerator(byteIV)
				: ivGenerator(DEFAULTIV);
		Cipher c = Cipher.getInstance(CRYPTALGORITHM);
		c.init(2, k, ivSpec);
		byte output[] = c.doFinal(input);
		return new String(output, CODINGTYPE);
	}

	private static byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

}
