/**
 * 
 */
package com.seehope.util.json;

import java.util.Arrays;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author kkk
 *
 */
public class JsonUtil {
	private static Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.enableComplexMapKeySerialization();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gson = gsonBuilder.create();
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 转化json时忽略指定字段
	 * @param obj
	 * @param field
	 * @param clazz
	 * @return
	 */
	public static String toJsonIgnoreField(Object obj, final String... fields) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.enableComplexMapKeySerialization();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				boolean isflag = false;
				for (String field : fields) {
					if (f.getName().equals(field)) {
						isflag = true;
					}
				}
				return isflag;
			}

			@Override
			public boolean shouldSkipClass(Class<?> _clazz) {
				return false;
			}
		}).create();
		return gson.toJson(obj);
	}

	/*
	 * public static Object fromJson(String jsonString,Class<?> _class) { return
	 * gson.fromJson(jsonString,_class); }
	 */
	public static <T> T fromJson(String jsonString, Class<T> _class) {
		return gson.fromJson(jsonString, _class);
	}

	public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
		Gson gson = new Gson();
		T[] array = gson.fromJson(json, clazz);
		return Arrays.asList(array);
	}
}
