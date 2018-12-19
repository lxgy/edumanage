package com.seehope.service;

import com.seehope.dao.SeehopeStudentClassLogDao;
import com.seehope.entity.Seehope_student_class_log;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeehopeStudentClassLogService {
	@Autowired
	SeehopeStudentClassLogDao logDao;

	public List<Seehope_student_class_log> findStuClassLog(Integer tid) {
		return this.logDao.findBy("student.id", tid);
	}
}
