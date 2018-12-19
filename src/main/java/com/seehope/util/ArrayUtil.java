package com.seehope.util;

import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
	public static boolean compare(Object[] objs1, Object[] objs2) {
		if (objs1.length != objs2.length) {
			return false;
		}
		for (int i = 0; i < objs1.length; i++) {
			if (!objs1[i].equals(objs2[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean compareIgnoreSort(Object[] objs1, Object[] objs2) {
		Arrays.sort(objs1);
		Arrays.sort(objs2);
		return compare(objs1, objs2);
	}

	public static boolean compareIgnoreSort(List<?> list, Object[] is_answer) {
		return compareIgnoreSort(list.toArray(), is_answer);
	}
}
