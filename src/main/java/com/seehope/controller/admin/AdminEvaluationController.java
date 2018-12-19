package com.seehope.controller.admin;

import com.seehope.controller.BaseController;
import com.seehope.entity.Evaluation;
import com.seehope.service.EvaluationService;
import com.seehope.service.SeehopeStudentService;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/admin/evaluation" })
public class AdminEvaluationController extends BaseController {
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private SeehopeStudentService studentService;

	@RequestMapping({ "/get_stage_evaluation" })
	public void get_stage_evaluation(Integer sid, Integer type, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "添加成功");
		try {
			Evaluation evaluation = this.evaluationService.getEvaluationByTypeStudent(type,
					this.studentService.getStudentById(sid));
			vo.setData(evaluation);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("添加失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/find_stage_evaluation" })
	public void find_stage_evaluation(Integer sid, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "添加成功");
		try {
			List<Evaluation> evaluations = this.evaluationService.findStudentEvaluation(sid);
			vo.setData(evaluations);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("添加失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/get_evaluation/{id}" })
	public void get_evaluation(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "查询成功");
		try {
			Evaluation evaluation = this.evaluationService.getEvaluationById(id);
			vo.setData(evaluation);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("查询失败");
		}
		writeJson(response, JsonUtil.toJson(vo));
	}

	@RequestMapping({ "/update_teacher_evaluation" })
	public void evaluate(Evaluation evaluation, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "评价成功");
		try {
			Evaluation saveEvaluation = this.evaluationService.getEvaluationById(evaluation.getId());
			saveEvaluation.setTeacher_evaluation(evaluation.getTeacher_evaluation());
			this.evaluationService.updateEvaluation(saveEvaluation);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("评价失败");
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
}
