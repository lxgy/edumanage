package com.seehope.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_stage_question_student")
public class Stage_question_student extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "paper_id")
	private Stage_question_paper paper;
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Stage_question question;
	@Column(name = "answer", length = 500)
	private String answer;
	@Column(name = "is_right")
	private Integer is_right;
	@Transient
	private List<Stage_question_option> options;

	public Stage_question getQuestion() {
		return this.question;
	}

	public void setQuestion(Stage_question question) {
		this.question = question;
	}

	public Stage_question_paper getPaper() {
		return this.paper;
	}

	public void setPaper(Stage_question_paper paper) {
		this.paper = paper;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<Stage_question_option> getOptions() {
		return this.options;
	}

	public void setOptions(List<Stage_question_option> options) {
		this.options = options;
	}

	public Integer getIs_right() {
		return this.is_right;
	}

	public void setIs_right(Integer is_right) {
		this.is_right = is_right;
	}
}
