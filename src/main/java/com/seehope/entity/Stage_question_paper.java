package com.seehope.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_stage_question_paper")
public class Stage_question_paper extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Seehope_student student;
	@Column(name = "type")
	private Integer type;
	@Column(name = "choice_num")
	private Integer choice_num;
	@Column(name = "right_num")
	private Integer right_num;
	@Transient
	private List<Stage_question_student> question_students;

	public Seehope_student getStudent() {
		return this.student;
	}

	public void setStudent(Seehope_student student) {
		this.student = student;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Stage_question_student> getQuestion_students() {
		return this.question_students;
	}

	public void setQuestion_students(List<Stage_question_student> question_students) {
		this.question_students = question_students;
	}

	public Integer getChoice_num() {
		return this.choice_num;
	}

	public void setChoice_num(Integer choice_num) {
		this.choice_num = choice_num;
	}

	public Integer getRight_num() {
		return this.right_num;
	}

	public void setRight_num(Integer right_num) {
		this.right_num = right_num;
	}
}
