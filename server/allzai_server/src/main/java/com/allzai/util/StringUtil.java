package com.allzai.util;

import java.sql.Timestamp;

public class StringUtil {

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0 || s.trim().isEmpty();
	}

	public static String ensureNotNull(String s) {
		return (s == null || "null".equals(s) ) ? "" : s;
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
