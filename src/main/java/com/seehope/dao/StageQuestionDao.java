package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Stage_question;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StageQuestionDao extends BaseDaoWithFind<Stage_question> {
	@Resource
	private SessionFactory sessionFactory;

	protected StageQuestionDao() {
		super(Stage_question.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
