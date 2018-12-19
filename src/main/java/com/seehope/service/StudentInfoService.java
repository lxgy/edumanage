package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.StudentInfoDao;
import com.seehope.dao.SurveyPaperDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Student_info;
import com.seehope.entity.Survey_paper;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentInfoService {
	@Autowired
	private StudentInfoDao studentInfoDao;
	@Autowired
	private SurveyPaperDao surveyPaperDao;

	public void ajax_regist(Student_info student_info) {
		student_info.setCreate_time(DateTimeUtil.getCurDateTime());
		student_info.setSurvey(Integer.valueOf(0));
		this.studentInfoDao.save(student_info);
	}

	public Student_info getStudentInfoByPhone(String phone) {
		Student_info student_info = (Student_info) this.studentInfoDao.getBy("phone", phone);
		return student_info;
	}

	public ResultVo isSurvey(Integer id) {
		ResultVo resultVo = new ResultVo("500", "��������");
		try {
			Student_info student_info = (Student_info) this.studentInfoDao.getById(id);
			if (student_info.getSurvey().intValue() == 1) {
				resultVo.setCode("200");
				resultVo.setMsg("��������");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public Page<Student_info> findStudentInfos(PageRequest pageRequest, String keyText) {
		QueryWhere queryWhere = new QueryWhere();
		try {
			if ((keyText != null) && (!keyText.equals(""))) {
				queryWhere.andLikeAll("username", keyText);
			}
			return this.studentInfoDao.findByWhere(queryWhere, pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Student_info getStudentInfoById(Integer id) {
		return (Student_info) this.studentInfoDao.getById(id);
	}

	public void deleteStudentInfoById(Integer id) {
		this.studentInfoDao.deleteById(id);
		List<Survey_paper> survey_papers = this.surveyPaperDao.findBy("student_info.id", id);
		if (survey_papers != null) {
			for (Survey_paper survey_paper : survey_papers) {
				this.surveyPaperDao.delete(survey_paper);
			}
		}
	}
}
