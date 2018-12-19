package com.seehope.core.global;

import java.util.Properties;

public class Global {
	public static String webPath;
	public static String filePath;

	public static void load() {
		String strFileName = "/global.properties";
		Properties ps = new Properties();
		try {
			ps.load(Global.class.getResourceAsStream(strFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		webPath = ps.getProperty("web_path");
		filePath = ps.getProperty("file_path");
	}
}
