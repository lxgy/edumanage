package com.seehope.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController extends BaseController {
	@RequestMapping({ "" })
	public String index() {
		return "/edu/login/user_login";
	}

	@RequestMapping({ "/nofind" })
	public void nofind(HttpServletRequest request) throws Exception {
	}

	@RequestMapping({ "/error" })
	public void error(HttpServletRequest request) throws Exception {
	}
}
