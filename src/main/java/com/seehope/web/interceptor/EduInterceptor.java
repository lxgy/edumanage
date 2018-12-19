package com.seehope.web.interceptor;

import com.seehope.core.annotation.ReturnTypeAnnotation;
import com.seehope.core.annotation.ReturnTypeEnum;
import com.seehope.core.global.CodeEnum;
import com.seehope.core.global.Const;
import com.seehope.entity.Seehope_student;
import com.seehope.util.UserSessionUtil;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.ResultVo;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class EduInterceptor extends HandlerInterceptorAdapter {
	private Set<String> m_excludes;
	private static final String PATH_A = "/edu";
	private String[] m_suffixs = Const.RESOURCES;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String currentPath = "/edu";

		HandlerMethod handlerMethod = null;
		ReturnTypeAnnotation returnType = null;

		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI().replaceAll("\\/\\/", "/");
		String[] arrayOfString;
		int j = (arrayOfString = this.m_suffixs).length;
		for (int i = 0; i < j; i++) {
			String suffix = arrayOfString[i];
			if (requestURI.endsWith("." + suffix)) {
				return super.preHandle(request, response, handler);
			}
		}
		if (this.m_excludes != null) {
			for (String ex : this.m_excludes) {
				if (requestURI.equals(contextPath + currentPath + ex)) {
					return super.preHandle(request, response, handler);
				}
			}
		}
		if (handler != null) {
			try {
				handlerMethod = (HandlerMethod) handler;
				returnType = (ReturnTypeAnnotation) handlerMethod.getMethodAnnotation(ReturnTypeAnnotation.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Seehope_student student = UserSessionUtil.getEduStudent(request);
		if (student != null) {
			return true;
		}
		redirectTo(returnType, contextPath + currentPath, requestURI, "��������", request, response);
		return false;
	}

	public void setExcludes(Set<String> excludes) {
		this.m_excludes = excludes;
	}

	void sendRedirectScript(String gotoUrl, HttpServletResponse response) throws IOException {
		String str = "<script>location.href='" + gotoUrl + "';</script>";
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}

	void redirectTo(ReturnTypeAnnotation rta, String path, String requestURI, String msg, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = path + "/login?ref=" + URLEncoder.encode(requestURI, "utf-8");
		if (rta == null) {
			sendRedirectScript(url, response);
		} else if (ReturnTypeEnum.json == rta.value()) {
			ResultVo resultVo = new ResultVo(CodeEnum._900.getCode(), msg);
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(JsonUtil.toJson(resultVo));
			out.close();
		} else {
			sendRedirectScript(url, response);
		}
	}

	public void setM_excludes(Set<String> m_excludes) {
		this.m_excludes = m_excludes;
	}
}
