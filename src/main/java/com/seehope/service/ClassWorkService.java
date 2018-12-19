package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.ClassWorkDao;
import com.seehope.entity.Class_work;
import com.seehope.util.date.DateTimeUtil;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClassWorkService {
	@Autowired
	ClassWorkDao classWorkDao;

	public void save(Class_work class_work) {
		String now = DateTimeUtil.getCurDateTime();
		class_work.setUpdate_time(now);
		if (class_work.getId() == null) {
			class_work.setCreate_time(now);
			this.classWorkDao.save(class_work);
		} else {
			this.classWorkDao.update(class_work);
		}
	}

	public Page<Class_work> pageWork(PageRequest pageRequest, Integer class_id) {
		return this.classWorkDao.findBy("seehope_class.id", class_id, pageRequest);
	}

	public Class_work getById(Integer id) {
		return (Class_work) this.classWorkDao.getById(id);
	}

	public List<Class_work> findAll(Integer class_id) {
		List<Class_work> list = this.classWorkDao.findBy("seehope_class.id", class_id);
		Collections.reverse(list);
		return list;
	}
}
