package com.seehope.controller.edu;

import com.seehope.controller.BaseController;
import com.seehope.dao.core.DaoException;
import com.seehope.entity.Seehope_student;
import com.seehope.service.SeehopeStudentService;
import com.seehope.util.UserSessionUtil;
import com.seehope.util.json.JsonUtil;
import com.seehope.util.security.MD5MixUtil;
import com.seehope.web.vo.ResultVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/edu" })
public class EduController extends BaseController {
	@Autowired
	SeehopeStudentService studentService;

	@RequestMapping({ "/login" })
	public String login() {
		return "/edu/login/user_login";
	}

	@RequestMapping({ "/ajax_login" })
	public void login(String loginName, String loginPwd, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo();
		try {
			Seehope_student getStu = this.studentService.getStudentByLoginName(loginName);
			if (getStu == null) {
				throw new DaoException("账号不存在");
			}
			if (!getStu.getPassword().equals(MD5MixUtil.md5Mix(loginPwd))) {
				throw new DaoException("密码不正确");
			}
			UserSessionUtil.setEduStudent(getStu, request);
			vo.setMsg("登录成功");
		} catch (Exception e) {
			vo.setCode("500");
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}

	@RequestMapping({ "/index" })
	public String index() {
		return "edu/index";
	}

	@RequestMapping({ "" })
	public String index2() {
		return "edu/index";
	}

	@RequestMapping({ "/logout" })
	public String logout(HttpServletRequest request) {
		UserSessionUtil.removeEduStudent(request);
		return "/edu/login/user_login";
	}

	@RequestMapping({ "/student_self_info" })
	public String user_info(HttpServletRequest request) {
		Seehope_student student = UserSessionUtil.getEduStudent(request);
		request.setAttribute("student", this.studentService.getStudentById(student.getId()));
		return "edu/info/student_self_info";
	}

	@RequestMapping({ "/modify_pwd" })
	public String modify_pwd() {
		return "edu/info/modify_pwd";
	}

	@RequestMapping({ "/check_oldPwd" })
	public void check_oldPwd(String oldPwd, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "密码正确");
		try {
			Seehope_student student = UserSessionUtil.getEduStudent(request);
			student = this.studentService.getStudentById(student.getId());
			if (!student.getPassword().equals(MD5MixUtil.md5Mix(oldPwd))) {
				vo.setMsg("密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg(e.getMessage());
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/update_pwd" })
	public void update_pwd(String password, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "修改成功");
		try {
			if ((password == null) || ("".equals(password))) {
				vo.setCode("500");
				vo.setMsg("密码不能为空");
			} else if (password.length() < 6) {
				vo.setCode("500");
				vo.setMsg("密码至少6位");
			} else {
				Seehope_student student = UserSessionUtil.getEduStudent(request);
				student = this.studentService.getStudentById(student.getId());
				student.setPassword(MD5MixUtil.md5Mix(password));
				this.studentService.saveStudent(student);
				UserSessionUtil.setEduStudent(student, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("修改失败");
		}
		writeJson(response, vo);
	}
}
