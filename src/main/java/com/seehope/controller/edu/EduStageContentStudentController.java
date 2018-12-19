package com.seehope.controller.edu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Stage;
import com.seehope.entity.Stage_content_student;
import com.seehope.service.StageContentStudentService;
import com.seehope.util.UserSessionUtil;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/edu/stage/student" })
public class EduStageContentStudentController extends BaseController {
	@Autowired
	StageContentStudentService contentStudentService;

	@RequestMapping({ "/list_self_stage_evaluation" })
	public void stage_student_evaluation_detail(PageRequest pageRequest, Integer type, HttpServletRequest request,
			HttpServletResponse response) {
		PageResult<Stage> pageResult = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			Seehope_student student = UserSessionUtil.getEduStudent(request);
			Page<Stage> page = this.contentStudentService.findStudentStageEvaluation(pageRequest, student.getId(),
					type);
			pageResult.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pageResult.setCode(Integer.valueOf(500));
			pageResult.setMsg("加载失败");
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
			resultVo.setMsg("加载失败");
		}
		writeJson(response, resultVo);
	}

	@RequestMapping({ "/update_stage_evaluation" })
	public void update(Stage_content_student content_student, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "更新成功");
		try {
			Stage_content_student saveContent_student = this.contentStudentService
					.getStageStudentById(content_student.getId());
			saveContent_student.setStudent_score(content_student.getStudent_score());
			saveContent_student.setTeacher_score(content_student.getTeacher_score());
			saveContent_student.setEvaluation(content_student.getEvaluation());

			this.contentStudentService.updateStageContentStudent(saveContent_student);
			vo.setData(saveContent_student);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("更新失败");
		}
		writeJson(response, vo);
	}
}
