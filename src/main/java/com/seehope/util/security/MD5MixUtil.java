package com.seehope.util.security;

import java.io.PrintStream;

public class MD5MixUtil {
	private static String MD5_MIX = "abc";

	public static String md5Mix(String pwd) {
		if (pwd == null) {
			return null;
		}
		String md5Pwd = MD5Util.MD5Encode(MD5_MIX + pwd);
		return MD5Util.MD5Encode(md5Pwd);
	}

	public static void main(String[] args) {
		System.out.println(md5Mix("123456"));
	}
}
