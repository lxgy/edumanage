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
import com.seehope.entity.Class_work;
import com.seehope.service.ClassWorkService;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/class/work" })
public class AdminClassWorkController extends BaseController {
	@Autowired
	ClassWorkService classWorkService;

	@RequestMapping({ "/class_work_list/{class_id}" })
	public String class_list(@PathVariable("class_id") Integer class_id, HttpServletRequest request) {
		request.setAttribute("class_id", class_id);
		return "admin/class/work/class_work_list";
	}

	@RequestMapping({ "/class_work_add/{class_id}" })
	public String add(@PathVariable("class_id") Integer class_id, HttpServletRequest request) {
		request.setAttribute("class_id", class_id);
		return "admin/class/work/class_work_add";
	}

	@RequestMapping({ "/class_work_edit/{id}" })
	public String class_edit(@PathVariable("id") Integer id, HttpServletRequest req) {
		Class_work class_work = this.classWorkService.getById(id);
		req.setAttribute("class_work", class_work);
		return "admin/class/work/class_work_edit";
	}

	@RequestMapping({ "/add" })
	public void add(Class_work class_work, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "添加成功");
		try {
			this.classWorkService.save(class_work);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("添加失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/list/{class_id}" })
	public void list(PageRequest pageRequest, @PathVariable("class_id") Integer class_id,
			HttpServletResponse response) {
		PageResult<Class_work> pr = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			pageRequest.setSort(new Sort("id", false));
			Page<Class_work> page = this.classWorkService.pageWork(pageRequest, class_id);
			pr.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pr.setCode(Integer.valueOf(500));
			pr.setMsg("加载失败");
		}
		writeJson(response, pr);
	}

	@RequestMapping({ "/findAll/{class_id}" })
	public void findAll(@PathVariable("class_id") Integer class_id, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "查询成功");
		try {
			List<Class_work> list = this.classWorkService.findAll(class_id);
			vo.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("加载失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/update" })
	public void update(Class_work work, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "修改成功");
		try {
			Class_work class_work = this.classWorkService.getById(work.getId());
			class_work.setContent(work.getContent());
			class_work.setPoint(work.getPoint());
			this.classWorkService.save(class_work);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("修改失败");
		}
		writeJson(response, vo);
	}
}
