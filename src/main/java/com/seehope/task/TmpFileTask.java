package com.seehope.task;

import com.seehope.core.global.Global;
import com.seehope.util.date.DateTimeUtil;
import java.io.File;
import java.io.PrintStream;
import org.springframework.stereotype.Component;

@Component("tmpFileTask")
public class TmpFileTask {
	public void cleanTmpFile() {
		String tmpPath = Global.filePath + "/upload/tmp/";
		File dir = new File(tmpPath);
		File[] files = dir.listFiles();
		File[] arrayOfFile1;
		int j = (arrayOfFile1 = files).length;
		for (int i = 0; i < j; i++) {
			File file = arrayOfFile1[i];
			if (file.isFile()) {
				file.delete();
			}
		}
		System.out.println("��������������������" + DateTimeUtil.getCurDateTime());
	}
}
