package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Survey_paper;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyPaperDao extends BaseDaoWithFind<Survey_paper> {
	@Resource
	private SessionFactory sessionFactory;

	protected SurveyPaperDao() {
		super(Survey_paper.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
