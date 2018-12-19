package com.seehope.dao;

import com.seehope.core.page.Page;
import com.seehope.core.page.Pageable;
import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Student_feedback;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StudentFeedbackDao extends BaseDaoWithFind<Student_feedback> {
	@Resource
	private SessionFactory sessionFactory;

	protected StudentFeedbackDao() {
		super(Student_feedback.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Student_feedback> findNotIn(String attribute, Object value, Pageable pageable) {
		QueryWhere where = new QueryWhere();
		where.andNotIn(attribute, new Object[] { value });
		return findByWhere(where, pageable);
	}
}
