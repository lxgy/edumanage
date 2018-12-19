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
import com.seehope.core.page.Sort;
import com.seehope.entity.Seehope_class;
import com.seehope.entity.Seehope_class_log;
import com.seehope.entity.Seehope_teacher;
import com.seehope.service.SeehopeClassLogService;
import com.seehope.service.SeehopeClassService;
import com.seehope.service.SeehopeTeacherService;
import com.seehope.util.UserSessionUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/class" })
public class AdminClassController extends BaseController {
	@Autowired
	SeehopeClassService classService;
	@Autowired
	SeehopeTeacherService teacherService;
	@Autowired
	SeehopeClassLogService classLogService;

	@RequestMapping({ "/class_list" })
	public String class_list() {
		return "admin/class/class_list";
	}

	@RequestMapping({ "/class_add" })
	public String class_add(HttpServletRequest req) {
		List<Seehope_teacher> chargeTeachers = this.teacherService.findTeachersByType(Integer.valueOf(2));
		List<Seehope_teacher> teachTeachers = this.teacherService.findTeachersByType(Integer.valueOf(1));
		req.setAttribute("charge_teachers", chargeTeachers);
		req.setAttribute("teach_teachers", teachTeachers);
		return "admin/class/class_add";
	}

	@RequestMapping({ "/class_edit/{id}" })
	public String class_edit(@PathVariable("id") Integer id, HttpServletRequest req) {
		Seehope_class clazz = this.classService.getClassById(id);
		req.setAttribute("clazz", clazz);

		List<Seehope_teacher> chargeTeachers = this.teacherService.findTeachersByType(Integer.valueOf(2));
		List<Seehope_teacher> teachTeachers = this.teacherService.findTeachersByType(Integer.valueOf(1));
		req.setAttribute("charge_teachers", chargeTeachers);
		req.setAttribute("teach_teachers", teachTeachers);
		return "admin/class/class_edit";
	}

	@RequestMapping({ "/class_info/{id}" })
	public String class_info(@PathVariable("id") Integer id, HttpServletRequest req) {
		Seehope_class clazz = this.classService.getClassById(id);
		req.setAttribute("clazz", clazz);
		return "admin/class/class_info";
	}

	@RequestMapping({ "/self_class_list" })
	public String self_class_list() {
		return "admin/info/self_class_list";
	}

	@RequestMapping({ "/class_log_list/{cid}" })
	public String student_class_log_list(@PathVariable("cid") Integer cid, HttpServletRequest request) {
		List<Seehope_class_log> list = this.classLogService.findClassLog(cid);
		request.setAttribute("logs", list);
		return "admin/class/class_log_list";
	}

	@RequestMapping({ "/add" })
	public void add(Seehope_class clazz, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "添加成功");
		try {
			this.classService.saveClasses(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("添加失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/list_self_class" })
	public void list(PageRequest pageRequest, Boolean isGraduation, HttpServletResponse response,
			HttpServletRequest request) {
		PageResult<Seehope_class> pr = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			Seehope_teacher teacher = UserSessionUtil.getAdminTeacher(request);
			pageRequest.setSort(new Sort("id", false));
			Page<Seehope_class> page = this.classService.findSelfClasses(pageRequest, isGraduation, teacher);
			pr.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pr.setCode(Integer.valueOf(500));
			pr.setMsg("加载失败");
		}
		writeJson(response, pr);
	}

	@RequestMapping({ "/list" })
	public void list(PageRequest pageRequest, String key, Integer status, HttpServletResponse response) {
		PageResult<Seehope_class> pr = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			pageRequest.setSort(new Sort("id", false));
			Page<Seehope_class> page = this.classService.findClasses(pageRequest, key, status);
			pr.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pr.setCode(Integer.valueOf(500));
			pr.setMsg("加载失败");
		}
		writeJson(response, pr);
	}

	@RequestMapping({ "/update" })
	public void update(Seehope_class clazz, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "修改成功");
		try {
			this.classService.updateClass(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("修改失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/delete_class/{id}" })
	public String delete_class(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo resultVo = new ResultVo("200", "删除成功");
		try {
			this.classService.deleteClassById(id);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("删除失败");
		}
		writeJson(response, resultVo);
		return null;
	}
}
