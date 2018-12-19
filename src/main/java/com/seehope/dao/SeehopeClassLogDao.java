package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Seehope_class_log;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SeehopeClassLogDao extends BaseDaoWithFind<Seehope_class_log> {
	@Resource
	private SessionFactory sessionFactory;

	public SeehopeClassLogDao() {
		super(Seehope_class_log.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
