package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Stage_question_student;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StageQuestionStudentDao extends BaseDaoWithFind<Stage_question_student> {
	@Resource
	private SessionFactory sessionFactory;

	protected StageQuestionStudentDao() {
		super(Stage_question_student.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
