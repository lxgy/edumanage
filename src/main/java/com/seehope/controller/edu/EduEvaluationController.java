package com.seehope.controller.edu;

import com.seehope.controller.BaseController;
import com.seehope.entity.Evaluation;
import com.seehope.entity.Seehope_student;
import com.seehope.service.EvaluationService;
import com.seehope.util.UserSessionUtil;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.ResultVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/edu/evaluation" })
public class EduEvaluationController extends BaseController {
	@Autowired
	EvaluationService evaluationService;

	@RequestMapping({ "/get_evaluation" })
	public void get_evaluation(Integer type, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "查询成功");
		try {
			Seehope_student student = UserSessionUtil.getEduStudent(request);
			Evaluation evaluation = this.evaluationService.getEvaluationByTypeStudent(type, student);
			vo.setData(evaluation);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("加载失败");
		}
		writeJson(response, JsonUtil.toJson(vo));
	}

	@RequestMapping({ "/evaluate" })
	public void evaluate(Evaluation evaluation, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "查询成功");
		try {
			Evaluation saveEvaluation = this.evaluationService.getEvaluationById(evaluation.getId());
			saveEvaluation.setContent(evaluation.getContent());
			this.evaluationService.updateEvaluation(saveEvaluation);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("加载失败");
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
}
