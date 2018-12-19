package com.seehope.controller.edu;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;

@Controller
@RequestMapping({ "/edu/question" })
public class EduQuestionController extends BaseController {
	
	@RequestMapping({ "/question_list" })
	public String question_list(HttpServletRequest request) {
		return "/edu/question/question_list";
	}
}
