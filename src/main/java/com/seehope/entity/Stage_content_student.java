package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_stage_content_student")
public class Stage_content_student extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "stage_content_id")
	private Stage_content stage_content;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Seehope_student student;
	@Column(name = "teacher_score", length = 20)
	private String teacher_score;
	@Column(name = "student_score", length = 20)
	private String student_score;
	@Column(name = "evaluation", length = 200)
	private String evaluation;
	@Column(name = "teacher_evaluation", length = 100)
	private String teacher_evaluation;
	@Column(name = "status")
	private Integer status;

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Seehope_student getStudent() {
		return this.student;
	}

	public void setStudent(Seehope_student student) {
		this.student = student;
	}

	public String getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public Stage_content getStage_content() {
		return this.stage_content;
	}

	public void setStage_content(Stage_content stage_content) {
		this.stage_content = stage_content;
	}

	public String getTeacher_score() {
		return this.teacher_score;
	}

	public void setTeacher_score(String teacher_score) {
		this.teacher_score = teacher_score;
	}

	public String getStudent_score() {
		return this.student_score;
	}

	public void setStudent_score(String student_score) {
		this.student_score = student_score;
	}

	public String getTeacher_evaluation() {
		return this.teacher_evaluation;
	}

	public void setTeacher_evaluation(String teacher_evaluation) {
		this.teacher_evaluation = teacher_evaluation;
	}
}
