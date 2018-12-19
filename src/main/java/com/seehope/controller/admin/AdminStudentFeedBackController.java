package com.seehope.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.core.page.Sort;
import com.seehope.entity.Student_feedback;
import com.seehope.service.StudentFeedbackService;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping({ "/admin/feedback" })
public class AdminStudentFeedBackController extends BaseController {
	@Autowired
	StudentFeedbackService feedbackService;

	@RequestMapping({ "/feedback_list" })
	public String feedBack_list() {
		return "admin/feedback/feedback_list";
	}

	@RequestMapping({ "/list" })
	public void list(PageRequest pageRequest, Boolean status, HttpServletResponse response) {
		PageResult<Student_feedback> pr = new PageResult<>(Integer.valueOf(200), "查询成功");
		try {
			pageRequest.setSort(new Sort(false, new String[] { "status", "id" }));
			Page<Student_feedback> page = this.feedbackService.findFeedBacks(pageRequest, status);
			pr.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pr.setCode(Integer.valueOf(500));
			pr.setMsg("加载失败");
		}
		writeJson(response, pr);
	}

	@RequestMapping({ "/delete/{id}" })
	public void delete(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "删除成功");
		try {
			this.feedbackService.deleteFeedBackById(id);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("删除失败");
		}
		writeJson(response, vo);
	}

	@RequestMapping({ "/update_status/{id}" })
	public void updateStatus(@PathVariable("id") Integer id, HttpServletResponse response) {
		ResultVo vo = new ResultVo("200", "更新成功");
		try {
			this.feedbackService.updateFeedBackStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setCode("500");
			vo.setMsg("更新失败");
		}
		writeJson(response, vo);
	}
}
