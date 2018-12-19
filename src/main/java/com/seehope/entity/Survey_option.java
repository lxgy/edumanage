package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_survey_option")
public class Survey_option extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "survey")
	private Survey survey;
	@Column(name = "summary")
	private String summary;
	@Column(name = "answer")
	private Integer answer;

	public Survey getSurvey() {
		return this.survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public String toString() {
		return "Survey_option [survey=" + this.survey + ", summary=" + this.summary + ", answer=" + this.answer + "]";
	}
}
