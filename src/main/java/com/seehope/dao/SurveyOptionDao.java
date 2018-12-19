package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Survey_option;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyOptionDao extends BaseDaoWithFind<Survey_option> {
	@Resource
	private SessionFactory sessionFactory;

	protected SurveyOptionDao() {
		super(Survey_option.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
