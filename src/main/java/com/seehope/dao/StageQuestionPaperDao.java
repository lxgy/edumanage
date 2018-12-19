package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Stage_question_paper;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StageQuestionPaperDao extends BaseDaoWithFind<Stage_question_paper> {
	@Resource
	private SessionFactory sessionFactory;

	protected StageQuestionPaperDao() {
		super(Stage_question_paper.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
