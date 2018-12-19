package com.seehope.dao;

import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Stage_work;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StageWorkDao extends BaseDaoWithFind<Stage_work> {
	@Resource
	private SessionFactory sessionFactory;

	public StageWorkDao() {
		super(Stage_work.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
