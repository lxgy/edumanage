package com.seehope.dao;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Evaluation;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EvaluationDao extends BaseDaoWithFind<Evaluation> {
	@Resource
	private SessionFactory sessionFactory;

	protected EvaluationDao() {
		super(Evaluation.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Evaluation> getAllStageSummed(PageRequest pageRequest) {
		return findAll(pageRequest);
	}

	public Evaluation getSummedById(Integer id) {
		return (Evaluation) getById(id);
	}

	public Evaluation getBy(Map<String, Object> by) {
		List<Evaluation> list = findBy(by);
		if (list.size() > 0) {
			return (Evaluation) list.get(0);
		}
		return null;
	}
}
