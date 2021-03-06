package com.seehope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_seehope_student_class_log")
public class Seehope_student_class_log extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Seehope_student student;
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Seehope_class seehope_class;
	@Column(name = "remark", length = 200)
	private String remark;

	public Seehope_student getStudent() {
		return this.student;
	}

	public void setStudent(Seehope_student student) {
		this.student = student;
	}

	public Seehope_class getSeehope_class() {
		return this.seehope_class;
	}

	public void setSeehope_class(Seehope_class seehope_class) {
		this.seehope_class = seehope_class;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
