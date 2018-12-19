package com.seehope.util.file;

import java.io.File;
import java.io.FileFilter;

public class FileFilterUtil implements FileFilter {
	String name;

	public FileFilterUtil(String name) {
		this.name = name;
	}

	public boolean accept(File file) {
		boolean ret = false;
		try {
			String getFilename = file.getName();
			if (getFilename.startsWith(this.name)) {
				ret = true;
			}
		} catch (Exception localException) {
		}
		return ret;
	}
}
