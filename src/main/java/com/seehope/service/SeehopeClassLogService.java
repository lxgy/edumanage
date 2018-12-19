package com.seehope.service;

import com.seehope.dao.SeehopeClassLogDao;
import com.seehope.entity.Seehope_class_log;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeehopeClassLogService {
	@Autowired
	SeehopeClassLogDao logDao;

	public List<Seehope_class_log> findClassLog(Integer cid) {
		return this.logDao.findBy("seehope_class.id", cid);
	}
}
