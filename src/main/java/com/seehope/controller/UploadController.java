package com.seehope.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.core.global.Global;
import com.seehope.util.file.UploadFile;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.UploadResultVo;

@Controller
public class UploadController extends BaseController {
	@RequestMapping({ "/uploadfile" })
	public void uploadfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uploadPath = Global.filePath + "/upload/";
		String uploadUrl = Global.webPath + "/upload/";
		UploadFile up = new UploadFile(request, uploadPath);

		UploadResultVo vo = new UploadResultVo(false, "上传失败", "", "", "");
		up.saveFile();
		if ((up.saveFile != null) && (up.saveFile.exists())) {
			vo = new UploadResultVo(true, "上传成功", up.fileName, uploadUrl + up.fileName, up.oriName);
		}
		writeJson(response, JsonUtil.toJson(vo));
	}

	@RequestMapping({ "/upload_student_excel" })
	public void upload_stu_excel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uploadPath = Global.filePath + "/upload/tmp/";
		String uploadUrl = Global.webPath + "/upload/tmp/";
		UploadFile up = new UploadFile(request, uploadPath);

		UploadResultVo vo = new UploadResultVo(false, "上传失败", "", "", "");
		up.saveFile();
		if ((up.saveFile != null) && (up.saveFile.exists())) {
			vo = new UploadResultVo(true, "上传成功", up.fileName, uploadUrl + up.fileName, up.oriName);
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
}
