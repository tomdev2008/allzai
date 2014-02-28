package com.yeahmobi.gamelala.util;

import java.sql.Timestamp;

public class StringUtil {

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static String ensureNotNull(String s) {
		return (s == null || "null".equals(s) ) ? "" : s;
	}

	public static int toInt(String s, int defaultValue) {
		if (!isEmpty(s)) {
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
			}
		}
		return defaultValue;
	}

	public static double toDouble(String s, double defaultValue) {
		if (!isEmpty(s)) {
			try {
				return Double.parseDouble(s);
			} catch (NumberFormatException e) {
			}
		}
		return defaultValue;
	}

	public static Timestamp toTimestamp(String time, int defaultValue) {
		if (!isEmpty(time)) {
			try {
				return Timestamp.valueOf(time);
			} catch (IllegalArgumentException e) {
			}
		}
		return new Timestamp(defaultValue);
	}

}
