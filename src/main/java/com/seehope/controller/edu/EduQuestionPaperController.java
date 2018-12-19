package com.seehope.controller.edu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.core.page.Sort;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Stage_question_paper;
import com.seehope.entity.Stage_question_student;
import com.seehope.service.StageQuestionPaperService;
import com.seehope.util.UserSessionUtil;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/edu/question/paper" })
public class EduQuestionPaperController extends BaseController {
	@Autowired
	private StageQuestionPaperService paperService;

	@RequestMapping({ "/question_paper_list" })
	public String question_paper_list(HttpServletRequest request) {
		return "/edu/question/question_paper_list";
	}

	@RequestMapping({ "/question_paper_details/{id}" })
	public String question_list(@PathVariable("id") Integer id, HttpServletRequest request) {
		Stage_question_paper paper = this.paperService.getPaperDetailsById(id);
		request.setAttribute("paper", paper);
		return "/edu/question/question_paper_details";
	}

	@RequestMapping({ "/create_paper" })
	public void create_paper(Integer stage_type, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "创建成功");
		try {
			Stage_question_paper paper = this.paperService.createPaper(stage_type);
			vo.setData(paper);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("创建失败");
		}
		writeJson(response, JsonUtil.toJsonIgnoreField(vo, new String[] { "stage_question", "paper" }));
	}

	@RequestMapping({ "/get_paper_details/{id}" })
	public void get_paper_details(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "查询成功");
		try {
			Stage_question_paper paper = this.paperService.getPaperDetailsById(id);
			vo.setData(paper);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("加载失败");
		}
		writeJson(response, JsonUtil.toJsonIgnoreField(vo, new String[] { "stage_question", "paper" }));
	}

	@RequestMapping({ "/find_student_paper" })
	public void find_student_paper(PageRequest pageRequest, HttpServletResponse response, HttpServletRequest request) {
		PageResult<Stage_question_paper> pr = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			Seehope_student student = UserSessionUtil.getEduStudent(request);
			pageRequest.setSort(new Sort("id", false));
			Page<Stage_question_paper> page = this.paperService.findPaperByStudentId(pageRequest, student.getId());
			pr.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pr.setCode(Integer.valueOf(500));
			pr.setMsg("查询失败");
		}
		writeJson(response, pr);
	}

	@RequestMapping({ "/submit_paper_type" })
	public void submit_paper(Stage_question_paper paper, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "提交成功");
		try {
			Seehope_student student = UserSessionUtil.getEduStudent(request);
			paper = this.paperService.submitPaperType(paper, student);
			vo.setData(paper);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("提交失败");
		}
		writeJson(response, JsonUtil.toJsonIgnoreField(vo, new String[] { "stage_question", "paper" }));
	}

	@RequestMapping({ "/submit_paper/{id}" })
	public void submit_paper(@RequestBody List<Stage_question_student> question_students,
			@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "提交成功");
		try {
			Stage_question_paper paper = this.paperService.submitPaper(question_students, id);
			vo.setData(paper);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("提交失败");
		}
		writeJson(response, JsonUtil.toJsonIgnoreField(vo, new String[] { "stage_question", "paper" }));
	}
}
