package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_student_feedback")
public class Student_feedback extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "status")
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Seehope_student student;
	@Column(name = "content", length = 200)
	private String content;

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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
