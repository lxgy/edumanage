package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_evaluation")
public class Evaluation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Seehope_student student;
	@Column(name = "content", length = 500)
	private String content;
	@Column(name = "type")
	private Integer type;
	@Column(name = "teacher_evaluation", length = 200)
	private String teacher_evaluation;
	@Column(name = "status")
	private Integer status;

	public Seehope_student getStudent() {
		return this.student;
	}

	public void setStudent(Seehope_student student) {
		this.student = student;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTeacher_evaluation() {
		return this.teacher_evaluation;
	}

	public void setTeacher_evaluation(String teacher_evaluation) {
		this.teacher_evaluation = teacher_evaluation;
	}
}
