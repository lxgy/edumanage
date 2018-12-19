package com.seehope.controller.edu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.entity.Survey;
import com.seehope.entity.Survey_option;
import com.seehope.entity.vo.StudentPaperVo;
import com.seehope.service.StudentInfoService;
import com.seehope.service.SurveyPaperService;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/user/survey" })
public class EduSurveyController extends BaseController {
	@Autowired
	private SurveyPaperService surveyPaperService;
	@Autowired
	private StudentInfoService studentInfoService;

	@RequestMapping({ "/survey_paper/{id}" })
	public String get_survey_paper(@PathVariable("id") Integer id, HttpServletRequest request) {
		request.setAttribute("sid", id);
		List<Survey> surveys = this.surveyPaperService.surveyQuestion();
		request.setAttribute("surveys", surveys);
		request.setAttribute("len", Integer.valueOf(surveys.size()));
		List<Survey_option> survey_options = this.surveyPaperService.surveyOptions();
		request.setAttribute("survey_options", survey_options);
		return "edu/survey/survey-list";
	}

	@RequestMapping({ "/is_survey/{id}" })
	public String is_survey(HttpServletResponse response, @PathVariable("id") Integer id) {
		System.out.println("id值为...." + id);
		ResultVo resultVo = this.studentInfoService.isSurvey(id);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/student_survey" })
	public String student_survey(HttpServletResponse response, StudentPaperVo studentPaperVo) {
		ResultVo resultVo = new ResultVo("500", "提交失败");
		boolean result = this.surveyPaperService.studentSurvey(studentPaperVo);
		if (result) {
			resultVo.setCode("200");
			resultVo.setMsg("提交成功");
		}
		writeJson(response, resultVo);
		return null;
	}
}
