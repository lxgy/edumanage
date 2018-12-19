package com.seehope.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.ActivitiesRegisterDao;
import com.seehope.entity.Activities;
import com.seehope.util.date.DateTimeUtil;

@Service
@Transactional
public class ActivitiesService {

	@Resource
	private ActivitiesRegisterDao activitiesRegisterDao;

	/**
	 * 添加报名信息.
	 * ===============
	 * @param activities
	 * @return
	 */
	public boolean addRegistPeople(Activities activities) {
		activities.setCreate_time(DateTimeUtil.getCurDateTime());
		activities.setVisible(1);
		try {
			this.activitiesRegisterDao.save(activities);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取所有报名用户资料.
	 * =============
	 * @return
	 */
	public List<Activities> findAllActivities() {
		List<Activities> activities = this.activitiesRegisterDao.findAll();
		return activities;
	}

	/**
	 * 条件查询.
	 * 分页查询.
	 * 条件为空 查询所有数据.
	 * ===================
	 * @param pageRequest
	 * @param key
	 * @return
	 */
	public Page<Activities> findAllActivitiesByPage(PageRequest pageRequest, String key) {
		Map<String, Object> by = new HashMap<>();
		by.put("visible", Integer.valueOf(1));
		if ((key != null) && (!key.equals(""))) {
			/* return this.classesDao.findByAndLike(by, "name", key, pageRequest); */
			return this.activitiesRegisterDao.findByAndLike(by, "name", key, pageRequest);
		}
		return this.activitiesRegisterDao.findBy(by, pageRequest);
	}
}
