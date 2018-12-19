package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_survey_paper")
public class Survey_paper extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "student_info")
	private Student_info student_info;
	@ManyToOne
	@JoinColumn(name = "survey")
	private Survey survey;
	@Column(name = "submit_summary", nullable = false)
	private String submit_summary;

	public Student_info getStudent_info() {
		return this.student_info;
	}

	public void setStudent_info(Student_info student_info) {
		this.student_info = student_info;
	}

	public Survey getSurvey() {
		return this.survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public String getSubmit_summary() {
		return this.submit_summary;
	}

	public void setSubmit_summary(String submit_summary) {
		this.submit_summary = submit_summary;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public String toString() {
		return "Survey_paper [student_info=" + this.student_info + ", survey=" + this.survey + ", submit_summary="
				+ this.submit_summary + "]";
	}
}
