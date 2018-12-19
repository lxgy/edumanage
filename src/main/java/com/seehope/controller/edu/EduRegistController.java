package com.seehope.controller.edu;

import com.seehope.controller.BaseController;
import com.seehope.entity.Student_info;
import com.seehope.service.StudentInfoService;
import com.seehope.web.vo.ResultVo;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/new/user" })
public class EduRegistController extends BaseController {
	@Autowired
	private StudentInfoService studentService;

	@RequestMapping({ "/regist" })
	public String getRegist() {
		return "edu/registuser/regist";
	}

	@RequestMapping({ "/ajax_regist" })
	public String ajax_regist(Student_info student_info, HttpServletResponse response) {
		ResultVo resultVo = new ResultVo("200", "注册成功");
		try {
			this.studentService.ajax_regist(student_info);
			Student_info info = this.studentService.getStudentInfoByPhone(student_info.getPhone());
			resultVo.setData(info);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("注册失败");
		}
		writeJson(response, resultVo);
		return null;
	}
}
