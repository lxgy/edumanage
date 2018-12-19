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
import com.seehope.entity.Evaluation;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Stage;
import com.seehope.entity.Stage_content_student;
import com.seehope.service.EvaluationService;
import com.seehope.service.SeehopeStudentService;
import com.seehope.service.StageContentStudentService;
import com.seehope.util.file.DownloadUtil;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/stage/evaluation" })
public class AdminStageContentStudentController extends BaseController {
	@Autowired
	private StageContentStudentService contentStudentService;
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private SeehopeStudentService studentService;

	@RequestMapping({ "/stage_student_evaluation_detail/{sid}" })
	public String stage_student_evaluation_detail(@PathVariable("sid") Integer sid, HttpServletRequest request) {
		request.setAttribute("sid", sid);
		List<Evaluation> evaluations = this.evaluationService.findStudentEvaluation(sid);
		request.setAttribute("evaluations", evaluations);
		return "/admin/stage/evaluation/stage_student_evaluation_detail";
	}

	@RequestMapping({ "/list_stage_student_evaluation/{sid}" })
	public void list_stage_student_evaluation(PageRequest pageRequest, @PathVariable("sid") Integer sid, Integer type,
			HttpServletResponse response) {
		PageResult<Stage> pageResult = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			Page<Stage> page = this.contentStudentService.findStudentStageEvaluation(pageRequest, sid, type);
			pageResult.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pageResult.setCode(Integer.valueOf(500));
			pageResult.setMsg("查询失败");
		}
		writeJson(response, JsonUtil.toJsonIgnoreField(pageResult, new String[] { "stage" }));
	}

	@RequestMapping({ "/get_stage_evaluation/{id}" })
	public void get_stage_evaluation(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo resultVo = new ResultVo("200", "查询成功");
		try {
			Stage_content_student content_student = this.contentStudentService.getStageStudentById(id);
			resultVo.setData(content_student);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("查询失败");
		}
		writeJson(response, resultVo);
	}

	@RequestMapping({ "/update_stage_teacher_evaluation" })
	public void update(Stage_content_student content_student, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "评价成功");
		try {
			Stage_content_student saveContent_student = this.contentStudentService
					.getStageStudentById(content_student.getId());
			saveContent_student.setTeacher_evaluation(content_student.getTeacher_evaluation());

			this.contentStudentService.updateStageContentStudent(saveContent_student);
			vo.setData(saveContent_student);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("评价失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/download_student_stage/{sid}" })
	public void download_student_stage(@PathVariable("sid") Integer sid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Seehope_student student = this.studentService.getStudentById(sid);

		String filePath = this.contentStudentService.createStudentStageFile(sid);

		String filename = student.getName() + "-阶段信息.xlsx";

		DownloadUtil.downloadFile(filename, filePath, request, response);
	}
}
