package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Seehope_teacher;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SeehopeTeacherDao extends BaseDaoWithFind<Seehope_teacher> {
	@Resource
	private SessionFactory sessionFactory;

	protected SeehopeTeacherDao() {
		super(Seehope_teacher.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
