package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Survey;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyDao extends BaseDaoWithFind<Survey> {
	@Resource
	private SessionFactory sessionFactory;

	protected SurveyDao() {
		super(Survey.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
