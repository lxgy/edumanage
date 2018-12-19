package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.StudentFeedbackDao;
import com.seehope.entity.Student_feedback;
import com.seehope.util.date.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentFeedbackService {
	@Autowired
	StudentFeedbackDao feedbackDao;

	public Page<Student_feedback> findFeedBacks(PageRequest pageRequest, Boolean status) {
		if ((status != null) && (status.booleanValue())) {
			return this.feedbackDao.findNotIn("status", Integer.valueOf(3), pageRequest);
		}
		return this.feedbackDao.findBy("status", Integer.valueOf(2), pageRequest);
	}

	public void deleteFeedBackById(Integer id) {
		Student_feedback feedback = (Student_feedback) this.feedbackDao.getById(id);
		feedback.setStatus(Integer.valueOf(3));
		feedback.setUpdate_time(DateTimeUtil.getCurDateTime());
		this.feedbackDao.update(feedback);
	}

	public void updateFeedBackStatus(Integer id) {
		Student_feedback feedback = (Student_feedback) this.feedbackDao.getById(id);
		feedback.setStatus(Integer.valueOf(1));
		feedback.setUpdate_time(DateTimeUtil.getCurDateTime());
		this.feedbackDao.update(feedback);
	}

	public void addFeedBack(Student_feedback feedback) {
		feedback.setCreate_time(DateTimeUtil.getCurDateTime());
		feedback.setUpdate_time(DateTimeUtil.getCurDateTime());
		feedback.setStatus(Integer.valueOf(2));
		this.feedbackDao.save(feedback);
	}
}
