package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_stage_question_option")
public class Stage_question_option extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Stage_question stage_question;
	@Column(name = "summary", length = 200)
	private String summary;
	@Column(name = "is_answer")
	private Integer is_answer;

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Stage_question getStage_question() {
		return this.stage_question;
	}

	public void setStage_question(Stage_question stage_question) {
		this.stage_question = stage_question;
	}

	public Integer getIs_answer() {
		return this.is_answer;
	}

	public void setIs_answer(Integer is_answer) {
		this.is_answer = is_answer;
	}
}
