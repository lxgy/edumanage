package com.seehope.util.security;


import org.apache.commons.codec.binary.Base64;

/**
 * 用于转码操作，以实现简单加密。涉及中文问题，请用 encode 与 decode 进行加密与解密。
 */
public class Base64Util {
	private static final String s_keys = "-_wR6OcdHIJQ7ijpfykT123UvKSPABClm89nozxeDE45FGVqrstuWXYZaN0LMbgh";

	public Base64Util() {
	}

	/**
	 * 把字符串转换成GBK编码的字节加密，以解决汉中文问题。
	 * 
	 * @param unencoded
	 */
	public static String encode(String unencoded) throws Exception {
		return new String(Base64.encodeBase64(unencoded.getBytes()));
		// return new BASE64Encoder().encode(unencoded.getBytes());
	}

	/**
	 * 与 encode（）对应，解码字符串
	 * 
	 * @param encoded
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encoded) throws Exception {
		return new String(Base64.decodeBase64(encoded.getBytes()));
		// return new String(new BASE64Decoder().decodeBuffer(encoded));
	}

	/**
	 * base64 加密码方法
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String str) throws Exception {
		if (str == null)
			throw new Exception("NULL pointer.");

		int len = str.length();
		if (len == 0)
			return str;

		StringBuffer pTmp = new StringBuffer(len);
		pTmp = pTmp.append(str);
		StringBuffer dest = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char ch = pTmp.charAt(i);
			int idx1 = ch >> 2 & 0x3f;
			int idx2 = ch << 4 & 0x30;
			dest.append(s_keys.charAt(idx1));
			if (++i == len) {
				dest.append(s_keys.charAt(idx2));
				break;
			}
			ch = pTmp.charAt(i);
			idx1 = idx2 | ch >> 4 & 0xf;
			idx2 = ch << 2 & 0x3f;
			dest.append(s_keys.charAt(idx1));
			if (++i == len) {
				dest.append(s_keys.charAt(idx2));
				break;
			}
			ch = pTmp.charAt(i);
			idx1 = idx2 | ch >> 6 & 0x3;
			idx2 = ch & 0x3f;
			dest.append(s_keys.charAt(idx1));
			dest.append(s_keys.charAt(idx2));
		}
		return dest.toString();
	}

	/**
	 * base64解密方法
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String str) throws Exception {
		if (str == null)
			throw new Exception("NULL pointer.");
		int len = str.length();
		if (len == 0)
			return str;

		StringBuffer dest = new StringBuffer();
		for (int j = 0; j < len; j++) {
			char ch = str.charAt(j);
			int i;
			for (i = 0; i < 64; i++)
				if (s_keys.charAt(i) == ch)
					break;

			char tempDest = (char) (i << 2);
			if (++j == len) {
				dest.append(tempDest);
				break;
			}
			ch = str.charAt(j);
			for (i = 0; i < 64; i++)
				if (s_keys.charAt(i) == ch)
					break;

			dest.append(tempDest |= i >> 4);
			int temp = (i & 0xf) << 4;
			if (++j == len)
				break;
			ch = str.charAt(j);
			for (i = 0; i < 64; i++)
				if (s_keys.charAt(i) == ch)
					break;

			dest.append((char) (temp | i >> 2));
			temp = (i & 0x3) << 6;
			if (++j == len)
				break;
			ch = str.charAt(j);
			for (i = 0; i < 64; i++)
				if (s_keys.charAt(i) == ch)
					break;

			dest.append((char) (temp | i));
		}

		return dest.toString();

	}

	// test
	public static void main(String args[]) throws Exception {
		System.out.println("ORIGIN:  123456");
		String e = encrypt("123456");
		System.out.println("ENCRYPT: " + e);
		String d = decrypt(e);
		System.out.println("DECRYPT: " + d);
	}
}
