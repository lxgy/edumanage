package com.seehope.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.core.page.Sort;
import com.seehope.entity.Stage_question_paper;
import com.seehope.service.StageQuestionPaperService;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/question/paper" })
public class AdminQuestionPaperController extends BaseController {
	@Autowired
	private StageQuestionPaperService paperService;

	@RequestMapping({ "/student_question_paper_list/{sid}" })
	public String get_paper_details(@PathVariable("sid") Integer sid, HttpServletRequest request) {
		request.setAttribute("sid", sid);
		return "admin/question/paper/student_question_paper_list";
	}

	@RequestMapping({ "/student_paper_details/{id}" })
	public String student_paper_details(@PathVariable("id") Integer id, HttpServletRequest request) {
		Stage_question_paper paper = this.paperService.getPaperDetailsById(id);
		request.setAttribute("paper", paper);
		return "admin/question/paper/student_paper_details";
	}

	@RequestMapping({ "/get_paper_details/{id}" })
	public void get_paper_details(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "成功");
		try {
			Stage_question_paper paper = this.paperService.getPaperDetailsById(id);
			vo.setData(paper);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("失败");
		}
		writeJson(response, JsonUtil.toJsonIgnoreField(vo, new String[] { "stage_question", "paper" }));
	}

	@RequestMapping({ "/find_student_paper/{sid}" })
	public void find_student_paper(@PathVariable("sid") Integer sid, PageRequest pageRequest,
			HttpServletResponse response) {
		PageResult<Stage_question_paper> pr = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			pageRequest.setSort(new Sort("id", false));
			Page<Stage_question_paper> page = this.paperService.findPaperByStudentId(pageRequest, sid);
			pr.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pr.setCode(Integer.valueOf(500));
			pr.setMsg("加载失败");
		}
		writeJson(response, pr);
	}
}
