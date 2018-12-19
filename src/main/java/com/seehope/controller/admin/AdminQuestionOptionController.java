package com.seehope.controller.admin;

import com.seehope.controller.BaseController;
import com.seehope.entity.Stage_question_option;
import com.seehope.service.StageQuestionOptionService;
import com.seehope.web.vo.ResultVo;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/admin/question/option" })
public class AdminQuestionOptionController extends BaseController {
	@Autowired
	private StageQuestionOptionService optionService;

	@RequestMapping({ "/save_option" })
	public Object save_option(Stage_question_option option, HttpServletResponse response) {
		ResultVo resultVo = this.optionService.saveOption(option);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/delete_option/{id}" })
	public Object delete_option(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo resultVo = this.optionService.deleteOption(id);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/update_option_summary" })
	public Object update_option_summary(Stage_question_option option, HttpServletResponse response) {
		ResultVo resultVo = this.optionService.updateOptionSummary(option);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/update_option_is_answer" })
	public Object update_option_is_answer(Stage_question_option option, HttpServletResponse response) {
		ResultVo resultVo = this.optionService.updateOptionIsAnswer(option);
		writeJson(response, resultVo);
		return null;
	}
}
