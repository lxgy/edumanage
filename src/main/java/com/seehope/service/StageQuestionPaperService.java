package com.seehope.service;

import com.seehope.core.page.Page;
import com.seehope.core.page.PageRequest;
import com.seehope.dao.StageQuestionDao;
import com.seehope.dao.StageQuestionOptionDao;
import com.seehope.dao.StageQuestionPaperDao;
import com.seehope.dao.StageQuestionStudentDao;
import com.seehope.dao.core.QueryWhere;
import com.seehope.entity.Seehope_student;
import com.seehope.entity.Stage_question;
import com.seehope.entity.Stage_question_option;
import com.seehope.entity.Stage_question_paper;
import com.seehope.entity.Stage_question_student;
import com.seehope.util.ArrayUtil;
import com.seehope.util.HtmlUtil;
import com.seehope.util.date.DateTimeUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageQuestionPaperService {
	@Autowired
	private StageQuestionDao questionDao;
	@Autowired
	private StageQuestionOptionDao optionDao;
	@Autowired
	private StageQuestionPaperDao paperDao;
	@Autowired
	private StageQuestionStudentDao questionStudentDao;

	public Stage_question_paper createPaper(Integer stage_type) {
		Stage_question_paper paper = new Stage_question_paper();

		QueryWhere where_choice = null;
		QueryWhere where_answer = null;

		Map<String, Object> by = new HashMap();

		by.put("status", Integer.valueOf(1));
		if (stage_type != null) {
			by.put("stage_type", stage_type);
			paper.setType(stage_type);
		}
		by.put("level", Integer.valueOf(1));
		where_choice = new QueryWhere(by);
		where_choice.andNotIn("type", new Number[] { Integer.valueOf(3) });
		List<Stage_question> list_choice_level1 = this.questionDao.findByWhere(where_choice);
		where_answer = new QueryWhere(by);
		where_answer.and("type", Integer.valueOf(3));
		List<Stage_question> list_answer_level1 = this.questionDao.findByWhere(where_answer);

		by.put("level", Integer.valueOf(2));
		where_choice = new QueryWhere(by);
		where_choice.andNotIn("type", new Number[] { Integer.valueOf(3) });
		List<Stage_question> list_choice_level2 = this.questionDao.findByWhere(where_choice);
		where_answer = new QueryWhere(by);
		where_answer.and("type", Integer.valueOf(3));
		List<Stage_question> list_answer_level2 = this.questionDao.findByWhere(where_answer);

		by.put("level", Integer.valueOf(3));
		where_choice = new QueryWhere(by);
		where_choice.andNotIn("type", new Number[] { Integer.valueOf(3) });
		List<Stage_question> list_choice_level3 = this.questionDao.findByWhere(where_choice);
		where_answer = new QueryWhere(by);
		where_answer.and("type", Integer.valueOf(3));
		List<Stage_question> list_answer_level3 = this.questionDao.findByWhere(where_answer);

		List<Stage_question_student> list = new ArrayList();
		if ((list_choice_level1 != null) && (!list_choice_level1.isEmpty())) {
			Collections.shuffle(list_choice_level1);
			for (int i = 0; (i < 6) && (i < list_choice_level1.size()); i++) {
				Stage_question_student question_student = new Stage_question_student();
				question_student.setQuestion((Stage_question) list_choice_level1.get(i));
				list.add(question_student);
			}
		}
		if ((list_choice_level2 != null) && (!list_choice_level2.isEmpty())) {
			Collections.shuffle(list_choice_level2);
			for (int i = 0; (i < 3) && (i < list_choice_level2.size()); i++) {
				Stage_question_student question_student = new Stage_question_student();
				question_student.setQuestion((Stage_question) list_choice_level2.get(i));
				list.add(question_student);
			}
		}
		if ((list_choice_level3 != null) && (!list_choice_level3.isEmpty())) {
			Collections.shuffle(list_choice_level3);
			for (int i = 0; (i < 1) && (i < list_choice_level3.size()); i++) {
				Stage_question_student question_student = new Stage_question_student();
				question_student.setQuestion((Stage_question) list_choice_level3.get(i));
				list.add(question_student);
			}
		}
		if ((list_answer_level1 != null) && (!list_answer_level1.isEmpty())) {
			Collections.shuffle(list_answer_level1);
			for (int i = 0; (i < 6) && (i < list_answer_level1.size()); i++) {
				Stage_question_student question_student = new Stage_question_student();
				question_student.setQuestion((Stage_question) list_answer_level1.get(i));
				list.add(question_student);
			}
		}
		if ((list_answer_level2 != null) && (!list_answer_level2.isEmpty())) {
			Collections.shuffle(list_answer_level2);
			for (int i = 0; (i < 3) && (i < list_answer_level2.size()); i++) {
				Stage_question_student question_student = new Stage_question_student();
				question_student.setQuestion((Stage_question) list_answer_level2.get(i));
				list.add(question_student);
			}
		}
		Stage_question_student question_student;
		if ((list_answer_level3 != null) && (!list_answer_level3.isEmpty())) {
			Collections.shuffle(list_answer_level3);
			for (int i = 0; (i < 1) && (i < list_answer_level3.size()); i++) {
				question_student = new Stage_question_student();
				question_student.setQuestion((Stage_question) list_answer_level3.get(i));
				list.add(question_student);
			}
		}
		for (Stage_question_student stage_question_student : list) {
			this.questionStudentDao.getSessionFactory().getCurrentSession().evict(stage_question_student.getQuestion());
			String question = HtmlUtil.htmlToString(stage_question_student.getQuestion().getQuestion());
			stage_question_student.getQuestion().setQuestion(question);

			List<Stage_question_option> options = this.optionDao.findBy("stage_question.id",
					stage_question_student.getQuestion().getId());
			for (Stage_question_option option : options) {
				this.optionDao.getSessionFactory().getCurrentSession().evict(option);
				String summary = HtmlUtil.htmlToString(option.getSummary());
				option.setSummary(summary);
			}
			stage_question_student.getQuestion().setOptions(options);
		}
		paper.setQuestion_students(list);
		return paper;
	}

	public Stage_question_paper getPaperDetailsById(Integer id) {
		Stage_question_paper paper = (Stage_question_paper) this.paperDao.getById(id);
		this.paperDao.getSessionFactory().getCurrentSession().evict(paper);
		List<Stage_question_student> list = this.questionStudentDao.findBy("paper.id", paper.getId());
		for (Stage_question_student stage_question_student : list) {
			this.questionStudentDao.getSessionFactory().getCurrentSession().evict(stage_question_student.getQuestion());
			String question = HtmlUtil.htmlToString(stage_question_student.getQuestion().getQuestion());
			stage_question_student.getQuestion().setQuestion(question);

			List<Stage_question_option> options = this.optionDao.findBy("stage_question.id",
					stage_question_student.getQuestion().getId());
			for (Stage_question_option option : options) {
				this.optionDao.getSessionFactory().getCurrentSession().evict(option);
				String summary = HtmlUtil.htmlToString(option.getSummary());
				System.out.println(summary);
				option.setSummary(summary);
			}
			stage_question_student.getQuestion().setOptions(options);
			if ((stage_question_student.getQuestion().getType().intValue() == 1)
					|| (stage_question_student.getQuestion().getType().intValue() == 2)) {
				List<Stage_question_option> answer_options = new ArrayList();
				String[] answer = stage_question_student.getAnswer().split(",");
				String[] arrayOfString1;
				int j = (arrayOfString1 = answer).length;
				for (int i = 0; i < j; i++) {
					String string = arrayOfString1[i];
					answer_options.add((Stage_question_option) this.optionDao.getById(Integer.valueOf(string)));
				}
				stage_question_student.setOptions(answer_options);
			}
		}
		paper.setQuestion_students(list);
		return paper;
	}

	public Page<Stage_question_paper> findPaperByStudentId(PageRequest pageRequest, Integer sid) {
		return this.paperDao.findBy("student.id", sid, pageRequest);
	}

	public Stage_question_paper submitPaper(List<Stage_question_student> question_students, Integer id) {
		Stage_question_paper paper = (Stage_question_paper) this.paperDao.getById(id);
		String now_time = DateTimeUtil.getCurDateTime();
		paper.setUpdate_time(now_time);

		int choice_num = 0;
		int right_num = 0;
		for (Stage_question_student question_student : question_students) {
			question_student.setPaper(paper);
			question_student.setCreate_time(now_time);
			question_student.setUpdate_time(now_time);

			List<Stage_question_option> options = this.optionDao.findBy("stage_question.id",
					question_student.getQuestion().getId());
			if (question_student.getQuestion().getType().intValue() == 1) {
				for (Stage_question_option option : options) {
					if ((option.getIs_answer() != null) && (option.getIs_answer().intValue() == 1)
							&& (question_student.getAnswer().equals(option.getId().toString()))) {
						question_student.setIs_right(Integer.valueOf(1));
						right_num++;
						break;
					}
					question_student.setIs_right(Integer.valueOf(0));
				}
				choice_num++;
			} else if (question_student.getQuestion().getType().intValue() == 2) {
				List<String> list = new ArrayList();
				for (Stage_question_option option : options) {
					if ((option.getIs_answer() != null) && (option.getIs_answer().intValue() == 1)) {
						list.add(option.getId().toString());
					}
				}
				String[] answer = question_student.getAnswer().split(",");
				if (ArrayUtil.compareIgnoreSort(list, answer)) {
					question_student.setIs_right(Integer.valueOf(1));
					right_num++;
				} else {
					question_student.setIs_right(Integer.valueOf(0));
				}
				choice_num++;
			}
		}
		this.questionStudentDao.save(question_students);
		paper.setChoice_num(Integer.valueOf(choice_num));
		paper.setRight_num(Integer.valueOf(right_num));
		this.paperDao.update(paper);
		return paper;
	}

	public Stage_question_paper submitPaperType(Stage_question_paper paper, Seehope_student student) {
		String now_time = DateTimeUtil.getCurDateTime();
		paper.setCreate_time(now_time);
		paper.setUpdate_time(now_time);
		paper.setStudent(student);
		this.paperDao.save(paper);
		return paper;
	}
}
