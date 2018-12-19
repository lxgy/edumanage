package com.seehope.service;

import com.seehope.dao.StudentInfoDao;
import com.seehope.dao.SurveyDao;
import com.seehope.dao.SurveyOptionDao;
import com.seehope.dao.SurveyPaperDao;
import com.seehope.entity.Student_info;
import com.seehope.entity.Survey;
import com.seehope.entity.Survey_option;
import com.seehope.entity.Survey_paper;
import com.seehope.entity.vo.StudentPaperVo;
import com.seehope.util.date.DateTimeUtil;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SurveyPaperService {
	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private SurveyOptionDao surveyOptionDao;
	@Autowired
	private SurveyPaperDao surveyPaperDao;
	@Autowired
	private StudentInfoDao studentInfoDao;

	public List<Survey> surveyQuestion() {
		List<Survey> surveys = this.surveyDao.findBy("status", Integer.valueOf(1));
		return surveys;
	}

	public List<Survey_option> surveyOptions() {
		List<Survey_option> survey_options = this.surveyOptionDao.findAll();
		return survey_options;
	}

	public List<Survey_paper> surveyPapers(Integer id) {
		List<Survey_paper> survey_papers = this.surveyPaperDao.findBy("student_info.id", id);
		System.out.println(survey_papers);
		return survey_papers;
	}

	public boolean studentSurvey(StudentPaperVo studentPaperVo) {
		List<Survey_paper> survey_papers = this.surveyPaperDao.findBy("student_info.id", studentPaperVo.getId());
		for (Survey_paper survey_paper : survey_papers) {
			this.surveyPaperDao.delete(survey_paper);
		}
		String[] danswer = studentPaperVo.getDanswer();

		String[] wanswer = studentPaperVo.getWanswer();

		System.out.println("***********************changudn*************");
		System.out.println(danswer.length);
		System.out.println(wanswer.length);
		System.out.println("***********************changudn*************");

		System.out.println(Arrays.toString(danswer));

		System.out.println(Arrays.toString(wanswer));

		Student_info student_info = new Student_info();
		student_info = (Student_info) this.studentInfoDao.getById(Integer.valueOf(studentPaperVo.getId().intValue()));
		student_info.setSurvey(Integer.valueOf(1));
		student_info.setUpdate_time(DateTimeUtil.getCurDateTime());
		this.studentInfoDao.update(student_info);

		String str_sur = "";

		String str_opt = "";
		try {
			String[] arrayOfString1;
			int j = (arrayOfString1 = danswer).length;
			for (int i = 0; i < j; i++) {
				String string = arrayOfString1[i];
				if (string != "") {
					Survey_paper survey_paper = new Survey_paper();

					str_opt = string.substring(0, string.lastIndexOf("_"));
					survey_paper.setSubmit_summary(str_opt);

					str_sur = string.substring(string.lastIndexOf("_") + 1);
					survey_paper.setSurvey((Survey) this.surveyDao.getById(Integer.valueOf(str_sur)));

					survey_paper.setStudent_info((Student_info) this.studentInfoDao.getById(studentPaperVo.getId()));

					survey_paper.setCreate_time(DateTimeUtil.getCurDateTime());
					this.surveyPaperDao.save(survey_paper);
				}
			}
			j = (arrayOfString1 = wanswer).length;
			for (int i = 0; i < j; i++) {
				String string = arrayOfString1[i];
				if (string != "") {
					str_sur = string.substring(string.lastIndexOf("_") + 1);
					Survey_paper survey_paper = new Survey_paper();
					survey_paper.setSurvey((Survey) this.surveyDao.getById(Integer.valueOf(str_sur)));
					survey_paper.setStudent_info((Student_info) this.studentInfoDao
							.getById(Integer.valueOf(studentPaperVo.getId().intValue())));
					String summary = string.substring(0, string.lastIndexOf("_"));
					if (summary.equals("")) {
						return false;
					}
					survey_paper.setSubmit_summary(summary);
					survey_paper.setCreate_time(DateTimeUtil.getCurDateTime());
					this.surveyPaperDao.save(survey_paper);
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("��������....");
			e.printStackTrace();
		}
		return false;
	}
}
