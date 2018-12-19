package com.seehope.dao;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Stage_content;
import com.seehope.entity.Stage_content_student;
import com.seehope.util.date.DateTimeUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StageContentStudentDao extends BaseDaoWithFind<Stage_content_student> {
	@Resource
	private SessionFactory sessionFactory;

	protected StageContentStudentDao() {
		super(Stage_content_student.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Stage_content_student> getAllStageStudent(PageRequest pageRequest) {
		return findAll(pageRequest);
	}

	public Stage_content_student getStageStudentById(Integer id) {
		return (Stage_content_student) getById(id);
	}

	public Stage_content_student getContentStudentBy(Map<String, Object> by) {
		List<Stage_content_student> list = findBy(by);
		if (list.size() > 0) {
			return (Stage_content_student) list.get(0);
		}
		return null;
	}

	public Stage_content_student getByContentAndStudent(Stage_content content, Seehope_student student) {
		Map<String, Object> by = new HashMap<>();
		by.put("stage_content.id", content.getId());
		by.put("student.id", student.getId());
		List<Stage_content_student> list = findBy(by);
		if (list.size() > 0) {
			return (Stage_content_student) list.get(0);
		}
		Stage_content_student content_student = new Stage_content_student();
		content_student.setCreate_time(DateTimeUtil.getCurDateTime());
		content_student.setUpdate_time(DateTimeUtil.getCurDateTime());
		content_student.setStudent(student);
		content_student.setStage_content(content);
		save(content_student);
		return content_student;
	}
}
