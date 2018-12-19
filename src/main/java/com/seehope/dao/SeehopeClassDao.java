package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Seehope_class;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SeehopeClassDao extends BaseDaoWithFind<Seehope_class> {
	@Resource
	private SessionFactory sessionFactory;

	public SeehopeClassDao() {
		super(Seehope_class.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
