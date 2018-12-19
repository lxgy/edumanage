package com.seehope.controller.admin;

import com.seehope.controller.BaseController;
import com.seehope.core.page.PageRequest;
import com.seehope.entity.Stage;
import com.seehope.service.StageService;
import com.seehope.util.json.JsonUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/admin/stage" })
public class AdminStageController extends BaseController {
	@Autowired
	private StageService stageService;

	@RequestMapping({ "/stage_list" })
	public String stage_list() {
		return "/admin/stage/stage_list";
	}

	@RequestMapping({ "/stage_edit/{id}" })
	public String stage_edit(@PathVariable("id") Integer id, HttpServletRequest request) {
		Stage stage = this.stageService.getStageById(id);
		request.setAttribute("stage", stage);
		return "/admin/stage/stage_edit";
	}

	@RequestMapping({ "/stage_add" })
	public String stage_add() {
		return "/admin/stage/stage_add";
	}

	@RequestMapping({ "/update_stage" })
	public String update_stage(Stage stage, HttpServletRequest request, HttpServletResponse response) {
		ResultVo resultVo = this.stageService.updateStage(stage);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/list_stage" })
	public String list_stage(PageRequest pageRequest, String key, Integer type, Integer status,
			HttpServletRequest request, HttpServletResponse response) {
		PageResult<Stage> pageResult = this.stageService.findStages(pageRequest, key, type, status);
		writeJson(response, JsonUtil.toJsonIgnoreField(pageResult, new String[] { "stage" }));
		return null;
	}

	@RequestMapping({ "/add_stage" })
	public String add_Stage(Stage stage, HttpServletRequest request, HttpServletResponse response) {
		ResultVo resultVo = this.stageService.addStage(stage);
		writeJson(response, resultVo);
		return null;
	}

	@RequestMapping({ "/delete_stage/{id}" })
	public void delete_stage(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
		ResultVo resultVo = new ResultVo("200", "删除成功");
		try {
			this.stageService.updateStageStatus(id, Integer.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("删除失败");
		}
		writeJson(response, resultVo);
	}

	@RequestMapping({ "/find_stage_by_type/{type}" })
	public void find_stage_by_type(@PathVariable("type") Integer type, HttpServletResponse response) {
		ResultVo resultVo = new ResultVo("200", "查找成功");
		try {
			List<Stage> list = this.stageService.findStageByStatusAndType(Integer.valueOf(1), type);
			resultVo.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("加载失败");
		}
		writeJson(response, resultVo);
	}
}
