package com.seehope.service;

import com.seehope.dao.SurveyDao;
import com.seehope.dao.SurveyOptionDao;
import com.seehope.entity.Survey;
import com.seehope.entity.Survey_option;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SurveyOptionService {
	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private SurveyOptionDao surveyOptionDao;

	public ResultVo addSurveyOption(Survey_option survey_option) {
		ResultVo resultVo = new ResultVo("200", "��������");
		resultVo.setData(survey_option);
		try {
			Survey survey = (Survey) this.surveyDao.getById(survey_option.getSurvey().getId());
			survey_option.setCreate_time(DateTimeUtil.getCurDateTime());
			survey_option.setSurvey(survey);
			this.surveyOptionDao.save(survey_option);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo UpdateAnswer(Integer id, Integer answer) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			Survey_option survey_option = (Survey_option) this.surveyOptionDao.getById(id);
			survey_option.setAnswer(answer);
			this.surveyOptionDao.update(survey_option);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("����������");
		}
		return resultVo;
	}

	public ResultVo update_option_summary1(Integer id, String summary) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			Survey_option survey_option = (Survey_option) this.surveyOptionDao.getById(id);
			survey_option.setSummary(summary);
			this.surveyOptionDao.update(survey_option);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo deleteOption(Integer id) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			this.surveyOptionDao.deleteById(id);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo updateOptionSummary2(Integer id, String summary) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			List<Survey_option> survey_options = this.surveyOptionDao.findBy("survey.id", id);
			if (survey_options != null) {
				for (Survey_option survey_option : survey_options) {
					survey_option.setSummary(summary);
					this.surveyOptionDao.update(survey_option);
				}
			}
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("����������");
		}
		return resultVo;
	}
}
