package com.seehope.util.file;

import com.seehope.core.global.Global;
import javax.servlet.http.HttpServletRequest;

public class UploadUtil {
	public void uploadfile(String dir, HttpServletRequest request) throws Exception {
		String uploadPath = Global.filePath + "/upload/tmp/";
		UploadFile up = new UploadFile(request, uploadPath);
		up.saveFile();
	}
}
