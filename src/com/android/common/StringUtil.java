package com.android.common;

public final class StringUtil {
	private StringUtil() {
	}

	public static boolean isNullOrEmpty(String str) {
		if (null == str || str.length() == 0)
			return true;
		return false;
	}

	public static boolean isNullOrWhiteSpace(String str) {
		if (null == str || str.length() == 0 || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
