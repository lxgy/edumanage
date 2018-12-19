package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.StageQuestionDao;
import com.seehope.dao.StageQuestionOptionDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Stage_question;
import com.seehope.entity.Stage_question_option;
import com.seehope.util.HtmlUtil;
import com.seehope.util.date.DateTimeUtil;
import com.seehope.web.vo.PageResult;
import com.seehope.web.vo.ResultVo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageQuestionService {
	@Autowired
	private StageQuestionDao questionDao;
	@Autowired
	private StageQuestionOptionDao optionDao;

	public PageResult<Stage_question> findQuestions(PageRequest pageRequest, String keyText, Integer type,
			Integer stage_type, Integer level) {
		PageResult<Stage_question> pageResult = new PageResult(Integer.valueOf(200), "��������");
		QueryWhere queryWhere = new QueryWhere();
		queryWhere.and("status", Integer.valueOf(1));
		try {
			if ((keyText != null) && (!keyText.equals(""))) {
				queryWhere.andLikeAll("question", keyText);
			}
			if (stage_type != null) {
				queryWhere.and("stage_type", stage_type);
			}
			if (type != null) {
				queryWhere.and("type", type);
			}
			if (level != null) {
				queryWhere.and("level", level);
			}
			Page<Stage_question> page = this.questionDao.findByWhere(queryWhere, pageRequest);

			List<Stage_question> list = page.getResults();
			for (Stage_question stage_question : list) {
				this.questionDao.getSessionFactory().getCurrentSession().evict(stage_question);
				String html = HtmlUtil.htmlToString(stage_question.getQuestion());
				stage_question.setQuestion(html);
			}
			pageResult.setPage(page);
		} catch (Exception e) {
			e.printStackTrace();
			pageResult.setCode(Integer.valueOf(500));
			pageResult.setMsg("��������");
		}
		return pageResult;
	}

	public ResultVo addQuestion(Stage_question question) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			String create_time = DateTimeUtil.getCurDateTime();
			question.setCreate_time(create_time);
			question.setUpdate_time(create_time);
			question.setStatus(Integer.valueOf(1));
			this.questionDao.save(question);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public Stage_question getQuestionById(Integer id) {
		return (Stage_question) this.questionDao.getById(id);
	}

	public Stage_question getQuestionOptionById(Integer id) {
		Stage_question question = (Stage_question) this.questionDao.getById(id);
		this.questionDao.getSessionFactory().getCurrentSession().evict(question);
		String html = HtmlUtil.htmlToString(question.getQuestion());
		question.setQuestion(html);

		List<Stage_question_option> options = this.optionDao.findBy("stage_question.id", id);
		if (options != null) {
			for (Stage_question_option option : options) {
				this.optionDao.getSessionFactory().getCurrentSession().evict(option);
				String summary = HtmlUtil.htmlToString(option.getSummary());
				option.setSummary(summary);
			}
			question.setOptions(options);
		}
		return question;
	}

	public ResultVo delete_question(Integer id) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			Stage_question question = (Stage_question) this.questionDao.getById(id);
			question.setStatus(Integer.valueOf(0));
			question.setUpdate_time(DateTimeUtil.getCurDateTime());
			this.questionDao.update(question);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public ResultVo updateQuestion(Stage_question question) {
		ResultVo resultVo = new ResultVo("200", "��������");
		try {
			Stage_question saveQuestion = (Stage_question) this.questionDao.getById(question.getId());
			String update_time = DateTimeUtil.getCurDateTime();
			saveQuestion.setStage_type(question.getStage_type());
			saveQuestion.setUpdate_time(update_time);
			saveQuestion.setQuestion(question.getQuestion());
			saveQuestion.setType(question.getType());
			saveQuestion.setIdea(question.getIdea());
			saveQuestion.setLevel(question.getLevel());
			this.questionDao.update(saveQuestion);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setCode("500");
			resultVo.setMsg("��������");
		}
		return resultVo;
	}

	public Page<Stage_question> findQuestionOption(PageRequest pageRequest, Integer type, Integer stage_id,
			Integer stage_type) {
		QueryWhere queryWhere = new QueryWhere();
		queryWhere.and("status", Integer.valueOf(1));
		if (stage_id != null) {
			queryWhere.and("stage.id", stage_id);
		} else if (stage_type != null) {
			queryWhere.and("stage.type", stage_type);
		}
		if (type != null) {
			queryWhere.and("type", type);
		}
		Page<Stage_question> page = this.questionDao.findByWhere(queryWhere, pageRequest);
		for (Stage_question question : page.getResults()) {
			this.questionDao.getSessionFactory().getCurrentSession().evict(question);
			String html = HtmlUtil.htmlToString(question.getQuestion());
			question.setQuestion(html);

			List<Stage_question_option> options = this.optionDao.findBy("stage_question.id", question.getId());
			for (Stage_question_option option : options) {
				this.optionDao.getSessionFactory().getCurrentSession().evict(option);
				String summary = HtmlUtil.htmlToString(option.getSummary());
				option.setSummary(summary);
			}
			question.setOptions(options);
		}
		return page;
	}
}
