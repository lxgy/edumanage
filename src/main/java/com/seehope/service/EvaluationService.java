package com.seehope.service;

import com.seehope.dao.EvaluationDao;
import com.seehope.dao.SeehopeStudentDao;
import com.seehope.entity.Evaluation;
import com.seehope.entity.Seehope_student;
import com.seehope.util.date.DateTimeUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EvaluationService {
	@Autowired
	EvaluationDao evaluationDao;
	@Autowired
	SeehopeStudentDao studentDao;

	public Evaluation getEvaluationByTypeStudent(Integer type, Seehope_student student) {
		Map<String, Object> by = new HashMap();
		by.put("student.id", student.getId());
		by.put("type", type);
		Evaluation evaluation = this.evaluationDao.getBy(by);
		if (evaluation == null) {
			evaluation = new Evaluation();
			evaluation.setStudent(student);
			evaluation.setType(type);
			evaluation.setCreate_time(DateTimeUtil.getCurDateTime());
			evaluation.setUpdate_time(DateTimeUtil.getCurDateTime());
			this.evaluationDao.save(evaluation);
		}
		return evaluation;
	}

	public List<Evaluation> findStudentEvaluation(Integer sid) {
		Seehope_student student = (Seehope_student) this.studentDao.getById(sid);
		List<Evaluation> list = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			list.add(getEvaluationByTypeStudent(Integer.valueOf(i), student));
		}
		return list;
	}

	public void updateEvaluation(Evaluation evaluation) {
		evaluation.setUpdate_time(DateTimeUtil.getCurDateTime());
		this.evaluationDao.update(evaluation);
	}

	public Evaluation getEvaluationById(Integer id) {
		return (Evaluation) this.evaluationDao.getById(id);
	}
}
