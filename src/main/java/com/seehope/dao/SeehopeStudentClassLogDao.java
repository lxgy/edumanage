package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Seehope_student_class_log;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SeehopeStudentClassLogDao extends BaseDaoWithFind<Seehope_student_class_log> {
	@Resource
	private SessionFactory sessionFactory;

	protected SeehopeStudentClassLogDao() {
		super(Seehope_student_class_log.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
