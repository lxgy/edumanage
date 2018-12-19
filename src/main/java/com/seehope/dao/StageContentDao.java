package com.seehope.dao;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.core.BaseDaoWithFind;
import com.seehope.entity.Stage_content;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StageContentDao extends BaseDaoWithFind<Stage_content> {
	@Resource
	private SessionFactory sessionFactory;

	protected StageContentDao() {
		super(Stage_content.class);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Stage_content> getAllStageContent(PageRequest pageRequest) {
		return findAll(pageRequest);
	}

	public List<Stage_content> getListStageContent() {
		return findAll();
	}

	public void deleteStageContent(Stage_content content) {
		delete(content);
	}

	public Stage_content getStageContentById(Integer id) {
		return (Stage_content) getById(id);
	}

	public void addStageContent(Stage_content content) {
		save(content);
	}

	public void updateStageContent(Stage_content content) {
		update(content);
	}
}
