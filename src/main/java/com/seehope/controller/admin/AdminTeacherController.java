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
import com.seehope.entity.Seehope_teacher;
import com.seehope.service.SeehopeTeacherService;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/teacher" })
public class AdminTeacherController extends BaseController {
	@Autowired
	SeehopeTeacherService teacherService;

	@RequestMapping({ "/teacher_list" })
	public String teacher_list() {
		return "admin/teacher/teacher_list";
	}

	@RequestMapping({ "/teacher_add" })
	public String teacher_add() {
		return "admin/teacher/teacher_add";
	}

	@RequestMapping({ "/teacher_edit/{id}" })
	public String teacher_edit(@PathVariable("id") Integer id, HttpServletRequest req) {
		Seehope_teacher teacher = this.teacherService.getTeacherById(id);
		req.setAttribute("teacher", teacher);
		return "admin/teacher/teacher_edit";
	}

	@RequestMapping({ "/teacher_info/{id}" })
	public String teacher_info(@PathVariable("id") Integer id, HttpServletRequest req) {
		Seehope_teacher teacher = this.teacherService.getTeacherById(id);
		req.setAttribute("teacher", teacher);
		return "admin/teacher/teacher_info";
	}

	@RequestMapping({ "/add" })
	public void add(Seehope_teacher teacher, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "添加成功");
		try {
			this.teacherService.saveTeacher(teacher);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("添加失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/list" })
	public void list(PageRequest pageRequest, String key, Integer type, Boolean status, HttpServletResponse response) {
		PageResult<Seehope_teacher> pr = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			pageRequest.setSort(new Sort(false, new String[] { "status" }));
			Page<Seehope_teacher> page = this.teacherService.findTeachers(pageRequest, key, type, status);
			pr.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pr.setCode(Integer.valueOf(500));
			pr.setMsg("查询失败");
		}
		writeJson(response, pr);
	}

	@RequestMapping({ "/update_status" })
	public void update_status(Integer tid, Integer status, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "更新成功");
		try {
			Seehope_teacher teacher = this.teacherService.getTeacherById(tid);
			teacher.setStatus(status);
			this.teacherService.saveTeacher(teacher);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("更新失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/update" })
	public void update(Seehope_teacher teacher, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "更新成功");
		try {
			Seehope_teacher saveTeacher = this.teacherService.getTeacherById(teacher.getId());
			if ((!saveTeacher.getPhone().equals(teacher.getPhone()))
					&& (this.teacherService.getTeacherByPhone(teacher.getPhone()) != null)) {
				vo.setCode("500");
				vo.setMsg("手机被占用");
			} else {
				saveTeacher.setName(teacher.getName());
				saveTeacher.setPhone(teacher.getPhone());
				saveTeacher.setWechat(teacher.getWechat());
				saveTeacher.setEmail(teacher.getEmail());
				saveTeacher.setQq(teacher.getQq());
				saveTeacher.setSex(teacher.getSex());
				saveTeacher.setType(teacher.getType());
				this.teacherService.saveTeacher(saveTeacher);
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
		ResultVo vo = new ResultVo("200", "可以使用");
		try {
			Seehope_teacher teacher = this.teacherService.getTeacherByPhone(phone);
			if (teacher != null) {
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
