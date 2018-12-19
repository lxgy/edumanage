package com.seehope.controller.edu;

import com.seehope.controller.BaseController;
import com.seehope.entity.Seehope_student;
import com.seehope.service.SeehopeStudentService;
import com.seehope.util.UserSessionUtil;
import com.seehope.web.vo.ResultVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/edu/student" })
public class EduStudentController extends BaseController {
	@Autowired
	SeehopeStudentService studentService;

	@RequestMapping({ "/update" })
	public void update(Seehope_student student, HttpServletResponse response, HttpServletRequest request) {
		ResultVo vo = new ResultVo("200", "更新成功");
		try {
			Seehope_student saveStudent = UserSessionUtil.getEduStudent(request);
			if ((!saveStudent.getPhone().equals(student.getPhone()))
					&& (this.studentService.getStudentByPhone(student.getPhone()) != null)) {
				vo.setCode("500");
				vo.setMsg("手机被占用");
			} else {
				saveStudent.setPhone(student.getPhone());
				saveStudent.setWechat(student.getWechat());
				saveStudent.setEmail(student.getEmail());
				saveStudent.setQq(student.getQq());
				saveStudent.setSex(student.getSex());
				this.studentService.saveStudent(saveStudent);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("修改失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/checkphone" })
	public void checkphone(String phone, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "手机可用");
		try {
			Seehope_student student = this.studentService.getStudentByPhone(phone);
			if (student != null) {
				vo.setMsg("手机被占用");
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg(e.getMessage());
		}
		writeJson(response, vo);
	}
}
