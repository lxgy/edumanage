package com.seehope.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.page.PageRequest;
import com.seehope.core.page.Sort;
import com.seehope.entity.Activities;
import com.seehope.service.ActivitiesService;
import com.seehope.web.vo.PageResult;

@Controller
@RequestMapping("/admin/activities")
public class AdminActivitiesController extends BaseController {

	@Resource
	private ActivitiesService activitiesService;

	/**
	 * 返回报名用户管理界面.
	 * ==========
	 * @return
	 * @author lxgy
	 */
	@RequestMapping("/activities_list")
	public String getPageActivities() {
		return "admin/activities/activities_list";
	}

	/**
	 * 查询所有报名用户信息.
	 * ==============
	 * @author lxgy
	 */
	@RequestMapping("/findAllActivities")
	public void activitiesList(PageRequest pageRequest, String key, HttpServletRequest request,
			HttpServletResponse response) {
		PageResult<Activities> pageResult = new PageResult<Activities>(200, "查询成功");
		pageRequest.setSort(new Sort("id", false));
		try {
			pageResult.setPage(this.activitiesService.findAllActivitiesByPage(pageRequest, key));
		} catch (Exception e) {
			// TODO: handle exception
			pageResult.setCode(500);
			pageResult.setMsg("查询失败");
		}
		this.writeJson(response, pageResult);
	}

}
