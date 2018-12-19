package com.seehope.web;

import com.seehope.core.global.CommonArray;
import com.seehope.core.global.Global;
import com.seehope.service.SeehopeTeacherService;
import java.io.PrintStream;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class Initialization implements ServletContextAware {
	ServletContext context;
	@Autowired
	SeehopeTeacherService teacherService;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@PostConstruct
	public void init() {
		initContentPath();
	}

	public void initContentPath() {
		if (Global.webPath == null) {
			Global.load();
		}
		if (this.context == null) {
			System.out.println("Initialization not context.");
			return;
		}
		String contextPath = this.context.getContextPath();
		this.context.setAttribute("path", contextPath);

		this.context.setAttribute("path_upload", Global.webPath + "/upload/");

		this.context.setAttribute("commonarray", CommonArray.getInstance());

		this.teacherService.initTeacher();
	}
}
