package com.seehope.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.global.Global;
import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.core.page.Sort;
import com.seehope.entity.Seehope_class;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Seehope_student_class_log;
import com.seehope.entity.Seehope_teacher;
import com.seehope.service.SeehopeClassService;
import com.seehope.service.SeehopeStudentClassLogService;
import com.seehope.service.SeehopeStudentService;
import com.seehope.service.SeehopeTeacherService;
import com.seehope.util.file.DownloadUtil;
import com.seehope.util.poi.ExcelPoiUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/student" })
public class AdminStudentController extends BaseController {
	@Autowired
	SeehopeStudentService studentService;
	@Autowired
	SeehopeClassService classService;
	@Autowired
	SeehopeTeacherService teacherService;
	@Autowired
	SeehopeStudentClassLogService logService;

	@RequestMapping({ "/graduated_students" })
	public String get_graduated_students() {
		return "admin/student/gs_list";
	}

	@RequestMapping({ "/student_list" })
	public String student_list() {
		return "admin/student/student_list";
	}

	@RequestMapping({ "/student_add" })
	public String student_add(HttpServletRequest request) {
		List<Seehope_class> classes = this.classService.findAllClass();
		List<Seehope_teacher> teachers = this.teacherService.findAllTeachers();
		request.setAttribute("classes", classes);
		request.setAttribute("teachers", teachers);
		return "admin/student/student_add";
	}

	@RequestMapping({ "/student_edit/{id}" })
	public String student_edit(@PathVariable("id") Integer id, HttpServletRequest req) {
		Seehope_student student = this.studentService.getStudentById(id);

		List<Seehope_teacher> teachers = this.teacherService.findAllTeachers();
		req.setAttribute("student", student);

		req.setAttribute("teachers", teachers);
		return "admin/student/student_edit";
	}

	@RequestMapping({ "/student_info/{id}" })
	public String student_info(@PathVariable("id") Integer id, HttpServletRequest req) {
		Seehope_student student = this.studentService.getStudentById(id);
		req.setAttribute("student", student);
		return "admin/student/student_info";
	}

	@RequestMapping({ "/class_student_list/{cid}" })
	public String class_student_list(@PathVariable("cid") Integer cid, HttpServletRequest req) {
		req.setAttribute("cid", cid);
		return "admin/student/class_student_list";
	}

	@RequestMapping({ "/student_upload" })
	public String student_upload() {
		return "admin/student/student_upload";
	}

	@RequestMapping({ "/student_class_log_list/{tid}" })
	public String student_class_log_list(@PathVariable("tid") Integer tid, HttpServletRequest request) {
		List<Seehope_student_class_log> list = this.logService.findStuClassLog(tid);
		request.setAttribute("logs", list);
		return "admin/student/student_class_log_list";
	}

	@RequestMapping({ "/add" })
	public void add(Seehope_student student, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "添加成功");
		try {
			if (this.studentService.getStudentByPhone(student.getPhone()) != null) {
				vo.setCode("500");
				vo.setMsg("手机已经被占用");
			} else {
				this.studentService.saveStudent(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("添加失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/list" })
	public void list(PageRequest pageRequest, String key, Boolean status, HttpServletResponse response) {
		PageResult<Seehope_student> pr = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			pageRequest.setSort(new Sort(false, new String[] { "status", "id" }));
			Page<Seehope_student> page = this.studentService.findStudents(pageRequest, key, status);
			pr.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pr.setCode(Integer.valueOf(500));
			pr.setMsg("加载失败");
		}
		writeJson(response, pr);
	}

	@RequestMapping({ "/list_class_student/{cid}" })
	public void list_class_student(@PathVariable("cid") Integer cid, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "查询成功");
		try {
			List<Seehope_student> list = this.studentService.findStudentsByClassId(cid);
			vo.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("加载失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/update_student_status" })
	public void update_student_status(Integer sid, Integer status, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "修改成功");
		try {
			this.studentService.updateStudentStatus(sid, status);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("修改失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/update" })
	public void update(Seehope_student student, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "修改成功");
		try {
			Seehope_student saveStudent = this.studentService.getStudentById(student.getId());
			if ((!saveStudent.getPhone().equals(student.getPhone()))
					&& (this.studentService.getStudentByPhone(student.getPhone()) != null)) {
				vo.setCode("500");
				vo.setMsg("该手机被占用");
			} else {
				this.studentService.updateStudent(student);
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

	@RequestMapping({ "/update_students_class" })
	public void update_students_class(String id, Integer cid, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "操作成功");
		try {
			String[] ids = id.replace("[", "").replace("]", "").split(",");
			Integer[] studentIds = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				studentIds[i] = Integer.valueOf(ids[i]);
			}
			this.studentService.updateStudentsClass(studentIds, cid);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("操作失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/update_class/{id}" })
	public void update_students_class(@PathVariable("id") Integer id, Integer classId, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "修改成功");
		try {
			Integer[] ids = { id };
			this.studentService.updateStudentsClass(ids, classId);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("修改失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/download_stu_excel" })
	public void download_stu_excel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String downFileName = Global.filePath + "/template/excel/template.xlsx";

		String filename = "模板.xlsx";

		DownloadUtil.downloadFile(filename, downFileName, request, response);
	}

	@RequestMapping({ "/import_info" })
	public void import_info(String filename, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "导入成功");
		try {
			this.studentService.importStudentInfo(filename);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("导入失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/download_class_student_excel/{cid}" })
	public void download_class_student_excel(@PathVariable("cid") Integer cid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Seehope_class seehope_class = this.classService.getClassById(cid);
		List<Seehope_student> students = this.studentService.findStudentsByClassId(cid);

		String templatePath = Global.filePath + "/template/student-info-template.xml";
		String createPath = Global.filePath + "/upload/tmp/";

		String createName = ExcelPoiUtil.objectsToExcel(createPath, templatePath, students, Seehope_student.class);

		String filename = seehope_class.getName() + "-学生信息表.xlsx";

		DownloadUtil.downloadFile(filename, createPath + createName, request, response);
	}

	@RequestMapping({ "/download_class_student_book/{cid}" })
	public void download_class_student_book(@PathVariable("cid") Integer cid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Seehope_class seehope_class = this.classService.getClassById(cid);
		List<Seehope_student> students = this.studentService.findStudentsByClassId(cid);

		String templatePath = Global.filePath + "/template/student-book-template.xml";
		String createPath = Global.filePath + "/upload/tmp/";

		String createName = ExcelPoiUtil.objectsToExcel(createPath, templatePath, students, Seehope_student.class);

		String filename = seehope_class.getName() + "-点名表.xlsx";

		DownloadUtil.downloadFile(filename, createPath + createName, request, response);
	}

	@RequestMapping({ "/graduated_list" })
	public String find_graduated_list(PageRequest pageRequest, String key, HttpServletRequest request,
			HttpServletResponse response) {
		PageResult<Seehope_student> pageResult = this.studentService.findGraduatedStudents(pageRequest, key,
				Integer.valueOf(1));
		writeJson(response, pageResult);
		return null;
	}
}
