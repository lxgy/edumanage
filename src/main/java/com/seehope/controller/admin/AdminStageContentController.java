package com.seehope.controller.admin;

import com.seehope.controller.BaseController;
import com.seehope.entity.Stage;
import com.seehope.entity.Stage_content;
import com.seehope.service.StageContentService;
import com.seehope.service.StageService;
import com.seehope.web.vo.ResultVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/admin/stage/content" })
public class AdminStageContentController extends BaseController {
	@Autowired
	private StageContentService stageContentService;
	@Autowired
	private StageService stageService;

	@RequestMapping({ "/stage_content_add/{sid}" })
	public String stage_content_add(@PathVariable("sid") Integer sid, HttpServletRequest request) {
		Stage stage = this.stageService.getStageById(sid);
		request.setAttribute("stage", stage);
		return "/admin/stage/content/stage_content_add";
	}

	@RequestMapping({ "/stage_content_edit/{id}" })
	public String stage_content_edit(@PathVariable("id") Integer id, HttpServletRequest request) {
		Stage_content content = this.stageContentService.getStageContentById(id);
		request.setAttribute("content", content);
		return "/admin/stage/content/stage_content_edit";
	}

	@RequestMapping({ "/add_stage_content" })
	public String add_content(Stage_content content, HttpServletRequest request, HttpServletResponse response) {
		ResultVo resultVo = this.stageContentService.addStageContent(content);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/delete_stage_content/{id}" })
	public String delete_content(@PathVariable("id") Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		ResultVo resultVo = this.stageContentService.deleteContent(id);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/update_stage_content" })
	public String update_content(Stage_content content, HttpServletRequest request, HttpServletResponse response) {
		ResultVo resultVo = this.stageContentService.updateContent(content);
		writeJson(response, resultVo);
		return null;
	}
}
