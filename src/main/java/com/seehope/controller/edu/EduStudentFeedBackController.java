package com.seehope.controller.edu;

import com.seehope.controller.BaseController;
import com.seehope.entity.Student_feedback;
import com.seehope.service.StudentFeedbackService;
import com.seehope.util.UserSessionUtil;
import com.seehope.web.vo.ResultVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/edu/feedback" })
public class EduStudentFeedBackController extends BaseController {
	@Autowired
	StudentFeedbackService feedbackService;

	@RequestMapping({ "/feedback_add" })
	public String feedBack_list() {
		return "/edu/feedback/feedback_add";
	}

	@RequestMapping({ "add_feedback" })
	public void add_feedback(Student_feedback feedback, HttpServletResponse response, HttpServletRequest request) {
		ResultVo vo = new ResultVo("200", "添加成功");
		try {
			feedback.setStudent(UserSessionUtil.getEduStudent(request));
			this.feedbackService.addFeedBack(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("添加失败");
		}
		writeJson(response, vo);
	}
}
