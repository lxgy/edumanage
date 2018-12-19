package com.seehope.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_stage")
public class Stage extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "status")
	private Integer status;
	@Column(name = "name", length = 32)
	private String name;
	@Column(name = "type")
	private Integer type;
	@Transient
	private List<Stage_content> contents;
	@Transient
	private List<Stage_content_student> student_evaluation;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Stage_content> getContents() {
		return this.contents;
	}

	public void setContents(List<Stage_content> contents) {
		this.contents = contents;
	}

	public List<Stage_content_student> getStudent_evaluation() {
		return this.student_evaluation;
	}

	public void setStudent_evaluation(List<Stage_content_student> student_evaluation) {
		this.student_evaluation = student_evaluation;
	}

	public String toString() {
		return "Stage [status=" + this.status + ", name=" + this.name + ", type=" + this.type + "]";
	}
}
