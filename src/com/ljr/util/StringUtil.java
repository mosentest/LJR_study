package com.ljr.util;

public class StringUtil {
	public static boolean isEmpty(String msg) {
		if (null == msg || "".equals(msg)) {
			return true;
		}
		return false;
	}
}
