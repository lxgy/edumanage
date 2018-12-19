package com.seehope.controller.edu;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seehope.controller.BaseController;
import com.seehope.core.global.CodeEnum;
import com.seehope.entity.Activities;
import com.seehope.service.ActivitiesService;
import com.seehope.web.vo.ResultVo;

@Controller
@RequestMapping("/activities")
public class EduActivitiesRegisterController extends BaseController {

	@Resource
	private ActivitiesService activitiesService;

	@RequestMapping("/regist")
	public String getPageActivities() {
		return "/edu/activities/activities";
	}

	@RequestMapping("/addRegistPeople")
	public void addRegistPeople(Activities activities, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(activities);
		ResultVo resultVo = new ResultVo(CodeEnum._500, "报名失败");
		if (activitiesService.addRegistPeople(activities)) {
			resultVo = new ResultVo(CodeEnum._200, "报名成功");
		}
		this.writeJson(response, resultVo);
	}
}
