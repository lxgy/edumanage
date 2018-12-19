package com.seehope.controller.edu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.dao.core.DaoException;
import com.seehope.entity.Class_work;
import com.seehope.entity.Seehope_student;
import com.seehope.service.ClassWorkService;
import com.seehope.util.UserSessionUtil;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/edu/work" })
public class EduClassWorkController extends BaseController {
	@Autowired
	ClassWorkService classWorkService;

	@RequestMapping({ "/class_work_list" })
	public String class_list(HttpServletRequest request) {
		return "edu/work/class_work_list";
	}

	@RequestMapping({ "/class_work_details/{id}" })
	public String add(@PathVariable("id") Integer id, HttpServletRequest request) {
		Class_work class_work = this.classWorkService.getById(id);
		request.setAttribute("class_work", class_work);
		return "edu/work/class_work_details";
	}

	@RequestMapping({ "/findAll" })
	public void findAll(HttpServletRequest request, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "查询成功");
		try {
			Seehope_student student = UserSessionUtil.getEduStudent(request);
			if ((student.getSeehope_class() != null) && (student.getSeehope_class().getId() != null)) {
				List<Class_work> list = this.classWorkService.findAll(student.getSeehope_class().getId());
				vo.setData(list);
			} else {
				throw new DaoException("加载失败");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			vo.setCode("500");
			vo.setMsg("加载失败");

			writeJson(response, vo);
		}
	}
}
