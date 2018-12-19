package com.seehope.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isEmpty(String str) {
		if ((str != null) && (!"".equals(str))) {
			return false;
		}
		return true;
	}

	public static String[] split(String str) {
		return str.split(",");
	}

	public static String[] split(String str, String splitsign) {
		if ((str == null) || (splitsign == null)) {
			return null;
		}
		return str.split(splitsign);
	}

	public static byte[] toByte(String szStr) {
		if (szStr == null) {
			return null;
		}
		byte[] tmp = szStr.getBytes();
		return tmp;
	}

	public static String array2String(int[] ids, String separator) {
		String ret = "";
		for (int i = 0; i < ids.length; i++) {
			if (i < ids.length - 1) {
				ret = ret + String.valueOf(ids[i]) + separator;
			} else {
				ret = ret + String.valueOf(ids[i]);
			}
		}
		return ret;
	}

	public static String array2String(String[] ids, String separator) {
		String ret = "";
		for (int i = 0; i < ids.length; i++) {
			if (i < ids.length - 1) {
				ret = ret + String.valueOf(ids[i]) + separator;
			} else {
				ret = ret + String.valueOf(ids[i]);
			}
		}
		return ret;
	}

	public static String toString(byte[] byBuf) {
		if (byBuf == null) {
			return null;
		}
		return new String(byBuf);
	}

	public static String toString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();
	}

	public static String toString(String obj) {
		if (obj == null) {
			return "";
		}
		return obj;
	}

	public static String subString(String str, int start, int len) {
		if (str == null) {
			return "";
		}
		return str.substring(start, len);
	}

	public static String subString(String str, int len) {
		return subString(str, 0, len);
	}

	public static List<String> str2List(String urlStr) {
		String URL_SPLIT_PATTERN = "[, ;\r\n]";
		if (urlStr == null) {
			return null;
		}
		String[] urlArray = urlStr.split("[, ;\r\n]");
		List<String> urlList = new ArrayList();
		String[] arrayOfString1;
		int j = (arrayOfString1 = urlArray).length;
		for (int i = 0; i < j; i++) {
			String url = arrayOfString1[i];
			url = url.trim();
			if (url.length() != 0) {
				urlList.add(url);
			}
		}
		return urlList;
	}

	public static String list2Str(List<Integer> list) {
		String str = "";
		if ((list != null) && (list.size() > 0)) {
			for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
				int i = ((Integer) localIterator.next()).intValue();
				str = str + i + ",";
			}
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	public static String toPrice(double d) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(d);
	}

	public static String toPrice(float d) {
		DecimalFormat df = new DecimalFormat("##0.00");
		return df.format(d);
	}

	public static String defaultString(String originalString, String defaultString) {
		if ((originalString == null) || ("".equals(originalString.trim()))) {
			return defaultString;
		}
		return originalString;
	}

	public static String defaultString(String originalString) {
		return defaultString(originalString, "");
	}

	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	public static String replace(String text, String repl, String with, int max) {
		if ((text == null) || (repl == null) || (with == null) || (repl.length() == 0) || (max == 0)) {
			return text;
		}
		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			max--;
			if (max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[��-��]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
}
