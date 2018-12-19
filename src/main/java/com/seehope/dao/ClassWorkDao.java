package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Class_work;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ClassWorkDao extends BaseDaoWithFind<Class_work> {
	@Resource
	private SessionFactory sessionFactory;

	public ClassWorkDao() {
		super(Class_work.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
}
