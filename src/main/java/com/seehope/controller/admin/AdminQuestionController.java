package com.seehope.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.page.PageRequest;
import com.seehope.core.page.Sort;
import com.seehope.entity.Stage_question;
import com.seehope.service.StageQuestionService;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/question" })
public class AdminQuestionController extends BaseController {
	@Autowired
	private StageQuestionService questionService;

	@RequestMapping({ "/question_list" })
	public String question_list(HttpServletRequest request) {
		return "/admin/question/question_list";
	}

	@RequestMapping({ "/question_edit/{id}" })
	public String question_edit(@PathVariable("id") Integer id, HttpServletRequest request) {
		Stage_question question = this.questionService.getQuestionById(id);
		request.setAttribute("question", question);
		return "/admin/question/question_edit";
	}

	@RequestMapping({ "/question_add" })
	public String question_add(HttpServletRequest request) {
		return "/admin/question/question_add";
	}

	@RequestMapping({ "/question_detail/{id}" })
	public String question_detail(@PathVariable("id") Integer id, HttpServletRequest request) {
		Stage_question question = this.questionService.getQuestionOptionById(id);
		request.setAttribute("question", question);
		return "/admin/question/question_detail";
	}

	@RequestMapping({ "/list_question" })
	public void list_question(PageRequest pageRequest, String keyText, Integer stage_type, Integer type, Integer level,
			HttpServletResponse response) {
		pageRequest.setSort(new Sort("id", false));
		PageResult<Stage_question> pageResult = this.questionService.findQuestions(pageRequest, keyText, type,
				stage_type, level);
		System.out.println(JsonUtil.toJson(pageResult));
		writeJson(response, pageResult);
	}

	@RequestMapping({ "/add_question" })
	public String add_question(Stage_question question, HttpServletResponse response) {
		ResultVo resultVo = this.questionService.addQuestion(question);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/delete_question/{id}" })
	public String delete_question(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo resultVo = this.questionService.delete_question(id);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/update_question" })
	public String update_question(Stage_question question, HttpServletResponse response) {
		ResultVo resultVo = this.questionService.updateQuestion(question);
		writeJson(response, resultVo);
		return null;
	}
}
