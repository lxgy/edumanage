package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.SurveyDao;
import com.seehope.dao.SurveyOptionDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Survey;
import com.seehope.entity.Survey_option;
import com.seehope.util.HtmlUtil;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SurveyService {
	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private SurveyOptionDao surveyOptionDao;

	public PageResult<Survey> findSurvey(PageRequest pageRequest, String keyText, Integer type) {
		PageResult<Survey> pageResult = new PageResult(Integer.valueOf(200), "��������");
		QueryWhere queryWhere = new QueryWhere();
		queryWhere.and("status", Integer.valueOf(1));
		try {
			if ((keyText != null) && (!keyText.equals(""))) {
				queryWhere.andLikeAll("description", keyText);
			}
			if (type != null) {
				queryWhere.and("type", type);
			}
			Page<Survey> page = this.surveyDao.findByWhere(queryWhere, pageRequest);

			List<Survey> surveys = page.getResults();
			if (surveys != null) {
				for (Survey survey : surveys) {
					this.surveyDao.getSessionFactory().getCurrentSession().evict(survey);
					String html = HtmlUtil.htmlToString(survey.getDescription());
					survey.setDescription(html);
				}
			}
			pageResult.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pageResult.setCode(Integer.valueOf(500));
			pageResult.setMsg("��������");
		}
		return pageResult;
	}

	public Survey getSurveyById(Integer id) {
		Survey survey = (Survey) this.surveyDao.getById(id);
		return survey;
	}

	public ResultVo deleteSurveyById(Integer id) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			this.surveyDao.deleteById(id);
			List<Survey_option> survey_options = this.surveyOptionDao.findBy("survey.id", id);
			if (survey_options != null) {
				for (Survey_option survey_option : survey_options) {
					this.surveyOptionDao.delete(survey_option);
				}
			}
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("����������");
		}
		return resultVo;
	}

	public ResultVo updateSurvey(Survey survey) {
		ResultVo resultVo = new ResultVo("200", "��������");
		String update_time = DateTimeUtil.getCurDateTime();
		survey.setStatus(Integer.valueOf(1));
		survey.setUpdate_time(update_time);
		try {
			this.surveyDao.update(survey);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo addSurvey(Survey survey) {
		ResultVo resultVo = new ResultVo("200", "��������");
		survey.setCreate_time(DateTimeUtil.getCurDateTime());
		survey.setStatus(Integer.valueOf(1));
		try {
			this.surveyDao.save(survey);
		} catch (Exception e) {
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public Survey getSurveyOption(Integer id) {
		Survey survey = (Survey) this.surveyDao.getById(id);
		this.surveyDao.getSessionFactory().getCurrentSession().evict(survey);
		String html = HtmlUtil.htmlToString(survey.getDescription());
		survey.setDescription(html);

		List<Survey_option> survey_options = this.surveyOptionDao.findBy("survey.id", id);
		if (survey_options != null) {
			for (Survey_option survey_option : survey_options) {
				this.surveyOptionDao.getSessionFactory().getCurrentSession().evict(survey_option);
				String summary = HtmlUtil.htmlToString(survey_option.getSummary());
				survey_option.setSummary(summary);
			}
			survey.setSurvey_options(survey_options);
		}
		return survey;
	}
}
