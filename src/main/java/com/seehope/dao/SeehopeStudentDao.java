package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Seehope_student;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SeehopeStudentDao extends BaseDaoWithFind<Seehope_student> {
	@Resource
	private SessionFactory sessionFactory;

	protected SeehopeStudentDao() {
		super(Seehope_student.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
