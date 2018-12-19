package com.seehope.util.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author zou
 */
public class DownloadUtil {

	/**
	 * 文件下载
	 * @param filename   下载文件名称
	 * @param filepath   文件路径
	 * @param request  
	 * @param response
	 * @throws Exception
	 */
	public static void downloadFile(String filename, String filepath, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getHeader("user-agent").toLowerCase().contains("msie")) {
			filename = URLEncoder.encode(filename, "UTF-8");// 将不安全的文件名改为UTF-8格式
		} else {
			filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 火狐浏览器
		}
		// 设置文件MIME类型
		response.setContentType(request.getServletContext().getMimeType(filename));
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		InputStream in = new FileInputStream(filepath);
		OutputStream out = response.getOutputStream();

		// 写文件
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		out.flush();
		out.close();
		in.close();
	}

}
