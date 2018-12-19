package com.seehope.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.entity.Student_info;
import com.seehope.entity.Survey;
import com.seehope.entity.Survey_option;
import com.seehope.entity.Survey_paper;
import com.seehope.service.StudentInfoService;
import com.seehope.service.SurveyOptionService;
import com.seehope.service.SurveyPaperService;
import com.seehope.service.SurveyService;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/survey" })
public class AdminSurveyController extends BaseController {
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private SurveyOptionService surveyOptionService;
	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private SurveyPaperService surveyPaperService;

	@RequestMapping({ "/survey_list" })
	public String get_survey_list() {
		return "admin/survey/survey_list";
	}

	@RequestMapping({ "/survey_add" })
	public String get_survey_add() {
		return "admin/survey/survey-add";
	}

	@RequestMapping({ "/survey_option/{id}" })
	public String get_survey_option(HttpServletRequest request, @PathVariable("id") Integer id) {
		Survey survey = this.surveyService.getSurveyOption(id);
		request.setAttribute("survey", survey);
		return "admin/survey/survey-option";
	}

	@RequestMapping({ "/student_survey_list" })
	public String get_student_survey_list() {
		return "admin/survey/paper/paper-list";
	}

	@RequestMapping({ "/survey_edit" })
	public String get_survey_edit(HttpServletRequest request, Integer id) {
		Survey survey = this.surveyService.getSurveyById(id);
		request.setAttribute("survey", survey);
		return "admin/survey/survey-edit";
	}

	@RequestMapping({ "/survey_paper_list" })
	public String get_survey_paper_list() {
		return "admin/survey/paper/paper-list";
	}

	@RequestMapping({ "/student_user_detail/{id}" })
	public String get_student_user_detail(@PathVariable("id") Integer id, HttpServletRequest request) {
		Student_info info = this.studentInfoService.getStudentInfoById(id);
		request.setAttribute("studentInfo", info);
		return "admin/survey/paper/user-detail";
	}

	@RequestMapping({ "/student_paper_detail/{id}" })
	public String get_student_paper_detail(@PathVariable("id") Integer id, HttpServletRequest request) {
		List<Survey> surveys = this.surveyPaperService.surveyQuestion();
		request.setAttribute("surveys", surveys);
		request.setAttribute("len", Integer.valueOf(surveys.size()));
		List<Survey_option> options = this.surveyPaperService.surveyOptions();
		request.setAttribute("survey_options", options);
		List<Survey_paper> papers = this.surveyPaperService.surveyPapers(id);
		if (papers.size() > 0) {
			request.setAttribute("papers", papers);
		} else {
			request.setAttribute("papers", null);
		}
		return "admin/survey/paper/paper-detail";
	}

	@RequestMapping({ "/list_survey" })
	public String find_survey(HttpServletResponse response, PageRequest pageRequest, String keyText, Integer type) {
		PageResult<Survey> pageResult = this.surveyService.findSurvey(pageRequest, keyText, type);
		writeJson(response, pageResult);
		return null;
	}

	@RequestMapping({ "/survey_delete" })
	public String delete_survey_by_id(HttpServletResponse response, Integer id) {
		ResultVo resultVo = this.surveyService.deleteSurveyById(id);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/update_survey" })
	public String update_survey(HttpServletResponse response, Survey survey) {
		ResultVo resultVo = this.surveyService.updateSurvey(survey);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/add_survey" })
	public String add_survey(HttpServletResponse response, Survey survey) {
		ResultVo resultVo = this.surveyService.addSurvey(survey);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/add_survey_option" })
	public String add_survey_option(HttpServletResponse response, Survey_option survey_option) {
		ResultVo resultVo = this.surveyOptionService.addSurveyOption(survey_option);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/update_option_answer" })
	public String update_option_answer(HttpServletResponse response, Integer id, Integer answer) {
		System.out.println("新修改的答案为" + answer);
		ResultVo resultVo = this.surveyOptionService.UpdateAnswer(id, answer);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/update_option_summary1" })
	public String update_option_summary1(HttpServletResponse response, Integer id, String summary) {
		ResultVo resultVo = this.surveyOptionService.update_option_summary1(id, summary);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/delete_option/{id}" })
	public String delete_option(HttpServletResponse response, @PathVariable("id") Integer id) {
		ResultVo resultVo = this.surveyOptionService.deleteOption(id);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/update_option_summary2" })
	public String update_option_summary2(HttpServletResponse response, String summary, Integer id) {
		ResultVo resultVo = this.surveyOptionService.updateOptionSummary2(id, summary);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/list_student_info" })
	public String list_student_info(HttpServletResponse response, PageRequest pageRequest, String keyText) {
		PageResult<Student_info> pageResult = new PageResult<>(Integer.valueOf(200), "查询成功");
		Page<Student_info> page = this.studentInfoService.findStudentInfos(pageRequest, keyText);
		if (page == null) {
			pageResult.setCode(Integer.valueOf(500));
			pageResult.setMsg("查询失败");
		}
		pageResult.setPage(page);
		writeJson(response, pageResult);
		return null;
	}

	@RequestMapping({ "/delete_student_info_id/{id}" })
	public String delete_student_info_id(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo resultVo = new ResultVo("200", "删除成功");
		try {
			this.studentInfoService.deleteStudentInfoById(id);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("删除失败");
		}
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/is_survey/{id}" })
	public String is_survey(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo vo = new ResultVo("500", "查询失败");
		Student_info info = this.studentInfoService.getStudentInfoById(id);
		if (info.getSurvey().intValue() == 1) {
			vo.setCode("200");
			vo.setMsg("查询成功");
		}
		writeJson(response, vo);
		return null;
	}
}
