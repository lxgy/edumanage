package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Student_info;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StudentInfoDao extends BaseDaoWithFind<Student_info> {
	@Resource
	private SessionFactory sessionFactory;

	protected StudentInfoDao() {
		super(Student_info.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
