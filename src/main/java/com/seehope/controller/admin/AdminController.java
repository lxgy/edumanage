package com.seehope.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.dao.core.DaoException;
import com.seehope.entity.Seehope_teacher;
import com.seehope.service.SeehopeTeacherService;
import com.seehope.util.UserSessionUtil;
import com.seehope.util.file.FileRwUtil;
import com.seehope.util.json.JsonUtil;
import com.seehope.util.security.MD5MixUtil;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin" })
public class AdminController extends BaseController {
	@Autowired
	SeehopeTeacherService teacherService;

	@RequestMapping({ "/login" })
	public String login() {
		return "/admin/login/admin_login";
	}

	@RequestMapping({ "/ajax_login" })
	public void login(String loginName, String loginPwd, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo();
		try {
			Seehope_teacher getTeacher = this.teacherService.getTeacherByLoginName(loginName);
			if (getTeacher == null) {
				throw new DaoException("账号不存在");
			}
			if (!getTeacher.getPassword().equals(MD5MixUtil.md5Mix(loginPwd))) {
				throw new DaoException("密码不正确");
			}
			UserSessionUtil.setAdminTeacher(getTeacher, request);
			vo.setMsg("登录成功");
			String navbar = FileRwUtil
					.readFileReader(request.getServletContext().getRealPath("/WEB-INF/res/navbar.json"));
			UserSessionUtil.setAdminMenu(request, navbar);
		} catch (Exception e) {
			vo.setCode("500");
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}

	@RequestMapping({ "/getmenujson" })
	public void getmenujson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String navbar = UserSessionUtil.getAdminMenu(request);
		writeJson(response, navbar);
	}

	@RequestMapping({ "/index" })
	public void index() {
	}

	@RequestMapping({ "" })
	public String index2() {
		return "/admin/index";
	}

	@RequestMapping({ "/main" })
	public void mainpage() {
	}

	@RequestMapping({ "/logout" })
	public String logout(HttpServletRequest request) {
		UserSessionUtil.removeAdminTeacher(request);
		UserSessionUtil.removeAdminMenu(request);
		return "/admin/login/admin_login";
	}

	@RequestMapping({ "/teacher_self_info" })
	public String teacher_self_info(HttpServletRequest request) {
		Seehope_teacher teacher = UserSessionUtil.getAdminTeacher(request);
		request.setAttribute("teacher", this.teacherService.getTeacherById(teacher.getId()));
		return "admin/info/teacher_self_info";
	}

	@RequestMapping({ "/modify_pwd" })
	public String modify_pwd() {
		return "admin/info/modify_pwd";
	}

	@RequestMapping({ "/check_oldPwd" })
	public void check_oldPwd(String oldPwd, HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "密码正确");
		try {
			Seehope_teacher teacher = UserSessionUtil.getAdminTeacher(request);
			teacher = this.teacherService.getTeacherById(teacher.getId());
			if (!teacher.getPassword().equals(MD5MixUtil.md5Mix(oldPwd))) {
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
				vo.setMsg("密码不为空");
			} else if (password.length() < 6) {
				vo.setCode("500");
				vo.setMsg("密码至少6位");
			} else {
				Seehope_teacher teacher = UserSessionUtil.getAdminTeacher(request);
				teacher = this.teacherService.getTeacherById(teacher.getId());
				teacher.setPassword(MD5MixUtil.md5Mix(password));
				this.teacherService.saveTeacher(teacher);
				UserSessionUtil.setAdminTeacher(teacher, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("修改失败");
		}
		writeJson(response, vo);
	}
}
