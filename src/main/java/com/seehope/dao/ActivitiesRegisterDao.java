package com.seehope.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Activities;

@Repository
public class ActivitiesRegisterDao extends BaseDaoWithFind<Activities> {

	@Resource
	private SessionFactory sessionFactory;

	protected ActivitiesRegisterDao() {
		super(Activities.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	

}
