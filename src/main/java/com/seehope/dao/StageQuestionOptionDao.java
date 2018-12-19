package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Stage_question_option;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StageQuestionOptionDao extends BaseDaoWithFind<Stage_question_option> {
	@Resource
	private SessionFactory sessionFactory;

	protected StageQuestionOptionDao() {
		super(Stage_question_option.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
