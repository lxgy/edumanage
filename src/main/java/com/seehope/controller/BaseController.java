package com.seehope.controller;

import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseController {
	protected Log _log = LogFactory.getLog(getClass());
	private String charset = "UTF-8";

	protected void writeJson(HttpServletResponse response, ResultVo vo) {
		writeJson(response, JsonUtil.toJson(vo));
	}

	protected void writeJson(HttpServletResponse response, PageResult<?> pr) {
		writeJson(response, JsonUtil.toJson(pr));
	}

	protected void writeJson(HttpServletResponse response, String context) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(context);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeXml(HttpServletResponse response, String context) {
		try {
			response.setContentType("text/xml");
			response.setCharacterEncoding(this.charset);
			PrintWriter out = response.getWriter();
			out.write(context);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeHtml(HttpServletResponse response, String context) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding(this.charset);
			PrintWriter out = response.getWriter();
			out.write(context);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
