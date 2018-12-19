package com.seehope.util;

import java.math.BigDecimal;

/**
 * 数字类方法。
 */
public class NumberUtil {
	// 字符型字段精准2位小数加
	public static String aAdd(Double value1, String value2) {
		BigDecimal v1 = new BigDecimal(value1);
		BigDecimal v2 = new BigDecimal(value2);
		BigDecimal vret = v1.add(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
		return vret.toString();
	}

	// 字符型字段精准2位小数减
	public static String aSubtract(Double value1, String value2) {
		BigDecimal v1 = new BigDecimal(value1);
		BigDecimal v2 = new BigDecimal(value2);
		BigDecimal vret = v1.subtract(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
		return vret.toString();
	}

	// 字符型字段精准2位小数乘法
	public static String aMultiply(Double value1, String value2) {
		BigDecimal v1 = new BigDecimal(value1);
		BigDecimal v2 = new BigDecimal(value2);
		BigDecimal vret = v1.multiply(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
		return vret.toString();
	}

	// 字符型字段精准2位小数除法
	public static String aDivide(Double value1, String value2) {
		BigDecimal v1 = new BigDecimal(value1);
		BigDecimal v2 = new BigDecimal(value2);
		BigDecimal vret = v1.divide(v2, 2, BigDecimal.ROUND_HALF_UP);
		return vret.toString();
	}

	public static double ToDouble(String value) {
		if (value == null)
			return 0.00F;
		// 处理"，"
		String szTemp = "";
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',')
				szTemp = szTemp + value.charAt(i);
		try {
			return Double.parseDouble(szTemp);
		} catch (NumberFormatException e) {
			return 0.00;
		}
	}

	// 将长整型转换成浮点类型
	public static float toFloat(long value) {
		return value;
	}

	public static float toFloat(Float value) {
		return value == null ? 0 : value;
	}

	// 将字符串转换成浮点类型，在Float.parseFloat方法上加上了对","的处理
	public static float toFloat(String value) {
		if (value == null)
			return 0.00F;
		// 处理"，"
		String szTemp = "";
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',')
				szTemp = szTemp + value.charAt(i);
		try {
			return Float.parseFloat(szTemp);
		} catch (NumberFormatException e) {
			return 0.00F;
		}
	}

	public static String subZeroAndDot(String s) {
		s = StringUtil.defaultString(s);
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	/**
	 * 四舍五入
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static float round(float v, int scale) {
		BigDecimal b = new BigDecimal(Float.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 四舍五入
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double round(double v, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 按最大来计算
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static float ceiling(float v, int scale) {
		BigDecimal b = new BigDecimal(Float.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_CEILING).floatValue();
	}

	public static int toInt(float value) {
		return (int) value;
	}

	public static int toInt(double value) {
		return (int) value;
	}

	public static int toInt(Integer value) {
		return value == null ? 0 : value;
	}

	// 将字符串转换成长整类型，在Long.parseLong方法上加上了对","的处理
	public static int toInt(String value) {
		if (value == null)
			return 0;
		// 处理"，"
		value = value.trim();
		String szTemp = "";
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',')
				szTemp = szTemp + value.charAt(i);
		try {
			return Integer.parseInt(szTemp);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static long toLong(float value) {
		return (long) value;
	}

	// 将字符串转换成长整类型，在Long.parseLong方法上加上了对","的处理
	public static long toLong(String value) {
		if (value == null)
			return 0L;
		// 处理"，"
		value = value.trim();
		String szTemp = "";
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',')
				szTemp = szTemp + value.charAt(i);
		try {
			return Long.parseLong(szTemp);
		} catch (NumberFormatException e) {
			return 0L;
		}
	}
}