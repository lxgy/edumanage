package com.seehope.dao;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Stage;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StageDao extends BaseDaoWithFind<Stage> {
	@Resource
	private SessionFactory sessionFactory;

	protected StageDao() {
		super(Stage.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Stage> getByType(int type, PageRequest pageRequest) {
		Page<Stage> page = findBy("type", Integer.valueOf(type), pageRequest);
		return page;
	}

	public void deleteStageById(Integer id) {
		deleteById(id);
	}

	public void updateStage(Stage stage) {
		update(stage);
	}

	public Stage getStageById(Integer id) {
		return (Stage) getById(id);
	}

	public List<Stage> getAllListStage() {
		return findBy("status", Integer.valueOf(1));
	}
}
